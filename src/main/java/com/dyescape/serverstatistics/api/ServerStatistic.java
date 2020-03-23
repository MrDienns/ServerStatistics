package com.dyescape.serverstatistics.api;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * A simple annotation used to mark methods as statistic endpoints. This annotation is to be used on methods only.
 * Methods annotated may return any supported object (collections, objects, primitives).
 * @since 2.0.0
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface ServerStatistic {

    String name();
}
