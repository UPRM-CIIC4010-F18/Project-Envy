package Game.GameStates;


import Display.UI.UIListener;
import Main.GameSetUp;
import Main.Handler;
import Resources.Images;
import Display.UI.UIManager;
import Display.UI.Selector;

import java.awt.*;
import java.util.Random;

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
	public int titleXPos;
	public int titleYPos;
	public int EyPos, NyPos, VyPos, YyPos; 
	public int menuSpeed = 3;
	public int imageSpeed = 15;

	public boolean eDown , nDown, vDown, yDown;
	Selector selector = new Selector(this.handler);
	private boolean statics=false;
	private int staticsCounter=0;
	private int staticsChoice;

    public MenuState(Handler handler) {
		super(handler);

		this.xPos = 0;
		this.xPos2 = handler.getWidth();
		this.imageX = handler.getWidth();
		this.yPos = 0;
		this.width = handler.getWidth();
		this.height = handler.getHeight();
		this.titleXPos = 200;
		this.titleYPos = 0 - 250;
		this.EyPos = this.NyPos = this.VyPos = this.YyPos = 0 - 300;
		this.eDown = this.nDown = this.vDown = this.yDown = false;
		

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
		g.drawImage(Images.projectTitle, this.titleXPos, this.titleYPos, 600, 300, null);
		g.setFont(new Font("AR ESSENCE", Font.PLAIN, 300));
		g.setColor(Color.YELLOW);
		g.drawString("E", this.titleXPos + 600 + 30, this.EyPos);
		g.drawString("N", this.titleXPos + 600 + 165, this.NyPos);
		g.drawString("V", this.titleXPos + 600 + 320, this.VyPos);
		g.drawString("Y", this.titleXPos + 600 + 475, this.YyPos);

		if(selector.getxPos2() == selector.menuXPositions[0] && selector.getyPos2() == selector.menuYPositions[0]) {

			g.drawImage(Images.butstart[1], handler.getWidth()/2 - 400, handler.getHeight()/2 - 150, 400, 300, null);

		}

		else {

			g.drawImage(Images.butstart[0], handler.getWidth()/2 - 400, handler.getHeight()/2 - 150, 400, 300, null);

		}

		if(selector.getxPos2() == selector.menuXPositions[1] && selector.getyPos2() == selector.menuYPositions[1]) {

			g.drawImage(Images.Quit[1],handler.getWidth()/2 - 425, handler.getHeight()/2 + 100, 450, 300, null);

		}

		else {

			g.drawImage(Images.Quit[0],handler.getWidth()/2 - 425, handler.getHeight()/2 + 100, 450, 300, null);

		}

		g.drawImage(Images.titleImage, this.imageX, this.yPos, handler.getHeight() / 2, handler.getHeight(), null);

		selector.render(g);
		uiManager.Render(g);

		if(UIListener.asset){
			standarize(g);
		}

		g.setFont(new Font("Bank Gothic",3,30));
		g.setColor(new Color(4, 82, 98));
		g.drawString("Music composed by Emmanuel Segarra", 50 , handler.getHeight()-75);
		g.setFont(new Font("Bank Gothic",3,15));
		g.drawString("www.emmanuelsegarramusic.com", 75 , handler.getHeight()-58);


	}

	public void moveMenuImages() {

		this.setxPos(this.getxPos() - this.menuSpeed);
		this.setxPos2(this.getxPos2() - this.menuSpeed);
		this.setImageX(this.getImageX() - this.imageSpeed);
		this.setTitleYPos(this.getTitleYPos() + this.imageSpeed);

		if(this.getImageX() <= handler.getWidth() - 600) {
			this.setImageX(handler.getWidth() - 600);
		}

		if(this.getxPos() + handler.getWidth() <= 0) {
			this.setxPos(handler.getWidth());
		}
		else if(this.getxPos2() + handler.getWidth() <= 0) {
			this.setxPos2(handler.getWidth());
		}

		if(this.getTitleYPos() + 300 >= 400) {
			this.setTitleYPos(400 - 300);
			this.eDown = true;
		}
		
		this.moveTitleLetters();

	}

	public void standarize(Graphics g){
		if(!statics){
			int x = new Random().nextInt(5000);
			staticsChoice = new Random().nextInt(3);
			if (x <= 5) {
				statics=true;
                handler.getGame().getMusicHandler().playEffect(GameSetUp.resList[new Random().nextInt(GameSetUp.resList.length)],handler.getGame().getMusicHandler().getEPlayer().size());
            }
		}else{
			staticsCounter++;
			if(staticsCounter>=60){
				staticsCounter=0;
				statics=false;
			}
			switch (staticsChoice){
				case 0:
					if(staticsCounter%2==0) {
						g.drawImage(Images.title2, 0, 0, handler.getWidth(), handler.getHeight(), null);
					}else{
						g.drawImage(Images.title3, 0, 0, handler.getWidth(), handler.getHeight(), null);
					}

					break;
				case 1:
					if(staticsCounter%2==0) {
						g.drawImage(Images.title3, 0, 0, handler.getWidth(), handler.getHeight(), null);
					}else{
						g.drawImage(Images.title, 0, 0, handler.getWidth(), handler.getHeight(), null);
					}
					break;
				case 2:
					if(staticsCounter%2==0) {
						g.drawImage(Images.title4, 0, 0, handler.getWidth(), handler.getHeight(), null);
					}else{
						g.drawImage(Images.title, 0, 0, handler.getWidth(), handler.getHeight(), null);
					}
					break;
			}
		}
	}
	
	public void moveTitleLetters() {
		
		if(this.EyPos > this.getTitleYPos() + 175) {
			this.EyPos = this.getTitleYPos() + 175;
			this.nDown = true;
		}
		if(this.NyPos > this.getTitleYPos() + 175) {
			this.NyPos = this.getTitleYPos() + 175;
			this.vDown = true;
		}
		if(this.VyPos > this.getTitleYPos() + 175) {
			this.VyPos = this.getTitleYPos() + 175;
			this.yDown = true;
		}
		if(this.YyPos > this.getTitleYPos() + 175) {
			this.YyPos = this.getTitleYPos() + 175;
		}
		
		if(this.eDown) {
			this.EyPos += 40;			
		}
		if(this.nDown) {
			this.NyPos += 40;			
		}
		if(this.vDown) {
			this.VyPos += 40;			
		}
		if(this.yDown) {
			this.YyPos += 40;			
		}
		
	}

	public void choose() {

		handler.getGame().getMusicHandler().playEffect("res/music/enterSelect.wav",0);
		
		if(selector.getxPos2() == selector.menuXPositions[0] && selector.getyPos2() == selector.menuYPositions[0]) {
			GameSetUp.LOADING=true;
			handler.getGame().getMusicHandler().set_changeMusic("res/music/OverWorld.mp3");
			handler.getGame().getMusicHandler().play();
			handler.getGame().getMusicHandler().setVolume(0.2);
			handler.getGame().getMusicHandler().setLoop(true);
			
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

	public int getTitleXPos() {
		return titleXPos;
	}

	public void setTitleXPos(int titleXPos) {
		this.titleXPos = titleXPos;
	}

	public int getTitleYPos() {
		return titleYPos;
	}

	public void setTitleYPos(int titleYPos) {
		this.titleYPos = titleYPos;
	}

}
