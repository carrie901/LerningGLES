package com.gamedev.sjm.glesmvpdemo.SimpleEngine.Graphics;

import android.opengl.GLES30;
import android.opengl.Matrix;

import com.gamedev.sjm.glesmvpdemo.SimpleEngine.Camera;
import com.gamedev.sjm.glesmvpdemo.SimpleEngine.GameObject;
import com.gamedev.sjm.glesmvpdemo.SimpleEngine.Util.MathUtil.Vector3;
import com.gamedev.sjm.glesmvpdemo.SimpleEngine.Util.MatrixUtil.MatrixUtil;
import com.gamedev.sjm.glesmvpdemo.SimpleEngine.Util.ShaderUtil.Shader;
import com.gamedev.sjm.glesmvpdemo.SimpleEngine.components.Mesh;
import com.gamedev.sjm.glesmvpdemo.SimpleEngine.components.Transform;

public class Graphics {

    public static void DrawMesh(Mesh mesh, Shader shader, Vector3 position,Vector3 rotation,Vector3 scale,int renderMode){
        shader.BindVertexAttribute(mesh);

        // 指定本次渲染使用的着色器程序
        shader.Use();

        // 激活并绑定纹理
        shader.ActiveAndBindTexture();

        // 启用顶点数据
        shader.EnableVertexAttribute();

        // mvp变换
        float[] mMatrix = MatrixUtil.GetModelMatrix(
                position.x,position.y,position.z,
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
                renderMode,                     // 绘制模式
                mesh.getTriangles().length,              // 顶点个数
                GLES30.GL_UNSIGNED_INT,                  // 索引类型
                0
        );
    }

    public static void DrawMesh(Mesh mesh, Shader shader, Transform transform,int renderMode){
        shader.BindVertexAttribute(mesh);

        // 指定本次渲染使用的着色器程序
        shader.Use();

        // 激活并绑定纹理
        shader.ActiveAndBindTexture();

        // 启用顶点数据
        shader.EnableVertexAttribute();

        // mvp变换
        float[] mMatrix = transform.getModelMatrix();
        float[] vMatrix = Camera.main.GetViewMatrix();
        float[] pMatrix = Camera.main.GetProjectMatrix();

        shader.SetMatrix4x4("mMatrix",mMatrix);
        shader.SetMatrix4x4("vMatrix",vMatrix);
        shader.SetMatrix4x4("pMatrix",pMatrix);

        // 进行绘制
        GLES30.glDrawElements(
                renderMode,                     // 绘制模式
                mesh.getTriangles().length,              // 顶点个数
                GLES30.GL_UNSIGNED_INT,                  // 索引类型
                0
        );
    }
}
