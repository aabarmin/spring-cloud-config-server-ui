package ru.mydesignstudio.config.webui.model;

import lombok.Data;

import java.util.HashSet;
import java.util.Set;

/**
 * Property that is used to configure the application.
 */
@Data
public class AppProperty {
    /**
     * Property key that is used in the application. It's assumed that this value is unique across the whole
     * configuration or at least has the same meaning for different services.
     */
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
    /**
     * What data type should be used to store the value.
     */
    private PropertyType propertyType;
}
