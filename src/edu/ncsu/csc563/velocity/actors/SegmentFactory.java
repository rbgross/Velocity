package edu.ncsu.csc563.velocity.actors;

import java.util.Random;

import edu.ncsu.csc563.velocity.actors.components.Transform;


public class SegmentFactory {
	public static final int NUM_SEGMENTS = 1;
	
	public static Segment getRandomSegment() {
		int rand = new Random().nextInt(NUM_SEGMENTS);
		switch (rand) {			
			case 0:
				return scatteredCubeSegment();			
			
			case 1:
				return rotatedPillarSegment();
			
			case 2:
				return needleSegment();
				
			case 3:
				return pillarSegment();
			
			case 4:
				return mineField();
		
			case 5:
				return panelSegment();
			default:
				return null;
		}
	}
	
	public static Segment panelSegment() {
		Segment segment = new Segment(0);
		Actor actor;
		
		actor = ActorFactory.rnd_cube_panel();
		((Transform) actor.getComponent("Transform")).setPosition(-4f, 0f, 0);
		((Transform) actor.getComponent("Transform")).setRotation(90, 90, 0);
		segment.addObstacle(actor);
		
		actor = ActorFactory.rnd_cube_panel();
		((Transform) actor.getComponent("Transform")).setPosition(-2f, 0f, 0);
		((Transform) actor.getComponent("Transform")).setRotation(90, 90, 0);
		segment.addObstacle(actor);
		
		actor = ActorFactory.rnd_cube_panel();
		((Transform) actor.getComponent("Transform")).setPosition(2f, 0f, 0);
		((Transform) actor.getComponent("Transform")).setRotation(90, 90, 0);
		segment.addObstacle(actor);
		
		actor = ActorFactory.rnd_cube_panel();
		((Transform) actor.getComponent("Transform")).setPosition(4f, 0f, 0);
		((Transform) actor.getComponent("Transform")).setRotation(90, 90, 0);
		segment.addObstacle(actor);
		
		actor = ActorFactory.rnd_cube_panel();
		((Transform) actor.getComponent("Transform")).setPosition(-4f, 0f, 16);
		((Transform) actor.getComponent("Transform")).setRotation(90, 90, 0);
		segment.addObstacle(actor);
		
		actor = ActorFactory.rnd_cube_panel();
		((Transform) actor.getComponent("Transform")).setPosition(-2f, 0f, 16);
		((Transform) actor.getComponent("Transform")).setRotation(90, 90, 0);
		segment.addObstacle(actor);
		
		actor = ActorFactory.rnd_cube_panel();
		((Transform) actor.getComponent("Transform")).setPosition(0f, 0f, 16);
		((Transform) actor.getComponent("Transform")).setRotation(90, 90, 0);
		segment.addObstacle(actor);
		
		actor = ActorFactory.rnd_cube_panel();
		((Transform) actor.getComponent("Transform")).setPosition(4f, 0f, 16);
		((Transform) actor.getComponent("Transform")).setRotation(90, 90, 0);
		segment.addObstacle(actor);
		
		actor = ActorFactory.rnd_cube_panel();
		((Transform) actor.getComponent("Transform")).setPosition(-4f, 0f, 32);
		((Transform) actor.getComponent("Transform")).setRotation(90, 90, 0);
		segment.addObstacle(actor);
		
		actor = ActorFactory.rnd_cube_panel();
		((Transform) actor.getComponent("Transform")).setPosition(-2f, 0f, 32);
		((Transform) actor.getComponent("Transform")).setRotation(90, 90, 0);
		segment.addObstacle(actor);
		
		actor = ActorFactory.rnd_cube_panel();
		((Transform) actor.getComponent("Transform")).setPosition(2f, 0f, 32);
		((Transform) actor.getComponent("Transform")).setRotation(90, 90, 0);
		segment.addObstacle(actor);
		
		actor = ActorFactory.rnd_cube_panel();
		((Transform) actor.getComponent("Transform")).setPosition(4f, 0f, 32);
		((Transform) actor.getComponent("Transform")).setRotation(90, 90, 0);
		segment.addObstacle(actor);
		
		actor = ActorFactory.rnd_cube_panel();
		((Transform) actor.getComponent("Transform")).setPosition(-4f, 0f, 48);
		((Transform) actor.getComponent("Transform")).setRotation(90, 90, 0);
		segment.addObstacle(actor);
		
		actor = ActorFactory.rnd_cube_panel();
		((Transform) actor.getComponent("Transform")).setPosition(0f, 0f, 48);
		((Transform) actor.getComponent("Transform")).setRotation(90, 90, 0);
		segment.addObstacle(actor);
		
		actor = ActorFactory.rnd_cube_panel();
		((Transform) actor.getComponent("Transform")).setPosition(2f, 0f, 48);
		((Transform) actor.getComponent("Transform")).setRotation(90, 90, 0);
		segment.addObstacle(actor);
		
		actor = ActorFactory.rnd_cube_panel();
		((Transform) actor.getComponent("Transform")).setPosition(4f, 0f, 48);
		((Transform) actor.getComponent("Transform")).setRotation(90, 90, 0);
		segment.addObstacle(actor);
		
		actor = ActorFactory.rnd_cube_panel();
		((Transform) actor.getComponent("Transform")).setPosition(-4f, 0f, 64);
		((Transform) actor.getComponent("Transform")).setRotation(90, 90, 0);
		segment.addObstacle(actor);
		
		actor = ActorFactory.rnd_cube_panel();
		((Transform) actor.getComponent("Transform")).setPosition(0f, 0f, 64);
		((Transform) actor.getComponent("Transform")).setRotation(90, 90, 0);
		segment.addObstacle(actor);
		
		actor = ActorFactory.rnd_cube_panel();
		((Transform) actor.getComponent("Transform")).setPosition(4f, 0f, 64);
		((Transform) actor.getComponent("Transform")).setRotation(90, 90, 0);
		segment.addObstacle(actor);
		
		return segment;
	}
	
