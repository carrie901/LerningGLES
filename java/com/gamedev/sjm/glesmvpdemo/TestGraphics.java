package com.gamedev.sjm.glesmvpdemo;

import android.graphics.Bitmap;
import android.opengl.GLES30;
import android.opengl.GLSurfaceView;
import android.opengl.Matrix;
import android.support.v4.math.MathUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;

import com.gamedev.sjm.glesmvpdemo.BufferUtil.BufferUtil;
import com.gamedev.sjm.glesmvpdemo.MatrixUtil.MatrixUtil;
import com.gamedev.sjm.glesmvpdemo.MeshObject.Cube;
import com.gamedev.sjm.glesmvpdemo.MeshObject.Quad;
import com.gamedev.sjm.glesmvpdemo.ShaderUtil.Shader;
import com.gamedev.sjm.glesmvpdemo.ShaderUtil.ShaderUtil;
import com.gamedev.sjm.glesmvpdemo.SimpleEngine.Camera;
import com.gamedev.sjm.glesmvpdemo.SimpleEngine.Mesh;
import com.gamedev.sjm.glesmvpdemo.TextureUtil.TextureFilteringMode;
import com.gamedev.sjm.glesmvpdemo.TextureUtil.TextureSamplingMode;
import com.gamedev.sjm.glesmvpdemo.TextureUtil.TextureUtil;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;

public class TestGraphics implements View.OnTouchListener {
    GLSurfaceView view;
    // 着色器ID
    int program;
    // 着色器对象
    Shader shader;

    Mesh quad;

    private float x1;
    private float y1;

    public Vector3 cameraPos;
    private Camera camera;
    public Vector3 cameraRotation = Vector3.Zero;
    Vector3 up = new Vector3(0,1,0);
    float near = 0.3f;
    int far = 10;


    public TestGraphics(GLSurfaceView view){
        this.view = view;
        cameraPos = new Vector3(0,0,-4);
        this.view.setOnTouchListener(this);
        quad = new Cube();
        InitShader();

        camera = new Camera(cameraPos,cameraRotation,up,45f,near,far,720f/1280f);
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
    float cameraAngle;
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
        Vector3 rotation = new Vector3(angle,45,45);   // 物体旋转角度
        Vector3 scale = new Vector3(1,1,1);  // 物体缩放程度

        cameraAngle = (cameraAngle+0.1f)%30;
        camera.transform.pos = cameraPos;
        // mvp变换
        float[] mMatrix = MatrixUtil.GetModelMatrix(
                pos.x,pos.y,pos.z,
                rotation.x,rotation.y,rotation.z,
                scale.x,scale.y,scale.z
        );
        float[] vMatrix = camera.GetViewMatrix();
        float[] pMatrix = camera.GetProjectMatrix();

        shader.SetMatrix4x4("mMatrix",mMatrix);
        shader.SetMatrix4x4("vMatrix",vMatrix);
        shader.SetMatrix4x4("pMatrix",pMatrix);

        // 进行绘制
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

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        float cameraSpeed = 2.5f * SimpleMVPRender.deltaTime;
        System.out.println(cameraSpeed);
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                //当手指按下的时候
                x1 = event.getX();
                y1 = event.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                //当手指移动的时候
                float x2 = event.getX();
                float y2 = event.getY();
                if(y1 - y2 > 50) {
                    cameraPos.y += 1f * cameraSpeed;
                } else if(y2 - y1 > 50) {
                    cameraPos.y -= 1f * cameraSpeed;
                } else if(x1 - x2 > 50) {
                    cameraPos.x += 1f * cameraSpeed;
                } else if(x2 - x1 > 50) {
                    cameraPos.x -= 1f * cameraSpeed;
                }
                break;
        }
        return true;
    }
}
