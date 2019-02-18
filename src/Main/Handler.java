package Main;

import Input.KeyManager;
import Input.MouseManager;

import java.awt.*;

import Game.Entities.EntityManager;
import Game.World.WorldManager;

/**
 * Created by AlexVR on 7/1/2018.
 */

public class Handler {

	GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
	int DEFAULTWIDTH = gd.getDisplayMode().getWidth();
	int DEFAULTHEIGHT = gd.getDisplayMode().getHeight();

	int width, height;

	private EntityManager entityManager;
	private WorldManager worldManager;

	int xOverWorldDisplacement, yOverWorldDisplacement,
		xInWorldDisplacement, yInWorldDisplacement;

	private String Area="None";

	private GameSetUp game;

	public Handler() {

//		height = (DEFAULTHEIGHT / 2) + (DEFAULTHEIGHT / 4);
//		width = (DEFAULTWIDTH / 3);

		height = DEFAULTHEIGHT;
		width = DEFAULTWIDTH;

	}


	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public GameSetUp getGame() {
		return game;
	}

	public void setGame(GameSetUp game) {
		this.game = game;
	}

	public KeyManager getKeyManager() {
		return game.getKeyManager();
	}

	public MouseManager getMouseManager() {
		return game.getMouseManager();
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public void setWorldManager(WorldManager worldManager) {
		this.worldManager = worldManager;
	}

	public EntityManager getEntityManager() {
		return entityManager;
	}

	public WorldManager getWorldManager() {
		return worldManager;
	}
	
	// For OverWorld Map Movement
	public void setXDisplacement(int xDis) {
		this.xOverWorldDisplacement = xDis;
	}

	public void setYDisplacement(int yDis) {
		this.yOverWorldDisplacement = yDis;
	}

	public int getXDisplacement() {
		return this.xOverWorldDisplacement;
	}

	public int getYDisplacement() {
		return this.yOverWorldDisplacement;
	}
	
	// For InWorld Map Movement
	public int getXInWorldDisplacement() {
		return xInWorldDisplacement;
	}
	//
	public void setXInWorldDisplacement(int xInWorldDisplacement) {
		this.xInWorldDisplacement = xInWorldDisplacement;
	}
	//
	public int getYInWorldDisplacement() {
		return yInWorldDisplacement;
	}
	//
	public void setYInWorldDisplacement(int yInWorldDisplacement) {
		this.yInWorldDisplacement = yInWorldDisplacement;
	}

	public String getArea() {
		return Area;
	}

	public void setArea(String area) {
		Area = area;
	}
}
