package io.serverstatistics.api;

public interface StatisticProvider {

    default boolean shouldEnable() {
        return true;
    }
}
