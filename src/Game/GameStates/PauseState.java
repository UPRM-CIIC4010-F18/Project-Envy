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
	int waitTimeForInput;
	public State lastState;
	public int RXPos = handler.getWidth()/2 - 128 * 2;
	public int QXPos = handler.getWidth()/2 + 150;
	public int YPos = handler.getHeight()/2 - 32;
	public int TXPos = handler.getWidth()/2 - 64;
	public int TYPos = handler.getHeight()/2 + 64;
	public int width = 128;
	public int height = 64;
	Selector selector; 

	public PauseState(Handler handler) {
		super(handler);

		uiManager = new UIManager(handler);
		selector = new Selector(this.handler);

	}

	@Override
	public void tick() {
		handler.getMouseManager().setUimanager(uiManager);
		uiManager.tick();

		selector.tick();

		if (handler.getKeyManager().keyJustPressed(KeyEvent.VK_ESCAPE)) {
		    returnToGame();
		}

		else if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_ENTER)) {

			this.choose();

		}

	}

	@Override
	public void render(Graphics g) {
		Graphics2D g2 = (Graphics2D)g;

		g2.setColor(new Color(61, 68, 128));
		g2.fill(new Rectangle( 0, 0, this.handler.getWidth(), this.handler.getHeight()));
		g.drawImage(Images.battleBackground[0],0,0,handler.getWidth(),handler.getHeight(),null);
		if(selector.getxPos() == selector.getRBpos()) {

			g2.drawImage(Images.Resume[1], this.RXPos, this.YPos, this.width, this.height, null);

		}

		else {

			g2.drawImage(Images.Resume[0], this.RXPos, this.YPos, this.width, this.height, null);

		}

		if(selector.getxPos() == selector.getQBpos()) {

			g2.drawImage(Images.Quit[1], this.QXPos, this.YPos, this.width - 20, this.height, null);

		}

		else {

			g2.drawImage(Images.Quit[0], this.QXPos, this.YPos, this.width - 20, this.height, null);

		}

		if(selector.getxPos() == selector.getTBXpos()) {

			g2.drawImage(Images.titleChoose[1], this.TXPos, this.TYPos, this.width - 20, this.height, null);

		}
		
		else {
			
			g2.drawImage(Images.titleChoose[0], this.TXPos, this.TYPos, this.width - 20, this.height, null);
			
		}
		
		uiManager.Render(g);
		selector.render(g2);

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

		if(selector.getxPos() == selector.getRBpos()) {
			returnToGame();

		}else if(selector.getxPos() == selector.getTBXpos()) {
            handler.setArea("None");
            handler.getGame().reStart();
			State.setState(handler.getGame().menuState);
		}
		else System.exit(0);

	}
}
