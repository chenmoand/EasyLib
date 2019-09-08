package com.brageast.easylib.realization;

import com.brageast.easylib.annotation.EnableEasyLib;
import com.brageast.easylib.util.reflection.ScanJar;

import java.io.File;

public class EnableEasyLibMethod {
    public void init () throws ClassNotFoundException {
        for (File file : ScanJar.getJar()) {
            final String main = (String) ScanJar.getJarYml(file).get("main");
            final String name = (String) ScanJar.getJarYml(file).get("name");
            if(main == null || name == null ) continue;
            Class cls = Class.forName(main);
            if(cls.getDeclaredAnnotation(EnableEasyLib.class) != null){
                System.out.println("hahshsa");
            }
        }
    }
}
