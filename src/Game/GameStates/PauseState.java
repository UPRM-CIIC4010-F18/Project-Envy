package Game.GameStates;

import Main.Handler;
import Resources.Images;
import Display.UI.ClickListlener;
import Display.UI.UIImageButton;
import Display.UI.UIManager;
import Game.Entities.Dynamics.PauseMenuSelector;

import java.awt.*;
import java.awt.event.KeyEvent;

/**
 * Created by AlexVR on 7/1/2018.
 */
public class PauseState extends State {

	private UIManager uiManager;
	int waitTimeForInput;
	public static State lastState;
	public int RXPos = handler.getWidth()/2 - 128 * 2;
	public int QXPos = handler.getWidth()/2 + 128;
	public int YPos = handler.getHeight()/2 - 32;
	public int width = 128;
	public int height = 64;
	PauseMenuSelector selector; 

	public PauseState(Handler handler) {
		super(handler);

		uiManager = new UIManager(handler);
		selector = new PauseMenuSelector(this.handler);

	}

	@Override
	public void tick() {
		handler.getMouseManager().setUimanager(uiManager);
		uiManager.tick();

		selector.tick();

		if (handler.getKeyManager().keyJustPressed(KeyEvent.VK_ESCAPE)) {
			State.setState(lastState);
		}

		if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_SPACE)) {

			this.choose();

		}

	}

	@Override
	public void render(Graphics g) {
		Graphics2D g2 = (Graphics2D)g;

		g2.setColor(new Color(61, 68, 128));
		g2.fill(new Rectangle( 0, 0, this.handler.getWidth(), this.handler.getHeight()));

		if(selector.getxPos() == selector.getRBpos()) {

			g2.drawImage(Images.Resume[1], this.RXPos, this.YPos, this.width, this.height, null);

		}

		else {

			g2.drawImage(Images.Resume[0], this.RXPos, this.YPos, this.width, this.height, null);

		}

		if(selector.getxPos() == selector.getQBpos()) {

			g2.drawImage(Images.Quit[1], this.QXPos, this.YPos, this.width, this.height, null);

		}
		
		else {

			g2.drawImage(Images.Quit[0], this.QXPos, this.YPos, this.width, this.height, null);

		}

		uiManager.Render(g);
		selector.render(g2);

	}

	public void choose() {

		if(selector.getxPos() == selector.getRBpos()) {

			State.setState(lastState);

		}

		else System.exit(0);

	}
}
