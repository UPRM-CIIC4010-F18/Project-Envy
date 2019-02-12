package Game.Entities.Dynamics;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import Game.GameStates.InWorldState;
import Game.GameStates.PauseState;
import Game.GameStates.State;
import Game.World.Walls;
import Game.World.InWorldAreas.BaseArea;
import Main.GameSetUp;
import Main.Handler;
import java.awt.event.KeyEvent;

public class Player extends BaseDynamicEntity {

	private Rectangle player;
	public boolean checkInWorld;

	public Player(Handler handler, int xPosition, int yPosition) {
		super(handler, yPosition, yPosition);

		this.xPosition = xPosition;
		this.yPosition = yPosition;

		player = new Rectangle();
		checkInWorld = false;
	}

	@Override
	public void tick() {
		super.tick();
		UpdateNextMove();
		PlayerInput();

	}

	@Override
	public void render(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;

		if (!checkInWorld)
			player = new Rectangle((int) xPosition, (int) yPosition, 25, 25);
		else
			player = new Rectangle((int) xPosition, (int) yPosition, 70, 70);

		g2.setColor(Color.RED);
		g2.fill(player);

		if (GameSetUp.DEBUGMODE) {
			g2.draw(nextArea);
		}
	}

	private void UpdateNextMove() {
		switch (facing) {
		case "Up":
			nextArea = new Rectangle((int) xPosition, (int) yPosition - speed, 24, 10);

			break;
		case "Down":
			nextArea = new Rectangle((int) xPosition, (int) yPosition + speed, 24, 25);

			break;
		case "Left":
			nextArea = new Rectangle((int) xPosition - speed, (int) yPosition, 25, 24);

			break;
		case "Right":
			nextArea = new Rectangle((int) xPosition + speed, (int) yPosition, 25, 24);

			break;
		}
	}

	private void PlayerInput() {

		boolean canMove = true;

		if (handler.getKeyManager().keyJustPressed(KeyEvent.VK_ESCAPE)) {
			PauseState.lastState = State.getState();
			State.setState(handler.getGame().pauseState);
		}

		if (handler.getKeyManager().runbutt) {
			speed = 2;
		} else {
			speed = 8;
		}

		for (Walls w : handler.getWorldManager().getWalls()) {
			if (nextArea.intersects(w)) {

				if (w.getType().equals("Wall")) {

					canMove = false;
					switch (facing) {
					case "Down":
						Move(false, 1);
						break;
					case "Up":
						Move(false, -1);
						break;
					case "Right":
						Move(true, 1);
						break;
					case "Left":
						Move(true, -1);
						break;
					}
					break;
				}

				else if (w.getType().equals("Entrance")) {
					canMove = true;

					if (w.getX() == (1662 + handler.getXDisplacement())
							&& w.getY() == (55 + handler.getYDisplacement())) {
						InWorldState.caveArea.oldPlayerXCoord = (int) getXOffset();
						InWorldState.caveArea.oldPlayerYCoord = (int) getYOffset() - 5;
						checkInWorld = true;
						State.setState(handler.getGame().inWorldState.setArea(InWorldState.caveArea));
					}

				}
			}

		}

		if (handler.getKeyManager().down & canMove) {
			Move(false, -speed);
			facing = "Down";
		} else if (handler.getKeyManager().up & canMove) {
			Move(false, speed);
			facing = "Up";
		} else if (handler.getKeyManager().right & canMove) {
			Move(true, -speed);
			facing = "Right";
		} else if (handler.getKeyManager().left & canMove) {
			Move(true, speed);
			facing = "Left";
		}

	}

	/**
	 * 
	 * @param XorY  where true is X and false is Y
	 * @param speed
	 */
	private void Move(boolean XorY, int speed) {

		if (!checkInWorld) {
			if (XorY) {
				handler.setXDisplacement(handler.getXDisplacement() + speed);
			} else {
				handler.setYDisplacement(handler.getYDisplacement() + speed);
			}
		} else {
			if (XorY) {
				handler.setXInWorldDisplacement((handler.getXInWorldDisplacement() + speed));
			} else {
				handler.setYInWorldDisplacement(handler.getYInWorldDisplacement() + speed);
			}

		}

	}

	@Override
	public Rectangle getCollision() {
		return player;
	}

	/**
	 * !!!!!!!!!TO REDESIGN OR DELETE!!!!!!!
	 * 
	 * 
	 * Called when the player has collided with another static entity. Used to push
	 * the player back from passing through a static entity.
	 *
	 * @param collidedXPos the xPosition the static entity is located at.
	 */
	public void WallBoundary(double collidedXPos) {

		int playerXPos = Math.abs(handler.getXDisplacement());

		if (playerXPos < collidedXPos / 2) {
			handler.setXDisplacement(handler.getXDisplacement() + 2);
		} else if (playerXPos > collidedXPos / 2) {
			handler.setXDisplacement(handler.getXDisplacement() - 2);
		}
	}

	/*
	 * Although the TRUE Player position is in the middle of the screen, these two
	 * methods give us the value as if the player was part of the world.
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
