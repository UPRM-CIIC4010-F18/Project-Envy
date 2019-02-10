package Game.World.InWorldAreas;

import Main.Handler;
import Resources.Images;

import java.awt.*;

public class CaveArea extends BaseArea {

    public CaveArea(Handler handler) {
        super(handler);
    }

    public void tick(){
        super.tick();
    }

    @Override
    public void render(Graphics g) {
        super.render(g);
        g.drawImage(Images.tree,0,0,null);
    }
}
