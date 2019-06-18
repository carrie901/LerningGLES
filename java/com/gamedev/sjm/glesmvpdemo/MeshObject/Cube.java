package com.gamedev.sjm.glesmvpdemo.MeshObject;

import com.gamedev.sjm.glesmvpdemo.SimpleEngine.components.Mesh;
import com.gamedev.sjm.glesmvpdemo.SimpleEngine.Util.MathUtil.Vector3;

public class Cube extends Mesh {
    final float UNIT_SIZE = 0.2f;
    public Cube(){
        // 正面
        Vector3 leftdown_Forward = new Vector3(0, 0, 0);
        Vector3 leftup_Forward = new Vector3(0, 2 * UNIT_SIZE, 0);
        Vector3 rightdown_Forward = new Vector3(2 * UNIT_SIZE, 0, 0);
        Vector3 rightup_Forward = new Vector3(2 * UNIT_SIZE, 2 * UNIT_SIZE, 0);

        // 右侧面
        Vector3 leftdown_Right = new Vector3(2 * UNIT_SIZE, 0, 0);
        Vector3 leftup_Right = new Vector3(2 * UNIT_SIZE, 2 * UNIT_SIZE, 0);
        Vector3 rightup_Right = new Vector3(2 * UNIT_SIZE, 2 * UNIT_SIZE, -2 * UNIT_SIZE);
        Vector3 rightdown_Right = new Vector3(2 * UNIT_SIZE, 0, -2 * UNIT_SIZE);

        // 左侧面
        Vector3 leftdown_Left = new Vector3(0, 0, -2 * UNIT_SIZE);
        Vector3 rightdown_Left = new Vector3(0, 0, 0);
        Vector3 leftup_Left = new Vector3(0, 2 * UNIT_SIZE, -2 * UNIT_SIZE);
        Vector3 rightup_Left = new Vector3(0, 2 * UNIT_SIZE, 0);

        // 背面
        Vector3 leftdown_Back = new Vector3(0, 0, -2 * UNIT_SIZE);
        Vector3 leftup_Back = new Vector3(0, 2 * UNIT_SIZE, -2 * UNIT_SIZE);
        Vector3 rightdown_Back = new Vector3(2 * UNIT_SIZE, 0, -2 * UNIT_SIZE);
        Vector3 rightup_Back = new Vector3(2 * UNIT_SIZE, 2 * UNIT_SIZE, -2 * UNIT_SIZE);

        // 上面
        Vector3 leftdown_Up = new Vector3(0, 2 * UNIT_SIZE, 0);
        Vector3 rightdown_Up = new Vector3(2 * UNIT_SIZE, 2 * UNIT_SIZE, 0);
        Vector3 leftup_Up = new Vector3(0, 2 * UNIT_SIZE, -2 * UNIT_SIZE);
        Vector3 rightup_Up = new Vector3(2 * UNIT_SIZE, 2 * UNIT_SIZE, -2 * UNIT_SIZE);

        // 下面
        Vector3 leftdown_Down = new Vector3(0, 0, 0);   // 左下
        Vector3 leftup_Down = new Vector3(0, 0, -2 * UNIT_SIZE);    // 左上
        Vector3 rightdown_Down = new Vector3(2 * UNIT_SIZE, 0, 0);     // 右下
        Vector3 rightup_Down = new Vector3(2 * UNIT_SIZE, 0, -2 * UNIT_SIZE);    // 右上

        float[] vertics = new float[]{
                // 正面
                leftdown_Forward.x,leftdown_Forward.y,leftdown_Forward.z,
                leftup_Forward.x,leftup_Forward.y,leftup_Forward.z,
                rightdown_Forward.x,rightdown_Forward.y,rightdown_Forward.z,
                rightup_Forward.x,rightup_Forward.y,rightup_Forward.z,

                // 右侧面
                leftdown_Right.x,leftdown_Right.y,leftdown_Right.z,
                leftup_Right.x,leftup_Right.y,leftup_Right.z,
                rightdown_Right.x,rightdown_Right.y,rightdown_Right.z,
                rightup_Right.x,rightup_Right.y,rightup_Right.z,

                // 左侧面
                leftdown_Left.x,leftdown_Left.y,leftdown_Left.z,
                leftup_Left.x,leftup_Left.y,leftup_Left.z,
                rightdown_Left.x,rightdown_Left.y,rightdown_Left.z,
                rightup_Left.x,rightup_Left.y,rightup_Left.z,

                // 背面
                leftdown_Back.x,leftdown_Back.y,leftdown_Back.z,
                leftup_Back.x,leftup_Back.y,leftup_Back.z,
                rightdown_Back.x,rightdown_Back.y,rightdown_Back.z,
                rightup_Back.x,rightup_Back.y,rightup_Back.z,

                // 上面
                leftdown_Up.x,leftdown_Up.y,leftdown_Up.z,
                leftup_Up.x,leftup_Up.y,leftup_Up.z,
                rightdown_Up.x,rightdown_Up.y,rightdown_Up.z,
                rightup_Up.x,rightup_Up.y,rightup_Up.z,

                // 下面
                leftdown_Down.x,leftdown_Down.y,leftdown_Down.z,
                leftup_Down.x,leftup_Down.y,leftup_Down.z,
                rightdown_Down.x,rightdown_Down.y,rightdown_Down.z,
                rightup_Down.x,rightup_Down.y,rightup_Down.z
        };

        float[] colors = new float[]{
                // 正面
                1,0,0,1,
                1,0,1,1,
                0,0,1,1,
                0,0,1,1,

                // 右侧面
                1,0,0,1,
                1,0,1,1,
                0,0,1,1,
                0,0,1,1,

                // 左侧面
                1,0,0,1,
                1,0,1,1,
                0,0,1,1,
                0,0,1,1,

                // 背面
                1,0,0,1,
                1,0,1,1,
                0,0,1,1,
                0,0,1,1,

                // 上面
                1,0,0,1,
                1,0,1,1,
                0,0,1,1,
                0,0,1,1,

                // 下面
                1,0,0,1,
                1,0,1,1,
                0,0,1,1,
                0,0,1,1,
        };

        float[] uvs = new float[]{
                // 正面
                0,0,
                0,1,
                1,0,
                1,1,

                // 右侧面
                0,0,
                0,1,
                1,0,
                1,1,

                // 左侧面
                0,0,
                0,1,
                1,0,
                1,1,

                // 背面
                0,0,
                0,1,
                1,0,
                1,1,

                // 上面
                0,0,
                0,1,
                1,0,
                1,1,

                // 下面
                0,0,
                0,1,
                1,0,
                1,1
        };

        int[] triangles = new int[] {
                // 正面
                0,1,2,
                1,3,2,
                // 右侧面
                4,5,6,
                5,7,6,
                // 左侧面
                8,9,10,
                9,11,10,
                // 背面
                12,13,14,
                13,15,14,
                // 上面
                16,17,18,
                17,19,18,
                // 下面
                20,21,22,
                21,23,22
        };

        setVertics(vertics);
        setUv(uvs);
        setTriangles(triangles);
        setColors(colors);
    }
}
