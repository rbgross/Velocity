package edu.ncsu.csc563.velocity.actors.components;

import java.util.ArrayList;

import edu.ncsu.csc563.velocity.actors.components.colliders.CircleCollider;
import edu.ncsu.csc563.velocity.actors.components.colliders.OBBCollider;
import edu.ncsu.csc563.velocity.actors.components.colliders.SphereCollider;

public class Collider extends Component {
	private OBBCollider mPrimaryCollider;
	private ArrayList<OBBCollider> mOBBColliders;
	private ArrayList<SphereCollider> mSphereColliders;
	private ArrayList<CircleCollider> mCircleColliders;
	
	public Collider() {
		this.mOBBColliders = new ArrayList<OBBCollider>();
		this.mSphereColliders = new ArrayList<SphereCollider>();
		this.mCircleColliders = new ArrayList<CircleCollider>();
	}
	
	public void setPrimaryCollider(OBBCollider collider) {
		this.mPrimaryCollider = collider;
	}
	
	public OBBCollider getPrimaryCollider() {
		return this.mPrimaryCollider;
	}
	
	public void addOBBCollider(OBBCollider collider) {
		this.mOBBColliders.add(collider);
	}
	
	public void addSphereCollider(SphereCollider collider) {
		this.mSphereColliders.add(collider);
	}
	
	public void addCircleCollider(CircleCollider collider) {
		this.mCircleColliders.add(collider);
	}
	
	public ArrayList<OBBCollider> getOBBColliders() {
		return this.mOBBColliders;
	}
	
	public ArrayList<SphereCollider> getSphereColliders() {
		return this.mSphereColliders;
	}
}
