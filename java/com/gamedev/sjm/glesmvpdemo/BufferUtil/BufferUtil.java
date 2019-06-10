package com.gamedev.sjm.glesmvpdemo.BufferUtil;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;

public class BufferUtil {
    /**
     * 根据一个属性数组和属性大小获得该属性的二进制缓冲
     * @param array
     * @param size 表示数组中每个数据所占用的内存
     * @return
     */
    public static FloatBuffer GetFloatBuffer(float[] array, int size){
        ByteBuffer buffer = ByteBuffer.allocateDirect(array.length*size);
        buffer.order(ByteOrder.nativeOrder());

        FloatBuffer result  = buffer.asFloatBuffer();

        result.put(array);
        result.position(0);

        return result;
    }

    public static IntBuffer GetIntBuffer(int[] array, int size){
        ByteBuffer buffer = ByteBuffer.allocateDirect(array.length*size);
        buffer.order(ByteOrder.nativeOrder());

        IntBuffer result  = buffer.asIntBuffer();

        result.put(array);
        result.position(0);

        return result;
    }
}
