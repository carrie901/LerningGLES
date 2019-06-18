#version 300 es
precision mediump float;
in vec4 color;
in vec2 uv;

out vec4 fragColor;

uniform sampler2D texture1;

void main(){
   vec4 texColor = texture(texture1,uv);
   fragColor = texColor;
}