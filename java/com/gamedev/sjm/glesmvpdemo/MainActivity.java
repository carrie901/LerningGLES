package com.gamedev.sjm.glesmvpdemo;

import android.opengl.GLES20;
import android.opengl.GLES30;
import android.opengl.GLSurfaceView;
import android.opengl.Matrix;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.gamedev.sjm.glesmvpdemo.ShaderUtil.ShaderUtil;

public class MainActivity extends AppCompatActivity {

    GLSurfaceView surfaceView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        surfaceView = new SurfaceView(this);
        setContentView(surfaceView);
    }
}

