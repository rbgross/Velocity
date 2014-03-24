package edu.ncsu.csc563.velocity.actors;

import java.util.ArrayList;
import java.util.Random;

import edu.ncsu.csc563.velocity.actors.components.Transform;


public class SegmentFactory {
	//public static final int NUM_SEGMENTS = 1;
	public static final int NUM_SEGMENTS = 4;
	
	public static ArrayList<Actor> getRandomSegment(float zDepth) {
		int rand = new Random().nextInt(NUM_SEGMENTS);
		switch (rand) {
			case 0:
				return pillarSegment().getActors(zDepth + 20);
			
			case 1:
			//case 0:
				return rotatedPillarSegment().getActors(zDepth + 20);
			
			case 2:
				return needleSegment().getActors(zDepth + 20);
				
			case 3:
				return scatteredCubeSegment().getActors(zDepth + 20);
				
			default:
				return null;
		}
	}
	
	public static Segment pillarSegment() {	
		Segment segment = new Segment(0);
		Actor actor;
		
		actor = ActorFactory.rectPrism();
		((Transform) actor.getComponent("Transform")).setPosition(1.5f, 0, -12);
		((Transform) actor.getComponent("Transform")).setRotation(90, 0, 0);
		segment.addActor(actor);
		
		actor = ActorFactory.rectPrism();
		((Transform) actor.getComponent("Transform")).setPosition(-1.5f, 0, -12);
		((Transform) actor.getComponent("Transform")).setRotation(90, 0, 0);
		segment.addActor(actor);
		
		actor = ActorFactory.rectPrism();
		((Transform) actor.getComponent("Transform")).setPosition(0, 1.5f, -8);
		((Transform) actor.getComponent("Transform")).setRotation(0, 90, 0);
		segment.addActor(actor);
		
		actor = ActorFactory.rectPrism();
		((Transform) actor.getComponent("Transform")).setPosition(0, -1.5f, -8);
		((Transform) actor.getComponent("Transform")).setRotation(0, 90, 0);
		segment.addActor(actor);
		
		actor = ActorFactory.rectPrism();
		((Transform) actor.getComponent("Transform")).setPosition(1.5f, 0, -4);
		((Transform) actor.getComponent("Transform")).setRotation(90, 0, 0);
		segment.addActor(actor);
		
		actor = ActorFactory.rectPrism();
		((Transform) actor.getComponent("Transform")).setPosition(-1.5f, 0, -4);
		((Transform) actor.getComponent("Transform")).setRotation(90, 0, 0);
		segment.addActor(actor);
		
		actor = ActorFactory.rectPrism();
		((Transform) actor.getComponent("Transform")).setPosition(0, 1.5f, 0);
		((Transform) actor.getComponent("Transform")).setRotation(0, 90, 0);
		segment.addActor(actor);
		
		actor = ActorFactory.rectPrism();
		((Transform) actor.getComponent("Transform")).setPosition(0, -1.5f, 0);
		((Transform) actor.getComponent("Transform")).setRotation(0, 90, 0);
		segment.addActor(actor);
		
		actor = ActorFactory.rectPrism();
		((Transform) actor.getComponent("Transform")).setPosition(1.5f, 0, 4);
		((Transform) actor.getComponent("Transform")).setRotation(90, 0, 0);
		segment.addActor(actor);
		
		actor = ActorFactory.rectPrism();
		((Transform) actor.getComponent("Transform")).setPosition(-1.5f, 0, 4);
		((Transform) actor.getComponent("Transform")).setRotation(90, 0, 0);
		segment.addActor(actor);
		
		actor = ActorFactory.rectPrism();
		((Transform) actor.getComponent("Transform")).setPosition(0, 1.5f, 8);
		((Transform) actor.getComponent("Transform")).setRotation(0, 90, 0);
		segment.addActor(actor);
		
		actor = ActorFactory.rectPrism();
		((Transform) actor.getComponent("Transform")).setPosition(0, -1.5f, 8);
		((Transform) actor.getComponent("Transform")).setRotation(0, 90, 0);
		segment.addActor(actor);
		
		actor = ActorFactory.rectPrism();
		((Transform) actor.getComponent("Transform")).setPosition(1.5f, 0, 12);
		((Transform) actor.getComponent("Transform")).setRotation(90, 0, 0);
		segment.addActor(actor);
		
		actor = ActorFactory.rectPrism();
		((Transform) actor.getComponent("Transform")).setPosition(-1.5f, 0, 12);
		((Transform) actor.getComponent("Transform")).setRotation(90, 0, 0);
		segment.addActor(actor);
		
		return segment;
	}
	
	public static Segment needleSegment() {	
		Segment segment = new Segment(0);
		Actor actor;
		
		actor = ActorFactory.rectPrism();
		((Transform) actor.getComponent("Transform")).setPosition(-2, -2, 0);
		segment.addActor(actor);
		
		actor = ActorFactory.rectPrism();
		((Transform) actor.getComponent("Transform")).setPosition(2, -2, 0);
		segment.addActor(actor);
		
		actor = ActorFactory.rectPrism();
		((Transform) actor.getComponent("Transform")).setPosition(2, 2, 0);
		segment.addActor(actor);
		
		actor = ActorFactory.rectPrism();
		((Transform) actor.getComponent("Transform")).setPosition(-2, 2, 0);
		segment.addActor(actor);
		
		actor = ActorFactory.rectPrism();
		((Transform) actor.getComponent("Transform")).setPosition(0, 0, 0);
		segment.addActor(actor);
		
		return segment;
	}
	
	public static Segment scatteredCubeSegment() {	
		Segment segment = new Segment(0);
		Actor actor;
		
		actor = ActorFactory.cube();
		((Transform) actor.getComponent("Transform")).setPosition(-1.5f, 2.7f, 0);
		((Transform) actor.getComponent("Transform")).setRotation(45, 0, 45);
		segment.addActor(actor);
		
		actor = ActorFactory.cube();
		((Transform) actor.getComponent("Transform")).setPosition(1.3f, 2.4f, 1.1f);
		((Transform) actor.getComponent("Transform")).setRotation(45, 0, 0);
		segment.addActor(actor);
		
		actor = ActorFactory.cube();
		((Transform) actor.getComponent("Transform")).setPosition(6.3f, -4.3f, 1.7f);
		((Transform) actor.getComponent("Transform")).setRotation(35, 0, 12);
		segment.addActor(actor);
		
		actor = ActorFactory.cube();
		((Transform) actor.getComponent("Transform")).setPosition(-2.6f, -1.3f, 2.6f);
		((Transform) actor.getComponent("Transform")).setRotation(19, 46, -31);
		segment.addActor(actor);		
		
		
		return segment;
	}

	public static Segment rotatedPillarSegment() {	
		Segment segment = new Segment(0);
		Actor actor;
		
		actor = ActorFactory.rectPrism();
		((Transform) actor.getComponent("Transform")).setPosition(0, 0, -12);
		((Transform) actor.getComponent("Transform")).setRotation(90, 0, 0);
		segment.addActor(actor);
		
		
		actor = ActorFactory.rectPrism();
		((Transform) actor.getComponent("Transform")).setPosition(0, 0, -4);
		((Transform) actor.getComponent("Transform")).setRotation(90, 60, 0);
		segment.addActor(actor);
		
		
		actor = ActorFactory.rectPrism();
		((Transform) actor.getComponent("Transform")).setPosition(0, 0, 4);
		((Transform) actor.getComponent("Transform")).setRotation(90, 120, 0);
		segment.addActor(actor);
		
		return segment;
	}
}
