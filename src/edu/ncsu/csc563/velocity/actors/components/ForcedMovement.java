package edu.ncsu.csc563.velocity.actors.components;

public class ForcedMovement extends Component {
	private Transform mTransform;
	private float mSpeed;

	public ForcedMovement(Transform transform) {
		this.mTransform = transform;
		this.mSpeed = 0.5f;
	}
	
	@Override
	public void update() {
		this.mTransform.translate(0, 0, -this.mSpeed);
	}
}
