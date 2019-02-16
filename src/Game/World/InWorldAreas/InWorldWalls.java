package Game.World.InWorldAreas;

import Game.World.Walls;
import Main.Handler;

public class InWorldWalls extends Walls {
	
	
	public InWorldWalls(Handler handler, int x, int y, int width, int height, String wallType) {
		super(handler, x, y, width, height, wallType);

		this.originalX = x;
		this.originalY = y;
		this.handler = handler;
		this.wallType = wallType;
	}

	@Override
	public void tick() {
		this.x = handler.getXInWorldDisplacement() + originalX;
		this.y = handler.getYInWorldDisplacement() + originalY;
	}
}
