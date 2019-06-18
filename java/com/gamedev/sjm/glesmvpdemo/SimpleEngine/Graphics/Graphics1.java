package com.gamedev.sjm.glesmvpdemo.SimpleEngine.Graphics;

import android.opengl.GLES30;

import com.gamedev.sjm.glesmvpdemo.SimpleEngine.Util.MatrixUtil.MatrixUtil;
import com.gamedev.sjm.glesmvpdemo.MeshObject.Quad;
import com.gamedev.sjm.glesmvpdemo.SimpleEngine.Util.ShaderUtil.Shader;
import com.gamedev.sjm.glesmvpdemo.SimpleEngine.Camera;
import com.gamedev.sjm.glesmvpdemo.SimpleEngine.components.Mesh;
import com.gamedev.sjm.glesmvpdemo.SurfaceView;
import com.gamedev.sjm.glesmvpdemo.SimpleEngine.Util.MathUtil.Vector3;

public class Graphics1 {

    public Mesh quad;
    public Shader shader;

    int angle;
    public Graphics1(){

        // 初始化mesh
        quad = new Quad();

        // 初始化shader
        shader = Shader.CreateShader(
                "shaders/TestShader1/vertex.glsl",
                "shaders/TestShader1/frag.glsl",
                SurfaceView.DrawingView.getResources());

        // 绑定顶点数据
        shader.BindVertexAttribute(quad);
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
        Vector3 pos = new Vector3(0,0,0);    // 物体位置
        Vector3 rotation = new Vector3(angle,0,0);   // 物体旋转角度
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
                quad.getTriangles().length,              // 顶点个数
                GLES30.GL_UNSIGNED_INT,// 索引类型
                0
        );
    }
}
