package edu.ncsu.csc563.velocity;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

import android.util.Log;

import edu.ncsu.csc563.velocity.components.Mesh;

public class ResourceManager {
	/** HashMap of meshes loaded from file */
	public static HashMap<String, Mesh> mMeshes = new HashMap<String, Mesh>();
	
	public static void loadMesh(String fileName, InputStream fileStream) throws IOException {
		if (ResourceManager.mMeshes.containsKey(fileName)) {
			Log.e("ResourceManager", "Mesh " + fileName + " has already been loaded.");
			return;
		}
		
		DataInputStream is = new DataInputStream(fileStream);
		
		int vertexCount = is.readInt();
		//System.out.println("Vertex Count is: " + vertexCount);
		float[] rawVertices = new float[vertexCount];
		for (int i = 0; i < vertexCount; i++) {
			rawVertices[i] = is.readFloat();
		}
		
		/*
		for (int i = 0; i < vertexCount; i += 3) {
			System.out.println(rawVertices[i] + ", " + rawVertices[i + 1] + ", " + rawVertices[i + 2]);
		}
		*/
		
		int elementCount = is.readInt();
		//System.out.println("Element Count is: " + elementCount);
		int[] rawElements = new int[elementCount];
		for (int i = 0; i < elementCount; i++) {
			rawElements[i] = is.readInt();
		}
		
		/*
		for (int i = 0; i < elementCount; i++) {
			System.out.println(rawElements[i]);
		}
		*/
		
		is.close();
		
		System.out.println("Mesh is done loading.");
		
		ResourceManager.mMeshes.put(fileName, new Mesh(rawVertices, rawElements));		
	}
}
