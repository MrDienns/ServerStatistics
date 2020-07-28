package io.serverstatistics.api;

/**
 * The {@link StatisticProvider} is an interface which one must implement if a non-static statistic method is created.
 * Optionally, you can override the {@link #shouldEnable()} function to enable and disable the metric. For example, you
 * may implement a check in this function which checks if you're using Paper or Spigot, or whether or not a specific
 * plugin is installed.
 * @since 2.0.0
 */
public interface StatisticProvider {

    /**
     * Function which returns a boolean, indicating whether or not the statistic methods in this class (and subclasses)
     * should be enabled or not. Defaults to true.
     * @return boolean
     * @since 2.0.0
     */
    default boolean shouldEnable() {
        return true;
    }
}
