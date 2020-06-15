package io.serverstatistics.api;

import java.util.SortedMap;

public class RuntimeStatistic {

    private final String sourceName;
    private final String statisticName;
    private final String description;
    private final SortedMap<String, String> tags;
    private final StatisticType type;
    private final Object value;

    public RuntimeStatistic(String sourceName, String statisticName, String description, SortedMap<String, String> tags,
                            StatisticType type, Object value) {
        this.sourceName = sourceName;
        this.statisticName = statisticName;
        this.description = description;
        this.tags = tags;
        this.type = type;
        this.value = value;
    }

    public String getSourceName() {
        return this.sourceName;
    }

    public String getStatisticName() {
        return this.statisticName;
    }

    public String getDescription() {
        return this.description;
    }

    public SortedMap<String, String> getTags() {
        return this.tags;
    }

    public StatisticType getType() {
        return this.type;
    }

    public Object getValue() {
        return this.value;
    }
}
