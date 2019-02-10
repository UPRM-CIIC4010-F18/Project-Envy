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

	ArrayList<Game.World.InvisibleWalls> InvisibleWalls;

	public WorldManager(Handler handler, EntityManager entityManager) {
		this.handler = handler;
		this.entityManager = entityManager;

		this.entityManager.AddEntity(new Tree(handler, 600, 600));
		this.entityManager.AddEntity(new EnemyOne(handler, 500, 800));

		AddInvisibleWalls();


	

	}

	public void tick() {

        for (InvisibleWalls iv: this.InvisibleWalls) {
            iv.tick();
        }
        
	}

	public void render(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		
		g2.setColor(Color.black);
		
		  if(GameSetUp.DEBUGMODE){
	            for (InvisibleWalls iv: this.InvisibleWalls) {
	                iv.render(g2);
	            }
	        }
	}
	
	// adds all the invisible walls in game
	private void AddInvisibleWalls() {
		InvisibleWalls = new ArrayList<>();
		InvisibleWalls.add(new InvisibleWalls(handler, 150, 0, 215, 490));
		InvisibleWalls.add(new InvisibleWalls(handler, 150, 600, 215, 495));
		InvisibleWalls.add(new InvisibleWalls(handler, 1800, -300, 180, 380));
		InvisibleWalls.add(new InvisibleWalls(handler, 1710, -150, 180, 250));
		InvisibleWalls.add(new InvisibleWalls(handler, 1600, 0, 60, 100));
	}
	
    public ArrayList<InvisibleWalls> getInvisibleWalls() {
        return InvisibleWalls;
    }

}
