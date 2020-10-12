package ru.mydesignstudio.config.webui.document;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashSet;
import java.util.Set;

@Document("config.properties")
public class PropertyDocument {
    /**
     * Property key that is used in the application. It's assumed that this value is unique across the whole
     * configuration or at least has the same meaning for different services.
     */
    @Id
    private String key;
    /**
     * Human-readable name of the property.
     */
    private String name;
    /**
     * Human-readable description of the property.
     */
    private String description;
    /**
     * A small example that describes what values this property can has.
     */
    private String example;
    /**
     * A regular expression that is used for validation of the value for this property.
     */
    private String validationExpression;
    /**
     * This flag shows that this is a common property and it's shared across all the services.
     */
    private boolean commonProperty;
    /**
     * A collection that stores keys of the services that use this property.
     */
    private Set<String> services = new HashSet<>();
}
