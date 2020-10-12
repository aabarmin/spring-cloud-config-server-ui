package ru.mydesignstudio.config.webui.model;

/**
 * Service of the application.
 */
public class AppService {
    /**
     * Unique identifier of the service - machine readable.
     */
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
