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

	int xDisplacement, yDisplacement;

	private GameSetUp game;

	public Handler() {

//		height = (DEFAULTHEIGHT / 2) + (DEFAULTHEIGHT / 4);
		height = DEFAULTHEIGHT;
//		width = (DEFAULTWIDTH / 3);
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

	public void setXDisplacement(int xDis) {
		this.xDisplacement = xDis;
	}

	public void setYDisplacement(int yDis) {
		this.yDisplacement = yDis;
	}

	public int getXDisplacement() {
		return this.xDisplacement;
	}

	public int getYDisplacement() {
		return this.yDisplacement;
	}

}
