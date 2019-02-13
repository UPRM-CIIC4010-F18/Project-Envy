package Game.Entities.Statics;

import Game.Entities.BaseEntity;
import Main.Handler;

public class BaseStaticEntity extends BaseEntity {
	
	public BaseStaticEntity(Handler handler, int xPosition, int yPosition) {
		super(handler, xPosition, xPosition);
	}

	
	// OTHER FUNCTIONALITIES THAT A STATIC ENTITY SHOULD HAVE?
}