	public static Segment mineField() {	
		Segment segment = new Segment(0);
		Actor actor;
		
		actor = ActorFactory.sm_plus();
		((Transform) actor.getComponent("Transform")).setPosition(-1.5f, 2.7f, 0);
		((Transform) actor.getComponent("Transform")).setRotation(45, 0, 45);
		segment.addObstacle(actor);
		
		actor = ActorFactory.sm_plus();
		((Transform) actor.getComponent("Transform")).setPosition(1.3f, 2.4f, 1.1f);
		((Transform) actor.getComponent("Transform")).setRotation(45, 0, 0);
		segment.addObstacle(actor);
		
		actor = ActorFactory.sm_plus();
		((Transform) actor.getComponent("Transform")).setPosition(6.3f, -4.3f, 1.7f);
		((Transform) actor.getComponent("Transform")).setRotation(35, 0, 12);
		segment.addObstacle(actor);
		
		actor = ActorFactory.sm_plus();
		((Transform) actor.getComponent("Transform")).setPosition(-2.6f, -1.3f, 2.6f);
		((Transform) actor.getComponent("Transform")).setRotation(19, 46, -31);
		segment.addObstacle(actor);
		
		actor = ActorFactory.sm_plus();
		((Transform) actor.getComponent("Transform")).setPosition(-3.5f, 4.7f, 1.5f);
		((Transform) actor.getComponent("Transform")).setRotation(48, 12, 16);
		segment.addObstacle(actor);
		
		actor = ActorFactory.sm_plus();
		((Transform) actor.getComponent("Transform")).setPosition(2.2f, -3.1f, 3.3f);
		((Transform) actor.getComponent("Transform")).setRotation(13, 149, 23);
		segment.addObstacle(actor);
		
		actor = ActorFactory.sm_plus();
		((Transform) actor.getComponent("Transform")).setPosition(3.5f, -1.2f, 3.8f);
		((Transform) actor.getComponent("Transform")).setRotation(0, 59, 15);
		segment.addObstacle(actor);
		
		actor = ActorFactory.sm_plus();
		((Transform) actor.getComponent("Transform")).setPosition(-5.7f, 0.4f, 1.6f);
		((Transform) actor.getComponent("Transform")).setRotation(124, 78, 16);
		segment.addObstacle(actor);
		
		actor = ActorFactory.sm_plus();
		((Transform) actor.getComponent("Transform")).setPosition(-1f, 0.2f, 1.2f);
		((Transform) actor.getComponent("Transform")).setRotation(11, 78, 26);
		segment.addObstacle(actor);
		
		actor = ActorFactory.sm_plus();
		((Transform) actor.getComponent("Transform")).setPosition(0.9f, -1f, 2.1f);
		((Transform) actor.getComponent("Transform")).setRotation(78, 34, 129);
		segment.addObstacle(actor);
		
		return segment;
	}
	
