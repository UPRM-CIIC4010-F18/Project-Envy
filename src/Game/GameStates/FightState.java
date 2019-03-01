package Game.GameStates;

import Game.Entities.Dynamics.BaseHostileEntity;
import Main.GameSetUp;
import Main.Handler;

import Resources.Animation;
import Resources.Images;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.Arrays;
import java.util.Random;

import Display.UI.ClickListlener;
import Display.UI.UIImageButton;
import Display.UI.UIManager;


@SuppressWarnings("Duplicates")
public class FightState extends InWorldState{
    private UIManager uiManager;
    private int entityY;

    BaseHostileEntity enemy;
    Rectangle enemyRect, playerRect;

    public int fightWordXPos = handler.getWidth()/2 - 250;
    public int fightWordYPos = 0 - 80;
    public int wordHeight = 150;
    public boolean passing = true;
    public int stringSpeed = 40;

    private int optionSelect, inputCoolDown;
    private int[] entityInfoX;
    Image background;

    public int turn=0,numOfEnemies=1; // 0= player ; else is enemy
    private boolean attacking=false,defense=false,skill=false,endTurn=false,attacked=false,isDefense=false;

    private int attackSpeed =40;

    private Animation playerIceSkill;
    private Animation playerDefenceMode;
    private Animation playerAttackMode;
    private Animation enemyFireSkill;

    private boolean Eattacking=false,Edefense=false,Eskill=false,EendTurn=false,Eattacked=false,EisDefense=false,battleOver =false;

    private int EattackSpeed =40;
    private String dmg = "";
    private double lvlUp = 0;

    private int green= 255,red=95,blue=255,alpha=0;

    private String prevState;

    private BaseHostileEntity inStateEnemy;
	private long eWait = 0;


    public FightState(Handler handler ,BaseHostileEntity enemy, String prevState) {
        super(handler);
        this.prevState=prevState;
        entityY = (int) handler.getHeight() * 2/3;
        entityInfoX = new int[2];
        //player info square coordinate
        entityInfoX[0] = handler.getWidth() * 3/20;
        //enemy info square coordinate
        entityInfoX[1] = handler.getWidth() * 14/20 + 4;
        
        handler.getGame().getMusicHandler().set_changeMusic("res/music/BattleMusic.mp3");
        handler.getGame().getMusicHandler().play();
        handler.getGame().getMusicHandler().setVolume(0.2);

        inStateEnemy=enemy;

        this.enemy = (handler.newEnemy(enemy.frames,handler,handler.getWidth() * 4/ 5, entityY,enemy.foundState,enemy.name,enemy.Area,
                enemy.type,enemy.getHealth(),enemy.getMana(),enemy.getXp(),enemy.getLvl(),enemy.getStr(),enemy.getDefense(),
                enemy.getIntl(),enemy.getMr(),enemy.getCons(),enemy.getAcc(),enemy.getEvs(),enemy.getInitiative(),enemy.getclass(),enemy.getSkill(),
                enemy.getBuffs(),enemy.getDebuffs()));

        this.enemy.lvlAdjust();
        playerRect = new Rectangle( (int) handler.getWidth() / 5, entityY, 100, 100);
        enemyRect = new Rectangle((int) handler.getWidth() * 4/ 5 - 70,entityY, 70, 70);

        setUiManager();
        backgroundSelect(prevState);

        optionSelect = 0;
        inputCoolDown = 0;
        dmg = "";
        lvlUp = handler.getEntityManager().getPlayer().getLvlUpXp();

        playerIceSkill = new Animation(20,Images.IceSkill);
        playerDefenceMode = new Animation(15, Images.DefenceMode);
        playerAttackMode = new Animation(15, Images.AttackMode);

        enemyFireSkill = new Animation(20,Images.FireSkill); // @Sergio: "Replace this with Images.FireSkill so u can do ur stuff"


        chooseTurn();

    }

    private void chooseTurn() {
        if(handler.getEntityManager().getPlayer().getInitiative()>=enemy.getInitiative()){
            turn = 0;
        }else{
            turn = 1;
        }

    }

    @Override
    public void tick() {
        if(turn>numOfEnemies){
            turn=0;
        }

        if(!enemy.PEnemyIdle.getCurrentFrame().equals(null)) {
            enemy.PEnemyIdle.tick();
        }

        ///TEMP CODE TO EXIT FIGHT///
        if (handler.getKeyManager().keyJustPressed(KeyEvent.VK_ESCAPE)) {
            handler.getGame().pauseState.lastState = State.getState();
            GameSetUp.SWITCHING=true;
            State.setState(handler.getGame().pauseState);
        }
        /////////////////////////////

        else {
            if(!attacking&&!defense&&!skill&&turn>0&&enemy.getHealth()<=0&&!battleOver){
                battleOver=true;
                
                handler.getGame().getMusicHandler().stop();
                handler.getGame().getMusicHandler().playEffect("res/music/victory.mp3",0);
                handler.getGame().getMusicHandler().setEffectVolume(0, 0.3);
                
            }
            if(!Eattacking&&!Edefense&&!Eskill&&turn==0&&handler.getEntityManager().getPlayer().getHealth()<=0&&!battleOver){
                battleOver=true;
            }
            if (!battleOver) {
                if (!attacking&&!defense&&!skill&&turn == 0) {
                    PlayerInput();
                    uiManager.tick();

                }else if(!Eattacking&&!Edefense&&!Eskill&&turn > 0){
                	 if(System.currentTimeMillis() > eWait)
                		 enemyTurn();
                }

            }

        }

        this.moveFightString();



    }

