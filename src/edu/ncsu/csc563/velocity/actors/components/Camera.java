package edu.ncsu.csc563.velocity.actors.components;

public class Camera extends Component {
	private Transform mTransform;
	private float[] mClearColor;
	private float[] mView;
	private float[] mProj;
	
	public Camera(Transform transform, float[] clearColor, float[] view, float[] proj) {
		this.mTransform = transform;		
		this.mClearColor = clearColor;
		this.mView = view;
		this.mProj = proj;
	}
	
	
	
	
}
