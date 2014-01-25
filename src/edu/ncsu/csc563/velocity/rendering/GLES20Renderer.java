package edu.ncsu.csc563.velocity.rendering;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import edu.ncsu.csc563.velocity.models.Cube;

import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import android.opengl.Matrix;

public class GLES20Renderer implements GLSurfaceView.Renderer {

	private GLES20Shader activeShader;
	
	private Cube cube;
	
	@Override
	public void onSurfaceCreated(GL10 gl, EGLConfig config) {
		this.activeShader = GLES20ShaderFactory.basic();
		this.activeShader.use();
		
		GLES20.glEnable(GLES20.GL_DEPTH_TEST);
		GLES20.glEnable(GLES20.GL_CULL_FACE);
		GLES20.glClearColor(0.15f, 0.15f, 0.15f, 1.0f);
		
		this.cube = new Cube(this.activeShader);
		
		float view[] = new float[16];
		float lightPos[] = {1.0f, 1.0f, -1.0f, 0.0f};
		
		Matrix.setLookAtM(view, 0, 2.5f, 2.5f, 2.5f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f);
		this.activeShader.setUniform("view", view);
		
		this.activeShader.setUniform("lightPosition", lightPos);
	}
	
	@Override
	public void onSurfaceChanged(GL10 unused, int width, int height) {
		// Adjust the viewport based on geometry changes, such as screen rotation
        GLES20.glViewport(0, 0, width, height);
        
        float proj[] = new float[16];
        float aspect = 1.0f * width / height;        
        Matrix.perspectiveM(proj, 0, 45, aspect, 1.0f, 100.0f);
        this.activeShader.setUniform("proj", proj);
	}
	
	@Override
	public void onDrawFrame(GL10 unused) {
		GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT | GLES20.GL_DEPTH_BUFFER_BIT);
		
		this.cube.draw();
	}
}
