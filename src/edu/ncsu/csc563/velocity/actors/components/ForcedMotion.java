package edu.ncsu.csc563.velocity.actors.components;

public class ForcedMotion extends Component {
	private Transform mTransform;
	private float mSpeed;
	
	public ForcedMotion(Transform transform) {
		this.mTransform = transform;
		this.mSpeed = 1.0f;
	}
	
	@Override
	public void update() {
		this.mTransform.translate(0, 0, this.mSpeed);
		if (this.mTransform.getPosition()[2] > 15) {
			this.mTransform.translate(0, 0, -200);
		}
	}
}
