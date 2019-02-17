package Resources;

import Main.Handler;
import javafx.embed.swing.JFXPanel;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Ellipse2D;
import java.io.*;
import java.util.ArrayList;
import java.util.Random;

import Game.Entities.Statics.BaseStaticEntity;

public class MusicHandler {


    private Media media;
    private MediaPlayer player;
    public boolean isPlaying = false;
    private String path;
    public boolean alreadyStarted = false;
    private boolean Loop = false;

    private ArrayList<MediaPlayer> playerE = new ArrayList<>();
    private Media mediaE;
    private String pathE;

    private Handler handler;


    public static final double MUTE = 0.0;

    public MusicHandler(Handler handler){
        this.handler = handler;
        final JFXPanel fxPanel = new JFXPanel();

    }

    public void play(){
        if(!isPlaying){
            if(!alreadyStarted) {
                player = new MediaPlayer(media);
                player.play();
                isPlaying = true;
                alreadyStarted = true;
            }else{
                player.play();
            }
        }

    }


    public void pause(){

        if(isPlaying){
            player.pause();
            isPlaying = false;
        }

    }

    public void set_changeMusic(String Path){

        if(isPlaying){
            pause();
            alreadyStarted = false;
        }
        path= Path;
        media = new Media(new File(Path).toURI().toString());

    }

    public void playEfect(String EPath,int index){

        pathE=EPath;
        mediaE = new Media(new File(EPath).toURI().toString());
        playerE.add(index, new MediaPlayer(mediaE));
        playerE.get(index).play();


    }

    public void stopEfect(int index){

        playerE.get(index).stop();

    }

    public MediaPlayer getPlayer() {
        return player;
    }

    public void setPlayer(MediaPlayer player) {
        this.player = player;
    }

    public void setLoop(boolean loop){
        Loop = loop;
        if(loop){
            player.setCycleCount(-1);
        }else{
            player.setCycleCount(1);
            player.setOnEndOfMedia(() -> {
                alreadyStarted = false;
                isPlaying = false;
            });
        }
    }

    public void setVolume (double volume){
        player.setVolume(volume);
    }
    
    public class Circle extends BaseStaticEntity{
    	
    	private int cWidth = 20;
    	private int cHeight = 20;
    	private Handler handler;
    	private Rectangle cBound;
    	private Random random = new Random();
    	
    	public Circle(int x, int y, Handler handler) {
    		super(handler, x, y);
    		
    		this.setXOffset(x);
    		this.setYOffset(y);
    		this.handler = handler;
    		this.cBound = new Rectangle((int) (this.handler.getXDisplacement() + this.xPosition), (int) (this.handler.getYDisplacement() + this.yPosition), this.getcWidth(), this.getcHeight());
    		
    	}
    	
    	public void tick() {
    		
    		if(this.handler.getEntityManager().getPlayer().getCollision().intersects(this.cBound));
    		
    	}
    	
    	public void render(Graphics g) {
    		
    		Graphics2D g2 = (Graphics2D) g;
    		
    		Ellipse2D.Double circle = new Ellipse2D.Double((int) (this.handler.getXDisplacement() + this.xPosition), (int) (this.handler.getYDisplacement() + this.yPosition), this.getcWidth(), this.getcHeight());
    		
    		g2.setColor(new Color(random.nextInt(255), random.nextInt(255), random.nextInt(255)));
    		
    		g2.draw(circle);
    		
    	}

    	public Rectangle getCollision() {   		
    		return this.cBound;  		
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
