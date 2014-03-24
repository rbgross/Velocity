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
		float[] halfWidths = {0.5f, 0.5f, 0.5f};
		OBBCollider collider = new OBBCollider((Transform) actor.getComponent("Transform"), halfWidths);
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
		float[] halfWidths = {0.5f, 0.5f, 0.5f};
		OBBCollider collider = new OBBCollider((Transform) actor.getComponent("Transform"), halfWidths);
		((Collider) actor.getComponent("Collider")).setPrimaryCollider(collider);
		((Collider) actor.getComponent("Collider")).addOBBCollider(collider);
		actor.addComponent("ForcedMovement", new ForcedMovement((Transform) actor.getComponent("Transform")));
		return actor;
	}
	
	public static Actor rectPrism() {
		Actor actor = new Actor();
		actor.addComponent("Transform", new Transform());
		actor.addComponent("Mesh", ResourceManager.getMesh("meshes/LongRoundedRectPrism.vmf"));
		actor.addComponent("Material", new Material(GLES20ShaderFactory.getShader("diffuseSpecular")));
		((Material) actor.getComponent("Material")).setDiffuseColor(0.3f, 0.3f, 0.3f);
		actor.addComponent("Collider", new Collider());
		float[] halfWidths = {0.5f, 0.5f, 4.0f};
		OBBCollider collider = new OBBCollider((Transform) actor.getComponent("Transform"), halfWidths);
		((Collider) actor.getComponent("Collider")).setPrimaryCollider(collider);
		((Collider) actor.getComponent("Collider")).addOBBCollider(collider);		
		actor.addComponent("OBBCollider", new OBBCollider((Transform) actor.getComponent("Transform"), halfWidths));
		actor.addComponent("ForcedMovement", new ForcedMovement((Transform) actor.getComponent("Transform")));
		return actor;
	}
}
