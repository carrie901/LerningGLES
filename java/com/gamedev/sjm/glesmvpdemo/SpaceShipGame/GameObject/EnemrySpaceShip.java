package com.gamedev.sjm.glesmvpdemo.SpaceShipGame.GameObject;

import android.graphics.Bitmap;
import android.opengl.GLES30;

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
import com.gamedev.sjm.glesmvpdemo.SpaceShipGame.Behaviors.EnemryShipBehavior;
import com.gamedev.sjm.glesmvpdemo.SpaceShipGame.GameTags.GameTags;
import com.gamedev.sjm.glesmvpdemo.SurfaceView;

public class EnemrySpaceShip extends GameObject {

    private Mesh mesh;
    private Shader shader;

    public boolean isUse;

    public EnemrySpaceShip(){
        tag = GameTags.ENEMRY;
        try {
            mesh = OBJLoader.ParseOBJ("obj/enemry_spaceship.obj", SurfaceView.DrawingView.getResources());
        }catch (Exception e){
            e.printStackTrace();
        }
        shader = Shader.CreateShader(
                "shaders/diffuseShader/vertex.glsl",
                "shaders/diffuseShader/frag.glsl",
                SurfaceView.DrawingView.getResources());

        MeshRender meshRender = new MeshRender(shader,mesh,GLES30.GL_TRIANGLE_FAN);
        // 添加meshRender组件
        AddComponent(meshRender);

        try {
            Bitmap bitmap = TextureUtil.LoadBitmap("textures/vehicle_enemyShip_purple_dff.png", SurfaceView.DrawingView.getContext());
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

        transform.pos = new Vector3(0,1,-2);
        transform.scale = new Vector3(0.2f,0.2f,0.2f);
        transform.rotation = new Vector3(-90,0,0);

        BoxCollider collider = new BoxCollider();
        collider.height = 0.15f;
        collider.length = 0.15f;
        collider.width = 0.15f;
        AddComponent("Collider",collider);

        AddComponent(new EnemryShipBehavior());
    }

    @Override
    public void Render() {
        if(!isUse) return;
        super.Render();
    }
}
