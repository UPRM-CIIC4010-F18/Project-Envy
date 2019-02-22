package Game.Entities.Dynamics;

import Game.Entities.BaseEntity;
import Main.Handler;
import Resources.Animation;
import Resources.Images;

import java.awt.*;
import java.awt.image.BufferedImage;

public class BaseDynamicEntity extends BaseEntity {

	public Animation PEnemyIdle;
	public BufferedImage[] frames;

	int speed = 2;
	protected boolean isMoving;


	//Where the player will stand
	protected Rectangle nextArea;
	public String facing = "Down";

    public BaseDynamicEntity(Handler handler, int xPosition, int yPosition,BufferedImage[] animFrames) {
		super(handler, xPosition, yPosition);
        nextArea = new Rectangle();
		PEnemyIdle = new Animation(120, animFrames);
		frames = animFrames;

	}
	
	// if it moves and stuff then what methods should it have? 

	
	// OTHER FUNCTIONALITIES THAT A DYNAMIC ENTITY SHOULD HAVE?


	public void tick(){
		if(!PEnemyIdle.getCurrentFrame().equals(null)) {
			PEnemyIdle.tick();
		}
	}

	public BufferedImage getIdle(){
		if(!PEnemyIdle.getCurrentFrame().equals(null)) {
			return PEnemyIdle.getCurrentFrame();
		}else{
			return Images.PEnemyIdle[0];
		}
	}

	 public BufferedImage getCurrentAnimationFrame( Animation animDown, Animation animUp, Animation animLeft, Animation animRight, BufferedImage[] front,BufferedImage[] back,BufferedImage[] left,BufferedImage[] right) {
		BufferedImage frame = null;
		 if(isMoving) {
				switch (facing) {
				case "Down":
					frame =  animDown.getCurrentFrame();
					break;
				case "Up":
					frame =  animUp.getCurrentFrame();
					break;
				case "Right":
					frame = animRight.getCurrentFrame();
					break;
				case "Left":
					frame = animLeft.getCurrentFrame();
					break;
				}
			}
			else {
				switch (facing) {
				case "Down":
					frame =  front[0];
					break;
				case "Up":
					frame =  back[0];
					break;
				case "Right":
					frame = right[0];
					break;
				case "Left":
					frame = left[0];
					break;
				}
			}
		 return frame;
	 }

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}
}
