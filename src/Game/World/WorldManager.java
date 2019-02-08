package Game.World;

import java.awt.Graphics;
import Game.Entities.EntityManager;
import Game.Entities.Dynamics.EnemyOne;
import Game.Entities.Statics.Tree;
import Main.Handler;

public class WorldManager {

	protected Handler handler;
	protected EntityManager entityManager;

	public WorldManager(Handler handler, EntityManager entityManager) {
		this.handler = handler;
		this.entityManager = entityManager;
		
		this.entityManager.AddEntity(new Tree(handler, 600, 600));
		this.entityManager.AddEntity(new EnemyOne(handler, 300, 800));
	}

	public void tick() {

		
		
	}

	public void render(Graphics g) {


	}

	
}
