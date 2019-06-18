package com.gamedev.sjm.glesmvpdemo.SimpleEngine.Util.ShaderUtil;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.opengl.GLES30;
import android.opengl.GLUtils;

import com.gamedev.sjm.glesmvpdemo.SimpleEngine.components.Mesh;
import com.gamedev.sjm.glesmvpdemo.SimpleEngine.Util.TextureUtil.TextureFilteringMode;
import com.gamedev.sjm.glesmvpdemo.SimpleEngine.Util.TextureUtil.TextureSamplingMode;

import java.util.HashMap;
import java.util.Map;

public class Shader {

    // 用于保存着色器中申请的纹理ID，以及已经使用过的纹理单元（texture1/2/3这些）
    // 其中纹理ID与纹理单元一一对应
    private Map<Integer,Integer> textureMap;

    // 着色器对象ID
    private int ID;

    // 各定点属性在该着色器的ID ( -1表示不启用该属性)
    private int vertexPosHandle = -1;   // 顶点位置属性ID
    private int colorHandle = -1;       // 顶点色属性ID
    private int texcoordHandle = -1;    // 顶点uv属性ID
    private int trianglesHandle = -1;   // 顶点索引属性ID

    public int getID() {
        return ID;
    }

    public Shader(int shaderID){
        this.ID = shaderID;
    }

    public static Shader CreateShader(String vertexPath, String fragPath, Resources resources){
        String vertexSource = ShaderUtil.loadFromAssetsFile(vertexPath,resources);
        String fragSource = ShaderUtil.loadFromAssetsFile(fragPath,resources);

        return Shader.CreateShader(vertexSource,fragSource);
    }

    public static Shader CreateShader(String vertexSource,String fragmentSource){
        int id = ShaderUtil.CreateProgram(vertexSource,fragmentSource);
        return new Shader(id);
    }

    public Shader(String vertexSource,String fragmentSource){
        this.ID = ShaderUtil.CreateProgram(vertexSource,fragmentSource);
    }

    /**
     * 使用该着色器程序进行渲染
     */
    public void Use(){
        GLES30.glUseProgram(ID);
    }

    /**
     * 设置着色器Uniform属性
     * @param name
     * @param value
     */
    public void SetBool(String name,boolean value){
        // 非0为真,0为假
        GLES30.glUniform1i(GLES30.glGetUniformLocation(ID,name),value?1:0);
    }
    public void SetInt(String name,int value){
        GLES30.glUniform1i(GLES30.glGetUniformLocation(ID,name),value);
    }
    public void SetFloat(String name,float value){
        GLES30.glUniform1f(GLES30.glGetUniformLocation(ID,name),value);
    }
    public void SetFloat3(String name,float x,float y,float z){
        int handle = GLES30.glGetUniformLocation(ID,name);
        GLES30.glUniform3f(handle,x,y,z);
    }
    public void SetFloat4(String name,float x,float y,float z,float w){
        int handle = GLES30.glGetUniformLocation(ID,name);
        GLES30.glUniform4f(handle,x,y,z,w);
    }

    public void SetMatrix4x4(String name,float[] matrix){
        int handler = GLES30.glGetUniformLocation(ID,name);
        GLES30.glUniformMatrix4fv(handler,1,false,matrix,0);
    }

    /**
     * 为Shader设置2D纹理
     * @param paramName 该纹理采样器在Shader中的名字
     * @param texture   目标纹理的Bitmap形式
     * @param textureUnit 纹理单元,有texture1/texture2/texture3等形式
     * @param samplingMode 纹理采样模式
     * @param filteringMode 纹理过滤模式
     */
    public void SetTexture2D(
            String paramName,
            Bitmap texture,
            int textureUnit,
            TextureSamplingMode samplingMode,
            TextureFilteringMode filteringMode){
        // 申请纹理ID
        int[] tempID = new int[1];
        int textureID;
        GLES30.glGenBuffers(1,tempID,0);
        textureID = tempID[0];

        // 激活纹理单元
        GLES30.glActiveTexture(GLES30.GL_TEXTURE0+textureUnit);
        // 绑定纹理
        GLES30.glBindTexture(GLES30.GL_TEXTURE_2D,textureID);

        // 纹理ID与纹理单元一一对应
        if(textureMap==null) textureMap = new HashMap<>();
        textureMap.put(textureID,textureUnit);

        // 设置纹理采样模式
        switch (samplingMode){
            case Clamp:
                GLES30.glTexParameteri(GLES30.GL_TEXTURE_2D,GLES30.GL_TEXTURE_WRAP_S,GLES30.GL_CLAMP_TO_EDGE);
                GLES30.glTexParameteri(GLES30.GL_TEXTURE_2D,GLES30.GL_TEXTURE_WRAP_T,GLES30.GL_CLAMP_TO_EDGE);
                break;
            case Repeat:
                GLES30.glTexParameteri(GLES30.GL_TEXTURE_2D,GLES30.GL_TEXTURE_WRAP_S,GLES30.GL_MIRRORED_REPEAT);
                GLES30.glTexParameteri(GLES30.GL_TEXTURE_2D,GLES30.GL_TEXTURE_WRAP_T,GLES30.GL_MIRRORED_REPEAT);
                break;
        }

        // 设置纹理过滤模式
        switch (filteringMode){
            case Point:
                GLES30.glTexParameteri(GLES30.GL_TEXTURE_2D,GLES30.GL_TEXTURE_MIN_FILTER, GLES30.GL_NEAREST);
                GLES30.glTexParameteri(GLES30.GL_TEXTURE_2D,GLES30.GL_TEXTURE_MAG_FILTER, GLES30.GL_NEAREST);
                break;
            case Bilinear:
                GLES30.glTexParameteri(GLES30.GL_TEXTURE_2D,GLES30.GL_TEXTURE_MIN_FILTER, GLES30.GL_LINEAR);
                GLES30.glTexParameteri(GLES30.GL_TEXTURE_2D,GLES30.GL_TEXTURE_MAG_FILTER, GLES30.GL_LINEAR);
                break;
            case Trilinear:

                break;
        }

        // 将该Bitmap类型的纹理输入到绑定的OpenGL纹理对象中去
        GLUtils.texImage2D(GLES30.GL_TEXTURE_2D,0,texture,0);
        // 用完回收纹理
        texture.recycle();

        // TODO~ 待优化
        // 设置纹理单元前要激活/使用该着色器对象(待优化)
        Use();

        // 设置纹理单元(表示)
        SetInt(paramName,textureUnit);

    }

