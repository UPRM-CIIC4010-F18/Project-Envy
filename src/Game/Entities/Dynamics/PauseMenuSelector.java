package Game.Entities.Dynamics;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.geom.Ellipse2D;

import Main.Handler;

public class PauseMenuSelector {
	
	Handler handler;
	public int xPos;
	public int yPos;
	public int RBXpos;
	public int QBXpos;
	
	public int size = 30;
	
	
	public PauseMenuSelector(Handler handler){
		
		this.handler = handler;
		
		RBXpos = handler.getWidth()/2 - 128 * 2 - 35;
		QBXpos = handler.getWidth()/2 + 128 - 10;
		
		this.yPos = handler.getHeight()/2 - 20;
		this.xPos = this.getRBpos();
		
	}
	
	public void tick() {
		
		this.select();
		
	}
	
	public void render(Graphics g) {
		
		Graphics2D g2 = (Graphics2D) g;
		
		Ellipse2D.Double selector = new Ellipse2D.Double(this.getxPos(), this.getyPos(), this.size, this.size);
		
		g2.setColor(Color.WHITE);
		
		g2.fill(selector);
		
	}
	
	public void select() {
		
		if(this.handler.getKeyManager().keyJustPressed(KeyEvent.VK_D)) {
			
			this.setxPos(this.getQBpos());
			
		}
		
		else if(this.handler.getKeyManager().keyJustPressed(KeyEvent.VK_A)) {
			
			this.setxPos(this.getRBpos());
			
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

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
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

}
