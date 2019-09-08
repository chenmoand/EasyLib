package com.brageast.easylib.util;

import com.brageast.easylib.realization.BeanMethod;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;

public abstract class BeanVaule {

    public static HashMap<String, Object>getPluginBean(JavaPlugin javaPlugin) {
        return BeanMethod.getBeans().get(javaPlugin.getDescription().getName());
    }
    public static HashMap<String, Object>getPluginBean(Plugin plugin) {
        return BeanMethod.getBeans().get(plugin.getDescription().getName());
    }
}
