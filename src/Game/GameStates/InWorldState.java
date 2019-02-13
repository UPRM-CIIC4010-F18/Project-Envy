package Game.GameStates;

import Game.Entities.EntityManager;
import Game.World.InWorldAreas.BaseArea;
import Game.World.InWorldAreas.CaveArea;
import Main.Handler;
import java.awt.*;
import java.awt.event.KeyEvent;

public class InWorldState extends State{


	EntityManager entityManager;	// To manager the entities within the InWorld
    public static BaseArea currentArea;
    public static BaseArea caveArea;

    public InWorldState(Handler handler) {
        super(handler);

        entityManager = new EntityManager(handler, handler.getEntityManager().getPlayer());
        
        caveArea = new CaveArea(handler, entityManager);
    }

    @Override
    public void tick() {

        ///TEMP CODE TO EXIT STATE///
        if (handler.getKeyManager().keyJustPressed(KeyEvent.VK_ESCAPE)) {
            PauseState.lastState = State.getState();
            State.setState(handler.getGame().pauseState);
        }
        /////////////////////////////
        
        if(currentArea!=null) {
            currentArea.tick();
            entityManager.tick();
        }
    	
    }

    @Override
    public void render(Graphics g) {

    	Graphics2D g2 = (Graphics2D)g;
        if(currentArea!=null) {
            currentArea.render(g);
        }
    	
    }

    public State setArea(BaseArea area){
        currentArea = area;
        return this;
    }

    
}
