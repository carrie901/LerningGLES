package com.gamedev.sjm.glesmvpdemo;

import android.opengl.GLSurfaceView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.gamedev.sjm.glesmvpdemo.SimpleEngine.Input.MultiTouchHandler;

public class MainActivity extends AppCompatActivity {

    GLSurfaceView surfaceView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        surfaceView = new SurfaceView(this);

        // 设置输入事件
        surfaceView.setOnTouchListener(MultiTouchHandler.GetMain());

        setContentView(surfaceView);
    }

}

