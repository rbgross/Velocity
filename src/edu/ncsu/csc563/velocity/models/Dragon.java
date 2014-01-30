package edu.ncsu.csc563.velocity.models;

import edu.ncsu.csc563.velocity.ResourceManager;
import edu.ncsu.csc563.velocity.components.Mesh;
import edu.ncsu.csc563.velocity.rendering.GLES20Shader;

import android.opengl.Matrix;
import android.os.SystemClock;

public class Dragon {	
	private GLES20Shader mShader;
	private Mesh mMesh;
	
	public Dragon(GLES20Shader shader, String modelName) {
		this.mShader = shader;
		this.mMesh = ResourceManager.mMeshes.get(modelName);
	}
	
	public void draw() {
		this.mShader.use();
		
		//Calculate the model matrix for this particular cube in this moment of time
		//It spins CCW around its Y axis as a function of time
		float model[] = new float[16];
		Matrix.setIdentityM(model, 0);		
		long time = SystemClock.uptimeMillis() % 4000L;
		float angle = 0.090f * ((int)time);
		Matrix.setRotateM(model, 0, angle, 0, 1, 0);
		
		//Set the value of the "model" shader uniform variable on the graphics card
		this.mShader.setUniform("model", model);
		
		//Set the value of the "diffuseColor" shader uniform variable on the graphics card
		float diffuseColor[] = {0.5f, 1.0f, 0.0f};
		this.mShader.setUniform("diffuseColor", diffuseColor);
		
		this.mMesh.draw();
	}
}
