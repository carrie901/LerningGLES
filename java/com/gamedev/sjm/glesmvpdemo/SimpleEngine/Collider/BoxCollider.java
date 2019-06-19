package com.gamedev.sjm.glesmvpdemo.SimpleEngine.Collider;

import com.gamedev.sjm.glesmvpdemo.MeshObject.Cube;
import com.gamedev.sjm.glesmvpdemo.SimpleEngine.Util.MathUtil.Vector3;
import com.gamedev.sjm.glesmvpdemo.SimpleEngine.components.Mesh;

public class BoxCollider extends Collider{
    // 长、宽、高，对应scale中的 (x,z,y)
    private float length,width,height;

    private float left,top,right,bottom,inner,outer;

    public float getLeft() {
        return left;
    }

    public float getTop() {
        return top;
    }

    public float getRight() {
        return right;
    }

    public float getBottom() {
        return bottom;
    }

    public float getInner() {
        return inner;
    }

    public float getOuter() {
        return outer;
    }

    // 用于渲染碰撞盒检测范围
    private Mesh mesh;

    public BoxCollider(){
        // 初始化碰撞体Mesh
        mesh = new Cube();

        // 碰撞盒中心
        Vector3 center = gameObject.transform.pos;
        // 默认碰撞盒大小为gameObject的scale一致
        length = gameObject.transform.scale.x;
        width = gameObject.transform.scale.z;
        height = gameObject.transform.scale.y;

        // 碰撞盒各顶点坐标
        left = center.x - length*0.5f;
        right = center.x + length*0.5f;
        top = center.y + height*0.5f;
        bottom = center.y - height*0.5f;
        inner = center.z + width*0.5f;
        outer = center.z - width*0.5f;
    }

    @Override
    public boolean IsCollide(Collider collider) {
        // 两个长方形碰撞盒之间的碰撞
        if(collider instanceof BoxCollider){
            BoxCollider anotherCollider = (BoxCollider)collider;

            // 比较水平方向轴(x轴)
            if(right >= anotherCollider.getLeft() &&
                left <= anotherCollider.getRight()){
                // 比较竖直方向轴(y轴)
                if(bottom <= anotherCollider.getTop() &&
                        top >= anotherCollider.bottom){

                    // 比较z轴
                    if(inner >= anotherCollider.getOuter()&&
                            outer <= anotherCollider.getInner()){

                        return true;

                    }

                }

            }

        }
        return false;
    }
}
