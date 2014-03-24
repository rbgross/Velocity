package edu.ncsu.csc563.velocity.actors.components;

public class ForcedMovement extends Component {
	private Transform mTransform;
	public static float mSpeed = 0.2f;

	public ForcedMovement(Transform transform) {
		this.mTransform = transform;
	}
	
	@Override
	public void update() {
		this.mTransform.translate(0, 0, -ForcedMovement.mSpeed);
	}
}
