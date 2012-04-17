package gamers.associate.warmup;

import gamers.associate.warmup.items.Spaceship;
import gamers.associate.warmup.screens.GameScreen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;

public class WarmUp extends Game {	
	private static final String MusicPath = "data/warmup.mp3";
	public static final String Tag = "WarmUp";
	private static WarmUp warmUp;	
	private Music music;
	
	public static WarmUp get() {
		if (warmUp == null) {
			warmUp = new WarmUp();
		}
		
		return warmUp;
	}
	
	@Override
	public void create() {		
		this.setScreen(new GameScreen());	
		this.music = Gdx.audio.newMusic(Gdx.files.internal(MusicPath));
		this.music.setLooping(true);
		this.music.play();
	}

	@Override
	public void dispose() {
		this.music.stop();
		super.dispose();
	}
}
