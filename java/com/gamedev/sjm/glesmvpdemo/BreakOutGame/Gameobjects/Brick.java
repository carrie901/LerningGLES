package com.gamedev.sjm.glesmvpdemo.BreakOutGame.Gameobjects;

import android.graphics.Bitmap;
import android.opengl.GLES30;

import com.gamedev.sjm.glesmvpdemo.BreakOutGame.Behaviors.PlayerBehavior;
import com.gamedev.sjm.glesmvpdemo.MeshObject.CenterQuad;
import com.gamedev.sjm.glesmvpdemo.SimpleEngine.Collider.BoxCollider;
import com.gamedev.sjm.glesmvpdemo.SimpleEngine.Collider.Collider;
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

public class Brick extends GameObject {

    Shader shader;
    Mesh mesh;

    private boolean destory;

    public Brick(){
        this(1,1,1);
    }

    public Brick(float r,float g,float b){
        tag = GameTags.BRICK;
        shader = Shader.CreateShader(
                "shaders/diffuseShader/vertex.glsl",
                "shaders/diffuseShader/frag.glsl",
                SurfaceView.DrawingView.getResources());
        try {
            // 初始化碰撞体Mesh
            mesh = new  CenterQuad();

            Bitmap bitmap = TextureUtil.LoadBitmap("textures/brick.png",SurfaceView.DrawingView.getContext());
            shader.SetTexture2D(
                    "diffuse",
                    bitmap,
                    0,
                    TextureSamplingMode.Clamp,
                    TextureFilteringMode.Bilinear
            );
            shader.SetFloat4("mainColor",r,g,b,1);

        }catch (Exception e){e.printStackTrace();}

        MeshRender render = new MeshRender(shader,mesh);
        AddComponent(render);

        transform.scale = new Vector3(0.3f,0.3f,0.3f);

        BoxCollider boxCollider = new BoxCollider();
        boxCollider.length = 0.15f;
        boxCollider.width = 0.15f;
        boxCollider.height = 0.15f;

        AddComponent("Collider",boxCollider);
    }

    @Override
    public void OnCollisionEnter(Collider collider) {
        if(destory) return;
        super.OnCollisionEnter(collider);

        if(collider.getGameObject().tag == GameTags.PLAYER){
            // 让玩家的y分量反向,同时让玩家的x分量随机变化
            PlayerBehavior behavior = collider.getGameObject().GetComponent("PlayerBehavior");
            // y分量反向
            behavior.direction.y = -behavior.direction.y;
            // x分量反向
            behavior.direction.x = -behavior.direction.x;

            destory = true;
        }
    }

    @Override
    public void Render() {
        if(destory) return;
        super.Render();
    }
}
