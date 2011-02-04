package gamers.associate.haaaaaa;

import java.util.Iterator;

import org.cocos2d.actions.UpdateCallback;
import org.cocos2d.actions.base.CCRepeatForever;
import org.cocos2d.actions.camera.CCOrbitCamera;
import org.cocos2d.actions.grid.CCFlipY3D;
import org.cocos2d.actions.interval.CCSequence;
import org.cocos2d.config.ccMacros;
import org.cocos2d.events.CCTouchDispatcher;
import org.cocos2d.layers.CCLayer;
import org.cocos2d.layers.CCScene;
import org.cocos2d.nodes.CCDirector;
import org.cocos2d.nodes.CCLabel;
import org.cocos2d.nodes.CCSprite;
import org.cocos2d.nodes.CCSpriteSheet;
import org.cocos2d.types.CGPoint;
import org.cocos2d.types.CGRect;
import org.cocos2d.types.CGSize;
import org.cocos2d.types.ccColor3B;
import org.cocos2d.types.ccGridSize;

import android.R.bool;
import android.view.MotionEvent;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;

public class HaaaaaaLayer extends CCLayer {
	public static final int kTagTileMap = 1;
    public static final int kTagSpriteManager = 1;
    public static final int kTagAnimation1 = 1;
    
	// Pixel to meters ratio. Box2D uses meters as the unit for measurement.
	// This ratio defines how many pixels correspond to 1 Box2D "meter"
	// Box2D is optimized for objects of 1x1 meter therefore it makes sense
	// to define the ratio so that your most common object type is 1x1 meter.
    protected static final float PTM_RATIO = 32.0f;
    
    // Simulation space should be larger than window per Box2D recommendation.
    protected static final float BUFFER = 1.0f;
    
    protected final World bxWorld;
    
    protected float scaledWidth;
    protected float scaledHeight;
    
    protected Boolean isAccelerometerEnabled = false;
    
    /*protected CCLabel xAccel;
    protected CCLabel yAccel;*/
    
	public static CCScene scene() {
		CCScene scene = CCScene.node();
		CCLayer layer = new HaaaaaaLayer();
		scene.addChild(layer);
		
		return scene;
	}
	
	protected HaaaaaaLayer() {
		super();
		
		this.setIsTouchEnabled(true);
		this.setIsAccelerometerEnabled(true);
		
		CGSize s = CCDirector.sharedDirector().winSize();
		
		// Define the gravity vector.
    	Vector2 gravity = new Vector2(0.0f, -10.0f);
		
		scaledWidth = s.width/PTM_RATIO;
        scaledHeight = s.height/PTM_RATIO;
        
        bxWorld = new World(gravity, true);
    	// bxWorld.setContinuousPhysics(true); // ?        
    	
    	// Define the ground body.
        BodyDef bxGroundBodyDef = new BodyDef();
        bxGroundBodyDef.position.set(0.0f, 0.0f);
		
		// Call the body factory which allocates memory for the ground body
		// from a pool and creates the ground box shape (also from a pool).
		// The body is also added to the world.
        Body groundBody = bxWorld.createBody(bxGroundBodyDef);

     // Define the ground box shape.
        PolygonShape groundBox = new PolygonShape();

        Vector2 bottomLeft = new Vector2(0f,0f);
        Vector2 topLeft = new Vector2(0f,scaledHeight);
        Vector2 topRight = new Vector2(scaledWidth,scaledHeight);
        Vector2 bottomRight = new Vector2(scaledWidth,0f);
        
		// bottom
		groundBox.setAsEdge(bottomLeft, bottomRight);
		groundBody.createFixture(groundBox,0);
		
		// top
		groundBox.setAsEdge(topLeft, topRight);
		groundBody.createFixture(groundBox,0);
		
		// left
		groundBox.setAsEdge(topLeft, bottomLeft);
		groundBody.createFixture(groundBox,0);
		
		// right
		groundBox.setAsEdge(topRight, bottomRight);
		groundBody.createFixture(groundBox,0);
		
		//Set up sprite
        CCSpriteSheet mgr = CCSpriteSheet.spriteSheet("blocks.png", 150);
        addChild(mgr, 0, kTagSpriteManager);
		
       // addNewSpriteWithCoords(CGPoint.ccp(s.width / 2.0f, s.height / 2.0f));
       
       CCLabel label = CCLabel.makeLabel("Tap screen", "DroidSans", 32);
       label.setPosition(CGPoint.make(s.width / 2f, s.height - 50f));
       label.setColor(new ccColor3B(0, 0, 255));
       addChild(label);
       
       /*this.xAccel = CCLabel.makeLabel("X Accelerometer:", "DroidSans", 32);
       this.xAccel.setPosition(CGPoint.make(s.width / 2f, 64f));
       this.xAccel.setColor(new ccColor3B(255, 0, 0));
       addChild(this.xAccel);
       
       this.yAccel = CCLabel.makeLabel("Y Accelerometer:", "DroidSans", 32);
       this.yAccel.setPosition(CGPoint.make(s.width / 2f, 32f));
       this.yAccel.setColor(new ccColor3B(255, 0, 0));
       addChild(this.yAccel);*/
	}
	
	private UpdateCallback tickCallback = new UpdateCallback() {
		
		@Override
		public void update(float d) {
			tick(d);
		}
	};
	
