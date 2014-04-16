package edu.ncsu.csc563.velocity.actors;

import edu.ncsu.csc563.velocity.actors.components.*;
import edu.ncsu.csc563.velocity.actors.components.colliders.CircleCollider;
import edu.ncsu.csc563.velocity.actors.components.colliders.OBBCollider;
import edu.ncsu.csc563.velocity.actors.components.colliders.SphereCollider;
import edu.ncsu.csc563.velocity.rendering.GLES20ShaderFactory;
import edu.ncsu.csc563.velocity.resources.ResourceManager;

public class ActorFactory {	
	public static Actor ship() {
		Actor actor = new Actor();
		actor.addComponent("Transform", new Transform());
		((Transform) actor.getComponent("Transform")).setPosition(0, 0, -2);
		((Transform) actor.getComponent("Transform")).rotate(0, 180, 0);
		actor.addComponent("Mesh", ResourceManager.getMesh("meshes/AdorbsShip.vmf"));
		actor.addComponent("Material", new Material(GLES20ShaderFactory.getShader("diffuseSpecular")));
		((Material) actor.getComponent("Material")).setDiffuseColor(0.5f, 1.0f, 0.0f);
		actor.addComponent("Controller", new PlayerController(((Transform) actor.getComponent("Transform"))));
		actor.addComponent("Collider", new Collider());
		float[] center = {0, 0, 0};
		float[] halfWidths = {0.5f, 0.5f, 0.5f};
		float radius = 0.5f;
		OBBCollider collider = new OBBCollider((Transform) actor.getComponent("Transform"), center, halfWidths);
		SphereCollider sCollider = new SphereCollider((Transform) actor.getComponent("Transform"), center, radius);
		((Collider) actor.getComponent("Collider")).setPrimaryCollider(collider);
		((Collider) actor.getComponent("Collider")).addSphereCollider(sCollider);
		return actor;
	}
	
