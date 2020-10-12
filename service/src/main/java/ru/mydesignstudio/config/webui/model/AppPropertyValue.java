package ru.mydesignstudio.config.webui.model;

/**
 * DTO to store property values.
 */
public class AppPropertyValue {
    /**
     * Property this value belongs to.
     */
    private String propertyKey;
    /**
     * Value of the property.
     */
    private String propertyValue;

    public String getPropertyKey() {
        return propertyKey;
    }

    public void setPropertyKey(String propertyKey) {
        this.propertyKey = propertyKey;
    }

    public String getPropertyValue() {
        return propertyValue;
    }

    public void setPropertyValue(String propertyValue) {
        this.propertyValue = propertyValue;
    }
}
