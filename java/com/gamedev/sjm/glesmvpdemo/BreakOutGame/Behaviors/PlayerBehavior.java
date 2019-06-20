package com.gamedev.sjm.glesmvpdemo.BreakOutGame.Behaviors;

import com.gamedev.sjm.glesmvpdemo.SimpleEngine.Behavior;
import com.gamedev.sjm.glesmvpdemo.SimpleEngine.GameObject;
import com.gamedev.sjm.glesmvpdemo.SimpleEngine.Time.Time;
import com.gamedev.sjm.glesmvpdemo.SimpleEngine.Util.MathUtil.Vector2;
import com.gamedev.sjm.glesmvpdemo.SimpleEngine.Util.MathUtil.Vector3;
import com.gamedev.sjm.glesmvpdemo.SimpleEngine.components.Transform;

public class PlayerBehavior extends Behavior {

    // 移动方向
    public Vector3 direction = new Vector3(0,-1,0);

    float moveSpeed = 1.0f;

    @Override
    public void OnUpdate() {
        super.OnUpdate();
        gameObject.transform.pos.x += moveSpeed*Time.deltaTime*direction.x;
        gameObject.transform.pos.y += moveSpeed*Time.deltaTime*direction.y;
    }
}
