package com.gamedev.sjm.glesmvpdemo.SimpleEngine.components;

import com.gamedev.sjm.glesmvpdemo.SimpleEngine.Util.BufferUtil.BufferUtil;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;

public class Mesh extends Component{
    // 顶点坐标
    private float[] vertics;
    private FloatBuffer verticsBuffers;
    public String vertexPosName = "vertex";  // 顶点位置属性在Shader中的默认名
    // 顶点uv坐标
    private float[] uv;
    private FloatBuffer uvBuffers;
    public String uvName = "texcoord";  // 顶点uv在Shader中的默认名
    // 顶点索引
    private int[] triangles;
    private IntBuffer trianglesBuffers;
    // 顶点色
    private float[] colors;
    private FloatBuffer colorBuffers;
    public String vertexColorName = "vertexColor";  // 顶点色在Shader中的默认名
    // 法线
    private float[] normals;
    private FloatBuffer normalBuffers;
    public String vertexNormalName = "normal";

    public float[] getVertics() {
        return vertics;
    }

    public float[] getUv() {
        return uv;
    }

    public int[] getTriangles() {
        return triangles;
    }

    public float[] getColors() {
        return colors;
    }

    public float[] getNormals() {
        return normals;
    }

    public void setVertics(float[] vertics) {
        this.vertics = vertics;
        verticsBuffers = BufferUtil.GetFloatBuffer(vertics,4);
    }
    public FloatBuffer getVerticsBuffers(){
        return verticsBuffers;
    }

    public void setUv(float[] uv) {
        this.uv = uv;
        uvBuffers = BufferUtil.GetFloatBuffer(uv,4);
    }
    public FloatBuffer getUvBuffers() {
        return uvBuffers;
    }

    public void setTriangles(int[] triangles) {
        this.triangles = triangles;
        trianglesBuffers = BufferUtil.GetIntBuffer(triangles,4);
    }
    public IntBuffer getTrianglesBuffers() {
        return trianglesBuffers;
    }

    public void setColors(float[] colors) {
        this.colors = colors;
        colorBuffers = BufferUtil.GetFloatBuffer(colors,4);
    }
    public FloatBuffer getColorBuffers() {
        return colorBuffers;
    }

    public void setNormals(float[] normals) {
        this.normals = normals;
        normalBuffers = BufferUtil.GetFloatBuffer(normals,4);
    }
    public FloatBuffer getNormalBuffers() {
        return normalBuffers;
    }
}
