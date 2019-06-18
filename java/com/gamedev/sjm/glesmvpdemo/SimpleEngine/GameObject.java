package com.gamedev.sjm.glesmvpdemo.SimpleEngine;

import com.gamedev.sjm.glesmvpdemo.SimpleEngine.InterFace.Renderable;
import com.gamedev.sjm.glesmvpdemo.SimpleEngine.components.Component;
import com.gamedev.sjm.glesmvpdemo.SimpleEngine.components.MeshRender;
import com.gamedev.sjm.glesmvpdemo.SimpleEngine.components.Transform;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * 通用的游戏对象
 */
public class GameObject implements Renderable {

    public Transform transform;

    // 全局唯一的标记
    int tag;

    private Map<String,Component> componentMap;

    public GameObject(Transform transform){
        componentMap = new HashMap<>();
        AddComponent("transform",transform);
        this.transform = transform;
    }

    public GameObject(){
        componentMap = new HashMap<>();
        Transform transform = new Transform();
        AddComponent("transform",transform);
        this.transform = transform;
    }

    public Collection<Component> GetAllComponent(){
        if(componentMap==null) return null;
        return componentMap.values();
    }

    public <T extends Component> T GetComponent(String key){
        if(componentMap==null || !componentMap.containsKey(key)) return null;
        return (T) componentMap.get(key);
    }

    public void AddComponent(Component component){
        String className = component.getClass().getSimpleName();
        AddComponent(className,component);
    }

    public void AddComponent(String key,Component component) {
        if(componentMap==null || componentMap.containsKey(key)) return;
        componentMap.put(key,component);
        component.setGameObject(this);
    }

    public static void Destory(GameObject gameObject){
        for(Component component : gameObject.GetAllComponent()){
            component.OnExit();
        }
        SimpleGameEnginer.main.deleteGameObject(gameObject);
    }

    @Override
    public void Render() {
        if(componentMap.containsKey("MeshRender")){
            MeshRender meshRender = GetComponent("MeshRender");
            meshRender.Render(this);
        }
    }
}
