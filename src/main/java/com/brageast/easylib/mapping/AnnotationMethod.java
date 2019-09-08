package com.brageast.easylib.mapping;

import com.brageast.easylib.annotation.Command;
import com.brageast.easylib.util.AnnotationTools;

import java.lang.annotation.Annotation;
import java.util.Set;

public abstract class AnnotationMethod {
    protected Class<? extends Annotation> baseAnnotation;

    public AnnotationMethod(Class<? extends Annotation> baseAnnotation){
        this.baseAnnotation = baseAnnotation;
    }

    public abstract void init(AnnotationTools annotationTools);
    public Set<Class<?>> getWhithAnnotationClass(AnnotationTools annotationTools) {
        return annotationTools.getReflections().getTypesAnnotatedWith(Command.class);
    }

}
