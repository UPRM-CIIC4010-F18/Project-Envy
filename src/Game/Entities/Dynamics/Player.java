package Game.Entities.Dynamics;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import Game.GameStates.InWorldState;
import Game.GameStates.State;
import Game.World.Walls;
import Game.World.InWorldAreas.CaveArea;
import Game.World.InWorldAreas.InWorldWalls;
import Main.GameSetUp;
import Main.Handler;
import Resources.Animation;
import Resources.Images;

import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

public class Player extends BaseDynamicEntity implements Fighter {

	private Rectangle player;
	private boolean canMove;
	public static boolean checkInWorld;

	public static final int InMapWidthFrontAndBack = 15 * 3, InMapHeightFront = 27 * 3, InMapHeightBack = 23 * 3,
							InMapWidthSideways = 13 * 3, InMapHeightSideways = 22 * 3, 
							InAreaWidthFrontAndBack = 15 * 5, InAreaHeightFront = 27 * 5, InAreaHeightBack = 23 * 5,
							InAreaWidthSideways = 13 * 5, InAreaHeightSideways = 22 * 5;

	private int currentWidth, currentHeight;
	public static boolean isinArea = false;
	private boolean weakenS = false;
	private int switchingCoolDown = 0;

	// Animations
	private Animation animDown, animUp, animLeft, animRight;
	private int animWalkingSpeed = 150;

	public Player(Handler handler, int xPosition, int yPosition) {
		super(handler, yPosition, yPosition, null);

		this.xPosition = xPosition;
		this.yPosition = yPosition;

		currentWidth = InMapWidthFrontAndBack;
		currentHeight = InMapHeightFront;

		animDown = new Animation(animWalkingSpeed, Images.player_front);
		animLeft = new Animation(animWalkingSpeed, Images.player_left);
		animRight = new Animation(animWalkingSpeed, Images.player_right);
		animUp = new Animation(animWalkingSpeed, Images.player_back);

		speed = 15;
		player = new Rectangle();
		checkInWorld = false;

	}

	@Override
	public void tick() {
		
		if (!GameSetUp.LOADING) {
			levelUP();
			
			animDown.tick();
			animUp.tick();
			animRight.tick();
			animLeft.tick();

			UpdateNextMove();
			PlayerInput();

			if (GameSetUp.SWITCHING) {
				switchingCoolDown++;
			}
			if (switchingCoolDown >= 30) {
				GameSetUp.SWITCHING = false;
				switchingCoolDown = 0;

			}

			if (State.getState().equals(handler.getGame().inWorldState)) {
				checkInWorld = true;
			} else {
				checkInWorld = false;
			}

		}
	}


	@Override
	public void render(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;

		g.drawImage(
				getCurrentAnimationFrame(animDown, animUp, animLeft, animRight, Images.player_front, Images.player_back,
						Images.player_left, Images.player_right),
				(int) xPosition, (int) yPosition, currentWidth, currentHeight, null);

		player = new Rectangle((int) xPosition, (int) yPosition+(currentHeight/2)+5, currentWidth-3, currentHeight/2);

		if (GameSetUp.DEBUGMODE) {
			g2.draw(nextArea);
			g2.draw(getCollision());
		}
	}

	private void UpdateNextMove() {
		switch (facing) {
		case "Up":
			nextArea = new Rectangle( player.x, player.y - speed, player.width, speed);
			break;
		case "Down":
			nextArea = new Rectangle(player.x , player.y+player.height-20 , player.width, speed);

			break;
		case "Left":
			nextArea = new Rectangle(player.x - speed, player.y, speed, player.height);

			break;
		case "Right":
			nextArea = new Rectangle(player.x + player.width, player.y, speed, player.height);

			break;
		}
	}

	@Override
	public BufferedImage getIdle() {
		return Images.player_attack;
	}

	private void PlayerInput() {

		canMove = true;

		if (handler.getKeyManager().runbutt) {
			speed = 2;
		} else {
			if(GameSetUp.DEBUGMODE){
				speed = 18;
			}else{
				speed = 8;
			}
		}

		CheckForWalls();

		if (handler.getKeyManager().down & canMove) {
			Move(false, -speed);
			facing = "Down";
		} else if (handler.getKeyManager().up & canMove) {
			Move(false, speed);
			facing = "Up";
		} else if (handler.getKeyManager().right & canMove) {
			Move(true, -speed);
			facing = "Right";
		} else if (handler.getKeyManager().left & canMove) {
			Move(true, speed);
			facing = "Left";
		} else {
			isMoving = false;
		}

	}

	private void PushPlayerBack() {

		canMove = false;
		switch (facing) {
		case "Down":
			Move(false, 1);
			break;
		case "Up":
			Move(false, -1);
			break;
		case "Right":
			Move(true, 1);
			break;
		case "Left":
			Move(true, -1);
			break;
		}
	}

