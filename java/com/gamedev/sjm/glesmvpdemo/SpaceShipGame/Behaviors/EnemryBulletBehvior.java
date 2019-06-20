package com.gamedev.sjm.glesmvpdemo.SpaceShipGame.Behaviors;

import com.gamedev.sjm.glesmvpdemo.SimpleEngine.Behavior;
import com.gamedev.sjm.glesmvpdemo.SimpleEngine.Time.Time;
import com.gamedev.sjm.glesmvpdemo.SpaceShipGame.GameObject.Bullet;
import com.gamedev.sjm.glesmvpdemo.SpaceShipGame.GameObject.EnemryBullet;

public class EnemryBulletBehvior extends Behavior {
    private float moveSpeed = 1.0f;

    @Override
    public void OnUpdate() {
        if(!((EnemryBullet)gameObject).isUsed) return;
        super.OnUpdate();
        gameObject.transform.pos.y -= moveSpeed*Time.deltaTime;
        float acceleration = 0.5f;
        moveSpeed += acceleration *Time.deltaTime;
        if(gameObject.transform.pos.y<=-3){
            ((EnemryBullet)gameObject).isUsed = false;
            moveSpeed = 1.0f;
        }
    }
}
