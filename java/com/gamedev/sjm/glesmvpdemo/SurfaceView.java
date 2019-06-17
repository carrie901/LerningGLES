package com.gamedev.sjm.glesmvpdemo;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;

public class SurfaceView extends GLSurfaceView{

    public SurfaceView(Context context) {
        super(context);
        setEGLContextClientVersion(3);
        setRenderer(new SimpleMVPRender(this));
        setRenderMode(GLSurfaceView.RENDERMODE_CONTINUOUSLY);
    }
}
