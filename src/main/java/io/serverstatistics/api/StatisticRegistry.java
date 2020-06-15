package io.serverstatistics.api;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class StatisticRegistry {

    private static final StatisticRegistry i = new StatisticRegistry();
    public static StatisticRegistry get() { return i; }

    private final Set<StatisticProvider> providers = new HashSet<>();
    private final Map<Method, StatisticProvider> methodInstanceLink = new HashMap<>();

    private StatisticRegistry() {

    }

    public void addStatisticProvider(StatisticProvider provider) {
        this.providers.add(provider);
        this.getInstancedStatisticMethodsFromProvider(provider).forEach(m -> this.methodInstanceLink.put(m, provider));
    }

    public Set<StatisticProvider> getProviders() {
        return this.providers;
    }

    public StatisticProvider getProviderInstanceFromMethod(Method method) {
        return this.methodInstanceLink.get(method);
    }

    private List<Method> getInstancedStatisticMethodsFromProvider(StatisticProvider provider) {
        return Arrays.stream(provider.getClass().getMethods())
                .filter(m -> m.isAnnotationPresent(ServerStatistic.class))
                .filter(m -> !Modifier.isStatic(m.getModifiers()))
                .collect(Collectors.toList());
    }
}
