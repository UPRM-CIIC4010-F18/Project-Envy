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

	private int imageWidth = 3680, imageHeight = 4000;
	public final static int playerXSpawn = -380, playerYSpawn = -3270;

	private Rectangle background = new Rectangle(3000, 3000);
	private Color backgroundColor = Color.BLUE;

	public static ArrayList<InWorldWalls> caveWalls;

	public CaveArea(Handler handler, EntityManager entityManager) {
		super(handler, entityManager);

		playerRect = new Rectangle((int) handler.getWidth() / 2 - 5, (int) (handler.getHeight() / 2) + 300, 70, 70);

		// Original player x and y location relative to the image displacement.
		handler.setXInWorldDisplacement(playerXSpawn);
		handler.setYInWorldDisplacement(playerYSpawn);

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
		System.out.println("X: " + handler.getEntityManager().getPlayer().getXOffset());
		System.out.println("Y: " + handler.getEntityManager().getPlayer().getYOffset());

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


		caveWalls.add(new InWorldWalls(handler, 100, 0, 10, imageHeight, "Wall"));								// Left Border
		caveWalls.add(new InWorldWalls(handler, 0, imageHeight-100, imageWidth/3, 50, "Wall"));					// Bottom LeftBorder (Left side relative to Entrance)
		caveWalls.add(new InWorldWalls(handler, imageWidth/2-350, imageHeight-100, imageWidth/4, 50, "Wall"));	// Bottom RightBorder (Right side relative to Entrance)
		caveWalls.add(new InWorldWalls(handler, 0, 130, imageWidth, 10, "Wall"));								//
		caveWalls.add(new InWorldWalls(handler, imageWidth - 130, 0, 10, imageHeight, "Wall"));					// Right Border


		caveWalls.add(new InWorldWalls(handler, 200, 3400, 400, 400, "Wall"));									// Left side Pond
		caveWalls.add(new InWorldWalls(handler, 500, 3075, 125, 100, "Wall"));									// Left side Water Hole
		
		caveWalls.add(new InWorldWalls(handler, 2440, 3355, 1, 500, "Wall"));									// Water Lake
		caveWalls.add(new InWorldWalls(handler, 1985, 3190, 500, 140, "Wall"));									//
		caveWalls.add(new InWorldWalls(handler, 1665, 3030, 500, 140, "Wall"));									//
		caveWalls.add(new InWorldWalls(handler, 1495, 2285, 1040, 700, "Wall"));								//
		caveWalls.add(new InWorldWalls(handler, 1595, 2985, 100, 100, "Wall"));									//
		caveWalls.add(new InWorldWalls(handler, 2520, 2750, 800, 1, "Wall"));									//
		caveWalls.add(new InWorldWalls(handler, 3258, 2608, 400, 400, "Wall"));									//
		
		caveWalls.add(new InWorldWalls(handler, 216, 428, 1030, 1000, "Wall"));									// Lava Lake
		caveWalls.add(new InWorldWalls(handler, 1246, 518, 300, 415, "Wall"));									// 
		caveWalls.add(new InWorldWalls(handler, 222, 1428, 1010, 130, "Wall"));									//
		caveWalls.add(new InWorldWalls(handler, 184, 1640, 100, 100, "Wall"));									// Lava Hole
		
		
		
		caveWalls.add(new InWorldWalls(handler, 176, 140, 400, 400, "Wall"));									// TopLeft Side Wall with torch
		caveWalls.add(new InWorldWalls(handler, 661, 205, 120, 100, "Wall"));									// Hole next to TopLeft Side wall with torch
		
		
		caveWalls.add(new InWorldWalls(handler, 1940, 2130, 100, 200, "Wall"));									// Pond next to Statue
		caveWalls.add(new InWorldWalls(handler, 2076, 1850, 150, 330, "Wall"));									// Statue
		
		caveWalls.add(new InWorldWalls(handler, 3380, 510, 120, 100, "Wall"));									// Hole next to Exit
		caveWalls.add(new InWorldWalls(handler, 2744, 140, 200, 300, "Wall"));									// Left wall relative to Exit
		caveWalls.add(new InWorldWalls(handler, 3288, 140, 200, 300, "Wall"));									// Right wall relative to Exit
		
		caveWalls.add(new InWorldWalls(handler, 2950, 340, 320, 100, "Door"));	//backexit							// Exit
		caveWalls.add(new InWorldWalls(handler, 1230, 3900, 280, 100, "Door"));//front exitExit
		caveWalls.add(new InWorldWalls(handler, imageWidth/3, imageHeight, 300, 50, "Wall"));					// Entrance
		

	}

	@Override
	public ArrayList<InWorldWalls> getWalls() {
		return caveWalls;
	}
}




