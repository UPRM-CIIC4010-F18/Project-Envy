package Game.GameStates;

import Game.Entities.EntityManager;
import Game.World.InWorldAreas.BaseArea;
import Game.World.InWorldAreas.CaveArea;
import Main.Handler;
import java.awt.*;

import Display.UI.UIManager;

public class InWorldState extends State{


	EntityManager entityManager;	// To manager the entities within the InWorld
	private UIManager uM ;
    public static BaseArea currentArea;
    public static BaseArea caveArea;
    public static BaseArea SArea;

    public InWorldState(Handler handler) {
        super(handler);
        uM= new UIManager(handler);
        entityManager = new EntityManager(handler, handler.getEntityManager().getPlayer());
        
        caveArea = new CaveArea(handler, entityManager);
        SArea = uM.new Area(handler, entityManager);

    }

    @Override
    public void tick() {
        
        if(currentArea!=null) {
            currentArea.tick();
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
