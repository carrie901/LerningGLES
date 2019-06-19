package com.gamedev.sjm.glesmvpdemo.MeshObject;

import android.opengl.GLES30;

import com.gamedev.sjm.glesmvpdemo.SimpleEngine.GameObject;
import com.gamedev.sjm.glesmvpdemo.SimpleEngine.Util.MathUtil.Vector3;
import com.gamedev.sjm.glesmvpdemo.SimpleEngine.Util.MeshUtil.OBJLoader;
import com.gamedev.sjm.glesmvpdemo.SimpleEngine.Util.ShaderUtil.Shader;
import com.gamedev.sjm.glesmvpdemo.SimpleEngine.components.Mesh;
import com.gamedev.sjm.glesmvpdemo.SimpleEngine.components.MeshRender;
import com.gamedev.sjm.glesmvpdemo.SurfaceView;

public class Sphere extends GameObject {

    Mesh sphere;

    public Sphere(){
        try {
           sphere = OBJLoader.ParseOBJ(
                   "obj/sphere2.obj",
                   SurfaceView.DrawingView.getResources());
        }catch (Exception e){e.printStackTrace();}
        Shader shader = Shader.CreateShader(
                "shaders/defaultShader/vertex.glsl",
                "shaders/defaultShader/frag.glsl",
                SurfaceView.DrawingView.getResources());
        transform.pos = new Vector3(0,-0.05f,-2);
        transform.scale = new Vector3(0.1f,0.1f,0.1f);
        MeshRender render = new MeshRender(shader,sphere,GLES30.GL_LINE_LOOP);
        AddComponent(render);
    }
}
