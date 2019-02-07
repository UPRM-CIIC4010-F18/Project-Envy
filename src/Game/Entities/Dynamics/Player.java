package Game.Entities.Dynamics;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import Game.GameStates.PauseState;
import Game.GameStates.State;
import Main.Handler;
import javafx.scene.input.KeyCode;

import java.awt.event.KeyEvent;


public class Player extends BaseDynamicEntity {
	
	Rectangle player;
	
	public Player(Handler handler, int xPosition, int yPosition) {
		super(handler, yPosition, yPosition);
		
		this.xPosition = xPosition;
		this.yPosition = yPosition;
		
		player = new Rectangle();
	}
	
	@Override
	public void tick() {
		if(handler.getKeyManager().runbutt){
		    speed = 2;
        }else{
		    speed =4;
        }
		PlayerInput();
		if (handler.getKeyManager().keyJustPressed(KeyEvent.VK_ESCAPE)) {
            PauseState.lastState = State.getState();
			State.setState(handler.getGame().pauseState);
		}
	}

	@Override
	public void render(Graphics g) {
		Graphics2D g2 = (Graphics2D)g;
		
		player = new Rectangle((int)xPosition, (int)yPosition, 25, 25);
		
		g2.setColor(Color.RED);
    	g2.fill(player);
    	g2.draw(player);
	}
	
	
	private void PlayerInput() {
		if (handler.getKeyManager().down){
			handler.setYDisplacement(handler.getYDisplacement() - speed);
        }
        if (handler.getKeyManager().up){
        	handler.setYDisplacement(handler.getYDisplacement() + speed);
        }
        if (handler.getKeyManager().right){
        	handler.setXDisplacement(handler.getXDisplacement() - speed);
        }
        if (handler.getKeyManager().left){
        	handler.setXDisplacement(handler.getXDisplacement() + speed);
        }
	}

	
	@Override
	public Rectangle getCollision() {
		return player; 
	}

	/**
	 * Called when the player has collided with another static entity.
	 * Used to push the player back from passing through a static entity.
	 * 
	 * @param collidedXPos the xPosition the static entity is located at.
	 */
	public void WallBoundary(double collidedXPos) {

		int playerXPos = Math.abs(handler.getXDisplacement());
		
		if (playerXPos < collidedXPos / 2) {
			handler.setXDisplacement(handler.getXDisplacement() + 2);
		}
		else if(playerXPos > collidedXPos / 2) {
			handler.setXDisplacement(handler.getXDisplacement() - 2); 
		}
	}
	

	
	/*
	 * Although the TRUE Player position is in the middle of the screen,
	 * these two methods give us the value as if the player was part of the world.
	 */
	@Override
	public double getXOffset() {
		return -this.handler.getXDisplacement() + xPosition;
	}
	
	@Override
	public double getYOffset() {
		return -this.handler.getYDisplacement() + yPosition;
	}

}
