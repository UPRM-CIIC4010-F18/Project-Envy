package Game.GameStates;

import Main.Handler;
import Resources.Images;

import java.awt.*;

public class MapState extends State{

    int x=-500,y=-500;

    public MapState(Handler handler) {
        super(handler);

    }

    @Override
    public void tick() {

        if (handler.getKeyManager().down){
            y-=5;
        }
        if (handler.getKeyManager().up){
            y+=5;
        }
        if (handler.getKeyManager().right){
            x-=5;
        }
        if (handler.getKeyManager().left){
            x+=5;
        }
    }

    @Override
    public void render(Graphics g) {

        g.drawImage(Images.map,x,y,null);

    }
}
