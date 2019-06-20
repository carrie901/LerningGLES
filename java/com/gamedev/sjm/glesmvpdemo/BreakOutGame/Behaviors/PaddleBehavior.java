package com.gamedev.sjm.glesmvpdemo.BreakOutGame.Behaviors;

import com.gamedev.sjm.glesmvpdemo.SimpleEngine.Behavior;
import com.gamedev.sjm.glesmvpdemo.SimpleEngine.Input.MultiTouchHandler;
import com.gamedev.sjm.glesmvpdemo.SimpleEngine.Time.Time;
import com.gamedev.sjm.glesmvpdemo.SimpleEngine.Util.MathUtil.Vector2;
import com.gamedev.sjm.glesmvpdemo.SimpleEngine.Util.MathUtil.Vector3;

public class PaddleBehavior extends Behavior {

    float moveSpeed = 1.0f;

    Vector2 xRange = new Vector2(-0.8f,0.8f);

    @Override
    public void OnUpdate() {
        super.OnUpdate();
        if(MultiTouchHandler.GetMain().isTouchMoved()){
            Vector2 moveScale = MultiTouchHandler.GetMain().getMoveScale(0);
            float nextX = moveScale.u*Time.deltaTime*moveSpeed + gameObject.transform.pos.x;
            if(nextX>=xRange.u && nextX<=xRange.v){
                gameObject.transform.pos.x = nextX;
            }
        }
    }
}
