package org.cocos2d.config;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;	

/**
 @file
 cocos2d helper macros
 */
public class ccMacros {

    /*
     * if COCOS2D_DEBUG is not defined, or if it is 0 then
     *	all CCLOGXXX macros will be disabled
     *
     * if COCOS2D_DEBUG==1 then:
     *		CCLOG() will be enabled
     *		CCLOGERROR() will be enabled
     *		CCLOGINFO()	will be disabled
     *
     * if COCOS2D_DEBUG==2 or higher then:
     *		CCLOG() will be enabled
     *		CCLOGERROR() will be enabled
     *		CCLOGINFO()	will be enabled 
     */
    public static final void CCLOG(final String logName, final String logStr) {
        if (ccConfig.COCOS2D_DEBUG >= 1) {
            Gdx.app.log(logName, logStr);
        }
    }

    public static final void CCLOGINFO(final String logName, final String logStr) {
        if (ccConfig.COCOS2D_DEBUG >= 1) {
        	Gdx.app.log(logName, logStr);
        }
    }

    public static final void CCLOGERROR(final String logName, final String logStr) {
        if (ccConfig.COCOS2D_DEBUG >= 2) {
        	Gdx.app.log(logName, logStr);
        }
    }

    public static final float FLT_EPSILON = 0.000001f;
    public static final int INT_MIN = -2147483648;
    public static final int CC_MAX_PARTICLE_SIZE = 64;
    
    /// java doesn't support swap primitive types.
    /// public static void CC_SWAP(T x, T y);

    /** @def CCRANDOM_MINUS1_1
      returns a random float between -1 and 1
    */
    public static final float CCRANDOM_MINUS1_1() {
        return (float) Math.random() * 2.0f - 1.0f;
    }

    /** @def CCRANDOM_0_1
      returns a random float between 0 and 1
    */
    public static final float CCRANDOM_0_1() {
        return (float) Math.random();
    }

    /** @def M_PI_2
            Math.PI divided by 2
    */
    public static final float M_PI_2 = (float)(Math.PI / 2);
    

    /** @def CC_DEGREES_TO_RADIANS
        converts degrees to radians
    */
    public static final float CC_DEGREES_TO_RADIANS(float angle) {
        return (angle / 180.0f * (float) Math.PI);
    }

    /** @def CC_RADIANS_TO_DEGREES
        converts radians to degrees
    */
    public static final float CC_RADIANS_TO_DEGREES(float angle) {
        return (angle / (float) Math.PI * 180.0f);
    }

    /** @def CC_ENABLE_DEFAULT_GL_STATES
      GL states that are enabled:
      - GL_TEXTURE_2D
      - GL_VERTEX_ARRAY
      - GL_TEXTURE_COORD_ARRAY
      - GL_COLOR_ARRAY
      */
    public static final void CC_ENABLE_DEFAULT_GL_STATES(GL10 gl) {
        gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
        gl.glEnableClientState(GL10.GL_COLOR_ARRAY);
        gl.glEnableClientState(GL10.GL_TEXTURE_COORD_ARRAY);
        gl.glEnable(GL10.GL_TEXTURE_2D);
    }

    /** @def CC_DISABLE_DEFAULT_GL_STATES 
      Disable default GL states:
      - GL_TEXTURE_2D
      - GL_VERTEX_ARRAY
      - GL_TEXTURE_COORD_ARRAY
      - GL_COLOR_ARRAY
      */
    public static final void CC_DISABLE_DEFAULT_GL_STATES(GL10 gl) {
        gl.glDisable(GL10.GL_TEXTURE_2D);
        gl.glDisableClientState(GL10.GL_COLOR_ARRAY);
        gl.glDisableClientState(GL10.GL_TEXTURE_COORD_ARRAY);
        gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
    }
    
    // Director init and end here

}

