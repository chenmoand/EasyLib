package com.brageast.easylib.annotation;

import java.lang.annotation.*;

@Inherited
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
public @interface AutoJoin {
    /**
     * 名字,如果不写直接获取自己创建的名字
     * @return
     */
    String value() default "";
}
