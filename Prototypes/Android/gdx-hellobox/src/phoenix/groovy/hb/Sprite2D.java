package phoenix.groovy.hb;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Sprite;
import com.badlogic.gdx.graphics.SpriteBatch;
import com.sun.opengl.impl.packrect.Rect;
 
public class Sprite2D {
 
    private Sprite mAnimation;
    private float mXPos;
    private float mYPos;    
    private int mFPS;
    private int mNoOfFrames;
    private int mCurrentFrame;
    private long mFrameTimer;
    private int mSpriteHeight;
    private int mSpriteWidth;
    private int mX;
    private int mY;
    public boolean IsRotating;
 
    public Sprite2D() {        
        mFrameTimer =0;
        mCurrentFrame =0;
        mXPos = 100;
        mYPos = Gdx.graphics.getHeight() - 100;
    }
 
    public void init(Sprite theSprite, int width, int height, int theFPS, int theFrameCount) {
        mAnimation = theSprite;
        mAnimation.setSize(width, height);        
        mSpriteHeight = height;
        mSpriteWidth = width;           
        mX = 0;
        mY = 0;
        mFPS = 1000 /theFPS;
        mNoOfFrames = theFrameCount;
        
        this.mAnimation.setTextureRegion(0, 0, mSpriteWidth, mSpriteHeight);
    }
 
    public void update(long gameTime) {
        if(gameTime > mFrameTimer + mFPS ) {
            mFrameTimer = gameTime;
            mCurrentFrame +=1;
 
            
            if (this.IsRotating) {
            	this.mAnimation.setTextureRegion(0, 0, mSpriteWidth, mSpriteHeight);
            	if(mCurrentFrame >= mNoOfFrames) {
            		mCurrentFrame = 0;
            	}
            }
            else
            {            
	            if(mCurrentFrame >= mNoOfFrames) {
	            	this.mAnimation.setTextureRegion(0, 0, mSpriteWidth, mSpriteHeight);
	            	mCurrentFrame = 0;
	            }
	            else
	            {
	            	this.mAnimation.setTextureRegion(mCurrentFrame * mSpriteWidth, 0, mSpriteWidth, mSpriteHeight);
	            }
            }
        }              
    }
 
    public void draw(SpriteBatch spriteBatch) {    	    	    	
    	this.mAnimation.setPosition(mXPos, mYPos);
    	this.mAnimation.setOrigin(mSpriteWidth / 2, mSpriteHeight / 2);
    	
    	if (this.IsRotating){
    		this.mAnimation.rotate(- (360 / this.mFPS));
    		this.mAnimation.draw(spriteBatch);
    	}    	
    	else
    	{    		    		    	
	        this.mAnimation.rotate(90.0f);        
	        this.mAnimation.draw(spriteBatch);
	        this.mAnimation.rotate(-90.0f);
    	}
    }
 
    public float getYPos()
    {
    	return mYPos;
    }
    public float getXPos()
    {
    	return mXPos;
    }
    
    public void setXPos(float newX)
    {
    	this.mXPos = newX; 
    }
    
    public void setYPos(float newY) {
    	this.mYPos = newY;
    }
}
