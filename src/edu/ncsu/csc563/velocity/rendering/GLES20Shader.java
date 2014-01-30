package edu.ncsu.csc563.velocity.rendering;

import java.util.HashMap;

import android.opengl.GLES20;
import android.util.Log;

/**
 * Wrapper around the OpenGL shader that provides ease-of-use functions
 * to quickly retrieve uniform data, build the shader, and set which
 * shader is in use by OpenGL
 */
public class GLES20Shader {
	
	//These are pre-defined locations for the common attribute values found
	//in our shaders
	/** Location of the position attribute */
	public static final int ATTRIB_POSITION = 0;
	/** Location of the normal attribute */
	public static final int ATTRIB_NORMAL = 1;
	/** Location of the texture coordinate attribute */
	public static final int ATTRIB_TEXCOORD = 2;
	
	/** GLuint handle that represents this shader in OpenGL */
	private int mProgram;
	/** HashMap of uniform values, stored for fast lookup */
	private HashMap<String, Integer> mUniforms;
	
	/** 
	 * Construct a shader object
	 */
	public GLES20Shader() {
		this.mProgram = GLES20.glCreateProgram();
		this.mUniforms = new HashMap<String, Integer>();
	}
	
	/**
	 * Load shader code into this shader program
	 * @param type of shader to add
	 * @param shaderCode to add
	 */
	public void loadShader(int type, String shaderCode) {
		//Create a handle for this particular shader type
		int shader = GLES20.glCreateShader(type);
		//Set the actual code used in this shader, compile it, and add it
		//to this shader program
		GLES20.glShaderSource(shader, shaderCode);
		GLES20.glCompileShader(shader);
		GLES20.glAttachShader(this.mProgram, shader);
		
		//Print any error information for this shader to the log
		this.printShaderInfoLog(shader);
	}
	
	/**
	 * Explicitly specify the location of an attribute in this shader
	 * @param index location to specify for this attribute
	 * @param name of the attribute to set
	 */
	public void bindAttribute(int index, String name) {
		GLES20.glBindAttribLocation(this.mProgram, index, name);
	}
	
	/**
	 * Link the provided shaders together into one usable shader program
	 */
	public void linkProgram() {
		GLES20.glLinkProgram(this.mProgram);
		
		//Print any shader program error information to the log
		this.printProgramInfoLog(this.mProgram);
	}
	
	/**
	 * Indicate to OpenGL to make this the current shader state
	 */
	public void use() {
		GLES20.glUseProgram(this.mProgram);
	}
	
	/**
	 * Add a uniform variable to the hashmap of uniform values, storing
	 * its uniform location as determined by OpenGL during shader compilation
	 * @param name of the uniform to add
	 */
	public void addUniform(String name) {
		this.mUniforms.put(name, GLES20.glGetUniformLocation(this.mProgram, name));
	}
	
	/**
	 * Set the value of the uniform value on the graphics card
	 * @param name of the uniform to set
	 * @param value of the uniform
	 */
	public void setUniform(String name, int value) {
		GLES20.glUniform1i(this.mUniforms.get(name), value);
	}
	
	/**
	 * Set the value of the uniform value on the graphics card
	 * @param name of the uniform to set
	 * @param value of the uniform
	 */
	public void setUniform(String name, float value) {
		GLES20.glUniform1f(this.mUniforms.get(name), value);
	}
	
	/**
	 * Set the value of the uniform value on the graphics card
	 * @param name of the uniform to set
	 * @param value of the uniform
	 */
	public void setUniform(String name, float[] value) {
		//Dynamically choose which OpenGL call to make based on the size
		//of the matrix or vector provided in value
		if (value.length == 16) {
			GLES20.glUniformMatrix4fv(this.mUniforms.get(name), 1, false, value, 0);
		} else if (value.length == 9) {
			GLES20.glUniformMatrix3fv(this.mUniforms.get(name), 1, false, value, 0);
		} else if (value.length == 4) {
			GLES20.glUniform4fv(this.mUniforms.get(name), 1, value, 0);
		} else if (value.length == 3) {
			GLES20.glUniform3fv(this.mUniforms.get(name), 1, value, 0);
		}
	}
	
	/**
	 * Print shader error information to the log, if it exists
	 * @param shader to error check
	 */
	private void printShaderInfoLog(int shader) {
		String infoLog = GLES20.glGetShaderInfoLog(shader);
		//If any errors occurred, print them to the log
		if (infoLog != null) {
			Log.e("Shader", infoLog);
		}
	}
	
	/**
	 * Print shader program error information to the log, if it exists
	 * @param program to error check
	 */
	private void printProgramInfoLog(int program) {
		String infoLog = GLES20.glGetProgramInfoLog(program);
		//If any errors occurred, print them to the log
		if (infoLog != null) {
			Log.e("Program", infoLog);
		}
	}
}
