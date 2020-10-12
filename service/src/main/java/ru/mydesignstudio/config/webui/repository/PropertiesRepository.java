package ru.mydesignstudio.config.webui.repository;

import ru.mydesignstudio.config.webui.model.AppProperty;

import java.util.List;
import java.util.Optional;

/**
 * Abstraction for the storage layer - it's created to have any implementation of the storage.
 */
public interface PropertiesRepository {
    /**
     * Find all the properties that are registered in the service.
     *
     * @return all the registered properties
     */
    List<AppProperty> findAll();

    /**
     * Find a simple property by its key.
     *
     * @param propertyKey key of the property
     * @return an optional with a property
     */
    Optional<AppProperty> findProperty(String propertyKey);

    /**
     * Save a property to the repository.
     *
     * @param property a property to be saved
     * @return saved property
     */
    AppProperty saveProperty(AppProperty property);

    /**
     * Deletes a property.
     *
     * @param property to be deleted
     * @return true if a property was deleted
     */
    boolean deleteProperty(AppProperty property);
}
