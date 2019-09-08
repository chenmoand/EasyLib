package com.brageast.easylib.realization;

import com.brageast.easylib.annotation.Command;
import com.brageast.easylib.command.CommandImpl;
import com.brageast.easylib.mapping.AnnotationMethod;
import com.brageast.easylib.util.AnnotationTools;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Set;

public class CommandMethod extends AnnotationMethod {

    public CommandMethod(Class<? extends Annotation> baseAnnotation) {
        super(baseAnnotation);
    } // 这样本来是想绑定类来的接股票放弃了

    @Override
    public void init(AnnotationTools annotationTools) {
//        final Set<Class<?>> typesAnnotatedWith = getWhithAnnotationClass(annotationTools);
////        annotationTools.getReflections().getMethodsAnnotatedWith(Command.class).forEach(m -> System.out.println(m.getName()));
        final Set<Method> methodsAnnotatedWith = annotationTools.getReflections().getMethodsAnnotatedWith(baseAnnotation);
        for(Method method : methodsAnnotatedWith) {
            if (method.getDeclaredAnnotation(baseAnnotation) != null){
                final Command cmd = method.getDeclaredAnnotation(Command.class);
                try {
                    if(method.getDeclaringClass() == annotationTools.getJavaPlugin().getClass()){ // 判断是否mian类防止再次创建插件
                        annotationTools.getJavaPlugin().getServer().
                                getPluginCommand(cmd.value()).setExecutor(
                                new CommandImpl(annotationTools.getJavaPlugin(), method));
                        continue;
                    }
                    Object object = method.getDeclaringClass().newInstance();
                    annotationTools.getJavaPlugin().getServer().
                            getPluginCommand(cmd.value()).setExecutor(
                                    new CommandImpl(object, method));
                } catch (InstantiationException | IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