    /**
     * 激活并绑定纹理，当使用设置有纹理的Shader时，必须在绘制前激活并绑定所有纹理
     */
    public void ActiveAndBindTexture(){
        if(textureMap!=null){
            for(int textureID : textureMap.keySet()){
                int textureUnit = textureMap.get(textureID);
                GLES30.glActiveTexture(GLES30.GL_TEXTURE0+textureUnit);
                GLES30.glBindTexture(GLES30.GL_TEXTURE_2D,textureID);
            }
        }
    }

    /**
     * 根据一个Mesh对象,设置该着色器的各顶点属性(顶点位置,顶点色,uv等)
     * @param mesh
     */
    public void BindVertexAttribute(Mesh mesh){
        // 根据顶点索引数组申请OpenGL索引对象ID
        // 申请OpenGL顶点索引对象
        int[] id = new int[1];
        GLES30.glGenBuffers(
                1,  // 申请数量
                id,    // 保存申请到的对象的id
                0 // 保存在数组中的偏移
        );
        trianglesHandle = id[0];
        // 绑定顶点索引对象
        GLES30.glBindBuffer(GLES30.GL_ELEMENT_ARRAY_BUFFER,trianglesHandle);
        // 将顶点索引数组的值输入到申请的顶点索引对象中去
        GLES30.glBufferData(
                GLES30.GL_ELEMENT_ARRAY_BUFFER,
                mesh.getTriangles().length*4,
                mesh.getTrianglesBuffers(),
                GLES30.GL_STATIC_DRAW
        );

        // 绑定顶点位置属性

        // 获得着色器中顶点位置和颜色属性的句柄
        vertexPosHandle = GLES30.glGetAttribLocation(this.ID,mesh.vertexPosName);
        // 设置顶点属性
        GLES30.glVertexAttribPointer(
                vertexPosHandle,
                3,      // 每个顶点的大小(这里因为顶点是(x,y,z),所以是3)
                GLES30.GL_FLOAT,
                false,
                3*4,    // 每次向右移动 3*sizeof(float)个单位
                mesh.getVerticsBuffers()
        );

        if(mesh.getUvBuffers()!=null) {
            texcoordHandle = GLES30.glGetAttribLocation(this.ID, mesh.uvName);
            GLES30.glVertexAttribPointer(
                    texcoordHandle,
                    2,
                    GLES30.GL_FLOAT,
                    false,
                    2 * 4,
                    mesh.getUvBuffers()
            );
        }

        if(mesh.getColorBuffers()!=null){
            colorHandle = GLES30.glGetAttribLocation(this.ID,mesh.vertexColorName);
            GLES30.glVertexAttribPointer(
                    colorHandle,
                    4,      // 每个顶点的大小(这里因为顶点是(x,y,z),所以是3)
                    GLES30.GL_FLOAT,
                    false,
                    4*4,    // 每次向右移动 4*sizeof(float)个单位
                    mesh.getColorBuffers()
            );
        }
    }

    /**
     * 自动启用已设置的顶点属性
     */
    public void EnableVertexAttribute(){
        if(vertexPosHandle!=-1){
            GLES30.glEnableVertexAttribArray(vertexPosHandle);
        }
        if(texcoordHandle!=-1){
            GLES30.glEnableVertexAttribArray(texcoordHandle);
        }
        if(colorHandle!=-1){
            GLES30.glEnableVertexAttribArray(colorHandle);
        }
        if(trianglesHandle!=-1){
            GLES30.glBindBuffer(GLES30.GL_ELEMENT_ARRAY_BUFFER,trianglesHandle);
        }
    }
}
