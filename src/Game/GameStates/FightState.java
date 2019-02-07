package Game.GameStates;

import Game.Entities.Dynamics.BaseDynamicEntity;
import Game.Entities.Dynamics.BaseHostileEntity;
import Main.Handler;

import java.awt.*;

public class FightState extends State{

    public FightState(Handler handler, BaseDynamicEntity player ,BaseHostileEntity enemy) {
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
    	
    	Graphics2D g2 = (Graphics2D)g;
    	
    	g2.setFont(new Font("MONOSPACED", 3, 80));
    	
    	g2.setColor(Color.red);
    	g2.drawString("FIGHT!", handler.getWidth()/2 - 110, handler.getHeight()/2);
    	
    	
    }
}
