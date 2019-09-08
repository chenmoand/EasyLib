package com.brageast.easylib.util.reflection;

import com.brageast.easylib.util.EasyPlugin;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public abstract class ScanJar {
    public static File[] getJar(){
        List<File> jarful = new ArrayList<>();
        File folder = new File("plugins");
        File[] files = folder.listFiles();
        for(File f : files){
            if(f.isFile() && f.getPath().endsWith(".jar")){
                jarful.add(f);
            }
        }
        return jarful.toArray(new File[jarful.size()]);
    }
    public static HashMap<String, Object> getJarYml(File f){
        HashMap<String, Object> m = null;
        try {
            InputStream is = null;
            JarFile jf = new JarFile(f);
            Enumeration<JarEntry> et = jf.entries();
            while (et.hasMoreElements()){
                JarEntry je = et.nextElement();
                if(je.getName().contains("plugin.yml")){
                    is = jf.getInputStream(je);
                    Yaml yml = new Yaml();
                    m = yml.load(is);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return m;
    }

    public static List<String> getPluginCorrelation() {
        File[] files = getJar();
        List<String> list = new ArrayList<>();
        EasyPlugin.getJavaPlugins().forEach((name, plugin) -> {
            for(File f : files) {
                String main = (String) getJarYml(f).get("main");
                if(main.equals(plugin.getDescription().getMain())){
                    list.add(f.getAbsolutePath());
                    break;
                }
            }
        });
        return list;
    }
    public static URL getPathUrl(String main){
        for (File file : getJar()) {
            String str = (String) getJarYml(file).get("main");
            if(str.equals(main)){
                try {
                    return new URL("jar:file:" + file.getAbsolutePath() + "!/");
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }
    public static URL[] getPathUrls() {
        final List<String> list = getPluginCorrelation();
        List<URL> urls = new ArrayList<>();
        list.forEach(s -> {
            try {
                urls.add(new URL("jar:file:" + s + "!/"));
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        });
//        System.out.println(urls);
        return urls.toArray(new URL[urls.size()]);
    }
}
