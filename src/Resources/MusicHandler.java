package Resources;

import Main.Handler;
import javafx.embed.swing.JFXPanel;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.io.*;
import java.util.ArrayList;
import java.util.Random;

import Game.Entities.Dynamics.Player;
import Game.Entities.Statics.BaseStaticEntity;
import Game.GameStates.InWorldState;
import Game.GameStates.State;

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

	public void stop() {
		player.stop();
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

	public void playEffect(String EPath,int index){

		pathE=EPath;
		mediaE = new Media(new File(EPath).toURI().toString());
		playerE.add(index, new MediaPlayer(mediaE));
		if(getPlayer().getVolume()>0) {
			playerE.get(index).setVolume(getPlayer().getVolume());
		}else{
			playerE.get(index).setVolume(0);
		}
		playerE.get(index).play();

	}
	
	public void stopEffect(int index){

		playerE.get(index).stop();

	}

	public MediaPlayer getEffect(int index){

		return playerE.get(index);

	}

	public ArrayList<MediaPlayer> getEPlayer(){
		return playerE;
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

	// Used after you've started playing the music
	public void setVolume (double volume){
		player.setVolume(volume);
	}

	public void setEffectVolume(int index, double volume) {
		playerE.get(index).setVolume(volume);
	}

}