    @Override
    public void render(Graphics g) {
        Graphics2D g2 = (Graphics2D)g;

        drawInfoSquare(g2);

        g2.setFont(new Font("Bank Gothic",3,15));
        g2.setStroke(new BasicStroke(1));
        drawCharacterInfo(g2);

        drawEntities(g2);

        Color color = new Color(0 , 0 , 0 , .5f); // transparentBlack
        drawFightBanner(g2,color);

        drawDebug(g);

        if(turn>0){
            g.setColor(color);
            g.fillRect(entityInfoX[0], handler.getHeight()* 4/5, (2*(handler.getWidth() * 3/20 - 4))+(handler.getWidth() * 8/20 - 8)+(10), handler.getHeight()/7);
        }
        if (turn == 0) {
            if(skill && handler.getEntityManager().getPlayer().getMana()>=25){
                callSkill(g);
            }else if (attacking) {
                attack(g);
            } else if (defense) {
                defend(g);
            }
        }else if (turn > 0){
            if(Eskill && enemy.getMana()>=25){
                EcallSkill(g);
            }else if(Eattacking){
                Eattack(g);
            }else if(Edefense){
                Edefend(g);
            }
        }

        if(battleOver){

            g2.setFont(new Font("IMPACT", 3, this.wordHeight));
            if(handler.getEntityManager().getPlayer().getHealth()==0){
                g.setColor(new Color(0,0,0,alpha+=2));
                g.fillRect(0,0,handler.getWidth(),handler.getHeight());
                g2.setColor(Color.RED);
                g.drawString("DEFEAT!",handler.getWidth()/3 - 50,handler.getHeight()/2);

            }else{
                g.setColor(new Color(255,255,255,alpha+=1));
                g.fillRect(0,0,handler.getWidth(),handler.getHeight());
                g2.setColor(Color.GREEN);
                g.drawString("VICTORY!",handler.getWidth()/3 - 50,handler.getHeight()/2);
                g2.setFont(new Font("IMPACT", 3, this.wordHeight/3));
                g.drawString("Experience gained: " + (int) enemy.getXp(),handler.getWidth()/3 + 10,handler.getHeight()/2 + 70);
                if(handler.getEntityManager().getPlayer().getXp() + enemy.getXp() >= lvlUp) {
                	 g2.setFont(new Font("IMPACT", 3, this.wordHeight/2));
                     g.drawString("LEVEL UP!",handler.getWidth()/3 + 100,handler.getHeight()/2 + 155);
                     g2.setFont(new Font("IMPACT", 3, this.wordHeight/3));
                     g.drawString( handler.getEntityManager().getPlayer().getLvl() + "  -> " + (handler.getEntityManager().getPlayer().getLvl()+1),handler.getWidth()/3 + 150,handler.getHeight()/2 + 230);
                }
                enemy.kill();
                inStateEnemy.kill();
            }
            if(alpha>=254){
                if(handler.getEntityManager().getPlayer().getHealth()==0){
                	
                    handler.getGame().reStart();
                    State.setState(handler.getGame().menuState);
                }else{
                	//restores an amount on hp
                	handler.getEntityManager().getPlayer().setHealth((int)(handler.getEntityManager().getPlayer().getHealth()+ ((handler.getEntityManager().getPlayer().getMaxHealth() - 
                			handler.getEntityManager().getPlayer().getHealth())* handler.getEntityManager().getPlayer().getCons()/100)));
                	if(handler.getEntityManager().getPlayer().getHealth() > handler.getEntityManager().getPlayer().getMaxHealth())
                		handler.getEntityManager().getPlayer().setHealth(handler.getEntityManager().getPlayer().getMaxHealth());
                	//Restores an amount of mp
                	handler.getEntityManager().getPlayer().setMana((int)(handler.getEntityManager().getPlayer().getMana()+ ((handler.getEntityManager().getPlayer().getMaxMana() - 
                			handler.getEntityManager().getPlayer().getMana())* handler.getEntityManager().getPlayer().getIntl()/100)));
                	if(handler.getEntityManager().getPlayer().getMana() > handler.getEntityManager().getPlayer().getMaxMana())
                		handler.getEntityManager().getPlayer().setMana(handler.getEntityManager().getPlayer().getMaxMana());
                	//Gives XP to the player
                	handler.getEntityManager().getPlayer().addXp(enemy.getXp());
                	
                    if(prevState.equals("None")){

                    	handler.getGame().getMusicHandler().set_changeMusic("res/music/OverWorld.mp3");
                        handler.getGame().getMusicHandler().play();
                        handler.getGame().getMusicHandler().setVolume(0.2);
                        
                        State.setState(handler.getGame().mapState);
                        handler.setArea("None");
                    }else{
                    	// cave music
                        handler.setArea(InWorldState.currentArea.name);
                        
                        handler.getGame().getMusicHandler().set_changeMusic("res/music/Cave.mp3");
                        handler.getGame().getMusicHandler().play();
                        handler.getGame().getMusicHandler().setVolume(0.4);
                        
                        State.setState(handler.getGame().inWorldState);
                    }
                }
            }
        }
    }

