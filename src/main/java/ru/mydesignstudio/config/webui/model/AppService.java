package ru.mydesignstudio.config.webui.model;

import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Service of the application.
 */
@Data
@Document("config.service")
public class AppService {
    @Id
    private ObjectId serviceId;

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
