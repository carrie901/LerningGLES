package com.gamedev.sjm.glesmvpdemo.SimpleEngine.Input;

import com.gamedev.sjm.glesmvpdemo.SimpleEngine.Util.MathUtil.Vector2;

public interface Input {
    boolean isTouchDown(int pointer);
    boolean isTouchDown();
    boolean isTouchMoved();
    float getTouchX(int pointer);
    float getTouchY(int pointer);
    Vector2 getMoveScale(int pointer);
}