    private void drawEntities(Graphics2D g2) {
        //Draws entities
        g2.drawImage(handler.getEntityManager().getPlayer().getIdle(), playerRect.x, playerRect.y, playerRect.width, playerRect.height, null);
        g2.setColor(Color.BLACK);
        g2.drawImage(enemy.getIdle(), enemyRect.x, enemyRect.y, enemyRect.width, enemyRect.height, null);
    }

    private void drawDebug(Graphics g) {
        if(GameSetUp.DEBUGMODE){
            g.setFont((new Font("IMPACT", Font.ITALIC, 25)));
            g.drawString("Turn: "+String.valueOf(turn),handler.getWidth()/2,25);

            //player
            g.drawString("Accuracy: "+String.valueOf(handler.getEntityManager().getPlayer().getAcc()),0,300);
            g.drawString("XP: "+String.valueOf(handler.getEntityManager().getPlayer().getXp()),0,25);
            g.drawString("Level: "+String.valueOf(handler.getEntityManager().getPlayer().getLvl()),0,50);
            g.drawString("Strength: "+String.valueOf(handler.getEntityManager().getPlayer().getStr()),0,75);
            g.drawString("Defence: "+String.valueOf(handler.getEntityManager().getPlayer().getDefense()),0,100);
            g.drawString("Intelligence: "+String.valueOf(handler.getEntityManager().getPlayer().getIntl()),0,125);
            g.drawString("Constitution: "+String.valueOf(handler.getEntityManager().getPlayer().getCons()),0,150);
            g.drawString("Evasion: "+String.valueOf(handler.getEntityManager().getPlayer().getEvs()),0,175);
            g.drawString("Initiative: "+String.valueOf(handler.getEntityManager().getPlayer().getInitiative()),0,200);
            g.drawString("Class: "+String.valueOf(handler.getEntityManager().getPlayer().getclass()),0,225);
            g.drawString("Buffs: "+ Arrays.toString(handler.getEntityManager().getPlayer().getBuffs()),0,250);
            g.drawString("Debuffs: "+ Arrays.toString(handler.getEntityManager().getPlayer().getDebuffs()),0,275);
            g.drawString("Magic Resist: "+ String.valueOf(handler.getEntityManager().getPlayer().getMr()),0,325);
            //enemy
            g.drawString("Accuracy: "+String.valueOf(enemy.getAcc()),handler.getWidth()-200,300);
            g.drawString("XP: "+String.valueOf(enemy.getXp()),handler.getWidth()-200,25);
            g.drawString("Level: "+String.valueOf(enemy.getLvl()),handler.getWidth()-200,50);
            g.drawString("Strength: "+String.valueOf(enemy.getStr()),handler.getWidth()-200,75);
            g.drawString("Defence: "+String.valueOf(enemy.getDefense()),handler.getWidth()-200,100);
            g.drawString("Intelligence: "+String.valueOf(enemy.getIntl()),handler.getWidth()-200,125);
            g.drawString("Constitution: "+String.valueOf(enemy.getCons()),handler.getWidth()-200,150);
            g.drawString("Evasion: "+String.valueOf(enemy.getEvs()),handler.getWidth()-200,175);
            g.drawString("Initiative: "+String.valueOf(enemy.getInitiative()),handler.getWidth()-200,200);
            g.drawString("Class: "+String.valueOf(enemy.getclass()),handler.getWidth()-200,225);
            g.drawString("Buffs: "+ Arrays.toString(enemy.getBuffs()),handler.getWidth()-200,250);
            g.drawString("Debuffs: "+ Arrays.toString(enemy.getDebuffs()),handler.getWidth()-200,275);
            g.drawString("Magic Resist: "+ String.valueOf(enemy.getMr()),handler.getWidth()-200,325);
        }
    }

    private void drawFightBanner(Graphics2D g2,Color color) {
        //Fight string that appears in beginning
        if(this.isPassing()) {

            g2.setColor(color);

            g2.fillRect(0, 0, this.handler.getWidth(), this.handler.getHeight());

        }
        g2.setFont(new Font("IMPACT", 3, this.wordHeight));
        g2.setColor(Color.RED);
        g2.drawString("FIGHT!", this.fightWordXPos, this.fightWordYPos);
    }

