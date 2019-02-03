package Game.Entities.Dynamics;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import Main.Handler;

public class Player extends BaseDynamicEntity {
	
	Rectangle player;
	
	public Player(Handler handler) {
		super(handler);
		
		player = new Rectangle();
	}
	
	@Override
	public void tick() {

		PlayerInput();
			
	}

	@Override
	public void render(Graphics g) {
		Graphics2D g2 = (Graphics2D)g;
		
		player = new Rectangle((int)handler.getWidth() / 2 - 5, (int) handler.getHeight() / 2, 25, 25);
		
		g2.setColor(Color.RED);
    	g2.fill(player);
    	g2.draw(player);
	}
	
	
	private void PlayerInput() {
		if (handler.getKeyManager().down){
			handler.setYDisplacement(handler.getYDisplacement() - 2);
        }
        else if (handler.getKeyManager().up){
        	handler.setYDisplacement(handler.getYDisplacement() + 2);
        }
        else if (handler.getKeyManager().right){
        	handler.setXDisplacement(handler.getXDisplacement() - 2);
        }
        else if (handler.getKeyManager().left){
        	handler.setXDisplacement(handler.getXDisplacement() + 2);
        }
	}

	public Rectangle GetCollisions() {
		return player; 
	}

	/**
	 * Called when the player has collided with another static entity.
	 * Used to push the player back from passing through a static entity.
	 * 
	 * @param collidedXPos the xPosition the static entity is located at.
	 */
	public void WallBoundary(int collidedXPos) {

		int playerXPos = Math.abs(handler.getXDisplacement());
		
		if (playerXPos < collidedXPos / 2) {
			handler.setXDisplacement(handler.getXDisplacement() + 2);
		}
		else if(playerXPos > collidedXPos / 2) {
			handler.setXDisplacement(handler.getXDisplacement() - 2); 
		}
	}
}
