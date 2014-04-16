package edu.ncsu.csc563.velocity.actors;

import java.util.LinkedList;


import edu.ncsu.csc563.velocity.MainActivity;
import edu.ncsu.csc563.velocity.actors.components.Collider;
import edu.ncsu.csc563.velocity.actors.components.ForcedMovement;
import edu.ncsu.csc563.velocity.actors.components.PlayerController;
import edu.ncsu.csc563.velocity.actors.components.Material;
import edu.ncsu.csc563.velocity.physics.Collision;

public class Scene {
	private Actor mPlayer;
	private LinkedList<Actor> mObstacles;
	private LinkedList<Actor> mTokens;
	private static Scene instance;
	private static boolean paused = false;
	private static boolean gameOver = false;
	private static boolean gameStart = true;
	
	private Scene() {		
		this.mPlayer = ActorFactory.ship();
		this.mObstacles = new LinkedList<Actor>();
		this.mTokens = new LinkedList<Actor>();
		
		Segment segment = SegmentFactory.getRandomSegment();
		this.mObstacles.addAll(segment.getObstacles(20));
		this.mTokens.addAll(segment.getTokens(20));		
		
		//Check if the max depth is too high; if so, add a new segment
		float zMax = ((Collider) this.mObstacles.getLast().getComponent("Collider")).getPrimaryCollider().getZBounds()[1];
		while (zMax < 200) {
			segment = SegmentFactory.getRandomSegment();
			this.mObstacles.addAll(segment.getObstacles(zMax + 20));
			this.mTokens.addAll(segment.getTokens(zMax + 20));
			zMax = ((Collider) this.mObstacles.getLast().getComponent("Collider")).getPrimaryCollider().getZBounds()[1];
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
		ForcedMovement.mSpeed = 0.2f;
		MainActivity.mscore = 0;
		MainActivity.score.post(new Runnable() {
		    public void run() {
		        MainActivity.score.setText("Score: " + MainActivity.mscore);
		    } 
		});
		instance = new Scene();
	}
	
	public void updateScene() {		
		if (!paused) {			
			this.mPlayer.update();
			
			for (Actor actor : this.mObstacles) {
				actor.update();
			}
			
			for (Actor actor : this.mTokens) {
				actor.update();
			}
			
			//Test token ship collisions
			int i = 0;
			float colRange = 0;
			if (i < this.mTokens.size()) {
				colRange = ((Collider) this.mTokens.get(i).getComponent("Collider")).getPrimaryCollider().getZBounds()[0];
			}
			while (colRange < -1.5f && i < this.mTokens.size()) {
				if (Collision.collisionTest((Collider) this.mPlayer.getComponent("Collider"), (Collider) this.mTokens.get(i).getComponent("Collider"))) {
					this.mTokens.remove(i);
					
					MainActivity.mscore += 100;
					MainActivity.score.post(new Runnable() {
					    public void run() {
					        MainActivity.score.setText("Score: " + MainActivity.mscore);
					    } 
					});
				}
				
				i++;
				if (i < this.mTokens.size()) {
					colRange = ((Collider) this.mTokens.get(i).getComponent("Collider")).getPrimaryCollider().getZBounds()[0];
				} else {
					break;
				}
			}
			
			if (!((PlayerController) this.mPlayer.getComponent("Controller")).getInvul()) {
				//Test obstacle ship collisions
				i = 0;
				colRange = ((Collider) this.mObstacles.get(i).getComponent("Collider")).getPrimaryCollider().getZBounds()[0];
				boolean collided = false;
				while (colRange < -1.5f) {
					if (Collision.collisionTest((Collider) this.mPlayer.getComponent("Collider"), (Collider) this.mObstacles.get(i).getComponent("Collider"))) {
						collided = true;
						break;
					}
					
					i++;
					if (i < this.mObstacles.size()) {
						colRange = ((Collider) this.mObstacles.get(i).getComponent("Collider")).getPrimaryCollider().getZBounds()[0];
					} else {
						break;
					}
				}
				
				if (collided) {				
					paused = true;
					gameOver = true;
					
					for (Actor actor : this.mObstacles) {
						((Material) actor.getComponent("Material")).setDiffuseColor(1, 1, 1);
					}
				}
			} else {
				// TODO: VISUALLY SET SHIP SEE THROUGH
			}
			
			//Remove actors whose backs have moved past the camera
			if (this.mTokens.size() > 0) {
				float zMin = ((Collider) this.mTokens.getFirst().getComponent("Collider")).getPrimaryCollider().getZBounds()[1];
				while (zMin < -15 ) {
					this.mTokens.remove();
					if (this.mTokens.size() > 0) {
						zMin = ((Collider) this.mTokens.getFirst().getComponent("Collider")).getPrimaryCollider().getZBounds()[1];
					} else {
						break;
					}
				}
			}
			
			//Remove actors whose backs have moved past the camera
			float zMin = ((Collider) this.mObstacles.getFirst().getComponent("Collider")).getPrimaryCollider().getZBounds()[1];
			while (zMin < -15 ) {
				this.mObstacles.remove();
				zMin = ((Collider) this.mObstacles.getFirst().getComponent("Collider")).getPrimaryCollider().getZBounds()[1];
			}
			
			//Check if the max depth is too high; if so, add a new segment
			float zMax = ((Collider) this.mObstacles.getLast().getComponent("Collider")).getPrimaryCollider().getZBounds()[1];
			if (zMax < 200) {
				Segment segment = SegmentFactory.getRandomSegment();
				this.mObstacles.addAll(segment.getObstacles(zMax + 20));
				this.mTokens.addAll(segment.getTokens(zMax + 20));
			}	
			
			ForcedMovement.mSpeed += 0.0001f;
		}
	}
	
	public void drawScene() {
		this.mPlayer.draw();
		
		for (Actor actor : this.mObstacles) {
			actor.draw();
		}
		
		for (Actor actor : this.mTokens) {
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
	
	public static boolean isGameStart() {
		return gameStart;
	}
	
	public static void startGame() {
		gameStart = false;
		unPause();
	}
	
	public  void activateInvul() {
		((PlayerController) this.mPlayer.getComponent("Controller")).enableInvul();
	}
}
