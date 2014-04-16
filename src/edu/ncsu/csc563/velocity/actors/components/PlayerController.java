package edu.ncsu.csc563.velocity.actors.components;

import android.os.SystemClock;
import edu.ncsu.csc563.velocity.GLES20InteractiveSurfaceView;

public class PlayerController extends Component {
	private Transform mTransform;
	private boolean invul;
	private long invulStartTime;
	private Material mMaterial;
	
	public static final long INVUL_DURATION = 3000;
	
	public PlayerController(Transform transform, Material material) {
		this.mTransform = transform;
		this.mMaterial = material;
	}
	
	public void enableInvul() {
		this.invul = true;
		this.invulStartTime = SystemClock.elapsedRealtime();
		this.mMaterial.setAlphaVal(0.5f);
	}
	
	public boolean getInvul() {
		return this.invul;
	}
	
	@Override
	public void update() {		
		this.mTransform.translate(-GLES20InteractiveSurfaceView.tilt / 14, GLES20InteractiveSurfaceView.roll / 2, 0);
		float[] tempPos = this.mTransform.getPosition();
		tempPos[0] = Math.min(tempPos[0], 5.5f);
		tempPos[0] = Math.max(tempPos[0], -5.5f);
		tempPos[1] = Math.min(tempPos[1], 3.4f);
		tempPos[1] = Math.max(tempPos[1], -3.4f);
		//tempPos[0] = Math.min(tempPos[0], 10.0f);
		//tempPos[0] = Math.max(tempPos[0], -10.0f);
		//tempPos[1] = Math.min(tempPos[1], 10.0f);
		//tempPos[1] = Math.max(tempPos[1], -10.0f);
		this.mTransform.setPosition(tempPos[0], tempPos[1], tempPos[2]);
		
		if (this.invul && SystemClock.elapsedRealtime() - this.invulStartTime > INVUL_DURATION) {
			this.invul = false;
			this.mMaterial.setAlphaVal(1.0f);
		}
	}
}
