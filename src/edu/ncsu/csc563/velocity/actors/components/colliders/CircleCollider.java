package edu.ncsu.csc563.velocity.actors.components.colliders;

import android.opengl.Matrix;
import edu.ncsu.csc563.velocity.actors.components.*;

public class CircleCollider extends Component {
	private Transform mTransform;
	private float[] mCenter;
	private float mRadius;
	
	public CircleCollider(Transform transform, float[] center, float radius) {
		this.mTransform = transform;
		this.mCenter = center;
		this.mRadius = radius;
	}
	
	public float getRadius() {
		return this.mRadius;
	}
	
	public float[] getRotationMatrix() {
		float[] r = new float[9];
		
		r[0] = this.mTransform.getModel()[0];
		r[1] = this.mTransform.getModel()[1];
		r[2] = this.mTransform.getModel()[2];
		r[3] = this.mTransform.getModel()[4];
		r[4] = this.mTransform.getModel()[5];
		r[5] = this.mTransform.getModel()[6];
		r[6] = this.mTransform.getModel()[8];
		r[7] = this.mTransform.getModel()[9];
		r[8] = this.mTransform.getModel()[10];
		
		return r; 
	}
	
	public float[] getCenter() {
		float[] model = this.mTransform.getModel();
		float[] result = new float[4];
		float[] center = {this.mCenter[0], this.mCenter[1], this.mCenter[2], 1};
		Matrix.multiplyMV(result, 0, model, 0, center, 0);		
		return result;
	}
}
