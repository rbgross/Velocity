package edu.ncsu.csc563.velocity.actors;

import java.util.ArrayList;

import edu.ncsu.csc563.velocity.actors.components.Transform;
import edu.ncsu.csc563.velocity.actors.components.Collider;

public class Segment {
	private ArrayList<Actor> mObstacles;
	private ArrayList<Actor> mTokens;
	private ArrayList<Float> mDepths;
	private float zMin;
	private float zMax;
	private float gap;
	
	public Segment(float gap) {
		this.gap = gap;
		this.mObstacles = new ArrayList<Actor>();
		this.mTokens = new ArrayList<Actor>();
		this.mDepths = new ArrayList<Float>();
		this.zMin = Float.MAX_VALUE;
		this.zMax = -Float.MAX_VALUE;
	}
	
	public void addObstacle(Actor actor) {
		float[] zBounds = ((Collider) actor.getComponent("Collider")).getPrimaryCollider().getZBounds();
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
				this.mObstacles.add(actor);
				break;
			}
			if (zBounds[0] < this.mDepths.get(i)) {
				this.mDepths.add(i, zBounds[0]);
				this.mObstacles.add(i, actor);
				break;
			}
			i++;
		}
	}
	
	public void addToken(Actor actor) {		
		this.mTokens.add(actor);		
	}
	
	public ArrayList<Actor> getObstacles(float zDepth) {
		ArrayList<Actor> actors = new ArrayList<Actor>(this.mObstacles);
		float zPos = zDepth - this.zMin + this.gap;
		for (Actor actor : actors) {
			((Transform) actor.getComponent("Transform")).translate(0, 0, zPos);
		}
		return actors;
	}
	
	public ArrayList<Actor> getTokens(float zDepth) {
		ArrayList<Actor> actors = new ArrayList<Actor>(this.mTokens);
		float zPos = zDepth - this.zMin + this.gap;
		for (Actor actor : actors) {
			((Transform) actor.getComponent("Transform")).translate(0, 0, zPos);
		}
		return actors;
	}
}
