package Game.GameStates;

import Main.Handler;
import Resources.Images;

import java.awt.*;
import java.awt.event.KeyEvent;

public class InWorldState extends State{


    public InWorldState(Handler handler) {
        super(handler);

    }

    @Override
    public void tick() {

        ///TEMP CODE TO EXIT STATE///
        if (handler.getKeyManager().keyJustPressed(KeyEvent.VK_ESCAPE)) {
            PauseState.lastState = State.getState();
            State.setState(handler.getGame().pauseState);
        }
        /////////////////////////////
    	
    }

    @Override
    public void render(Graphics g) {

    	Graphics2D g2 = (Graphics2D)g;
    	
    	g2.drawImage(Images.InWorldOne, 0,0, handler.getWidth(), handler.getHeight(), null);
    	
    	
    }
}
