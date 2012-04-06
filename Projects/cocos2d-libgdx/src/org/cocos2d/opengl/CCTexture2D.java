package org.cocos2d.opengl;

import static com.badlogic.gdx.graphics.GL10.GL_CLAMP_TO_EDGE;
import static com.badlogic.gdx.graphics.GL10.GL_FLOAT;
import static com.badlogic.gdx.graphics.GL10.GL_LINEAR;
import static com.badlogic.gdx.graphics.GL10.GL_NEAREST;
import static com.badlogic.gdx.graphics.GL10.GL_REPEAT;
import static com.badlogic.gdx.graphics.GL10.GL_TEXTURE_2D;
import static com.badlogic.gdx.graphics.GL10.GL_TEXTURE_COORD_ARRAY;
import static com.badlogic.gdx.graphics.GL10.GL_TEXTURE_MAG_FILTER;
import static com.badlogic.gdx.graphics.GL10.GL_TEXTURE_MIN_FILTER;
import static com.badlogic.gdx.graphics.GL10.GL_TEXTURE_WRAP_S;
import static com.badlogic.gdx.graphics.GL10.GL_TEXTURE_WRAP_T;
import static com.badlogic.gdx.graphics.GL10.GL_TRIANGLE_STRIP;
import static com.badlogic.gdx.graphics.GL10.GL_VERTEX_ARRAY;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.IntBuffer;
import java.util.ArrayList;
import java.util.HashMap;

import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.GL11;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;

import org.cocos2d.nodes.CCLabel;
import org.cocos2d.opengl.GLResourceHelper.Resource;
import org.cocos2d.types.CCTexParams;
import org.cocos2d.types.CGPoint;
import org.cocos2d.types.CGRect;
import org.cocos2d.types.CGSize;
import org.cocos2d.utils.FastFloatBuffer;

/** CCTexture2D class.
 * This class allows easy creation of OpenGL 2D textures from images, text or raw data.
 * The created CCTexture2D object will always have power-of-two dimensions. 
 * Depending on how you create the CCTexture2D object, the actual image area of the texture
 * might be smaller than the texture dimensions i.e. "contentSize" != (pixelsWide, pixelsHigh)
 * and (maxS, maxT) != (1.0, 1.0).
 * Be aware that the content of the generated textures will be upside-down!
*/
public class CCTexture2D implements Resource {
    // private static final String LOG_TAG = CCTexture2D.class.getSimpleName();
	
	public static final int kMaxTextureSize = 1024;
    /**
     * width in pixels
     */
    public int pixelsWide() {
        return mWidth;
    }

    /**
     * height in pixels
     */
    public int pixelsHigh() {
        return mHeight;
    }

    /**
     * width in pixels
     */
    public float getWidth() {
        return mContentSize.width;
    }

    /**
     * height in pixels
     */
    public float getHeight() {
        return mContentSize.height;
    }

    /** texture name */
    public int name() {
    	
//        if( _name == 0 && CCDirector.gl != null && Thread.currentThread().getName().startsWith("GLThread"))
    	
//    	if( _name == 0) {	
//        	this.loadTexture(CCDirector.gl);
//        }
        return _name;
    }

    /** texture max S */
    public float maxS() {
        return _maxS;
    }

    /** texture max T */
    public float maxT() {
        return _maxT;
    }

    private boolean premultipliedAlpha = false;
    /** whether or not the texture has their Alpha premultiplied */
    public boolean hasPremultipliedAlpha() {
        return premultipliedAlpha;
    }
    
    private FastFloatBuffer mVertices;
    private FastFloatBuffer mCoordinates;
//    private ShortBuffer mIndices;

    /** texture name */
    private int _name = 0;

    /** content size */
    private CGSize mContentSize;

    /** width in pixels */
    private int mWidth;

    /** hight in pixels */
    private int mHeight;

    /** texture max S */
    private float _maxS;

    /** texture max T */
    private float _maxT;

    private CCTexParams _texParams;
    
    private Texture texture;
    
//    /** this object is responsible for loading Bitmap for texture */
//    private GLResourceHelper.GLResourceLoader mLoader;

