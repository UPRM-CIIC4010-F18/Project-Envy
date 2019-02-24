package Game.GameStates;

import Main.GameSetUp;
import Main.Handler;
import Resources.Images;
import Display.UI.UIManager;
import Display.UI.Selector;

import java.awt.*;
import java.awt.event.KeyEvent;

/**
 * Created by AlexVR on 7/1/2018.
 */
public class PauseState extends State {

	private UIManager uiManager;
	private boolean showStats=false;
	int statX=handler.getWidth()/6,statY=handler.getHeight();
	public State lastState;
	public int RXPos = handler.getWidth()/2 - 128 * 2;
	public int QXPos = handler.getWidth()/2 + 150;
	public int YPos = handler.getHeight()/2 - 32;
	public int TXPos = handler.getWidth()/2 - 64;
	public int TYPos = handler.getHeight()/2 + 64;
	public int width = 128;
	public int height = 64;
	Selector selector; 

	String[] statList = {"Level","Health","Mana","Experience","Class","Defense","Strength","Intelligence", "Magic Res.","Constitution","Accuracy","Evasion","Initiative","Max Health","Skill"};

	public PauseState(Handler handler) {
		super(handler);

		uiManager = new UIManager(handler);
		selector = new Selector(this.handler);

	}

	@Override
	public void tick() {
		if(!showStats) {
		    if(statY>=handler.getHeight()) {
                handler.getMouseManager().setUimanager(uiManager);
                uiManager.tick();

                selector.tick();

                if (handler.getKeyManager().keyJustPressed(KeyEvent.VK_ESCAPE)) {
                    returnToGame();
                } else if (handler.getKeyManager().keyJustPressed(KeyEvent.VK_ENTER)) {

                    this.choose();

                }
            }else{
		        statY+=10;
            }
		}else{
            if(statY>0) {
                statY-=10;
            }
            if (handler.getKeyManager().keyJustPressed(KeyEvent.VK_ESCAPE)) {
                showStats=false;
            }

        }

	}

	@Override
	public void render(Graphics g) {
		if(!showStats&&statY>=handler.getHeight()) {
			Graphics2D g2 = (Graphics2D) g;
			g2.setColor(new Color(61, 68, 128));
			g2.fill(new Rectangle(0, 0, this.handler.getWidth(), this.handler.getHeight()));
			g.drawImage(Images.battleBackground[0], 0, 0, handler.getWidth(), handler.getHeight(), null);
			if (selector.getxPos() == selector.pauseXPositions[0] && selector.getyPos() == selector.pauseYPositions[0]) {
				g2.drawImage(Images.Resume[1], this.RXPos, this.YPos, this.width, this.height, null);
			} else {
				g2.drawImage(Images.Resume[0], this.RXPos, this.YPos, this.width, this.height, null);
			}
			if (selector.getxPos() == selector.pauseXPositions[3] && selector.getyPos() == selector.pauseYPositions[3]) {
				g2.drawImage(Images.Quit[1], this.QXPos, this.YPos, this.width - 20, this.height, null);
			} else {
				g2.drawImage(Images.Quit[0], this.QXPos, this.YPos, this.width - 20, this.height, null);
			}
			if (selector.getxPos() == selector.pauseXPositions[2] && selector.getyPos() == selector.pauseYPositions[2]) {
				g2.drawImage(Images.titleChoose[1], this.TXPos, this.TYPos, this.width - 20, this.height, null);
			} else {
				g2.drawImage(Images.titleChoose[0], this.TXPos, this.TYPos, this.width - 20, this.height, null);
			}
			if (selector.getxPos() == selector.pauseXPositions[1] && selector.getyPos() == selector.pauseYPositions[1]) {
				g2.drawImage(Images.StatBut[1], this.TXPos, this.TYPos - 200, this.width - 20, this.height, null);
			} else {
				g2.drawImage(Images.StatBut[0], this.TXPos, this.TYPos - 200, this.width - 20, this.height, null);
			}
			uiManager.Render(g);
			selector.render(g2);
		}else{
            g.setColor(new Color(61, 68, 128));
            g.fillRect(0, 0, this.handler.getWidth(), this.handler.getHeight());
            g.drawImage(Images.battleBackground[0], 0, 0, handler.getWidth(), handler.getHeight(), null);
            g.drawImage(Images.Stats, statX, statY, 2*(handler.getWidth()/3), handler.getHeight(), null);
            g.setFont(new Font("Ink Free", Font.ITALIC, 35));
            int initialX=(handler.getWidth() / 6) + 327,initialY=241+statY;
            g.drawString(statList[0]+": "+handler.getEntityManager().getPlayer().getLvl(),initialX+175,initialY);
            for (int i = 0; i < 15; i++,initialY+=50) {
                if(i==0){continue;}
                String value = getStatValue(statList[i]);
                g.drawString(statList[i]+": "+value, initialX, initialY);
                if(i==7){initialY=241+statY;initialX+=300;}

            }
        }
	}

