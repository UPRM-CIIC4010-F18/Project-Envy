package Game.World;

import Game.Entities.EntityManager;
import Game.Entities.Statics.SmokeHouse;
import Game.Entities.Statics.Tree;
import Input.MouseManager.Circle;
import Main.GameSetUp;
import Main.Handler;
import Resources.Animation;
import Resources.Images;

import java.awt.*;
import java.util.ArrayList;

public class WorldManager {

	protected Handler handler;
	private Circle circle;
	public EntityManager entityManager;
	Animation animation;
	Rectangle rectangle;
	private int xPos;
	private int yPos;
	String alphabet1 = " abcdefghijklmnopqrstuvwxyzabcd";

	ArrayList<Game.World.Walls> worldWalls;

	public WorldManager(Handler handler, EntityManager entityManager) {
		this.handler = handler;
		this.entityManager = entityManager;
		this.animation = new Animation(50, Images.SItem);
		this.xPos = 0 - 2000;
		this.yPos = this.handler.getHeight() - 100;

		rectangle = new Rectangle();
		circle = handler.getGame().getMouseManager().new Circle(5627,380, this.handler);
		this.entityManager.AddEntity(new Tree(handler, 600, 600));
		this.entityManager.AddEntity(new SmokeHouse(handler, 1153, 335));

		this.entityManager.AddEntity(handler.newEnemy(Images.PEnemyIdle,handler,500, 800,"MapState","Jovan","None","EnemyOne",100,25,40,1,8,12,20,10,20,10,1,5,"None","Fire",null,null)); // lvl 0 dificulty
		this.entityManager.AddEntity(handler.newEnemy(Images.PEnemyIdle,handler,1400, 600,"MapState","Common Rat","None","EnemyOne",100,25,40,1,8,12,20,10,20,10,1,5,"None","Fire",null,null)); // lvl 0 dificulty
		this.entityManager.AddEntity(handler.newEnemy(Images.PEnemyIdle,handler,2400, -200,"MapState","Common Rat","None","EnemyOne",100,25,40,1,8,12,20,10,20,10,1,5,"None","Fire",null,null)); // lvl 0 dificulty
		
		this.entityManager.AddEntity(circle);

		AddWalls();

	}

	public void tick() {

		for (Walls w: this.worldWalls) {
			w.tick();
		}
		this.animation.tick();
		this.collidedWithWall();
		this.moveString();

	}

	public void render(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		if(!this.handler.getEntityManager().getPlayer().getWeaken()) {
		g2.drawImage(this.animation.getCurrentFrame(),
		3027 + handler.getXDisplacement(), 3875 + 
		handler.getYDisplacement(), 30, 30, null);}
		rectangle = new Rectangle( 3027 + 
		handler.getXDisplacement(), 3875 + 
		handler.getYDisplacement(), 30, 30);
		g2.setColor(Color.ORANGE);
		g2.setFont(new Font("AR ESSENCE", Font.PLAIN, 100));
		g2.drawString(this.getString(), this.xPos,this.yPos);

		if(GameSetUp.DEBUGMODE){

			g2.setColor(Color.BLACK);
			g2.draw(rectangle);
			for (Walls w: this.worldWalls) {

				if (w.getType().equals("Wall"))
					g2.setColor(Color.black);
				else
					g2.setColor(Color.PINK);

				w.render(g2);
			}
		}
	}

