package com.brageast.easylib.realization;

import com.brageast.easylib.annotation.AutoJoin;
import com.brageast.easylib.mapping.AnnotationMethod;
import com.brageast.easylib.util.AnnotationTools;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Objects;
import java.util.Set;

public class AutoJoinMethod extends AnnotationMethod {
    public AutoJoinMethod(Class<? extends Annotation> baseAnnotation) {
        super(baseAnnotation);
    }

    @Override
    public void init(AnnotationTools annotationTools) {
        HashMap<String, Object> objects = Objects.requireNonNull(BeanMethod.getBeans().get(annotationTools.getName()));
        final Set<Field> fieldsAnnotatedWith = annotationTools.getReflections().getFieldsAnnotatedWith(baseAnnotation);
        for(Field field : fieldsAnnotatedWith ) {
            field.setAccessible(true);
            final AutoJoin autoJoin = (AutoJoin) field.getDeclaredAnnotation(baseAnnotation);
            final String val = autoJoin.value().equals("") ? field.getName() : autoJoin.value();
            if(!annotationTools.isJavaPlugin(field)) {
                try {
                    field.set(field.getDeclaringClass().newInstance(), Objects.requireNonNull(objects.get(val), "没要找到bean,无法注入"));
                } catch (IllegalAccessException | InstantiationException e) {
                    e.printStackTrace();
                }
            } else {
                try {
                    field.set(annotationTools.getJavaPlugin(), Objects.requireNonNull(objects.get(val), "没要找到bean,无法注入"));
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
