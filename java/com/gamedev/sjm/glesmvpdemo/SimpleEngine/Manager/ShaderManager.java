package com.gamedev.sjm.glesmvpdemo.SimpleEngine.Manager;

import com.gamedev.sjm.glesmvpdemo.SimpleEngine.Util.ShaderUtil.Shader;
import com.gamedev.sjm.glesmvpdemo.SurfaceView;

public class ShaderManager {
    public Shader getDefaultShader() {
        if(defaultShader==null){
            defaultShader = Shader.CreateShader(
                    "shaders/defaultShader/vertex.glsl",
                    "shaders/defaultShader/frag.glsl",
                    SurfaceView.DrawingView.getResources());
        }
        return defaultShader;
    }

    private Shader defaultShader;

}
