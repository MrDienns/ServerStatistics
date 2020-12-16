package io.serverstatistics.api;

import java.util.Collection;

public interface ServerStatisticRegistry {

    void addStatistic(ServerStatistic<?, ?> ...statistics);
    void removeStatistic(ServerStatistic<?, ?> ...statistics);
    Collection<ServerStatistic<?, ?>> getStatistics();
}
