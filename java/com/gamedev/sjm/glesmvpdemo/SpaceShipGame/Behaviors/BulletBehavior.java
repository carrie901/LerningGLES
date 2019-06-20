package com.gamedev.sjm.glesmvpdemo.SpaceShipGame.Behaviors;

import com.gamedev.sjm.glesmvpdemo.SimpleEngine.Behavior;
import com.gamedev.sjm.glesmvpdemo.SimpleEngine.Time.Time;
import com.gamedev.sjm.glesmvpdemo.SpaceShipGame.GameObject.Bullet;

public class BulletBehavior extends Behavior {

    private float moveSpeed = 2.0f;
    private float acceleration = 0.5f;

    @Override
    public void OnUpdate() {
        if(!((Bullet)gameObject).isUsed) return;
        super.OnUpdate();
        gameObject.transform.pos.y += moveSpeed*Time.deltaTime;
        moveSpeed += acceleration*Time.deltaTime;
        if(gameObject.transform.pos.y>=3){
            ((Bullet)gameObject).isUsed = false;
            moveSpeed = 1.0f;
        }
    }
}
