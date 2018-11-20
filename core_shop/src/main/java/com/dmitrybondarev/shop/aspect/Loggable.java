package com.dmitrybondarev.shop.aspect;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface Loggable {

    boolean logBefore() default true;

    boolean logResponse() default true;

    boolean logExceptions() default true;

    boolean logOnlyExceptions() default false;

    boolean logArgumentsAndResults() default true;

    LoggingLevel logLevel() default LoggingLevel.INFO;
}
