package edu.ncsu.csc563.velocity.actors;

import edu.ncsu.csc563.velocity.actors.components.*;
import edu.ncsu.csc563.velocity.rendering.GLES20ShaderFactory;
import edu.ncsu.csc563.velocity.utility.ResourceManager;

public class ActorFactory {
	public static Actor ship() {
		Actor actor = new Actor();
		actor.addComponent("Transform", new Transform());
		actor.addComponent("Mesh", ResourceManager.getMesh("meshes/Sphere.vmf"));
		actor.addComponent("Material", new Material(GLES20ShaderFactory.getShader("diffuseSpecular")));
		((Material) actor.getComponent("Material")).setDiffuseColor(0.0f, 0.5f, 1.0f);
		actor.addComponent("Controller", new PlayerController(((Transform) actor.getComponent("Transform"))));
		return actor;
	}
	
	public static Actor dragon() {
		Actor actor = new Actor();
		actor.addComponent("Transform", new Transform());
		actor.addComponent("Mesh", ResourceManager.getMesh("meshes/Dragon.vmf"));
		actor.addComponent("Material", new Material(GLES20ShaderFactory.getShader("diffuseSpecular")));
		((Material) actor.getComponent("Material")).setDiffuseColor(0.5f, 1.0f, 0.0f);
		return actor;
	}
	
	public static Actor cube() {
		Actor actor = new Actor();
		actor.addComponent("Transform", new Transform());
		actor.addComponent("Mesh", ResourceManager.getMesh("meshes/RoundedCube.vmf"));
		actor.addComponent("Material", new Material(GLES20ShaderFactory.getShader("diffuseSpecular")));
		((Material) actor.getComponent("Material")).setDiffuseColor(0.3f, 0.3f, 0.3f);
		return actor;
	}
	
	public static Actor rectPrism() {
		Actor actor = new Actor();
		actor.addComponent("Transform", new Transform());
		actor.addComponent("Mesh", ResourceManager.getMesh("meshes/LongRoundedRectPrism.vmf"));
		actor.addComponent("Material", new Material(GLES20ShaderFactory.getShader("diffuseSpecular")));
		((Material) actor.getComponent("Material")).setDiffuseColor(0.3f, 0.3f, 0.3f);
		actor.addComponent("ForcedMotion", new ForcedMotion(((Transform) actor.getComponent("Transform"))));
		return actor;
	}
}
