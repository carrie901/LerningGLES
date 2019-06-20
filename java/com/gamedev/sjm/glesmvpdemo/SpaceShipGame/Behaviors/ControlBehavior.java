package com.gamedev.sjm.glesmvpdemo.SpaceShipGame.Behaviors;

import com.gamedev.sjm.glesmvpdemo.SimpleEngine.Behavior;
import com.gamedev.sjm.glesmvpdemo.SimpleEngine.SimpleGameEnginer;
import com.gamedev.sjm.glesmvpdemo.SimpleEngine.Time.Time;
import com.gamedev.sjm.glesmvpdemo.SimpleEngine.Util.MathUtil.Vector3;
import com.gamedev.sjm.glesmvpdemo.SpaceShipGame.GameObject.Asteroid1;

import java.util.ArrayList;
import java.util.List;

public class ControlBehavior extends Behavior {

    public List<Asteroid1> asteroids;

    private float max = 0.75f;
    private float min = -0.75f;

    private float duration = 1f;
    private float time = duration;

    @Override
    public void OnStart() {
        super.OnStart();
        asteroids = new ArrayList<>();

        for(int i=0;i<5;i++){
            float x = min+ (float) (Math.random()*(max-min));
            float angleX = (float)(Math.random()*45);
            float angleZ = (float)(Math.random()*45);
            Asteroid1 asteroid1 = new Asteroid1(new Vector3(x,3,-2),new Vector3(angleX,0,angleZ));
            asteroids.add(asteroid1);
            SimpleGameEnginer.main.gameObjects.add(asteroid1);
        }

    }

    @Override
    public void OnUpdate() {
        super.OnUpdate();
        time += Time.deltaTime;
        if(time>=duration){
            // 重置
            time = 0;
            // 产生一个陨石
            Asteroid1 asteroid = null;
            // 找到一个还未被使用过的陨石，把他放置到场景中
            for(Asteroid1 obj : asteroids){
                if(!obj.isUsed){
                    obj.isUsed = true;
                    obj.isDestoryed = false;
                    asteroid = obj;
                    break;
                }
            }
            if(asteroid==null) return;
            float x = min+ (float) (Math.random()*(max-min));
            float angleX = (float)(Math.random()*45);
            float angleZ = (float)(Math.random()*45);
            asteroid.transform.pos.x = x;asteroid.transform.pos.y = 3;asteroid.transform.pos.z = -2;
            asteroid.transform.rotation = new Vector3(angleX,0,angleZ);
        }
    }
}
