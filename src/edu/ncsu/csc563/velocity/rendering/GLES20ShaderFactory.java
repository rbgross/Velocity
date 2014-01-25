package edu.ncsu.csc563.velocity.rendering;

import android.opengl.GLES20;

public class GLES20ShaderFactory {
	
	public static GLES20Shader basic() {
		String vertexShaderCode =
				"attribute vec3 normal;" +
				"attribute vec3 position;" +
				"varying vec3 eyeNormal;" +
				"varying vec3 eyePosition;" +
				"uniform mat4 model;" +
				"uniform mat4 view;" +
				"uniform mat4 proj;" +
				"void main() {" +
				"	eyeNormal = mat3( view * model ) * normal;" +
				"	eyePosition = vec3( view * model * vec4( position, 1.0f ) );" +
				"	gl_Position = proj * view * model * vec4( position, 1.0f );" +
				"}";
		
		String fragmentShaderCode =
				"varying vec3 eyeNormal;" +
				"varying vec3 eyePosition;" +
				"uniform mat4 view;" +
				"uniform vec4 lightPosition;" +
				"uniform vec3 diffuseColor;" +
				"void main() {" +
				"	vec4 eyeLightPosition = view * lightPosition;" +
				"	vec3 normal = normalize( eyeNormal );" +
				"	vec3 toLightDir = normalize( eyeLightPosition.xyz - eyeLightPosition.w * eyePosition );" +
				"	vec3 toViewerDir = normalize( -eyePosition );" +
				"	vec3 halfAngle = normalize( toLightDir + toViewerDir );" +
				"	vec3 lightIntensity = vec3( 1.0f, 1.0f, 1.0f );" +
				"	vec3 ambientColor = 0.2f * diffuseColor;" +
				"	vec3 specularColor = vec3( 1.0f, 1.0f, 1.0f );" +
				"	float specExp = 40.0f;" +
				"	vec3 ambient = ambientColor;" +
				"	vec3 diffuse = diffuseColor * max ( dot( normal, toLightDir ), 0.0f );" +
				"	vec3 specular = specularColor * pow( max( dot( normal, halfAngle ), 0.0f ), specExp );" +
				"	gl_FragColor = vec4( lightIntensity * ( ambient + diffuse + specular ), 1.0f );" +
				"}";
		
		GLES20Shader shader = new GLES20Shader();
		shader.loadShader(GLES20.GL_VERTEX_SHADER, vertexShaderCode);
		shader.loadShader(GLES20.GL_FRAGMENT_SHADER, fragmentShaderCode);
		shader.bindAttribute(GLES20Shader.ATTRIB_POSITION, "position");
		shader.bindAttribute(GLES20Shader.ATTRIB_NORMAL, "color");
		shader.linkProgram();
		shader.addUniform("model");
		shader.addUniform("view");
		shader.addUniform("proj");
		shader.addUniform("lightPosition");
		shader.addUniform("diffuseColor");
		return shader;
	}
}
