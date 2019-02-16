package Game.World.InWorldAreas;

import Main.GameSetUp;
import Main.Handler;
import Resources.Images;
import java.awt.*;
import java.util.ArrayList;

import Game.Entities.EntityManager;
import Game.Entities.Dynamics.EnemyOne;
import Game.World.Walls;

public class CaveArea extends BaseArea {

	Rectangle exit;
	Rectangle playerRect;
	EntityManager entityManager;

	private int imageWidth = 2560, imageHeight = 3360;

	private Rectangle background = new Rectangle(3000, 3000);
	private Color backgroundColor = Color.BLUE;

	public static ArrayList<InWorldWalls> caveWalls;

	public CaveArea(Handler handler, EntityManager entityManager) {
		super(handler, entityManager);

		playerRect = new Rectangle((int) handler.getWidth() / 2 - 5, (int) (handler.getHeight() / 2) + 300, 70, 70);

		// Original player x and y location relative to the image displacement.
		handler.setXInWorldDisplacement(-390);
		handler.setYInWorldDisplacement(-2670);

		this.entityManager = entityManager;
		
		this.entityManager.AddEntity(new EnemyOne(handler, 300, 1000));

		caveWalls = new ArrayList<>();
		AddWalls();

	}

	public void tick() {
		super.tick();

		for (Walls w : caveWalls) {
			w.tick();
		}
		
		entityManager.tick();

	}

	@Override
	public void render(Graphics g) {
		super.render(g);

		Graphics2D g2 = (Graphics2D) g;

		g2.setColor(backgroundColor);
		g2.fill(background);

		g.drawImage(Images.ScaledCave, handler.getXInWorldDisplacement(), handler.getYInWorldDisplacement(), null);

		if (GameSetUp.DEBUGMODE) {
			for (Walls w : caveWalls) {

				if (w.getType().equals("Wall"))
					g2.setColor(Color.black);
				else
					g2.setColor(Color.PINK);

				w.render(g2);
			}
		}
		
		entityManager.render(g);
	}

	private void AddWalls() {

		// Borders
		caveWalls.add(new InWorldWalls(handler, 0, 0, 10, imageHeight, "Wall"));
		caveWalls.add(new InWorldWalls(handler, 0, 130, imageWidth, 10, "Wall"));
		caveWalls.add(new InWorldWalls(handler, 0, imageHeight - 10, imageWidth, 10, "Wall"));
		caveWalls.add(new InWorldWalls(handler, imageWidth - 10, 0, 10, imageHeight, "Wall"));

		// Holes
		caveWalls.add(new InWorldWalls(handler, 190, 2280, 100, 100, "Wall"));
		caveWalls.add(new InWorldWalls(handler, 500, 200, 100, 100, "Wall"));
		caveWalls.add(new InWorldWalls(handler, 1310, 520, 100, 100, "Wall"));

		// Water/Lava Areas
		caveWalls.add(new InWorldWalls(handler, 0, 3100, 260, 300, "Wall"));

		caveWalls.add(new InWorldWalls(handler, imageWidth/2 - 350, 330, 250, 100, "Door"));

	}

	@Override
	public ArrayList<InWorldWalls> getWalls() {
		return caveWalls;
	}

}
