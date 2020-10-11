package ru.mydesignstudio.config.webui.repository;

import ru.mydesignstudio.config.webui.model.AppConfig;

import java.util.List;
import java.util.Optional;

/**
 * An abstraction to store configurations. A simple trick to manage config using any kind of storage layer and database.
 */
public interface ConfigRepository {
    /**
     * Find all versions of the configuration.
     *
     * @return all the configurations.
     */
    List<AppConfig> findAll();

    /**
     * Return a config's version by its number.
     *
     * @param number of a version to find
     * @return an optional with a config's version
     */
    Optional<AppConfig> findVersion(int number);

    /**
     * Save a new version of a configuration.
     *
     * @param config to be updated
     * @return a new version of a configuration
     */
    AppConfig saveConfig(AppConfig config);
}