    private String getStatValue(String s) {

	    switch (s){
            case "Health":
                return String.valueOf(handler.getEntityManager().getPlayer().getHealth());
            case "Mana":
                return String.valueOf(handler.getEntityManager().getPlayer().getMana());
            case "Experience":
                return String.valueOf(handler.getEntityManager().getPlayer().getXp());
            case "Class":
                return String.valueOf(handler.getEntityManager().getPlayer().getclass());
            case "Defense":
                return String.valueOf(handler.getEntityManager().getPlayer().getDefense());
            case "Strength":
                return String.valueOf(handler.getEntityManager().getPlayer().getStr());
            case "Intelligence":
                return String.valueOf(handler.getEntityManager().getPlayer().getIntl());
            case "Magic Res.":
                return String.valueOf(handler.getEntityManager().getPlayer().getMr());
            case "Constitution":
                return String.valueOf(handler.getEntityManager().getPlayer().getCons());
            case "Accuracy":
                return String.valueOf(handler.getEntityManager().getPlayer().getAcc());
            case "Evasion":
                return String.valueOf(handler.getEntityManager().getPlayer().getEvs());
            case "Initiative":
                return String.valueOf(handler.getEntityManager().getPlayer().getInitiative());
            case "Max Health":
                return String.valueOf(handler.getEntityManager().getPlayer().getMaxHealth());
            case "Skill":
                return String.valueOf(handler.getEntityManager().getPlayer().getSkill());

        }
        return "0";
    }

    private void returnToGame(){
		GameSetUp.LOADING=true;
		if(lastState instanceof MapState || lastState instanceof FightState){
			handler.setArea("None");
		}else if(lastState instanceof InWorldState){
			handler.setArea(InWorldState.currentArea.name);
		}
		GameSetUp.SWITCHING=true;
		State.setState(lastState);
	}


	public void choose() {
		
		if(!handler.getGame().getMusicHandler().getEPlayer().isEmpty()&&!handler.getGame().getMusicHandler().getEffect(0).equals(null)) {
			handler.getGame().getMusicHandler().stopEffect(0);
		}
		handler.getGame().getMusicHandler().playEffect("res/music/enterSelect.wav",0);

		if(selector.getxPos() == selector.pauseXPositions[0] && selector.getyPos() == selector.pauseYPositions[0]) {
			returnToGame();
		}
		else if(selector.getxPos() == selector.pauseXPositions[1] && selector.getyPos() == selector.pauseYPositions[1]) {	
			showStats=true;			
		}
		else if(selector.getxPos() == selector.pauseXPositions[2] && selector.getyPos() == selector.pauseYPositions[2]) {
			handler.setArea("None");
			handler.getGame().reStart();
			State.setState(handler.getGame().menuState);
		}
		else System.exit(0);

	}
}
