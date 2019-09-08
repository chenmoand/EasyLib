package com.brageast.easylib.util;

import com.brageast.easylib.mapping.AnnotationMethod;
import com.brageast.easylib.util.reflection.ScanJar;
import org.bukkit.plugin.java.JavaPlugin;
import org.reflections.Reflections;
import org.reflections.scanners.*;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

/**
 * @author chenmo
 * 这个类在因为插件加载顺序问题所弃用
 * 由EasyHook代替
 *
 */
@Deprecated
public abstract class EasyPlugin {
    // 存储插件注册的地方
    private static HashMap<String, JavaPlugin> javaPlugins = new HashMap<>();
    // 注解类存放的地方
    private static List<AnnotationMethod> annotations = new ArrayList<>();
    // AnnotationTools
    private static List<AnnotationTools> atools = new ArrayList<>();

    public static HashMap<String, JavaPlugin> getJavaPlugins() {
        return javaPlugins;
    }

    public static List<AnnotationMethod> getAnnotationMethods() {
        return annotations;
    }

    /**
     * 将插件注册到EasyLib
     * @param javaPlugin
     * @param msg 是否显示对接消息
     */
    @Deprecated
    public static void register(JavaPlugin javaPlugin, boolean msg) {
        Objects.requireNonNull(javaPlugin, "注册的javaPlugin不能为空");
        if(msg) javaPlugin.getLogger().info("成功在EasyLib注册");
        javaPlugins.put(javaPlugin.getDescription().getName(), javaPlugin);
    }
    public static void register(JavaPlugin javaPlugin) {
        register(javaPlugin, true);
    }
//    @Deprecated
    public static void registerAnnotation(AnnotationMethod annotationMethod){
        Objects.requireNonNull(annotationMethod, "注解实现类不能传入空值");
        annotations.add(annotationMethod);
    }

    public static void init() {
        javaPlugins.forEach((name, plugin) -> {
            AnnotationTools annotationTools;
            annotationTools = new AnnotationTools(
                    plugin, new Reflections(
                            new ConfigurationBuilder().
                                    setUrls(ClasspathHelper.forPackage(plugin.getClass().getPackage().getName())).
                                    setUrls(ScanJar.getPathUrls()).
                                    setScanners(new SubTypesScanner(true), new TypeAnnotationsScanner(), new MethodAnnotationsScanner(),
                                            new MethodParameterScanner(), new MethodParameterNamesScanner(), new MemberUsageScanner(),  new FieldAnnotationsScanner())

            )
            );
            atools.add(annotationTools);

        });
        atools.forEach(annotationTools -> annotations.forEach(annotationMethod -> annotationMethod.init(annotationTools)));
    }

    public List<AnnotationTools> getAtools() {
        return atools;
    }
}
