#version 300 es
layout (location = 0) in vec3 vertex;
layout (location = 1) in vec2 texcoord;
layout (location = 2) in vec3 normal;

out vec2 uv;

uniform mat4 mMatrix;
uniform mat4 vMatrix;
uniform mat4 pMatrix;
// 消融的程度
uniform float burnAmount;
// 飞行因子与飞行阈值
uniform float flyFactor;
uniform float flyAmount;

void main()
{
   vec3 v = vertex;
   v.xyz += normal * clamp(burnAmount-flyAmount,0.0,1.0) * flyFactor;
   gl_Position = pMatrix * vMatrix * mMatrix * vec4(v,1);   
   uv = texcoord;
}