#version 300 es
precision mediump float;
in vec2 uv;

out vec4 fragColor;

uniform sampler2D texture1;
uniform sampler2D texture2;

void main(){
   vec4 tex1 = texture(texture1,uv);
   vec4 tex2 = texture(texture2,uv);

   fragColor = mix(tex1,tex2,0.5);
}