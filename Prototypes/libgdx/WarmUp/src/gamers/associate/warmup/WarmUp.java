package gamers.associate.warmup;

import gamers.associate.warmup.items.Spaceship;
import gamers.associate.warmup.screens.GameScreen;

import com.badlogic.gdx.Game;

public class WarmUp extends Game {	
	public static final String Tag = "WarmUp";
	private static WarmUp warmUp;	
	
	public static WarmUp get() {
		if (warmUp == null) {
			warmUp = new WarmUp();
		}
		
		return warmUp;
	}
	
	@Override
	public void create() {		
		this.setScreen(new GameScreen());				
	}
}
