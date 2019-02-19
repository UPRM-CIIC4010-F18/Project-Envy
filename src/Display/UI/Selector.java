package Display.UI;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.geom.Ellipse2D;
import java.util.Random;

import Game.GameStates.State;
import Main.Handler;
import Resources.Animation;
import Resources.Images;

public class Selector {

	Handler handler;
	public int xPos;
	public int xPos2;
	public int yPos;
	public int yPos2;
	public int RBXpos;
	public int RBYpos;
	public int QBXpos;
	public int SBXpos;
	public int SBYpos;
	public int QBYpos2;
	public int TBXpos;
	public int TBYpos;
	private Animation animSelector;


	public Selector(Handler handler){

		this.handler = handler;

		//Pause State positions
		RBXpos = handler.getWidth()/2 - 128 * 2 - 35;	//resume button
		RBYpos = handler.getHeight()/2 - 20;
		QBXpos = handler.getWidth()/2 + 128 - 10;		//pause quit button
		TBXpos = handler.getWidth()/2 - 64 - 35;
		TBYpos = handler.getHeight()/2 + 64 + 15;
		this.xPos = this.getRBpos();					//pause x position
		this.yPos = handler.getHeight()/2 - 20;			//pause y position


		//Menu State positions
		SBYpos = handler.getHeight()/2 - 230;			//start button
		QBYpos2 =  handler.getHeight()/2 + 95;			//menu quit button
		this.yPos2 = this.getSBYpos();					//menu y position
		this.xPos2 = handler.getWidth()/2 - 400 - 150;	//menu x position
		animSelector = new Animation(50, Images.IceSkill);


	}



	public void tick() {

		this.select();
		animSelector.tick();

	}

	public void render(Graphics g) {

		Graphics2D g2 = (Graphics2D) g;

		Ellipse2D.Double selector;

		if(State.getState().equals(handler.getGame().menuState)) {
				g.drawImage(Images.tint(animSelector.getCurrentFrame(), new Random().nextInt(200), new Random().nextInt(200), new Random().nextInt(200)), this.getxPos2() - 25, this.getyPos2() - 25, 150, 150, null);



		}

		else {

			selector = new Ellipse2D.Double(this.getxPos(), this.getyPos(), 30, 30);
			g2.setColor(Color.WHITE);

			g2.fill(selector);

		}



	}

	public void select() {

		if(State.getState().equals(handler.getGame().pauseState)) {

			if(this.handler.getKeyManager().keyJustPressed(KeyEvent.VK_D)) {

				if(this.getxPos() == this.getRBpos()) {

					this.setxPos(this.getTBXpos());
					this.setyPos(this.getTBYpos());

				}

				else {

					this.setxPos(this.getQBpos());
					this.setyPos(this.getRBYpos());

				}

			}


			else if(this.handler.getKeyManager().keyJustPressed(KeyEvent.VK_A)) {

				if(this.getxPos() == this.getQBpos()) {

					this.setxPos(this.getTBXpos());
					this.setyPos(this.getTBYpos());

				}

				else {

					this.setxPos(this.getRBpos());
					this.setyPos(this.getRBYpos());

				}

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



	public int getTBXpos() {
		return TBXpos;
	}



	public int getRBXpos() {
		return RBXpos;
	}



	public void setRBXpos(int rBXpos) {
		RBXpos = rBXpos;
	}



	public int getRBYpos() {
		return RBYpos;
	}



	public void setRBYpos(int rBYpos) {
		RBYpos = rBYpos;
	}



	public void setTBXpos(int tBXpos) {
		TBXpos = tBXpos;
	}



	public int getTBYpos() {
		return TBYpos;
	}



	public void setTBYpos(int tBYpos) {
		TBYpos = tBYpos;
	}
}
