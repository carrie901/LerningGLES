package com.gamedev.sjm.glesmvpdemo.SpaceShipGame.Behaviors;

import com.gamedev.sjm.glesmvpdemo.SimpleEngine.Behavior;
import com.gamedev.sjm.glesmvpdemo.SimpleEngine.SimpleGameEnginer;
import com.gamedev.sjm.glesmvpdemo.SimpleEngine.Time.Time;
import com.gamedev.sjm.glesmvpdemo.SpaceShipGame.GameObject.EnemryBullet;
import com.gamedev.sjm.glesmvpdemo.SpaceShipGame.GameObject.EnemrySpaceShip;

import java.util.ArrayList;
import java.util.List;

public class EnemryShipBehavior extends Behavior {
    private float time;

    // 每隔1s发射一个激光
    private float duration = 2f;

    private float moveSpeed = 0.5f;

    private List<EnemryBullet> enemryBullets = new ArrayList<>();

    public EnemryShipBehavior(){
        for(int i=0;i<5;i++){
            EnemryBullet enemryBullet = new EnemryBullet();
            enemryBullets.add(enemryBullet);
            SimpleGameEnginer.main.gameObjects.add(enemryBullet);
        }
    }

    @Override
    public void OnUpdate() {
        if(!((EnemrySpaceShip)gameObject).isUse) return;
        super.OnUpdate();
        time += Time.deltaTime;
        if(time>=duration){
            time = 0;
            EnemryBullet bullet = null;
            for(EnemryBullet b:enemryBullets){
                if(!b.isUsed){
                    b.isUsed = true;
                    bullet = b;
                    break;
                }
            }
            if(bullet==null) return;
            bullet.transform.pos.x = gameObject.transform.pos.x;
            bullet.transform.pos.y = gameObject.transform.pos.y-0.2f;
            bullet.transform.pos.z = gameObject.transform.pos.z;
        }

        gameObject.transform.pos.y -= moveSpeed*Time.deltaTime;
        gameObject.transform.pos.x += (-1f+(float)(2*Math.random()))*Time.deltaTime*0.1f;
        if(gameObject.transform.pos.y<=-3){
            ((EnemrySpaceShip)gameObject).isUse = false;
        }
    }
}
