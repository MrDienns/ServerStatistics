package io.serverstatistics.api.example.uniquejoincounter.statistics;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

/**
 * The UniqueJoinCounter implements {@link StatisticProvider} so it can automatically be picked up by ServerStatistcs.
 * We have no additional criteria for whether or not this provider should be enabled or not, we don't override the
 * {@link #shouldEnable()} function. We also implement {@link Listener} so we can listen for some events we need for
 * this statistic.
 */
public class UniqueJoinCounterStatistic implements StatisticProvider, Listener {

    private int uniqueJoins;

    public UniqueJoinCounterStatistic() {
        this.uniqueJoins = 0;
    }

    /**
     * In here, you want to implement whatever logic you need to determine whether or not the player is unique. This
     * strictly differs per server; some use the built-in Bukkit mechanism, some use databases, some use a restful API,
     * etc. Here I use the built-in Bukkit mechanism, but you get the point.
     * @param event {@link PlayerJoinEvent e}
     * @return boolean
     */
    private boolean isUnique(PlayerJoinEvent event) {
        return !event.getPlayer().hasPlayedBefore();
    }

    /**
     * Listen for incoming {@link PlayerJoinEvent} events, and if our player is unique, we increase our unique join
     * counter.
     * @param event {@link PlayerJoinEvent}
     */
    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void onJoin(PlayerJoinEvent event) {
        if (this.isUnique(event)) {
            this.uniqueJoins++;
        }
    }

    /**
     * This function will be called by ServerStatistics. The implementation of the {@link StatisticProvider}
     * interface plus methods on this class with the {@link ServerStatistic} annotation, causes ServerStatistics to
     * automatically find and register this metric.
     * @return int
     */
    @ServerStatistic(name = "Unique joins", description = "Keeps track of the new players that joined the server",
            type = StatisticType.COUNTER)
    public int getUniqueJoins() {
        return this.uniqueJoins;
    }

    /**
     * With the {@link ServerTag} annotation, you can return compiled tags. You may return anything here. It will show
     * up in the metrics as selectable filters.
     * @return String
     */
    @ServerTag(name = "Version")
    public String getVersionTag() {
        return "v1.0.0";
    }
}
