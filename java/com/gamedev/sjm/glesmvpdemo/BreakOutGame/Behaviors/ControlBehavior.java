package com.gamedev.sjm.glesmvpdemo.BreakOutGame.Behaviors;

import com.gamedev.sjm.glesmvpdemo.BreakOutGame.ColorManager;
import com.gamedev.sjm.glesmvpdemo.BreakOutGame.Gameobjects.Brick;
import com.gamedev.sjm.glesmvpdemo.BreakOutGame.Gameobjects.HardBrick;
import com.gamedev.sjm.glesmvpdemo.SimpleEngine.Behavior;
import com.gamedev.sjm.glesmvpdemo.SimpleEngine.SimpleGameEnginer;
import com.gamedev.sjm.glesmvpdemo.SimpleEngine.Util.MathUtil.Vector2;
import com.gamedev.sjm.glesmvpdemo.SimpleEngine.Util.MathUtil.Vector3;

import java.util.ArrayList;
import java.util.List;

public class ControlBehavior extends Behavior {

    private int[][] places;

    private float intervalDistance = 0.3f;
    private Vector2 xRange = new Vector2(-1,1);
    private Vector2 yRange = new Vector2(-1.9f,1.9f);

    // 列数
    private int col = 8;
    // 行数
    private int row = 12;

    List<Brick> bricks = new ArrayList<>();

    Vector3[] colors = new Vector3[]{
            ColorManager.Blue,
            ColorManager.Red,
            ColorManager.Green,
            ColorManager.Purple,
            ColorManager.Yellow
    };

    @Override
    public void OnStart() {
        super.OnStart();
        places = new int[][]{
                {1,1,0,0,0,1,1,1,1},
                {1,1,1,1,1,1,2,2,1},
                {0,1,2,2,1,1,0,1,1},
                {0,0,1,1,1,0,0,1,1},
                {1,1,2,2,2,2,1,1,1},
                {0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0},
        };

        for(int i=0;i<row;i++){
            for(int j=0;j<col;j++){
                if(places[i][j]==1){
                    Vector3 color = colors[(int)(Math.random()*colors.length)];
                    // 此处有砖块
                    Brick brick = new Brick(color.x,color.y,color.z);
                    // 左上角为（1,1.9）,右下角为(-1,-1.9)
                    // 即j==1时，x=1,j=col时，x=-1,每次增幅为0.3
                    // 则 x = 1-0.3*j
                    // 同理，y = 1.9-0.3*i;
                    brick.transform.pos = new Vector3(
                            1f-0.3f*j,
                            1.9f-0.3f*i,
                            0
                            );
                    SimpleGameEnginer.main.gameObjects.add(brick);
                    bricks.add(brick);
                }else if(places[i][j]==2){
                    HardBrick brick = new HardBrick();
                    brick.transform.pos = new Vector3(
                            1f-0.3f*j,
                            1.9f-0.3f*i,
                            0
                    );
                    SimpleGameEnginer.main.gameObjects.add(brick);
                    bricks.add(brick);
                }
            }
        }
    }

    @Override
    public void OnUpdate() {
        super.OnUpdate();
    }
}
