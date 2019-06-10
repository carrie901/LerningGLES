package com.gamedev.sjm.glesmvpdemo;

import android.graphics.Bitmap;
import android.opengl.GLES30;
import android.opengl.GLSurfaceView;
import android.opengl.Matrix;
import android.support.v4.math.MathUtils;

import com.gamedev.sjm.glesmvpdemo.BufferUtil.BufferUtil;
import com.gamedev.sjm.glesmvpdemo.MatrixUtil.MatrixUtil;
import com.gamedev.sjm.glesmvpdemo.MeshObject.Cube;
import com.gamedev.sjm.glesmvpdemo.MeshObject.Quad;
import com.gamedev.sjm.glesmvpdemo.ShaderUtil.Shader;
import com.gamedev.sjm.glesmvpdemo.ShaderUtil.ShaderUtil;
import com.gamedev.sjm.glesmvpdemo.SimpleEngine.Mesh;
import com.gamedev.sjm.glesmvpdemo.TextureUtil.TextureFilteringMode;
import com.gamedev.sjm.glesmvpdemo.TextureUtil.TextureSamplingMode;
import com.gamedev.sjm.glesmvpdemo.TextureUtil.TextureUtil;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;

public class TestGraphics {
    GLSurfaceView view;
    // 着色器ID
    int program;
    // 着色器对象
    Shader shader;

    Mesh quad;

    public TestGraphics(GLSurfaceView view){
        this.view = view;
        quad = new Cube();
        InitShader();
    }

    /**
     * 初始化着色器程序
     */
    public void InitShader(){
        String vertexShader = ShaderUtil.loadFromAssetsFile("vertex.glsl",view.getResources());
        String fragmentShader = ShaderUtil.loadFromAssetsFile("frag.glsl",view.getResources());
        // 加载着色器程序
        program = ShaderUtil.CreateProgram(vertexShader,fragmentShader);
        shader = new Shader(program);

        InitTexture();

        // 绑定并设置各顶点属性
        shader.BindVertexAttribute(quad);
    }

    public void InitTexture(){

        // ==========设置纹理1=============
        Bitmap bitmap = TextureUtil.LoadBitmap(R.raw.wall,view.getResources());
        shader.SetTexture2D(
                "texture1",
                bitmap,
                0,
                TextureSamplingMode.Repeat,
                TextureFilteringMode.Point
                );


        // ============设置纹理2===================
        // 绑定纹理
        Bitmap bitmap2 = TextureUtil.LoadBitmap(R.raw.grid,view.getResources());;
        shader.SetTexture2D(
                "texture2",
                bitmap2,
                1,
                TextureSamplingMode.Repeat,
                TextureFilteringMode.Point
        );
    }

    int angle;
    public void DrawTriangle(){

        // 指定本次渲染使用的着色器程序
        shader.Use();

        // 激活并绑定纹理
        shader.ActiveAndBindTexture();

        // 启用顶点数据
        shader.EnableVertexAttribute();

        angle = (angle+1)%360;
        // 物体属性
        Vector3 pos = new Vector3(0,0,0);    // 物体位置
        Vector3 rotation = new Vector3(angle,angle,angle);   // 物体旋转角度
        Vector3 scale = new Vector3(1,1,1);  // 物体缩放程度

        // 摄像机属性
        Vector3 cameraPos = new Vector3(0,0,-2);
        Vector3 up = new Vector3(0,1,0);
        int right = 1;
        int left = -1;
        int top = 1;
        int bottom = -1;
        float near = 1;
        int far = 10;

        // mvp变换
        float[] mMatrix = MatrixUtil.GetModelMatrix(
                pos.x,pos.y,pos.z,
                rotation.x,rotation.y,rotation.z,
                scale.x,scale.y,scale.z
        );
        float[] vMatrix = MatrixUtil.GetViewMatrix(
                cameraPos,pos,up
        );
        float[] pMatrix = MatrixUtil.GetProjectFrustumMatrix(
                near,far,top,bottom,right,left
        );

        float[] mvp = new float[16];
        Matrix.multiplyMM(mvp,0,vMatrix,0,mMatrix,0);
        Matrix.multiplyMM(mvp,0,pMatrix,0,mvp,0);
        shader.SetMatrix4x4("mvp",mvp);

        // 进行绘制
//        GLES30.glDrawArrays(GLES30.GL_TRIANGLE_FAN, 0, 6);
        GLES30.glDrawElements(
                GLES30.GL_TRIANGLES,    // 绘制模式
                36,              // 顶点个数
                GLES30.GL_UNSIGNED_INT,// 索引类型
                0
        );
    }


    public void DrawMesh(Mesh mesh,float[] mMatrix,Shader shader){

    }
    public void DrawMesh(Mesh mesh,Vector3 pos,Vector3 rotation,Vector3 scale,Shader shader){
        float[] mMatrix = MatrixUtil.GetModelMatrix(
                pos.x,pos.y,pos.z,
                rotation.x,rotation.y,rotation.z,
                scale.x,scale.y,scale.z
        );
        DrawMesh(mesh,mMatrix,shader);
    }
}
