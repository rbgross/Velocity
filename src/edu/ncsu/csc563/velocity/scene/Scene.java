package edu.ncsu.csc563.velocity.scene;

import java.util.ArrayList;

import edu.ncsu.csc563.velocity.actors.*;
import edu.ncsu.csc563.velocity.actors.components.*;

public class Scene {
	private ArrayList<Actor> mActors;
	public static Scene instance;
	private static boolean paused = false;
	
	private Scene() {
		this.mActors = new ArrayList<Actor>();
		this.mActors.add(ActorFactory.ship());
		
		Actor actor;		
		
		actor = ActorFactory.rectPrism();
		((Transform) actor.getComponent("Transform")).setPosition(2, 0, -12);
		((Transform) actor.getComponent("Transform")).setRotation(90, 0, 0);
		this.mActors.add(actor);
		
		actor = ActorFactory.rectPrism();
		((Transform) actor.getComponent("Transform")).setPosition(-2, 0, -12);
		((Transform) actor.getComponent("Transform")).setRotation(90, 0, 0);
		this.mActors.add(actor);
		
		actor = ActorFactory.rectPrism();
		((Transform) actor.getComponent("Transform")).setPosition(0, 2, -8);
		((Transform) actor.getComponent("Transform")).setRotation(0, 90, 0);
		this.mActors.add(actor);
		
		actor = ActorFactory.rectPrism();
		((Transform) actor.getComponent("Transform")).setPosition(0, -2, -8);
		((Transform) actor.getComponent("Transform")).setRotation(0, 90, 0);
		this.mActors.add(actor);
		
		actor = ActorFactory.rectPrism();
		((Transform) actor.getComponent("Transform")).setPosition(2, 0, -4);
		((Transform) actor.getComponent("Transform")).setRotation(90, 0, 0);
		this.mActors.add(actor);
		
		actor = ActorFactory.rectPrism();
		((Transform) actor.getComponent("Transform")).setPosition(-2, 0, -4);
		((Transform) actor.getComponent("Transform")).setRotation(90, 0, 0);
		this.mActors.add(actor);
		
		actor = ActorFactory.rectPrism();
		((Transform) actor.getComponent("Transform")).setPosition(0, 2, 0);
		((Transform) actor.getComponent("Transform")).setRotation(0, 90, 0);
		this.mActors.add(actor);
		
		actor = ActorFactory.rectPrism();
		((Transform) actor.getComponent("Transform")).setPosition(0, -2, 0);
		((Transform) actor.getComponent("Transform")).setRotation(0, 90, 0);
		this.mActors.add(actor);
		
		actor = ActorFactory.rectPrism();
		((Transform) actor.getComponent("Transform")).setPosition(2, 0, 4);
		((Transform) actor.getComponent("Transform")).setRotation(90, 0, 0);
		this.mActors.add(actor);
		
		actor = ActorFactory.rectPrism();
		((Transform) actor.getComponent("Transform")).setPosition(-2, 0, 4);
		((Transform) actor.getComponent("Transform")).setRotation(90, 0, 0);
		this.mActors.add(actor);
		
		actor = ActorFactory.rectPrism();
		((Transform) actor.getComponent("Transform")).setPosition(0, 2, 8);
		((Transform) actor.getComponent("Transform")).setRotation(0, 90, 0);
		this.mActors.add(actor);
		
		actor = ActorFactory.rectPrism();
		((Transform) actor.getComponent("Transform")).setPosition(0, -2, 8);
		((Transform) actor.getComponent("Transform")).setRotation(0, 90, 0);
		this.mActors.add(actor);
		
		actor = ActorFactory.rectPrism();
		((Transform) actor.getComponent("Transform")).setPosition(2, 0, 12);
		((Transform) actor.getComponent("Transform")).setRotation(90, 0, 0);
		this.mActors.add(actor);
		
		actor = ActorFactory.rectPrism();
		((Transform) actor.getComponent("Transform")).setPosition(-2, 0, 12);
		((Transform) actor.getComponent("Transform")).setRotation(90, 0, 0);
		this.mActors.add(actor);
	}
	
	public static Scene getInstance() {
		if (instance == null) {
			instance = new Scene();
		}
		return instance;
	}
	
	public void updateScene() {
		if (!paused) {
			for (Actor actor : this.mActors) {
				actor.update();
			}
		}
	}
	
	public void drawScene() {
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
