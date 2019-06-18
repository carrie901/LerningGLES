package com.gamedev.sjm.glesmvpdemo.SimpleEngine.components;

import com.gamedev.sjm.glesmvpdemo.SimpleEngine.Util.MatrixUtil.MatrixUtil;
import com.gamedev.sjm.glesmvpdemo.Vector3;

import java.util.ArrayList;
import java.util.List;

/**
 * 变换组件,用于设定物体的世界坐标,旋转角度,缩放程度
 */
public class Transform extends Component{

    public final static Transform Default = new Transform();

    public Vector3 pos = Vector3.Zero;
    public Vector3 rotation = Vector3.Zero;
    public Vector3 scale = Vector3.One;

    public Transform parent;
    private List<Transform> childs;

    public float[] getModelMatrix(){
        return MatrixUtil.GetModelMatrix(
                pos.x,pos.y,pos.z,
                rotation.x,rotation.y,rotation.z,
                scale.x,scale.y,scale.z
        );
    }

    public void SetParent(Transform transform){
        this.parent = transform;
    }

    public void AddChild(Transform transform){
        if(childs==null) childs = new ArrayList<>();
        childs.add(transform);
    }
    public Transform GetChild(int index){
        if(index>=childs.size()) return null;
        return childs.get(index);
    }
}
