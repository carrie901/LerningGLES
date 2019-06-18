package com.gamedev.sjm.glesmvpdemo.SpaceShipGame.GameObject;

import android.graphics.Bitmap;

import com.gamedev.sjm.glesmvpdemo.MeshObject.Quad;
import com.gamedev.sjm.glesmvpdemo.SimpleEngine.GameObject;
import com.gamedev.sjm.glesmvpdemo.SimpleEngine.Util.ShaderUtil.Shader;
import com.gamedev.sjm.glesmvpdemo.SimpleEngine.Util.TextureUtil.TextureFilteringMode;
import com.gamedev.sjm.glesmvpdemo.SimpleEngine.Util.TextureUtil.TextureSamplingMode;
import com.gamedev.sjm.glesmvpdemo.SimpleEngine.Util.TextureUtil.TextureUtil;
import com.gamedev.sjm.glesmvpdemo.SimpleEngine.components.Mesh;
import com.gamedev.sjm.glesmvpdemo.SimpleEngine.components.MeshRender;
import com.gamedev.sjm.glesmvpdemo.SurfaceView;
import com.gamedev.sjm.glesmvpdemo.Vector3;
import com.gamedev.sjm.glesmvpdemo.R;
import java.io.IOException;

public class SpaceBackGround extends GameObject {

    Bitmap background;
    Shader shader;

    public SpaceBackGround() {
        try {
//            background = TextureUtil.LoadBitmap("textures/tile_nebula_green_dff.png", SurfaceView.DrawingView.getContext());
            background = TextureUtil.LoadBitmap(R.drawable.wall,SurfaceView.DrawingView.getResources());
        }catch (Exception e){
            e.printStackTrace();
        }

        shader = Shader.CreateShader(
                "shaders/backgroudShader/vertex.glsl",
                "shaders/backgroudShader/frag.glsl",
                SurfaceView.DrawingView.getResources());
        Mesh quad = new Quad();
        MeshRender meshRender = new MeshRender(shader,quad);

        // 添加meshRender组件
        AddComponent(meshRender);

        // 设置坐标与缩放程度
        transform.pos = Vector3.Zero;
        transform.scale = new Vector3(15,30,1);
        transform.rotation = new Vector3(90,0,0);

        if(background!=null && !background.isRecycled())
            shader.SetTexture2D(
                    "texture1",
                    background,
                    0,
                    TextureSamplingMode.Repeat,
                    TextureFilteringMode.Bilinear
            );
    }
}
