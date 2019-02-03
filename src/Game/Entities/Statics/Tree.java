package Game.Entities.Statics;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import Main.Handler;
import Resources.Images;

public class Tree extends BaseStaticEntity {
	
	Rectangle collision;
	int width, height,
		xOffset, yOffset;
	
	public Tree(Handler handler) {
		super(handler);
		width = 100;
		height = 100;
		xOffset = 600;
		yOffset = 600;
		
		collision = new Rectangle();
	}
	
	
	@Override
	public void render(Graphics g) {
		
		Graphics2D g2 = (Graphics2D)g;
		
		g.drawImage(Images.tree, handler.getXDisplacement() + xOffset, handler.getYDisplacement() + yOffset, width, height, null);
		collision = new Rectangle(handler.getXDisplacement() + xOffset + 35, handler.getYDisplacement() + yOffset + 50, width/4, height/2);
		
		g2.setColor(Color.black);
		g2.draw(collision);
		
	}
	
	@Override
	public Rectangle getCollision() {
		return collision;
	}
	
	@Override
	public int getX() {
		return xOffset;
	}
	
	
}