	@Override
	public void onEnter() {
		super.onEnter();
		
		// start ticking (for physics simulation)
		schedule(tickCallback);
	}

	@Override
	public void onExit() {
		super.onExit();
		
		// stop ticking (for physics simulation)			
		unschedule(tickCallback);
	}
	
	private void addNewSpriteWithCoords(CGPoint pos) {
  		CCSpriteSheet sheet = (CCSpriteSheet)getChild(kTagSpriteManager);

		//We have a 64x64 sprite sheet with 4 different 32x32 images.  The following code is
		//just randomly picking one of the images
		int idx = (ccMacros.CCRANDOM_0_1() > .5 ? 0:1);
		int idy = (ccMacros.CCRANDOM_0_1() > .5 ? 0:1);

//		CCSprite sprite = CCSprite.sprite("blocks.png", CGRect.make(32 * idx,32 * idy,32,32));
//		this.addChild(sprite);
		CCSprite sprite = CCSprite.sprite(sheet, CGRect.make(32 * idx,32 * idy,32,32));		
		//sheet.addChild(sprite);
		addChild(sprite);
		
		CGPoint spawnPoint = new CGPoint();
		spawnPoint.x = pos.x;
		spawnPoint.y = CCDirector.sharedDirector().winSize().getHeight() - 64;
		sprite.setPosition(spawnPoint);
		CCOrbitCamera orbit1 = CCOrbitCamera.action(2, 1, 0, 0, 180, 0, 0);
		CCSequence action1 = CCSequence.actions(
			orbit1,
			orbit1.reverse()
			);
		sprite.runAction(CCRepeatForever.action(action1));
		//sheet.runAction(CCRepeatForever.action(action1));
		//sprite.runAction(CCFlipY3D.action(2.0f));

		// Define the dynamic body.
		//Set up a 1m squared box in the physics world
		BodyDef bodyDef = new BodyDef();
		bodyDef.type = BodyType.DynamicBody;
		bodyDef.position.set(spawnPoint.x/PTM_RATIO, spawnPoint.y/PTM_RATIO);
		
		// Define another box shape for our dynamic body.
		PolygonShape dynamicBox = new PolygonShape();
		dynamicBox.setAsBox(.5f, .5f);//These are mid points for our 1m box
//		dynamicBox.density = 1.0f;
//        dynamicBox.friction = 0.3f;
		
    	synchronized (bxWorld) {
    		// Define the dynamic body fixture and set mass so it's dynamic.
    		Body body = bxWorld.createBody(bodyDef);
    		body.setUserData(sprite);
    		
    		FixtureDef fixtureDef = new FixtureDef();
    		fixtureDef.shape = dynamicBox;	
    		fixtureDef.density = 1.0f;
    		fixtureDef.friction = 0.3f;
    		fixtureDef.restitution = 0.5f;
    		body.createFixture(fixtureDef);
    	}        	
    }
	

    public synchronized void tick(float delta) {
    	// It is recommended that a fixed time step is used with Box2D for stability
    	// of the simulation, however, we are using a variable time step here.
    	// You need to make an informed choice, the following URL is useful
    	// http://gafferongames.com/game-physics/fix-your-timestep/
    	
    	// Instruct the world to perform a simulation step. It is
    	// generally best to keep the time step and iterations fixed.
    	synchronized (bxWorld) {
    		bxWorld.step(delta, 6, 2);
    	}
        	
    	// Iterate over the bodies in the physics world
    	Iterator<Body> it = bxWorld.getBodies();
    	while(it.hasNext()) {
    		Body b = it.next();
    		Object userData = b.getUserData();
    		
    		if (userData != null && userData instanceof CCSprite) {
    			//Synchronize the Sprites position and rotation with the corresponding body
    			CCSprite sprite = (CCSprite)userData;
    			sprite.setPosition(b.getPosition().x * PTM_RATIO, b.getPosition().y * PTM_RATIO);
    			sprite.setRotation(-1.0f * ccMacros.CC_RADIANS_TO_DEGREES(b.getAngle()));    			    			    			
    		}	
    	}
    }

    @Override
    public boolean ccTouchesBegan(MotionEvent event) {
        CGPoint location = CCDirector.sharedDirector()
        	.convertToGL(CGPoint.make(event.getX(), event.getY()));

        addNewSpriteWithCoords(location);

        return CCTouchDispatcher.kEventHandled;
    }

    static float prevX=0, prevY=0;
    
    Vector2 gravity = new Vector2();
    
	@Override
    public void ccAccelerometerChanged(float accelX, float accelY, float accelZ) {
		// this.xAccel.setString("X Accelerometer: " + String.valueOf(accelX));
		// this.yAccel.setString("Y Accelerometer: " + String.valueOf(accelY));
		
		//#define kFilterFactor 0.05f
		if (isAccelerometerEnabled) {
			float kFilterFactor  = 1.0f;	// don't use filter. the code is here just as an example
	
			float accX = (float) accelX * kFilterFactor + (1- kFilterFactor)* prevX;
			float accY = (float) accelY * kFilterFactor + (1- kFilterFactor)* prevY;
			
			prevX = accX;
			prevY = accY;		
			
			// no filtering being done in this demo (just magnify the gravity a bit)
			//gravity.set( accY * 1.0f, accX * -1.0f );
			gravity.set( accelX * -1.0f, accelY * -1.0f );
			bxWorld.setGravity( gravity );
		}
	}		
}
