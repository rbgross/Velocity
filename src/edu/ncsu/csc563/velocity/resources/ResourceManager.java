package edu.ncsu.csc563.velocity.resources;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.opengl.GLES20;
import android.opengl.GLUtils;

import edu.ncsu.csc563.velocity.actors.components.Mesh;

public class ResourceManager {
	/** HashMap of meshes loaded from file */
	private static HashMap<String, Mesh> mMeshes = new HashMap<String, Mesh>();
	private static HashMap<String, Integer> mTextures = new HashMap<String, Integer>();
	
	public static void loadTexture(final Context context, final int resourceId)
	{
	    final int[] textureHandle = new int[1];
	 
	    GLES20.glGenTextures(1, textureHandle, 0);
	 
	    if (textureHandle[0] != 0)
	    {
	        final BitmapFactory.Options options = new BitmapFactory.Options();
	        options.inScaled = false;   // No pre-scaling
	 
	        // Read in the resource
	        final Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), resourceId, options);
	 
	        // Bind to the texture in OpenGL
	        GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, textureHandle[0]);
	 
	        // Set filtering
	        GLES20.glTexParameteri(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_MIN_FILTER, GLES20.GL_NEAREST);
	        GLES20.glTexParameteri(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_MAG_FILTER, GLES20.GL_NEAREST);
	 
	        // Load the bitmap into the bound texture.
	        GLUtils.texImage2D(GLES20.GL_TEXTURE_2D, 0, bitmap, 0);
	 
	        // Recycle the bitmap, since its data has been loaded into OpenGL.
	        bitmap.recycle();
	    }
	 
	    if (textureHandle[0] == 0)
	    {
	        throw new RuntimeException("Error loading texture.");
	    }
	 
	   ResourceManager.mTextures.put("ship", textureHandle[0]);
	}
	
	public static int getTexture() {
		return ResourceManager.mTextures.get("ship");
	}
	
	public static void loadMesh(String fileName, InputStream fileStream) throws IOException {		
		if (ResourceManager.mMeshes.get(fileName) == null) {
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
		
		ResourceManager.mMeshes.get(fileName).setOGLResources();
	}
	
	public static Mesh getMesh(String fileName) {
		return ResourceManager.mMeshes.get(fileName);
	}
}