	private void CheckForWalls() {

		if (!checkInWorld) {
			for (Walls w : handler.getWorldManager().getWalls()) {

				if (nextArea.intersects(w)) {

					if (w.getType().equals("Wall")) {
						PushPlayerBack();
					}

					else if (w.getType().startsWith("Door")) {
						canMove = true;

						if (w.getType().equals("Door Cave")) {
							checkInWorld = true;
							InWorldState.caveArea.oldPlayerXCoord = (int) (handler.getXDisplacement());
							InWorldState.caveArea.oldPlayerYCoord = (int) (handler.getYDisplacement());
							CaveArea.isInCave = true;
							setWidthAndHeight(InAreaWidthFrontAndBack, InAreaHeightFront);
							handler.setXInWorldDisplacement(CaveArea.playerXSpawn);
							handler.setYInWorldDisplacement(CaveArea.playerYSpawn);
							GameSetUp.LOADING = true;
							handler.setArea("Cave");
							
	                        handler.getGame().getMusicHandler().set_changeMusic("res/music/Cave.mp3");
	                        handler.getGame().getMusicHandler().play();
	                        handler.getGame().getMusicHandler().setVolume(0.4);
							
							State.setState(handler.getGame().inWorldState.setArea(InWorldState.caveArea));
						}

						if (w.getType().equals("Door S")) {
							checkInWorld = true;
							InWorldState.SArea.oldPlayerXCoord = (int) (handler.getXDisplacement());
							InWorldState.SArea.oldPlayerYCoord = (int) (handler.getYDisplacement());
							this.isinArea = true;
							setWidthAndHeight(InMapWidthFrontAndBack, InMapHeightFront);
							GameSetUp.LOADING = true;
							handler.setArea("S");
							State.setState(handler.getGame().inWorldState.setArea(InWorldState.SArea));
						}
					}

				}
			}
		} else

		{
			if (CaveArea.isInCave) {
				for (InWorldWalls iw : CaveArea.caveWalls) {
					if (nextArea.intersects(iw)) {
						if (iw.getType().equals("Wall"))
							PushPlayerBack();
						else {

							if (iw.getType().equals("Start Exit")) {

								handler.setXDisplacement(handler.getXDisplacement() - 450); // Sets the player x/y
																							// outside the
								handler.setYDisplacement(handler.getYDisplacement() + 400); // Cave

							} else if (iw.getType().equals("End Exit")) {

								handler.setXDisplacement(InWorldState.caveArea.oldPlayerXCoord);// Sets the player x/y
								handler.setYDisplacement(InWorldState.caveArea.oldPlayerYCoord);// outside theCave
							}
	
							GameSetUp.LOADING = true;
							handler.setArea("None");
							
	                    	handler.getGame().getMusicHandler().set_changeMusic("res/music/OverWorld.mp3");
	                        handler.getGame().getMusicHandler().play();
	                        handler.getGame().getMusicHandler().setVolume(0.2);
							
							State.setState(handler.getGame().mapState);
							CaveArea.isInCave = false;
							checkInWorld = false;
							System.out.println("Left Cave");
							setWidthAndHeight(InMapWidthFrontAndBack, InMapHeightFront);
						}
					}
				}
			}

			else if (Player.isinArea) {

				for (InWorldWalls iw : InWorldState.SArea.getWalls()) {

					if (nextArea.intersects(iw)) {
						if (iw.getType().equals("Wall"))
							PushPlayerBack();

					}
				}
			}
		}
	}

	/**
	 *
	 * @param XorY  where true is X and false is Y
	 * @param speed
	 */
	private void Move(boolean XorY, int speed) {

		isMoving = true;

		if (!checkInWorld) {
			if (XorY) {
				setWidthAndHeight(InMapWidthSideways, InMapHeightSideways);
				handler.setXDisplacement(handler.getXDisplacement() + speed);
			} else {
				if (facing.equals("Up")) {
					setWidthAndHeight(InMapWidthFrontAndBack, InMapHeightBack);
				} else {
					setWidthAndHeight(InMapWidthFrontAndBack, InMapHeightFront);
				}
				handler.setYDisplacement(handler.getYDisplacement() + speed);
			}
		} else {
			if (XorY) {
				setWidthAndHeight(InAreaWidthSideways, InAreaHeightSideways);
				handler.setXInWorldDisplacement((handler.getXInWorldDisplacement() + speed));
			} else {
				if (facing.equals("Up")) {
					setWidthAndHeight(InAreaWidthFrontAndBack, InAreaHeightBack);
				} else {
					setWidthAndHeight(InAreaWidthFrontAndBack, InAreaHeightFront);
				}

				handler.setYInWorldDisplacement(handler.getYInWorldDisplacement() + speed);
			}

		}

	}

	@Override
	public Rectangle getCollision() {
		return player;
	}

	/**
	 * !!!!!!!!!TO REDESIGN OR DELETE!!!!!!!
	 *
	 *
	 * Called when the player has collided with another static entity. Used to push
	 * the player back from passing through a static entity.
	 *
	 * @param collidedXPos the xPosition the static entity is located at.
	 */
	public void WallBoundary(double collidedXPos) {

		int playerXPos = Math.abs(handler.getXDisplacement());

		if (playerXPos < collidedXPos / 2) {
			handler.setXDisplacement(handler.getXDisplacement() + 2);
		} else if (playerXPos > collidedXPos / 2) {
			handler.setXDisplacement(handler.getXDisplacement() - 2);
		}
	}

