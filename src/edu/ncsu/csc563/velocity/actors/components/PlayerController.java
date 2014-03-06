package edu.ncsu.csc563.velocity.actors.components;

import android.opengl.Matrix;
import edu.ncsu.csc563.velocity.GLES20InteractiveSurfaceView;

public class PlayerController extends Component {
	private Transform mTransform;
	
	public PlayerController(Transform transform) {
		this.mTransform = transform;
	}
	
	@Override
	public void update() {
		float model[] = this.mTransform.getModel();
		Matrix.translateM(model, 0, -GLES20InteractiveSurfaceView.tilt / 7, GLES20InteractiveSurfaceView.roll, 0);
		
		model[12] = Math.min(model[12], 10.0f); // Modify this to be the aspect ratio x 6
		model[12] = Math.max(model[12], -10.0f); // Modify this to be the aspect ratio x 6
		model[13] = Math.min(model[13], 6.0f);
		model[13] = Math.max(model[13], -6.0f);
	}
}
