package com.gamedev.sjm.glesmvpdemo.BreakOutGame.Gameobjects;

import android.graphics.Bitmap;

import com.gamedev.sjm.glesmvpdemo.BreakOutGame.Behaviors.PlayerBehavior;
import com.gamedev.sjm.glesmvpdemo.MeshObject.CenterQuad;
import com.gamedev.sjm.glesmvpdemo.SimpleEngine.Collider.Collider;
import com.gamedev.sjm.glesmvpdemo.SimpleEngine.GameObject;
import com.gamedev.sjm.glesmvpdemo.SimpleEngine.Time.Time;
import com.gamedev.sjm.glesmvpdemo.SimpleEngine.Util.MathUtil.Vector3;
import com.gamedev.sjm.glesmvpdemo.SimpleEngine.Util.ShaderUtil.Shader;
import com.gamedev.sjm.glesmvpdemo.SimpleEngine.Util.TextureUtil.TextureFilteringMode;
import com.gamedev.sjm.glesmvpdemo.SimpleEngine.Util.TextureUtil.TextureSamplingMode;
import com.gamedev.sjm.glesmvpdemo.SimpleEngine.Util.TextureUtil.TextureUtil;
import com.gamedev.sjm.glesmvpdemo.SimpleEngine.components.Mesh;
import com.gamedev.sjm.glesmvpdemo.SimpleEngine.components.MeshRender;
import com.gamedev.sjm.glesmvpdemo.SpaceShipGame.GameTags.GameTags;
import com.gamedev.sjm.glesmvpdemo.SurfaceView;

public class HardBrick extends Brick {

    public HardBrick(){
        super();
        try {
            shader.Use();
            Bitmap bitmap = TextureUtil.LoadBitmap("textures/hard_brick.png",SurfaceView.DrawingView.getContext());
            shader.SetTexture2D(
                    "diffuse",
                    bitmap,
                    0,
                    TextureSamplingMode.Clamp,
                    TextureFilteringMode.Bilinear
            );
            shader.SetFloat4("mainColor",1f,1f,1f,1f);

        }catch (Exception e){e.printStackTrace();}
    }

    @Override
    public void OnCollisionEnter(Collider collider) {
        if(collider.getGameObject().tag == GameTags.PLAYER){
            // 让玩家的y分量反向,同时让玩家的x分量随机变化
            PlayerBehavior behavior = collider.getGameObject().GetComponent("PlayerBehavior");
            // y分量反向
            behavior.direction.y = -behavior.direction.y;
            // x分量反向
            behavior.direction.x = -behavior.direction.x;

            // 重定位玩家的位置

        }
    }
}
