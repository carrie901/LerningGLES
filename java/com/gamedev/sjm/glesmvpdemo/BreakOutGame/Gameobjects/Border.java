package com.gamedev.sjm.glesmvpdemo.BreakOutGame.Gameobjects;

import com.gamedev.sjm.glesmvpdemo.BreakOutGame.Behaviors.PlayerBehavior;
import com.gamedev.sjm.glesmvpdemo.SimpleEngine.Collider.BoxCollider;
import com.gamedev.sjm.glesmvpdemo.SimpleEngine.Collider.Collider;
import com.gamedev.sjm.glesmvpdemo.SimpleEngine.GameObject;
import com.gamedev.sjm.glesmvpdemo.SimpleEngine.Util.MathUtil.Vector3;
import com.gamedev.sjm.glesmvpdemo.SpaceShipGame.GameTags.GameTags;

public class Border extends GameObject {
    private boolean isTop;
    public Border(boolean isTop){
        this.isTop = isTop;
        BoxCollider boxCollider = new BoxCollider();
        if(isTop) {
            boxCollider.length = 1.9f;
            boxCollider.width = 0.1f;
            boxCollider.height = 0.08f;
        }else {
            boxCollider.length = 0.05f;
            boxCollider.width = 0.1f;
            boxCollider.height = 2.3f;
        }
        AddComponent("Collider",boxCollider);
    }

    @Override
    public void OnCollisionEnter(Collider collider) {
        super.OnCollisionEnter(collider);
        if(collider.getGameObject().tag == GameTags.PLAYER){
            // 让玩家的y分量反向,同时让玩家的x分量随机变化
            PlayerBehavior behavior = collider.getGameObject().GetComponent("PlayerBehavior");
            if(isTop){
                // y分量反向
                behavior.direction.y = -behavior.direction.y;
            }else {
                // x分量反向
                behavior.direction.x = -behavior.direction.x;
            }
        }
    }
}
