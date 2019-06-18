package com.gamedev.sjm.glesmvpdemo.SimpleEngine;

import android.opengl.Matrix;

import com.gamedev.sjm.glesmvpdemo.SimpleEngine.Util.MatrixUtil.MatrixUtil;
import com.gamedev.sjm.glesmvpdemo.SimpleEngine.components.Component;
import com.gamedev.sjm.glesmvpdemo.SimpleEngine.components.Transform;
import com.gamedev.sjm.glesmvpdemo.Vector3;

public class Camera extends Component {

    // 主摄像机
    public static Camera main;

    public Transform transform = Transform.Default;
    // 默认观察方向为摄像机前方
    private Vector3 forwardDir = new Vector3(0,0,1);
    // 摄像机向上的向量,默认为竖直向上
    private Vector3 upDir;
    // 摄像机fov角度
    public float fovAngle;
    // 距离近平面距离
    public float near;
    // 距离远平面距离
    public float far;
    // 屏幕宽高比
    public float aspect;

    /**
     * 将当前摄像机设置为主摄像机
     */
    public void SetMain(){
        main = this;
    }

    /**
     *
     * @param pos 摄像机位置
     * @param rotation 摄像机旋转角度
     * @param up 摄像机向上的向量
     */
    public Camera(Vector3 pos,Vector3 rotation, Vector3 up,float fovAngle,float near,float far,float aspect){
        transform.pos = pos;
        transform.rotation = rotation;
        upDir = up;
        this.fovAngle = fovAngle;
        this.near = near;
        this.far = far;
        this.aspect = aspect;
    }

    /**
     * 获取观察矩阵
     * @return
     */
    public float[] GetViewMatrix(){
        float[] tempForwardDir = new float[]{forwardDir.x,forwardDir.y,forwardDir.z,0};
        float[] cameraRMatrix = MatrixUtil.GetRotationMatrix(transform.rotation.x,transform.rotation.y,transform.rotation.z);
        float[] forwardResultDir = new float[16];
        Matrix.multiplyMV(forwardResultDir,0,cameraRMatrix,0,tempForwardDir,0);
        Vector3 fDir = new Vector3(forwardResultDir[0],forwardResultDir[1],forwardResultDir[2]);

        Vector3 targetPos = transform.pos.Add(fDir);
        return MatrixUtil.GetViewMatrix(
                transform.pos,targetPos,upDir
        );
    }

    /**
     * 获取投影矩阵
     * @return
     */
    public float[] GetProjectMatrix(){
        return MatrixUtil.GetProjectFrustumMatrix(
                near,far,fovAngle,aspect
        );
    }

    public float[] GetVPMatrix(){
        float[] vMatrix = GetViewMatrix();
        float[] pMatrix = GetProjectMatrix();
        float[] resultMatrix = new float[16];
        Matrix.multiplyMM(resultMatrix,0,pMatrix,0,vMatrix,0);
        return resultMatrix;
    }
}
