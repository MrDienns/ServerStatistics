package io.serverstatistics.api;

/**
 * A simple enum to give specific types to metrics. Different types will bring different calculation & reset logic.
 * @since 2.0.0
 */
public enum StatisticType {

    /**
     * A gauge is a simple metric which is meant to go up and down. If a metric was passed with a given value, but the
     * metric is not passed in the next scrape (for example, a list of entities in a world, but there are no entities
     * of a given type in the next scrape), it will automatically be reset to zero.
     * @since 2.0.0
     */
    GAUGE,

    /**
     * A counter is a metric which is meant to only go up, and not down. An example of a counter metric can be a player
     * join count. You can only increase the value.
     * @since 2.0.0
     */
    COUNTER,
}
