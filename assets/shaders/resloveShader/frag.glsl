#version 300 es
precision mediump float;
in vec2 uv;

out vec4 fragColor;

// 漫反射贴图
uniform sampler2D diffuse;
// 主颜色
uniform vec4 mainColor;
// 噪声贴图
uniform sampler2D burnMap;
// 火焰边缘的两种颜色
uniform vec4 burnFirstColor;
uniform vec4 burnSecondColor;

// 火焰烧焦效果的线宽,值越大,火焰蔓延程度越大
uniform float lineWidth;
// 消融的程度
uniform float burnAmount;

void main(){

   // 采样噪声纹理
   vec3 noise = texture(burnMap,uv).rgb;
   if(noise.r-burnAmount<0.0)
      discard;
   
   vec3 diffuse = (vec4(mainColor.rgb,1.0) * texture(diffuse,uv)).rgb;
   // 混合系数t
   float t = 1.0 - smoothstep(0.0,lineWidth,noise.r-burnAmount);
   vec3 burnColor = mix(burnFirstColor,burnSecondColor,t).rgb;

   vec3 finalColor = mix(diffuse,burnColor,t*step(0.0001,burnAmount));


   fragColor = vec4(finalColor,1.0);
}