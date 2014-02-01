package edu.ncsu.csc563.velocity.components;

import edu.ncsu.csc563.velocity.actors.Actor;

public abstract class Component {
	
	private Actor actor;
	
	public void initialize() {}
	public void update(float deltaTime) {}
}
