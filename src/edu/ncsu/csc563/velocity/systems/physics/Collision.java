package edu.ncsu.csc563.velocity.systems.physics;

import edu.ncsu.csc563.velocity.actors.components.*;
import edu.ncsu.csc563.velocity.actors.components.colliders.OBBCollider;
import edu.ncsu.csc563.velocity.actors.components.colliders.PlaneCollider;

public class Collision {

	public static boolean collisionTest(PlaneCollider plane, OBBCollider obb) {
		return false;		
	}
	
	public static boolean collisionTest(PlaneCollider plane1, PlaneCollider plane2) {
		return false;
	}
	
	public static boolean collisionTest(OBBCollider obb1, OBBCollider obb2) {
		return false;
	}
}
