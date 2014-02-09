package edu.ncsu.csc563.velocity.scene;

import edu.ncsu.csc563.velocity.actors.Actor;
import edu.ncsu.csc563.velocity.actors.ActorFactory;

public class Scene {
	//private ArrayList<Actor> mActors;
	public static Scene instance;
	private Actor mActor;
	private static boolean paused = false;
	
	private Scene() {
		//this.mActors = new ArrayList<Actor>();
		//this.mActors.add(ActorFactory.cube());
		this.mActor = ActorFactory.cube();
	}
	
	public static Scene getInstance() {
		if (instance == null) {
			instance = new Scene();
		}
		return instance;
	}
	
	public void updateScene() {
		//for (Actor actor : this.mActors) {
		//	actor.update();
		//}
		if (!paused) this.mActor.update();
	}
	
	public void drawScene() {
		//for (Actor actor : this.mActors) {
		//	actor.draw();
		//}
		this.mActor.draw();
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
