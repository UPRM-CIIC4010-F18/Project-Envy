package Game.Entities.Dynamics;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import Game.GameStates.FightState;
import Game.GameStates.State;
import Main.Handler;

public class EnemyOne extends BaseHostileEntity implements Fighter{

    Rectangle enemyOne;
    int width, height;

    public EnemyOne(Handler handler, int xPosition, int yPosition, String state,String name,String area) {
        super(handler, yPosition, yPosition,state,name,area);
        width = 30;
        height = 30;
        speed = 1;
        type="EnemyOne";
        this.setXOffset(xPosition);
        this.setYOffset(yPosition);

        this.foundState = state;
        enemyOne = new Rectangle();
    }

    @Override
    public void tick() {

        if(!Player.isinArea)super.tick();

    }

    @Override
    public void render(Graphics g) {
        super.render(g);

        Graphics2D g2 = (Graphics2D) g;


        if(handler.getArea().equals(this.Area)) {
            if (!Player.checkInWorld) {
                enemyOne = new Rectangle((int) (handler.getXDisplacement() + getXOffset()),
                        (int) (handler.getYDisplacement() + getYOffset()), 30, 30);

            } else {
                enemyOne = new Rectangle((int) (handler.getXInWorldDisplacement() + getXOffset()),
                        (int) (handler.getYInWorldDisplacement() + getYOffset()), 70, 70);

            }

            g2.setColor(Color.black);

            g2.fill(enemyOne);

            if (enemyOne.intersects(handler.getEntityManager().getPlayer().getCollision())) {
                handler.getEntityManager().getPlayer().facing = "Left";
                State.setState(new FightState(handler, this, this.Area));
            }
        }


    }

    @Override
    public Rectangle getCollision() {
        return enemyOne;
    }

    //GETTERS AND SETTERS FOR FIGHT STATS

    double health=100,mana=100,xp=0l,lvl=1,defense=10,str=40,intl=30,cons=10,acc=10,evs=5,initiative=1;
    String Class = "none",skill = "none";
    String[] buffs = {},debuffs = {};

    @Override
    public double getMaxHealth() {
        return 100;
    }
    @Override
    public double getMaxMana() {
        return 100;
    }
    @Override
    public double getHealth() {
        return health;
    }

    @Override
    public void setHealth(double health) {
        this.health=health;
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

}
