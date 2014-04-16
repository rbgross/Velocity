package edu.ncsu.csc563.velocity.rendering;

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
	 */
	public static void shipShader() {	
		if (GLES20ShaderFactory.getShader("shipShader") == null) {
			GLES20ShaderFactory.mShaders.put("shipShader", new GLES20Shader());	
		}
		
		GLES20Shader shader = GLES20ShaderFactory.mShaders.get("shipShader");
		
		String vertexShaderCode =
				"precision mediump float;" +
				//Take in provided position and normal data
				"attribute vec3 normal;" +
				"attribute vec3 position;" +
				"attribute vec2 texCoordinate;" +
				//Going to output the position and normal for
				//each vertex in eye-space (the space defined by the camera
				//properties as represented by the view matrix, also called
				//view space or camera space)
				"varying vec3 eyeNormal;" +
				"varying vec3 eyePosition;" +
				"varying vec2 texCoord;" +
				//Using the model view projection (MVP) matrices to transform position
				//and normal into eye space and clip space
				"uniform mat4 model;" +
				"uniform mat4 view;" +
				"uniform mat4 proj;" +
				//Main is always the entry point to shader code, like normal programs
				"void main() {" +
				//Transform the normal and position values to eye space for lighting calculations
				"	eyeNormal = mat3( view * model ) * normal;" +
				"	eyePosition = vec3( view * model * vec4( position, 1.0 ) );" +
				"	texCoord = vec2(texCoordinate.x, 1.0 - texCoordinate.y);" +
				//Transform the position into clip coordinates as the primary output for this shader
				"	gl_Position = proj * view * model * vec4( position, 1.0 );" +
				"}";
		
		String fragmentShaderCode =
				"precision mediump float;" +
				//These are the values calculated in the vertex shader, interpolated for each pixel
				"varying vec3 eyeNormal;" +
				"varying vec3 eyePosition;" +
				"varying vec2 texCoord;" +
				//Uniforms used to calculate lighting values for this pixel
				"uniform mat4 view;" +
				"uniform vec4 lightPosition;" +
				"uniform sampler2D shipTex;" +
				"uniform float alphaVal;" +
				"void main() {" +
				//Calculate the light position in eye space
				"	vec4 eyeLightPosition = view * lightPosition;" +
				//Normalize the normal in eyespace in case the interpolation made it longer or shorter than 1
				"	vec3 normal = normalize( eyeNormal );" +
				//Calculate the direction from this pixel to the light position in eye space
				"	vec3 toLightDir = normalize( eyeLightPosition.xyz - eyeLightPosition.w * eyePosition );" +
				//Calculate the direction from the light to this pixel in eye space
				"	vec3 toViewerDir = normalize( -eyePosition );" +
				//These are hardcoded properties of the light and object; in a better shader, these would
				//also be provided as uniforms; for this shader, we assume the light is pure, full white
				//and the object reflects pure white light in the perfect reflection direction (which is a typical
				//case)
				"	vec3 lightIntensity = vec3( 1.0, 1.0, 1.0 );" +
				"	vec3 specularColor = vec3( 1.0, 1.0, 1.0 );" +
				"	float specExp = 40.0;" +
				//Calculate an approximation of the reflection direction
				"	vec3 halfAngle = normalize( toLightDir + toViewerDir );" +
				//Again, here this is a hardcoded value for the ambient light in the scene; it is assume to be pure white
				//at 20% intensity, and objects reflect their diffuse color at that intensity
				"	vec4 ambientColor = 0.2 * texture2D(shipTex, texCoord);" +
				//These determine the contribution of each lighting term to the overall color of the pixel
				"	vec4 ambient = ambientColor;" +
				"	vec4 diffuse = texture2D(shipTex, texCoord) * max ( dot( normal, toLightDir ), 0.0 );" +
				"	vec4 specular = texture2D(shipTex, texCoord) * pow( max( dot( normal, halfAngle ), 0.0 ), specExp );" +
				//The primary output of this fragment shader is the color of this pixel, represented in a vec4
				"	gl_FragColor = vec4( vec3(ambient + diffuse + specular), alphaVal );" +
				"}";
		
		//Get a handle for the shader
		shader.createProgram();
		
		//Add the vertex and fragment shader code to the shader
		shader.loadShader(GLES20.GL_VERTEX_SHADER, vertexShaderCode);
		shader.loadShader(GLES20.GL_FRAGMENT_SHADER, fragmentShaderCode);
		
		//Define the set locations of the attributes "position" and "normal" (0 and 1 respectively)
		shader.bindAttribute(GLES20Shader.ATTRIB_POSITION, "position");
		shader.bindAttribute(GLES20Shader.ATTRIB_NORMAL, "normal");
		shader.bindAttribute(GLES20Shader.ATTRIB_TEXCOORD, "texCoordinate");
		
		//Build the shader with the provided vertex and fragment pieces
		shader.linkProgram();
		
		//Define the uniform values found in this shader in a hashmap in the shader for faster future lookup
		//and assignment
		shader.addUniform("model");
		shader.addUniform("view");
		shader.addUniform("proj");
		shader.addUniform("lightPosition");
		shader.addUniform("shipTex");
		shader.addUniform("alphaVal");
	}	
	
	/**
	 * Creates a shader that takes in position and normal data, MVP matrices,
	 * a light position, and a set color to render the object with diffuse and specular
	 * lighting in color provided in diffuseColor
	 * @return shader with diffuse/specular lighting
	 */
	public static void diffuseSpecular() {	
		if (GLES20ShaderFactory.getShader("diffuseSpecular") == null) {
			GLES20ShaderFactory.mShaders.put("diffuseSpecular", new GLES20Shader());	
		}
		
		GLES20Shader shader = GLES20ShaderFactory.mShaders.get("diffuseSpecular");
		
		String vertexShaderCode =
				"precision mediump float;" +
				//Take in provided position and normal data
				"attribute vec3 normal;" +
				"attribute vec3 position;" +
				//Going to output the position and normal for
				//each vertex in eye-space (the space defined by the camera
				//properties as represented by the view matrix, also called
				//view space or camera space)
				"varying vec3 eyeNormal;" +
				"varying vec3 eyePosition;" +
				//Using the model view projection (MVP) matrices to transform position
				//and normal into eye space and clip space
				"uniform mat4 model;" +
				"uniform mat4 view;" +
				"uniform mat4 proj;" +
				//Main is always the entry point to shader code, like normal programs
				"void main() {" +
				//Transform the normal and position values to eye space for lighting calculations
				"	eyeNormal = mat3( view * model ) * normal;" +
				"	eyePosition = vec3( view * model * vec4( position, 1.0 ) );" +
				//Transform the position into clip coordinates as the primary output for this shader
				"	gl_Position = proj * view * model * vec4( position, 1.0 );" +
				"}";
		
		String fragmentShaderCode =
				"precision mediump float;" +
				//These are the values calculated in the vertex shader, interpolated for each pixel
				"varying vec3 eyeNormal;" +
				"varying vec3 eyePosition;" +
				//Uniforms used to calculate lighting values for this pixel
				"uniform mat4 view;" +
				"uniform vec4 lightPosition;" +
				"uniform vec3 diffuseColor;" +
				"void main() {" +
				//Calculate the light position in eye space
				"	vec4 eyeLightPosition = view * lightPosition;" +
				//Normalize the normal in eyespace in case the interpolation made it longer or shorter than 1
				"	vec3 normal = normalize( eyeNormal );" +
				//Calculate the direction from this pixel to the light position in eye space
				"	vec3 toLightDir = normalize( eyeLightPosition.xyz - eyeLightPosition.w * eyePosition );" +
				//Calculate the direction from the light to this pixel in eye space
				"	vec3 toViewerDir = normalize( -eyePosition );" +
				//These are hardcoded properties of the light and object; in a better shader, these would
				//also be provided as uniforms; for this shader, we assume the light is pure, full white
				//and the object reflects pure white light in the perfect reflection direction (which is a typical
				//case)
				"	vec3 lightIntensity = vec3( 1.0, 1.0, 1.0 );" +
				"	vec3 specularColor = vec3( 1.0, 1.0, 1.0 );" +
				"	float specExp = 40.0;" +
				//Calculate an approximation of the reflection direction
				"	vec3 halfAngle = normalize( toLightDir + toViewerDir );" +
				//Again, here this is a hardcoded value for the ambient light in the scene; it is assume to be pure white
				//at 20% intensity, and objects reflect their diffuse color at that intensity
				"	vec3 ambientColor = 0.2 * diffuseColor;" +
				//These determine the contribution of each lighting term to the overall color of the pixel
				"	vec3 ambient = ambientColor;" +
				"	vec3 diffuse = diffuseColor * max ( dot( normal, toLightDir ), 0.0 );" +
				"	vec3 specular = specularColor * pow( max( dot( normal, halfAngle ), 0.0 ), specExp );" +
				//The primary output of this fragment shader is the color of this pixel, represented in a vec4
				"	gl_FragColor = vec4( lightIntensity * ( ambient + diffuse + specular ), 1.0 );" +
				"}";
		
		//Get a handle for the shader
		shader.createProgram();
		
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
	}
	
	public static GLES20Shader getShader(String shaderName) {		
		return GLES20ShaderFactory.mShaders.get(shaderName);
	}
}
