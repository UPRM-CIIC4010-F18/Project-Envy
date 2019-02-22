package Main;

import Game.Entities.Dynamics.BaseHostileEntity;
import Game.Entities.Dynamics.EnemyOne;
import Input.KeyManager;
import Input.MouseManager;

import java.awt.*;
import java.awt.image.BufferedImage;

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

/*      If your game display seems zoomed out, usually the case on 4k screens, 
 * 		remove the comments on the following two lines of code and comment out the height and width declaration in this method*/
//		height =( DEFAULTHEIGHT/1080)*1080;
//		width = (DEFAULTWIDTH/1920)*1920;
		
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


	public BaseHostileEntity newEnemy(BufferedImage[] images,Handler handler, int xPosition, int yPosition, String state, String name, String area,
									  String typeOfEnemy, double hp, double mana, double xp, double lvl, double str, double def,
									  double intl, double cons, double acc, double evs, double initiative,
									  String Class, String Skill, String[] buffs, String[] debuffs){
		if(typeOfEnemy.equals("EnemyOne")) {
			EnemyOne n = new EnemyOne(handler, xPosition, yPosition, state, name, area,images);
			n.setAcc(acc);
			n.setBuffs(buffs);
			n.setClass(Class);
			n.setCons(cons);
			n.setDebuffs(debuffs);
			n.setDefense(def);
			n.setEvs(evs);
			n.setHealth(hp);
            n.setMaxHealth(hp);
			n.setInitiative(initiative);
			n.setIntl(intl);
			n.setLvl(lvl);
			n.setMana(mana);
			n.setSkill(Skill);
			n.setStr(str);
			n.setXp(xp);
			return n;
		}else{//default
			EnemyOne n = new EnemyOne(handler, xPosition, yPosition, state, name, area,images);
			n.setAcc(acc);
			n.setBuffs(buffs);
			n.setClass(Class);
			n.setCons(cons);
			n.setDebuffs(debuffs);
			n.setDefense(def);
			n.setEvs(evs);
			n.setHealth(hp);
            n.setMaxHealth(hp);
			n.setInitiative(initiative);
			n.setIntl(intl);
			n.setLvl(lvl);
			n.setMana(mana);
			n.setSkill(Skill);
			n.setStr(str);
			n.setXp(xp);
			return n;
		}


	}
}
