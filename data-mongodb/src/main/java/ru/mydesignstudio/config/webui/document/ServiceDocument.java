package ru.mydesignstudio.config.webui.document;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("config.services")
public class ServiceDocument {
    /**
     * Unique identifier of the service - machine readable.
     */
    @Id
    private String key;
    /**
     * Human-readable name of the service.
     */
    private String name;
    /**
     * Human-readable description of the service.
     */
    private String description;
}
