/*
 * Copyright 2010 Mario Zechner (contact@badlogicgames.com), Nathan Sweet (admin@esotericsoftware.com)
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the
 * License. You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS"
 * BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */

package phoenix.groovy.hb;

import java.util.ArrayList;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Files.FileType;
import com.badlogic.gdx.Input.Peripheral;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Mesh;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.VertexAttribute;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.Texture.TextureWrap;
import com.badlogic.gdx.graphics.VertexAttributes.Usage;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.physics.box2d.MassData;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.Shape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Music;


public class HelloWorld implements ApplicationListener, InputProcessor, ContactListener {	
	private SpriteBatch spriteBatch;
	private Sprite sprite;
	private Sprite spriteVortex;
	private Sprite2D spriteAnim;
	private Sprite2D vortexAnim;
	private Texture texture;
	private Texture vortexTexture;
	private BitmapFont font;
	private Vector2 textPosition; 
	
	private World world;
	private Body box;
	private Body box2;
	
	private ArrayList<BoxV> boxVList;
	
	private long lastFrameTimer;
	private long mTimer;
	private int frameCount;
	private int lastFPS;
	
	private float targetFPS = 120.0f;
	private float dt = 1000f / targetFPS;
	private float accumulator = 0.0f;
	
	private float sqareSize = 16;	
	private int initSquare = 10;
	private float gravityRatio = 1.0f;
	private int boxPerWidth = 10;
	
	private Music backMusic;
	private Sound bip;	
	
	private int contactCount;
	private int contactMax = 100;
	
	private Vector2 gravityVector;
	
	private boolean isMagnetOn;
	private boolean isBurstOn;
	private float lastX;
	private float lastY;
	
	private long lastClick;
	
	public static float box2DRatio = 16.0f;
	
	@Override public void dispose () {

	}

	@Override public void render () {
		int centerX = Gdx.graphics.getWidth() / 2;
		int centerY = Gdx.graphics.getHeight() / 2;

		Gdx.graphics.getGL10().glClear(GL10.GL_COLOR_BUFFER_BIT);
		
		// 6 - 2
		int velocityIterations = 6;
		int positionIterations = 2;
		
		long newTime = System.currentTimeMillis();
		long frameTime = newTime - this.mTimer;
		this.mTimer = newTime;
		
		accumulator += frameTime;
		
		int loopCount = 0;
		if (Gdx.input.isPeripheralAvailable(Peripheral.Accelerometer)) {
			this.gravityVector = new Vector2(- Gdx.input.getAccelerometerX() * gravityRatio, - Gdx.input.getAccelerometerY() * gravityRatio);
			this.world.setGravity(this.gravityVector);
		}
		
		// this.AddForces();
		if (this.isMagnetOn) {
			this.magnet(this.lastX / HelloWorld.box2DRatio, this.lastY / HelloWorld.box2DRatio);
			this.vortexAnim.update(this.mTimer);			
		}
		
		if (this.isBurstOn) {
			//this.burst();
		}
		
		while (accumulator >= dt && loopCount < 4) {																
			this.world.step(1.0f / this.targetFPS, velocityIterations, positionIterations);
			accumulator -= dt;
			loopCount++;
		}
		
		this.spriteBatch.begin();				
		
		if (this.isMagnetOn) {
			this.vortexAnim.setXPos(this.lastX - 64);
			this.vortexAnim.setYPos(this.lastY - 64);
			this.vortexAnim.draw(spriteBatch);
		}
		
		for (BoxV b : this.boxVList) {			
			b.draw(this.spriteBatch);
		}			
		
		// spriteBatch.setBlendFunction(GL10.GL_ONE, GL10.GL_ONE_MINUS_SRC_ALPHA);
		font.draw(spriteBatch, "FPS: " + String.valueOf(this.lastFPS), (int)textPosition.x, (int)textPosition.y);
		font.draw(spriteBatch, "Objects: " + String.valueOf(this.boxVList.size()), (int)textPosition.x, (int)textPosition.y - 30);
		font.draw(spriteBatch, "X: " + String.valueOf(Gdx.input.getAccelerometerX()), (int)textPosition.x, (int)textPosition.y - 60);
		font.draw(spriteBatch, "Y: " + String.valueOf(Gdx.input.getAccelerometerY()), (int)textPosition.x, (int)textPosition.y - 90);
		font.draw(spriteBatch, "Z: " + String.valueOf(Gdx.input.getAccelerometerZ()), (int)textPosition.x, (int)textPosition.y - 120);
		//font.draw(spriteBatch, "Contacts: " + String.valueOf(this.contactCount), (int)textPosition.x, (int)textPosition.y - 150);
		
		this.spriteAnim.update(this.mTimer);
		this.spriteAnim.draw(this.spriteBatch);
		
		this.spriteBatch.end();			
		
		if (this.mTimer - this.lastFrameTimer > 1000) {
			this.lastFPS = this.frameCount;
			this.frameCount = 0;
			this.lastFrameTimer = this.mTimer;
		}
		
		this.frameCount++;
	}

