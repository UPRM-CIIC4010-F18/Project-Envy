package Game.GameStates;

import Game.World.InWorldAreas.BaseArea;
import Game.World.InWorldAreas.CaveArea;
import Main.Handler;
import Resources.Images;

import java.awt.*;
import java.awt.event.KeyEvent;

public class InWorldState extends State{


    BaseArea currentArea;
    public static BaseArea caveArea;

    public InWorldState(Handler handler) {
        super(handler);

        caveArea = new CaveArea(handler);
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
        }
    	
    }

    @Override
    public void render(Graphics g) {

    	Graphics2D g2 = (Graphics2D)g;
    	g2.drawImage(Images.InWorldOne, 0,0, handler.getWidth(), handler.getHeight(), null);
        if(currentArea!=null) {
            currentArea.render(g);
        }
    	
    }

    public State setArea(BaseArea area){
        currentArea = area;
        return this;
    }
}
