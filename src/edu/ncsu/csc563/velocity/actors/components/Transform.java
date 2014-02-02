package edu.ncsu.csc563.velocity.actors.components;

import android.opengl.Matrix;

public class Transform extends Component{
	private float[] mPosition;
	private float[] mRotation;
	private float[] mScale;
	private float[] mModel;
	private boolean mChanged;
	
	public Transform() {
		
		this.mPosition = new float[3];
		this.mRotation = new float[3];
		this.mScale = new float[3];
		this.mScale[0] = 1;
		this.mScale[1] = 1;
		this.mScale[2] = 1;
		this.mModel = new float[16];
		Matrix.setIdentityM(this.mModel, 0);
		this.mChanged = false;
	}
	
	public float[] getPosition() {
		return this.mPosition;		
	}
	
	public void setPosition(float x, float y, float z) {
		this.mPosition[0] = x;
		this.mPosition[1] = y;
		this.mPosition[2] = z;
		this.mChanged = true;		
	}
	
	public float[] getRotation() {
		return this.mRotation;
	}
	
	public void setRotation(float x, float y, float z) {
		this.mRotation[0] = x;
		this.mRotation[1] = y;
		this.mRotation[2] = z;
		this.mChanged = true;		
	}
	
	public float[] getScale() {
		return this.mScale;
	}
	
	public void setScale(float x, float y, float z) {
		this.mScale[0] = x;
		this.mScale[1] = y;
		this.mScale[2] = z;
	}
	
	public float[] getModel() {
		if (this.mChanged) {
			Matrix.setIdentityM(this.mModel, 0);
			Matrix.scaleM(this.mModel, 0, this.mScale[0], this.mScale[1], this.mScale[2]);
			Matrix.translateM(this.mModel, 0, this.mPosition[0], this.mPosition[1], this.mPosition[2]);
			float[] tempRot = new float[16];
			float[] tempModel = this.mModel;
			Matrix.setRotateEulerM(tempRot, 0, this.mRotation[0], this.mRotation[1], this.mRotation[2]);
			Matrix.multiplyMM(this.mModel, 0, tempRot, 0, tempModel, 0);
			this.mChanged = false;
		}
		
		return this.mModel;
	}
	
	public void setModel(float[] model) {
		this.mModel = model;
		this.mChanged = false;
	}
}
