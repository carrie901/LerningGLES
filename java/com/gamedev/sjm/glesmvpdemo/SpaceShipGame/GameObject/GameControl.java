package com.gamedev.sjm.glesmvpdemo.SpaceShipGame.GameObject;

import com.gamedev.sjm.glesmvpdemo.SimpleEngine.GameObject;
import com.gamedev.sjm.glesmvpdemo.SpaceShipGame.Behaviors.ControlBehavior;
import com.gamedev.sjm.glesmvpdemo.SpaceShipGame.Behaviors.EnemryCollectionBehavior;

public class GameControl extends GameObject {
    ControlBehavior controlBehavior;
    public GameControl(){
        controlBehavior = new ControlBehavior();
        AddComponent(controlBehavior);
        AddComponent(new EnemryCollectionBehavior());
    }
}
