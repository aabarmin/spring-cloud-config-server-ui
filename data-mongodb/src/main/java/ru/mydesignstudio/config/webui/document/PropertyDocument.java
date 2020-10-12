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

    public String getExample() {
        return example;
    }

    public void setExample(String example) {
        this.example = example;
    }

    public String getValidationExpression() {
        return validationExpression;
    }

    public void setValidationExpression(String validationExpression) {
        this.validationExpression = validationExpression;
    }

    public boolean isCommonProperty() {
        return commonProperty;
    }

    public void setCommonProperty(boolean commonProperty) {
        this.commonProperty = commonProperty;
    }

    public Set<String> getServices() {
        return services;
    }

    public void setServices(Set<String> services) {
        this.services = services;
    }
}
