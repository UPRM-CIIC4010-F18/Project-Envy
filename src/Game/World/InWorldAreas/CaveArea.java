package Game.World.InWorldAreas;

import Main.Handler;
import Resources.Images;
import java.awt.*;
import Game.Entities.EntityManager;

public class CaveArea extends BaseArea {

	Rectangle exit;
	Rectangle playerRect;
	EntityManager entityManager;

	public CaveArea(Handler handler, EntityManager entityManager) {
		super(handler);

		playerRect = new Rectangle((int) handler.getWidth() / 2 - 5, (int) (handler.getHeight() / 2) + 300, 70, 70);

		handler.setXInWorldDisplacement(-100);
		handler.setYInWorldDisplacement(-1500);
		
		this.entityManager = entityManager;
		
		
	}

	public void tick() {
		super.tick();
		entityManager.tick();
	
	}

	@Override
	public void render(Graphics g) {
		super.render(g);

		Graphics2D g2 = (Graphics2D) g;

		g.drawImage(Images.ScaledCave, handler.getXInWorldDisplacement(), handler.getYInWorldDisplacement(), null);

		g2.setColor(Color.red);

		entityManager.render(g);
//		g2.fill(playerRect);
	}

}