	// adds all the walls in game
	private void AddWalls() {
		worldWalls = new ArrayList<>();

		//Bridge Walls
		worldWalls.add(new Walls(handler, 150, 120, 210, 360, "Wall"));
		worldWalls.add(new Walls(handler, 150, 600, 210, 495, "Wall"));
		worldWalls.add(new Walls(handler, 0, 450, 150, 200, "Wall"));

		//Walls of the Island
		///Left Bottom Border of the Island
		worldWalls.add(new Walls(handler, 360, 950, 480, 5, "Wall" ));
		worldWalls.add(new Walls(handler, 845, 950, 5, 250, "Wall"));
		///Bottom of the Island
		worldWalls.add(new Walls(handler, 850, 1200, 670, 5, "Wall"));
		///Bottom of the Island (right-ish)
		worldWalls.add(new Walls(handler, 1500, 830, 5, 370, "Wall"));
		worldWalls.add(new Walls(handler, 1500, 820, 500, 5, "Wall"));
		worldWalls.add(new Walls(handler, 2000, 440, 5, 390, "Wall"));
		///Right Side of the Island
		worldWalls.add(new Walls(handler, 2020, 440, 400, 5, "Wall"));
		worldWalls.add(new Walls(handler, 2400, 50, 5, 400, "Wall"));
		////Top right of the Island
		worldWalls.add(new Walls(handler, 2420, 40, 600, 5, "Wall"));
		worldWalls.add(new Walls(handler, 3000, -400, 5, 450, "Wall"));
		worldWalls.add(new Walls(handler, 1365, -380, 1650, 5, "Wall"));
		///Top of the Island
		worldWalls.add(new Walls(handler,1365,-390,5,260,"Wall"));
		worldWalls.add(new Walls(handler, 670, -130, 700, 5, "Wall"));
		///Top right of the Island
		worldWalls.add(new Walls(handler, 670, -130, 5, 250, "Wall"));
		worldWalls.add(new Walls(handler, 360, 120, 310, 5, "Wall"));
		///Right Mountains
		worldWalls.add(new Walls(handler, 1860, -320, 50, 50 , "Wall"));
		worldWalls.add(new Walls(handler, 1830, -270, 120, 50 , "Wall"));
		worldWalls.add(new Walls(handler, 1800, -220, 80, 70 , "Wall"));
		worldWalls.add(new Walls(handler, 1710, -150, 250, 250, "Wall"));
		worldWalls.add(new Walls(handler, 1600, 0, 60, 100, "Wall"));
		worldWalls.add(new Walls(handler, 1662, -60, 50, 80, "Wall"));
		worldWalls.add(new Walls(handler, 1950, -270, 200, 50, "Wall"));
		worldWalls.add(new Walls(handler, 1950, -300, 100, 50, "Wall"));

		worldWalls.add(new Walls(handler, 1980, -350, 50, 50, "Wall"));
		worldWalls.add(new Walls(handler, 1950, -250, 200, 100, "Wall"));	
		worldWalls.add(new Walls(handler, 1960, -150, 120, 100, "Wall"));

		worldWalls.add(new Walls(handler, 1662, 55, 50, 50, "Door Cave"));
		worldWalls.add(new Walls(handler, (int) circle.getXOffset(),(int) 
				circle.getYOffset(), 20, 20, "Door S"));	

		///Left Mountains
		worldWalls.add(new Walls(handler, 700, 180, 140, 200, "Wall"));
		worldWalls.add(new Walls(handler, 620, 210, 80, 160, "Wall"));
		worldWalls.add(new Walls(handler, 840, 240, 120, 110, "Wall"));
		worldWalls.add(new Walls(handler, 580, 300, 40, 50, "Wall"));
	}

	public void collidedWithWall() {
		if(this.handler.getEntityManager().getPlayer().getCollision().intersects(this.rectangle)) {
		if(!handler.getGame().getMusicHandler().getEPlayer().isEmpty()&&!handler.getGame().getMusicHandler().getEffect(0).equals(null)) {
		handler.getGame().getMusicHandler().stopEffect(0);}    	
		handler.getGame().getMusicHandler().playEffect("res/music/SSAcquired.wav",1);
		handler.getEntityManager().getPlayer().setWeaken(true);
		}
	}
	public void moveString() {
		if(this.handler.getEntityManager().getPlayer().getWeaken()) {
			this.xPos += 10;
		}
		if(this.xPos > this.handler.getWidth() + 100) {
			this.xPos = this.handler.getWidth() + 100;
		}
	}
	public ArrayList<Walls> getWalls() {
		return worldWalls;
	}
	public String getString() {  	
		return "* " + this.getString("xhttgdexsfhpeny"
				+ "jrefhvznwji", 5) + " *";	    	
	}
	public String getString(String str, int key) {	
		String newString = "";	
		for(int i = 0; i < str.length(); i++) {		
			for(int j = key + 1; j < alphabet1.length(); j++) {			
				if(str.charAt(i) == alphabet1.charAt(j)) {				
					newString += alphabet1.charAt(j - key);				
				}		
			}
		}		
		return newString.toUpperCase();		
	}

}
