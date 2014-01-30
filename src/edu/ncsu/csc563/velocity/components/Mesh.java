package edu.ncsu.csc563.velocity.components;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;

import edu.ncsu.csc563.velocity.rendering.GLES20Shader;

import android.opengl.GLES20;

public class Mesh {
	/** Array of handles that identify the buffers associated with this object */
	private int[] mBufferHandles;	
	/** FloatBuffer containing the formatted vertex data (position and normals) */
	private FloatBuffer mVertices;
	/** IntBuffer containing the formatted element data */
	private IntBuffer mElements;
	
	public Mesh(float[] rawVertices, int[] rawElements) {
		//Generate handles for buffers to store data on the graphics card
		//mBufferHandles[0] is the handle to the vertex buffer
		//mBufferHandles[1] is the handle to the element buffer
		this.mBufferHandles = new int[2];
		GLES20.glGenBuffers(2, this.mBufferHandles, 0);
		
		//Order the data in the vertex array and store it in a float buffer
        ByteBuffer vertexBB = ByteBuffer.allocateDirect(rawVertices.length * 4);
        vertexBB.order(ByteOrder.nativeOrder());
        this.mVertices = vertexBB.asFloatBuffer();
        this.mVertices.put(rawVertices);
        this.mVertices.position(0);
        
        //Bind the vertex buffer generated earlier, and put the buffered vertex data on the graphics card
        GLES20.glBindBuffer(GLES20.GL_ARRAY_BUFFER, this.mBufferHandles[0]);
		GLES20.glBufferData(GLES20.GL_ARRAY_BUFFER, 4 * this.mVertices.capacity(), this.mVertices, GLES20.GL_STATIC_DRAW);
        
		//Order the data in the mElements array and store it in a int buffer
        ByteBuffer elementBB = ByteBuffer.allocateDirect(rawElements.length * 4);
        elementBB.order(ByteOrder.nativeOrder());
        this.mElements = elementBB.asIntBuffer();
        this.mElements.put(rawElements);
        this.mElements.position(0);
		
        //Bind the element buffer generated earlier and put the buffered data on the graphics card
		GLES20.glBindBuffer(GLES20.GL_ELEMENT_ARRAY_BUFFER, this.mBufferHandles[1]);
		GLES20.glBufferData(GLES20.GL_ELEMENT_ARRAY_BUFFER, 4 * this.mElements.capacity(), this.mElements, GLES20.GL_STATIC_DRAW);
	}
	
	public void draw() {
		//Bind the array buffer using the handle we generated for the vertex buffer earlier,
		//to tell the graphics card which buffer we're talking about when we issue the next few
		//commands
		GLES20.glBindBuffer(GLES20.GL_ARRAY_BUFFER, this.mBufferHandles[0]);
		
		//Tell the graphics card how position data is stored in our vertex buffer, so the shader knows
		//where to get the data for the "position" attribute value in the shader
		GLES20.glEnableVertexAttribArray(GLES20Shader.ATTRIB_POSITION);
        GLES20.glVertexAttribPointer(GLES20Shader.ATTRIB_POSITION, 3, GLES20.GL_FLOAT, false, 6 * 4, 0);
        
        //Tell the graphics card how normal data is stored in our vertex buffer, so the shader knows
      	//where to get the data for the "normal" attribute value in the shader
        GLES20.glEnableVertexAttribArray(GLES20Shader.ATTRIB_NORMAL);
        GLES20.glVertexAttribPointer(GLES20Shader.ATTRIB_NORMAL, 3, GLES20.GL_FLOAT, false, 6 * 4, 12);
        
        //Bind the element buffer using the handle we generated for the element buffer earlier,
      	//to tell the graphics card which buffer we're talking about when we issue the next few
      	//commands
        GLES20.glBindBuffer(GLES20.GL_ELEMENT_ARRAY_BUFFER, this.mBufferHandles[1]);
        
        //Issue a command to the graphics card, telling it to draw triangles using the provided data, of which
        //there are mElements.capacity() total vertices, with the element data provided as unsigned ints (4 bytes
        //per element value), and starting at offset 0 into the bound element array buffer
        GLES20.glDrawElements(GLES20.GL_TRIANGLES, this.mElements.capacity(), GLES20.GL_UNSIGNED_INT, 0);
	}

}
