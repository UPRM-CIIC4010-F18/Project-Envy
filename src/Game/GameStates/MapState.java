package Game.GameStates;

import Main.GameSetUp;
import Main.Handler;
import Resources.Animation;
import Resources.Images;
import java.awt.*;
import java.awt.event.KeyEvent;

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
	
	private Animation map;


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
		
		map = new Animation(300, Images.map);

	}

	@Override
	public void tick() {
		map.tick();

	    if(GameSetUp.LOADING){
	        if(GameSetUp.loadCounter>=90){
	            GameSetUp.loadCounter=0;
	            GameSetUp.LOADING=false;
	            return;
            }
            worldManager.tick();
            GameSetUp.loadCounter++;
        }else {

            if (handler.getKeyManager().keyJustPressed(KeyEvent.VK_ESCAPE)) {
                handler.getGame().pauseState.lastState = State.getState();
				GameSetUp.SWITCHING=true;
				State.setState(handler.getGame().pauseState);
            } else {

                worldManager.tick();
                entityManager.tick();
            }
        }
	}

	@Override
	public void render(Graphics g) {

	    if(!GameSetUp.LOADING) {
            Graphics2D g2 = (Graphics2D) g;

            g2.setColor(backgroundColor);
            g2.fill(background);

            // Movement of the Image
            g2.drawImage(map.getCurrentFrame(), handler.getXDisplacement() - initialXMapDisplacement, handler.getYDisplacement() - initialYMapDisplacement, null);

            worldManager.render(g);
            entityManager.render(g);
        }else{
	        g.drawImage(Images.Loading,0,0,handler.getWidth(),handler.getHeight(),null);
        }
	}

}
