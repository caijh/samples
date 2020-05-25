package com.github.caijh.sample.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Metrics {

    /**
     * @return 指标名称
     */
    String name();

    /**
     * 指标的参数
     *
     * @return 参数数组
     */
    MetricsParam[] params() default {};

}
