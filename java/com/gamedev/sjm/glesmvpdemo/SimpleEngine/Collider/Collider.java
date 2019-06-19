package com.gamedev.sjm.glesmvpdemo.SimpleEngine.Collider;

import com.gamedev.sjm.glesmvpdemo.SimpleEngine.GameObject;
import com.gamedev.sjm.glesmvpdemo.SimpleEngine.InterFace.Renderable;
import com.gamedev.sjm.glesmvpdemo.SimpleEngine.SimpleGameEnginer;
import com.gamedev.sjm.glesmvpdemo.SimpleEngine.components.Component;

import java.util.List;

public class Collider extends Component implements Renderable {

    /**
     * 虚方法，跟另外一个碰撞体发生碰撞时要处理的游戏逻辑
     * @param collider
     */
    public void OnCollisionEnter(Collider collider){}

    /**
     * 虚方法，判断当前碰撞体是否和另外一个碰撞体发生碰撞
     * @param collider
     * @return
     */
    public boolean IsCollide(Collider collider){ return false; }

    /**
     * 和场景中每一个物体进行碰撞检测，如果发生碰撞，
     * 那么使用OnCollisionEnter处理碰撞发生时的游戏逻辑
     */
    @Override
    public void OnUpdate() {
        super.OnUpdate();
        for(GameObject object : SimpleGameEnginer.main.gameObjects){
            if(object==gameObject) continue;
            Collider collider = object.GetComponent("Collider");
            if(collider!=null){
                // 进行碰撞检测
                if(this.IsCollide(collider)){
                    // 发生碰撞事件
                    OnCollisionEnter(collider);
                }
            }
        }
    }

    /**
     * 用于绘制该碰撞盒的检测范围
     */
    @Override
    public void Render() {

    }
}
