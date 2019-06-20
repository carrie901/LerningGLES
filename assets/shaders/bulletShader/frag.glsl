#version 300 es
precision mediump float;
in vec2 uv;

out vec4 fragColor;

uniform sampler2D diffuse;
uniform vec4 mainColor;

void main(){
   vec4 color = mainColor * texture(diffuse,uv);
   if(color.r<=0.5 || color.g<=0.5 || color.b<=0.5)
      color.a = 0.0; 
   fragColor = color;
}