package Game.Entities;

import java.awt.Graphics;
import java.awt.Rectangle;

import Main.Handler;

public class BaseEntity {

	protected Handler handler;
	
	public double xPosition;
	public double yPosition;
	
	public BaseEntity(Handler handler, double xPosition, double yPosition) {
		this.handler = handler;
	}
	
	public void tick() {
		
	}
	public void render(Graphics g){
		
	}
	
	public Rectangle getCollision() {
		return new Rectangle(); // Placeholder Rectangle
	}
	
	// Getters and Setters of Offsets.
	public double getXOffset() {
		return this.xPosition;
	}
	public double getYOffset() {
		return this.yPosition;
	}
	public void setXOffset(double xPosition) {
		this.xPosition = xPosition;
	}
	public void setYOffset(double yPosition) {
		this.yPosition = yPosition;
	}
	
}
