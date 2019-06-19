package com.gamedev.sjm.glesmvpdemo.SpaceShipGame.Behaviors;

import com.gamedev.sjm.glesmvpdemo.SimpleEngine.Behavior;
import com.gamedev.sjm.glesmvpdemo.SimpleEngine.Time.Time;
import com.gamedev.sjm.glesmvpdemo.SimpleEngine.Util.MathUtil.Vector3;

public class AsteroidBehavior extends Behavior {

    float moveSpeed = 1;
    float anglespeed = 70;

    @Override
    public void OnUpdate() {
        super.OnUpdate();
        gameObject.transform.rotation.x =
                (gameObject.transform.rotation.x + anglespeed*Time.deltaTime)%360;
        gameObject.transform.rotation.z =
                (gameObject.transform.rotation.z + anglespeed*Time.deltaTime)%360;
//        gameObject.transform.pos.y -= moveSpeed*Time.deltaTime;
    }
}
