package edu.ncsu.csc563.velocity.rendering;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;

import android.opengl.GLES20;

/**
 * The ShaderFactory is a set of static convenience functions that will construct Shaders
 * based on the provided shader code for use by other parts of the application
 */
public class GLES20ShaderFactory {
	/** HashMap of meshes loaded from file */
	private static HashMap<String, GLES20Shader> mShaders = new HashMap<String, GLES20Shader>();
	
	//The vertex shader takes in attributes and uniform values and returns
	//a vec4 that represents the position of the vertex processed in this shader
	//in x, y, z, w coordinates, called homogeneous coordinates
	
	//The fragment shader uses varying values and uniform values to output
	//color data for the particular on-screen pixel that the shader is processing;
	//Varying values are values that are output by the vertex shader per vertex, but
	//are interpolated across the surface of the triangle based on distances from the vertices
	//the color is output as a vec4 in r, g, b, a coordinates
	
	//In terms of the Model, View, Projection matrices, multiplying the coordinates of a base
	//model (usually centered around 0, 0, 0 for ease of manipulation, but this isn't a requirement)
	//by the Model matrix transforms the object from Model space to World space; multiplying that
	//by the View matrix transforms the object from World space to Eye space (or camera space, or view space);
	//multiplying that by the Projection matrix transforms the object from Eye space to Clip space; a last, further
	//transformation is automatically made that transforms these values into Normalized Device Coordinates, which can
	//vary by device but typically the center is point (0,0), far left  is (-1,0), and so on
	
	/**
	 * Creates a shader that takes in position and normal data, MVP matrices,
	 * a light position, and a set color to render the object with diffuse and specular
	 * lighting in color provided in diffuseColor
	 * @return shader with diffuse/specular lighting
	 * @throws IOException 
	 */
	public static void loadShader(String shaderName, InputStream vertexStream, InputStream fragmentStream) throws IOException {		
		String temp;
		
		String vertexShaderCode = "";
		BufferedReader vertexBR = new BufferedReader(new InputStreamReader(vertexStream));
		while ((temp = vertexBR.readLine()) != null) {
			vertexShaderCode += temp;
		}		
		vertexBR.close();
		
		String fragmentShaderCode = "";
		BufferedReader fragmentBR = new BufferedReader(new InputStreamReader(fragmentStream));
		while ((temp = fragmentBR.readLine()) != null) {
			fragmentShaderCode += temp;
		}		
		fragmentBR.close();
		
		//Create the shader
		GLES20Shader shader = new GLES20Shader();
		
		//Add the vertex and fragment shader code to the shader
		shader.loadShader(GLES20.GL_VERTEX_SHADER, vertexShaderCode);
		shader.loadShader(GLES20.GL_FRAGMENT_SHADER, fragmentShaderCode);
		
		//Define the set locations of the attributes "position" and "normal" (0 and 1 respectively)
		shader.bindAttribute(GLES20Shader.ATTRIB_POSITION, "position");
		shader.bindAttribute(GLES20Shader.ATTRIB_NORMAL, "normal");
		
		//Build the shader with the provided vertex and fragment pieces
		shader.linkProgram();
		
		//Define the uniform values found in this shader in a hashmap in the shader for faster future lookup
		//and assignment
		shader.addUniform("model");
		shader.addUniform("view");
		shader.addUniform("proj");
		shader.addUniform("lightPosition");
		shader.addUniform("diffuseColor");
		
		//Return the created shader object
		GLES20ShaderFactory.mShaders.put(shaderName, shader);	
	}
	
	public static GLES20Shader getShader(String shaderName) {		
		return GLES20ShaderFactory.mShaders.get(shaderName);
	}
}
