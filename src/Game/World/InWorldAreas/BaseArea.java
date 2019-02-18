package Game.World.InWorldAreas;

import Main.Handler;

import java.awt.*;
import java.util.ArrayList;

import Game.Entities.EntityManager;

public class BaseArea {

    Handler handler;

    public EntityManager entityManager;

    public int oldPlayerXCoord,oldPlayerYCoord;
    public int inWorldX, inWorldY;
    public String name;

    public BaseArea(Handler handler, EntityManager entityManager) {
        this.handler = handler;
    }

    public void tick(){

    }

    public void render(Graphics g){


    }
    
    public ArrayList<InWorldWalls> getWalls() {
    	return null;
    }

}
