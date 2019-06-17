package com.gamedev.sjm.glesmvpdemo.SimpleEngine;

import com.gamedev.sjm.glesmvpdemo.MatrixUtil.MatrixUtil;
import com.gamedev.sjm.glesmvpdemo.Vector3;

/**
 * 变换组件,用于设定物体的世界坐标,旋转角度,缩放程度
 */
public class Transform {

    public final static Transform Default = new Transform();

    public Vector3 pos = Vector3.Zero;
    public Vector3 rotation = Vector3.Zero;
    public Vector3 scale = Vector3.Zero;

    public float[] getModelMatrix(){
        return MatrixUtil.GetModelMatrix(
                pos.x,pos.y,pos.z,
                rotation.x,rotation.y,rotation.z,
                scale.x,scale.y,scale.z
        );
    }
}
