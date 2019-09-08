package com.brageast.easylib.command;

import com.brageast.easylib.annotation.AutoJoin;
import com.brageast.easylib.annotation.Command;
import org.bukkit.command.CommandSender;

public class EasyCommand {

    @AutoJoin("str")
    private String java;

    @Command("easylib")
    public boolean easyLib(CommandSender sender, String[] args){
        sender.sendMessage("这是一条测试的命令" + java);
        return true;
    }

}
