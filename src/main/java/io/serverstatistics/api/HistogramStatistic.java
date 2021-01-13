package io.serverstatistics.api;

import java.util.Collection;

public interface HistogramStatistic extends ServerStatistic<Number, Collection<Number>> {

    @Override
    default Class<Number> getValueType() {
        return Number.class;
    }
}
