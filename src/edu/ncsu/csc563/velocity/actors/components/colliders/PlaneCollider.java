package edu.ncsu.csc563.velocity.actors.components.colliders;

import edu.ncsu.csc563.velocity.actors.components.*;

public class PlaneCollider extends Component {
	private Transform mTransform;
	private float[] mNormal;
	
	public PlaneCollider(Transform transform, float[] normal) {
		this.mTransform = transform;
		this.mNormal = normal;
	}

}
