package Game.Entities;

import java.awt.Graphics;
import java.util.ArrayList;

import Game.Entities.Dynamics.Player;
import Game.Entities.Statics.Tree;
import Main.Handler;

public class EntityManager {
	
	protected Handler handler;
	protected Player player;
	
	ArrayList<BaseEntity> entities;
	
	public EntityManager(Handler handler, Player player) {
		this.handler = handler;
		this.player = player;
		

		entities = new ArrayList<>();
	}
	
	public void tick() {
		
		for (BaseEntity e : entities) {
			CheckCollisions(e);
			e.tick();
		}
		
		player.tick();
		
	}
	
	
	private void CheckCollisions(BaseEntity e) {
		
		
		if ( player.GetCollisions().intersects(e.getCollision())) {
			// TODO: Check if Hostile or A collision
			player.WallBoundary(e.getX());
			
		}

		
	}

	public void render(Graphics g){
		
		player.render(g);
		
		for (BaseEntity e : entities) {
			e.render(g);
		}

	}
	
	
	public void AddEntity(BaseEntity e) {
		entities.add(e);
	}
	
	public Player getPlayer() {
		return player;
	}
}
