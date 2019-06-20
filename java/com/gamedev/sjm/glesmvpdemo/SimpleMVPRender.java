package com.gamedev.sjm.glesmvpdemo;

import android.opengl.GLES20;
import android.opengl.GLES30;
import android.opengl.GLSurfaceView;
import android.view.MotionEvent;
import android.view.View;

import com.gamedev.sjm.glesmvpdemo.SpaceShipGame.Behaviors.OperationBehavior;
import com.gamedev.sjm.glesmvpdemo.SimpleEngine.Graphics.Graphics1;
import com.gamedev.sjm.glesmvpdemo.SimpleEngine.Util.MathUtil.Vector3;
import com.gamedev.sjm.glesmvpdemo.SimpleEngine.Camera;
import com.gamedev.sjm.glesmvpdemo.SimpleEngine.GameObject;
import com.gamedev.sjm.glesmvpdemo.SimpleEngine.SimpleGameEnginer;
import com.gamedev.sjm.glesmvpdemo.SimpleEngine.Time.Time;
import com.gamedev.sjm.glesmvpdemo.SimpleEngine.components.MeshRender;
import com.gamedev.sjm.glesmvpdemo.SpaceShipGame.GameObject.GameControl;
import com.gamedev.sjm.glesmvpdemo.SpaceShipGame.GameObject.SpaceBackGround;
import com.gamedev.sjm.glesmvpdemo.SpaceShipGame.GameObject.SpaceShip;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

/**
 * 基于简单的渲染管线的渲染线程
 */
public class SimpleMVPRender implements GLSurfaceView.Renderer {

    GLSurfaceView view;

    SimpleGameEnginer gameEnginer;

    private float x1;
    private float y1;

    public Vector3 cameraPos;
    private Camera camera;
    public Vector3 cameraRotation = Vector3.Zero;
    Vector3 up = new Vector3(0,1,0);
    float near = 0.3f;
    int far = 15;

    Graphics1 g;

    public SimpleMVPRender(GLSurfaceView view){
        this.view = view;
        gameEnginer = new SimpleGameEnginer();
    }

    public void StartSpaceShipGame(){
        SpaceBackGround backGround = new SpaceBackGround();
        gameEnginer.gameObjects.add(backGround);
        SpaceShip spaceShip = new SpaceShip();
        spaceShip.AddComponent(new OperationBehavior());
        gameEnginer.gameObjects.add(spaceShip);
        GameControl control = new GameControl();
        gameEnginer.gameObjects.add(control);
    }

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        // 清除屏幕
        GLES30.glClearColor(0,0,0,1.0f);
        // 打开深度测试
        GLES30.glEnable(GLES30.GL_DEPTH_TEST);

        GLES30.glEnable(GLES30.GL_BLEND); //启用融合
        GLES30.glBlendFunc(GLES30.GL_SRC_ALPHA,GLES20.GL_ONE_MINUS_SRC_ALPHA);//定义源因子与目的因子

        cameraPos = new Vector3(0,0,-5);
        cameraRotation = new Vector3(0,0,0);
        // 初始化摄像机
        camera = new Camera(cameraPos,cameraRotation,up,45f,near,far,720f/1280f);;
        camera.SetMain();
        // 测试,给场景添加游戏对象
        StartSpaceShipGame();

        gameEnginer.OnStart();
    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {
        GLES30.glViewport(0,0,width,height);
    }

    @Override
    public void onDrawFrame(GL10 gl) {
        camera.transform.pos = cameraPos;
        gameEnginer.Update();
    }

}
