package edu.ncsu.csc563.velocity.scene;

import java.util.ArrayList;

import android.opengl.Matrix;

import edu.ncsu.csc563.velocity.GLES20InteractiveSurfaceView;
import edu.ncsu.csc563.velocity.actors.Actor;
import edu.ncsu.csc563.velocity.actors.ActorFactory;
import edu.ncsu.csc563.velocity.actors.components.Transform;

public class Scene {
	//private ArrayList<Actor> mActors;
	public static Scene instance;
	private Actor cube;
	
	private Scene() {
		//this.mActors = new ArrayList<Actor>();
		//this.mActors.add(ActorFactory.cube());
		this.cube = ActorFactory.cube();
	}
	
	public static Scene getInstance() {
		if (instance == null) {
			instance = new Scene();
		}
		return instance;
	}
	
	public void updateScene() {
		//for (Actor actor : this.mActors) {
		//	actor.update();
		//}		
		this.cube.update();
	}
	
	public void drawScene() {
		//for (Actor actor : this.mActors) {
		//	actor.draw();
		//}
		this.cube.draw();
	}
}
