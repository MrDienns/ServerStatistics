package io.serverstatistics.api;

/**
 * A simple interface defining a server wide statistic. Can be implemented in order to create your own statistics which
 * will be exposed through whatever output plugin is configured.
 * @param <Value> A generic parameterized value indicating what kind of object this statistic returns. Numeric objects
 *               or collections containing other objects may be returned. Configured mapping will change how the
 *               objects (non-numeric ones) are mapped into the output plugins.
 */
public interface ServerStatistic<ValueType, Value> {

    /**
     * Returns the simple name of this statistic. This name will be prefixed by the type of server, such as "Spigot"
     * or "Bungeecord".
     * @return {@link String} - The name of this statistic.
     */
    String getName();

    /**
     * A short description of what this statistic returns.
     * @return {@link String} - The description of this statistic.
     */
    String getDescription();

    /**
     *
     * @return {@link Value} The value generic of this statistic.
     */
    Value getValue();

    /**
     *
     * @return {@link Class<ValueType>} The value generic of this statistic.
     */
    Class<ValueType> getValueType();

    /**
     * Called when the statistic is enabled.
     */
    default void onEnable() {

    }
}
