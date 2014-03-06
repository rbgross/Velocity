package edu.ncsu.csc563.velocity.actors;

import java.util.ArrayList;

import edu.ncsu.csc563.velocity.actors.components.Transform;
import edu.ncsu.csc563.velocity.actors.components.colliders.OBBCollider;

public class Segment {
	private ArrayList<Actor> mActors;
	private ArrayList<Float> mDepths;
	private float zMin;
	private float zMax;
	private float gap;
	
	public Segment(float gap) {
		this.gap = gap;
		this.mActors = new ArrayList<Actor>();
		this.mDepths = new ArrayList<Float>();
		this.zMin = Float.MAX_VALUE;
		this.zMax = -Float.MAX_VALUE;
	}
	
	public void addActor(Actor actor) {
		float[] zBounds = ((OBBCollider) actor.getComponent("OBBCollider")).getZBounds();
		if (zBounds[0] < this.zMin) {
			this.zMin = zBounds[0];
		}
		if (zBounds[1] > this.zMax) {
			this.zMax = zBounds[1];
		}
		
		//Depth sort actors by their z min depth
		int i = 0;
		while (true) {
			if (i == this.mDepths.size()) {
				this.mDepths.add(zBounds[0]);
				this.mActors.add(actor);
				break;
			}
			if (zBounds[0] < this.mDepths.get(i)) {
				this.mDepths.add(i, zBounds[0]);
				this.mActors.add(i, actor);
				break;
			}
			i++;
		}
	}
	
	public ArrayList<Actor> getActors(float zDepth) {
		ArrayList<Actor> actors = new ArrayList<Actor>(this.mActors);
		float zPos = zDepth - this.zMin + this.gap;
		for (Actor actor : actors) {
			((Transform) actor.getComponent("Transform")).translate(0, 0, zPos);
		}
		return actors;
	}
}
