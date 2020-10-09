package ru.mydesignstudio.config.webui.model;

import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * The main class of the application - it's versioned configuration of the solution.
 */
@Data
@Document("config.versions")
public class AppConfig {
    @Id
    private ObjectId versionId;

    /**
     * Actually, this is a reference to the previous version of the @{@link AppConfig} that is used as a base
     * for the current version of the configuration.
     */
    private ObjectId parentId;
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
