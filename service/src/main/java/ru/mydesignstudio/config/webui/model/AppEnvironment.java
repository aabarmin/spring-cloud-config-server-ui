package ru.mydesignstudio.config.webui.model;

import lombok.Data;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Environment that is used to run the application. Also stores overrides for this environment - ex. it's possible
 * having a common value defined in the shared configuration and next override this value for a particular
 * environment.
 */
@Data
public class AppEnvironment {
    /**
     * Environment key, machine-readable identifier.
     */
    private String key;
    /**
     * Human-readable name
     */
    private String name;
    /**
     * Human-readable description.
     */
    private String description;
    /**
     * Version of the configuration is used for on this environment.
     */
    private int version;
    /**
     * Common overrides for the environment.
     */
    private Set<AppPropertyValue> common = new HashSet<>();
    /**
     * Service overrides for the environment.
     */
    private Map<String, Set<AppPropertyValue>> services = new HashMap<>();
}
