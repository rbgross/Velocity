package edu.ncsu.csc563.velocity.utility;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

import android.util.Log;

import edu.ncsu.csc563.velocity.components.Mesh;

public class ResourceManager {
	/** HashMap of meshes loaded from file */
	private static HashMap<String, Mesh> mMeshes = new HashMap<String, Mesh>();
	
	public static void loadMesh(String fileName, InputStream fileStream) throws IOException {
		/*
		if (ResourceManager.mMeshes.containsKey(fileName)) {
			Log.e("ResourceManager", "Mesh " + fileName + " has already been loaded.");
			return;
		}
		*/
		
		DataInputStream is = new DataInputStream(fileStream);
		
		int vertexCount = is.readInt();
		float[] rawVertices = new float[vertexCount];
		for (int i = 0; i < vertexCount; i++) {
			rawVertices[i] = is.readFloat();
		}
		
		int elementCount = is.readInt();
		int[] rawElements = new int[elementCount];
		for (int i = 0; i < elementCount; i++) {
			rawElements[i] = is.readInt();
		}
		
		is.close();
		
		ResourceManager.mMeshes.put(fileName, new Mesh(rawVertices, rawElements));		
	}
	
	public static Mesh getMesh(String fileName) {
		return ResourceManager.mMeshes.get(fileName);
	}
}
