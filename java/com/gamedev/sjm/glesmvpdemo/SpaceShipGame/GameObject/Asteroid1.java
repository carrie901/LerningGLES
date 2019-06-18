package com.gamedev.sjm.glesmvpdemo.SpaceShipGame.GameObject;

import android.graphics.Bitmap;
import android.opengl.GLES30;

import com.gamedev.sjm.glesmvpdemo.SimpleEngine.GameObject;
import com.gamedev.sjm.glesmvpdemo.SimpleEngine.Util.MathUtil.Vector3;
import com.gamedev.sjm.glesmvpdemo.SimpleEngine.Util.MeshUtil.OBJLoader;
import com.gamedev.sjm.glesmvpdemo.SimpleEngine.Util.ShaderUtil.Shader;
import com.gamedev.sjm.glesmvpdemo.SimpleEngine.Util.TextureUtil.TextureFilteringMode;
import com.gamedev.sjm.glesmvpdemo.SimpleEngine.Util.TextureUtil.TextureSamplingMode;
import com.gamedev.sjm.glesmvpdemo.SimpleEngine.Util.TextureUtil.TextureUtil;
import com.gamedev.sjm.glesmvpdemo.SimpleEngine.components.Mesh;
import com.gamedev.sjm.glesmvpdemo.SimpleEngine.components.MeshRender;
import com.gamedev.sjm.glesmvpdemo.SurfaceView;

import java.io.IOException;

public class Asteroid1 extends GameObject {

    private Mesh asteroidMesh;
    private Shader shader;

    public Asteroid1() {
        try {
            asteroidMesh = OBJLoader.ParseOBJ("obj/asteroid1.obj",SurfaceView.DrawingView.getResources());
        } catch (IOException e) {
            e.printStackTrace();
        }
        shader = Shader.CreateShader(
                "shaders/spaceShip/vertex.glsl",
                "shaders/spaceShip/frag.glsl",
                SurfaceView.DrawingView.getResources());
        MeshRender render = new MeshRender(shader,asteroidMesh,GLES30.GL_TRIANGLE_FAN);
        AddComponent(render);
        transform.pos = new Vector3(0,1,-2);
        transform.scale = new Vector3(0.2f,0.2f,0.2f);

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
    }

    int angle = 0;

    @Override
    public void Render() {
        angle = (angle+1)%360;
        transform.rotation = new Vector3(angle,0,angle);
        super.Render();
    }
}
