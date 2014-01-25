package edu.ncsu.csc563.velocity.models;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;

import edu.ncsu.csc563.velocity.rendering.GLES20Shader;

import android.opengl.GLES20;
import android.opengl.Matrix;
import android.os.SystemClock;

public class Cube {	
	
	private int[] bufferHandles;
	private FloatBuffer vertices;
	private ShortBuffer elements;
	
	private GLES20Shader shader;
	
	public Cube(GLES20Shader shader) {
		
		float rawVertices[] = {
		//       -----Position-----   ------Normal------
		//         X      Y      Z      X      Y      Z
				-0.5f,  0.5f,  0.5f,  0.0f,  0.0f,  1.0f, //0
				-0.5f, -0.5f,  0.5f,  0.0f,  0.0f,  1.0f, //1
				 0.5f, -0.5f,  0.5f,  0.0f,  0.0f,  1.0f, //2
				 0.5f,  0.5f,  0.5f,  0.0f,  0.0f,  1.0f, //3
				 0.5f,  0.5f,  0.5f,  1.0f,  0.0f,  0.0f, //4
				 0.5f, -0.5f,  0.5f,  1.0f,  0.0f,  0.0f, //5
				 0.5f, -0.5f, -0.5f,  1.0f,  0.0f,  0.0f, //6
				 0.5f,  0.5f, -0.5f,  1.0f,  0.0f,  0.0f, //7
				 0.5f,  0.5f, -0.5f,  0.0f,  0.0f, -1.0f, //8
				 0.5f, -0.5f, -0.5f,  0.0f,  0.0f, -1.0f, //9
				-0.5f, -0.5f, -0.5f,  0.0f,  0.0f, -1.0f, //10
				-0.5f,  0.5f, -0.5f,  0.0f,  0.0f, -1.0f, //11
				-0.5f,  0.5f, -0.5f, -1.0f,  0.0f,  0.0f, //12
				-0.5f, -0.5f, -0.5f, -1.0f,  0.0f,  0.0f, //13
				-0.5f, -0.5f,  0.5f, -1.0f,  0.0f,  0.0f, //14
				-0.5f,  0.5f,  0.5f, -1.0f,  0.0f,  0.0f, //15
				-0.5f,  0.5f,  0.5f,  0.0f,  1.0f,  0.0f, //16
				 0.5f,  0.5f,  0.5f,  0.0f,  1.0f,  0.0f, //17
				 0.5f,  0.5f, -0.5f,  0.0f,  1.0f,  0.0f, //18
				-0.5f,  0.5f, -0.5f,  0.0f,  1.0f,  0.0f, //19
				-0.5f, -0.5f,  0.5f,  0.0f, -1.0f,  0.0f, //20
				-0.5f, -0.5f, -0.5f,  0.0f, -1.0f,  0.0f, //21
				 0.5f, -0.5f, -0.5f,  0.0f, -1.0f,  0.0f, //22
				 0.5f, -0.5f,  0.5f,  0.0f, -1.0f,  0.0f, //23
			};
			
		short rawElements[] = {
				0,	1,	2,
				2,	3,	0,
				4,	5,	6,
				6,	7,	4,
				8,	9,	10,
				10,	11,	8,
				12,	13,	14,
				14,	15,	12,
				16,	17,	18,
				18,	19,	16,
				20,	21,	22,
				22,	23,	20,
		};
		
		this.shader = shader;
		
		//Generate handles for buffers to store data on the graphics card
		//	+ bufferHandles[0] is the handle to the vertex buffer
		//	+ bufferHandles[1] is the handle to the element buffer
		this.bufferHandles = new int[2];
		GLES20.glGenBuffers(2, this.bufferHandles, 0);
		
		//Order the data in the vertex array and store it in a float buffer
        ByteBuffer vertexBB = ByteBuffer.allocateDirect(rawVertices.length * 4);
        vertexBB.order(ByteOrder.nativeOrder());
        this.vertices = vertexBB.asFloatBuffer();
        this.vertices.put(rawVertices);
        this.vertices.position(0);
        
        //Bind the vertex buffer generated earlier, and put the buffered vertex data on the graphics card
        GLES20.glBindBuffer(GLES20.GL_ARRAY_BUFFER, this.bufferHandles[0]);
		GLES20.glBufferData(GLES20.GL_ARRAY_BUFFER, 4 * this.vertices.capacity(), this.vertices, GLES20.GL_STATIC_DRAW);
        
		//Order the data in the elements array and store it in a short buffer
        ByteBuffer elementBB = ByteBuffer.allocateDirect(rawElements.length * 2);
        elementBB.order(ByteOrder.nativeOrder());
        this.elements = elementBB.asShortBuffer();
        this.elements.put(rawElements);
        this.elements.position(0);
		
        //Bind the element buffer generated earlier and put the buffered data on the graphics card
		GLES20.glBindBuffer(GLES20.GL_ELEMENT_ARRAY_BUFFER, this.bufferHandles[1]);
		GLES20.glBufferData(GLES20.GL_ELEMENT_ARRAY_BUFFER, 2 * this.elements.capacity(), this.elements, GLES20.GL_STATIC_DRAW);
	}
	
	public void draw() {
		this.shader.use();
		
		float model[] = new float[16];
		Matrix.setIdentityM(model, 0);
		
		long time = SystemClock.uptimeMillis() % 4000L;
		float angle = 0.090f * ((int)time);
		Matrix.setRotateM(model, 0, angle, 0, 1, 0);
		
		this.shader.setUniform("model", model);
		
		float diffuseColor[] = {0.5f, 1.0f, 0.0f};
		this.shader.setUniform("diffuseColor", diffuseColor);
		
		GLES20.glBindBuffer(GLES20.GL_ARRAY_BUFFER, this.bufferHandles[0]);
		
		GLES20.glEnableVertexAttribArray(GLES20Shader.ATTRIB_POSITION);
        GLES20.glVertexAttribPointer(GLES20Shader.ATTRIB_POSITION, 3, GLES20.GL_FLOAT, false, 6 * 4, 0);
        
        GLES20.glEnableVertexAttribArray(GLES20Shader.ATTRIB_NORMAL);
        GLES20.glVertexAttribPointer(GLES20Shader.ATTRIB_NORMAL, 3, GLES20.GL_FLOAT, false, 6 * 4, 12);
        
        GLES20.glBindBuffer(GLES20.GL_ELEMENT_ARRAY_BUFFER, this.bufferHandles[1]);
        
        GLES20.glDrawElements(GLES20.GL_TRIANGLES, 36, GLES20.GL_UNSIGNED_SHORT, 0);
	}
}
