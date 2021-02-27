package io.serverstatistics.api.example.uniquejoincounter.statistics;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import io.serverstatistics.api.ServerStatistic;

public class UniqueJoinCounterStatistic implements ServerStatistic<Integer, Integer>, Listener {

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

    @Override
    public String getName() {
        return "Unique joins";
    }

    @Override
    public String getDescription() {
        return "Counts the unique player joins";
    }

    @Override
    public Integer getValue() {
        return this.uniqueJoins;
    }

    @Override
    public Class<Integer> getValueType() {
        return null;
    }
}
