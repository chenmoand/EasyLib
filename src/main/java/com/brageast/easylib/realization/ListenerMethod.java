package com.brageast.easylib.realization;

import com.brageast.easylib.mapping.AnnotationMethod;
import com.brageast.easylib.util.AnnotationTools;
import org.bukkit.plugin.java.JavaPlugin;

import java.lang.annotation.Annotation;
import java.util.Objects;
import java.util.Set;

public class ListenerMethod extends AnnotationMethod  {

    public ListenerMethod(Class<? extends Annotation> baseAnnotation) {
        super(baseAnnotation);
    }

    @Override
    public void init(AnnotationTools annotationTools) {
        final Set<Class<?>> typesAnnotatedWith = annotationTools.getReflections().getTypesAnnotatedWith(baseAnnotation);
        for (Class<?> cls : typesAnnotatedWith) {

            if(annotationTools.isJavaPlugin(cls)) continue;

            JavaPlugin javaPlugin = annotationTools.getJavaPlugin();
            try {
                javaPlugin.getServer().getPluginManager().
                        registerEvents((org.bukkit.event.Listener) cls.newInstance(), Objects.requireNonNull(javaPlugin.getServer().getPluginManager().getPlugin(javaPlugin.getDescription().getName())));
            } catch (InstantiationException | IllegalAccessException e) {
                e.printStackTrace();
            }

        }
    }
}
