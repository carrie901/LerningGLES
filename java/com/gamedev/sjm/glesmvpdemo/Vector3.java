package com.gamedev.sjm.glesmvpdemo;

public class Vector3 {
    public float x,y,z;

    public final static Vector3 Zero = new Vector3(0,0,0);

    public Vector3(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Vector3 Add(Vector3 another){
        Vector3 result = new Vector3(0,0,0);
        result.x = this.x + another.x;
        result.y = this.y + another.y;
        result.z = this.z + another.z;
        return result;
    }
}
