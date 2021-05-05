package io.serverstatistics.api;

import java.util.Collection;
import java.util.Map;

public interface Profile {

    String getName();
    Collection<Class<?>> getAppliesTo();
    Map<String, String> profile(Object value);
}
