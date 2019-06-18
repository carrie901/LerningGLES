#version 300 es
precision mediump float;
in vec4 color;
in vec2 uv;

out vec4 fragColor;

void main(){
   fragColor = color;
}