package edu.ncsu.csc563.velocity.actors.components.colliders;

import android.opengl.Matrix;
import edu.ncsu.csc563.velocity.actors.components.Component;
import edu.ncsu.csc563.velocity.actors.components.Transform;

public class OBBCollider extends Component {
	private Transform mTransform;
	private float[] mHalfWidths;
	
	public OBBCollider(Transform transform, float[] halfWidths) {
		this.mTransform = transform;
		this.mHalfWidths = halfWidths;
	}
	
	/** Returns a float array, the first value being the min Z and the second value being the max Z */
	public float[] getZBounds() {
		float[] model = this.mTransform.getModel();
		float[] zBounds = new float[2];
		zBounds[0] = Float.MAX_VALUE;
		zBounds[1] = -Float.MAX_VALUE;
		for (int x = -1; x < 2; x += 2) {
			for (int y = -1; y < 2; y += 2) {
				for (int z = -1; z < 2; z += 2) {
					float[] result = new float[4];
					float[] point = {x * this.mHalfWidths[0], y * this.mHalfWidths[1], z * this.mHalfWidths[2], 1};
					Matrix.multiplyMV(result, 0, model, 0, point, 0);
					if (result[2] < zBounds[0]) {
						zBounds[0] = result[2];
					}
					if (result[2] > zBounds[1]) {
						zBounds[1] = result[2];
					}
				}
			}
		}
		return zBounds;
	}
	
	public float[] getHalfWidths() {
		return this.mHalfWidths;
	}
	
	public float[] getRotationMatrix() {
		float[] r = new float[9];
		r[0] = this.mTransform.getModel()[0];
		r[1] = this.mTransform.getModel()[4];
		r[2] = this.mTransform.getModel()[8];
		r[3] = this.mTransform.getModel()[1];
		r[4] = this.mTransform.getModel()[5];
		r[5] = this.mTransform.getModel()[9];
		r[6] = this.mTransform.getModel()[2];
		r[7] = this.mTransform.getModel()[6];
		r[8] = this.mTransform.getModel()[10];
		return r; 
	}
	
	public float[] getCenter() {
		return this.mTransform.getPosition();
	}
}
