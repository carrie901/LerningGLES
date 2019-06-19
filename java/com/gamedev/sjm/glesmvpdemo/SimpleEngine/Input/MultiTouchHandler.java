package com.gamedev.sjm.glesmvpdemo.SimpleEngine.Input;

import android.view.MotionEvent;
import android.view.View;

import com.gamedev.sjm.glesmvpdemo.SimpleEngine.SimpleGameEnginer;
import com.gamedev.sjm.glesmvpdemo.SimpleEngine.Util.MathUtil.Vector2;
import com.gamedev.sjm.glesmvpdemo.SimpleEngine.Util.MathUtil.Vector3;

public class MultiTouchHandler implements Input,View.OnTouchListener {

    private static MultiTouchHandler main;
    public static MultiTouchHandler GetMain(){
        if(main==null) main = new MultiTouchHandler();
        return main;
    }

    // 最多允许有多少手指触碰屏幕
    private static final int MAX_TOUCHPOINTS = 2;
    boolean[] isTouched = new boolean[MAX_TOUCHPOINTS];
    boolean[] isTouchMove = new boolean[MAX_TOUCHPOINTS];
    float[] touchX = new float[MAX_TOUCHPOINTS];
    float[] touchY = new float[MAX_TOUCHPOINTS];
    int[] id = new int[MAX_TOUCHPOINTS];

    // 移动刻度
    private Vector2[] moveScale = new Vector2[MAX_TOUCHPOINTS];

    public MultiTouchHandler(){for(int i=0;i<MAX_TOUCHPOINTS;i++)moveScale[i]=Vector2.Zero;}

    @Override
    public boolean isTouchDown(int pointer) {
        synchronized (this){
            int index = getIndex(pointer);
            if(index<0 || index>=MAX_TOUCHPOINTS)
                return false;
            else
                return isTouched[index];
        }
    }

    @Override
    public boolean isTouchDown() {
        for(int i=0;i<MAX_TOUCHPOINTS;i++){
            if(isTouched[i])
                return true;
        }
        return false;
    }

    @Override
    public boolean isTouchMoved() {
        for(int i=0;i<MAX_TOUCHPOINTS;i++){
            if(isTouchMove[i])
                return true;
        }
        return false;
    }

    @Override
    public float getTouchX(int pointer) {
        synchronized (this){
            int index = getIndex(pointer);
            if(index<0 || index>=MAX_TOUCHPOINTS)
                return 0;
            else
                return touchX[index];
        }
    }

    @Override
    public float getTouchY(int pointer) {
        synchronized (this){
            int index = getIndex(pointer);
            if(index<0 || index>=MAX_TOUCHPOINTS)
                return 0;
            else
                return touchY[index];
        }
    }

    @Override
    public Vector2 getMoveScale(int pointer) {
        synchronized (this){
            int index = getIndex(pointer);
            if(index<0 || index>=MAX_TOUCHPOINTS)
                return Vector2.Zero;
            else
                return moveScale[index];
        }
    }

    /**
     * 根据鼠标指针ID获得该鼠标的下标
     * @param pointerID
     * @return
     */
    private int getIndex(int pointerID){
        for(int i=0;i<MAX_TOUCHPOINTS;i++){
            if(id[i]==pointerID)
                return i;
        }
        return -1;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {

        synchronized (this){
            int action = event.getAction() & MotionEvent.ACTION_MASK;
            int pointerIndex = (event.getAction() & MotionEvent.ACTION_POINTER_ID_MASK )
                    >> MotionEvent.ACTION_POINTER_ID_SHIFT;
            int pointerCount = event.getPointerCount();
            for(int i=0;i<MAX_TOUCHPOINTS;i++){
                if(i>=pointerCount) {
                    isTouched[i] = false;
                    id[i] = -1;
                    isTouchMove[i] = false;
                    moveScale[i] = Vector2.Zero;
                    continue;
                }
                int pointerID = event.getPointerId(i);
                if(i!=pointerIndex) continue;
                switch (action){
                    case MotionEvent.ACTION_DOWN:
                    case MotionEvent.ACTION_POINTER_DOWN:
                        isTouched[i] = true;
                        id[i] = pointerID;
                        touchX[i] = event.getX(i);
                        touchY[i] = event.getY(i);
                        moveScale[i] = Vector2.Zero;
                        isTouchMove[i] = false;

                        break;
                    case MotionEvent.ACTION_UP:
                    case MotionEvent.ACTION_POINTER_UP:
                    case MotionEvent.ACTION_CANCEL:
                        isTouched[i] = false;
                        id[i] = -1;
                        touchX[i] = event.getX(i);
                        touchY[i] = event.getY(i);
                        moveScale[i] = Vector2.Zero;
                        isTouchMove[i] = false;

                        break;
                    case MotionEvent.ACTION_MOVE:
                        isTouched[i] = true;
                        id[i] = pointerID;
                        float x2 = event.getX(i);
                        float y2 = event.getY(i);

                        float xScale = (x2-touchX[i])/SimpleGameEnginer.screenWidth;
                        float yScale = (y2-touchY[i])/SimpleGameEnginer.screenHeight;

                        moveScale[i] = new Vector2(xScale,yScale);
                        isTouchMove[i] = true;
                        break;
                }
            }
        }

        return true;
    }
}
