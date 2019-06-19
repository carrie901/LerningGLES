package com.gamedev.sjm.glesmvpdemo.SpaceShipGame.GameObject;

import android.graphics.Bitmap;
import android.opengl.GLES30;

import com.gamedev.sjm.glesmvpdemo.SpaceShipGame.Behaviors.AsteroidBehavior;
import com.gamedev.sjm.glesmvpdemo.SimpleEngine.Collider.BoxCollider;
import com.gamedev.sjm.glesmvpdemo.SimpleEngine.GameObject;
import com.gamedev.sjm.glesmvpdemo.SimpleEngine.Util.MathUtil.Vector3;
import com.gamedev.sjm.glesmvpdemo.SimpleEngine.Util.MeshUtil.OBJLoader;
import com.gamedev.sjm.glesmvpdemo.SimpleEngine.Util.ShaderUtil.Shader;
import com.gamedev.sjm.glesmvpdemo.SimpleEngine.Util.TextureUtil.TextureFilteringMode;
import com.gamedev.sjm.glesmvpdemo.SimpleEngine.Util.TextureUtil.TextureSamplingMode;
import com.gamedev.sjm.glesmvpdemo.SimpleEngine.Util.TextureUtil.TextureUtil;
import com.gamedev.sjm.glesmvpdemo.SimpleEngine.components.Mesh;
import com.gamedev.sjm.glesmvpdemo.SimpleEngine.components.MeshRender;
import com.gamedev.sjm.glesmvpdemo.SpaceShipGame.GameTags.GameTags;
import com.gamedev.sjm.glesmvpdemo.SurfaceView;

import java.io.IOException;

public class Asteroid1 extends GameObject {

    private Mesh asteroidMesh;
    private Shader shader;

    public Mesh getAsteroidMesh() {
        return asteroidMesh;
    }

    public Asteroid1(Vector3 pos,Vector3 rotation){
        this();
        transform.pos = pos;
        transform.rotation = rotation;
    }

    public Asteroid1() {
        tag = GameTags.ENEMRY;
        try {
            asteroidMesh = OBJLoader.ParseOBJ("obj/asteroid1.obj",SurfaceView.DrawingView.getResources());
        } catch (IOException e) {
            e.printStackTrace();
        }
        transform.pos = new Vector3(0,1,-2);
        transform.scale = new Vector3(0.2f,0.2f,0.2f);

        BoxCollider collider = new BoxCollider();
        collider.length = 0.1f;
        collider.height = 0.1f;
        collider.width = 0.1f;
        AddComponent("Collider",collider);

        shader = Shader.CreateShader(
                "shaders/diffuseShader/vertex.glsl",
                "shaders/diffuseShader/frag.glsl",
                SurfaceView.DrawingView.getResources());

        try {
            Bitmap bitmap = TextureUtil.LoadBitmap("textures/prop_asteroid_01_dff.png", SurfaceView.DrawingView.getContext());
            shader.SetTexture2D(
                    "diffuse",
                    bitmap,
                    0,
                    TextureSamplingMode.Clamp,
                    TextureFilteringMode.Bilinear);
            shader.SetFloat4("mainColor",1,1,1,1);
        }catch (Exception e){
            e.printStackTrace();
        }

        MeshRender render = new MeshRender(shader,asteroidMesh,GLES30.GL_TRIANGLE_FAN);
        AddComponent(render);

        AddComponent(new AsteroidBehavior());
    }
}
