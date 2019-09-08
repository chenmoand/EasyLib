package com.brageast.easylib.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bukkit.plugin.java.JavaPlugin;
import org.reflections.Reflections;

import java.lang.reflect.Method;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AnnotationTools {
    private JavaPlugin javaPlugin;
    private Reflections reflections;

    /**
     * 判断是否是JavaPliguin
     * @param method
     * @return 验证是否javaPlugi的boolean
     */
    public boolean isJavaPlugin(Method method){
        return javaPlugin.getClass() == method.getDeclaringClass();
    }
    public boolean isJavaPlugin(Class<?> cls){
        return javaPlugin.getClass() == cls;
    }
}
