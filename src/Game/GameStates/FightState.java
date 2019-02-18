package Game.GameStates;

import Game.Entities.EntityManager;
import Game.Entities.Dynamics.BaseDynamicEntity;
import Game.Entities.Dynamics.BaseHostileEntity;
import Game.Entities.Dynamics.Player;
import Main.GameSetUp;
import Main.Handler;

import Resources.Images;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.Arrays;

import Display.UI.ClickListlener;
import Display.UI.UIImageButton;
import Display.UI.UIManager;


public class FightState extends InWorldState{
    private UIManager uiManager;
    EntityManager entityManager;
    private int entityY;

    Player player;
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




    public FightState(Handler handler, BaseDynamicEntity player ,BaseHostileEntity enemy, String prevState) {
        super(handler);
        System.out.println(prevState);

        entityY = (int) handler.getHeight() * 2/3;
        entityInfoX = new int[2];
        //player info square coordinate
        entityInfoX[0] = handler.getWidth() * 3/20;
        //enemy info square coordinate
        entityInfoX[1] = handler.getWidth() * 14/20 + 4;

        //get's the info from the player and enemy (Will be used for taking their info)
        this.player = new Player(handler,  handler.getWidth() / 5, entityY);

        this.enemy = (handler.newEnemy(handler,handler.getWidth() * 4/ 5, entityY,enemy.foundState,enemy.name,enemy.Area,
                enemy.type,enemy.getHealth(),enemy.getMana(),enemy.getXp(),enemy.getLvl(),enemy.getStr(),enemy.getDefense(),
                enemy.getIntl(),enemy.getCons(),enemy.getAcc(),enemy.getEvs(),enemy.getInitiative(),enemy.getclass(),enemy.getSkill(),
                enemy.getBuffs(),enemy.getDebuffs()));

        entityManager = new EntityManager(handler, this.player);
        entityManager.AddEntity(this.enemy);
        this.handler.setEntityManager(entityManager);

        playerRect = new Rectangle( (int) handler.getWidth() / 5, entityY, 70, 70);
        enemyRect = new Rectangle((int) handler.getWidth() * 4/ 5,entityY, 70, 70);

        setUiManager();
        backgroundSelect(prevState);

        optionSelect = 0;
        inputCoolDown = 0;

//        Possibly need to add the more of the same image on the attack image array for the mouse to work with the UIManager
//        Since it only has one image and crashes when the mouse is hovered over it.

//        handler.getMouseManager().setUimanager(uiManager);

    }

