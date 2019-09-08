package com.brageast.easylib.event;

import com.brageast.easylib.annotation.Bean;
import com.brageast.easylib.annotation.BukkitListener;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerLoadEvent;

import static org.bukkit.event.EventPriority.HIGHEST;

@Bean
@BukkitListener
public class PluginInit implements Listener {

    @Bean
    public String str = "这是个测试";

    @EventHandler(priority = HIGHEST)
    public void onServerInit(ServerLoadEvent event){
//        EasyPlugin.init();
    }
}
