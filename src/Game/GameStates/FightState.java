package Game.GameStates;

import Game.Entities.Dynamics.BaseDynamicEntity;
import Main.Handler;

import java.awt.*;

public class FightState extends State{

    public FightState(Handler handler, BaseDynamicEntity player,BaseDynamicEntity enemy) {
        super(handler);

    }

    public FightState(Handler handler, BaseDynamicEntity player,BaseDynamicEntity[] enemie) {
        super(handler);

    }

    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics g) {

    }
}
