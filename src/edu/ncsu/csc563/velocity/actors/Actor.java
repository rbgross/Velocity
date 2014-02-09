package edu.ncsu.csc563.velocity.actors;

import java.util.HashMap;

import android.opengl.Matrix;
import android.os.SystemClock;
import android.util.Log;

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
		
		//xAngle = GLES20InteractiveSurfaceView.yAngle;
		//zAngle = GLES20InteractiveSurfaceView.zAngle;
		
		//Matrix.translateM(model, 0, xAngle/10, zAngle/10, 0);
		
		Matrix.translateM(model, 0, GLES20InteractiveSurfaceView.tilt/10, GLES20InteractiveSurfaceView.roll, 0);
		
		model[12] = Math.min(model[12], 13.0f);
		model[12] = Math.max(model[12], -13.0f);
		model[13] = Math.min(model[13], 7.5f);
		model[13] = Math.max(model[13], -7.5f);
	}
	
	public void draw() {
		GLES20Shader shader = ((Material) this.getComponent("Material")).getShader();
		((Material) this.getComponent("Material")).draw();
		
		//Set the value of the "model" shader uniform variable on the graphics card
		shader.setUniform("model", ((Transform) this.getComponent("Transform")).getModel());
		
		((Mesh) this.getComponent("Mesh")).draw();
	}
}
