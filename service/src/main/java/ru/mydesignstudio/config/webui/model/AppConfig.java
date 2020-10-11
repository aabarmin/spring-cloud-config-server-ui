package ru.mydesignstudio.config.webui.model;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * The main class of the application - it's versioned configuration of the solution.
 */
@Data
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
}
