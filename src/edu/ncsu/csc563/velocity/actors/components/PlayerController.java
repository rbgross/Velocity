package edu.ncsu.csc563.velocity.actors.components;

import edu.ncsu.csc563.velocity.GLES20InteractiveSurfaceView;

public class PlayerController extends Component {
	private Transform mTransform;
	
	public PlayerController(Transform transform) {
		this.mTransform = transform;
	}
	
	@Override
	public void update() {
		
		this.mTransform.translate(-GLES20InteractiveSurfaceView.tilt / 7, GLES20InteractiveSurfaceView.roll, 0);
		float[] tempPos = this.mTransform.getPosition();
		tempPos[0] = Math.min(tempPos[0], 6.5f);
		tempPos[0] = Math.max(tempPos[0], -6.5f);
		tempPos[1] = Math.min(tempPos[1], 3.9f);
		tempPos[1] = Math.max(tempPos[1], -3.9f);
		this.mTransform.setPosition(tempPos[0], tempPos[1], tempPos[2]);
	}
}
