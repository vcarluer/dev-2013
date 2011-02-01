package phoenix.groovy;

import org.apache.http.util.LangUtils;

import phoenix.groovy.HelloView.HelloThread;
import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.Display;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

public class HelloBox extends Activity {
	private HelloView mView;
	private HelloThread mThread;
	
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        //this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        this.setFullScreen();
        this.setContentView(R.layout.main);
        
        this.mView = (HelloView) this.findViewById(R.id.hello);
        this.mView.setTextView((TextView) findViewById(R.id.text));
        
        Display display = getWindowManager().getDefaultDisplay(); 
        int width = display.getWidth();
        int height = display.getHeight();
        this.mView.init(width, height);
        // Not use for new, used to set state ready or restore state
        this.mThread = this.mView.getThread();               
    }
    
    public void setFullScreen() {
    	this.requestWindowFeature(Window.FEATURE_NO_TITLE);
    	this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
									WindowManager.LayoutParams.FLAG_FULLSCREEN);
    	}
}