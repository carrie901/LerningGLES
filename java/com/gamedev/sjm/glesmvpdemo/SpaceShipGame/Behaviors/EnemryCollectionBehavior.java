package com.gamedev.sjm.glesmvpdemo.SpaceShipGame.Behaviors;

import com.gamedev.sjm.glesmvpdemo.SimpleEngine.Behavior;
import com.gamedev.sjm.glesmvpdemo.SimpleEngine.SimpleGameEnginer;
import com.gamedev.sjm.glesmvpdemo.SimpleEngine.Time.Time;
import com.gamedev.sjm.glesmvpdemo.SimpleEngine.Util.MathUtil.Vector3;
import com.gamedev.sjm.glesmvpdemo.SpaceShipGame.GameObject.EnemrySpaceShip;

import java.util.ArrayList;
import java.util.List;

public class EnemryCollectionBehavior extends Behavior {

    List<EnemrySpaceShip>enemrySpaceShips = new ArrayList<>();

    float time = 0;
    float duration = 3f;

    private float max = 0.75f;
    private float min = -0.75f;

    public  EnemryCollectionBehavior(){
        for (int i=0;i<5;i++){
            EnemrySpaceShip ship = new EnemrySpaceShip();
            enemrySpaceShips.add(ship);
            SimpleGameEnginer.main.gameObjects.add(ship);
        }
    }

    @Override
    public void OnUpdate() {
        super.OnUpdate();
        time += Time.deltaTime;
        if(time>=duration){
            time = 0;
            EnemrySpaceShip ship = null;
            for(EnemrySpaceShip s:enemrySpaceShips){
                if(!s.isUse){
                    s.isUse = true;
                    ship = s;
                    break;
                }
            }
            if(ship==null) return;

            float x = min+ (float) (Math.random()*(max-min));
            ship.transform.pos.x = x;ship.transform.pos.y = 3;ship.transform.pos.z = -2;
        }
    }
}
