package edu.ncsu.csc563.velocity.actors;

import java.util.LinkedList;

import android.util.Log;

import edu.ncsu.csc563.velocity.actors.components.colliders.OBBCollider;

public class Scene {
	private Actor mPlayer;
	private LinkedList<Actor> mActors;
	public static Scene instance;
	private static boolean paused = false;
	
	private Scene() {		
		this.mPlayer = ActorFactory.ship();
		this.mActors = new LinkedList<Actor>();
		
		this.mActors.addAll(SegmentFactory.pillarSegment(100));
	}
	
	public static Scene getInstance() {
		if (instance == null) {
			instance = new Scene();
		}
		return instance;
	}
	
	public void updateScene() {
		if (!paused) {
			this.mPlayer.update();
			
			for (Actor actor : this.mActors) {
				actor.update();
			}
			
			//Remove actors who's backs have moved past the camera
			float zMin = ((OBBCollider) this.mActors.getFirst().getComponent("OBBCollider")).getZBounds()[1];
			while (zMin < -15 ) {
				this.mActors.remove();
				zMin = ((OBBCollider) this.mActors.getFirst().getComponent("OBBCollider")).getZBounds()[1];
			}
			
			//Check if the max depth is too high; if so, add a new segment
			float zMax = ((OBBCollider) this.mActors.getLast().getComponent("OBBCollider")).getZBounds()[1];
			if (zMax < 100) {
				this.mActors.addAll(SegmentFactory.pillarSegment(zMax));
			}	
			
			Log.e("numActors", "" + this.mActors.size());
		}	
	}
	
	public void drawScene() {
		this.mPlayer.draw();
		
		for (Actor actor : this.mActors) {
			actor.draw();
		}
	}
	
	public static boolean isPaused() {
		return paused;
	}
	
	public static void pause() {
		paused = true;
	}
	
	public static void unPause() {
		paused = false;
	}
}
