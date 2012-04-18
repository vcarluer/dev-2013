package gamers.associate.warmup.client;

import gamers.associate.warmup.WarmUp;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.backends.gwt.GwtApplication;
import com.badlogic.gdx.backends.gwt.GwtApplicationConfiguration;

public class WarmUpGwt extends GwtApplication {

	public GwtApplicationConfiguration getConfig() {
		GwtApplicationConfiguration configuration = new GwtApplicationConfiguration(WarmUp.WIDTH, WarmUp.HEIGHT);
		configuration.useDebugGL = false;
		return configuration;
	}

	public ApplicationListener getApplicationListener() {
		return WarmUp.get();
	}

}