	@Override public void resize (int width, int height) {
		/*spriteBatch.getProjectionMatrix().setToOrtho2D(0, 0, width, height);
		textPosition.set(0,0);*/
	}

	@Override public void create () {		
		Gdx.input.setInputProcessor(this);
		
		this.mTimer = System.currentTimeMillis();
		this.lastFrameTimer = this.mTimer;
		this.frameCount = 0;		
		
		// Music
		this.backMusic = Gdx.audio.newMusic(Gdx.files.getFileHandle("data/mus.mp3", FileType.Internal));
		this.backMusic.setLooping(true);
		this.backMusic.play();
		this.bip = Gdx.audio.newSound(Gdx.files.getFileHandle("data/predmet.mp3", FileType.Internal));			
		
		// Font
		font = new BitmapFont();
		font.setColor(Color.RED);
		this.textPosition = new Vector2(0, Gdx.graphics.getHeight());
		
		// Boxes
		this.sqareSize = Gdx.graphics.getWidth() / this.boxPerWidth / 2;
		HelloWorld.box2DRatio = this.sqareSize;
		this.initSquare = this.boxPerWidth;
		
		this.boxVList = new ArrayList<BoxV>();
		texture = new Texture(
				Gdx.files.getFileHandle("data/boxV.png", FileType.Internal));
//		texture.setFilter(TextureFilter.MipMap, TextureFilter.Linear);
//		texture.setWrap(TextureWrap.ClampToEdge, TextureWrap.ClampToEdge);
		this.sprite = new Sprite(texture);
		this.sprite.setSize(this.sqareSize * 2, this.sqareSize * 2);
		this.sprite.setOrigin(this.sprite.getWidth() / 2, this.sprite.getHeight() / 2);
		
		// Sprite anim
		texture = new Texture(
				Gdx.files.getFileHandle("data/arrow.png", FileType.Internal));
//		texture.setFilter(TextureFilter.MipMap, TextureFilter.Linear);
//		texture.setWrap(TextureWrap.ClampToEdge, TextureWrap.ClampToEdge);
		
		Sprite sa = new Sprite(texture);
		sa.setOrigin(sa.getWidth() / 2, sa.getHeight() / 2);		
		this.spriteAnim = new Sprite2D();
		this.spriteAnim.init(sa, 60, 100, 6, 6);
		
		// Vortex anim
		this.vortexTexture = new Texture(Gdx.files.getFileHandle("data/vortex.png", FileType.Internal));		
//		texture.setFilter(TextureFilter.MipMap, TextureFilter.Linear);
//		texture.setWrap(TextureWrap.ClampToEdge, TextureWrap.ClampToEdge);

		Sprite vo = new Sprite(this.vortexTexture);
		vo.setOrigin(0, 0);
		this.vortexAnim = new Sprite2D();
		this.vortexAnim.init(vo, 128, 128, 15, 1);
		this.vortexAnim.IsRotating = true;
		
		// SpriteBatch
		this.spriteBatch = new SpriteBatch();		
		
		// Create physics
		this.gravityVector = new Vector2(0.0f, -10.0f * gravityRatio);
		boolean doSleep = true;
		
		this.world = new World(this.gravityVector, doSleep);				
		this.world.setContactListener(this);
		
		this.CreateBoxAround();
		
		int countV = this.initSquare;
		for(int i =0; i < countV; i++) {
			float tX = (Gdx.graphics.getWidth() / 2 + (3 * i)) / HelloWorld.box2DRatio;
			float tY = (200 + (this.sqareSize * 2 + 10) * i) / HelloWorld.box2DRatio;
			Body bodyBox = this.createBox((this.sqareSize) / HelloWorld.box2DRatio, tX , tY);
			BoxV box = new BoxV(this.sprite, bodyBox);
			this.boxVList.add(box);
		}
	}
	
	private void AddForces() {
		for(BoxV b : this.boxVList) {
			//if (!b.getForceApplied()) {
				this.AddForce(b.getBody());
				//b.forceApplyCount++;
			//}
		}
	}
	
	private void AddForce(Body body) {
		Vector2 force = this.gravityVector.mul(10.0f);
		body.applyForce(force, body.getPosition());		 
		// body.applyLinearImpulse(force, body.getPosition());
	}
	
