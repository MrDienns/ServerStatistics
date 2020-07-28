package io.serverstatistics.api.example.uniquejoincounter;

import io.serverstatistics.api.StatisticRegistry;
import io.serverstatistics.api.example.uniquejoincounter.statistics.UniqueJoinCounterStatistic;
import org.bukkit.plugin.java.JavaPlugin;

public class UniqueJoinCounter extends JavaPlugin {

    @Override
    public void onEnable() {
        // Create a new instance of our statistic class
        UniqueJoinCounterStatistic uniqueJoinCounterStatistic = new UniqueJoinCounterStatistic();

        // Then pass it into the singleton defined StatisticRegistry. For the time being, this must be done during
        // the onEnable sequence.
        StatisticRegistry.get().addStatisticProvider(uniqueJoinCounterStatistic);
    }
}
