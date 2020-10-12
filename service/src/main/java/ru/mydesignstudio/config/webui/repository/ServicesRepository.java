package ru.mydesignstudio.config.webui.repository;

import org.springframework.lang.NonNull;
import ru.mydesignstudio.config.webui.model.AppService;

import java.util.List;
import java.util.Optional;

/**
 * An abstraction to deal with services. This allows having any implementation of the data storage layer.
 */
public interface ServicesRepository {
    /**
     * Find an instance of a service by its key.
     *
     * @param key of a service to look for.
     * @return an optional with a service.
     */
    Optional<AppService> findService(String key);

    /**
     * Save a service and return a saved instance.
     *
     * @param service to be saved
     * @return a saved instance
     */
    AppService saveService(AppService service);

    /**
     * Find all saved service instances.
     *
     * @return a collection of saved service instances.
     */
    List<AppService> findAll();

    /**
     * Delete an instance of a service.
     *
     * @param service instance to delete
     * @return true if a service was deleted successfully
     */
    boolean deleteService(final AppService service);
}
