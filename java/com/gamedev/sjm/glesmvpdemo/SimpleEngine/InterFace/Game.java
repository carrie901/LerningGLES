package com.gamedev.sjm.glesmvpdemo.SimpleEngine.InterFace;

public interface Game {
    /**
     * 游戏开始时的初始化工作
     */
    void OnStart();

    /**
     * 每帧的游戏逻辑更新
     */
    void OnUpdate();

    /**
     * 游戏结束时的离开工作
     */
    void OnExit();
}
