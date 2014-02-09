package edu.ncsu.csc563.velocity.actors.components;

import android.opengl.Matrix;
import edu.ncsu.csc563.velocity.GLES20InteractiveSurfaceView;

public class Controller extends Component {
	private Transform transform;
	
	public Controller(Transform transform) {
		this.transform = transform;
	}
	
	@Override
	public void update() {
		float model[] = this.transform.getModel();
		Matrix.translateM(model, 0, GLES20InteractiveSurfaceView.tilt/7, GLES20InteractiveSurfaceView.roll, 0);
		
		model[12] = Math.min(model[12], 13.0f);
		model[12] = Math.max(model[12], -13.0f);
		model[13] = Math.min(model[13], 7.5f);
		model[13] = Math.max(model[13], -7.5f);
	}
}
