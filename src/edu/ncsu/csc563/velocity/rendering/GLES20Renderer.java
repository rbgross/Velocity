package edu.ncsu.csc563.velocity.rendering;

import java.io.IOException;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import edu.ncsu.csc563.velocity.models.Dragon;
import edu.ncsu.csc563.velocity.utility.ResourceManager;

import android.content.Context;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import android.opengl.Matrix;
import android.util.Log;

/**
 * Renderer for OpenGL ES 2.0
 */
public class GLES20Renderer implements GLSurfaceView.Renderer {
	/** Reference to this activity's context */
	private Context context;
	
	/** Reference to the current shader state on the graphics card */
	private GLES20Shader mActiveShader;
	
	/** Object to be rendered by this class */
	private Dragon dragon;
	
	public GLES20Renderer(Context context) {
		this.context = context;
	}
	
	@Override
	public void onSurfaceCreated(GL10 gl, EGLConfig config) {
		//Create a basic shader and activate it
		this.mActiveShader = GLES20ShaderFactory.diffuseSpecular();
		this.mActiveShader.use();
		
		//Enable GL states to perform a depth test and cull back facing polygons
		GLES20.glEnable(GLES20.GL_DEPTH_TEST);
		GLES20.glEnable(GLES20.GL_CULL_FACE);
		
		//Set the default color of the background each time a new frame is drawn
		GLES20.glClearColor(0.3f, 0.3f, 0.3f, 1.0f);
		
		String modelName = "DragonSmall.vmf";
		try {
			ResourceManager.loadMesh(modelName, this.context.getAssets().open(modelName));
		} catch (IOException e) {
			Log.e("MainActivity", "The file " + modelName + " could not be found.");
		}
		
		//Create a cube to be rendered
		this.dragon = new Dragon(this.mActiveShader, "DragonSmall.vmf");
		
		//Calculate the value for a view matrix and store that value for this
		//shader on the graphics card
		float view[] = new float[16];		
		Matrix.setLookAtM(view, 0, 2.5f, 2.5f, 2.5f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f);
		this.mActiveShader.setUniform("view", view);
		
		//Store the value for the light position on the graphics card
		float lightPos[] = {1.0f, 1.0f, -1.0f, 0.0f};
		this.mActiveShader.setUniform("lightPosition", lightPos);
	}
	
	@Override
	public void onSurfaceChanged(GL10 unused, int width, int height) {        
        //Calculate the value for a projection matrix and store the value for it on 
        //the graphics card
        float proj[] = new float[16];
        float aspect = 1.0f * width / height;        
        Matrix.perspectiveM(proj, 0, 45, aspect, 1.0f, 100.0f);
        this.mActiveShader.setUniform("proj", proj);
	}
	
	@Override
	public void onDrawFrame(GL10 unused) {
		//Reset the values of the color and depth bits in the default frame buffer to what
		//we specified them as (the default for the depth buffer bit is essentially the back of
		//your clipping plane specified in the projection matrix, so there's no need to set that
		//value explicitly in most situations)
		GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT | GLES20.GL_DEPTH_BUFFER_BIT);
		
		//Draw the cube
		this.dragon.draw();
	}
}
