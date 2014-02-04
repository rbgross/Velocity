package edu.ncsu.csc563.velocity.actors;

import edu.ncsu.csc563.velocity.actors.components.*;
import edu.ncsu.csc563.velocity.rendering.GLES20ShaderFactory;
import edu.ncsu.csc563.velocity.utility.ResourceManager;

public class ActorFactory {
	public static Actor cube() {
		Actor actor = new Actor();
		actor.addComponent("Transform", new Transform());
		actor.addComponent("Mesh", ResourceManager.getMesh("meshes/Cube.vmf"));
		actor.addComponent("Material", new Material(GLES20ShaderFactory.getShader("shaders/diffuseSpecular")));
		((Material) actor.getComponent("Material")).setDiffuseColor(0.0f, 0.5f, 1.0f);
		return actor;
	}
}
