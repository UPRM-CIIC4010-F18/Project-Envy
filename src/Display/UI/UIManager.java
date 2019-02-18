package Display.UI;

import Main.Handler;
import Resources.Images;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Random;

import Game.Entities.EntityManager;
import Game.World.Walls;
import Game.World.InWorldAreas.BaseArea;
import Game.World.InWorldAreas.InWorldWalls;

/**
 * Created by AlexVR on 7/1/2018.
 */
public class UIManager {

	private Handler handler;
	private ArrayList<UIObject> objects;

	public UIManager(Handler handler){
		this.handler=handler;
		objects = new ArrayList<UIObject>();
	}

	public void tick(){
		for(UIObject o: objects){
			o.tick();
		}
	}

	public void Render(Graphics g){
		for(UIObject o: objects){
			o.render(g);
		}
	}

	public void onMousePressed(MouseEvent e) {
		for(UIObject o: objects){
			o.onMousePressed(e);
		}
	}

	public void onMouseMove(MouseEvent e){
		for(UIObject o: objects){
			o.onMouseMove(e);
		}
	}

	public void onMouseRelease(MouseEvent e){
		for(UIObject o: objects){
			o.onMouseRelease(e);
		}
	}

	public void addObjects (UIObject o){
		objects.add(o);
	}

	public void removeObsjects(UIObject o){
		objects.remove(o);
	}

	public Handler getHandler() {
		return handler;
	}

	public void setHandler(Handler handler) {
		this.handler = handler;
	}

	public ArrayList<UIObject> getObjects() {
		return objects;
	}

	public void setObjects(ArrayList<UIObject> objects) {
		this.objects = objects;
	}

	public void isActive(ArrayList<UIObject> o, Boolean active){
		for(UIObject i: o){
			i.active=active;
		}
	}

	public class Area extends BaseArea{

		EntityManager manager;
		private final static int playerXSpawn = -380, playerYSpawn = -3270;
		Rectangle playerRect;
		Random random = new Random();
		private int width = 8000;
		private int height = 6000;

		public ArrayList<InWorldWalls> areaWalls;

		public Area(Handler handler, EntityManager entityManager) {
			super(handler, entityManager);

			handler.setXInWorldDisplacement(playerXSpawn);
			handler.setYInWorldDisplacement(playerYSpawn + 500);

			this.manager = entityManager;

			this.areaWalls = new ArrayList<>();

			this.addWalls();

		}

		public void tick() {
			super.tick();

			this.manager.tick();

			for(Walls w : areaWalls) {

				w.tick();

			}


		}

		public void render(Graphics g) {

			Graphics2D g2 = (Graphics2D) g;

			g2.drawImage(Images.ScaledArea, handler.getXInWorldDisplacement(), handler.getYInWorldDisplacement(), null);

			this.manager.render(g2);

			for(Walls w : areaWalls) {

				g2.setColor(Color.WHITE);

				w.render(g2);

			}

			g2.setColor(Color.WHITE);

			for(int i=0;i<handler.getWidth();i+=random.nextInt(45)){
                    int s= random.nextInt(10)+1;
                    g2.fillRect((random.nextInt(this.width)) + handler.getXInWorldDisplacement(), (random.nextInt(this.height)) + handler.getYInWorldDisplacement(), s, s);
            }

		}

		private void addWalls() {

			this.areaWalls.add(new InWorldWalls(handler, 0, 0, this.width, 2, "Wall"));
			this.areaWalls.add(new InWorldWalls(handler, 0, 0, 2, this.height, "Wall"));
			this.areaWalls.add(new InWorldWalls(handler, 0, this.height, this.width, 2, "Wall"));
			this.areaWalls.add(new InWorldWalls(handler, this.width, 0, 2, this.height, "Wall"));


		}

		public ArrayList<InWorldWalls> getWalls(){

			return this.areaWalls;

		}


	}

}
