package io.serverstatistics.api.example.uniquejoincounter;

import org.bukkit.plugin.java.JavaPlugin;

import io.serverstatistics.api.BasicServerStatisticRegistry;
import io.serverstatistics.api.example.uniquejoincounter.statistics.UniqueJoinCounterStatistic;

public class UniqueJoinCounter extends JavaPlugin {

    @Override
    public void onEnable() {
        // Create a new instance of our statistic class
        UniqueJoinCounterStatistic uniqueJoinCounterStatistic = new UniqueJoinCounterStatistic();

        // Then pass it into the singleton defined StatisticRegistry. For the time being, this must be done during
        // the onEnable sequence.
        BasicServerStatisticRegistry.get().addStatistic(uniqueJoinCounterStatistic);
        this.getServer().getPluginManager().registerEvents(uniqueJoinCounterStatistic, this);
    }
}
