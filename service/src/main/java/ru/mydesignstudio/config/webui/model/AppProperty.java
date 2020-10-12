package ru.mydesignstudio.config.webui.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

/**
 * Property that is used to configure the application.
 */
public class AppProperty {
    /**
     * Property key that is used in the application. It's assumed that this value is unique across the whole
     * configuration or at least has the same meaning for different services.
     */
    @NotNull
    @Size(min = 3, max = 4096)
    private String key;
    /**
     * Human-readable name of the property.
     */
    @NotNull
    @Size(min = 3, max = 4096)
    private String name;
    /**
     * Human-readable description of the property.
     */
    @Size(max = 4096)
    private String description;
    /**
     * A small example that describes what values this property can has.
     */
    @Size(max = 4096)
    private String example;
    /**
     * A regular expression that is used for validation of the value for this property.
     */
    @Size(max = 4096)
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

    public PropertyType getPropertyType() {
        return propertyType;
    }

    public void setPropertyType(PropertyType propertyType) {
        this.propertyType = propertyType;
    }
}
