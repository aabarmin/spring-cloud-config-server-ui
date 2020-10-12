package ru.mydesignstudio.config.webui.document;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import ru.mydesignstudio.config.webui.model.AppPropertyValue;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Document("config.versions")
public class ConfigDocument {
    /**
     * Number of the basic version
     */
    private Integer parentId;
    /**
     * Version number.
     */
    @Id
    private String version;
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
