package com.gamedev.sjm.glesmvpdemo.SimpleEngine.Util.MeshUtil;

import android.content.res.Resources;

import com.gamedev.sjm.glesmvpdemo.SimpleEngine.Util.MathUtil.Vector2;
import com.gamedev.sjm.glesmvpdemo.SimpleEngine.Util.MathUtil.Vector3;
import com.gamedev.sjm.glesmvpdemo.SimpleEngine.Util.ShaderUtil.ShaderUtil;
import com.gamedev.sjm.glesmvpdemo.SimpleEngine.components.Mesh;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Vector;

public class OBJLoader {
    public static Mesh ParseOBJ(String objPath, Resources resources) throws IOException {

        List<Vector2> tuvs = new ArrayList<>();
        List<Vector3> tNormals = new ArrayList<>();
        List<Vertex> tVertex = new ArrayList<>();
        List<Integer> ttriangles = new ArrayList<>();

        String line;
        BufferedReader reader = new BufferedReader(new InputStreamReader(resources.getAssets().open(objPath)));
        String[] param;
        while ((line=reader.readLine())!=null){
            if (line.length() <= 2) continue;

            String anotherCommand = line.substring(0, 2);
            // 转为全大写
            anotherCommand = anotherCommand.toUpperCase();
            if (anotherCommand.equals("VT") || anotherCommand.equals("TU") || anotherCommand.equals("TV")) {
                // 表示输入的是纹理坐标
                param = line.split(" ");
                float u = Float.parseFloat(param[1]);
                float v = Float.parseFloat(param[2]);
                tuvs.add(new Vector2(u, v));

                continue;
            } else if (anotherCommand.equals("VN")) {
                // 表示输入的是法向量
                param = line.split(" ");
                float x = Float.parseFloat(param[1]);
                float y = Float.parseFloat(param[2]);
                float z = Float.parseFloat(param[3]);
                tNormals.add(new Vector3(x, y, z));

                continue;
            }

            char command = line.charAt(0);

            switch (command) {
                // 表示这是个顶点
                // 顶点后面跟着三个浮点数，表示该顶点在模型空间下的坐标
                case 'v':
                case 'V':
                    param = line.split(" ");
                    float x = Float.parseFloat(param[1]);
                    float y = Float.parseFloat(param[2]);
                    float z = Float.parseFloat(param[3]);
                    Vector3 ttpos = (new Vector3(x,y,z));
                    tVertex.add(new Vertex(ttpos,Vector3.Zero,new Vector2(0,0)));
                    break;
                case 'f':
                case 'F':
                    // 表示读取的是一个三角面
                    // 这个三角面的格式如下:
                    // f Vertex1/Texture1/Normal1 Vertex2/Texture2/Normal2 Vertex3/Texture3/Normal3


                    param = line.split(" ");

                    for (int i = 1; i < 4; i++) {
                        String[] ps = param[i].split("/");

                        // 需要注意的是输入的索引是从1开始的,这里要将其-1

                        // 顶点索引
                        int triangle = Integer.parseInt(ps[0])-1;
                        // uv索引
                        int uvIndex = Integer.parseInt(ps[1])-1;
                        // 法线索引
                        int normalIndex = Integer.parseInt(ps[2])-1;

                        // 获得目标顶点
                        Vertex v = tVertex.get(triangle);

                        // 设置法线
                        v.normal = tNormals.get(normalIndex);

                        // 设置uv
                        v.texcoord = new Vector2(tuvs.get(uvIndex).u,tuvs.get(uvIndex).v);


                        // 添加进索引中
                        ttriangles.add(triangle);

                    }

                    break;
            }

        }

        Mesh mesh = new Mesh();
        float[] vertics = new float[tVertex.size()*3];
        float[] uv = new float[tuvs.size()*2];
        int[] triangles = new int[ttriangles.size()];
        float[] normals = new float[tVertex.size()*3];

        // 复制顶点索引
        for(int i=0;i<ttriangles.size();i++) triangles[i] = ttriangles.get(i);

        int vIndex = 0;
        int uvIndex = 0;
        int normalIndex = 0;
        // 复制顶点各属性
        for(int i=0;i<tVertex.size();i++){
            Vertex v = tVertex.get(i);
            vertics[vIndex++] = v.position.x;vertics[vIndex++] = v.position.y;vertics[vIndex++] = v.position.z;
            uv[uvIndex++] = v.texcoord.u;uv[uvIndex++] = v.texcoord.v;
            normals[normalIndex++] = v.normal.x;normals[normalIndex++] = v.normal.y;normals[normalIndex++] = v.normal.z;
        }

        mesh.setVertics(vertics);
        mesh.setNormals(normals);
        mesh.setUv(uv);
        mesh.setTriangles(triangles);

        return mesh;
    }
}
