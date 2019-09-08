package com.brageast.easylib.util;

import com.brageast.easylib.mapping.AnnotationMethod;
import com.brageast.easylib.util.reflection.ScanJar;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.plugin.java.JavaPlugin;
import org.reflections.Reflections;
import org.reflections.scanners.*;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author chenmo
 * 链接插件钩子
 */
public final class EasyHook {

    @Getter private static List<AnnotationMethod> annotations = new ArrayList<>();
    @Setter private JavaPlugin javaPlugin;
    // AnnotationTools
    @Getter private AnnotationTools annotationTools = new AnnotationTools();
//    public EasyHook() {}
////    public EasyHook(JavaPlugin javaPlugin) {
////        this.setJavaPlugin(javaPlugin);
////    }

    public EasyHook register(JavaPlugin javaPlugin){
        return register(javaPlugin, "成功与EasyLib对接");
    }
    public EasyHook register(JavaPlugin javaPlugin, String msg){
        Objects.requireNonNull(javaPlugin, "传入JavaPlugin为空");
        this.setJavaPlugin(javaPlugin);
        this.javaPlugin.getLogger().info(msg);
        return this;
    }
    public EasyHook registerAnnotation(AnnotationMethod annotationMethod) {
        annotations.add(annotationMethod);
        return this;
    }
    public EasyHook registerAnnotations(List<AnnotationMethod> annotationMethods) {
        annotations.addAll(annotationMethods);
        return this;
    }
    public void init(){
        Objects.requireNonNull(javaPlugin, "没有注册javaPlugin");

        this.annotationTools = new AnnotationTools(this.javaPlugin, new Reflections(
                new ConfigurationBuilder()
                        .setUrls(ClasspathHelper.forPackage(javaPlugin.getDescription().getMain()))
                        .setUrls(ScanJar.getPathUrl(javaPlugin.getDescription().getMain()))
                        .setScanners(new SubTypesScanner(true), new TypeAnnotationsScanner(), new MethodAnnotationsScanner(),
                                new MethodParameterScanner(), new MethodParameterNamesScanner(), new MemberUsageScanner(),  new FieldAnnotationsScanner())
        ));
        annotations.forEach(annotationMethod -> annotationMethod.init(annotationTools));
    }


}