	public static Segment pillarSegment() {	
		Segment segment = new Segment(0);
		Actor actor;
		
		actor = ActorFactory.rectPrism();
		((Transform) actor.getComponent("Transform")).setPosition(1.5f, 0, -12);
		((Transform) actor.getComponent("Transform")).setRotation(90, 0, 0);
		segment.addObstacle(actor);
		
		actor = ActorFactory.rectPrism();
		((Transform) actor.getComponent("Transform")).setPosition(-1.5f, 0, -12);
		((Transform) actor.getComponent("Transform")).setRotation(90, 0, 0);
		segment.addObstacle(actor);
		
		actor = ActorFactory.rectPrism();
		((Transform) actor.getComponent("Transform")).setPosition(0, 1.5f, -8);
		((Transform) actor.getComponent("Transform")).setRotation(0, 90, 0);
		segment.addObstacle(actor);
		
		actor = ActorFactory.rectPrism();
		((Transform) actor.getComponent("Transform")).setPosition(0, -1.5f, -8);
		((Transform) actor.getComponent("Transform")).setRotation(0, 90, 0);
		segment.addObstacle(actor);
		
		actor = ActorFactory.rectPrism();
		((Transform) actor.getComponent("Transform")).setPosition(1.5f, 0, -4);
		((Transform) actor.getComponent("Transform")).setRotation(90, 0, 0);
		segment.addObstacle(actor);
		
		actor = ActorFactory.rectPrism();
		((Transform) actor.getComponent("Transform")).setPosition(-1.5f, 0, -4);
		((Transform) actor.getComponent("Transform")).setRotation(90, 0, 0);
		segment.addObstacle(actor);
		
		actor = ActorFactory.rectPrism();
		((Transform) actor.getComponent("Transform")).setPosition(0, 1.5f, 0);
		((Transform) actor.getComponent("Transform")).setRotation(0, 90, 0);
		segment.addObstacle(actor);
		
		actor = ActorFactory.rectPrism();
		((Transform) actor.getComponent("Transform")).setPosition(0, -1.5f, 0);
		((Transform) actor.getComponent("Transform")).setRotation(0, 90, 0);
		segment.addObstacle(actor);
		
		actor = ActorFactory.token();
		segment.addToken(actor);
		
		actor = ActorFactory.rectPrism();
		((Transform) actor.getComponent("Transform")).setPosition(1.5f, 0, 4);
		((Transform) actor.getComponent("Transform")).setRotation(90, 0, 0);
		segment.addObstacle(actor);
		
		actor = ActorFactory.rectPrism();
		((Transform) actor.getComponent("Transform")).setPosition(-1.5f, 0, 4);
		((Transform) actor.getComponent("Transform")).setRotation(90, 0, 0);
		segment.addObstacle(actor);
		
		actor = ActorFactory.rectPrism();
		((Transform) actor.getComponent("Transform")).setPosition(0, 1.5f, 8);
		((Transform) actor.getComponent("Transform")).setRotation(0, 90, 0);
		segment.addObstacle(actor);
		
		actor = ActorFactory.rectPrism();
		((Transform) actor.getComponent("Transform")).setPosition(0, -1.5f, 8);
		((Transform) actor.getComponent("Transform")).setRotation(0, 90, 0);
		segment.addObstacle(actor);
		
		actor = ActorFactory.rectPrism();
		((Transform) actor.getComponent("Transform")).setPosition(1.5f, 0, 12);
		((Transform) actor.getComponent("Transform")).setRotation(90, 0, 0);
		segment.addObstacle(actor);
		
		actor = ActorFactory.rectPrism();
		((Transform) actor.getComponent("Transform")).setPosition(-1.5f, 0, 12);
		((Transform) actor.getComponent("Transform")).setRotation(90, 0, 0);
		segment.addObstacle(actor);
		
		return segment;
	}
	
