package io.serverstatistics.api;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * The {@link StatisticRegistry} is a singleton class designed to keep all metrics in place. This class keeps track of
 * the registered {@link StatisticProvider} classes. This is used internally by the ServerStatistics plugin to load
 * which metrics exist at runtime.
 */
public class StatisticRegistry {

    private static final StatisticRegistry i = new StatisticRegistry();
    public static StatisticRegistry get() { return i; }

    private final Set<StatisticProvider> providers = new HashSet<>();
    private final Map<Method, StatisticProvider> methodInstanceLink = new HashMap<>();

    private StatisticRegistry() {

    }

    /**
     * Register a {@link StatisticProvider} implementation. The passed class will be scanned for methods, and any
     * methods annotated with the {@link ServerStatistic} tag will be registered.
     * @param provider {@link StatisticProvider}
     */
    public void addStatisticProvider(StatisticProvider provider) {
        this.providers.add(provider);
        this.getAnnotatedMethodsFromProvider(provider, ServerStatistic.class).forEach(m -> this.methodInstanceLink.put(m, provider));
        this.getAnnotatedMethodsFromProvider(provider, ServerTag.class).forEach(m -> this.methodInstanceLink.put(m, provider));
    }

    /**
     * Removes all registered {@link StatisticProvider} classes.
     */
    public void removeAllStatisticProviders() {
        this.providers.clear();
    }

    /**
     * Returns a {@link Set<StatisticProvider>} with all registered {@link StatisticProvider} implementations.
     * @return {@link Set<StatisticProvider>}
     */
    public Set<StatisticProvider> getProviders() {
        return this.providers;
    }

    /**
     * Returns the {@link StatisticProvider} associated with the passed {@link Method} (annotated with
     * {@link ServerStatistic}) instance.
     * @param method {@link Method}
     * @return {@link StatisticProvider}
     */
    public StatisticProvider getProviderInstanceFromMethod(Method method) {
        return this.methodInstanceLink.get(method);
    }

    /**
     * Returns a list of {@link Method} which are detected as annotated {@link ServerStatistic} methods.
     * @param provider {@link ServerStatistic}
     * @return {@link List<Method>}
     */
    private List<Method> getAnnotatedMethodsFromProvider(StatisticProvider provider, Class<? extends Annotation> annotation) {
        return Arrays.stream(provider.getClass().getMethods())
                .filter(m -> m.isAnnotationPresent(annotation))
                .filter(m -> !Modifier.isStatic(m.getModifiers()))
                .collect(Collectors.toList());
    }
}
