package ru.mydesignstudio.config.webui.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Service of the application.
 */
public class AppService {
    /**
     * Unique identifier of the service - machine readable.
     */
    @NotNull
    @Size(min = 3, max = 4096)
    private String key;
    /**
     * Human-readable name of the service.
     */
    @NotNull
    @Size(min = 3, max = 4096)
    private String name;
    /**
     * Human-readable description of the service.
     */
    @Size(min = 0, max = 4096)
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