    public final CGSize getContentSize() {
        return mContentSize;
    }

    public void releaseTexture (GL10 gl) {
        if (_name != 0) {
            gl.glDeleteTextures(1, new int[]{_name}, 0);
            _name = 0;
        }
    }
    
    @Override
    protected void finalize() throws Throwable {
    	if (_name != 0) {
    		GLResourceHelper.sharedHelper().perform(new GLResourceHelper.GLResorceTask() {
    			
				@Override
				public void perform(GL10 gl) {
					IntBuffer intBuffer = IntBuffer.allocate(1);
					intBuffer.put(0, _name);
					gl.glDeleteTextures(1, intBuffer);
				}
				
			});
    	}

        super.finalize();
    }

    public CCTexture2D() {        
    }
    
//    public void checkName() {
//    	if (mLoader != null && _name == 0)
//    		mLoader.load(this);
//    }
    
    public void setLoader(GLResourceHelper.GLResourceLoader loader) {
    	if(loader != null) {
    		loader.load(this);
    		
        	// we called load and should not add task
    		GLResourceHelper.sharedHelper().addLoader(this, loader, false);
    	}
//    	mLoader = loader;
    }
    
    /**
      Extensions to make it easy to create a CCTexture2D object from an image file.
      Note that RGBA type textures will have their alpha premultiplied - use the blending mode (GL_ONE, GL_ONE_MINUS_SRC_ALPHA).
      */
    /** Initializes a texture from a UIImage object */
    public void initWithImage(Pixmap image) {
    	this.texture = new Texture(image);
        mWidth = image.getWidth();
        mHeight = image.getHeight();
    }
    
    // No text method for now

    public void loadTexture(GL10 gl) {
        Texture.createGLHandle();        
    }

    public boolean isLoaded() {
        return texture != null;
    }

    /**
      Drawing extensions to make it easy to draw basic quads using a CCTexture2D object.
      These functions require GL_TEXTURE_2D and both GL_VERTEX_ARRAY and GL_TEXTURE_COORD_ARRAY
            client states to be enabled.
      */
    /** draws a texture at a given point */
    public void drawAtPoint(GL10 gl, CGPoint point) {
        gl.glEnable(GL_TEXTURE_2D);

        loadTexture(gl);

        float width = (float) mWidth * _maxS;
        float height = (float) mHeight * _maxT;

        float vertices[] = {point.x, point.y, 0.0f,
                width + point.x, point.y, 0.0f,
                point.x, height + point.y, 0.0f,
                width + point.x, height + point.y, 0.0f};

        mVertices.put(vertices);
        mVertices.position(0);

        float coordinates[] = {0.0f, _maxT,
                _maxS, _maxT,
                0.0f, 0.0f,
                _maxS, 0.0f};

        mCoordinates.put(coordinates);
        mCoordinates.position(0);

        gl.glEnableClientState(GL_VERTEX_ARRAY);
        gl.glEnableClientState(GL_TEXTURE_COORD_ARRAY);

        gl.glBindTexture(GL_TEXTURE_2D, _name);

        gl.glTexParameterf(GL_TEXTURE_2D, GL_TEXTURE_WRAP_S, GL_REPEAT);
        gl.glTexParameterf(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_REPEAT);

        gl.glVertexPointer(3, GL_FLOAT, 0, mVertices.bytes);
        gl.glTexCoordPointer(2, GL_FLOAT, 0, mCoordinates.bytes);
        gl.glDrawArrays(GL_TRIANGLE_STRIP, 0, 4);

        // Clear the vertex and color arrays
        gl.glDisableClientState(GL_VERTEX_ARRAY);
        gl.glDisableClientState(GL_TEXTURE_COORD_ARRAY);

        gl.glDisable(GL_TEXTURE_2D);
    }

