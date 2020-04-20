package io.serverstatistics.api;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * A simple annotation used to mark methods as global tags. This is no replacement for the mapping system in the plugin.
 * This annotation should only be used as global tags. For instance; a server type, a datacenter region, or some kind
 * of server specific setting that you want to filter on in your datasource. Any object may be returned by this method,
 * but they will all be passed to a string. It would be recommended to return a string by origin, to prevent potential
 * unexpected results.
 * @since 2.0.0
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface ServerTag {

    String name();
}
