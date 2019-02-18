package Game.Entities.Dynamics;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.geom.Ellipse2D;

import Game.GameStates.State;
import Main.Handler;

public class Selector {

	Handler handler;
	public int xPos;
	public int xPos2;
	public int yPos;
	public int yPos2;
	public int RBXpos;
	public int QBXpos;
	public int SBXpos;
	public int SBYpos;
	public int QBYpos2;


	public Selector(Handler handler){

		this.handler = handler;
		
		//Pause State positions
		RBXpos = handler.getWidth()/2 - 128 * 2 - 35;	//resume button
		QBXpos = handler.getWidth()/2 + 128 - 10;		//pause quit button
		this.xPos = this.getRBpos();					//pause x position
		this.yPos = handler.getHeight()/2 - 20;			//pause y position
		
		//Menu State position
		SBYpos = handler.getHeight()/2 - 230;			//start button
		QBYpos2 =  handler.getHeight()/2 + 95;			//menu quit button
		this.yPos2 = this.getSBYpos();					//menu y position
		this.xPos2 = handler.getWidth()/2 - 400 - 150;	//menu x position

	}



	public void tick() {

		this.select();

	}

	public void render(Graphics g) {

		Graphics2D g2 = (Graphics2D) g;

		Ellipse2D.Double selector;

		if(State.getState().equals(handler.getGame().menuState)) {

			selector = new Ellipse2D.Double(this.getxPos2(), this.getyPos2(), 100, 100);

		}
		
		else {
			
			selector = new Ellipse2D.Double(this.getxPos(), this.getyPos(), 30, 30);
			
		}

		g2.setColor(Color.WHITE);

		g2.fill(selector);

	}

	public void select() {

		if(State.getState().equals(handler.getGame().pauseState)) {

			if(this.handler.getKeyManager().keyJustPressed(KeyEvent.VK_D)) {

				this.setxPos(this.getQBpos());

			}

			else if(this.handler.getKeyManager().keyJustPressed(KeyEvent.VK_A)) {

				this.setxPos(this.getRBpos());

			}
		}

		else if(State.getState().equals(handler.getGame().menuState)) {

			if(this.handler.getKeyManager().keyJustPressed(KeyEvent.VK_W)) {

				this.setyPos2(this.getSBYpos());

			}

			else if(this.handler.getKeyManager().keyJustPressed(KeyEvent.VK_S)) {

				this.setyPos2(this.getQBYpos2());

			}

		}

	}

	public int getxPos() {
		return xPos;
	}

	public void setxPos(int xPos) {
		this.xPos = xPos;
	}

	public int getyPos() {
		return yPos;
	}

	public void setyPos(int yPos) {
		this.yPos = yPos;
	}

	public int getRBpos() {
		return RBXpos;
	}

	public void setRBpos(int rBpos) {
		RBXpos = rBpos;
	}

	public int getQBpos() {
		return QBXpos;
	}

	public void setQBpos(int qBpos) {
		QBXpos = qBpos;
	}

	public int getSBXpos() {
		return SBXpos;
	}

	public void setSBXpos(int sBXpos) {
		SBXpos = sBXpos;
	}

	public int getSBYpos() {
		return SBYpos;
	}

	public void setSBYpos(int sBYpos) {
		SBYpos = sBYpos;
	}

	public int getQBYpos2() {
		return QBYpos2;
	}

	public void setQBYpos2(int qBYpos2) {
		QBYpos2 = qBYpos2;
	}	
	
	public int getxPos2() {
		return xPos2;
	}



	public void setxPos2(int xPos2) {
		this.xPos2 = xPos2;
	}



	public int getyPos2() {
		return yPos2;
	}



	public void setyPos2(int yPos2) {
		this.yPos2 = yPos2;
	}
}
