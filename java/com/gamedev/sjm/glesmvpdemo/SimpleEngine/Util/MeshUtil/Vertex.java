package com.gamedev.sjm.glesmvpdemo.SimpleEngine.Util.MeshUtil;

import com.gamedev.sjm.glesmvpdemo.SimpleEngine.Util.MathUtil.Vector2;
import com.gamedev.sjm.glesmvpdemo.SimpleEngine.Util.MathUtil.Vector3;

public class Vertex {
    public Vector3 position;
    public Vector3 normal;
    public Vector2 texcoord;

    public Vertex(Vector3 position, Vector3 normal, Vector2 texcoord) {
        this.position = position;
        this.normal = normal;
        this.texcoord = texcoord;
    }
}
