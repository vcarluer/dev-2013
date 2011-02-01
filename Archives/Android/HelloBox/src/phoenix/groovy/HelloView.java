package phoenix.groovy;

import java.security.spec.MGF1ParameterSpec;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.TextView;

public class HelloView extends SurfaceView implements SurfaceHolder.Callback {

	public Sprite2D testSprite;
	// Thread which will draw animation
	private HelloThread mThread;
	private Context mContext;
	private SurfaceHolder mSurfaceHolder;
	private TextView mStatusText;
	
	class HelloThread extends Thread {
		public static final int STATE_LOSE = 1;
        public static final int STATE_PAUSE = 2;
        public static final int STATE_READY = 3;
        public static final int STATE_RUNNING = 4;
        public static final int STATE_WIN = 5;		
        
		private SurfaceHolder mSurfaceHolder;
		private Context mContext;
		private Handler mHandler;
		
		private float mCanvasWidth;
		private float mCanvasHeight;
		
		private Paint mCirclePaint;
		
		private boolean mRun;
		
		private PhysicsWorld mWorld;
		
		private long mTimer;				
		
		/** The state of the game. One of READY, RUNNING, PAUSE, LOSE, or WIN */
        private int mMode;
		
        private SoundPool sounds;
        private int beep;
        
		public HelloThread(SurfaceHolder surfaceHolder, Context context, float width, float height, Handler handler) {						
			this.mSurfaceHolder = surfaceHolder;
			this.mContext = context;
			this.mHandler = handler;
			
			this.mCirclePaint = new Paint();
			this.mCirclePaint.setAntiAlias(true);
			this.mCirclePaint.setARGB(255, 0, 255, 0);
			
			this.mWorld = new PhysicsWorld();					
			
			this.setSurfaceSize(width, height);
			this.mWorld.create(this.mCanvasWidth, this.mCanvasHeight);
		}
		
		// Callback called when surface dimension change
		public void setSurfaceSize(float width, float height) {
			synchronized(this.mSurfaceHolder){
				this.mCanvasWidth = width;
				this.mCanvasHeight = height;
				
				// Resize here background image
			}
		}
		
		/**
         * Used to signal the thread whether it should be running or not.
         * Passing true allows the thread to run; passing false will shut it
         * down if it's already running. Calling start() after this was most
         * recently called with false will result in an immediate shutdown.
         * 
         * @param b true to run, false to shut down
         */
		public void setRunning(boolean b){
			this.mRun = b;
			this.mMode = STATE_RUNNING;
		}
		
		@Override
		public void run() {					
			
			 MediaPlayer mp = MediaPlayer.create(this.mContext, R.raw.mus);
			 mp.setLooping(true);
			 mp.start();
			 
			 sounds = new SoundPool(5, AudioManager.STREAM_MUSIC, 0);
			 beep = sounds.load(this.mContext, R.raw.predmet, 1);
			
			int frame = 0;
			long lastFrameTimer = System.currentTimeMillis();
						
	    	float dt = 1000f / 60.0f;
	    	this.mTimer = System.currentTimeMillis();
	    	float accumulator = 0.0f;	    	
			
			while (this.mRun) {
				Canvas c = null;
				try {															
					long newTime = System.currentTimeMillis();
					long frameTime = newTime - this.mTimer;
					this.mTimer = newTime;
					
					c = this.mSurfaceHolder.lockCanvas(null);
					synchronized (this.mSurfaceHolder) {
						if (this.mMode == STATE_RUNNING) {
							// this.updatePhysics();
							
							accumulator += frameTime;							
							// Log.v("Physics", "accumulator: " + String.valueOf(accumulator));
							int loopCount = 0;
							while (accumulator >= dt && loopCount < 4) {																
								this.mWorld.update(1.0f / 60.0f);
								accumulator -= dt;
								loopCount++;
							}
							
							// Log.v("Physics", "loops: " + String.valueOf(loopCount));
						}
						
						if (c != null) {
							this.doDraw(c);
							
							if (this.mTimer - lastFrameTimer > 1000){
								Message msg = mHandler.obtainMessage();
			                    Bundle b = new Bundle();
			                    b.putString("text", "FPS: " + String.valueOf(frame));
			                    b.putInt("viz", View.VISIBLE);
			                    msg.setData(b);
			                    mHandler.sendMessage(msg);
								Log.v("FPS", String.valueOf(frame));
								lastFrameTimer = this.mTimer;
								frame = 0;
							}
							
							frame++;
						}
					}
				} finally {
					// do this in a finally so that if an exception is thrown
                    // during the above, we don't leave the Surface in an
                    // inconsistent state
					if (c != null) {
						this.mSurfaceHolder.unlockCanvasAndPost(c);
					}
				}								
			}
			
			mp.stop();
			mp.release();
		}
		
