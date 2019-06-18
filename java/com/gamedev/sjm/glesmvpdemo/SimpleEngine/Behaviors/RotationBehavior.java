package com.gamedev.sjm.glesmvpdemo.SimpleEngine.Behaviors;

import com.gamedev.sjm.glesmvpdemo.SimpleEngine.Behavior;

public class RotationBehavior extends Behavior {

    int anglespeed = 1;

    @Override
    public void OnUpdate() {
        super.OnUpdate();
        this.gameObject.transform.rotation.y = (this.gameObject.transform.rotation.y+anglespeed)%360;
    }
}
