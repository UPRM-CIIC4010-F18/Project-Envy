package Game.Entities.Dynamics;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Random;

import Game.GameStates.InWorldState;
import Game.World.InWorldAreas.InWorldWalls;
import Game.World.Walls;
import Main.GameSetUp;
import Main.Handler;

public class BaseHostileEntity extends BaseDynamicEntity implements Fighter{

	private Random rand;
	private boolean chasingPlayer;
	private Rectangle detector;

	private int count;
	private int directionMov;
	double chaseSpeed = 1.5;
	boolean canMove = true;
	public String foundState;
	public String name="enemy";
	public String Area;//None for MapState
    public String type;//class it is ex: "EnemyOne"

	public BaseHostileEntity(Handler handler, int xPosition, int yPosition, String state,String name,String area, BufferedImage[] animFrames) {
		super(handler, xPosition, yPosition,animFrames);
		this.foundState = state;
		chasingPlayer = false;
		Area=area;
		count = 0;
		directionMov = 4;
		this.name = name;
		nextArea = new Rectangle();
		rand = new Random();
		detector = new Rectangle();

	}

	@Override
	public void tick() {
			super.tick();
			if(handler.getArea().equals(this.Area)) {
	            UpdateNextMove();
	            checkCollision();
	
	
	            if (canMove) {
	                count++;
	                if (count >= 100 + rand.nextInt(350)) {
	
	                    directionMov = rand.nextInt(5); // 0 (idle), 1(up), 2(down), 3(left), 4(right)
	
	                    count = 0;
	                }
	
	                PlayerDetector();
	
	                if (!chasingPlayer) {
	                    Move();
	                } else {
	                    Chase();
	                }
	            }
	            canMove = true;
	        }
	}

    private void checkCollision() {
	    if(foundState.equals("MapState")){
            for(Walls w:handler.getWorldManager().getWalls()){
                if(w.intersects(nextArea)) {
                    canMove = false;
                    switch (directionMov) {
                        case 0://idle
                            break;
                        case 1://down
                            this.setYOffset(this.getYOffset() - speed);
                            break;
                        case 2://up
                            this.setYOffset(this.getYOffset() + speed);
                            break;

                        case 3://left
                            this.setXOffset(this.getXOffset() + speed);
                            break;

                        case 4://right
                            this.setXOffset(this.getXOffset() - speed);
                            break;
                    }
                }
            }
        }else if(foundState.equals("InWorldState")){
            for(InWorldWalls w:InWorldState.currentArea.getWalls()){
                if(w.intersects(nextArea)) {
                    canMove = false;
                    switch (directionMov) {
                        case 1://down
                            this.setYOffset(this.getYOffset() - speed);
                            break;
                        case 2://up
                            this.setYOffset(this.getYOffset() + speed);
                            break;

                        case 3://left
                            this.setXOffset(this.getXOffset() + speed);
                            break;

                        case 4://right
                            this.setXOffset(this.getXOffset() - speed);
                            break;
                    }
                }
            }
        }
    }

    private void UpdateNextMove() {
		if(foundState.equals("MapState")) {
			switch (facing) {
				case "Up":
					nextArea = new Rectangle((int) getXOffset() + handler.getXDisplacement(), (int) getYOffset() + handler.getYDisplacement() - 10, getCollision().width, getCollision().height / 2);

					break;
				case "Down":
					nextArea = new Rectangle((int) getXOffset() + handler.getXDisplacement(), (int) getYOffset() + handler.getYDisplacement() + getCollision().height, getCollision().width, 10);

					break;
				case "Left":
					nextArea = new Rectangle((int) getXOffset() + handler.getXDisplacement() - 10, (int) getYOffset() + handler.getYDisplacement(), 10, getCollision().height);

					break;
				case "Right":
					nextArea = new Rectangle((int) getXOffset() + handler.getXDisplacement() + getCollision().width, (int) getYOffset() + handler.getYDisplacement(), 10, getCollision().height);

					break;
			}
		}else if(foundState.equals("InWorldState")){
			switch (facing) {
				case "Up":
					nextArea = new Rectangle((int) getXOffset() + handler.getXInWorldDisplacement(), (int) getYOffset() + handler.getYInWorldDisplacement() - 10, getCollision().width, getCollision().height / 2);

					break;
				case "Down":
					nextArea = new Rectangle((int) getXOffset() + handler.getXInWorldDisplacement(), (int) getYOffset() + handler.getYInWorldDisplacement() + getCollision().height, getCollision().width, 10);

					break;
				case "Left":
					nextArea = new Rectangle((int) getXOffset() + handler.getXInWorldDisplacement() - 10, (int) getYOffset() + handler.getYInWorldDisplacement(), 10, getCollision().height);

					break;
				case "Right":
					nextArea = new Rectangle((int) getXOffset() + handler.getXInWorldDisplacement() + getCollision().width, (int) getYOffset() + handler.getYInWorldDisplacement(), 10, getCollision().height);

					break;
			}
		}
	}

	private void PlayerDetector() {

		detector = this.getCollision();

		detector.setRect(detector.getX() - detector.getWidth() * 4.5, detector.getY() - detector.getHeight() * 4.5,
				detector.getWidth() * 10, detector.getHeight() * 10);

		chasingPlayer = handler.getEntityManager().getPlayer().getCollision().intersects(detector);

		if (!Player.checkInWorld) {
			chaseSpeed = 1.5;
		}
		else {
			chaseSpeed = 5;
		}
	}

