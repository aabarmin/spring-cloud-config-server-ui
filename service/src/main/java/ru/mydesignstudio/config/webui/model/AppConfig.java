package ru.mydesignstudio.config.webui.model;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * The main class of the application - it's versioned configuration of the solution.
 */
public class AppConfig {
    /**
     * Number of the basic version
     */
    private Integer parentId;
    /**
     * Version number.
     */
    private int version;
    /**
     * Date and time when this version was created.
     */
    private LocalDateTime created;
    /**
     * Config values shared across all the services.
     */
    private Set<AppPropertyValue> common = new HashSet<>();
    /**
     * Config values for services. 
     */
    private Map<String, Set<AppPropertyValue>> services = new HashMap<>();

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
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
