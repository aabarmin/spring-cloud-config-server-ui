package ru.mydesignstudio.config.webui.model;

import lombok.Data;

/**
 * DTO to store property values.
 */
@Data
public class AppPropertyValue {
    /**
     * Property this value belongs to.
     */
    private String propertyKey;
    /**
     * Value of the property.
     */
    private String propertyValue;
}
