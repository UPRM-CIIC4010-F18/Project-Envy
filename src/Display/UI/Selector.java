package Display.UI;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.geom.Ellipse2D;
import java.util.Random;

import Game.Entities.Dynamics.Fighter;
import Game.GameStates.State;
import Main.Handler;
import Resources.Animation;
import Resources.Images;

public class Selector {

	Handler handler;
	public int xPos;
	public int xPos2;
	public int yPos;
	public int yPos2;
	public int RBXpos;
	public int RBYpos;
	public int QBXpos;
	public int SBXpos;
	public int SBYpos;
	public int QBYpos2;
	public int TBXpos;
	public int TBYpos;
	private Animation animSelector;


	public Selector(Handler handler){

		this.handler = handler;

		//Pause State positions
		RBXpos = handler.getWidth()/2 - 128 * 2 - 35;	//resume button
		RBYpos = handler.getHeight()/2 - 20;
		QBXpos = handler.getWidth()/2 + 128 - 10;		//pause quit button
		TBXpos = handler.getWidth()/2 - 64 - 35;
		TBYpos = handler.getHeight()/2 + 64 + 15;
		this.xPos = this.getRBpos();					//pause x position
		this.yPos = handler.getHeight()/2 - 20;			//pause y position


		//Menu State positions
		SBYpos = handler.getHeight()/2 - 52;			//start button
		QBYpos2 =  handler.getHeight()/2 + 195;			//menu quit button
		this.yPos2 = this.getSBYpos();					//menu y position
		this.xPos2 = handler.getWidth()/2 - 400 - 150;	//menu x position
		animSelector = new Animation(50, Images.IceSkill);


	}



	public void tick() {

		this.select();
		animSelector.tick();

	}

	public void render(Graphics g) {

		Graphics2D g2 = (Graphics2D) g;

		Ellipse2D.Double selector;

		if(State.getState().equals(handler.getGame().menuState)) {
				g.drawImage(Images.tint(animSelector.getCurrentFrame(), new Random().nextInt(200), new Random().nextInt(200), new Random().nextInt(200)), this.getxPos2() - 25, this.getyPos2() - 25, 150, 150, null);
		}

		else {
		    int r = new Random().nextInt(8);
            g.drawImage(Images.tint(animSelector.getCurrentFrame(),r, r, r), this.getxPos()-30, this.getyPos()-25, 80, 80, null);

		}



	}

	public void select() {

		if(State.getState().equals(handler.getGame().pauseState)) {

			if(this.handler.getKeyManager().keyJustPressed(KeyEvent.VK_D)) {

				if(this.getxPos() == this.getRBpos()) {

					this.setxPos(this.getTBXpos());
					this.setyPos(this.getTBYpos());

				}

				else {

					this.setxPos(this.getQBpos());
					this.setyPos(this.getRBYpos());

				}

			}


			else if(this.handler.getKeyManager().keyJustPressed(KeyEvent.VK_A)) {

				if(this.getxPos() == this.getQBpos()) {

					this.setxPos(this.getTBXpos());
					this.setyPos(this.getTBYpos());

				}

				else {

					this.setxPos(this.getRBpos());
					this.setyPos(this.getRBYpos());

				}

			}
		}

		else if(State.getState().equals(handler.getGame().menuState)) {

			if(this.handler.getKeyManager().keyJustPressed(KeyEvent.VK_W)) {

				this.setyPos2(this.getSBYpos());

			}

			else if(this.handler.getKeyManager().keyJustPressed(KeyEvent.VK_S)) {

				this.setyPos2(this.getQBYpos2());

			}

		}

	}

	public int getxPos() {
		return xPos;
	}

	public void setxPos(int xPos) {
		this.xPos = xPos;
	}

	public int getyPos() {
		return yPos;
	}

	public void setyPos(int yPos) {
		this.yPos = yPos;
	}

	public int getRBpos() {
		return RBXpos;
	}

	public void setRBpos(int rBpos) {
		RBXpos = rBpos;
	}

	public int getQBpos() {
		return QBXpos;
	}

	public void setQBpos(int qBpos) {
		QBXpos = qBpos;
	}

	public int getSBXpos() {
		return SBXpos;
	}

	public void setSBXpos(int sBXpos) {
		SBXpos = sBXpos;
	}

	public int getSBYpos() {
		return SBYpos;
	}

	public void setSBYpos(int sBYpos) {
		SBYpos = sBYpos;
	}

	public int getQBYpos2() {
		return QBYpos2;
	}

	public void setQBYpos2(int qBYpos2) {
		QBYpos2 = qBYpos2;
	}

	public int getxPos2() {
		return xPos2;
	}



	public void setxPos2(int xPos2) {
		this.xPos2 = xPos2;
	}



	public int getyPos2() {
		return yPos2;
	}



	public void setyPos2(int yPos2) {
		this.yPos2 = yPos2;
	}



	public int getTBXpos() {
		return TBXpos;
	}



	public int getRBXpos() {
		return RBXpos;
	}



	public void setRBXpos(int rBXpos) {
		RBXpos = rBXpos;
	}



	public int getRBYpos() {
		return RBYpos;
	}



	public void setRBYpos(int rBYpos) {
		RBYpos = rBYpos;
	}



	public void setTBXpos(int tBXpos) {
		TBXpos = tBXpos;
	}



	public int getTBYpos() {
		return TBYpos;
	}



	public void setTBYpos(int tBYpos) {
		TBYpos = tBYpos;
	}

	public class JMPEnemy implements Fighter{

		private Random rand;
		double health, mana, xp,lvl,defense,str,intl,cons,acc,evs,initiative,mr, maxHealth;
		Handler handler;
		public String name;

		public JMPEnemy(String name, Handler handler) {

			this.name = name;
			rand = new Random();
			this.handler = handler;
			
			if(this.handler.getEntityManager().getPlayer().getWeaken()) {
				
				health=100;mana=80;xp=0l;lvl=1;defense=16;str=6;intl=23;cons=15;acc=10;mr= 10;evs=2;initiative=1; maxHealth = 100;		    
				
			}
			
			else{
				
				health=100;mana=100;xp=100;lvl=100;defense=100;str=100;intl=100;cons=100;acc=100;mr=100;evs=100;initiative=100; maxHealth = 100;
			    		
			}


		}

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
	        return 100;
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

		@Override
		public double getMr() {
			return this.mr;
		}

		@Override
		public void setMr(double intl) {
			this.mr = intl;
			
		}

	}

}
