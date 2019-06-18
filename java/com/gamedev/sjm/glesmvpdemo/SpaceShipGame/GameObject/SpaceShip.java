package com.gamedev.sjm.glesmvpdemo.SpaceShipGame.GameObject;

import android.graphics.Bitmap;
import android.opengl.GLES30;

import com.gamedev.sjm.glesmvpdemo.SimpleEngine.GameObject;
import com.gamedev.sjm.glesmvpdemo.SimpleEngine.InterFace.Renderable;
import com.gamedev.sjm.glesmvpdemo.SimpleEngine.Util.MathUtil.Vector3;
import com.gamedev.sjm.glesmvpdemo.SimpleEngine.Util.MeshUtil.OBJLoader;
import com.gamedev.sjm.glesmvpdemo.SimpleEngine.Util.ShaderUtil.Shader;
import com.gamedev.sjm.glesmvpdemo.SimpleEngine.Util.TextureUtil.TextureFilteringMode;
import com.gamedev.sjm.glesmvpdemo.SimpleEngine.Util.TextureUtil.TextureSamplingMode;
import com.gamedev.sjm.glesmvpdemo.SimpleEngine.Util.TextureUtil.TextureUtil;
import com.gamedev.sjm.glesmvpdemo.SimpleEngine.components.Mesh;
import com.gamedev.sjm.glesmvpdemo.SimpleEngine.components.MeshRender;
import com.gamedev.sjm.glesmvpdemo.SurfaceView;

public class SpaceShip extends GameObject {

    private Shader shader;
    private Mesh spaceShipMesh;

    int angle = 0;

    public SpaceShip(){
        try {
            spaceShipMesh = OBJLoader.ParseOBJ("obj/spaceship.obj", SurfaceView.DrawingView.getResources());
        }catch (Exception e){
            e.printStackTrace();
        }
        shader = Shader.CreateShader(
                "shaders/spaceShip/vertex.glsl",
                "shaders/spaceShip/frag.glsl",
                SurfaceView.DrawingView.getResources());

        MeshRender meshRender = new MeshRender(shader,spaceShipMesh,GLES30.GL_TRIANGLE_FAN);
        // 添加meshRender组件
        AddComponent(meshRender);

        transform.pos = new Vector3(0,0,-2);
        transform.scale = new Vector3(0.15f,2f,0.15f);
        transform.rotation = new Vector3(90,0,0);


        try {
            Bitmap bitmap = TextureUtil.LoadBitmap("textures/spaceship_diffuse.png", SurfaceView.DrawingView.getContext());
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

    @Override
    public void Render() {
        super.Render();
    }
}
