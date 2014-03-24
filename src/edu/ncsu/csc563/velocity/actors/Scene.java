package edu.ncsu.csc563.velocity.actors;

import java.util.LinkedList;

import edu.ncsu.csc563.velocity.actors.components.Collider;
import edu.ncsu.csc563.velocity.actors.components.ForcedMovement;
import edu.ncsu.csc563.velocity.actors.components.Material;
import edu.ncsu.csc563.velocity.physics.Collision;

public class Scene {
	private Actor mPlayer;
	private LinkedList<Actor> mActors;
	public static Scene instance;
	private static boolean paused = false;
	private static boolean gameOver = false;
	
	private Scene() {		
		this.mPlayer = ActorFactory.ship();
		this.mActors = new LinkedList<Actor>();
		
		this.mActors.addAll(SegmentFactory.getRandomSegment(0));
		
		//Check if the max depth is too high; if so, add a new segment
		float zMax = ((Collider) this.mActors.getLast().getComponent("Collider")).getPrimaryCollider().getZBounds()[1];
		while (zMax < 200) {
			this.mActors.addAll(SegmentFactory.getRandomSegment(zMax));
			zMax = ((Collider) this.mActors.getLast().getComponent("Collider")).getPrimaryCollider().getZBounds()[1];
		}	
	}
	
	public static Scene getInstance() {
		if (instance == null) {
			instance = new Scene();
		}
		return instance;
	}
	
	public static void resetGame() {
		gameOver = false;
		instance = new Scene();
	}
	
	public void updateScene() {
		if (!paused) {
			this.mPlayer.update();
			
			for (Actor actor : this.mActors) {
				actor.update();
			}
			
			//Test obstacle ship collisions
			int i = 0;
			float colRange = ((Collider) this.mActors.get(i).getComponent("Collider")).getPrimaryCollider().getZBounds()[0];
			boolean collided = false;
			while (colRange < 0.25f) {
				if (Collision.collisionTest((Collider) this.mPlayer.getComponent("Collider"), (Collider) this.mActors.get(i).getComponent("Collider"))) {
					collided = true;
					break;
				}
				
				i++;
				if (i < this.mActors.size()) {
					colRange = ((Collider) this.mActors.get(i).getComponent("Collider")).getPrimaryCollider().getZBounds()[0];
				} else {
					break;
				}
			}
			
			if (collided) {				
				paused = true;
				gameOver = true;
				
				for (Actor actor : this.mActors) {
					float[] tempCol = ((Material) actor.getComponent("Material")).getDiffuseColor();
					tempCol[0] = 1 - tempCol[0];
					tempCol[1] = 1 - tempCol[1];
					tempCol[2] = 1 - tempCol[2];
					((Material) actor.getComponent("Material")).setDiffuseColor(tempCol[0], tempCol[1], tempCol[2]);
				}
				
				((Material) this.mActors.get(i).getComponent("Material")).setDiffuseColor(1.0f, 0, 0);
			}
			
			//Remove actors who's backs have moved past the camera
			float zMin = ((Collider) this.mActors.getFirst().getComponent("Collider")).getPrimaryCollider().getZBounds()[1];
			while (zMin < -15 ) {
				this.mActors.remove();
				zMin = ((Collider) this.mActors.getFirst().getComponent("Collider")).getPrimaryCollider().getZBounds()[1];
			}
			
			//Check if the max depth is too high; if so, add a new segment
			float zMax = ((Collider) this.mActors.getLast().getComponent("Collider")).getPrimaryCollider().getZBounds()[1];
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
	
	public static boolean isGameOver() {
		return gameOver;
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
