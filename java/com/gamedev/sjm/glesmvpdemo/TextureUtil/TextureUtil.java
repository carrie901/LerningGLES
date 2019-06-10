package com.gamedev.sjm.glesmvpdemo.TextureUtil;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.opengl.GLES30;
import android.opengl.GLUtils;

import com.gamedev.sjm.glesmvpdemo.R;

import java.io.IOException;
import java.io.InputStream;

public class TextureUtil {
    public static Bitmap LoadBitmap(int bitmapID, Resources resources){
        InputStream is = resources.openRawResource(bitmapID);
        Bitmap bitmap;
        try {
            bitmap = BitmapFactory.decodeStream(is);
        }finally {
            try {
                is.close();
            }catch (IOException e){
                e.printStackTrace();
            }
        }
        return bitmap;
    }
}
