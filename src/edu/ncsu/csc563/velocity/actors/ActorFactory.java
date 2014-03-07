package edu.ncsu.csc563.velocity.actors;

import java.util.Random;

import edu.ncsu.csc563.velocity.actors.components.*;
import edu.ncsu.csc563.velocity.actors.components.colliders.OBBCollider;
import edu.ncsu.csc563.velocity.rendering.GLES20ShaderFactory;
import edu.ncsu.csc563.velocity.resources.ResourceManager;

public class ActorFactory {
	public static String[] randomMeshes = {
		"meshes/Nathan/large_spike_ball.vmf",
		"meshes/Nathan/large_sqr_pyr.vmf",
		"meshes/Nathan/large_tri_pyr.vmf",
		"meshes/Nathan/lg_cube.vmf",
		"meshes/Nathan/lg_ell.vmf",
		"meshes/Nathan/lg_hplus.vmf",
		"meshes/Nathan/lg_plus.vmf",
		"meshes/Nathan/lg_step.vmf",
		"meshes/Nathan/long_hex_prism.vmf",
		"meshes/Nathan/long_rect_prism.vmf",
		"meshes/Nathan/short_hex_prism.vmf",
		"meshes/Nathan/short_rect_prism.vmf",
		"meshes/Nathan/sm_cube.vmf",
		"meshes/Nathan/sm_ell.vmf",
		"meshes/Nathan/sm_hplus.vmf",
		"meshes/Nathan/sm_plus.vmf",
		"meshes/Nathan/sm_step.vmf",
		"meshes/Nathan/small_spike_ball.vmf",
		"meshes/Nathan/small_sqr_pyr.vmf",
		"meshes/Nathan/small_tri_pyr.vmf"
	};
	
	public static Actor ship() {
		Actor actor = new Actor();
		actor.addComponent("Transform", new Transform());
		actor.addComponent("Mesh", ResourceManager.getMesh("meshes/RoundedCube.vmf"));
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
		float[] halfWidths = {0.5f, 0.5f, 0.5f};
		actor.addComponent("OBBCollider", new OBBCollider((Transform) actor.getComponent("Transform"), halfWidths));
		actor.addComponent("ForcedMovement", new ForcedMovement((Transform) actor.getComponent("Transform")));
		return actor;
	}
	
	public static Actor rectPrism() {
		Actor actor = new Actor();
		actor.addComponent("Transform", new Transform());
		actor.addComponent("Mesh", ResourceManager.getMesh("meshes/LongRoundedRectPrism.vmf"));
		actor.addComponent("Material", new Material(GLES20ShaderFactory.getShader("diffuseSpecular")));
		((Material) actor.getComponent("Material")).setDiffuseColor(0.3f, 0.3f, 0.3f);
		float[] halfWidths = {0.5f, 0.5f, 4.0f};
		actor.addComponent("OBBCollider", new OBBCollider((Transform) actor.getComponent("Transform"), halfWidths));
		actor.addComponent("ForcedMovement", new ForcedMovement((Transform) actor.getComponent("Transform")));
		return actor;
	}
	
	public static Actor randomObject() {
		Actor actor = new Actor();
		actor.addComponent("Transform", new Transform());
		int rand = new Random().nextInt(randomMeshes.length);
		actor.addComponent("Mesh", ResourceManager.getMesh(randomMeshes[rand]));
		actor.addComponent("Material", new Material(GLES20ShaderFactory.getShader("diffuseSpecular")));
		((Material) actor.getComponent("Material")).setDiffuseColor(0.3f, 0.3f, 0.3f);
		float[] halfWidths = {0.0f, 0.0f, 0.0f};
		actor.addComponent("OBBCollider", new OBBCollider((Transform) actor.getComponent("Transform"), halfWidths));
		actor.addComponent("ForcedMovement", new ForcedMovement((Transform) actor.getComponent("Transform")));
		return actor;
	}
}
