package com.brageast.easylib.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class CommandImpl implements CommandExecutor {
    private Object obj;
    private Method method;

    public CommandImpl(Object obj, Method method) {
        this.obj = obj;
        this.method = method;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        try {
            method.setAccessible(true);
            return (boolean) method.invoke(obj, sender, args);
        } catch (IllegalAccessException | InvocationTargetException e) {
            return false;
        }

    }
}
