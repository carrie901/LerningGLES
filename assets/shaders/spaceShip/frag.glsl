#version 300 es
precision mediump float;
in vec2 uv;

out vec4 fragColor;

uniform sampler2D diffuse;
uniform vec4 mainColor;

void main(){
   fragColor = mainColor * texture(diffuse,uv);
}