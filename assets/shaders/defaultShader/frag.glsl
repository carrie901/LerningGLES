#version 300 es
precision mediump float;
in vec2 uv;

out vec4 fragColor;

void main(){
   fragColor = vec4(uv.xy,1,1);
}