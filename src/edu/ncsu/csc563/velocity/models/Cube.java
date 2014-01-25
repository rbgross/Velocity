package edu.ncsu.csc563.velocity.models;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;

import edu.ncsu.csc563.velocity.rendering.GLES20Shader;

import android.opengl.GLES20;
import android.opengl.Matrix;
import android.os.SystemClock;

/**
 * Sample class that provides rendering logic for a 3D cube with position
 * and normal data (for per-pixel diffuse and specular lighting)
 */
public class Cube {	
	
	/** Array of handles that identify the buffers associated with this object */
	private int[] bufferHandles;	
	/** FloatBuffer containing the formatted vertex data (position and normals) */
	private FloatBuffer vertices;
	/** ShortBuffer containing the formatted element data */
	private ShortBuffer elements;
	/** Shader that is used to render this object with the data it provides */
	private GLES20Shader shader;
	
	/**
	 * Construct a renderable cube object with hardcoded vertex data
	 * @param shader used to draw this object
	 */
	public Cube(GLES20Shader shader) {
		
		//Hard-coded position and normal data for a standard cube
		//Each row lists the x, y, and z position data and x, y, and z
		//normal data for a unique vertex on the cube; because a cube has flat
		//intersecting faces, there are 24 unique vertices (4 per face) even though
		//there are only 8 corners, because the vertex at each corner must be duplicated 
		//twice to account for 3 different normals (1 for each face that touches that
		//corner)
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
		
		//Hard-coded element data for a standard cube
		//An element array specifies the order in which the vertices (listed above)
		//will be drawn, to save duplicating vertices in the vertex buffer
		//For example, a square face is drawn as two triangles, but both triangles share
		//two vertices; the element array says to draw the vertices in a set order (say
		//0, 1, 2 -- 2, 3, 0) which saves duplicating 6 floats for vertices 0 and 2 in
		//this example
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
		//bufferHandles[0] is the handle to the vertex buffer
		//bufferHandles[1] is the handle to the element buffer
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
	
	/**
	 * Issue the OpenGL API calls required to draw the cube object to the application window
	 */
	public void draw() {
		//Set the handle of the shader to use on the graphics card
		this.shader.use();
		
		//Calculate the model matrix for this particular cube in this moment of time
		//It spins CCW around its Y axis as a function of time
		float model[] = new float[16];
		Matrix.setIdentityM(model, 0);		
		long time = SystemClock.uptimeMillis() % 4000L;
		float angle = 0.090f * ((int)time);
		Matrix.setRotateM(model, 0, angle, 0, 1, 0);
		
		//Set the value of the "model" shader uniform variable on the graphics card
		this.shader.setUniform("model", model);
		
		//Set the value of the "diffuseColor" shader uniform variable on the graphics card
		float diffuseColor[] = {0.5f, 1.0f, 0.0f};
		this.shader.setUniform("diffuseColor", diffuseColor);
		
		//Bind the array buffer using the handle we generated for the vertex buffer earlier,
		//to tell the graphics card which buffer we're talking about when we issue the next few
		//commands
		GLES20.glBindBuffer(GLES20.GL_ARRAY_BUFFER, this.bufferHandles[0]);
		
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
        GLES20.glBindBuffer(GLES20.GL_ELEMENT_ARRAY_BUFFER, this.bufferHandles[1]);
        
        //Issue a command to the graphics card, telling it to draw triangles using the provided data, of which
        //there are 36 total vertices (3 per triangle, 2 triangles per face, 6 faces for the cube), with the element
        //data provided as unsigned shorts (2 bytes per element value), and starting at offset 0 into the bound element
        //array buffer
        GLES20.glDrawElements(GLES20.GL_TRIANGLES, 36, GLES20.GL_UNSIGNED_SHORT, 0);
	}
}
