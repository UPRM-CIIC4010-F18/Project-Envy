package Input;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;

import Display.UI.UIListener;
import Game.Entities.EntityManager;
import Game.GameStates.State;
import Game.World.Walls;
import Game.World.InWorldAreas.BaseArea;
import Game.World.InWorldAreas.InWorldWalls;
import Main.GameSetUp;
import Main.Handler;
import Resources.Animation;
import Resources.Images;


/**
 * Created by AlexVR on 7/1/2018.
 */

public class KeyManager implements KeyListener {

	private boolean[] keys,justPressed,cantPress;
	public boolean up=false, down=false, left=false, right=false;
	public boolean attbut=false;
	public boolean fattbut=false;
	public boolean pbutt=false;
	public boolean runbutt=false;


	public KeyManager(){

		keys = new boolean[256];
		justPressed = new boolean[keys.length];
		cantPress = new boolean[keys.length];

	}

	public void tick(){
		for(int i =0; i < keys.length;i++){
			if(cantPress[i] && !keys[i]){
				cantPress[i]=false;

			}else if(justPressed[i]){
				cantPress[i]=true;
				justPressed[i] =false;
			}
			if(!cantPress[i] && keys[i]){
				justPressed[i]=true;
			}
		}

		up = keys[KeyEvent.VK_W];
		down = keys[KeyEvent.VK_S];
		left = keys[KeyEvent.VK_A];
		right = keys[KeyEvent.VK_D];

		attbut = keys[KeyEvent.VK_E];
		fattbut = keys[KeyEvent.VK_C];
		pbutt = keys[KeyEvent.VK_ESCAPE];
		runbutt = keys[KeyEvent.VK_SHIFT];

	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() < 0 || e.getKeyCode() >= keys.length)
			return;
		keys[e.getKeyCode()] = true;
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode() < 0 || e.getKeyCode() >= keys.length)
			return;
		keys[e.getKeyCode()] = false;
	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

	public boolean keyJustPressed(int keyCode){
		if(keyCode < 0 || keyCode >= keys.length)
			return false;
		return justPressed[keyCode];
	}
	
	public class Area extends BaseArea{

		EntityManager manager;
		Handler handler;
		private final static int playerXSpawn = -380, playerYSpawn = -3270;
		Rectangle playerRect;
		Random random = new Random();
		public int xPos = random.nextInt(7000 - 100) + 100;
		public int yPos = random.nextInt(5000 - 100) + 100;
		private int width = 8000;
		private int height = 6000;
		public int counter = 0;
		Animation enemyAnimation;
		Animation enemySAnimation;
		Rectangle rectangle;
		Rectangle rectangle2;

		public ArrayList<InWorldWalls> areaWalls;

		public Area(Handler handler, EntityManager entityManager) {
			super(handler, entityManager);
			name="S";
			this.handler = handler;
			handler.setXInWorldDisplacement(playerXSpawn);
			handler.setYInWorldDisplacement(playerYSpawn + 500);

			this.manager = entityManager;

			rectangle = new Rectangle();
			rectangle2 = new Rectangle();

			enemyAnimation = new Animation(100, Images.Enemy);
			enemySAnimation = new Animation(150, Images.SEnemy);

			this.areaWalls = new ArrayList<>();

			this.addWalls();


		}

		public void tick() {
			super.tick();

			this.manager.tick();

			for(Walls w : areaWalls) {

				w.tick();

			}

			this.enemyAnimation.tick();
			this.enemySAnimation.tick();
			this.respawnEnemyTick();

			this.collidedWithEnemy();


		}

		public void render(Graphics g) {

			Graphics2D g2 = (Graphics2D) g;

			rectangle = new Rectangle(this.getxPos() + 425 + handler.getXInWorldDisplacement(), this.getyPos()  + 478 + handler.getYInWorldDisplacement(), 100, 100);
			rectangle2 = new Rectangle( this.getxPos() + 290 - 50 + handler.getXInWorldDisplacement(), this.getyPos() + 330 - 50 + handler.getYInWorldDisplacement(), 450, 450);

			g2.drawImage(this.enemyAnimation.getCurrentFrame(), this.getxPos() + handler.getXInWorldDisplacement(), this.getyPos() + handler.getYInWorldDisplacement(), 1000, 1000, null);

			g2.setColor(Color.WHITE);

			this.manager.render(g2);

			if(GameSetUp.DEBUGMODE) {
				for (Walls w : areaWalls) {

					g2.setColor(Color.WHITE);

					w.render(g2);

				}

				g2.draw(this.rectangle);
				g2.draw(this.rectangle2);

			}

			g2.setColor(Color.WHITE);

			for(int i=0;i<handler.getWidth();i+=random.nextInt(45)){
				int s= random.nextInt(10)+1;
				g2.fillRect((random.nextInt(this.width)) + handler.getXInWorldDisplacement(), (random.nextInt(this.height)) + handler.getYInWorldDisplacement(), s, s);
			}

		}

		private void addWalls() {

			this.areaWalls.add(new InWorldWalls(handler, 0, 0, this.width, 2, "Wall"));
			this.areaWalls.add(new InWorldWalls(handler, 0, 0, 2, this.height, "Wall"));
			this.areaWalls.add(new InWorldWalls(handler, 0, this.height, this.width, 2, "Wall"));
			this.areaWalls.add(new InWorldWalls(handler, this.width, 0, 2, this.height, "Wall"));

		}

		public ArrayList<InWorldWalls> getWalls(){

			return this.areaWalls;

		}

		public void collidedWithEnemy() {

			if(handler.getEntityManager().getPlayer().getCollision().intersects(this.rectangle)) {
				
				handler.getGame().getMusicHandler().set_changeMusic("res/music/SEnemy.mp3");
				handler.getGame().getMusicHandler().play();			
				handler.getGame().getMusicHandler().setLoop(true);	
				handler.getGame().getMusicHandler().setVolume(0.1);
				State.setState(new UIListener(handler));


			}

		}

		public void respawnEnemyTick() {

			if(handler.getEntityManager().getPlayer().getCollision().intersects(this.rectangle2) && this.counter < 10) {

				this.setxPos( random.nextInt(7000 - 100) + 100);
				this.setyPos(random.nextInt(5000 - 100) + 100);
				
				if(this.getxPos() > 8000 || this.getyPos() > 6000) {
					
					this.setxPos( random.nextInt(7000 - 100) + 100);
					this.setyPos(random.nextInt(5000 - 100) + 100);
					
				}

				this.counter++;

			}

		}

		public int getxPos() {
			return xPos;
		}

		public void setxPos(int xPos) {
			this.xPos = xPos;
		}

		public int getyPos() {
			return yPos;
		}

		public void setyPos(int yPos) {
			this.yPos = yPos;
		}

	}

}
