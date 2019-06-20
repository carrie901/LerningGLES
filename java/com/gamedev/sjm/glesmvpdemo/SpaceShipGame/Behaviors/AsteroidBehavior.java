package com.gamedev.sjm.glesmvpdemo.SpaceShipGame.Behaviors;

import com.gamedev.sjm.glesmvpdemo.SimpleEngine.Behavior;
import com.gamedev.sjm.glesmvpdemo.SimpleEngine.Time.Time;
import com.gamedev.sjm.glesmvpdemo.SimpleEngine.Util.MathUtil.Vector3;
import com.gamedev.sjm.glesmvpdemo.SpaceShipGame.GameObject.Asteroid1;

public class AsteroidBehavior extends Behavior {

    float moveSpeed = 0.5f;
    float anglespeed = 70;

    @Override
    public void OnUpdate() {
        if(!((Asteroid1)gameObject).isUsed) return;
        super.OnUpdate();
        gameObject.transform.rotation.x =
                (gameObject.transform.rotation.x + anglespeed*Time.deltaTime)%360;
        gameObject.transform.rotation.z =
                (gameObject.transform.rotation.z + anglespeed*Time.deltaTime)%360;

        gameObject.transform.pos.y = gameObject.transform.pos.y - moveSpeed*Time.deltaTime;
        if(gameObject.transform.pos.y<=-3){
            ((Asteroid1)gameObject).isUsed = false;
        }
    }
}
