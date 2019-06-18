package com.gamedev.sjm.glesmvpdemo.SimpleEngine.components;

import android.opengl.GLES30;

import com.gamedev.sjm.glesmvpdemo.SimpleEngine.Util.ShaderUtil.Shader;
import com.gamedev.sjm.glesmvpdemo.SimpleEngine.Camera;
import com.gamedev.sjm.glesmvpdemo.SimpleEngine.GameObject;

public class MeshRender extends Component{

    private boolean isInit;
    private int renderMode = GLES30.GL_TRIANGLES;

    Shader shader;
    Mesh mesh;
    public MeshRender(Shader shader,Mesh mesh){
        this.shader = shader;
        this.mesh = mesh;
    }
    public MeshRender(Shader shader,Mesh mesh,int renderMode){
        this.shader = shader;
        this.mesh = mesh;
        this.renderMode = renderMode;
    }
    public void PrepareRenderMesh(){
        shader.BindVertexAttribute(mesh);
        isInit = true;
    }

    public void Render(GameObject gameObject){
        // 初始化渲染数据
        PrepareRenderMesh();

        // 指定本次渲染使用的着色器程序
        shader.Use();

        // 激活并绑定纹理
        shader.ActiveAndBindTexture();

        // 启用顶点数据
        shader.EnableVertexAttribute();

        // mvp变换
        float[] mMatrix = gameObject.transform.getModelMatrix();
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
