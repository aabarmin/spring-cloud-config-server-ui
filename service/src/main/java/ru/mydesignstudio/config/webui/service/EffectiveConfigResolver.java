package ru.mydesignstudio.config.webui.service;

import ru.mydesignstudio.config.webui.model.AppEnvironment;
import ru.mydesignstudio.config.webui.model.AppPropertyValue;

import java.util.Set;

/**
 * This component resolves a configuration for the given environment.
 */
public interface EffectiveConfigResolver {
    /**
     * Resolve a configuration for the given environment and service.
     *
     * @param environment instance to resolve configuration for
     * @param service to compute configuration for.
     * @return a collection of properties that belongs to the given config and environment
     */
    Set<AppPropertyValue> resolve(AppEnvironment environment, String service);
}
