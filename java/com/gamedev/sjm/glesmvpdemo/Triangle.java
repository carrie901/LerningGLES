package com.gamedev.sjm.glesmvpdemo;

import android.opengl.GLES30;

import com.gamedev.sjm.glesmvpdemo.SimpleEngine.Util.MathUtil.Vector3;

public class Triangle {
    // 三角形的三个顶点
    private Vector3 v1,v2,v3;

    int mProgram;

    public Triangle(){
        v1 = new Vector3(0,0,0);
        v2 = new Vector3(1,1,0);
        v3 = new Vector3(2,0,0);
    }

    public void Draw(){
        // 指定使用Shader程序
        GLES30.glUseProgram(mProgram);

    }
}
