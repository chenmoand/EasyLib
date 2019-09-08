package com.brageast.easylib.event;

import com.brageast.easylib.annotation.BukkitListener;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerLoadEvent;

import static org.bukkit.event.EventPriority.HIGHEST;

@BukkitListener
public class PluginInit implements Listener {

    @EventHandler(priority = HIGHEST)
    public void onServerInit(ServerLoadEvent event){
//        EasyPlugin.init();
    }
}
