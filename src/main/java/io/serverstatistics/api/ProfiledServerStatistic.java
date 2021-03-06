package io.serverstatistics.api;

import java.util.Map;

/**
 * The {@link ProfiledServerStatistic} interface can be used on raw statistical objects to help expand the profiling
 * when configuration defined profiles can't do.
 */
public interface ProfiledServerStatistic {

    /**
     * Passes the raw statistical objects and returns a {@link Map<String, String>} with the profiled tags.
     * @param value The value to profile
     * @return The generated tags
     */
    Map<String, String> profile(Object value);
}
