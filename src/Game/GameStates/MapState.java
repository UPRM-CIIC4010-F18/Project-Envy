package Game.GameStates;

import Main.Handler;
import Resources.Images;
import java.awt.*;
import Game.Entities.EntityManager;
import Game.Entities.Dynamics.Player;
import Game.World.WorldManager;

public class MapState extends State {

	int xDisplacement, yDisplacement;

	WorldManager worldManager;
	EntityManager entityManager;
	Player player;

	//changes the initial spawn of the player
	int initialXMapDisplacement=1450;
	int initialYMapDisplacement=500;


	Rectangle background = new Rectangle(3000, 3000);
	Color backgroundColor = new Color(61, 68, 128);

	public MapState(Handler handler) {
		super(handler);

		xDisplacement = -200;
		yDisplacement = -200;
		this.handler.setXDisplacement(xDisplacement);
		this.handler.setYDisplacement(yDisplacement);

		player = new Player(handler, (int) handler.getWidth() / 2 - 5, (int) handler.getHeight() / 2);

		entityManager = new EntityManager(handler, player);
		worldManager = new WorldManager(handler, entityManager);
		this.handler.setWorldManager(worldManager);
		this.handler.setEntityManager(entityManager);

	}

	@Override
	public void tick() {
		
		worldManager.tick();
		entityManager.tick();
	}

	@Override
	public void render(Graphics g) {

		Graphics2D g2 = (Graphics2D) g;



        g2.setColor(backgroundColor);
		g2.fill(background);

		// Movement of the Image
		g2.drawImage(Images.Scaledmap, handler.getXDisplacement()-initialXMapDisplacement, handler.getYDisplacement()-initialYMapDisplacement, null);

		worldManager.render(g);
		entityManager.render(g);

	}

}
