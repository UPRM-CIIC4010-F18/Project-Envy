package Game.Entities;

import java.awt.Graphics;
import java.awt.Rectangle;

import Main.Handler;

public class BaseEntity {

	protected Handler handler;
	
	public BaseEntity(Handler handler) {
		this.handler = handler;
	}
	
	public void tick() {
		
	}
	public void render(Graphics g){
		
	}
	
	public Rectangle getCollision() {
		return new Rectangle(); // Placeholder Rectangle
	}
	
	public int getX() {
		return 0; // Placeholder value
	}
}
