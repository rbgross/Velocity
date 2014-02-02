package edu.ncsu.csc563.velocity.actors.components;

import edu.ncsu.csc563.velocity.rendering.GLES20Shader;

public class Material extends Component {	
	private GLES20Shader mShader;
	private float[] mDiffuseColor;

	public Material(GLES20Shader shader)
	{
		this.mShader = shader;
		this.mDiffuseColor = new float[3];
	}
	
	public float[] getDiffuseColor() {
		return this.mDiffuseColor;
	}
	
	public void setDiffuseColor(float r, float g, float b) {
		this.mDiffuseColor[0] = r;
		this.mDiffuseColor[1] = g;
		this.mDiffuseColor[2] = b;
	}
	
	public GLES20Shader getShader() {
		return this.mShader;
	}
	
	public void draw() {
		this.mShader.use();
		this.mShader.setUniform("diffuseColor", this.mDiffuseColor);
	}
}
