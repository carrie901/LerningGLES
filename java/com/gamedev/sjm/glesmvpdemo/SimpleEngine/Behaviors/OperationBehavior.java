package com.gamedev.sjm.glesmvpdemo.SimpleEngine.Behaviors;

import com.gamedev.sjm.glesmvpdemo.SimpleEngine.Behavior;
import com.gamedev.sjm.glesmvpdemo.SimpleEngine.Input.MultiTouchHandler;
import com.gamedev.sjm.glesmvpdemo.SimpleEngine.Time.Time;
import com.gamedev.sjm.glesmvpdemo.SimpleEngine.Util.MathUtil.Vector2;
import com.gamedev.sjm.glesmvpdemo.SimpleEngine.Util.MathUtil.Vector3;

/**
 * 控制人物移动的脚本
 */
public class OperationBehavior extends Behavior {

    public float moveSpeed = 1.0f;

    public Vector3 left = new Vector3(90,-50,0);
    public Vector3 right = new Vector3(90,50,0);

    @Override
    public void OnUpdate() {
        super.OnUpdate();
        if(MultiTouchHandler.GetMain().isTouchDown()){
//            System.out.println("玩家按下鼠标");
            float x = MultiTouchHandler.GetMain().getTouchX(0);
            float y = MultiTouchHandler.GetMain().getTouchY(0);
//            System.out.println("鼠标坐标为：("+x+","+y+")");
        }
        if(MultiTouchHandler.GetMain().isTouchMoved()){
//            System.out.println("玩家移动鼠标");
            Vector2 moveScale = MultiTouchHandler.GetMain().getMoveScale(0);
//            System.out.println("移动刻度为：("+moveScale.u+","+moveScale.v+")");
            gameObject.transform.pos = gameObject.transform.pos.Add(new Vector3(moveSpeed*moveScale.u*Time.deltaTime,moveSpeed*Time.deltaTime*moveScale.v,0));
            if(moveScale.u<0)
            gameObject.transform.rotation = Vector3.Lerp(
                    gameObject.transform.rotation,
                    left,
                    -moveScale.u * Time.deltaTime * moveSpeed
            );
            else
                gameObject.transform.rotation = Vector3.Lerp(
                        gameObject.transform.rotation,
                        right,
                        moveScale.u * Time.deltaTime * moveSpeed
                );
        }
    }
}
