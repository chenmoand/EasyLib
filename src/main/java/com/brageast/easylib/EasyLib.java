package com.brageast.easylib;

import com.brageast.easylib.annotation.Bean;
import com.brageast.easylib.annotation.BukkitListener;
import com.brageast.easylib.annotation.Command;
import com.brageast.easylib.annotation.EnableEasyLib;
import com.brageast.easylib.realization.BeanMethod;
import com.brageast.easylib.realization.CommandMethod;
import com.brageast.easylib.realization.ListenerMethod;
import com.brageast.easylib.util.EasyHook;
import org.bukkit.plugin.java.JavaPlugin;

@EnableEasyLib
public class EasyLib extends JavaPlugin {
    @Override
    public void onDisable() {
        getLogger().info("插件已经成功卸载");
    }

    @Override
    public void onEnable() {
        /*try {
            new EnableEasyLibMethod().init();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }*/
//        Bukkit.getPluginManager().registerEvents(new PluginInit(), this);
        /*EasyPlugin.register(this); // 注册插件
        EasyPlugin.registerAnnotation(new CommandMethod(Command.class));
        EasyPlugin.init();*/

        new EasyHook().register(this)
                .registerAnnotation(new CommandMethod(Command.class))
                .registerAnnotation(new ListenerMethod(BukkitListener.class))
                .registerAnnotation(new BeanMethod(Bean.class))
                .init();
    }

}
