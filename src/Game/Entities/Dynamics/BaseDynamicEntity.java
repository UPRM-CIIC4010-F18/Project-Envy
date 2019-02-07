package Game.Entities.Dynamics;

import Game.Entities.BaseEntity;
import Main.Handler;

public class BaseDynamicEntity extends BaseEntity {


	int speed = 2;
	
	public BaseDynamicEntity(Handler handler, int xPosition, int yPosition) {
		super(handler, xPosition, yPosition);
	}
	
	// if it moves and stuff then what methods should it have? 

	
	
	// OTHER FUNCTIONALITIES THAT A DYNAMIC ENTITY SHOULD HAVE?


	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}
}
