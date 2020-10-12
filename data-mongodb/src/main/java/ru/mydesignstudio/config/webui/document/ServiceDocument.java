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
}
