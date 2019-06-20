package com.gamedev.sjm.glesmvpdemo.SpaceShipGame.GameObject;

import android.graphics.Bitmap;
import android.opengl.GLES30;
import android.widget.Space;

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
import com.gamedev.sjm.glesmvpdemo.SpaceShipGame.Behaviors.BulletBehavior;
import com.gamedev.sjm.glesmvpdemo.SpaceShipGame.Behaviors.EnemryBulletBehvior;
import com.gamedev.sjm.glesmvpdemo.SpaceShipGame.GameTags.GameTags;
import com.gamedev.sjm.glesmvpdemo.SurfaceView;

public class EnemryBullet extends GameObject {
    private Mesh mesh;
    private Shader shader;

    public boolean isUsed;

    public EnemryBullet(){
        mesh = new CenterQuad();
        shader = Shader.CreateShader(
                "shaders/bulletShader/vertex.glsl",
                "shaders/bulletShader/frag.glsl",
                SurfaceView.DrawingView.getResources());

        try {
            Bitmap bitmap = TextureUtil.LoadBitmap("textures/fx_lazer_cyan_dff.png", SurfaceView.DrawingView.getContext());
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
        MeshRender render = new MeshRender(shader,mesh,GLES30.GL_TRIANGLES);
        AddComponent(render);

        BoxCollider collider = new BoxCollider();
        collider.length = 0.04f;
        collider.height = 0.1f;
        collider.width = 0.1f;
        AddComponent("Collider",collider);

        transform.scale = new Vector3(0.4f,0.4f,0.4f);

        AddComponent(new EnemryBulletBehvior());
    }

    @Override
    public void OnCollisionEnter(Collider collider) {
        super.OnCollisionEnter(collider);
        if(collider.getGameObject().tag==GameTags.PLAYER) {
            isUsed = false;
            if(collider.getGameObject() instanceof SpaceShip)
                ((SpaceShip)collider.getGameObject()).isDestoryed = true;
        }
    }

    @Override
    public void Render() {
        if(!isUsed) return;
        super.Render();
    }
}
