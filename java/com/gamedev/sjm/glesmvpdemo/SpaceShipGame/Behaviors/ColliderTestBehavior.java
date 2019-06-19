package com.gamedev.sjm.glesmvpdemo.SpaceShipGame.Behaviors;

import com.gamedev.sjm.glesmvpdemo.SimpleEngine.Behavior;
import com.gamedev.sjm.glesmvpdemo.SimpleEngine.Collider.Collider;
import com.gamedev.sjm.glesmvpdemo.SimpleEngine.GameObject;

public class ColliderTestBehavior extends Behavior {
    public Collider anotherCollider;
    Collider collider;

    @Override
    public void OnStart() {
        super.OnStart();
        collider = gameObject.GetComponent("Collider");
    }

    @Override
    public void OnUpdate() {
        super.OnUpdate();

    }
}
