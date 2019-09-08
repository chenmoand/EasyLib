package com.brageast.easylib.annotation;

import java.lang.annotation.*;

@Inherited
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.FIELD})
public @interface Bean {
    /**
     * 默认获取这个对象的名字
     * 如果没有默认是你自己创建的名字
     * @return 对象名字
     */
    String value() default "";
}