    private void drawCharacterInfo(Graphics2D g2) {
        /*
         * used for drawing the info of the player and the Enemy.
         * When the entities have their own health, mp, name, etc.  it will be updated as to reflect the info of the
         * respective entity entity
         */
        for(int i = 0; i < 2;i++) {
            if(i==1) {//enemy
                g2.drawString(enemy.name, entityInfoX[i] + 15, (handler.getHeight() * 4 / 5) + 20);

                //draws health info
                if(enemy.getHealth()>=enemy.getMaxHealth() * 3/4){
                    g2.setColor(Color.GREEN);
                }else if(enemy.getHealth()>= enemy.getMaxHealth() * 1/2){
                    g2.setColor(Color.YELLOW);
                }else{
                    g2.setColor(Color.RED);
                }
                g2.fillRect(entityInfoX[i] + 15, (handler.getHeight() * 4 / 5) + 46, (int)((handler.getWidth() * 2 / 20)*(enemy.getHealth()/enemy.getMaxHealth())), 17);
                g2.setColor(Color.BLACK);
                g2.drawRect(entityInfoX[i] + 15, (handler.getHeight() * 4 / 5) + 46, handler.getWidth() * 2 / 20, 17);
                g2.drawString("Health: ", entityInfoX[i] + 15, (handler.getHeight() * 4 / 5) + 40);
                g2.drawString(String.valueOf(enemy.getHealth()), entityInfoX[i] + 16, (handler.getHeight() * 4 / 5) + 60);

                if(GameSetUp.DEBUGMODE) {
	                //Draws MP Information
	                g2.setColor(Color.BLUE);
	                g2.fillRect(entityInfoX[i] + 15, (handler.getHeight() * 4 / 5) + 86, (int)((handler.getWidth() * 2 / 20)*(enemy.getMana()/enemy.getMaxMana())), 17);
	                g2.setColor(Color.WHITE);
	                g2.drawRect(entityInfoX[i] + 15, (handler.getHeight() * 4 / 5) + 86, handler.getWidth() * 2 / 20, 17);
	                g2.drawString("Mana: ", entityInfoX[i] + 15, (handler.getHeight() * 4 / 5) + 80);
	                g2.drawString(String.valueOf(enemy.getMana()), entityInfoX[i] + 16, (handler.getHeight() * 4 / 5) + 100);
	
	                g2.drawString("Skill: " + String.valueOf(enemy.getSkill()), entityInfoX[i] + 15, (handler.getHeight() * 4 / 5) + 120);
	                g2.drawString("Mana Cost: " + "25 MP", entityInfoX[i] + 15, (handler.getHeight() * 4 / 5) + 140);
                }
            }else{//player
                g2.drawString("Player ", entityInfoX[i] + 15, (handler.getHeight() * 4 / 5) + 20);

                //draws health info
                if(handler.getEntityManager().getPlayer().getHealth()>= handler.getEntityManager().getPlayer().getMaxHealth() * 3/4){
                    g2.setColor(Color.GREEN);
                }else if(handler.getEntityManager().getPlayer().getHealth()>=handler.getEntityManager().getPlayer().getMaxHealth() * 1/2){
                    g2.setColor(Color.YELLOW);
                }else{
                    g2.setColor(Color.RED);
                }
                g2.fillRect(entityInfoX[i] + 15, (handler.getHeight() * 4 / 5) + 46, (int)((handler.getWidth() * 2 / 20)*(handler.getEntityManager().getPlayer().getHealth()/handler.getEntityManager().getPlayer().getMaxHealth())), 17);
                g2.setColor(Color.BLACK);
                g2.drawRect(entityInfoX[i] + 15, (handler.getHeight() * 4 / 5) + 46, handler.getWidth() * 2 / 20, 17);
                g2.drawString("Health: ", entityInfoX[i] + 15, (handler.getHeight() * 4 / 5) + 40);
                g2.drawString(String.valueOf(handler.getEntityManager().getPlayer().getHealth()), entityInfoX[i] + 16, (handler.getHeight() * 4 / 5) + 60);

                //Draws MP Information
                g2.setColor(Color.BLUE);
                g2.fillRect(entityInfoX[i] + 15, (handler.getHeight() * 4 / 5) + 86, (int)((handler.getWidth() * 2 / 20)*(handler.getEntityManager().getPlayer().getMana()/handler.getEntityManager().getPlayer().getMaxMana())), 17);
                g2.setColor(Color.WHITE);
                g2.drawRect(entityInfoX[i] + 15, (handler.getHeight() * 4 / 5) + 86, handler.getWidth() * 2 / 20, 17);
                g2.drawString("Mana: ", entityInfoX[i] + 15, (handler.getHeight() * 4 / 5) + 80);
                g2.drawString(String.valueOf(handler.getEntityManager().getPlayer().getMana()), entityInfoX[i] + 16, (handler.getHeight() * 4 / 5) + 100);
                
                g2.drawString("Skill: " + String.valueOf(handler.getEntityManager().getPlayer().getSkill()), entityInfoX[i] + 15, (handler.getHeight() * 4 / 5) + 120);
                g2.drawString("Mana Cost: " + "25 MP", entityInfoX[i] + 15, (handler.getHeight() * 4 / 5) + 140);
            }

        }
    }

