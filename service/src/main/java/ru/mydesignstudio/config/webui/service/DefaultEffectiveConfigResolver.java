package ru.mydesignstudio.config.webui.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import ru.mydesignstudio.config.webui.model.AppConfig;
import ru.mydesignstudio.config.webui.model.AppEnvironment;
import ru.mydesignstudio.config.webui.model.AppPropertyValue;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Basic implementation of a configuration resolver.
 */
@Component
public class DefaultEffectiveConfigResolver implements EffectiveConfigResolver {
    @Autowired
    private ConfigService configService;

    /**
     * @{inherited}
     */
    @Override
    public Set<AppPropertyValue> resolve(final @NonNull AppEnvironment environment, final @NonNull String service) {
        final Map<String, String> values = new LinkedHashMap<>();

        final Optional<AppConfig> config = configService.findVersion(environment.getVersion());

        // take all properties from config's common
        config.map(AppConfig::getCommon)
                .orElse(Set.of())
                .stream()
                .forEach(property -> values.put(property.getPropertyKey(), property.getPropertyValue()));

        // take all properties from config's for a given service
        config.map(AppConfig::getServices)
                .orElse(Map.of())
                .getOrDefault(service, Set.of())
                .stream()
                .forEach(property -> values.put(property.getPropertyKey(), property.getPropertyValue()));

        // take all props from env commons
        environment.getCommon()
                .forEach(property -> values.put(property.getPropertyKey(), property.getPropertyValue()));

        // take all props from env service
        environment.getServices()
                .getOrDefault(service, Set.of())
                .forEach(propery -> values.put(propery.getPropertyKey(), propery.getPropertyValue()));


        return values.entrySet()
                .stream()
                .map(this::toPropertyValue)
                .collect(Collectors.toSet());
    }

    private AppPropertyValue toPropertyValue(Map.Entry<String, String> entry) {
        final AppPropertyValue property = new AppPropertyValue();
        property.setPropertyKey(entry.getKey());
        property.setPropertyValue(entry.getValue());
        return property;
    }
}
