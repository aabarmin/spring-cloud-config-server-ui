package ru.mydesignstudio.config.webui.model;

import lombok.Data;

/**
 * Service of the application.
 */
@Data
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
}
