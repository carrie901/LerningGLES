package com.gamedev.sjm.glesmvpdemo.BreakOutGame.Gameobjects;

import android.graphics.Bitmap;

import com.gamedev.sjm.glesmvpdemo.BreakOutGame.Behaviors.PaddleBehavior;
import com.gamedev.sjm.glesmvpdemo.BreakOutGame.Behaviors.PlayerBehavior;
import com.gamedev.sjm.glesmvpdemo.MeshObject.CenterQuad;
import com.gamedev.sjm.glesmvpdemo.SimpleEngine.Collider.BoxCollider;
import com.gamedev.sjm.glesmvpdemo.SimpleEngine.Collider.Collider;
import com.gamedev.sjm.glesmvpdemo.SimpleEngine.GameObject;
import com.gamedev.sjm.glesmvpdemo.SimpleEngine.Util.MathUtil.Vector3;
import com.gamedev.sjm.glesmvpdemo.SimpleEngine.Util.ShaderUtil.Shader;
import com.gamedev.sjm.glesmvpdemo.SimpleEngine.Util.TextureUtil.TextureFilteringMode;
import com.gamedev.sjm.glesmvpdemo.SimpleEngine.Util.TextureUtil.TextureSamplingMode;
import com.gamedev.sjm.glesmvpdemo.SimpleEngine.Util.TextureUtil.TextureUtil;
import com.gamedev.sjm.glesmvpdemo.SimpleEngine.components.Mesh;
import com.gamedev.sjm.glesmvpdemo.SimpleEngine.components.MeshRender;
import com.gamedev.sjm.glesmvpdemo.SpaceShipGame.GameTags.GameTags;
import com.gamedev.sjm.glesmvpdemo.SurfaceView;

public class Paddle extends GameObject {
    Shader shader;
    Mesh mesh;
    public Paddle(){
        shader = Shader.CreateShader(
                "shaders/diffuseShader/vertex.glsl",
                "shaders/diffuseShader/frag.glsl",
                SurfaceView.DrawingView.getResources());
        try {
            // 初始化碰撞体Mesh
            mesh = new CenterQuad();

            Bitmap bitmap = TextureUtil.LoadBitmap("textures/paddle.png",SurfaceView.DrawingView.getContext());
            shader.SetTexture2D(
                    "diffuse",
                    bitmap,
                    0,
                    TextureSamplingMode.Clamp,
                    TextureFilteringMode.Bilinear
            );
            shader.SetFloat4("mainColor",1,1,1,1);

        }catch (Exception e){e.printStackTrace();}

        MeshRender render = new MeshRender(shader,mesh);
        AddComponent(render);

        transform.pos = new Vector3(0,-1.5f,0);
        transform.scale = new Vector3(0.7f,0.2f,0.3f);

        BoxCollider boxCollider = new BoxCollider();
        boxCollider.length = 0.32f;
        boxCollider.width = 0.1f;
        boxCollider.height = 0.09f;
        AddComponent("Collider",boxCollider);

        AddComponent(new PaddleBehavior());
    }

    @Override
    public void OnCollisionEnter(Collider collider) {
        super.OnCollisionEnter(collider);
        if(collider.getGameObject().tag == GameTags.PLAYER){
            // 让玩家的y分量反向,同时让玩家的x分量随机变化
            PlayerBehavior behavior = collider.getGameObject().GetComponent("PlayerBehavior");
            behavior.direction.y = -behavior.direction.y;
            behavior.direction.x = (float)Math.random();
        }
    }
}
