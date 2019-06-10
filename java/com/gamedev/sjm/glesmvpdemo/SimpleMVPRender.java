package com.gamedev.sjm.glesmvpdemo;

import android.opengl.GLES30;
import android.opengl.GLSurfaceView;
import android.opengl.Matrix;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

/**
 * 基于简单的渲染管线的渲染线程
 */
public class SimpleMVPRender implements GLSurfaceView.Renderer {

    GLSurfaceView view;

    TestGraphics graphics;

    public SimpleMVPRender(GLSurfaceView view){
        this.view = view;
    }

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        // 清除屏幕
        GLES30.glClearColor(0,0,0,1.0f);
        // 打开深度测试
        GLES30.glEnable(GLES30.GL_DEPTH_TEST);
    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {
        GLES30.glViewport(0,0,width,height);
        graphics = new TestGraphics(view);

    }

    @Override
    public void onDrawFrame(GL10 gl) {

        // 清除深度缓冲与颜色缓冲
        GLES30.glClearColor(0.35f,0.5f,0.35f,1.0f);
        GLES30.glClear(GLES30.GL_DEPTH_BUFFER_BIT |
        GLES30.GL_COLOR_BUFFER_BIT);

        graphics.DrawTriangle();
    }

    /**
     * 绘制一个三角形图元的方法
     * @param v1
     * @param v2
     * @param v3
     * @param MVP
     */
    public void DrawPrimitive(Vector3 v1,Vector3 v2,Vector3 v3,float[] MVP){
    }




}