    private void drawInfoSquare(Graphics2D g2) {

        g2.setBackground(new Color(61,68,128));
        g2.drawImage(background, 0, 0,handler.getWidth(),handler.getHeight(), null);

        g2.setColor(new Color(51, 96, 178));
        g2.setComposite(AlphaComposite.SrcOver.derive(0.8f));

        //Draws info rectangles
        g2.fillRect(entityInfoX[0], handler.getHeight()* 4/5, handler.getWidth() * 3/20 - 4, handler.getHeight()/7);
        g2.fillRect(handler.getWidth() * 6/20 + 4, handler.getHeight()* 4/5, handler.getWidth() * 8/20 - 8, handler.getHeight()/7);
        g2.fillRect(entityInfoX[1], handler.getHeight()* 4/5, handler.getWidth() * 3/20- 8, handler.getHeight()/7);
        g2.setColor(new Color(84, 91, 104));
        g2.setStroke(new BasicStroke(4));
        //Draws info rectangles borders
        g2.drawRect(entityInfoX[0], handler.getHeight()* 4/5, handler.getWidth() * 3/20 - 4, handler.getHeight()/7);
        g2.drawRect(handler.getWidth() * 6/20 + 4, handler.getHeight()* 4/5, handler.getWidth() * 8/20 - 8, handler.getHeight()/7);
        g2.drawRect(entityInfoX[1], handler.getHeight()* 4/5, handler.getWidth() * 3/20- 8, handler.getHeight()/7);
        g2.setComposite(AlphaComposite.SrcOver);

        //Draws the options
        uiManager.Render(g2);
        g2.setColor(Color.white);
        //Draws the rectangle around the current option
        g2.drawRect((int) uiManager.getObjects().get(optionSelect).getX(), 5*handler.getHeight()/6 + 10, 128, 44);

    }

