package Game.Entities.Dynamics;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import Game.GameStates.InWorldState;
import Game.GameStates.State;
import Game.World.InWorldAreas.CaveArea;
import Main.Handler;

public class EnemyOne extends BaseHostileEntity {

	Rectangle enemyOne;
	int width, height;

	public EnemyOne(Handler handler, int xPosition, int yPosition, String state) {
		super(handler, yPosition, yPosition,state);
		width = 30;
		height = 30;
		speed = 1;
		this.setXOffset(xPosition);
		this.setYOffset(yPosition);

		this.foundState = state;
		enemyOne = new Rectangle();
	}

	@Override
	public void tick() {
		
		if(!Player.isinArea)super.tick(); 
		
	}

	@Override
	public void render(Graphics g) {
		super.render(g);
		Graphics2D g2 = (Graphics2D) g;	

		if(!Player.isinArea) {
			if (!Player.checkInWorld) {
				enemyOne = new Rectangle((int) (handler.getXDisplacement() + getXOffset()),
						(int) (handler.getYDisplacement() + getYOffset()), 30, 30);
			} else {
				enemyOne = new Rectangle((int) (handler.getXInWorldDisplacement() + getXOffset()),
						(int) (handler.getYInWorldDisplacement() + getYOffset()), 70, 70);

			}

			g2.setColor(Color.black);

			g2.fill(enemyOne);

		}
	}



	@Override
	public Rectangle getCollision() {
		return enemyOne;
	}

}
