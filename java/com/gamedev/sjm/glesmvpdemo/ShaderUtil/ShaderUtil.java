package com.gamedev.sjm.glesmvpdemo.ShaderUtil;

import android.content.res.Resources;
import android.opengl.GLES30;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

/**
 * 用于加载Shader的辅助类
 */
public class ShaderUtil {

    public static Shader CreateShaderProgram(String vertexSource,String fragmentSource){
        return new Shader(CreateProgram(vertexSource,fragmentSource));
    }

    /**
     * 根据顶点着色器和片元着色器的源码创建着色器对象(经过连接后的),
     * 返回该着色器对象的ID
     * @param vertexSource
     * @param fragmentSource
     * @return
     */
    public static int CreateProgram(String vertexSource,String fragmentSource){
        int vertexShader = LoadShader(GLES30.GL_VERTEX_SHADER,vertexSource);
        int fragmentShader = LoadShader(GLES30.GL_FRAGMENT_SHADER,fragmentSource);
        if(vertexShader==0 || fragmentShader==0) return 0;

        // 创建着色器对象
        int program = GLES30.glCreateProgram();
        if(program!=0){
            GLES30.glAttachShader(program,vertexShader);
            CheckGLError("GL Attach Vertex Shader");
            GLES30.glAttachShader(program,fragmentShader);
            CheckGLError("GL Attach Fragment Shader");

            // 链接顶点和片元着色器
            GLES30.glLinkProgram(program);

            // 获得链接状态已经错误提示
            int[] linkStatus = new int[1];
            GLES30.glGetProgramiv(program,GLES30.GL_LINK_STATUS,linkStatus,0);
            if(linkStatus[0]!=GLES30.GL_TRUE){
                Log.e("ES20_ERROR", "Could not link program: ");
                Log.e("ES20_ERROR", GLES30.glGetProgramInfoLog(program));
                GLES30.glDeleteProgram(program);
                program = 0;
            }
        }
        return program;
    }

    public static void CheckGLError(String op){
        int error;
        while ((error = GLES30.glGetError()) != GLES30.GL_NO_ERROR) {
            Log.e("ES20_ERROR", op + ": glError " + error);
            throw new RuntimeException(op + ": glError " + error);
        }
    }

    /**
     * 根据着色器内容和类型创建着色器程序，
     * 函数返回该着色器程序的ID
     * @param shaderType
     * @param shaderContent
     * @return
     */
    public static int LoadShader(int shaderType, String shaderContent){
        int shaderProgram = GLES30.glCreateShader(shaderType);
        if(shaderProgram!=0){
            // 将着色器源码附加到着色器对象上
            GLES30.glShaderSource(shaderProgram,shaderContent);
            // 编译Shader
            GLES30.glCompileShader(shaderProgram);

            // 存储编译结果的指针
            int[] compiled = new int[1];
            GLES30.glGetShaderiv(shaderProgram,GLES30.GL_COMPILE_STATUS,compiled,0);
            if(compiled[0]==0){
                // 编译失败
                Log.e("ES20_ERROR","Could not compile shader " + shaderType + ":");
                Log.e("ES20_ERROR", GLES30.glGetShaderInfoLog(shaderProgram));
                // 删除该着色器对象
                GLES30.glDeleteShader(shaderProgram);
                shaderProgram = 0;
            }
        }

        return shaderProgram;
    }

    /**
     * 从Assets文件夹中读取文件
     * @param fileName
     * @param resources
     * @return
     */
    public static String loadFromAssetsFile(String fileName,Resources resources){
        InputStream inputStream = null;
        String result = null;
        try {
            inputStream = resources.getAssets().open(fileName);
            byte[] bytes = new byte[inputStream.available()];
            inputStream.read(bytes);
            result = new String(bytes,"UTF-8");
            result=result.replaceAll("\\r\\n","\n");
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            try {
                inputStream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return result;
    }
}