	private void CreateBoxAround() {
		float w = Gdx.graphics.getWidth() / HelloWorld.box2DRatio;
		float h = Gdx.graphics.getHeight() / HelloWorld.box2DRatio;
		float w2 = w / 2;
		float h2 = h / 2;
		float size = 1;
		
		// top
		this.createGroundBody(w2, size, w2, h + size);
		// right
		this.createGroundBody(size, h2 + size, w + size, h2);
		// Bottom
		this.createGroundBody(w2, size, w2, -size);
		// Left
		this.createGroundBody(size, h2 + size, -size, h2);		
	}
	
	private Body createBox(float size, float x, float y) {
		BodyDef boxDef = new BodyDef();
		boxDef.type = BodyType.DynamicBody;
		boxDef.position.set(x, y);		
		Body box = this.world.createBody(boxDef);
		
		PolygonShape dynamicBox = new PolygonShape();				
		dynamicBox.setAsBox(size, size);
		
		FixtureDef fixtureDef = new FixtureDef();
		fixtureDef.shape = dynamicBox;
		fixtureDef.density = 0.5f;
		fixtureDef.friction = 0.1f;
		fixtureDef.restitution = 0.8f;		

		box.createFixture(fixtureDef);
		
		/*MassData mass = box.getMassData();
		mass.mass = 1.0f;
		box.setMassData(mass);*/
		
		//this.AddForce(box);
		
		return box;
	}
	
	private Body createGroundBody(float width, float height, float x, float y) {
		BodyDef groundBodyDef = new BodyDef();
		groundBodyDef.type = BodyType.StaticBody;
		groundBodyDef.position.set(new Vector2(x, y));		
		Body groundBody = this.world.createBody(groundBodyDef);
		
		PolygonShape groundBox = new PolygonShape();
		groundBox.setAsBox(width, height);
		
		FixtureDef groundFixture = new FixtureDef();
		groundFixture.shape = groundBox;		
		
		groundBody.createFixture(groundFixture);
		
		return groundBody;
	}
	
	private void magnet(float x, float y) {
		for(BoxV b : this.boxVList) {			
			Vector2 pos = b.getPosition();
			
			Vector2 force = new Vector2(
					x - pos.x,
					y - pos.y);
			force.nor().mul(2.0f);
			b.getBody().applyLinearImpulse(force, b.getPosition());
			// .getBody().applyForce(force.mul(5.0f), b.getPosition());
		}
	}
	
	private void burst() {
		for(BoxV b : this.boxVList) {
			// b.getBody().applyForce(this.gravityVector.mul(-1.0f), b.getPosition());
			b.getBody().applyLinearImpulse(this.gravityVector.mul(-1.0f), b.getPosition());
			// b.getBody().setLinearVelocity(this.gravityVector.mul(1.0f));
		}
	}

	@Override
	public void pause() {
		
	}

	@Override
	public void resume() {
		
	}

	@Override
	public boolean keyDown(int arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyTyped(char arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyUp(int arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDragged(int arg0, int arg1, int arg2) {
		// this.explosion(arg0, Gdx.graphics.getHeight() - arg1);
		if (arg2 == 0) {
			this.lastX = arg0;
			this.lastY = Gdx.graphics.getHeight() - arg1;
		}
		
		return true;
	}

	@Override
	public void beginContact(Contact arg0) {
		
		if (this.contactCount == this.contactMax) {
			this.contactCount = 0;
		}
		
		if (arg0.getFixtureA() != null && arg0.getFixtureB() != null) {
			this.contactCount++;
		}
	}

	@Override
	public void endContact(Contact arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void postSolve(Contact arg0, ContactImpulse arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void preSolve(Contact arg0, Manifold arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean scrolled(int arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int arg0, int arg1, int arg2, int arg3) {
		if (arg2 == 0) {
			this.isBurstOn = true;		
			this.isMagnetOn = true;
			this.lastX = arg0;
			this.lastY = Gdx.graphics.getHeight() - arg1;
			this.lastClick = System.currentTimeMillis();			
		}
		
		return false;
	}

	@Override
	public boolean touchMoved(int arg0, int arg1) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchUp(int arg0, int arg1, int arg2, int arg3) {
		if (this.boxVList.size() < 50) {
			if ((System.currentTimeMillis() - this.lastClick < 500) || arg2 != 0) {
				this.bip.play();				
				Body bodyBox = this.createBox((this.sqareSize) / HelloWorld.box2DRatio, arg0 / HelloWorld.box2DRatio , (Gdx.graphics.getHeight() - arg1) / HelloWorld.box2DRatio);
				BoxV box = new BoxV(this.sprite, bodyBox);
				this.boxVList.add(box);
			}
		}
		
		if (arg2 == 0) {
			this.isMagnetOn = false;
			this.isBurstOn = false;
		}
		
		return true;
	}
}
