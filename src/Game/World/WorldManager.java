package Game.World;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

import Game.Entities.EntityManager;
import Game.Entities.Dynamics.EnemyOne;
import Game.Entities.Statics.SmokeHouse;
import Game.Entities.Statics.Tree;
import Main.GameSetUp;
import Main.Handler;

public class WorldManager {

	protected Handler handler;
	protected EntityManager entityManager;

	ArrayList<Game.World.Walls> worldWalls;

	public WorldManager(Handler handler, EntityManager entityManager) {
		this.handler = handler;
		this.entityManager = entityManager;

		this.entityManager.AddEntity(new Tree(handler, 600, 600));
		this.entityManager.AddEntity(new SmokeHouse(handler, 1153, 335));
		this.entityManager.AddEntity(new EnemyOne(handler, 500, 800));

		AddWalls();

	}

	public void tick() {

        for (Walls w: this.worldWalls) {
            w.tick();
        }
        
	}

	public void render(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		

		
		  if(GameSetUp.DEBUGMODE){
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
		worldWalls.add(new Walls(handler, 150, 120, 210, 360, "Wall"));
		worldWalls.add(new Walls(handler, 150, 600, 210, 495, "Wall"));
		
		worldWalls.add(new Walls(handler, 0, 450, 150, 200, "Wall"));
		worldWalls.add(new Walls(handler, 360,950, 480,5,"Wall" ));
		worldWalls.add(new Walls(handler, 845, 950, 5, 250, "Wall"));
		worldWalls.add(new Walls(handler, 850, 1200, 670, 5, "Wall"));
		worldWalls.add(new Walls(handler, 1520, 850, 5, 350, "Wall"));
		worldWalls.add(new Walls(handler, 1520, 840, 480, 5, "Wall"));
		worldWalls.add(new Walls(handler, 2000, 490, 5, 350, "Wall"));
		worldWalls.add(new Walls(handler, 2000, 460, 430, 5, "Wall"));
		worldWalls.add(new Walls(handler, 2450, 50, 5, 400, "Wall"));
		worldWalls.add(new Walls(handler, 2450, 50, 580, 5, "Wall"));
		worldWalls.add(new Walls(handler, 3020, -400, 5, 450, "Wall"));
		worldWalls.add(new Walls(handler, 1350, -400, 1620, 5, "Wall"));
		worldWalls.add(new Walls(handler, 1350, -400, 5, 250, "Wall"));
		worldWalls.add(new Walls(handler, 650, -150, 700, 5, "Wall"));
		worldWalls.add(new Walls(handler, 650, -150, 5, 270, "Wall"));
		worldWalls.add(new Walls(handler, 360, 120, 290, 5, "Wall"));
		
		
		worldWalls.add(new Walls(handler, 1800, -300, 180, 380 , "Wall"));
		worldWalls.add(new Walls(handler, 1710, -150, 180, 250, "Wall"));
		worldWalls.add(new Walls(handler, 1600, 0, 60, 100, "Wall"));
		worldWalls.add(new Walls(handler, 1662, -60, 50, 80, "Wall"));
		
		worldWalls.add(new Walls(handler, 1662, 55, 50, 50, "Door"));
	}
	
    public ArrayList<Walls> getWalls() {
        return worldWalls;
    }

}
