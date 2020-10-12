package ru.mydesignstudio.config.webui.service;

import com.google.common.base.Preconditions;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import ru.mydesignstudio.config.webui.model.AppProperty;
import ru.mydesignstudio.config.webui.repository.PropertiesRepository;
import ru.mydesignstudio.config.webui.service.exception.AppPropertyKeyDuplicationException;
import ru.mydesignstudio.config.webui.service.exception.AppPropertyKeyNotSetException;

import java.util.List;
import java.util.Optional;

/**
 * A simple service that is dealing with all the registered properties.
 */
@Service
public class PropertiesService {
    private static final Logger LOGGER = LoggerFactory.getLogger(PropertiesService.class);

    @Autowired
    private PropertiesRepository propertiesRepository;

    /**
     * Create a new simple property.
     *
     * @return a new property
     */
    public AppProperty createProperty() {
        return new AppProperty();
    }

    /**
     * Find all saved properties.
     *
     * @return a collection of saved properties.
     */
    public List<AppProperty> findAll() {
        return propertiesRepository.findAll();
    }

    /**
     * Find one single property by its key.
     *
     * @param propertyKey a key of the property to look for.
     * @return an optional with a property
     */
    public Optional<AppProperty> findProperty(@NonNull final String propertyKey) {
        Preconditions.checkArgument(StringUtils.isNoneEmpty(propertyKey), "Property should not be empty");

        LOGGER.info("Looking for the property with key {}", propertyKey);

        return propertiesRepository.findProperty(propertyKey);
    }

    /**
     * Save a property.
     *
     * @param property to be saved
     * @return saved property
     */
    public AppProperty saveProperty(@NonNull final AppProperty property) {
        Preconditions.checkArgument(property != null, "Property should not be empty");

        if (StringUtils.isEmpty(property.getKey())) {
            throw new AppPropertyKeyNotSetException();
        }

        return propertiesRepository.saveProperty(property);
    }

    /**
     * Deletes a property.
     *
     * @param property to be deleted
     * @return true if a property was deleted
     */
    public boolean deleteProperty(AppProperty property) {
        return propertiesRepository.deleteProperty(property);
    }
}
