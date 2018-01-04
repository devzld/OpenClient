package com.zld.openclient.base.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * ClassName: ActivityFragmentInject<p>
 * Function: Activity、Fragment初始化的用到的注解<p>
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface ActivityFragmentInject {

    /**
     * 布局Id
     */
    int contentViewId() default -1;

    /**
     * 是否开启EventBus
     */
    boolean isOpenEventBus() default false;
}
