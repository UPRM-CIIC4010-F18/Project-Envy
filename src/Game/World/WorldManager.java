package Game.World;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

import Game.Entities.EntityManager;
import Game.Entities.Dynamics.EnemyOne;
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
		this.entityManager.AddEntity(new EnemyOne(handler, 500, 800));

		AddWalls();


	

	}

	public void tick() {

        for (Walls iv: this.worldWalls) {
            iv.tick();
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
		worldWalls.add(new Walls(handler, 150, 0, 215, 490, "Wall"));
		worldWalls.add(new Walls(handler, 150, 600, 215, 495, "Wall"));
		worldWalls.add(new Walls(handler, 1800, -300, 180, 380 , "Wall"));
		worldWalls.add(new Walls(handler, 1710, -150, 180, 250, "Wall"));
		worldWalls.add(new Walls(handler, 1600, 0, 60, 100, "Wall"));
		
		worldWalls.add(new Walls(handler, 1662, 55, 50, 50, "Entrance"));
	}
	
    public ArrayList<Walls> getWalls() {
        return worldWalls;
    }

}
