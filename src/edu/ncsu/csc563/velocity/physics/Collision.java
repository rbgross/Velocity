package edu.ncsu.csc563.velocity.physics;

import edu.ncsu.csc563.velocity.actors.components.Collider;
import edu.ncsu.csc563.velocity.actors.components.colliders.CircleCollider;
import edu.ncsu.csc563.velocity.actors.components.colliders.OBBCollider;
import edu.ncsu.csc563.velocity.actors.components.colliders.SphereCollider;

public class Collision {
	
	public static boolean collisionTest(Collider c1, Collider c2) {
		for (OBBCollider obb1 : c1.getOBBColliders()) {
			for (OBBCollider obb2 : c2.getOBBColliders()) {
				if (collisionTest(obb1, obb2)) {
					return true;
				}
			}
			
			for (SphereCollider sphere2 : c2.getSphereColliders()) {
				if (collisionTest(obb1, sphere2)) {
					return true;
				}
			}
		}
		
		for (SphereCollider sphere1 : c1.getSphereColliders()) {
			for (OBBCollider obb2 : c2.getOBBColliders()) {
				if (collisionTest(obb2, sphere1)) {
					return true;
				}
			}
			
			for (SphereCollider sphere2 : c2.getSphereColliders()) {
				if (collisionTest(sphere1, sphere2)) {
					return true;
				}
			}
			
			for (CircleCollider circle2 : c2.getCircleColliders()) {
				if (collisionTest(sphere1, circle2)) {
					return true;
				}
			}
		}
		
		for (CircleCollider circle1 : c1.getCircleColliders()) {
			for (SphereCollider sphere2 : c2.getSphereColliders()) {
				if (collisionTest(sphere2, circle1)) {
					return true;
				}
			}
		}
		
		return false;
	}
	
	// This code is a java adaption of the Sphere-OBB collision code as found in Real Time Collision Detection
	//by Christer Ericson
	private static boolean collisionTest(OBBCollider obb, SphereCollider sphere) {
		float[] obbCen = obb.getCenter();
		float[] sphereCen = sphere.getCenter();
		float[] obbRot = obb.getRotationMatrix();
		float[] obbHW = obb.getHalfWidths();
		float sphereRad = sphere.getRadius();
		
		float[] d = {sphereCen[0] - obbCen[0], sphereCen[1] - obbCen[1], sphereCen[2] - obbCen[2]};
		float[] q = obb.getCenter();
		
		for (int i = 0; i < 3; i++) {
			float dist = d[0] * obbRot[3 * i + 0] + d[1] * obbRot[3 * i + 1] + d[2] * obbRot[3 * i + 2];
			if (dist > obbHW[i]) {
				dist = obbHW[i];
			}
			if (dist < -obbHW[i]) {
				dist = -obbHW[i];
			}
			q[0] += dist * obbRot[3 * i + 0];
			q[1] += dist * obbRot[3 * i + 1];
			q[2] += dist * obbRot[3 * i + 2];
		}
		
		float[] v = new float[3];
		v[0] = q[0] - sphereCen[0];
		v[1] = q[1] - sphereCen[1];
		v[2] = q[2] - sphereCen[2];
		
		return (v[0] * v[0] + v[1] * v[1] + v[2] * v[2]) <= (sphereRad * sphereRad);
	}
	
	// This code is a java adaption of the Sphere-Sphere collision code as found in Real Time Collision Detection
	// by Christer Ericson
	private static boolean collisionTest(SphereCollider a, SphereCollider b) {
		float aRad = a.getRadius();
		float bRad = b.getRadius();
		float[] aCen = a.getCenter();
		float[] bCen = b.getCenter();
		
		// Calculate squared distance between centers
		float[] d = {aCen[0] - bCen[0], aCen[1] - bCen[1], aCen[2] - bCen[2]};
		float dist2 = d[0] * d[0] + d[1] * d[1] + d[2] * d[2];
		
		// Spheres intersect if squared distance is less than squared sum of radii
		float radiusSum = aRad + bRad;
		return dist2 <= radiusSum * radiusSum;
	}
	
