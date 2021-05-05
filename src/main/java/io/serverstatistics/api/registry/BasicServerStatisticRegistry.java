package io.serverstatistics.api.registry;

import io.serverstatistics.api.ServerStatistic;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Logger;

public class BasicServerStatisticRegistry implements ServerStatisticRegistry {

    private static final Logger LOGGER = Logger.getLogger("ServerStatistics");

    private static final BasicServerStatisticRegistry i = new BasicServerStatisticRegistry();
    public static BasicServerStatisticRegistry get() { return i; }

    private final Set<ServerStatistic<?, ?>> statistics = new HashSet<>();

    private BasicServerStatisticRegistry() {

    }

    public void addStatistic(ServerStatistic<?, ?> ...statistics) {
        for (ServerStatistic<?, ?> statistic : statistics) {
            LOGGER.info(String.format("Registered statistic '%s'", sanitizeStatisticName(statistic)));
            this.statistics.add(statistic);
        }
    }

    public void removeStatistic(ServerStatistic<?, ?> ...statistics) {
        for (ServerStatistic<?, ?> statistic : statistics) {
            LOGGER.info(String.format("Unregistered statistic '%s'", sanitizeStatisticName(statistic)));
            this.statistics.remove(statistic);
        }
    }

    public Collection<ServerStatistic<?, ?>> getStatistics() {
        return this.statistics;
    }

    private String sanitizeStatisticName(ServerStatistic<?, ?> statistic) {
        return statistic.getName()
                .replace(" ", "-")
                .replace(".", "-")
                .toLowerCase();
    }
}
