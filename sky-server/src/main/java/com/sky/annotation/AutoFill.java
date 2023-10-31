package com.sky.annotation;

import com.sky.enumeration.OperationType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Author: kante_yang
 * @Date: 2023/10/31
 */

/**
 * 自定义注解，用来标识某个方法是否需要进行AOP 拦截，增强
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface AutoFill {
    // 数据库的操作类型：UPDATE INSERT
    OperationType value();
}
