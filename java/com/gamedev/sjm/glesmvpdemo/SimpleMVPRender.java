package com.gamedev.sjm.glesmvpdemo;

import android.opengl.GLES20;
import android.opengl.GLES30;
import android.opengl.GLSurfaceView;
import android.view.MotionEvent;
import android.view.View;

import com.gamedev.sjm.glesmvpdemo.SimpleEngine.Collider.Collider;
import com.gamedev.sjm.glesmvpdemo.SpaceShipGame.Behaviors.OperationBehavior;
import com.gamedev.sjm.glesmvpdemo.SimpleEngine.Graphics.Graphics1;
import com.gamedev.sjm.glesmvpdemo.SimpleEngine.Util.MathUtil.Vector3;
import com.gamedev.sjm.glesmvpdemo.SimpleEngine.Camera;
import com.gamedev.sjm.glesmvpdemo.SimpleEngine.GameObject;
import com.gamedev.sjm.glesmvpdemo.SimpleEngine.SimpleGameEnginer;
import com.gamedev.sjm.glesmvpdemo.SimpleEngine.Time.Time;
import com.gamedev.sjm.glesmvpdemo.SimpleEngine.components.MeshRender;
import com.gamedev.sjm.glesmvpdemo.SpaceShipGame.GameObject.Asteroid1;
import com.gamedev.sjm.glesmvpdemo.SpaceShipGame.GameObject.Bullet;
import com.gamedev.sjm.glesmvpdemo.SpaceShipGame.GameObject.ColliderTest;
import com.gamedev.sjm.glesmvpdemo.SpaceShipGame.GameObject.EnemrySpaceShip;
import com.gamedev.sjm.glesmvpdemo.SpaceShipGame.GameObject.GameControl;
import com.gamedev.sjm.glesmvpdemo.SpaceShipGame.GameObject.SpaceBackGround;
import com.gamedev.sjm.glesmvpdemo.SpaceShipGame.GameObject.SpaceShip;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

/**
 * 基于简单的渲染管线的渲染线程
 */
public class SimpleMVPRender implements GLSurfaceView.Renderer,View.OnTouchListener {

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

    public static float deltaTime = 0.0f; // 当前帧与上一帧的时间差
    public static long lastFrame = 0; // 上一帧的时间

    Graphics1 g;

    public SimpleMVPRender(GLSurfaceView view){
        this.view = view;
//        this.view.setOnTouchListener(this);
        gameEnginer = new SimpleGameEnginer();
    }
    MeshRender meshRender;
    GameObject gameObject;
    public void test(){

        SpaceBackGround backGround = new SpaceBackGround();
        gameEnginer.gameObjects.add(backGround);

        SpaceShip spaceShip = new SpaceShip();
        spaceShip.AddComponent(new OperationBehavior());
        gameEnginer.gameObjects.add(spaceShip);

//        EnemrySpaceShip enemrySpaceShip = new EnemrySpaceShip();
//        gameEnginer.gameObjects.add(enemrySpaceShip);

//        Bullet bullet = new Bullet();
//        gameEnginer.gameObjects.add(bullet);

//        GameControl control = new GameControl();
//        gameEnginer.gameObjects.add(control);

//        Asteroid1 asteroid1 = new Asteroid1();
//        asteroid1.transform.pos = new Vector3(0.75f,0,-2);
//        gameEnginer.gameObjects.add(asteroid1);

//        SpaceShip spaceShip1 = new SpaceShip();
//        spaceShip1.transform.pos = new Vector3(0,2,-2);
//        gameEnginer.gameObjects.add(spaceShip1);

//        ColliderTest colliderTest = new ColliderTest();
//        OperationBehavior behavior = new OperationBehavior();
//        colliderTest.AddComponent(behavior);
//        colliderTest.transform.scale = new Vector3(0.2f,0.1f,0.2f);
//        gameEnginer.gameObjects.add(colliderTest);

//        ColliderTest colliderTest1 = new ColliderTest();
//        colliderTest1.transform.pos = new Vector3(0,1,-2);
//        gameEnginer.gameObjects.add(colliderTest1);
//        Sphere sphere = new Sphere();
//        gameEnginer.gameObjects.add(sphere);

        //=============对象2==================================
//        GameObject gameObject2 = new GameObject();
//        gameObject2.transform.pos = new Vector3(1,0,0);
//        gameObject2.transform.rotation = new Vector3(0,0,0);
//        Mesh cube = new Quad();
//        // 初始化shader
//        Shader shader2 = Shader.CreateShader(
//                "shaders/TestShader1/vertex.glsl",
//                "shaders/TestShader1/frag.glsl",
//                SurfaceView.DrawingView.getResources());
//        MeshRender meshRender2 = new MeshRender(shader2,cube);
//        gameObject2.AddComponent("MeshRender",meshRender2);
//        AsteroidBehavior rotationBehavior = new AsteroidBehavior();
//        gameObject2.AddComponent(rotationBehavior);
//        gameEnginer.gameObjects.add(gameObject2);
//
//        //================对象3=======================
//        GameObject gameObject3 = new GameObject();
//        gameObject3.transform.pos = new Vector3(-1,-1,0);
//        gameObject3.transform.rotation = new Vector3(0,0,0);
//        Mesh cube2 = new Cube();
//        // 初始化shader
//        Shader shader3 = Shader.CreateShader(
//                "shaders/TestShader1/vertex.glsl",
//                "shaders/TestShader1/frag.glsl",
//                SurfaceView.DrawingView.getResources());
//        MeshRender meshRender3 = new MeshRender(shader3,cube);
//        gameObject3.AddCompoent("MeshRender",meshRender3);
//        gameEnginer.gameObjects.add(gameObject3);

        //===========对象1=====================================
//        gameObject = new GameObject();
//        gameObject.transform.pos = new Vector3(0,0,0);
//        gameObject.transform.rotation = new Vector3(0,0,0);
//        gameObject.transform.scale = new Vector3(0.4f,0.4f,0.4f);
//        Mesh quad = new Cube();
//        // 初始化shader
//        Shader shader1 = Shader.CreateShader(
//                "shaders/TestShader1/vertex.glsl",
//                "shaders/TestShader1/frag.glsl",
//                SurfaceView.DrawingView.getResources());
//        meshRender = new MeshRender(shader1,quad);
//        gameObject.AddComponent("MeshRender",meshRender);
//        gameEnginer.gameObjects.add(gameObject);
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
        test();

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

    /**
     * 绘制一个三角形图元的方法
     * @param v1
     * @param v2
     * @param v3
     * @param MVP
     */
    public void DrawPrimitive(Vector3 v1,Vector3 v2,Vector3 v3,float[] MVP){
    }


    @Override
    public boolean onTouch(View v, MotionEvent event) {
        float cameraSpeed = 2.5f * Time.deltaTime;
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
                }
                if(y2 - y1 > 50) {
                    cameraPos.y -= 1f * cameraSpeed;
                }
                if(x1 - x2 > 50) {
                    cameraPos.x += 1f * cameraSpeed;
                }
                if(x2 - x1 > 50) {
                    cameraPos.x -= 1f * cameraSpeed;
                }
                break;
        }
        return true;
    }
}