		private void updatePhysics() {
			// this.mWorld.update(this.mTimer);									
		}
		
		private void doDraw(Canvas c) {						
			c.drawColor(Color.GRAY);
			c.drawCircle(10, 10, 5, this.mCirclePaint);			
			
			this.mWorld.draw(c);
			
			testSprite.update(this.mTimer);
			testSprite.draw(c);
		}
		
		/**
         * Handles a key-down event.
         * 
         * @param keyCode the key that was pressed
         * @param msg the original event object
         * @return true
         */
        boolean doKeyDown(int keyCode, KeyEvent msg) {
        	boolean handled = false;
        	
        	if (mMode == STATE_RUNNING) {
	        	synchronized (mSurfaceHolder) {                
	                if (keyCode == KeyEvent.KEYCODE_C
	                        || keyCode == KeyEvent.KEYCODE_SPACE) {                        
	                    //this.mWorld.addBall(1.0f);
	                	handled = true;
	                } else if (keyCode == KeyEvent.KEYCODE_B) {
	                	// this.mWorld.addBox();
	                	handled = true;
	                }
	            }
        	}
        	
            return handled;
        }        
        
        public void doClick(float x, float y)
        {
        	if (this.mWorld != null){
        		sounds.play(this.beep, 1, 1, 1, 0, 1);
        		// this.mWorld.addBall(1000.0f, x, y);
        		this.mWorld.addBox(3.0f, x, y);        		
        	}
        }

        /**
         * Handles a key-up event.
         * 
         * @param keyCode the key that was pressed
         * @param msg the original event object
         * @return true if the key was handled and consumed, or else false
         */
        boolean doKeyUp(int keyCode, KeyEvent msg) {
            boolean handled = false;

            synchronized (this.mSurfaceHolder) {
                if (mMode == STATE_RUNNING) { 
                	
                }
            }

            return handled;
        }
	}
	// END THREAD CLASS
	
	public HelloView(Context context, AttributeSet attrs) {
		super(context, attrs);
				
		this.mContext = context;				
	}
	
	public void init(float width, float height) {
		// registering event to our surface
		SurfaceHolder holder = this.getHolder();
		holder.addCallback(this);
		
		this.testSprite = new Sprite2D();
		this.testSprite.init(BitmapFactory.decodeResource(this.getResources(), R.drawable.arrow), 100, 60, 6, 6); 
		// Create thread. Will be started when surface is created		
		this.mThread = new HelloThread(holder, this.mContext, width, height, new Handler() {
            @Override
            public void handleMessage(Message m) {
                mStatusText.setVisibility(m.getData().getInt("viz"));
                mStatusText.setText(m.getData().getString("text"));
            }
        });
		
		this.setFocusable(true);
	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
		this.mThread.setSurfaceSize(width, height);		
	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		this.mThread.setRunning(true);
		this.mThread.start();		
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		 // we have to tell thread to shut down & wait for it to finish, or else
        // it might touch the Surface after we return and explode
		boolean retry = true;
		this.mThread.setRunning(false);
		while (retry) {
			try {
				this.mThread.join();
				retry = false;
			} catch (InterruptedException e) {				
			}
		}
		
	}
	
	public HelloThread getThread() {
		return this.mThread;
	}

	/* (non-Javadoc)
	 * @see android.view.View#onWindowFocusChanged(boolean)
	 */
	/*@Override
	public void onWindowFocusChanged(boolean hasWindowFocus) {
		if (!this.hasWindowFocus()) this.mThread.pause();
	}*/
	
	/**
     * Standard override to get key-press events.
     */
    /*@Override
    public boolean onKeyDown(int keyCode, KeyEvent msg) {
        return this.mThread.doKeyDown(keyCode, msg);
    }*/

    /**
     * Standard override for key-up. We actually care about these, so we can
     * turn off the engine or stop rotating.
     */
    /*@Override
    public boolean onKeyUp(int keyCode, KeyEvent msg) {
    	return this.mThread.doKeyUp(keyCode, msg);
    }*/

	/* (non-Javadoc)
	 * @see android.view.View#setOnClickListener(android.view.View.OnClickListener)
	 */
	/*@Override
	public void setOnClickListener(OnClickListener l) {
		// TODO Auto-generated method stub
		super.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                mThread.doClick();
            }
        });
	}*/

	/* (non-Javadoc)
	 * @see android.view.View#onTouchEvent(android.view.MotionEvent)
	 */
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		if (event.getAction() == MotionEvent.ACTION_DOWN) {
			if (this.mThread != null) {
				this.mThread.doClick(event.getX(), event.getY());
				return true;
			}
		}
		
		return false;
	}
	
	public void setTextView(TextView view) {
		this.mStatusText = view;
	}      
}
