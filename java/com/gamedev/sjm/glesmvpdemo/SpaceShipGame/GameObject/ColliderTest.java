package com.gamedev.sjm.glesmvpdemo.SpaceShipGame.GameObject;

import android.opengl.GLES30;

import com.gamedev.sjm.glesmvpdemo.SimpleEngine.Collider.BoxCollider;
import com.gamedev.sjm.glesmvpdemo.SimpleEngine.GameObject;
import com.gamedev.sjm.glesmvpdemo.SimpleEngine.Util.MathUtil.Vector3;
import com.gamedev.sjm.glesmvpdemo.SimpleEngine.Util.MeshUtil.OBJLoader;
import com.gamedev.sjm.glesmvpdemo.SimpleEngine.Util.ShaderUtil.Shader;
import com.gamedev.sjm.glesmvpdemo.SimpleEngine.components.Mesh;
import com.gamedev.sjm.glesmvpdemo.SimpleEngine.components.MeshRender;
import com.gamedev.sjm.glesmvpdemo.SurfaceView;

public class ColliderTest extends GameObject {
    Mesh cube;
    Shader shader;
    public ColliderTest(){
        try {
            cube = OBJLoader.ParseOBJ("obj/cube.obj", SurfaceView.DrawingView.getResources());
        }catch (Exception e){e.printStackTrace();}
        shader = Shader.CreateShader(
                "shaders/defaultShader/vertex.glsl",
                "shaders/defaultShader/frag.glsl",
                SurfaceView.DrawingView.getResources());
        MeshRender render = new MeshRender(shader,cube,GLES30.GL_LINE_LOOP);
        AddComponent(render);
        transform.scale = Vector3.One.Mutliply(0.2f);
        transform.pos = new Vector3(0,0,-2);
        BoxCollider collider = new BoxCollider();
        AddComponent("Collider",collider);
//        AddComponent(new OperationBehavior());
    }
}
