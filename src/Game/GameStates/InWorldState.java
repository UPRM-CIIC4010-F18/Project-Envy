package Game.GameStates;

import Game.Entities.EntityManager;
import Game.World.InWorldAreas.BaseArea;
import Game.World.InWorldAreas.CaveArea;
import Main.GameSetUp;
import Main.Handler;
import java.awt.*;
import java.awt.event.KeyEvent;

import Resources.Images;

public class InWorldState extends State{


    public EntityManager entityManager;	// To manager the entities within the InWorld
    public static BaseArea currentArea;
    public static BaseArea caveArea;
    public static BaseArea SArea;

    public InWorldState(Handler handler) {
        super(handler);
        entityManager = new EntityManager(handler, handler.getEntityManager().getPlayer());

        caveArea = new CaveArea(handler, entityManager);
        SArea = this.handler.getKeyManager().new Area(handler, entityManager);

    }

    @Override
    public void tick() {
        if(GameSetUp.LOADING){
            if(GameSetUp.loadCounter>=90){
                GameSetUp.loadCounter=0;
                GameSetUp.LOADING=false;
                return;
            }
            if (currentArea != null) {
                currentArea.tick();
            }
            GameSetUp.loadCounter++;
        }else {
            if (handler.getKeyManager().keyJustPressed(KeyEvent.VK_ESCAPE)) {
                handler.getGame().pauseState.lastState = State.getState();
                GameSetUp.SWITCHING=true;
                State.setState(handler.getGame().pauseState);
            }else {
                if (currentArea != null) {
                    currentArea.tick();
                }
            }
        }

    }

    @Override
    public void render(Graphics g) {

        if(!GameSetUp.LOADING) {
            Graphics2D g2 = (Graphics2D) g;
            if (currentArea != null) {
                currentArea.render(g);
            }
        }else{
            g.drawImage(Images.Loading,0,0,handler.getWidth(),handler.getHeight(),null);
        }

    }

    public State setArea(BaseArea area){
        currentArea = area;
        return this;
    }

}