	// This code is a java adaption of the OBB-OBB collision code as found in Real Time Collision Detection
	// by Christer Ericson
	private static boolean collisionTest(OBBCollider a, OBBCollider b) {
		float[] aHW = a.getHalfWidths();
		float[] bHW = b.getHalfWidths();
		float[] aRot = a.getRotationMatrix();
		float[] bRot = b.getRotationMatrix();
		float[] aCen = a.getCenter();
		float[] bCen = b.getCenter();
		
		// Compute rotation matrix expressing b in a's coordinate frame
		float[] R = new float[9];
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				R[3 * i + j] = aRot[3 * i + 0] * bRot[3 * j + 0] + aRot[3 * i + 1] * bRot[3 * j + 1] + aRot[3 * i + 2] * bRot[3 * j + 2];
				//R[3 * j + i] = aRot[3 * i + 0] * bRot[3 * j + 0] + aRot[3 * i + 1] * bRot[3 * j + 1] + aRot[3 * i + 2] * bRot[3 * j + 2];
			}
		}
		
		// Compute translation vector t
		float[] t = new float[3];
		t[0] = bCen[0] - aCen[0];
		t[1] = bCen[1] - aCen[1];
		t[2] = bCen[2] - aCen[2];
		
		// Bring translation into a's coordinate frame
		float[] tempT = new float[3];
		tempT[0] = t[0] * aRot[0] + t[1] * aRot[1] + t[2] * aRot[2];
		tempT[1] = t[0] * aRot[3] + t[1] * aRot[4] + t[2] * aRot[5];
		tempT[2] = t[0] * aRot[6] + t[1] * aRot[7] + t[2] * aRot[8];
		
		//tempT[0] = t[0] * aRot[0] + t[1] * aRot[3] + t[2] * aRot[6];
		//tempT[1] = t[0] * aRot[1] + t[1] * aRot[4] + t[2] * aRot[7];
		//tempT[2] = t[0] * aRot[2] + t[1] * aRot[5] + t[2] * aRot[8];
		
		t = tempT;
		
		// Compute common subexpressions.  Add in an epsilon term to
		// counteract arithmetic errors when two edges are parallel and
		// cross product is (near) null
		float[] AbsR = new float[9];
		float EPSILON = 0.0001f;
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				AbsR[3 * i + j] = Math.abs(R[3 * i + j]) + EPSILON;
			}
		}
		
		float ra;
		float rb;
		
		// Test axes L = A0, L = A1, L = A2
		for (int i = 0; i < 3; i++) {
			ra = aHW[i];
			rb = bHW[0] * AbsR[3 * i + 0] + bHW[1] * AbsR[3 * i + 1] + bHW[2] * AbsR[3 * i + 2];
			if (Math.abs(t[i]) > ra + rb) {
				return false;
			}
		}
		
		// Test axes L = B0, L = B1, L = B2
		for (int i = 0; i < 3; i++) {
			ra = aHW[0] * AbsR[0 + i] + aHW[1] * AbsR[3 + i] + aHW[2] * AbsR[6 + i];
			rb = bHW[i];
			if (Math.abs(t[0] * R[0 + i] + t[1] * R[3 + i] + t[2] * R[6 + i]) > ra + rb) {
				return false;
			}
		}
		
		// Test axis L = A0 x B0
		ra = aHW[1] * AbsR[6] + aHW[2] * AbsR[3];
		rb = bHW[1] * AbsR[2] + bHW[2] * AbsR[1];
		if (Math.abs(t[2] * R[3] - t[1] * R[6]) > ra + rb) {
			return false;
		}
		
		// Test axis L = A0 x B1
		ra = aHW[1] * AbsR[7] + aHW[2] * AbsR[4];
		rb = bHW[0] * AbsR[2] + bHW[2] * AbsR[0];
		if (Math.abs(t[2] * R[4] - t[1] * R[7]) > ra + rb) {
			return false;
		}
		
		// Test axis L = A0 x B2
		ra = aHW[1] * AbsR[8] + aHW[2] * AbsR[5];
		rb = bHW[0] * AbsR[1] + bHW[1] * AbsR[0];
		if (Math.abs(t[2] * R[5] - t[1] * R[8]) > ra + rb) {
			return false;
		}
		
		// Test axis L = A1 x B0
		ra = aHW[0] * AbsR[6] + aHW[2] * AbsR[0];
		rb = bHW[1] * AbsR[5] + bHW[2] * AbsR[4];
		if (Math.abs(t[0] * R[6] - t[2] * R[0]) > ra + rb) {
			return false;
		}
		
		// Test axis L = A1 x B1
		ra = aHW[0] * AbsR[7] + aHW[2] * AbsR[1];
		rb = bHW[0] * AbsR[5] + bHW[2] * AbsR[3];
		if (Math.abs(t[0] * R[7] - t[2] * R[1]) > ra + rb) {
			return false;
		}
		
		// Test axis L = A1 x B2
		ra = aHW[0] * AbsR[8] + aHW[2] * AbsR[2];
		rb = bHW[0] * AbsR[4] + bHW[1] * AbsR[3];
		if (Math.abs(t[0] * R[8] - t[2] * R[2]) > ra + rb) {
			return false;
		}
		
		// Test axis L = A2 x B0
		ra = aHW[0] * AbsR[3] + aHW[1] * AbsR[0];
		rb = bHW[1] * AbsR[8] + bHW[2] * AbsR[7];
		if (Math.abs(t[1] * R[0] - t[0] * R[3]) > ra + rb) {
			return false;
		}
		
		// Test axis L = A2 x B1
		ra = aHW[0] * AbsR[4] + aHW[1] * AbsR[1];
		rb = bHW[0] * AbsR[8] + bHW[2] * AbsR[6];
		if (Math.abs(t[1] * R[1] - t[0] * R[4]) > ra + rb) {
			return false;
		}
		
		// Test axis L = A2 x B2
		ra = aHW[0] * AbsR[5] + aHW[1] * AbsR[2];
		rb = bHW[0] * AbsR[7] + bHW[1] * AbsR[6];
		if (Math.abs(t[1] * R[2] - t[0] * R[5]) > ra + rb) {
			return false;
		}
		
		return true;
	}
	
	private static boolean collisionTest(SphereCollider sphere, CircleCollider circle) {
		float sphereRad = sphere.getRadius();
		float circleRad = circle.getRadius();
		float[] sphereCen = sphere.getCenter();
		float[] circleCen = circle.getCenter();
		
		// Calculate squared distance between centers
		float[] d = {sphereCen[0] - circleCen[0], sphereCen[1] - circleCen[1], sphereCen[2] - circleCen[2]};
		
		
		if (d[2] * d[2] >  sphereRad * sphereRad) {
			return false;
		}
		if (d[0] * d[0] + d[1] * d[1] > (sphereRad + circleRad) * (sphereRad + circleRad)) {
			return false;
		}
		return true;
	}
}
