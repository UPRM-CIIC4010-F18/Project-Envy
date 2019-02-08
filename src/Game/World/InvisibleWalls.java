package Game.World;

import Main.Handler;

import java.awt.*;

public class InvisibleWalls extends Rectangle {

    Handler handler;
    int originalX,originalY;

    public InvisibleWalls(Handler handler, int x, int y, int width, int height) {
        super(x, y, width, height);
        originalX = x;
        originalY = y;
        this.handler = handler;

    }

    public void tick(){
        this.x = handler.getXDisplacement() + originalX;
        this.y = handler.getYDisplacement() + originalY;
    }

    public void render(Graphics2D g2){
        g2.draw(this);
    }

}
