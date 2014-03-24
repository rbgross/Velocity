package edu.ncsu.csc563.velocity.actors;

import java.util.LinkedList;

import android.util.Log;

import edu.ncsu.csc563.velocity.actors.components.ForcedMovement;
import edu.ncsu.csc563.velocity.actors.components.Material;
import edu.ncsu.csc563.velocity.actors.components.colliders.OBBCollider;
import edu.ncsu.csc563.velocity.physics.Collision;

public class Scene {
	private Actor mPlayer;
	private LinkedList<Actor> mActors;
	public static Scene instance;
	private static boolean paused = false;
	
	private Scene() {		
		this.mPlayer = ActorFactory.ship();
		this.mActors = new LinkedList<Actor>();
		
		this.mActors.addAll(SegmentFactory.getRandomSegment(50));
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
			
			//Test obstacle ship collisions
			int i = 0;
			float colRange = ((OBBCollider) this.mActors.get(i).getComponent("OBBCollider")).getZBounds()[0];
			boolean collided = false;
			while (colRange < 0.25f) {
				if (Collision.collisionTest((OBBCollider) this.mPlayer.getComponent("OBBCollider"), (OBBCollider) this.mActors.get(i).getComponent("OBBCollider"))) {
					collided = true;
					((Material) this.mActors.get(i).getComponent("Material")).setDiffuseColor(1.0f, 0.5f, 0.0f);
					Log.e("collision", "YOU COLLIDED!");
				} else {
					((Material) this.mActors.get(i).getComponent("Material")).setDiffuseColor(0.3f, 0.3f, 0.3f);
				}
				i++;
				colRange = ((OBBCollider) this.mActors.get(i).getComponent("OBBCollider")).getZBounds()[0];
			}
			
			if (collided) {
				//paused = true;
			}
			
			//Remove actors who's backs have moved past the camera
			float zMin = ((OBBCollider) this.mActors.getFirst().getComponent("OBBCollider")).getZBounds()[1];
			while (zMin < -15 ) {
				this.mActors.remove();
				zMin = ((OBBCollider) this.mActors.getFirst().getComponent("OBBCollider")).getZBounds()[1];
			}
			
			//Check if the max depth is too high; if so, add a new segment
			float zMax = ((OBBCollider) this.mActors.getLast().getComponent("OBBCollider")).getZBounds()[1];
			if (zMax < 200) {
				this.mActors.addAll(SegmentFactory.getRandomSegment(zMax));
			}	
			
			ForcedMovement.mSpeed += 0.0001f;
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
