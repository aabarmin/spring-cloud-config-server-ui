package ru.mydesignstudio.config.webui.model;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Environment that is used to run the application. Also stores overrides for this environment - ex. it's possible
 * having a common value defined in the shared configuration and next override this value for a particular
 * environment.
 */
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

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public Set<AppPropertyValue> getCommon() {
        return common;
    }

    public void setCommon(Set<AppPropertyValue> common) {
        this.common = common;
    }

    public Map<String, Set<AppPropertyValue>> getServices() {
        return services;
    }

    public void setServices(Map<String, Set<AppPropertyValue>> services) {
        this.services = services;
    }
}
