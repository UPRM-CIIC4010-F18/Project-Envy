package Game.Entities.Dynamics;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.Random;

import Game.GameStates.InWorldState;
import Game.World.InWorldAreas.InWorldWalls;
import Game.World.Walls;
import Main.GameSetUp;
import Main.Handler;

public class BaseHostileEntity extends BaseDynamicEntity {

	private Random rand;
	private boolean chasingPlayer;
	private Rectangle detector;

	private int count;
	private int directionMov;
	double chaseSpeed = 1.5;
	boolean canMove = true;
	public String foundState;
	public String name="enemy";
	public String Area;//None for MapState

	public BaseHostileEntity(Handler handler, int xPosition, int yPosition, String state,String name,String area) {
		super(handler, xPosition, yPosition);
		this.foundState = state;
		chasingPlayer = false;
		Area=area;
		count = 0;
		directionMov = 4;
		this.name = name;
		nextArea = new Rectangle();
		rand = new Random();
		detector = new Rectangle();

	}

	@Override
	public void tick() {
		super.tick();
		if(handler.getArea().equals(this.Area)) {
            UpdateNextMove();
            checkCollision();


            if (canMove) {
                count++;
                if (count >= 100 + rand.nextInt(350)) {

                    directionMov = rand.nextInt(5); // 0 (idle), 1(up), 2(down), 3(left), 4(right)

                    count = 0;
                }

                PlayerDetector();

                if (!chasingPlayer) {
                    Move();
                } else {
                    Chase();
                }
            }
            canMove = true;
        }
	}

    private void checkCollision() {
	    if(foundState.equals("MapState")){
            for(Walls w:handler.getWorldManager().getWalls()){
                if(w.intersects(nextArea)) {
                    canMove = false;
                    switch (directionMov) {
                        case 0://idle
                            break;
                        case 1://down
                            this.setYOffset(this.getYOffset() - speed);
                            break;
                        case 2://up
                            this.setYOffset(this.getYOffset() + speed);
                            break;

                        case 3://left
                            this.setXOffset(this.getXOffset() + speed);
                            break;

                        case 4://right
                            this.setXOffset(this.getXOffset() - speed);
                            break;
                    }
                }
            }
        }else if(foundState.equals("InWorldState")){
            for(InWorldWalls w:InWorldState.currentArea.getWalls()){
                if(w.intersects(nextArea)) {
                    canMove = false;
                    switch (directionMov) {
                        case 1://down
                            this.setYOffset(this.getYOffset() - speed);
                            break;
                        case 2://up
                            this.setYOffset(this.getYOffset() + speed);
                            break;

                        case 3://left
                            this.setXOffset(this.getXOffset() + speed);
                            break;

                        case 4://right
                            this.setXOffset(this.getXOffset() - speed);
                            break;
                    }
                }
            }
        }
    }

    private void UpdateNextMove() {
		if(foundState.equals("MapState")) {
			switch (facing) {
				case "Up":
					nextArea = new Rectangle((int) getXOffset() + handler.getXDisplacement(), (int) getYOffset() + handler.getYDisplacement() - 10, getCollision().width, getCollision().height / 2);

					break;
				case "Down":
					nextArea = new Rectangle((int) getXOffset() + handler.getXDisplacement(), (int) getYOffset() + handler.getYDisplacement() + getCollision().height, getCollision().width, 10);

					break;
				case "Left":
					nextArea = new Rectangle((int) getXOffset() + handler.getXDisplacement() - 10, (int) getYOffset() + handler.getYDisplacement(), 10, getCollision().height);

					break;
				case "Right":
					nextArea = new Rectangle((int) getXOffset() + handler.getXDisplacement() + getCollision().width, (int) getYOffset() + handler.getYDisplacement(), 10, getCollision().height);

					break;
			}
		}else if(foundState.equals("InWorldState")){
			switch (facing) {
				case "Up":
					nextArea = new Rectangle((int) getXOffset() + handler.getXInWorldDisplacement(), (int) getYOffset() + handler.getYInWorldDisplacement() - 10, getCollision().width, getCollision().height / 2);

					break;
				case "Down":
					nextArea = new Rectangle((int) getXOffset() + handler.getXInWorldDisplacement(), (int) getYOffset() + handler.getYInWorldDisplacement() + getCollision().height, getCollision().width, 10);

					break;
				case "Left":
					nextArea = new Rectangle((int) getXOffset() + handler.getXInWorldDisplacement() - 10, (int) getYOffset() + handler.getYInWorldDisplacement(), 10, getCollision().height);

					break;
				case "Right":
					nextArea = new Rectangle((int) getXOffset() + handler.getXInWorldDisplacement() + getCollision().width, (int) getYOffset() + handler.getYInWorldDisplacement(), 10, getCollision().height);

					break;
			}
		}
	}

	private void PlayerDetector() {

		detector = this.getCollision();

		detector.setRect(detector.getX() - detector.getWidth() * 4.5, detector.getY() - detector.getHeight() * 4.5,
				detector.getWidth() * 10, detector.getHeight() * 10);

		chasingPlayer = handler.getEntityManager().getPlayer().getCollision().intersects(detector);

		if (!Player.checkInWorld) {
			chaseSpeed = 1.5;
		}
		else {
			chaseSpeed = 5;
		}
	}

	@Override
	public void render(Graphics g) {
		if (GameSetUp.DEBUGMODE) {
			Graphics2D g2 = (Graphics2D) g;
			g2.draw(detector);
			g2.draw(nextArea);
		}

	}

	private void Chase() {
		if (this.handler.getEntityManager().getPlayer().getXOffset() > this.getXOffset() && canMove) {
			facing = "Right";
			this.setXOffset(this.getXOffset() + chaseSpeed);
		}
		if (this.handler.getEntityManager().getPlayer().getXOffset() < this.getXOffset() && canMove) {
			facing = "Left";
			this.setXOffset(this.getXOffset() - chaseSpeed);
		}

		if (this.handler.getEntityManager().getPlayer().getYOffset() < this.getYOffset() && canMove) {
			facing = "Up";
			this.setYOffset(this.getYOffset() - chaseSpeed);
		}

		if (this.handler.getEntityManager().getPlayer().getYOffset() > this.getYOffset() && canMove) {
			facing = "Down";
			this.setYOffset(this.getYOffset() + chaseSpeed);
		}

	}

	private void Move() {

		switch (directionMov) {
			case 0:
				break;
			case 1:
				facing = "Down";
				this.setYOffset(this.getYOffset() + speed);
				break;

			case 2:
				facing = "Up";
				this.setYOffset(this.getYOffset() - speed);
				break;

			case 3:
				facing = "Left";
				this.setXOffset(this.getXOffset() - speed);
				break;

			case 4:
				facing = "Right";
				this.setXOffset(this.getXOffset() + speed);
				break;
		}
	}

}
