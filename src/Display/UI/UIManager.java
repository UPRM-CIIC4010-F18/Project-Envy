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
		private int sXPos = (random.nextInt(this.width)) + handler.getXInWorldDisplacement();
		private int sYPos = (random.nextInt(this.height)) + handler.getYInWorldDisplacement();
		private int sXPos2 = (random.nextInt(this.width)) + handler.getXInWorldDisplacement();
		private int sYPos2 = (random.nextInt(this.height)) + handler.getYInWorldDisplacement();
		private int sXPos3 = (random.nextInt(this.width)) + handler.getXInWorldDisplacement();
		private int sYPos3 = (random.nextInt(this.height)) + handler.getYInWorldDisplacement();
		private int sXPos4 = (random.nextInt(this.width)) + handler.getXInWorldDisplacement();
		private int sYPos4 = (random.nextInt(this.height)) + handler.getYInWorldDisplacement();
		private int sXPos5 = (random.nextInt(this.width)) + handler.getXInWorldDisplacement();
		private int sYPos5 = (random.nextInt(this.height)) + handler.getYInWorldDisplacement();
		private int sXPos6 = (random.nextInt(this.width)) + handler.getXInWorldDisplacement();
		private int sYPos6 = (random.nextInt(this.height)) + handler.getYInWorldDisplacement();
		private int sXPos7 = (random.nextInt(this.width)) + handler.getXInWorldDisplacement();
		private int sYPos7 = (random.nextInt(this.height)) + handler.getYInWorldDisplacement();
		private int sXPos8 = (random.nextInt(this.width)) + handler.getXInWorldDisplacement();
		private int sYPos8 = (random.nextInt(this.height)) + handler.getYInWorldDisplacement();
		private int sXPos9 = (random.nextInt(this.width)) + handler.getXInWorldDisplacement();
		private int sYPos9 = (random.nextInt(this.height)) + handler.getYInWorldDisplacement();
		private int sSize = 10;

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

			g2.fillRect(this.sXPos, this.sYPos, this.sSize, this.sSize);
			g2.fillRect(this.sXPos2, this.sYPos2, this.sSize, this.sSize);
			g2.fillRect(this.sXPos3, this.sYPos3, this.sSize, this.sSize);
			g2.fillRect(this.sXPos3, this.sYPos3, this.sSize, this.sSize);
			g2.fillRect(this.sXPos4, this.sYPos4, this.sSize, this.sSize);
			g2.fillRect(this.sXPos5, this.sYPos5, this.sSize, this.sSize);
			g2.fillRect(this.sXPos6, this.sYPos6, this.sSize, this.sSize);
			g2.fillRect(this.sXPos7, this.sYPos7, this.sSize, this.sSize);
			g2.fillRect(this.sXPos8, this.sYPos8, this.sSize, this.sSize);
			g2.fillRect(this.sXPos9, this.sYPos9, this.sSize, this.sSize);

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
		
		public void changeSparklePos() {
			
			this.setsXPos(random.nextInt(this.width) + handler.getXInWorldDisplacement());
			this.setsYPos(random.nextInt(this.height) + handler.getYInWorldDisplacement());
			this.setsXPos2(random.nextInt(this.width) + handler.getXInWorldDisplacement());
			this.setsYPos2(random.nextInt(this.height) + handler.getYInWorldDisplacement());
			this.setsXPos3(random.nextInt(this.width) + handler.getXInWorldDisplacement());
			this.setsYPos3(random.nextInt(this.height) + handler.getYInWorldDisplacement());
			this.setsXPos4(random.nextInt(this.width) + handler.getXInWorldDisplacement());
			this.setsYPos4(random.nextInt(this.height) + handler.getYInWorldDisplacement());
			this.setsXPos5(random.nextInt(this.width) + handler.getXInWorldDisplacement());
			this.setsYPos5(random.nextInt(this.height) + handler.getYInWorldDisplacement());
			this.setsXPos6(random.nextInt(this.width) + handler.getXInWorldDisplacement());
			this.setsYPos6(random.nextInt(this.height) + handler.getYInWorldDisplacement());
			this.setsXPos7(random.nextInt(this.width) + handler.getXInWorldDisplacement());
			this.setsYPos7(random.nextInt(this.height) + handler.getYInWorldDisplacement());
			this.setsXPos8(random.nextInt(this.width) + handler.getXInWorldDisplacement());
			this.setsYPos8(random.nextInt(this.height) + handler.getYInWorldDisplacement());
			this.setsXPos9(random.nextInt(this.width) + handler.getXInWorldDisplacement());
			this.setsYPos9(random.nextInt(this.height) + handler.getYInWorldDisplacement());
			
		}

		public int getsXPos() {
			return sXPos;
		}

		public void setsXPos(int sXPos) {
			this.sXPos = sXPos;
		}

		public int getsYPos() {
			return sYPos;
		}

		public void setsYPos(int sYPos) {
			this.sYPos = sYPos;
		}

		public int getsXPos2() {
			return sXPos2;
		}

		public void setsXPos2(int sXPos2) {
			this.sXPos2 = sXPos2;
		}

		public int getsYPos2() {
			return sYPos2;
		}

		public void setsYPos2(int sYPos2) {
			this.sYPos2 = sYPos2;
		}

		public int getsXPos3() {
			return sXPos3;
		}

		public void setsXPos3(int sXPos3) {
			this.sXPos3 = sXPos3;
		}

		public int getsYPos3() {
			return sYPos3;
		}

		public void setsYPos3(int sYPos3) {
			this.sYPos3 = sYPos3;
		}

		public int getsXPos4() {
			return sXPos4;
		}

		public void setsXPos4(int sXPos4) {
			this.sXPos4 = sXPos4;
		}

		public int getsYPos4() {
			return sYPos4;
		}

		public void setsYPos4(int sYPos4) {
			this.sYPos4 = sYPos4;
		}

		public int getsXPos5() {
			return sXPos5;
		}

		public void setsXPos5(int sXPos5) {
			this.sXPos5 = sXPos5;
		}

		public int getsYPos5() {
			return sYPos5;
		}

		public void setsYPos5(int sYPos5) {
			this.sYPos5 = sYPos5;
		}

		public int getsXPos6() {
			return sXPos6;
		}

		public void setsXPos6(int sXPos6) {
			this.sXPos6 = sXPos6;
		}

		public int getsYPos6() {
			return sYPos6;
		}

		public void setsYPos6(int sYPos6) {
			this.sYPos6 = sYPos6;
		}

		public int getsXPos7() {
			return sXPos7;
		}

		public void setsXPos7(int sXPos7) {
			this.sXPos7 = sXPos7;
		}

		public int getsYPos7() {
			return sYPos7;
		}

		public void setsYPos7(int sYPos7) {
			this.sYPos7 = sYPos7;
		}

		public int getsXPos8() {
			return sXPos8;
		}

		public void setsXPos8(int sXPos8) {
			this.sXPos8 = sXPos8;
		}

		public int getsYPos8() {
			return sYPos8;
		}

		public void setsYPos8(int sYPos8) {
			this.sYPos8 = sYPos8;
		}

		public int getsXPos9() {
			return sXPos9;
		}

		public void setsXPos9(int sXPos9) {
			this.sXPos9 = sXPos9;
		}

		public int getsYPos9() {
			return sYPos9;
		}

		public void setsYPos9(int sYPos9) {
			this.sYPos9 = sYPos9;
		}

	}

}
