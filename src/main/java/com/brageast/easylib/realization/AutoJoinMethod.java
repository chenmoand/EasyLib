package com.brageast.easylib.realization;

import com.brageast.easylib.annotation.AutoJoin;
import com.brageast.easylib.mapping.AnnotationMethod;
import com.brageast.easylib.util.AnnotationTools;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Objects;
import java.util.Set;

/**
 * 只适用 static 代码注入
 *
 */
public class AutoJoinMethod extends AnnotationMethod {
    public AutoJoinMethod(Class<? extends Annotation> baseAnnotation) {
        super(baseAnnotation);
    }

    @Override
    public void init(AnnotationTools annotationTools) {
        HashMap<String, Object> objects = Objects.requireNonNull(BeanMethod.getBeans().get(annotationTools.getName()));
        final Set<Field> fieldsAnnotatedWith = annotationTools.getReflections().getFieldsAnnotatedWith(baseAnnotation);
        for(Field field : fieldsAnnotatedWith ) {
//            System.out.println(field);

            final AutoJoin autoJoin = (AutoJoin) field.getDeclaredAnnotation(baseAnnotation);
            final String val = autoJoin.value().equals("") ? field.getName() : autoJoin.value();
//            System.out.println(val);
//            System.out.println(objects.get(val));
            if(!annotationTools.isJavaPlugin(field)) {
                try {
                    field.setAccessible(true);
                    field.set(field.getDeclaringClass().newInstance(), Objects.requireNonNull(objects.get(val), "没要找到bean,无法注入"));
                } catch (IllegalAccessException | InstantiationException e) {
                    e.printStackTrace();
                }
            }
        }

    }
    public static Object autoClassImport(Object obj, String pluginName) {
        HashMap<String, Object> objects = Objects.requireNonNull(BeanMethod.getBeans().get(pluginName));
        Class cls = obj.getClass();
        for (Field field : cls.getFields()) {
            if(field.getDeclaredAnnotation(AutoJoin.class)!= null) {
                AutoJoin autoJoin = field.getDeclaredAnnotation(AutoJoin.class);
                System.out.println(autoJoin);
                final String val = autoJoin.value().equals("") ? field.getName() : autoJoin.value();
                field.setAccessible(true);
                try {
                    field.set(obj, Objects.requireNonNull(objects.get(val), "没要找到bean,无法注入"));
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        return obj;
    }
}
