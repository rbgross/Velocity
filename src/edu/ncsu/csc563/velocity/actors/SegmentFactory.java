package edu.ncsu.csc563.velocity.actors;

import java.util.ArrayList;

import edu.ncsu.csc563.velocity.actors.components.Transform;


public class SegmentFactory {
	
	public static ArrayList<Actor> pillarSegment(float zDepth) {	
		Segment segment = new Segment(zDepth);
		Actor actor;
		
		actor = ActorFactory.rectPrism();
		((Transform) actor.getComponent("Transform")).setPosition(2, 0, -12);
		((Transform) actor.getComponent("Transform")).setRotation(90, 0, 0);
		segment.addActor(actor);
		
		actor = ActorFactory.rectPrism();
		((Transform) actor.getComponent("Transform")).setPosition(-2, 0, -12);
		((Transform) actor.getComponent("Transform")).setRotation(90, 0, 0);
		segment.addActor(actor);
		
		actor = ActorFactory.rectPrism();
		((Transform) actor.getComponent("Transform")).setPosition(0, 2, -8);
		((Transform) actor.getComponent("Transform")).setRotation(0, 90, 0);
		segment.addActor(actor);
		
		actor = ActorFactory.rectPrism();
		((Transform) actor.getComponent("Transform")).setPosition(0, -2, -8);
		((Transform) actor.getComponent("Transform")).setRotation(0, 90, 0);
		segment.addActor(actor);
		
		actor = ActorFactory.rectPrism();
		((Transform) actor.getComponent("Transform")).setPosition(2, 0, -4);
		((Transform) actor.getComponent("Transform")).setRotation(90, 0, 0);
		segment.addActor(actor);
		
		actor = ActorFactory.rectPrism();
		((Transform) actor.getComponent("Transform")).setPosition(-2, 0, -4);
		((Transform) actor.getComponent("Transform")).setRotation(90, 0, 0);
		segment.addActor(actor);
		
		actor = ActorFactory.rectPrism();
		((Transform) actor.getComponent("Transform")).setPosition(0, 2, 0);
		((Transform) actor.getComponent("Transform")).setRotation(0, 90, 0);
		segment.addActor(actor);
		
		actor = ActorFactory.rectPrism();
		((Transform) actor.getComponent("Transform")).setPosition(0, -2, 0);
		((Transform) actor.getComponent("Transform")).setRotation(0, 90, 0);
		segment.addActor(actor);
		
		actor = ActorFactory.rectPrism();
		((Transform) actor.getComponent("Transform")).setPosition(2, 0, 4);
		((Transform) actor.getComponent("Transform")).setRotation(90, 0, 0);
		segment.addActor(actor);
		
		actor = ActorFactory.rectPrism();
		((Transform) actor.getComponent("Transform")).setPosition(-2, 0, 4);
		((Transform) actor.getComponent("Transform")).setRotation(90, 0, 0);
		segment.addActor(actor);
		
		actor = ActorFactory.rectPrism();
		((Transform) actor.getComponent("Transform")).setPosition(0, 2, 8);
		((Transform) actor.getComponent("Transform")).setRotation(0, 90, 0);
		segment.addActor(actor);
		
		actor = ActorFactory.rectPrism();
		((Transform) actor.getComponent("Transform")).setPosition(0, -2, 8);
		((Transform) actor.getComponent("Transform")).setRotation(0, 90, 0);
		segment.addActor(actor);
		
		actor = ActorFactory.rectPrism();
		((Transform) actor.getComponent("Transform")).setPosition(2, 0, 12);
		((Transform) actor.getComponent("Transform")).setRotation(90, 0, 0);
		segment.addActor(actor);
		
		actor = ActorFactory.rectPrism();
		((Transform) actor.getComponent("Transform")).setPosition(-2, 0, 12);
		((Transform) actor.getComponent("Transform")).setRotation(90, 0, 0);
		segment.addActor(actor);
		
		return segment.getActors();
	}

}
