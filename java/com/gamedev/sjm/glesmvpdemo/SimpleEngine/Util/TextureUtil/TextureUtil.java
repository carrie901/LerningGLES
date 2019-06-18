package com.gamedev.sjm.glesmvpdemo.SimpleEngine.Util.TextureUtil;

import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

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

    /**
     * 从资源文件夹中读取纹理图片
     * @param texturePath
     * @param context
     * @return
     * @throws IOException
     */
    public static Bitmap LoadBitmap(String texturePath, Context context) throws IOException {
        AssetManager assetManager = context.getAssets();
        InputStream is = assetManager.open(texturePath);
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
