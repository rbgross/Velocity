package edu.ncsu.csc563.velocity.actors;

import java.util.HashMap;

import edu.ncsu.csc563.velocity.components.Component;

public class Actor {
	private HashMap<String, Component> mComponents;
	
	public Actor() {
		this.mComponents = new HashMap<String, Component>();
	}
	
	public void addComponent(String componentName, Component component) {
		this.mComponents.put(componentName, component);
	}
	
	public Component getComponent(String componentName) {
		return this.mComponents.get(componentName);
	}
}
