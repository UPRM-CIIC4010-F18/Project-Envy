package Display.UI;

import Main.GameSetUp;
import Main.Handler;
import Resources.Animation;
import Resources.Images;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Random;

import Game.Entities.EntityManager;
import Game.World.Walls;
import Game.World.InWorldAreas.BaseArea;
import Game.World.InWorldAreas.InWorldWalls;

import javax.swing.*;

/**
 * Created by AlexVR on 7/1/2018.
 */
public class UIManager {

	private Handler handler;
	private ArrayList<UIObject> objects;

	public UIManager(Handler handler){
		this.handler=handler;
		objects = new ArrayList<UIObject>();
	}

	public void tick(){
		for(UIObject o: objects){
			o.tick();
		}
	}

	public void Render(Graphics g){
		for(UIObject o: objects){
			o.render(g);
		}
	}

	public void onMousePressed(MouseEvent e) {
		for(UIObject o: objects){
			o.onMousePressed(e);
		}
	}

	public void onMouseMove(MouseEvent e){
		for(UIObject o: objects){
			o.onMouseMove(e);
		}
	}

	public void onMouseRelease(MouseEvent e){
		for(UIObject o: objects){
			o.onMouseRelease(e);
		}
	}

	public void addObjects (UIObject o){
		objects.add(o);
	}

	public void removeObsjects(UIObject o){
		objects.remove(o);
	}

	public Handler getHandler() {
		return handler;
	}

	public void setHandler(Handler handler) {
		this.handler = handler;
	}

	public ArrayList<UIObject> getObjects() {
		return objects;
	}

	public void setObjects(ArrayList<UIObject> objects) {
		this.objects = objects;
	}

	public void isActive(ArrayList<UIObject> o, Boolean active){
		for(UIObject i: o){
			i.active=active;
		}
	}

	public class Area extends BaseArea{

		EntityManager manager;
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

			g2.drawImage(Images.ScaledArea, handler.getXInWorldDisplacement(), handler.getYInWorldDisplacement(), null);

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

				System.exit(0);

			}

		}

		public void respawnEnemyTick() {

			if(handler.getEntityManager().getPlayer().getCollision().intersects(this.rectangle2) && this.counter <= 10) {

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
