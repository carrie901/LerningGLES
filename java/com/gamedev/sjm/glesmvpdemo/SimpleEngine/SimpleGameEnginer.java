package com.gamedev.sjm.glesmvpdemo.SimpleEngine;

import android.graphics.Color;
import android.opengl.GLES30;

import com.gamedev.sjm.glesmvpdemo.SimpleEngine.InterFace.Game;
import com.gamedev.sjm.glesmvpdemo.SimpleEngine.InterFace.Renderable;
import com.gamedev.sjm.glesmvpdemo.SimpleEngine.Time.Time;
import com.gamedev.sjm.glesmvpdemo.SimpleEngine.components.Component;

import java.util.ArrayList;
import java.util.List;

public class SimpleGameEnginer implements Game {

    public static SimpleGameEnginer main;

    // 简单管理场景内所有游戏对象
    public List<GameObject> gameObjects = new ArrayList<>();

    private void CalculateTimeDelta(){
        // 计算每帧之间的时间差
        long currentFrame = System.currentTimeMillis();
        Time.deltaTime = (currentFrame-Time.lastFrame)/1000f;
        Time.lastFrame = currentFrame;
    }

    private void GLClearBuffer(){
        // 清除深度缓冲与颜色缓冲
        GLES30.glClearColor(0.35f,0.5f,0.35f,1.0f);
        GLES30.glClear(GLES30.GL_DEPTH_BUFFER_BIT |
                GLES30.GL_COLOR_BUFFER_BIT);
    }

    public void deleteGameObject(GameObject gameObject){
        gameObjects.remove(gameObject);
    }

    public void Update(){

        // 计算每帧之间的时间差
        CalculateTimeDelta();

        // 清除颜色和深度缓冲
        GLClearBuffer();

        // 逻辑更新
        OnUpdate();
        // 渲染更新
        RenderAllGameObject();
    }

    @Override
    public void OnStart() {
        // 更新所有游戏对象的Component组件
        for(GameObject gameObject : gameObjects){
            for(Component component : gameObject.GetAllComponent()){
                component.OnStart();
            }
        }
    }

    /**
     * 每帧更新操作
     */
    @Override
    public void OnUpdate() {
        // 更新所有游戏对象的Component组件
        for(GameObject gameObject : gameObjects){
            for(Component component : gameObject.GetAllComponent()){
                component.OnUpdate();
            }
        }
    }

    /**
     * 渲染所有游戏对象,
     * 在所有游戏逻辑更新完后进行绘制
     */
    public void RenderAllGameObject(){
        for(Renderable object : gameObjects){
            object.Render();
        }
    }

    @Override
    public void OnExit() {
        // 更新所有游戏对象的Component组件
        for(GameObject gameObject : gameObjects){
            for(Component component : gameObject.GetAllComponent()){
                component.OnExit();
            }
        }
    }
}
