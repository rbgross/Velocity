precision mediump float;
varying vec3 eyeNormal;
varying vec3 eyePosition;
uniform mat4 view;
uniform vec4 lightPosition;
uniform vec3 diffuseColor;
void main() {
	vec4 eyeLightPosition = view * lightPosition;
	vec3 normal = normalize( eyeNormal );
	vec3 toLightDir = normalize( eyeLightPosition.xyz - eyeLightPosition.w * eyePosition );
	vec3 toViewerDir = normalize( -eyePosition );
	vec3 lightIntensity = vec3( 1.0, 1.0, 1.0 );
	vec3 specularColor = vec3( 1.0, 1.0, 1.0 );
	float specExp = 40.0;
	vec3 halfAngle = normalize( toLightDir + toViewerDir );
	vec3 ambientColor = 0.2 * diffuseColor;
	vec3 ambient = ambientColor;
	vec3 diffuse = diffuseColor * max ( dot( normal, toLightDir ), 0.0 );
	vec3 specular = specularColor * pow( max( dot( normal, halfAngle ), 0.0 ), specExp );
	gl_FragColor = vec4( lightIntensity * ( ambient + diffuse + specular ), 1.0 );
}