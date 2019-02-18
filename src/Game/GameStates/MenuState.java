package Game.GameStates;


import Main.Handler;
import Resources.Images;
import Display.UI.ClickListlener;
import Display.UI.UIImageButton;
import Display.UI.UIManager;
import Game.Entities.Dynamics.Selector;

import java.awt.*;

import com.sun.glass.events.KeyEvent;

/**
 * Created by AlexVR on 7/1/2018.
 */
public class MenuState extends State {

	private UIManager uiManager;

	public int xPos;
	public int xPos2;
	public int imageX;
	public int yPos;
	public int width;
	public int height;
	public int menuSpeed = 3;
	public int imageSpeed = 15;

	Selector selector = new Selector(this.handler);

	public MenuState(Handler handler) {
		super(handler);

		this.xPos = 0;
		this.xPos2 = handler.getWidth();
		this.imageX = handler.getWidth();
		this.yPos = 0;
		this.width = handler.getWidth();
		this.height = handler.getHeight();

		uiManager = new UIManager(handler);
		handler.getMouseManager().setUimanager(uiManager);

	}

	@Override
	public void tick() {
		handler.getMouseManager().setUimanager(uiManager);
		uiManager.tick();
		selector.tick();
		this.moveMenuImages();
		
		if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_ENTER)) {
			
			this.choose();
			
		}

	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.darkGray);

		g.fillRect(0,0,handler.getWidth(),handler.getHeight());

		g.drawImage(Images.title,this.xPos, this.yPos, this.width, this.height, null);
		g.drawImage(Images.title,this.xPos2, this.yPos, this.width, this.height, null);
		if(selector.getyPos2() == selector.getSBYpos()) {

			g.drawImage(Images.butstart[1], handler.getWidth()/2 - 400, handler.getHeight()/2 - 328, 400, 300, null);

		}

		else {

			g.drawImage(Images.butstart[0], handler.getWidth()/2 - 400, handler.getHeight()/2 - 328, 400, 300, null);

		}

		if(selector.getyPos2() == selector.getQBYpos2()) {

			g.drawImage(Images.Quit[1],handler.getWidth()/2 - 425, handler.getHeight()/2, 450, 300, null);

		}

		else {

			g.drawImage(Images.Quit[0],handler.getWidth()/2 - 425, handler.getHeight()/2, 450, 300, null);

		}
		
		g.drawImage(Images.titleImage, this.imageX, this.yPos, handler.getHeight() / 2, handler.getHeight(), null);

		selector.render(g);
		uiManager.Render(g);

	}

	public void moveMenuImages() {

		this.setxPos(this.getxPos() - this.menuSpeed); 
		this.setxPos2(this.getxPos2() - this.menuSpeed);
		this.setImageX(this.getImageX() - this.imageSpeed);

		if(this.getImageX() <= handler.getWidth() - 700) {
			this.setImageX(handler.getWidth() - 700);
		}

		if(this.getxPos() + handler.getWidth() <= 0) {
			this.setxPos(handler.getWidth());   		
		}
		else if(this.getxPos2() + handler.getWidth() <= 0) {
			this.setxPos2(handler.getWidth());
		}

	}
	
	public void choose() {

		if(selector.getyPos2() == selector.getSBYpos()) {

			State.setState(handler.getGame().mapState);

		}

		else System.exit(0);

	}

	public int getxPos() {
		return xPos;
	}

	public void setxPos(int xPos) {
		this.xPos = xPos;
	}

	public int getxPos2() {
		return xPos2;
	}

	public void setxPos2(int xPos2) {
		this.xPos2 = xPos2;
	}

	public int getImageX() {
		return imageX;
	}

	public void setImageX(int imageX) {
		this.imageX = imageX;
	}

	public int getyPos() {
		return yPos;
	}

	public void setyPos(int yPos) {
		this.yPos = yPos;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getMenuSpeed() {
		return menuSpeed;
	}

	public void setMenuSpeed(int menuSpeed) {
		this.menuSpeed = menuSpeed;
	}


}
