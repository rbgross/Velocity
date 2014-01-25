package edu.ncsu.csc563.velocity.rendering;

import java.util.HashMap;

import android.opengl.GLES20;
import android.util.Log;

public class GLES20Shader {
	
	public static final int ATTRIB_POSITION = 0;
	public static final int ATTRIB_NORMAL = 1;
	public static final int ATTRIB_TEXCOORD = 2;
	
	private int mProgram;
	private HashMap<String, Integer> mUniforms;
	
	public GLES20Shader() {
		this.mProgram = GLES20.glCreateProgram();
		this.mUniforms = new HashMap<String, Integer>();
	}
	
	public void loadShader(int type, String shaderCode) {
		int shader = GLES20.glCreateShader(type);
		GLES20.glShaderSource(shader, shaderCode);
		GLES20.glCompileShader(shader);
		GLES20.glAttachShader(this.mProgram, shader);
		
		this.printShaderInfoLog(shader);
	}
	
	public void bindAttribute(int index, String name) {
		GLES20.glBindAttribLocation(this.mProgram, index, name);
	}
	
	public void linkProgram() {
		GLES20.glLinkProgram(this.mProgram);
		
		this.printProgramInfoLog(this.mProgram);
	}
	
	public void use() {
		GLES20.glUseProgram(this.mProgram);
	}
	
	public void addUniform(String name) {
		this.mUniforms.put(name, GLES20.glGetUniformLocation(this.mProgram, name));
	}
	
	public void setUniform(String name, int value) {
		GLES20.glUniform1i(this.mUniforms.get(name), value);
	}
	
	public void setUniform(String name, float value) {
		GLES20.glUniform1f(this.mUniforms.get(name), value);
	}
	
	public void setUniform(String name, float[] value) {
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
	
	private void printShaderInfoLog(int shader) {
		String infoLog = GLES20.glGetShaderInfoLog(shader);
		if (infoLog != null) {
			System.err.println(infoLog);
			Log.e("Shader", infoLog);
		}
	}
	
	private void printProgramInfoLog(int program) {
		String infoLog = GLES20.glGetProgramInfoLog(program);
		if (infoLog != null) {
			System.err.println(infoLog);
			Log.e("Program", infoLog);
		}
	}
}
