package com.gamedev.sjm.glesmvpdemo.SimpleEngine.Collider;

import android.opengl.GLES30;

import com.gamedev.sjm.glesmvpdemo.MeshObject.Cube;
import com.gamedev.sjm.glesmvpdemo.SimpleEngine.Graphics.Graphics;
import com.gamedev.sjm.glesmvpdemo.SimpleEngine.Util.MathUtil.Vector3;
import com.gamedev.sjm.glesmvpdemo.SimpleEngine.Util.MeshUtil.OBJLoader;
import com.gamedev.sjm.glesmvpdemo.SimpleEngine.Util.ShaderUtil.Shader;
import com.gamedev.sjm.glesmvpdemo.SimpleEngine.components.Mesh;
import com.gamedev.sjm.glesmvpdemo.SurfaceView;

public class BoxCollider extends Collider{
    // 长、宽、高，对应scale中的 (x,z,y)
    public float length,width,height;

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
    private Shader shader;

    @Override
    public void OnStart() {
        super.OnStart();
        try {
            // 初始化碰撞体Mesh
            mesh = OBJLoader.ParseOBJ("obj/cube.obj", SurfaceView.DrawingView.getResources());
            shader = Shader.CreateShader(
                    "shaders/defaultShader/vertex.glsl",
                    "shaders/defaultShader/frag.glsl",
                    SurfaceView.DrawingView.getResources());
        }catch (Exception e){e.printStackTrace();}
        // 碰撞盒中心
        Vector3 center = gameObject.transform.pos;
        // 默认碰撞盒大小为gameObject的scale一致
        if(length==0 && width==0 && height==0) {
            length = gameObject.transform.scale.x;
            width = gameObject.transform.scale.z;
            height = gameObject.transform.scale.y;
        }

        // 碰撞盒各顶点坐标
        left = center.x - length*0.5f;
        right = center.x + length*0.5f;
        top = center.y + height*0.5f;
        bottom = center.y - height*0.5f;
        inner = center.z + width*0.5f;
        outer = center.z - width*0.5f;

//        Vector3 v1 = new Vector3(left,bottom,outer);    // 正面,左下角
//        Vector3 v2 = new Vector3(left,top,outer);       // 正面,左上角
//        Vector3 v3 = new Vector3(right,top,outer);      // 正面,右上角
//        Vector3 v4 = new Vector3(right,bottom,outer);   // 正面,右下角
//        Vector3 v5 = new Vector3(left,bottom,inner);    // 背面,左下角
//        Vector3 v6 = new Vector3(left,top,inner);       // 背面,左上角
//        Vector3 v7 = new Vector3(right,top,inner);      // 背面,右上角
//        Vector3 v8 = new Vector3(right,bottom,inner);   // 背面,右下角

//        System.out.println();
    }

    @Override
    public void OnUpdate() {
        // 碰撞盒中心
        Vector3 center = gameObject.transform.pos;
        // 默认碰撞盒大小为gameObject的scale一致
//        length = gameObject.transform.scale.x;
//        width = gameObject.transform.scale.z;
//        height = gameObject.transform.scale.y;

        // 碰撞盒各顶点坐标
        left = center.x - length;
        right = center.x + length;
        top = center.y + height;
        bottom = center.y - height;
        inner = center.z + width;
        outer = center.z - width;

        super.OnUpdate();
    }

    @Override
    public void OnCollisionEnter(Collider collider) {
        super.OnCollisionEnter(collider);
        System.out.println("发生碰撞");
        gameObject.OnCollisionEnter(collider);
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
                        top >= anotherCollider.getBottom()){

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

    @Override
    public void Render() {
        super.Render();
        Graphics.DrawMesh(
                mesh,
                shader,
                gameObject.transform.pos,
                gameObject.transform.rotation,
                new Vector3(length,height,width),
                GLES30.GL_LINE_LOOP);
    }
}
