package phoenix.groovy;

import java.util.ArrayList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import org.jbox2d.collision.AABB;
import org.jbox2d.collision.CircleDef;
import org.jbox2d.collision.MassData;
import org.jbox2d.collision.PolygonDef;
import org.jbox2d.collision.Shape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.World;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;

public class PhysicsWorld 
{
	public int targetFPS = 30;  
	public int timeStep = (1000 / targetFPS);  
	public int iterations = 5;

    private ArrayList<Body> bodies;
    private int count = 0;

    private AABB worldAABB;
    private World world;
    private BodyDef groundBodyDef;
    private PolygonDef boxShapeDef;

    //a paint
    private Paint paint;
    
    private ReentrantReadWriteLock lock;
    
    private float mWidth;
    private float mHeight;
        
    private long mFrameTimer;    
    
    public void create(float w, float h) 
    {
    	this.mFrameTimer =0;    	
        
    	this.mWidth = w;
    	this.mHeight = h;
    	
        //create the paint
        paint           = new Paint();
        // paint.setColor(0xFFFFFF);
        paint.setAntiAlias(true);
        paint.setARGB(255, 0, 255, 0);

        this.lock = new ReentrantReadWriteLock();
        this.lock.writeLock().lock();
        // Step 1: Create Physics World Boundaries
        worldAABB       = new AABB();
        worldAABB.lowerBound.set(new Vec2((float) -20, (float) -20));
        worldAABB.upperBound.set(new Vec2(this.mWidth + 20, this.mHeight + 20));

        // Step 2: Create Physics World with Gravity
        Vec2 gravity    = new Vec2((float) 0.0, (float)300.0);
        boolean doSleep = true;
        
        world           = new World(worldAABB, gravity, doSleep);
        
        bodies          = new ArrayList<Body>();        

        // Step 3: Create Ground Box
        groundBodyDef   = new BodyDef();
        groundBodyDef.position.set(new Vec2(this.mWidth / 2, this.mHeight - 10));
        Body groundBody = world.createBody(groundBodyDef);
        boxShapeDef  = new PolygonDef();
        boxShapeDef.setAsBox(this.mWidth / 2, 10.0f);
        groundBody.createShape(boxShapeDef);
        
        this.lock.writeLock().unlock();
        // this.addBall(1.0f);
        // this.addBox();
        // this.addBall(15.0f);        
    }

    public void addBall(float density, float x, float y) 
    {    		    	
    	this.lock.writeLock().lock();
        // Create Dynamic Body
    	BodyDef bodyDef  = new BodyDef();
        // bodyDef.position.set((float)16.0+(count * 25), (float)24.0);        
    	bodyDef.position.set(x, y);
        Body newBody     = world.createBody(bodyDef);        

        if (newBody != null) {
	        // Create Shape with Properties
	        CircleDef circle = new CircleDef();
	        circle.radius    = (float)50;
	        circle.density   = density;
	        circle.restitution = (float) 0.75;
	
	        // Assign shape to Body
	    	newBody.createShape(circle);	    		    	
	    	newBody.setMassFromShapes();
	        // MassData mass = new MassData();
	        // mass.mass = density;
	        // newBody.setMass(mass);
	        newBody.m_userData = "Circle";        
	    	bodies.add(newBody);	  
	    	// Increase Counter
	    	count += 1;
        }
        
    	this.lock.writeLock().unlock();    	    	
    }    

    public void addBox(float density, float x, float y) 
    {
    	this.lock.writeLock().lock();
        // Create Dynamic Body
        BodyDef bodyDef  = new BodyDef();
        // bodyDef.position.set((float)36.0+(count * 10), (float)24.0);        
        bodyDef.position.set(x, y);                
        Body newBody     = world.createBody(bodyDef);

        if (newBody != null) {
	        // Create Shape with Properties
	        boxShapeDef  = new PolygonDef();
	        boxShapeDef.setAsBox((float) 25.0, (float) 25.0);
	        boxShapeDef.density = density;
	        boxShapeDef.restitution = 0.2f;
	
	        // Assign shape to Body
	        newBody.createShape(boxShapeDef);
	        newBody.setMassFromShapes();
	        Log.v("Physic", "Mass:" + String.valueOf(newBody.getMass()));
	        newBody.m_userData = "Box";
	        newBody.applyImpulse(new Vec2(0, 2000000), newBody.getPosition());
	        
	    	bodies.add(newBody);
	        // Increase Counter
	    	count += 1;
        }
        
    	this.lock.writeLock().unlock();
    }

    /*
     * 
     * 
     * Physics Update
     */

    private Vec2 position;
    private float angle;

    public void update(float dt) 
    {    	       	
    	/*if(gameTime > this.mFrameTimer + this.timeStep ) {
            float dt = gameTime - this.mFrameTimer;
    		this.mFrameTimer = gameTime;            
                     
	        // Update Physics World
    		this.lock.writeLock().lock();
    		world.step(1f / 10, 2);		 
    		this.lock.writeLock().unlock();
    	}*/
    	
    	this.world.step(dt, 2);
    }
    
    public void draw(Canvas canvas) {
    	canvas.drawRect(0f, this.mHeight - 20, this.mWidth, this.mHeight, paint);
        
        // Print info of latest body
        this.lock.readLock().lock();
        ArrayList<Body> markedForDelete = new ArrayList<Body>();
        int deleteCount = 0;
        int count = 0;
        for (Body i : bodies) 
        {
            position = i.getPosition();
            angle    = i.getAngle(); 

            if (position.x < - 15 || position.y < -15)
            {
            	markedForDelete.add(i);
            	deleteCount++;
            }
            else
            {
            	if(i.m_userData == "Circle")
                    canvas.drawCircle(position.x, position.y, 50,paint );

                if(i.m_userData == "Box")
                {                    
                	canvas.save();
                	canvas.rotate((float) Math.toDegrees(angle), position.x, position.y);
                	canvas.drawRect((float)(position.x -25), (float)(position.y - 25), (float)(position.x + 25), (float)(position.y + 25), paint );
                	Paint txtPaint = new Paint(Color.BLACK);
                	canvas.drawText(String.valueOf(i.getMass()), position.x - 25, position.y, txtPaint);
                	canvas.restore();                	                	
                }
                	

                // Log.v("Physics Test", "Pos: (" + position.x + ", " + position.y + "), Angle: " + angle);
            }            
            count++;
        }                
        
        this.lock.readLock().unlock();
        if (deleteCount > 0) {
        	this.lock.writeLock().lock();
        	
        	for (Body i : markedForDelete) {
        		this.world.destroyBody(i);
        		this.bodies.remove(i);
        	}
        	
        	this.lock.writeLock().unlock();
        }
                
    }
}
