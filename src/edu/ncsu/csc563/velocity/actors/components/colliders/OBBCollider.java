package edu.ncsu.csc563.velocity.actors.components.colliders;

import edu.ncsu.csc563.velocity.actors.components.Component;
import edu.ncsu.csc563.velocity.actors.components.Transform;

public class OBBCollider extends Component {
	private Transform mTransform;
	private float[] mHalfWidths;
	
	public OBBCollider(Transform transform, float[] halfWidths) {
		this.mTransform = transform;
		this.mHalfWidths = halfWidths;
	}
}