    /*
     * Disables player movement.  W and S no longer do anything, A and D move between the options
     */
    private void PlayerInput() {

        if(inputCoolDown <= 15){
            inputCoolDown++;
        }
        if (handler.getKeyManager().down || handler.getKeyManager().up) {}
        if (handler.getKeyManager().right && inputCoolDown > 15){
//        	choose options to the right
        	if(!handler.getGame().getMusicHandler().getEPlayer().isEmpty()&&!handler.getGame().getMusicHandler().getEffect(0).equals(null)) {
    			handler.getGame().getMusicHandler().stopEffect(0);
    		}
        	
    		handler.getGame().getMusicHandler().playEffect("res/music/selectBeep.wav",0);
    		
            if(optionSelect < uiManager.getObjects().size()-1 ) {
                optionSelect++;
                inputCoolDown = 0;
            }

        }
        if (handler.getKeyManager().left && inputCoolDown > 15){
//        	choose options to the left
        	
        	if(!handler.getGame().getMusicHandler().getEPlayer().isEmpty()&&!handler.getGame().getMusicHandler().getEffect(0).equals(null)) {
    			handler.getGame().getMusicHandler().stopEffect(0);
    		}
        	
    		handler.getGame().getMusicHandler().playEffect("res/music/selectBeep.wav",0);
    		
            if(optionSelect > 0){
                optionSelect -= 1;
                inputCoolDown = 0;
            }

        }

        uiManager.getObjects().get(optionSelect);

        if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_ENTER)) {
        	if(!handler.getGame().getMusicHandler().getEPlayer().isEmpty()&&!handler.getGame().getMusicHandler().getEffect(0).equals(null)) {
    			handler.getGame().getMusicHandler().stopEffect(0);
    		}
    		handler.getGame().getMusicHandler().playEffect("res/music/enterSelect.wav",0);
            uiManager.getObjects().get(optionSelect).onClick();}

        eWait = System.currentTimeMillis() + 3500;
    }

    private void setUiManager() {
        uiManager = new UIManager(handler);

        //Attack
        uiManager.addObjects(new UIImageButton(handler.getWidth() * 22/60 - 128/2, 5*handler.getHeight()/6, 128, 64, Images.Attack, new ClickListlener() {
            @Override
            public void onClick() {
                System.out.println("Attack");
                attacking=true;

            }
        }));

        //Defend
        uiManager.addObjects(new UIImageButton(handler.getWidth() * 30/60 - 128/2, 5*handler.getHeight()/6, 128, 64, Images.Defend, new ClickListlener() {
            @Override
            public void onClick() {
                System.out.println("Defend");
                defense=true;
            }
        }));

        //Skills
        uiManager.addObjects(new UIImageButton(handler.getWidth() * 38/60 - 128/2, 5*handler.getHeight()/6, 128, 64, Images.Skill, new ClickListlener() {
            @Override
            public void onClick() {
                if(handler.getEntityManager().getPlayer().getMana()>=25) {
                    System.out.println("Skill");
                    skill = true;
                }

            }
        }));



    }


    //Sets the background according to the previous state
    private void backgroundSelect(String prev) {

        if(prev.equals("None"))
            background = Images.battleBackground[1];
        else if (prev.equals("Cave"))
            background = Images.battleBackground[3];


    }

    public void moveFightString() {

        if(this.passing) this.setFightWordYPos(this.getFightWordYPos() + this.stringSpeed);

        if(this.getFightWordYPos() - this.wordHeight * 2 > this.handler.getHeight()) {

            this.setFightWordYPos(0 - this.wordHeight);
            this.setPassing(false);

        }

    }

    private void attack(Graphics g) {

        if(!playerAttackMode.end){
            playerAttackMode.tick();
            g.drawImage(playerAttackMode.getCurrentFrame(),playerRect.x,playerRect.y,playerRect.width,playerRect.height,null);
        }
        else{
            playerRect.x += attackSpeed;
            if (playerRect.x >= handler.getWidth()) {
                playerRect.x = 0 - 70;
            }

            int ev=(int)enemy.getEvs();
            int evade=new Random().nextInt(100);
            
            int atk = (int) handler.getEntityManager().getPlayer().getStr() * 3;
            for(int i = 0; i < 3;i++) {
            	if(new Random().nextInt(20) <= (int)handler.getEntityManager().getPlayer().getAcc())
            		atk+= (int) handler.getEntityManager().getPlayer().getStr();
            }
            
            atk = (int) (atk* (1-(enemy.getDefense()/100))); //amount to be received by the enemy
            
            if(evade>ev &&!attacked && enemy.getHealth()-atk>=0) {
                enemy.setHealth(enemy.getHealth() - atk);
                attacked=true;
                dmg = String.valueOf(atk);
            }else  if(evade>ev &&!attacked && enemy.getHealth()-atk <0){
                enemy.setHealth(0);
                attacked = true;
                dmg = String.valueOf(atk);
            }else if( !(evade>ev) && !attacked)
            	dmg = "Evaded!";
            g.setFont((new Font("IMPACT", Font.ITALIC, 25)));
            g.drawString(dmg , (int)enemy.getXOffset(), (int) enemy.getYOffset() - 20); 
            
            if(this.enemyRect.x < this.playerRect.x && this.playerRect.x < this.enemyRect.x + 50) {

				if(!handler.getGame().getMusicHandler().getEPlayer().isEmpty()&&!handler.getGame().getMusicHandler().getEffect(0).equals(null)) {
					handler.getGame().getMusicHandler().stopEffect(0);
				}

				handler.getGame().getMusicHandler().playEffect("res/music/slash.wav",0);
			}

            if (playerRect.x <= (handler.getWidth() / 5) - 10 && playerRect.x >= (handler.getWidth() / 5) - 110) {
                playerRect.x = (handler.getWidth() / 5);
                attacking = false;
                endTurn = true;
            }

            if (endTurn || battleOver) {
            	//addMana
                if(handler.getEntityManager().getPlayer().getMana() <= handler.getEntityManager().getPlayer().getMaxMana()- 5)
                	handler.getEntityManager().getPlayer().setMana(handler.getEntityManager().getPlayer().getMana() + (int)(10 * (handler.getEntityManager().getPlayer().getIntl()/100)));
                attacking = false;
                endTurn = false;
                turn++;
                attacked = false;
                playerAttackMode.reset();
                if (EisDefense) {
                    enemy.setDefense(enemy.getDefense() - 15);
                    enemy.setMr(enemy.getMr()-4);
                    EisDefense = false;
                }
            }
            
        }
        
       

    }

    private void defend(Graphics g) {

        playerDefenceMode.tick();

        g.drawImage(playerDefenceMode.getCurrentFrame(),playerRect.x,playerRect.y,playerRect.width,playerRect.height,null);

        isDefense = true;

        if(playerDefenceMode.getIndex()>=Images.DefenceMode.length-1){
            handler.getEntityManager().getPlayer().setDefense(handler.getEntityManager().getPlayer().getDefense()+15);
            handler.getEntityManager().getPlayer().setMr(handler.getEntityManager().getPlayer().getMr()+ 4);
          //addMana
            if(handler.getEntityManager().getPlayer().getMana() <= handler.getEntityManager().getPlayer().getMaxMana()- 10)
            	handler.getEntityManager().getPlayer().setMana(handler.getEntityManager().getPlayer().getMana() + (int)(10 * (handler.getEntityManager().getPlayer().getIntl()* 2/100)));
            defense=false;
            endTurn=false;
            turn++;
            if(EisDefense){
                enemy.setDefense(enemy.getDefense()-15);
                enemy.setMr(enemy.getMr()-4);
                EisDefense = false;
            }
            
        }
       

    }

    private void callSkill(Graphics g) {

        playerIceSkill.tick();
        
        g.setColor( new Color(Math.max(0,red--),Math.max(0, green--),Math.max(0, blue--)));
        g.drawImage(Images.tint(enemy.getIdle(),0,0,Math.max(0, blue--)),enemyRect.x,enemyRect.y,enemyRect.width,enemyRect.height,null);

        g.drawImage(playerIceSkill.getCurrentFrame(),(handler.getWidth() * 4/ 5)-93,entityY-93,256,256,null);

        
        int ev=(int)enemy.getEvs();
        int evade=new Random().nextInt(125);
        int skillAtk = (int) handler.getEntityManager().getPlayer().getIntl() * 2; //raw skillAtk
        
        skillAtk = (int) (skillAtk*(1-(enemy.getMr()/100)));
        
        if(evade>ev &&!attacked && enemy.getHealth()-skillAtk>=0) {
            enemy.setHealth(enemy.getHealth() - skillAtk);
            attacked=true;
            dmg = String.valueOf(skillAtk);
        }else  if(evade>ev &&!attacked && enemy.getHealth()-skillAtk<0){
            enemy.setHealth(0);
            attacked = true;
            dmg = String.valueOf(skillAtk);
        }else if( !(evade>ev) && !attacked)
        	dmg = "Evaded!";
        
        g.setFont((new Font("IMPACT", Font.ITALIC, 25)));
        g.drawString(dmg , (int)enemy.getXOffset(), (int) enemy.getYOffset() - 20);  

        if(playerIceSkill.getIndex()>=99){
            endTurn=true;
            green= 255;
            red=95;
            blue=255;
        }

        if(endTurn|| battleOver){
        	attacked=false;
            skill=false;
            endTurn=false;
            turn++;
            handler.getEntityManager().getPlayer().setMana(handler.getEntityManager().getPlayer().getMana()-25);
            if(EisDefense){
                enemy.setDefense(enemy.getDefense()-15);
                enemy.setMr(enemy.getMr()-4);
                EisDefense = false;
            }
        }
    }

    private void enemyTurn() {  
    	
        if(!Eskill&&!Edefense&&!Eattacking && enemy.getMana()>=25) {
            int choice = new Random().nextInt(5);
            switch (choice) {
                case 0://attack
                case 1:
                    Eattacking = true;
                    System.out.println("Attacked");
                    break;
                case 2://defence
                    System.out.println("Defense");
                    Edefense = true;
                    break;
                case 3://skill
                case 4:
                    System.out.println("Skill");
                    Eskill = true;
                    break;
            }
        }else  if(!Eskill&&!Edefense&&!Eattacking) {
            int choice = new Random().nextInt(3);
            switch (choice) {
                case 0://attack
                case 1:
                    Eattacking = true;
                    System.out.println("Attacked");
                    break;
                case 2://defence
                    System.out.println("Defense");
                    Edefense = true;
                    break;
            }
        }
    }

    private void Eattack(Graphics g) {

        if(!playerAttackMode.end){
            playerAttackMode.tick();
            g.drawImage(Images.tint(playerAttackMode.getCurrentFrame(),0,0,2),enemyRect.x-15,enemyRect.y-5,enemyRect.width+10,enemyRect.height+10,null);
        }
        else {

            enemyRect.x -= attackSpeed;
            if (enemyRect.x + 70 < 0) {
                enemyRect.x = handler.getWidth();
            }

            int ev=(int)handler.getEntityManager().getPlayer().getEvs();
            int evade=new Random().nextInt(100);
            
            int atk = (int) enemy.getStr() * 3;
            for(int i = 0; i < 3;i++) {
            	if(new Random().nextInt(20) <= (int)enemy.getAcc())
            		atk+= (int) enemy.getStr();
            }
            
            atk = (int) (atk *(1- (handler.getEntityManager().getPlayer().getDefense()/100)));
            
	        if(evade <ev &&!Eattacked && handler.getEntityManager().getPlayer().getHealth()-atk>=0) {
	            handler.getEntityManager().getPlayer().setHealth(handler.getEntityManager().getPlayer().getHealth() - atk);
	            Eattacked=true;
	            dmg = String.valueOf(atk);
	        }else  if(evade<ev &&!Eattacked && handler.getEntityManager().getPlayer().getHealth()-atk<0){
	            handler.getEntityManager().getPlayer().setHealth(0);
	            dmg = String.valueOf(atk);
	        }else if( !(evade>ev) && !Eattacked)
	        	dmg = "Evaded!";
            
	        g.setColor(Color.RED);
	        g.setFont((new Font("IMPACT", Font.ITALIC, 25)));
	        g.drawString(dmg , playerRect.x, playerRect.y - 20);


            if (enemyRect.x >= (handler.getWidth() * 4 / 5) + 10 && enemyRect.x <= (handler.getWidth() * 4 / 5) + 110) {
                enemyRect.x = (handler.getWidth() * 4 / 5);
                Eattacking = false;
                EendTurn = true;
            }

            if (EendTurn || battleOver) {
                Eattacking = false;
                EendTurn = false;
                turn++;
                Eattacked = false;
                playerAttackMode.reset();
                if (isDefense) {
                    handler.getEntityManager().getPlayer().setDefense(handler.getEntityManager().getPlayer().getDefense() - 15);
                    handler.getEntityManager().getPlayer().setMr(handler.getEntityManager().getPlayer().getMr()- 4);
                    isDefense = false;
                }
            }
        }
    }

    private void Edefend(Graphics g) {

        playerDefenceMode.tick();
        g.drawImage(Images.tint(playerDefenceMode.getCurrentFrame(),0,0,2),enemyRect.x-15,enemyRect.y-5,enemyRect.width+10,enemyRect.height+10,null);
        
        EisDefense = true;
        enemy.setMana(enemy.getMana()+1 );
        if(playerDefenceMode.getIndex()>=Images.DefenceMode.length-1){
            enemy.setDefense(enemy.getDefense()+15);
            enemy.setMr(enemy.getMr()+4);
            Edefense=false;
            EendTurn=false;
            turn++;
            if(isDefense){
                handler.getEntityManager().getPlayer().setDefense(handler.getEntityManager().getPlayer().getDefense()- 15);
                handler.getEntityManager().getPlayer().setMr(handler.getEntityManager().getPlayer().getMr()- 4);
                isDefense = false;
            }
        }
    }

    private void EcallSkill(Graphics g) {
        enemyFireSkill.tick();
        g.setColor( new Color(Math.max(0,red--), Math.max(0,green--),Math.max(0,blue--)));
        g.drawImage(Images.tint(Images.player_attack,Math.max(0,red--),0,0),playerRect.x,playerRect.y,playerRect.width,playerRect.height,null);

        g.drawImage((enemyFireSkill.getCurrentFrame()),(handler.getWidth()/ 5)-93,entityY-105,256,256,null);

        
        int ev=(int)handler.getEntityManager().getPlayer().getEvs();
        int evade=new Random().nextInt(125);
        int skillAtk = (int) enemy.getIntl() * 2; // raw skillAtk
        
        skillAtk = (int) (skillAtk * ( 1- (handler.getEntityManager().getPlayer().getMr()/100))); // final Skill Atk amount

        if(evade>ev &&!Eattacked && handler.getEntityManager().getPlayer().getHealth()-skillAtk>=0) {
            handler.getEntityManager().getPlayer().setHealth(handler.getEntityManager().getPlayer().getHealth() - skillAtk);
            Eattacked=true;
            dmg = String.valueOf(skillAtk);
        }else  if(evade>ev &&!Eattacked && handler.getEntityManager().getPlayer().getHealth()-skillAtk<0){
            handler.getEntityManager().getPlayer().setHealth(0);
            dmg = String.valueOf(skillAtk);
        }else if( !(evade>ev) && !Eattacked)
        	dmg = "Evaded!";
        
        g.setColor(Color.RED);
        g.setFont((new Font("IMPACT", Font.ITALIC, 25)));
        g.drawString(dmg , playerRect.x, playerRect.y - 20);
        
        if(enemyFireSkill.getIndex()>=69){
            EendTurn=true;
            green= 255;
            red=95;
            blue=255;
        }

        if(EendTurn || battleOver){
        	Eattacked=false;
            Eskill=false;
            EendTurn=false;
            turn++;
            enemy.setMana(enemy.getMana()-25);
            if(isDefense){
                handler.getEntityManager().getPlayer().setDefense(handler.getEntityManager().getPlayer().getDefense()-15);
                handler.getEntityManager().getPlayer().setMr(handler.getEntityManager().getPlayer().getMr()- 4);
                isDefense = false;
            }
        }
    }

    public int getFightWordXPos() {
        return fightWordXPos;
    }

    public void setFightWordXPos(int fightWordXPos) {
        this.fightWordXPos = fightWordXPos;
    }

    public int getFightWordYPos() {
        return fightWordYPos;
    }

    public void setFightWordYPos(int fightWordYPos) {
        this.fightWordYPos = fightWordYPos;
    }

    public int getWordHeight() {
        return wordHeight;
    }

    public void setWordHeight(int wordHeight) {
        this.wordHeight = wordHeight;
    }

    public boolean isPassing() {
        return passing;
    }

    public void setPassing(boolean passing) {
        this.passing = passing;
    }

    public int getStringSpeed() {
        return stringSpeed;
    }

    public void setStringSpeed(int stringSpeed) {
        this.stringSpeed = stringSpeed;
    }



}
