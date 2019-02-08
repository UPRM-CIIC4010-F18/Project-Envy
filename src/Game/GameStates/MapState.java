package Game.GameStates;

import Main.GameSetUp;
import Main.Handler;
import Resources.Images;
import java.awt.*;
import java.util.ArrayList;

import Game.Entities.EntityManager;
import Game.Entities.Dynamics.Player;
import Game.Entities.Statics.Tree;
import Game.World.WorldManager;

public class MapState extends State {

	int xDisplacement, yDisplacement;

	WorldManager worldManager;
	EntityManager entityManager;
	Player player;

//	ArrayList<Polygon> invisibleWalls;

	Rectangle background = new Rectangle(3000, 3000);
	Color backgroundColor = new Color(61, 68, 128);

	public MapState(Handler handler) {
		super(handler);

		xDisplacement = -200;
		yDisplacement = -200;
		this.handler.setXDisplacement(xDisplacement);
		this.handler.setYDisplacement(yDisplacement);


//		invisibleWalls = new ArrayList<>();
//        int xPoly[] = {150, 250, 325, 375, 450, 275, 100};
//        int yPoly[] = {150, 100, 125, 225, 250, 375, 300};
//		invisibleWalls.add(new Polygon(xPoly, yPoly, xPoly.length));

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
		g2.drawImage(Images.Scaledmap, handler.getXDisplacement(), handler.getYDisplacement(), null);

		worldManager.render(g);
		entityManager.render(g);

//        if(GameSetUp.DEBUGMODE){
//            for(Polygon p : invisibleWalls){
//                for(int x: p.xpoints){
//                    x+=handler.getXDisplacement();
//                }
//                for(int y: p.ypoints){
//                    y+=handler.getYDisplacement();
//
//                }
//                g2.setColor(Color.RED);
//                g2.drawPolygon(p);
//            }
//        }

	}
}
