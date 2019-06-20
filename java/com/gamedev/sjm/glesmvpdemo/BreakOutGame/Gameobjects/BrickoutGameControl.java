package com.gamedev.sjm.glesmvpdemo.BreakOutGame.Gameobjects;

import com.gamedev.sjm.glesmvpdemo.BreakOutGame.Behaviors.ControlBehavior;
import com.gamedev.sjm.glesmvpdemo.SimpleEngine.GameObject;

public class BrickoutGameControl extends GameObject {
    public BrickoutGameControl(){
        AddComponent(new ControlBehavior());
    }
}
