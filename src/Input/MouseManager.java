package Input;

import Display.UI.UIManager;
import Game.Entities.Dynamics.Player;
import Game.Entities.Statics.BaseStaticEntity;
import Game.GameStates.InWorldState;
import Game.GameStates.State;
import Main.Handler;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Ellipse2D;
import java.util.Random;

/**
 * Created by AlexVR on 7/1/2018.
 */
public class MouseManager implements MouseListener,MouseMotionListener{

    private boolean leftPressed,rightPressed;
    private int mouseX,mouseY;
    private UIManager uimanager;

    public MouseManager(){

    }

    public void setUimanager(UIManager uimanager){
        this.uimanager=uimanager;
    }

    public boolean isLeftPressed() {
        return leftPressed;
    }

    public boolean isRightPressed() {
        return rightPressed;
    }

    public int getMouseX() {
        return mouseX;
    }

    public int getMouseY() {
        return mouseY;
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        if(e.getButton()==MouseEvent.BUTTON1){
            leftPressed=true;
        }
        else if(e.getButton()==MouseEvent.BUTTON3){
            rightPressed=true;
        }
        if(uimanager !=null){
            uimanager.onMousePressed(e);
        }

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if(e.getButton()==MouseEvent.BUTTON1){
            leftPressed=false;
        }
        else if(e.getButton()==MouseEvent.BUTTON3){
            rightPressed=false;
        }
        if(uimanager !=null){
            uimanager.onMouseRelease(e);
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        mouseX = e.getX();
        mouseY = e.getY();
        if(uimanager !=null){
            uimanager.onMouseMove(e);
        }

    }
    
	public class Circle extends BaseStaticEntity{

		private int cWidth = 20;
		private int cHeight = 20;
		int[] xPos = {5627, 47, 4331, 4097};
		int[] yPos = {380, 5058, 5058, 2018};
		private Handler handler;
		private Random random = new Random();
		private int spot;

		public Circle(int x, int y, Handler handler) {
			super(handler, x, y);
			
			spot = random.nextInt(4);
			this.setXOffset(xPos[spot]);
			this.setYOffset(yPos[spot]);
			this.handler = handler;

		}

		public void tick() {

			if(this.handler.getEntityManager().getPlayer().getCollision().intersects(this.getCollision())) {

				InWorldState.SArea.oldPlayerXCoord = (int) (handler.getXDisplacement());
				InWorldState.SArea.oldPlayerYCoord = (int) (handler.getYDisplacement());

				handler.getEntityManager().getPlayer().setWidthAndHeight(25, 25); 
				Player.isinArea = true;
				State.setState(handler.getGame().inWorldState.setArea(InWorldState.SArea));



			}

		}

		public void render(Graphics g) {

			Graphics2D g2 = (Graphics2D) g;

			Ellipse2D.Double circle = new Ellipse2D.Double((int) (this.handler.getXDisplacement() + this.xPosition), (int) (this.handler.getYDisplacement() + this.yPosition), this.getcWidth(), this.getcHeight());

			g2.setColor(new Color(random.nextInt(255), random.nextInt(255), random.nextInt(255)));   		
			g2.draw(circle);

		}

		public int getcWidth() {
			return cWidth;
		}

		public void setcWidth(int cWidth) {
			this.cWidth = cWidth;
		}

		public int getcHeight() {
			return cHeight;
		}

		public void setcHeight(int cHeight) {
			this.cHeight = cHeight;
		}

	}
}
