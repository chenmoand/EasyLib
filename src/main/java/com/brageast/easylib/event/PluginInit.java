package com.brageast.easylib.event;

import com.brageast.easylib.annotation.AutoJoin;
import com.brageast.easylib.annotation.Bean;
import com.brageast.easylib.annotation.BukkitListener;
import org.bukkit.event.Listener;

@Bean
@BukkitListener
public class PluginInit implements Listener {

    @Bean
    public String str = "这是个测试";

    @AutoJoin("str")
    private String java;

    /*@EventHandler(priority = HIGHEST)
    public void onServerInit(Event event){
//        EasyPlugin.init();
    }*/
}
