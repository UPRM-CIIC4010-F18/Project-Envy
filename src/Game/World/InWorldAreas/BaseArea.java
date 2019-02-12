package Game.World.InWorldAreas;

import Main.Handler;

import java.awt.*;

public class BaseArea {

    Handler handler;

	public int oldPlayerXCoord,oldPlayerYCoord;
    public int inWorldX, inWorldY;

    public BaseArea(Handler handler) {
        this.handler = handler;

    }

    public void tick(){

    }

    public void render(Graphics g){


    }

}
