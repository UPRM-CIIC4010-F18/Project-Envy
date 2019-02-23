package Game.World;

import Main.Handler;

import java.awt.*;


/**
 * Two types of walls:
 * 	. InvisibleWalls
 * 	. EntranceWalls
 *
 */
public class Walls extends Rectangle {

    protected Handler handler;
    protected int originalX,originalY;
    protected String wallType;
    
    public Walls(Handler handler, int x, int y, int width, int height, String wallType) {
        super(x, y, width, height);
        
        originalX = x;
        originalY = y;
        this.handler = handler;
        this.wallType = wallType;

    }

    public void tick(){
        this.x = handler.getXDisplacement() + originalX;
        this.y = handler.getYDisplacement() + originalY;
    }

    public void render(Graphics2D g2){
        g2.draw(this);
    }

    public String getType() {
    	
    	return wallType;
    }
    
    
    
    
    
}