	public static Actor sphere() {
		Actor actor = new Actor();
		actor.addComponent("Transform", new Transform());
		actor.addComponent("Mesh", ResourceManager.getMesh("meshes/Sphere.vmf"));
		actor.addComponent("Material", new Material(GLES20ShaderFactory.getShader("diffuseSpecular")));
		((Material) actor.getComponent("Material")).setDiffuseColor(0.3f, 0.3f, 0.3f);
		actor.addComponent("Collider", new Collider());
		float[] center = {0, 0, 0};
		float radius = 0.5f;
		float[] halfWidths = {0.5f, 0.5f, 0.5f};
		OBBCollider obbCollider = new OBBCollider((Transform) actor.getComponent("Transform"), center, halfWidths);
		SphereCollider sphereCollider = new SphereCollider((Transform) actor.getComponent("Transform"), center, radius);
		((Collider) actor.getComponent("Collider")).setPrimaryCollider(obbCollider);
		((Collider) actor.getComponent("Collider")).addSphereCollider(sphereCollider);
		actor.addComponent("ForcedMovement", new ForcedMovement((Transform) actor.getComponent("Transform")));
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
	
	public static Actor large_spike_ball() {
		Actor actor = new Actor();
		actor.addComponent("Transform", new Transform());
		actor.addComponent("Mesh", ResourceManager.getMesh("meshes/Nathan2/large_spike_ball.vmf"));
		actor.addComponent("Material", new Material(GLES20ShaderFactory.getShader("diffuseSpecular")));
		((Material) actor.getComponent("Material")).setDiffuseColor(0.3f, 0.3f, 0.3f);
		{
			// Add a collider container to the actor, to hold the primary and composite colliders
			
		}
		actor.addComponent("ForcedMovement", new ForcedMovement((Transform) actor.getComponent("Transform")));
		return actor;
	}
	public static Actor large_sqr_pyr() {
		Actor actor = new Actor();
		actor.addComponent("Transform", new Transform());
		actor.addComponent("Mesh", ResourceManager.getMesh("meshes/Nathan2/large_sqr_pyr.vmf"));
		actor.addComponent("Material", new Material(GLES20ShaderFactory.getShader("diffuseSpecular")));
		((Material) actor.getComponent("Material")).setDiffuseColor(0.3f, 0.3f, 0.3f);
		actor.addComponent("ForcedMovement", new ForcedMovement((Transform) actor.getComponent("Transform")));
		return actor;
	}
	
	public static Actor large_tri_pyr() {
		Actor actor = new Actor();
		actor.addComponent("Transform", new Transform());
		actor.addComponent("Mesh", ResourceManager.getMesh("meshes/Nathan2/large_tri_pyr.vmf"));
		actor.addComponent("Material", new Material(GLES20ShaderFactory.getShader("diffuseSpecular")));
		((Material) actor.getComponent("Material")).setDiffuseColor(0.3f, 0.3f, 0.3f);
		actor.addComponent("ForcedMovement", new ForcedMovement((Transform) actor.getComponent("Transform")));
		return actor;
	}
	
	public static Actor lg_ell() {
		Actor actor = new Actor();
		actor.addComponent("Transform", new Transform());
		actor.addComponent("Mesh", ResourceManager.getMesh("meshes/Nathan2/lg_ell.vmf"));
		actor.addComponent("Material", new Material(GLES20ShaderFactory.getShader("diffuseSpecular")));
		((Material) actor.getComponent("Material")).setDiffuseColor(0.3f, 0.3f, 0.3f);
		// COLLIDERS
		{
			// Add a collider container to the actor, to hold the primary and composite colliders
			actor.addComponent("Collider", new Collider());
			
			// Create any collider this way (this is an example for OBB Collider)
			// A SphereCollider uses the transform, center, and radius as default arguments
			float[] center = {0, 0, 0};
			float[] halfWidths = {1f, 0.5f, 1.875f};
			OBBCollider collider = new OBBCollider((Transform) actor.getComponent("Transform"), center, halfWidths);
			
			// Add created colliders to the Collider container, 1 for the large primary collider and then as many as you need
			// as sub colliders, added either to the OBBCollider or SphereCollider lists
			((Collider) actor.getComponent("Collider")).setPrimaryCollider(collider);
			((Collider) actor.getComponent("Collider")).addOBBCollider(collider);
			
			float[] center1 = {-0.77273f, 0, 1.13068f};
			float[] halfWidths1 = {1f, 1.0f, 1.0f};
			OBBCollider subCollider1 = new OBBCollider((Transform) actor.getComponent("Transform"), center1, halfWidths1);
			((Collider) actor.getComponent("Collider")).addOBBCollider(subCollider1);
			
			float[] center2 = {0.22727f, 0, 1.13068f};
			OBBCollider subCollider2 = new OBBCollider((Transform) actor.getComponent("Transform"), center2, halfWidths1);
			((Collider) actor.getComponent("Collider")).addOBBCollider(subCollider2);
			
			float[] center3 = {0.22727f, 0, 0.25568f};
			OBBCollider subCollider3 = new OBBCollider((Transform) actor.getComponent("Transform"), center3, halfWidths1);
			((Collider) actor.getComponent("Collider")).addOBBCollider(subCollider3);
			
			float[] center4 = {0.22727f, 0, -0.68182f};
			OBBCollider subCollider4 = new OBBCollider((Transform) actor.getComponent("Transform"), center4, halfWidths1);
			((Collider) actor.getComponent("Collider")).addOBBCollider(subCollider4);
			
			float[] center5 = {0.22727f, 0, -1.68182f};
			OBBCollider subCollider5 = new OBBCollider((Transform) actor.getComponent("Transform"), center5, halfWidths1);
			((Collider) actor.getComponent("Collider")).addOBBCollider(subCollider5);
		}
		actor.addComponent("ForcedMovement", new ForcedMovement((Transform) actor.getComponent("Transform")));
		return actor;
	}
	
	public static Actor lg_hplus() {
		Actor actor = new Actor();
		actor.addComponent("Transform", new Transform());
		actor.addComponent("Mesh", ResourceManager.getMesh("meshes/Nathan2/lg_hplus.vmf"));
		actor.addComponent("Material", new Material(GLES20ShaderFactory.getShader("diffuseSpecular")));
		((Material) actor.getComponent("Material")).setDiffuseColor(0.3f, 0.3f, 0.3f);
		// COLLIDERS
		{
			// Add a collider container to the actor, to hold the primary and composite colliders
			actor.addComponent("Collider", new Collider());
			
			// Create any collider this way (this is an example for OBB Collider)
			// A SphereCollider uses the transform, center, and radius as default arguments
			float[] center = {0, 0, 0};
			float[] halfWidths = {2.25f, 0.75f, 1.5f};
			OBBCollider collider = new OBBCollider((Transform) actor.getComponent("Transform"), center, halfWidths);
			
			// Add created colliders to the Collider container, 1 for the large primary collider and then as many as you need
			// as sub colliders, added either to the OBBCollider or SphereCollider lists
			((Collider) actor.getComponent("Collider")).setPrimaryCollider(collider);
			((Collider) actor.getComponent("Collider")).addOBBCollider(collider);
			
			float[] center1 = {0, 0, -0.41667f};
			float[] halfWidths1 = {1f, 1.0f, 1.0f};
			OBBCollider subCollider1 = new OBBCollider((Transform) actor.getComponent("Transform"), center1, halfWidths1);
			((Collider) actor.getComponent("Collider")).addOBBCollider(subCollider1);
			
			float[] center2 = {0, 0, 1.08333f};
			OBBCollider subCollider2 = new OBBCollider((Transform) actor.getComponent("Transform"), center2, halfWidths1);
			((Collider) actor.getComponent("Collider")).addOBBCollider(subCollider2);
			
			float[] center3 = {1.5f, 0, -0.41667f};
			OBBCollider subCollider3 = new OBBCollider((Transform) actor.getComponent("Transform"), center3, halfWidths1);
			((Collider) actor.getComponent("Collider")).addOBBCollider(subCollider3);
			
			float[] center4 = {-1.5f, 0, -0.41667f};
			OBBCollider subCollider4 = new OBBCollider((Transform) actor.getComponent("Transform"), center4, halfWidths1);
			((Collider) actor.getComponent("Collider")).addOBBCollider(subCollider4);
		}
		actor.addComponent("ForcedMovement", new ForcedMovement((Transform) actor.getComponent("Transform")));
		return actor;
	}
	
	public static Actor lg_plus() {
		Actor actor = new Actor();
		actor.addComponent("Transform", new Transform());
		actor.addComponent("Mesh", ResourceManager.getMesh("meshes/Nathan2/lg_plus.vmf"));
		actor.addComponent("Material", new Material(GLES20ShaderFactory.getShader("diffuseSpecular")));
		((Material) actor.getComponent("Material")).setDiffuseColor(0.3f, 0.3f, 0.3f);
		// COLLIDERS
		{
			// Add a collider container to the actor, to hold the primary and composite colliders
			actor.addComponent("Collider", new Collider());
			
			// Create any collider this way (this is an example for OBB Collider)
			// A SphereCollider uses the transform, center, and radius as default arguments
			float[] center = {0, 0, 0};
			float[] halfWidths = {1f, 1f, 1f};
			OBBCollider collider = new OBBCollider((Transform) actor.getComponent("Transform"), center, halfWidths);
			
			// Add created colliders to the Collider container, 1 for the large primary collider and then as many as you need
			// as sub colliders, added either to the OBBCollider or SphereCollider lists
			((Collider) actor.getComponent("Collider")).setPrimaryCollider(collider);
			((Collider) actor.getComponent("Collider")).addOBBCollider(collider);
			
			float[] center1 = {0, 0, 0};
			float[] halfWidths1 = {1f, 1.0f, 1.0f};
			OBBCollider subCollider1 = new OBBCollider((Transform) actor.getComponent("Transform"), center1, halfWidths1);
			((Collider) actor.getComponent("Collider")).addOBBCollider(subCollider1);
			
			float[] center2 = {0, 0.6667f, 0};
			OBBCollider subCollider2 = new OBBCollider((Transform) actor.getComponent("Transform"), center2, halfWidths1);
			((Collider) actor.getComponent("Collider")).addOBBCollider(subCollider2);
			
			float[] center3 = {0, -0.6667f, 0};
			OBBCollider subCollider3 = new OBBCollider((Transform) actor.getComponent("Transform"), center3, halfWidths1);
			((Collider) actor.getComponent("Collider")).addOBBCollider(subCollider3);
			
			float[] center4 = {0.6667f, 0, 0};
			OBBCollider subCollider4 = new OBBCollider((Transform) actor.getComponent("Transform"), center4, halfWidths1);
			((Collider) actor.getComponent("Collider")).addOBBCollider(subCollider4);
			
			float[] center5 = {-0.6667f, 0, 0};
			OBBCollider subCollider5 = new OBBCollider((Transform) actor.getComponent("Transform"), center5, halfWidths1);
			((Collider) actor.getComponent("Collider")).addOBBCollider(subCollider5);
			
			float[] center6 = {0, 0, 0.6667f};
			OBBCollider subCollider6 = new OBBCollider((Transform) actor.getComponent("Transform"), center6, halfWidths1);
			((Collider) actor.getComponent("Collider")).addOBBCollider(subCollider6);
			
			float[] center7 = {0, 0, -0.6667f};
			OBBCollider subCollider7 = new OBBCollider((Transform) actor.getComponent("Transform"), center7, halfWidths1);
			((Collider) actor.getComponent("Collider")).addOBBCollider(subCollider7);
		}
		actor.addComponent("ForcedMovement", new ForcedMovement((Transform) actor.getComponent("Transform")));
		return actor;
	}
	
	public static Actor lg_step() {
		Actor actor = new Actor();
		actor.addComponent("Transform", new Transform());
		actor.addComponent("Mesh", ResourceManager.getMesh("meshes/Nathan2/lg_step.vmf"));
		actor.addComponent("Material", new Material(GLES20ShaderFactory.getShader("diffuseSpecular")));
		((Material) actor.getComponent("Material")).setDiffuseColor(0.3f, 0.3f, 0.3f);
		// COLLIDERS
		{
			// Add a collider container to the actor, to hold the primary and composite colliders
			actor.addComponent("Collider", new Collider());
			
			// Create any collider this way (this is an example for OBB Collider)
			// A SphereCollider uses the transform, center, and radius as default arguments
			float[] center = {0, 0, 0};
			float[] halfWidths = {2.25f, 0.75f, 1.5f};
			OBBCollider collider = new OBBCollider((Transform) actor.getComponent("Transform"), center, halfWidths);
			
			// Add created colliders to the Collider container, 1 for the large primary collider and then as many as you need
			// as sub colliders, added either to the OBBCollider or SphereCollider lists
			((Collider) actor.getComponent("Collider")).setPrimaryCollider(collider);
			((Collider) actor.getComponent("Collider")).addOBBCollider(collider);
			
			float[] center1 = {0,0,-0.75f};
			float[] halfWidths1 = {1.5f, 1.5f, 1.5f};
			OBBCollider subCollider1 = new OBBCollider((Transform) actor.getComponent("Transform"), center1, halfWidths1);
			((Collider) actor.getComponent("Collider")).addOBBCollider(subCollider1);
			
			float[] center2 = {1.5f,0,-0.75f};
			OBBCollider subCollider2 = new OBBCollider((Transform) actor.getComponent("Transform"), center2, halfWidths1);
			((Collider) actor.getComponent("Collider")).addOBBCollider(subCollider2);
			
			float[] center3 = {0,0,0.75f};
			OBBCollider subCollider3 = new OBBCollider((Transform) actor.getComponent("Transform"), center3, halfWidths1);
			((Collider) actor.getComponent("Collider")).addOBBCollider(subCollider3);
			
			float[] center4 = {-1.5f,0,-1.5f};
			OBBCollider subCollider4 = new OBBCollider((Transform) actor.getComponent("Transform"), center4, halfWidths1);
			((Collider) actor.getComponent("Collider")).addOBBCollider(subCollider4);
		}
		actor.addComponent("ForcedMovement", new ForcedMovement((Transform) actor.getComponent("Transform")));
		return actor;
	}
	
	public static Actor rnd_cube_panel() {
		// Make a new actor
		Actor actor = new Actor();
		
		// Add a transform; the center of this is the center of the primary (big) collider as well
		actor.addComponent("Transform", new Transform());
		
		// Add the appropriate mesh from the file (add these to the assets/meshes/Nathan2 folder)
		actor.addComponent("Mesh", ResourceManager.getMesh("meshes/Nathan2/rnd_cube_panel.vmf"));
		
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
			float[] halfWidths = {1.5f, 0.125f, 0.75f};
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
	
	public static Actor long_rect_prism() {
		// Make a new actor
		Actor actor = new Actor();
		
		// Add a transform; the center of this is the center of the primary (big) collider as well
		actor.addComponent("Transform", new Transform());
		
		// Add the appropriate mesh from the file (add these to the assets/meshes/Nathan2 folder)
		actor.addComponent("Mesh", ResourceManager.getMesh("meshes/Nathan2/long_rect_prism.vmf"));
		
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
			float[] halfWidths = {4f, 1f, 1f};
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
	
	public static Actor short_rect_prism() {
		// Make a new actor
		Actor actor = new Actor();
		
		// Add a transform; the center of this is the center of the primary (big) collider as well
		actor.addComponent("Transform", new Transform());
		
		// Add the appropriate mesh from the file (add these to the assets/meshes/Nathan2 folder)
		actor.addComponent("Mesh", ResourceManager.getMesh("meshes/Nathan2/short_rect_prism.vmf"));
		
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
			float[] halfWidths = {3f, 1f, 1f};
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
	
	public static Actor sm_cube() {
		// Make a new actor
		Actor actor = new Actor();
		
		// Add a transform; the center of this is the center of the primary (big) collider as well
		actor.addComponent("Transform", new Transform());
		
		// Add the appropriate mesh from the file (add these to the assets/meshes/Nathan2 folder)
		actor.addComponent("Mesh", ResourceManager.getMesh("meshes/Nathan2/sm_cube.vmf"));
		
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
			float[] halfWidths = {1f, 1f, 1f};
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
	
	public static Actor sm_ell() {
		Actor actor = new Actor();
		actor.addComponent("Transform", new Transform());
		actor.addComponent("Mesh", ResourceManager.getMesh("meshes/Nathan2/sm_ell.vmf"));
		actor.addComponent("Material", new Material(GLES20ShaderFactory.getShader("diffuseSpecular")));
		((Material) actor.getComponent("Material")).setDiffuseColor(0.3f, 0.3f, 0.3f);
		// COLLIDERS
		{
			// Add a collider container to the actor, to hold the primary and composite colliders
			actor.addComponent("Collider", new Collider());
			
			// Create any collider this way (this is an example for OBB Collider)
			// A SphereCollider uses the transform, center, and radius as default arguments
			float[] center = {0, 0, 0};
			float[] halfWidths = {0.5f, 0.25f, 0.9375f};
			OBBCollider collider = new OBBCollider((Transform) actor.getComponent("Transform"), center, halfWidths);
			
			// Add created colliders to the Collider container, 1 for the large primary collider and then as many as you need
			// as sub colliders, added either to the OBBCollider or SphereCollider lists
			((Collider) actor.getComponent("Collider")).setPrimaryCollider(collider);
			((Collider) actor.getComponent("Collider")).addOBBCollider(collider);
			
			float[] center1 = {-0.38636f, 0, 0.56534f};
			float[] halfWidths1 = {0.5f, 0.5f, 0.5f};
			OBBCollider subCollider1 = new OBBCollider((Transform) actor.getComponent("Transform"), center1, halfWidths1);
			((Collider) actor.getComponent("Collider")).addOBBCollider(subCollider1);
			
			float[] center2 = {0.11364f, 0, 0.56534f};
			OBBCollider subCollider2 = new OBBCollider((Transform) actor.getComponent("Transform"), center2, halfWidths1);
			((Collider) actor.getComponent("Collider")).addOBBCollider(subCollider2);
			
			float[] center3 = {0.11364f, 0, 0.12784f};
			OBBCollider subCollider3 = new OBBCollider((Transform) actor.getComponent("Transform"), center3, halfWidths1);
			((Collider) actor.getComponent("Collider")).addOBBCollider(subCollider3);
			
			float[] center4 = {0.11364f, 0, -0.34091f};
			OBBCollider subCollider4 = new OBBCollider((Transform) actor.getComponent("Transform"), center4, halfWidths1);
			((Collider) actor.getComponent("Collider")).addOBBCollider(subCollider4);
			
			float[] center5 = {0.11364f, 0, -0.84091f};
			OBBCollider subCollider5 = new OBBCollider((Transform) actor.getComponent("Transform"), center5, halfWidths1);
			((Collider) actor.getComponent("Collider")).addOBBCollider(subCollider5);
		}
		actor.addComponent("ForcedMovement", new ForcedMovement((Transform) actor.getComponent("Transform")));
		return actor;
	}
	
	public static Actor sm_plus() {
		Actor actor = new Actor();
		actor.addComponent("Transform", new Transform());
		actor.addComponent("Mesh", ResourceManager.getMesh("meshes/Nathan2/sm_plus.vmf"));
		actor.addComponent("Material", new Material(GLES20ShaderFactory.getShader("diffuseSpecular")));
		((Material) actor.getComponent("Material")).setDiffuseColor(0.3f, 0.3f, 0.3f);
		// COLLIDERS
		{
			// Add a collider container to the actor, to hold the primary and composite colliders
			actor.addComponent("Collider", new Collider());
			
			// Create any collider this way (this is an example for OBB Collider)
			// A SphereCollider uses the transform, center, and radius as default arguments
			float[] center = {0, 0, 0};
			float[] halfWidths = {1f, 1f, 1f};
			OBBCollider collider = new OBBCollider((Transform) actor.getComponent("Transform"), center, halfWidths);
			
			// Add created colliders to the Collider container, 1 for the large primary collider and then as many as you need
			// as sub colliders, added either to the OBBCollider or SphereCollider lists
			((Collider) actor.getComponent("Collider")).setPrimaryCollider(collider);
			
			float[] center1 = {0, 0, 0};
			float[] halfWidths1 = {0.166665f, 0.166665f, 0.166665f};
			OBBCollider subCollider1 = new OBBCollider((Transform) actor.getComponent("Transform"), center1, halfWidths1);
			((Collider) actor.getComponent("Collider")).addOBBCollider(subCollider1);
			
			float[] center2 = {0, 0, 0.33333f};
			OBBCollider subCollider2 = new OBBCollider((Transform) actor.getComponent("Transform"), center2, halfWidths1);
			((Collider) actor.getComponent("Collider")).addOBBCollider(subCollider2);
			
			float[] center3 = {0, 0, -0.33333f};
			OBBCollider subCollider3 = new OBBCollider((Transform) actor.getComponent("Transform"), center3, halfWidths1);
			((Collider) actor.getComponent("Collider")).addOBBCollider(subCollider3);
			
			float[] center4 = {0.33333f, 0, 0};
			OBBCollider subCollider4 = new OBBCollider((Transform) actor.getComponent("Transform"), center4, halfWidths1);
			((Collider) actor.getComponent("Collider")).addOBBCollider(subCollider4);
			
			float[] center5 = {-0.33333f, 0, 0};
			OBBCollider subCollider5 = new OBBCollider((Transform) actor.getComponent("Transform"), center5, halfWidths1);
			((Collider) actor.getComponent("Collider")).addOBBCollider(subCollider5);
			
			float[] center6 = {0, 0.33333f, 0};
			OBBCollider subCollider6 = new OBBCollider((Transform) actor.getComponent("Transform"), center6, halfWidths1);
			((Collider) actor.getComponent("Collider")).addOBBCollider(subCollider6);
			
			float[] center7 = {0, -0.33333f, 0};
			OBBCollider subCollider7 = new OBBCollider((Transform) actor.getComponent("Transform"), center7, halfWidths1);
			((Collider) actor.getComponent("Collider")).addOBBCollider(subCollider7);
		}
		actor.addComponent("ForcedMovement", new ForcedMovement((Transform) actor.getComponent("Transform")));
		return actor;
	}
	
	public static Actor sm_step() {
		Actor actor = new Actor();
		actor.addComponent("Transform", new Transform());
		actor.addComponent("Mesh", ResourceManager.getMesh("meshes/Nathan2/sm_step.vmf"));
		actor.addComponent("Material", new Material(GLES20ShaderFactory.getShader("diffuseSpecular")));
		((Material) actor.getComponent("Material")).setDiffuseColor(0.3f, 0.3f, 0.3f);
		// COLLIDERS
		{
			// Add a collider container to the actor, to hold the primary and composite colliders
			actor.addComponent("Collider", new Collider());
			
			// Create any collider this way (this is an example for OBB Collider)
			// A SphereCollider uses the transform, center, and radius as default arguments
			float[] center = {0, 0, 0};
			float[] halfWidths = {1.5f, 0.5f, 1f};
			OBBCollider collider = new OBBCollider((Transform) actor.getComponent("Transform"), center, halfWidths);
			
			// Add created colliders to the Collider container, 1 for the large primary collider and then as many as you need
			// as sub colliders, added either to the OBBCollider or SphereCollider lists
			((Collider) actor.getComponent("Collider")).setPrimaryCollider(collider);
			((Collider) actor.getComponent("Collider")).addOBBCollider(collider);
			
			float[] center1 = {0,0,0f};
			float[] halfWidths1 = {1.5f, 1.5f, 1.5f};
			OBBCollider subCollider1 = new OBBCollider((Transform) actor.getComponent("Transform"), center1, halfWidths1);
			((Collider) actor.getComponent("Collider")).addOBBCollider(subCollider1);
			
			float[] center2 = {0, 0, 0.5f};
			OBBCollider subCollider2 = new OBBCollider((Transform) actor.getComponent("Transform"), center2, halfWidths1);
			((Collider) actor.getComponent("Collider")).addOBBCollider(subCollider2);
			
			float[] center3 = {-1, 0, 0.5f};
			OBBCollider subCollider3 = new OBBCollider((Transform) actor.getComponent("Transform"), center3, halfWidths1);
			((Collider) actor.getComponent("Collider")).addOBBCollider(subCollider3);
			
			float[] center4 = {1, 0, -0.5f};
			OBBCollider subCollider4 = new OBBCollider((Transform) actor.getComponent("Transform"), center4, halfWidths1);
			((Collider) actor.getComponent("Collider")).addOBBCollider(subCollider4);
		}
		actor.addComponent("ForcedMovement", new ForcedMovement((Transform) actor.getComponent("Transform")));
		return actor;
	}
	
	public static Actor token() {
		Actor actor = new Actor();
		actor.addComponent("Transform", new Transform());
		((Transform) actor.getComponent("Transform")).rotate(0, 90, 0);
		actor.addComponent("Mesh", ResourceManager.getMesh("meshes/Nathan2/token.vmf"));
		actor.addComponent("Material", new Material(GLES20ShaderFactory.getShader("diffuseSpecular")));
		((Material) actor.getComponent("Material")).setDiffuseColor(0.25f, 0.75f, 1.0f);
		actor.addComponent("Collider", new Collider());
		float[] center = {0, 0, 0};
		float radius = 0.25f;
		float[] halfWidths = {0.025f, 0.25f, 0.25f};
		OBBCollider obbCollider = new OBBCollider((Transform) actor.getComponent("Transform"), center, halfWidths);
		CircleCollider circleCollider = new CircleCollider((Transform) actor.getComponent("Transform"), center, radius);
		((Collider) actor.getComponent("Collider")).setPrimaryCollider(obbCollider);
		((Collider) actor.getComponent("Collider")).addCircleCollider(circleCollider);
		actor.addComponent("ForcedMovement", new ForcedMovement((Transform) actor.getComponent("Transform")));
		return actor;
	}
}
