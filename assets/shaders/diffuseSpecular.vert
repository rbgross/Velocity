precision mediump float;
attribute vec3 normal;
attribute vec3 position;
varying vec3 eyeNormal;
varying vec3 eyePosition;
uniform mat4 model;
uniform mat4 view;
uniform mat4 proj;
void main() {
	eyeNormal = mat3( view * model ) * normal;
	eyePosition = vec3( view * model * vec4( position, 1.0 ) );
	gl_Position = proj * view * model * vec4( position, 1.0 );
}