package edu.ncsu.csc563.velocity.actors.components;

import android.opengl.GLES20;
import android.util.Log;
import edu.ncsu.csc563.velocity.rendering.GLES20Shader;

public class Material extends Component {	
	private GLES20Shader mShader;
	private float[] mDiffuseColor;
	private int mTextureHandle;
	private float alphaVal;

	public Material(GLES20Shader shader)
	{
		this.mShader = shader;
		this.mDiffuseColor = new float[3];
		this.mTextureHandle = -1;
		this.alphaVal = 1.0f;
	}
	
	public Material(GLES20Shader shader, int textureHandle)
	{
		this.mShader = shader;
		this.mDiffuseColor = new float[3];
		this.mTextureHandle = textureHandle;
		this.alphaVal = 1.0f;
	}
	
	public float[] getDiffuseColor() {
		return this.mDiffuseColor;
	}
	
	public void setDiffuseColor(float r, float g, float b) {
		this.mDiffuseColor[0] = r;
		this.mDiffuseColor[1] = g;
		this.mDiffuseColor[2] = b;
	}
	
	public void setAlphaVal(float alphaVal) {
		this.alphaVal = alphaVal;
	}
	
	public GLES20Shader getShader() {
		return this.mShader;
	}
	
	public void draw() {
		this.mShader.use();
		if (this.mTextureHandle == -1) {
			this.mShader.setUniform("diffuseColor", this.mDiffuseColor);
		} else {
			 // Set the active texture unit to texture unit 0.
		    GLES20.glActiveTexture(GLES20.GL_TEXTURE0);
		 
		    // Bind the texture to this unit.
		    GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, mTextureHandle);
			
		    this.mShader.setUniform("shipTex", 0);
		    this.mShader.setUniform("alphaVal", this.alphaVal);
		}
	}
}
