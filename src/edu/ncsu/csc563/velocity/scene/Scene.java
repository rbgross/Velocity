package edu.ncsu.csc563.velocity.scene;

import java.util.ArrayList;

import edu.ncsu.csc563.velocity.actors.Actor;
import edu.ncsu.csc563.velocity.actors.ActorFactory;

public class Scene {
	private ArrayList<Actor> mActors;
	public static Scene instance;
	
	private Scene() {
		this.mActors = new ArrayList<Actor>();
		this.mActors.add(ActorFactory.cube());
	}
	
	public static Scene getInstance() {
		if (instance == null) {
			instance = new Scene();
		}
		return instance;
	}
	
	public void updateScene() {
		for (Actor actor : this.mActors) {
			actor.update();
		}
	}
	
	public void drawScene() {
		for (Actor actor : this.mActors) {
			actor.draw();
		}
	}
}