	@Override
	public void render(Graphics g) {
		if (GameSetUp.DEBUGMODE) {
			Graphics2D g2 = (Graphics2D) g;
			g2.draw(detector);
			g2.draw(nextArea);
		}

	}

	protected void Chase() {
		if (this.handler.getEntityManager().getPlayer().getXOffset()+(handler.getEntityManager().getPlayer().getCollision().width/2) > this.getXOffset() && canMove) {
			facing = "Right";
			this.setXOffset(this.getXOffset() + chaseSpeed);
		}
		if (this.handler.getEntityManager().getPlayer().getXOffset()+(handler.getEntityManager().getPlayer().getCollision().width/2) < this.getXOffset() && canMove) {
			facing = "Left";
			this.setXOffset(this.getXOffset() - chaseSpeed);
		}

		if (this.handler.getEntityManager().getPlayer().getYOffset()+(handler.getEntityManager().getPlayer().getCollision().height) < this.getYOffset() && canMove) {
			facing = "Up";
			this.setYOffset(this.getYOffset() - chaseSpeed);
		}

		if (this.handler.getEntityManager().getPlayer().getYOffset()+(handler.getEntityManager().getPlayer().getCollision().height) > this.getYOffset() && canMove) {
			facing = "Down";
			this.setYOffset(this.getYOffset() + chaseSpeed);
		}

	}

	private void Move() {

		switch (directionMov) {
			case 0:
				break;
			case 1:
				facing = "Down";
				this.setYOffset(this.getYOffset() + speed);
				break;

			case 2:
				facing = "Up";
				this.setYOffset(this.getYOffset() - speed);
				break;

			case 3:
				facing = "Left";
				this.setXOffset(this.getXOffset() - speed);
				break;

			case 4:
				facing = "Right";
				this.setXOffset(this.getXOffset() + speed);
				break;
		}
	}


    //GETTERS AND SETTERS FOR FIGHT STATS

    double health=100,mana=25,xp=0l,lvl=1,defense=12,str=8,intl=20, mr = 10,cons=20,acc=10,evs=2,initiative=10, maxHealth = 100, maxMana = 100;
    boolean isDead = false;
    String Class = "none",skill = "none";
    String[] buffs = {},debuffs = {};

    @Override
    public double getHealth() {
        return health;
    }

    @Override
    public double getMaxHealth() {
        return maxHealth;
    }
    @Override
    public double getMaxMana() {
        return maxMana;
    }

    @Override
    public void setHealth(double health) {
        this.health=health;
    }
    
    
    public void setMaxHealth(double maxHealth) {
        this.maxHealth=maxHealth;
    }

    @Override
    public double getMana() {
        return mana;
    }

    @Override
    public void setMana(double mana) {
        this.mana=mana;
    }

    @Override
    public double getXp() {
        return xp;
    }

    @Override
    public void setXp(double xp) {
        this.xp=xp;
    }

    @Override
    public double getLvl() {
        return lvl;
    }

    @Override
    public void setLvl(double lvl) {
        this.lvl=lvl;
    }

    @Override
    public double getDefense() {
        return defense;
    }

    @Override
    public void setDefense(double defense) {
        this.defense=defense;
    }

    @Override
    public double getStr() {
        return this.str;
    }

    @Override
    public void setStr(double str) {
        this.str=str;
    }

    @Override
    public double getIntl() {
        return intl;
    }

    @Override
    public void setIntl(double intl) {
        this.intl=intl;
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
        this.cons=cons;
    }

    @Override
    public double getAcc() {
        return this.acc;
    }

    @Override
    public void setAcc(double acc) {
        this.acc=acc;
    }

    @Override
    public double getEvs() {
        return evs;
    }

    @Override
    public void setEvs(double evs) {
        this.evs=evs;
    }

    @Override
    public double getInitiative() {
        return initiative;
    }

    @Override
    public void setInitiative(double initiative) {
        this.initiative=initiative;
    }

    @Override
    public String getclass() {
        return Class;
    }

    @Override
    public void setClass(String aClass) {
        this.Class=aClass;
    }

    @Override
    public String getSkill() {
        return this.skill;
    }

    @Override
    public void setSkill(String skill) {
        this.skill=skill;
    }

    @Override
    public String[] getBuffs() {
        return buffs;
    }

    @Override
    public void setBuffs(String[] buffs) {
        this.buffs=buffs;
    }

    @Override
    public String[] getDebuffs() {
        return debuffs;
    }

    @Override
    public void setDebuffs(String[] debuffs) {
        this.debuffs=debuffs;
    }
    
    public boolean isDead() {
    	return isDead;
    }
    
    public void kill() {
    	isDead = true;
    }
    
    public void lvlAdjust() {
    	if(lvl > 1) {
	    	health += 5 + 7*(lvl-1);
			maxHealth = health;
			mana += 5 + 5*(lvl-1);
			if(mana > 100)
				mana = 100;
			str += 1 + 1 *(int)((lvl - 1)/2);
			acc += 1 + 1 *(int)((lvl - 1)/2);
			defense += 1 + 1 *(int)((lvl - 1)/2);
			intl += 1 + 1 *(int)((lvl - 1)/2);
			mr += 1 + 1 *(int)((lvl - 1)/2);
			cons += 1 + 1 *(int)((lvl - 1)/2);
			if(lvl%4 ==0)
				evs += (lvl -lvl%4)/4;
			xp += 20 *(lvl);
    	}
    }



}
