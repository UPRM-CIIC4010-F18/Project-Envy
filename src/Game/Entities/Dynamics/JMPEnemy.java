package Game.Entities.Dynamics;

import java.util.Random;

import Main.Handler;


public class JMPEnemy implements Fighter{

	private Random rand;
	double health, mana, xp,lvl,defense,str,intl,cons,acc,evs,initiative, maxHealth;
	Handler handler;
	public String name;

	public JMPEnemy(String name, Handler handler) {

		this.name = name;
		rand = new Random();
		this.handler = handler;
		
		if(this.handler.getEntityManager().getPlayer().getWeaken()) {
			
			health=100;mana=80;xp=0l;lvl=1;defense=16;str=6;intl=23;cons=15;acc=10;evs=2;initiative=1; maxHealth = 100;		    
			
		}
		
		else{
			
			health=100;mana=100;xp=100;lvl=100;defense=100;str=100;intl=100;cons=100;acc=100;evs=100;initiative=100; maxHealth = 100;
		    		
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

}
