package edu.ncsu.csc563.velocity.actors.components;

import edu.ncsu.csc563.velocity.actors.Actor;

public abstract class Component {	
	private Actor mActor;
	
	/*
	public Component(Actor actor) {
		this.mActor = actor;
	}
	*/
	
	public Actor getActor() {
		return this.mActor;
	}
	
	public void initialize() {}
	public void update(float deltaTime) {}
}
