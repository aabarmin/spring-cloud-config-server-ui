package ru.mydesignstudio.config.webui.repository;

import ru.mydesignstudio.config.webui.model.AppEnvironment;

import java.util.List;
import java.util.Optional;

/**
 * An abstraction to deal with the storage level of environments.
 */
public interface EnvironmentsRepository {
    /**
     * Find an environment by key.
     *
     * @param key an environment's key to find.
     * @return an optional with @{@link AppEnvironment}
     */
    Optional<AppEnvironment> findEnvironment(String key);

    /**
     * Save an environment and return a saved instance.
     *
     * @param environment an instance to save
     * @return saved instance
     */
    AppEnvironment saveEnvironment(AppEnvironment environment);

    /**
     * Get all the registered environments.
     *
     * @return a list of available environments
     */
    List<AppEnvironment> findAll();

    /**
     * Deletes an environment.
     *
     * @param environment to be deleted
     * @return true if deleted correctly
     */
    boolean deleteEnvironment(AppEnvironment environment);
}
