package com.gamedev.sjm.glesmvpdemo.SimpleEngine.components;

import com.gamedev.sjm.glesmvpdemo.SimpleEngine.GameObject;
import com.gamedev.sjm.glesmvpdemo.SimpleEngine.InterFace.Game;

/**
 * 通用的组件对象
 */
public class Component implements Game {

    public void setGameObject(GameObject gameObject) {
        this.gameObject = gameObject;
    }

    // 该组件依附的游戏对象
    protected GameObject gameObject;

    public Component(){}

    @Override
    public void OnStart() {

    }

    @Override
    public void OnUpdate() {

    }

    @Override
    public void OnExit() {

    }
}
