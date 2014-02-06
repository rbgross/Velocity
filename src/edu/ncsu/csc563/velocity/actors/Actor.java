package edu.ncsu.csc563.velocity.actors;

import java.util.HashMap;

import android.opengl.Matrix;
import android.os.SystemClock;

import edu.ncsu.csc563.velocity.GLES20InteractiveSurfaceView;
import edu.ncsu.csc563.velocity.actors.components.*;
import edu.ncsu.csc563.velocity.rendering.GLES20Shader;

public class Actor {
	private HashMap<String, Component> mComponents;
	
	private float xAngle;
	private float zAngle;
	
	public Actor() {
		this.mComponents = new HashMap<String, Component>();
	}
	
	public void addComponent(String componentName, Component component) {
		this.mComponents.put(componentName, component);
	}
	
	public Component getComponent(String componentName) {
		return this.mComponents.get(componentName);
	}
	
	public void update() {
		//Temporarily hardcoded in; should be its own component
		//float model[] = new float[16];
		//Matrix.setIdentityM(model, 0);		
		//long time = SystemClock.uptimeMillis() % 4000L;
		//float angle = 0.090f * ((int)time);
		//Matrix.setRotateM(model, 0, angle, 0, 1, 0);
		//((Transform) this.getComponent("Transform")).setModel(model);
		
		float model[] = ((Transform) this.getComponent("Transform")).getModel();
		if (GLES20InteractiveSurfaceView.shouldReset) {
			GLES20InteractiveSurfaceView.shouldReset = false;
			Matrix.setIdentityM(model, 0);
		}
		
		xAngle = GLES20InteractiveSurfaceView.yAngle;
		zAngle = GLES20InteractiveSurfaceView.zAngle;
		
		Matrix.translateM(model, 0, xAngle/20, zAngle/20, 0);
	}
	
	public void draw() {
		GLES20Shader shader = ((Material) this.getComponent("Material")).getShader();
		((Material) this.getComponent("Material")).draw();
		
		//Set the value of the "model" shader uniform variable on the graphics card
		shader.setUniform("model", ((Transform) this.getComponent("Transform")).getModel());
		
		((Mesh) this.getComponent("Mesh")).draw();
	}
}
