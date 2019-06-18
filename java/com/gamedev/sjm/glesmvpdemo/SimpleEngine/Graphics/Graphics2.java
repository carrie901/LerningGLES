package com.gamedev.sjm.glesmvpdemo.SimpleEngine.Graphics;

import android.graphics.Bitmap;
import android.opengl.GLES30;

import com.gamedev.sjm.glesmvpdemo.SimpleEngine.Util.MatrixUtil.MatrixUtil;
import com.gamedev.sjm.glesmvpdemo.MeshObject.Cube;
import com.gamedev.sjm.glesmvpdemo.R;
import com.gamedev.sjm.glesmvpdemo.SimpleEngine.Util.ShaderUtil.Shader;
import com.gamedev.sjm.glesmvpdemo.SimpleEngine.Camera;
import com.gamedev.sjm.glesmvpdemo.SimpleEngine.components.Mesh;
import com.gamedev.sjm.glesmvpdemo.SurfaceView;
import com.gamedev.sjm.glesmvpdemo.SimpleEngine.Util.TextureUtil.TextureFilteringMode;
import com.gamedev.sjm.glesmvpdemo.SimpleEngine.Util.TextureUtil.TextureSamplingMode;
import com.gamedev.sjm.glesmvpdemo.SimpleEngine.Util.TextureUtil.TextureUtil;
import com.gamedev.sjm.glesmvpdemo.Vector3;

public class Graphics2 {


    Mesh cube;

    Shader shader;

    int angle;

    public Graphics2(){

        cube = new Cube();

        // 初始化shader
        shader = Shader.CreateShader(
                "shaders/TestShader2/vertex.glsl",
                "shaders/TestShader2/frag.glsl",
                SurfaceView.DrawingView.getResources());

        shader.BindVertexAttribute(cube);

        // ==========设置纹理1=============
        Bitmap bitmap = TextureUtil.LoadBitmap(R.raw.wall,SurfaceView.DrawingView.getResources());
        shader.SetTexture2D(
                "texture1",
                bitmap,
                0,
                TextureSamplingMode.Repeat,
                TextureFilteringMode.Point
        );


        // ============设置纹理2===================
        // 绑定纹理
        Bitmap bitmap2 = TextureUtil.LoadBitmap(R.raw.grid,SurfaceView.DrawingView.getResources());;
        shader.SetTexture2D(
                "texture2",
                bitmap2,
                1,
                TextureSamplingMode.Repeat,
                TextureFilteringMode.Point
        );
    }

    public void Render(){
        // 指定本次渲染使用的着色器程序
        shader.Use();

        // 激活并绑定纹理
        shader.ActiveAndBindTexture();

        // 启用顶点数据
        shader.EnableVertexAttribute();

        angle = (angle+1)%360;
        // 物体属性
        Vector3 pos = new Vector3(2,1,1);    // 物体位置
        Vector3 rotation = new Vector3(angle,45+angle,45);   // 物体旋转角度
        Vector3 scale = new Vector3(1,1,1);  // 物体缩放程度

        // mvp变换
        float[] mMatrix = MatrixUtil.GetModelMatrix(
                pos.x,pos.y,pos.z,
                rotation.x,rotation.y,rotation.z,
                scale.x,scale.y,scale.z
        );
        float[] vMatrix = Camera.main.GetViewMatrix();
        float[] pMatrix = Camera.main.GetProjectMatrix();

        shader.SetMatrix4x4("mMatrix",mMatrix);
        shader.SetMatrix4x4("vMatrix",vMatrix);
        shader.SetMatrix4x4("pMatrix",pMatrix);

        // 进行绘制
        GLES30.glDrawElements(
                GLES30.GL_TRIANGLES,    // 绘制模式
                cube.getTriangles().length,              // 顶点个数
                GLES30.GL_UNSIGNED_INT,// 索引类型
                0
        );
    }
}
