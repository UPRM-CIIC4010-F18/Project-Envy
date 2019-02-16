package Game.World.InWorldAreas;

import Main.Handler;

import java.awt.*;
import java.util.ArrayList;

import Game.Entities.EntityManager;

public class BaseArea {

    Handler handler;

	public int oldPlayerXCoord,oldPlayerYCoord;
    public int inWorldX, inWorldY;

	private EntityManager entityManager;

    public BaseArea(Handler handler, EntityManager entityManager) {
        this.handler = handler;
        this.entityManager = entityManager;
        
        
    }

    public void tick(){
    	this.entityManager.tick();
    }

    public void render(Graphics g){


    }
    
    public ArrayList<InWorldWalls> getWalls() {
    	return null;
    }

}
