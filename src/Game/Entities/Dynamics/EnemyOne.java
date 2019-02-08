package Game.Entities.Dynamics;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import Main.Handler;

public class EnemyOne extends BaseHostileEntity {

	Rectangle enemyOne;
	int width, height;
	
	
	public EnemyOne(Handler handler, int xPosition, int yPosition) {
		super(handler, yPosition, yPosition);
		width = 30;
		height = 30;
		speed = 1;
		this.setXOffset(xPosition);
		this.setYOffset(yPosition);
		
		enemyOne = new Rectangle();
	}
	
	@Override
	public void tick() {
        super.tick();
	}



	
	@Override
	public void render(Graphics g) {
		super.render(g);
		Graphics2D g2 = (Graphics2D)g;
		
		g2.setColor(Color.black);
		
		enemyOne = new Rectangle((int) (handler.getXDisplacement()+getXOffset()),(int) (handler.getYDisplacement()+getYOffset()), 30, 30);
		g2.fill(enemyOne);
	}
	
	@Override
	public Rectangle getCollision() {
		return enemyOne;
	}

}
