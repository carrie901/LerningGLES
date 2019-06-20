package com.gamedev.sjm.glesmvpdemo.SpaceShipGame.Behaviors;

import com.gamedev.sjm.glesmvpdemo.SimpleEngine.Behavior;
import com.gamedev.sjm.glesmvpdemo.SimpleEngine.Input.MultiTouchHandler;
import com.gamedev.sjm.glesmvpdemo.SimpleEngine.SimpleGameEnginer;
import com.gamedev.sjm.glesmvpdemo.SimpleEngine.Time.Time;
import com.gamedev.sjm.glesmvpdemo.SimpleEngine.Util.MathUtil.Vector2;
import com.gamedev.sjm.glesmvpdemo.SimpleEngine.Util.MathUtil.Vector3;
import com.gamedev.sjm.glesmvpdemo.SpaceShipGame.GameObject.Bullet;

import java.util.ArrayList;
import java.util.List;

/**
 * 控制人物移动的脚本
 */
public class OperationBehavior extends Behavior {

    public float moveSpeed = 1.0f;

    public Vector3 left = new Vector3(90,-50,0);
    public Vector3 right = new Vector3(90,50,0);

    List<Bullet> bullets = new ArrayList<>();

    public OperationBehavior(){

        for(int i=0;i<20;i++){
            Bullet bullet = new Bullet();
            bullets.add(bullet);
            SimpleGameEnginer.main.gameObjects.add(bullet);
        }
    }

    public void OnClick(int pointer){
        // 找到还未被使用的子弹
        Bullet bullet = null;
        for(Bullet b : bullets){
            if(!b.isUsed){
                b.isUsed = true;
                bullet = b;
                break;
            }
        }
        if(bullet==null) return;
        // 将该子弹的初始位置设置为飞船正上方
        bullet.transform.pos = new Vector3(
                gameObject.transform.pos.x,
                gameObject.transform.pos.y+0.2f,
                gameObject.transform.pos.z);
    }

    @Override
    public void OnUpdate() {
        super.OnUpdate();
        if(!MultiTouchHandler.GetMain().isTouchMoved() && MultiTouchHandler.GetMain().isTouchDown(0)){
            float x = MultiTouchHandler.GetMain().getTouchX(0);
            float y = MultiTouchHandler.GetMain().getTouchY(0);
            OnClick(0);
        }
        if(!MultiTouchHandler.GetMain().isTouchMoved() && MultiTouchHandler.GetMain().isTouchDown(1)){
            OnClick(1);
        }

        if(MultiTouchHandler.GetMain().isTouchMoved()){
            Vector2 moveScale = MultiTouchHandler.GetMain().getMoveScale(0);
            gameObject.transform.pos = gameObject.transform.pos.Add(new Vector3(moveSpeed*moveScale.u*Time.deltaTime,moveSpeed*Time.deltaTime*moveScale.v,0));
        }
    }
}
