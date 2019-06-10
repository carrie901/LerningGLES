#version 300 es
precision mediump float;
in vec2 TexCoord;
in vec4 color;

out vec4 fragColor;

uniform sampler2D texture1;
uniform sampler2D texture2;

void main(){
   vec4 color1 = texture(texture1,TexCoord);
   vec4 color2 = texture(texture2,TexCoord);
   fragColor = mix(color1,color2,0.5f);
   fragColor += color;
}