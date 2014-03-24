package edu.ncsu.csc563.velocity.actors;

import edu.ncsu.csc563.velocity.actors.components.*;
import edu.ncsu.csc563.velocity.actors.components.colliders.OBBCollider;
import edu.ncsu.csc563.velocity.rendering.GLES20ShaderFactory;
import edu.ncsu.csc563.velocity.resources.ResourceManager;

public class ActorFactory {	
	public static Actor ship() {
		Actor actor = new Actor();
		actor.addComponent("Transform", new Transform());
		((Transform) actor.getComponent("Transform")).rotate(0, 90, 0);
		//((Transform) actor.getComponent("Transform")).setScale(0.5f, 0.5f, 0.5f);
		actor.addComponent("Mesh", ResourceManager.getMesh("meshes/RoundedCube.vmf"));
		actor.addComponent("Material", new Material(GLES20ShaderFactory.getShader("diffuseSpecular")));
		((Material) actor.getComponent("Material")).setDiffuseColor(0.0f, 0.5f, 1.0f);
		actor.addComponent("Controller", new PlayerController(((Transform) actor.getComponent("Transform"))));
		actor.addComponent("Collider", new Collider());
		float[] center = {0, 0, 0};
		float[] halfWidths = {0.5f, 0.5f, 0.5f};
		OBBCollider collider = new OBBCollider((Transform) actor.getComponent("Transform"), center, halfWidths);
		((Collider) actor.getComponent("Collider")).setPrimaryCollider(collider);
		((Collider) actor.getComponent("Collider")).addOBBCollider(collider);
		return actor;
	}
	
	public static Actor cube() {
		Actor actor = new Actor();
		actor.addComponent("Transform", new Transform());
		actor.addComponent("Mesh", ResourceManager.getMesh("meshes/RoundedCube.vmf"));
		actor.addComponent("Material", new Material(GLES20ShaderFactory.getShader("diffuseSpecular")));
		((Material) actor.getComponent("Material")).setDiffuseColor(0.3f, 0.3f, 0.3f);
		actor.addComponent("Collider", new Collider());
		float[] center = {0, 0, 0};
		float[] halfWidths = {0.5f, 0.5f, 0.5f};
		OBBCollider collider = new OBBCollider((Transform) actor.getComponent("Transform"), center, halfWidths);
		((Collider) actor.getComponent("Collider")).setPrimaryCollider(collider);
		((Collider) actor.getComponent("Collider")).addOBBCollider(collider);
		actor.addComponent("ForcedMovement", new ForcedMovement((Transform) actor.getComponent("Transform")));
		return actor;
	}
	
	public static Actor rectPrism() {
		// Make a new actor
		Actor actor = new Actor();
		
		// Add a transform; the center of this is the center of the primary (big) collider as well
		actor.addComponent("Transform", new Transform());
		
		// Add the appropriate mesh from the file (add these to the assets/meshes/Nathan2 folder)
		actor.addComponent("Mesh", ResourceManager.getMesh("meshes/LongRoundedRectPrism.vmf"));
		
		// Add a material to the actor and set the diffuse color
		actor.addComponent("Material", new Material(GLES20ShaderFactory.getShader("diffuseSpecular")));
		((Material) actor.getComponent("Material")).setDiffuseColor(0.3f, 0.3f, 0.3f);
		
		// COLLIDERS
		{
			// Add a collider container to the actor, to hold the primary and composite colliders
			actor.addComponent("Collider", new Collider());
			
			// Create any collider this way (this is an example for OBB Collider)
			// A SphereCollider uses the transform, center, and radius as default arguments
			float[] center = {0, 0, 0};
			float[] halfWidths = {0.5f, 0.5f, 4.0f};
			OBBCollider collider = new OBBCollider((Transform) actor.getComponent("Transform"), center, halfWidths);
			
			// Add created colliders to the Collider container, 1 for the large primary collider and then as many as you need
			// as sub colliders, added either to the OBBCollider or SphereCollider lists
			((Collider) actor.getComponent("Collider")).setPrimaryCollider(collider);
			((Collider) actor.getComponent("Collider")).addOBBCollider(collider);		
		}
		
		// For obstacles, add the forced movement that makes them accelerate towards the screen
		actor.addComponent("ForcedMovement", new ForcedMovement((Transform) actor.getComponent("Transform")));
		return actor;
	}
}
