#version 300 es
layout (location = 0) in vec3 vertex;
layout (location = 1) in vec2 texcoord;
layout (location = 2) in vec4 vertexColor;

out vec2 TexCoord;
out vec4 color;

uniform mat4 mMatrix;
uniform mat4 vMatrix;
uniform mat4 pMatrix;

void main()
{
   gl_Position = pMatrix * vMatrix * mMatrix * vec4(vertex,1);   
   TexCoord = vec2(texcoord.x,1.0-texcoord.y);
   color = vertexColor;
}