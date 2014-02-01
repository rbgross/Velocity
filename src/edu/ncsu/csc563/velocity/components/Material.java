package edu.ncsu.csc563.velocity.components;

import edu.ncsu.csc563.velocity.rendering.GLES20Shader;

import android.opengl.GLES20;

public class Material extends Component {	
	private float[] mDiffuseColor;

	public Material(GLES20Shader shader, float[] diffuseColor)
	{
		this.mDiffuseColor = diffuseColor;
	}
}
