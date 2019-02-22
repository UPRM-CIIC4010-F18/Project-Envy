package Game.Entities.Dynamics;

public interface Fighter {
    //copy these into the fighter
    double health=100,mana=100,xp=0l,lvl=1,defense=12,str=8,intl=20, mr = 10,cons=20,acc=10,evs=1,initiative=1,maxHealth=100;
    String Class = "none",skill = "none";
    String[] buffs = {},debuffs = {};
    //

    double getMaxHealth();
    double getMaxMana();

    double getHealth();

    void setHealth(double health);

    double getMana();
    void setMana(double mana);
    double getXp();

    void setXp(double xp);

    double getLvl();

    void setLvl(double lvl) ;

    double getDefense();

    void setDefense(double defense);

    double getStr();

    void setStr(double str);

    double getIntl();

    void setIntl(double intl) ;
    
    double getMr();

    void setMr(double intl) ;

    double getCons();

    void setCons(double cons);

    double getAcc();

    void setAcc(double acc);

    double getEvs();

    void setEvs(double evs);

    double getInitiative() ;

    void setInitiative(double initiative);

    String getclass() ;

    void setClass(String aClass);
    String getSkill();

    void setSkill(String skill);

    String[] getBuffs();

    void setBuffs(String[] buffs);

    String[] getDebuffs();

    void setDebuffs(String[] debuffs);
}