	/*
	 * Although the TRUE Player position is in the middle of the screen, these two
	 * methods give us the value as if the player was part of the world.
	 */
	@Override
	public double getXOffset() {

		if (!checkInWorld)
			return -this.handler.getXDisplacement() + xPosition;
		else
			return -this.handler.getXInWorldDisplacement() + xPosition;
	}

	@Override
	public double getYOffset() {

		if (!checkInWorld)
			return -this.handler.getYDisplacement() + yPosition;
		else
			return -this.handler.getYInWorldDisplacement() + yPosition;
	}

	public void setWidthAndHeight(int newWidth, int newHeight) {
		this.currentWidth = newWidth;
		this.currentHeight = newHeight;
	}

	// GETTERS AND SETTERS FOR FIGHT STATS

	double health = 200, mana = 100, xp = 0, lvl = 1, defense = 16, str = 10, intl = 25, mr = 12, cons = 20, acc = 12, evs = 4,
			initiative = 13, maxHealth = 200, maxMana = 100, lvlUpExp = 200;

	String Class = "none", skill = "none";
	String[] buffs = {}, debuffs = {};

	@Override
	public double getMaxHealth() {
		return maxHealth;
	}

	@Override
	public double getMaxMana() {
		return maxMana;
	}

	@Override
	public double getHealth() {
		return health;
	}

	@Override
	public void setHealth(double health) {
		this.health = health;
	}

	@Override
	public double getMana() {
		return mana;
	}

	@Override
	public void setMana(double mana) {
		this.mana = mana;
	}

	@Override
	public double getXp() {
		return xp;
	}

	@Override
	public void setXp(double xp) {
		this.xp = xp;
	}

	@Override
	public double getLvl() {
		return lvl;
	}

	@Override
	public void setLvl(double lvl) {
		this.lvl = lvl;
	}

	@Override
	public double getDefense() {
		return defense;
	}

	@Override
	public void setDefense(double defense) {
		this.defense = defense;
	}

	@Override
	public double getStr() {
		return this.str;
	}

	@Override
	public void setStr(double str) {
		this.str = str;
	}

	@Override
	public double getIntl() {
		return intl;
	}

	@Override
	public void setIntl(double intl) {
		this.intl = intl;
	}
	
	@Override
	public double getMr() {
		return mr;
	}
	
	@Override
	public void setMr(double mr) {
		this.mr = mr;	
	}

	@Override
	public double getCons() {
		return cons;
	}

	@Override
	public void setCons(double cons) {
		this.cons = cons;
	}

	@Override
	public double getAcc() {
		return this.acc;
	}

	@Override
	public void setAcc(double acc) {
		this.acc = acc;
	}

	@Override
	public double getEvs() {
		return evs;
	}

	@Override
	public void setEvs(double evs) {
		this.evs = evs;
	}

	@Override
	public double getInitiative() {
		return initiative;
	}

	@Override
	public void setInitiative(double initiative) {
		this.initiative = initiative;
	}

	@Override
	public String getclass() {
		return Class;
	}

	@Override
	public void setClass(String aClass) {
		this.Class = aClass;
	}

	@Override
	public String getSkill() {
		return this.skill;
	}

	@Override
	public void setSkill(String skill) {
		this.skill = skill;
	}

	@Override
	public String[] getBuffs() {
		return buffs;
	}

	@Override
	public void setBuffs(String[] buffs) {
		this.buffs = buffs;
	}

	@Override
	public String[] getDebuffs() {
		return debuffs;
	}

	@Override
	public void setDebuffs(String[] debuffs) {
		this.debuffs = debuffs;
	}
	public void setWeaken(boolean arg) {
		this.weakenS = arg;
	}

	public boolean getWeaken() {
		
		return this.weakenS;
		
	}
	
	public void addXp(double xp) {
		this.xp += xp;
	}
	
	public double getLvlUpXp() {
		return lvlUpExp;
	}
	
	private void levelUP() {
		if(xp >= lvlUpExp) {
			xp-= lvlUpExp;
			lvlUpExp *= 1.2;
			maxHealth += 15 + 5*(lvl-1);
			maxMana += 5 + 5*(lvl-1);
			str += 1 + 1 *(int)((lvl - 1)/2);
			acc += 1 + 1 *(int)((lvl - 1)/2);
			defense += 1 + 1 *(int)((lvl - 1)/2);
			intl += 1 + 1 *(int)((lvl - 1)/2);
			mr += 1 + 1 *(int)((lvl - 1)/2);
			cons += 1 + 1 *(int)((lvl - 1)/2);
			if(lvl%4 ==0)
				evs++;
			
			lvl++;
			
			
		}
		
	}

}