    @Override
    public void tick() {

        ///TEMP CODE TO EXIT FIGHT///
        if (handler.getKeyManager().keyJustPressed(KeyEvent.VK_ESCAPE)) {
            handler.getGame().pauseState.lastState = State.getState();
            GameSetUp.SWITCHING=true;
            State.setState(handler.getGame().pauseState);
        }
        /////////////////////////////

        else{
            PlayerInput();
            uiManager.tick();
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


    }

    private void drawEntities(Graphics2D g2) {
        //Draws entities
        g2.setColor(Color.RED);
        g2.fill(playerRect);
        g2.setColor(Color.BLACK);
        g2.fill(enemyRect);
    }

    private void drawDebug(Graphics g) {
        if(GameSetUp.DEBUGMODE){
            g.setFont((new Font("IMPACT", Font.ITALIC, 25)));
            //player
            g.drawString("Accuracy: "+String.valueOf(player.getAcc()),0,300);
            g.drawString("XP: "+String.valueOf(player.getXp()),0,25);
            g.drawString("Level: "+String.valueOf(player.getLvl()),0,50);
            g.drawString("Strength: "+String.valueOf(player.getStr()),0,75);
            g.drawString("Defence: "+String.valueOf(player.getDefense()),0,100);
            g.drawString("Intelligence: "+String.valueOf(player.getIntl()),0,125);
            g.drawString("Constitution: "+String.valueOf(player.getCons()),0,150);
            g.drawString("Evasion: "+String.valueOf(player.getEvs()),0,175);
            g.drawString("Initiative: "+String.valueOf(player.getInitiative()),0,200);
            g.drawString("Class: "+String.valueOf(player.getclass()),0,225);
            g.drawString("Buffs: "+ Arrays.toString(player.getBuffs()),0,250);
            g.drawString("Debuffs: "+ Arrays.toString(player.getDebuffs()),0,275);
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
                g2.drawString("Name: " + enemy.name, entityInfoX[i] + 15, (handler.getHeight() * 4 / 5) + 20);

                //draws health info
                g2.setColor(Color.GREEN);
                g2.fillRect(entityInfoX[i] + 15, (handler.getHeight() * 4 / 5) + 46, (handler.getWidth() * 2 / 20), 17);
                g2.setColor(Color.WHITE);
                g2.drawRect(entityInfoX[i] + 15, (handler.getHeight() * 4 / 5) + 46, handler.getWidth() * 2 / 20, 17);
                g2.drawString("Health: ", entityInfoX[i] + 15, (handler.getHeight() * 4 / 5) + 40);
                g2.drawString(String.valueOf(enemy.getHealth()), entityInfoX[i] + 16, (handler.getHeight() * 4 / 5) + 60);

                //Draws MP Information
                g2.setColor(Color.BLUE);
                g2.fillRect(entityInfoX[i] + 15, (handler.getHeight() * 4 / 5) + 86, handler.getWidth() * 2 / 20, 17);
                g2.setColor(Color.WHITE);
                g2.drawRect(entityInfoX[i] + 15, (handler.getHeight() * 4 / 5) + 86, handler.getWidth() * 2 / 20, 17);
                g2.drawString("Mana: ", entityInfoX[i] + 15, (handler.getHeight() * 4 / 5) + 80);
                g2.drawString(String.valueOf(enemy.getMana()), entityInfoX[i] + 16, (handler.getHeight() * 4 / 5) + 100);

                g2.drawString("Skill: " + String.valueOf(enemy.getSkill()), entityInfoX[i] + 15, (handler.getHeight() * 4 / 5) + 120);
                g2.drawString("Mana Cost: " + "25 MP", entityInfoX[i] + 15, (handler.getHeight() * 4 / 5) + 140);
            }else{//player
                g2.drawString("Name: "+"Player ", entityInfoX[i] + 15, (handler.getHeight() * 4 / 5) + 20);

                //draws health info
                g2.setColor(Color.GREEN);
                g2.fillRect(entityInfoX[i] + 15, (handler.getHeight() * 4 / 5) + 46, handler.getWidth() * 2 / 20, 17);
                g2.setColor(Color.WHITE);
                g2.drawRect(entityInfoX[i] + 15, (handler.getHeight() * 4 / 5) + 46, handler.getWidth() * 2 / 20, 17);
                g2.drawString("Health: ", entityInfoX[i] + 15, (handler.getHeight() * 4 / 5) + 40);
                g2.drawString(String.valueOf(player.getHealth()), entityInfoX[i] + 16, (handler.getHeight() * 4 / 5) + 60);

                //Draws MP Information
                g2.setColor(Color.BLUE);
                g2.fillRect(entityInfoX[i] + 15, (handler.getHeight() * 4 / 5) + 86, handler.getWidth() * 2 / 20, 17);
                g2.setColor(Color.WHITE);
                g2.drawRect(entityInfoX[i] + 15, (handler.getHeight() * 4 / 5) + 86, handler.getWidth() * 2 / 20, 17);
                g2.drawString("Mana: ", entityInfoX[i] + 15, (handler.getHeight() * 4 / 5) + 80);
                g2.drawString(String.valueOf(player.getMana()), entityInfoX[i] + 16, (handler.getHeight() * 4 / 5) + 100);

                g2.drawString("Skill: " + String.valueOf(player.getSkill()), entityInfoX[i] + 15, (handler.getHeight() * 4 / 5) + 120);
                g2.drawString("Mana Cost: " + "25 MP", entityInfoX[i] + 15, (handler.getHeight() * 4 / 5) + 140);
            }

        }
    }

    private void drawInfoSquare(Graphics2D g2) {


        g2.setBackground(new Color(61,68,128));
        g2.drawImage(background, 0, 0, null);

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
            if(optionSelect < uiManager.getObjects().size()-1 ) {
                optionSelect++;
                inputCoolDown = 0;
            }

        }
        if (handler.getKeyManager().left && inputCoolDown > 15){
//        	choose options to the left
            if(optionSelect > 0){
                optionSelect -= 1;
                System.out.println(optionSelect);
                inputCoolDown = 0;
            }

        }

        uiManager.getObjects().get(optionSelect);

        if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_ENTER))
            uiManager.getObjects().get(optionSelect).onClick();


    }



    private void setUiManager() {
        uiManager = new UIManager(handler);

        //Attack
        uiManager.addObjects(new UIImageButton(handler.getWidth() * 22/60 - 128/2, 5*handler.getHeight()/6, 128, 64, Images.Attack, new ClickListlener() {
            @Override
            public void onClick() {
                //for testing purposes
                System.out.println("Attack");
                attack();

            }
        }));



        //Defend
        uiManager.addObjects(new UIImageButton(handler.getWidth() * 30/60 - 128/2, 5*handler.getHeight()/6, 128, 64, Images.Defend, new ClickListlener() {
            @Override
            public void onClick() {
                System.out.println("Defend");
            }
        }));

        //Skills
        uiManager.addObjects(new UIImageButton(handler.getWidth() * 38/60 - 128/2, 5*handler.getHeight()/6, 128, 64, Images.Skill, new ClickListlener() {
            @Override
            public void onClick() {
                System.out.println("Skill");


            }
        }));



    }


    //Sets the background according to the previous state
    //Current backgrounds where only added for testing purposes
    private void backgroundSelect(String prev) {

        if(prev.equals("None"))
            background = Images.battleBackground[1];
        else if (prev.equals("Cave"))
            background = Images.battleBackground[0];


    }

    public void moveFightString() {

        if(this.passing) this.setFightWordYPos(this.getFightWordYPos() + this.stringSpeed);

        if(this.getFightWordYPos() - this.wordHeight * 2 > this.handler.getHeight()) {

            this.setFightWordYPos(0 - this.wordHeight);
            this.setPassing(false);

        }

    }


    //doesn't work rn
    private void attack() {

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