	public static Segment needleSegment() {	
		Segment segment = new Segment(0);
		Actor actor;
		
		actor = ActorFactory.rectPrism();
		((Transform) actor.getComponent("Transform")).setPosition(-2, -2, 0);
		segment.addObstacle(actor);
		
		actor = ActorFactory.rectPrism();
		((Transform) actor.getComponent("Transform")).setPosition(2, -2, 0);
		segment.addObstacle(actor);
		
		actor = ActorFactory.rectPrism();
		((Transform) actor.getComponent("Transform")).setPosition(2, 2, 0);
		segment.addObstacle(actor);
		
		actor = ActorFactory.rectPrism();
		((Transform) actor.getComponent("Transform")).setPosition(-2, 2, 0);
		segment.addObstacle(actor);
		
		actor = ActorFactory.rectPrism();
		((Transform) actor.getComponent("Transform")).setPosition(0, 0, 0);
		segment.addObstacle(actor);
		
		return segment;
	}
	
	public static Segment scatteredCubeSegment() {	
		Segment segment = new Segment(0);
		Actor actor;
		
		actor = ActorFactory.cube();
		((Transform) actor.getComponent("Transform")).setPosition(-1.5f, 2.7f, 0);
		((Transform) actor.getComponent("Transform")).setRotation(45, 0, 45);
		segment.addObstacle(actor);
		
		actor = ActorFactory.cube();
		((Transform) actor.getComponent("Transform")).setPosition(1.3f, 2.4f, 1.1f);
		((Transform) actor.getComponent("Transform")).setRotation(45, 0, 0);
		segment.addObstacle(actor);
		
		actor = ActorFactory.cube();
		((Transform) actor.getComponent("Transform")).setPosition(6.3f, -4.3f, 1.7f);
		((Transform) actor.getComponent("Transform")).setRotation(35, 0, 12);
		segment.addObstacle(actor);
		
		actor = ActorFactory.cube();
		((Transform) actor.getComponent("Transform")).setPosition(-2.6f, -1.3f, 2.6f);
		((Transform) actor.getComponent("Transform")).setRotation(19, 46, -31);
		segment.addObstacle(actor);		
		
		actor = ActorFactory.cube();
		((Transform) actor.getComponent("Transform")).setPosition(0.1f, -0.3f, 1.4f);
		((Transform) actor.getComponent("Transform")).setRotation(113, 26, -119);
		segment.addObstacle(actor);	
		
		
		return segment;
	}

	public static Segment rotatedPillarSegment() {	
		Segment segment = new Segment(0);
		Actor actor;
		
		actor = ActorFactory.rectPrism();
		((Transform) actor.getComponent("Transform")).setPosition(0, 0, -12);
		((Transform) actor.getComponent("Transform")).setRotation(90, 0, 0);
		segment.addObstacle(actor);
		
		
		actor = ActorFactory.rectPrism();
		((Transform) actor.getComponent("Transform")).setPosition(0, 0, -4);
		((Transform) actor.getComponent("Transform")).setRotation(90, 60, 0);
		segment.addObstacle(actor);
		
		
		actor = ActorFactory.rectPrism();
		((Transform) actor.getComponent("Transform")).setPosition(0, 0, 4);
		((Transform) actor.getComponent("Transform")).setRotation(90, 120, 0);
		segment.addObstacle(actor);
		
		return segment;
	}
	
	public static Segment tokenSegment() {	
		Segment segment = new Segment(0);
		Actor actor;
		
		actor = ActorFactory.token();
		segment.addToken(actor);
		
		return segment;
	}
}
