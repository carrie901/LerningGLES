package com.gamedev.sjm.glesmvpdemo.MeshObject;

import com.gamedev.sjm.glesmvpdemo.SimpleEngine.components.Mesh;

/**
 * 四边形
 */
public class Quad extends Mesh {
    public Quad(){
        // 顶点位置
        float[] vertics = new float[]{
                1f, 1f, 0.0f,       // 右上角
                1f, 0f, 0.0f,      // 右下角
                0f, 0, 0.0f,      // 左下角
                0,  1f,  0f           // 左上角
        };
        // 顶点色
        float[] colors = new float[]{
                1,0,0,1,
                0,1,0,1,
                0,0,1,1,
                1,0,1,1
        };
        // 纹理坐标
        float[] texcoords = new float[]{
                1.0f,1.0f,
                1.0f,0.0f,
                0,0,
                0,1.0f
        };
        // 顶点索引数组
        int[] triangleIndex = new int[]{
                2,3,1,
                3,0,1
        };

        setVertics(vertics);
        setTriangles(triangleIndex);
        setUv(texcoords);
        setColors(colors);
    }
}
