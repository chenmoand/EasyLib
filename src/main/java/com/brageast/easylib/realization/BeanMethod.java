package com.brageast.easylib.realization;

import com.brageast.easylib.annotation.Bean;
import com.brageast.easylib.mapping.AnnotationMethod;
import com.brageast.easylib.util.AnnotationTools;
import lombok.Getter;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Set;

/**
 * 一个处理bean的类
 * @author chenmo
 */
public class BeanMethod extends AnnotationMethod {
    // bean 存储map很重要
    @Getter private static HashMap<String, HashMap<String, Object>> beans = new HashMap<>();

    public BeanMethod(Class<? extends Annotation> baseAnnotation) {
        super(baseAnnotation);
    }

    @Override
    public void init(AnnotationTools annotationTools) {
        HashMap<String, Object> objects = new HashMap<>();
        final String name = annotationTools.getName();
        final Set<Class<?>> typesAnnotatedWith = annotationTools.getReflections().getTypesAnnotatedWith(baseAnnotation);
        final Set<Field> fieldsAnnotatedWith = annotationTools.getReflections().getFieldsAnnotatedWith(baseAnnotation);
        for(Class cls : typesAnnotatedWith) {
            final Bean bean = (Bean) cls.getDeclaredAnnotation(baseAnnotation);
            final String beanname = bean.value().equals("") ? cls.getSimpleName() : bean.value();
            try {
                if(!annotationTools.isJavaPlugin(cls)) {
                    objects.put(beanname, cls.newInstance());
                } else {
                    objects.put(beanname, annotationTools.getJavaPlugin());
                }
            } catch (InstantiationException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        for(Field field : fieldsAnnotatedWith) {
            final Bean bean = (Bean) field.getDeclaredAnnotation(baseAnnotation);
            final String beanname = bean.value().equals("") ? field.getName() : bean.value();
            if(!annotationTools.isJavaPlugin(field)) {
                try {
                    objects.put(beanname, field.get(field.getDeclaringClass().newInstance()));
                } catch (IllegalAccessException | InstantiationException e) {
                    e.printStackTrace();
                }
            } else {
                try {
                    objects.put(beanname, field.get(annotationTools.getJavaPlugin()));
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        objects.forEach((n, b ) -> System.out.println(n  + " : " + b));
        beans.put(name, objects); // 最终到这里面来
    }

}