    /** draws a texture inside a rect */
    public void drawInRect(GL10 gl, CGRect rect) {
        gl.glEnable(GL_TEXTURE_2D);

        loadTexture(gl);

        // float width = (float) mWidth * _maxS;
        // float height = (float) mHeight * _maxT;

	    float vertices[] = {
            rect.origin.x, rect.origin.y, /*0.0f,*/
			rect.origin.x + rect.size.width,	rect.origin.y,	/*0.0f,*/
			rect.origin.x, rect.origin.y + rect.size.height, /*0.0f,*/
			rect.origin.x + rect.size.width, rect.origin.y + rect.size.height, /*0.0f*/
        };

        mVertices.put(vertices);
        mVertices.position(0);

        float coordinates[] = {0.0f, _maxT,
                _maxS, _maxT,
                0.0f, 0.0f,
                _maxS, 0.0f};

        mCoordinates.put(coordinates);
        mCoordinates.position(0);

        gl.glEnableClientState(GL_VERTEX_ARRAY);
        gl.glEnableClientState(GL_TEXTURE_COORD_ARRAY);

        gl.glBindTexture(GL_TEXTURE_2D, _name);

        gl.glTexParameterf(GL_TEXTURE_2D, GL_TEXTURE_WRAP_S, GL_REPEAT);
        gl.glTexParameterf(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_REPEAT);

        gl.glVertexPointer(2, GL_FLOAT, 0, mVertices.bytes);
        gl.glTexCoordPointer(2, GL_FLOAT, 0, mCoordinates.bytes);
        gl.glDrawArrays(GL_TRIANGLE_STRIP, 0, 4);

        // Clear the vertex and color arrays
        gl.glDisableClientState(GL_VERTEX_ARRAY);
        gl.glDisableClientState(GL_TEXTURE_COORD_ARRAY);

        gl.glDisable(GL_TEXTURE_2D);
    }

    //
    // Use to apply MIN/MAG filter
    //

//    private static CCTexParams _gTexParams = new CCTexParams(GL_LINEAR, GL_LINEAR, GL_CLAMP_TO_EDGE, GL_CLAMP_TO_EDGE);
//    private static CCTexParams _texParamsCopy;

    /** sets the min filter, mag filter, wrap s and wrap t texture parameters.
      If the texture size is NPOT (non power of 2),
             then in can only use GL_CLAMP_TO_EDGE in GL_TEXTURE_WRAP_{S,T}.
      @since v0.8
    */
    public void setTexParameters(CCTexParams texParams) {
    	_texParams.set(texParams);
    }
    
    public void setTexParameters(int min, int mag, int s, int t) {
    	_texParams.set(min, mag, s, t);
    	if(_name != 0) {
    		GLResourceHelper.sharedHelper().perform(new GLResourceHelper.GLResorceTask() {
    			
				@Override
				public void perform(GL10 gl) {
					applyTexParameters(gl);
				}
    		});
    	}
    }

//    public static CCTexParams texParameters() {
//        return gTexParams;
//    }

    private void applyTexParameters(GL10 gl) {
        gl.glBindTexture(GL_TEXTURE_2D, _name);
        gl.glTexParameterf(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, _texParams.minFilter );
        gl.glTexParameterf(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, _texParams.magFilter);
        gl.glTexParameterf(GL_TEXTURE_2D, GL_TEXTURE_WRAP_S, _texParams.wrapS);
        gl.glTexParameterf(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, _texParams.wrapT);
    }

//    public static void restoreTexParameters() {
//        _gTexParams = _texParamsCopy;
//    }
//
//    public static void saveTexParameters() {
//        _texParamsCopy = _gTexParams.copy();
//    }


    /** sets alias texture parameters:
      - GL_TEXTURE_MIN_FILTER = GL_NEAREST
      - GL_TEXTURE_MAG_FILTER = GL_NEAREST

      @since v0.8
    */
    public void setAliasTexParameters() {
    	setTexParameters(GL_NEAREST, GL_NEAREST, GL_CLAMP_TO_EDGE, GL_CLAMP_TO_EDGE);
    }


    /** sets antialias texture parameters:
      - GL_TEXTURE_MIN_FILTER = GL_LINEAR
      - GL_TEXTURE_MAG_FILTER = GL_LINEAR

      @since v0.8
      */
    public void setAntiAliasTexParameters() {
    	setTexParameters(GL_LINEAR, GL_LINEAR, GL_CLAMP_TO_EDGE, GL_CLAMP_TO_EDGE);
    }
}

