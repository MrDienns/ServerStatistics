package io.serverstatistics.api.registry;

import io.serverstatistics.api.ServerStatistic;

import java.util.Collection;

public interface ServerStatisticRegistry {

    void addStatistic(ServerStatistic<?, ?>...statistics);
    void removeStatistic(ServerStatistic<?, ?> ...statistics);
    Collection<ServerStatistic<?, ?>> getStatistics();
}
