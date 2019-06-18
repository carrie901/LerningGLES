#version 300 es
layout (location = 0) in vec3 vertex;
layout (location = 1) in vec4 vertexColor;
layout (location = 2) in vec2 texcoord;

out vec4 color;
out vec2 uv;

uniform mat4 mMatrix;
uniform mat4 vMatrix;
uniform mat4 pMatrix;

void main()
{
   gl_Position = pMatrix * vMatrix * mMatrix * vec4(vertex,1);   
   color = vertexColor;
   uv = texcoord;
}