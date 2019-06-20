package com.gamedev.sjm.glesmvpdemo.SpaceShipGame.Behaviors;

import com.gamedev.sjm.glesmvpdemo.SimpleEngine.Behavior;
import com.gamedev.sjm.glesmvpdemo.SimpleEngine.GameObject;
import com.gamedev.sjm.glesmvpdemo.SimpleEngine.Time.Time;
import com.gamedev.sjm.glesmvpdemo.SimpleEngine.Util.ShaderUtil.Shader;

public class DeadBehavior extends Behavior {
    public Shader getShader() {
        return shader;
    }

    public void setShader(Shader shader) {
        this.shader = shader;
    }

    public DeadBehavior(Shader shader){
        this.shader = shader;
    }

    private Shader shader;
    private float factor = 0;
    public float resloveSpeed = 0.5f;
    public boolean used = true;

    @Override
    public void OnUpdate() {
        if(!used) return;
        if(factor>=1.2f) {
            GameObject.Destory(gameObject);
            used = false;
            shader.Use();
            shader.SetFloat("burnAmount",0);
            return;
        }
        super.OnUpdate();
        factor += resloveSpeed*Time.deltaTime;
        shader.Use();
        shader.SetFloat("burnAmount",factor);
    }
}
