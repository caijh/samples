package com.github.caijh.sample.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.ANNOTATION_TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MetricsParam {

    /**
     * @return 参数名称
     */
    String name();

    /**
     * @return 参数值类型
     */
    Class<?> valueType();

    /**
     * @return 是否隐式
     */
    boolean implicit() default false;

}
