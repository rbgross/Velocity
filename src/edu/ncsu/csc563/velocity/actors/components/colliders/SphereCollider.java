package edu.ncsu.csc563.velocity.actors.components.colliders;

import edu.ncsu.csc563.velocity.actors.components.*;

public class SphereCollider extends Component {
	private Transform mTransform;
	private float mRadius;
	
	public SphereCollider(Transform transform, float radius) {
		this.mTransform = transform;
		this.mRadius = radius;
	}
}
