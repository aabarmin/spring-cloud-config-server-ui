package ru.mydesignstudio.config.webui.service;

import com.google.common.base.Preconditions;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import ru.mydesignstudio.config.webui.model.AppEnvironment;
import ru.mydesignstudio.config.webui.repository.EnvironmentsRepository;
import ru.mydesignstudio.config.webui.service.exception.AppEnvironmentKeyDuplicationException;
import ru.mydesignstudio.config.webui.service.exception.AppEnvironmentKeyNotSetException;

import java.util.List;
import java.util.Optional;

/**
 * A service that is responsible for management of environments.
 */
@Service
public class EnvironmentsService {
    @Autowired
    private EnvironmentsRepository environmentsRepository;

    /**
     * Creates a new environment instance.
     * @return
     */
    public AppEnvironment create() {
        return new AppEnvironment();
    }

    /**
     * Find an environment by key.
     *
     * @param key a key to used for finding.
     * @return an optional with an environment.
     */
    public Optional<AppEnvironment> findEnvironment(@NonNull final String key) {
        Preconditions.checkArgument(StringUtils.isNoneEmpty(key), "Key should not be empty");

        return environmentsRepository.findEnvironment(key);
    }

    /**
     * Save an environment.
     *
     * @param environment instance to be saved.
     * @return a saved instance
     */
    public AppEnvironment saveEnvironment(@NonNull final AppEnvironment environment) {
        Preconditions.checkArgument(environment != null, "Environment should not be null");

        if (StringUtils.isEmpty(environment.getKey())) {
            throw new AppEnvironmentKeyNotSetException();
        }

        return environmentsRepository.saveEnvironment(environment);
    }

    /**
     * Get all the registered environments.
     *
     * @return a list of available environments
     */
    public List<AppEnvironment> findAll() {
        return environmentsRepository.findAll();
    }

    /**
     * Deletes an environment.
     *
     * @param environment to be deleted
     * @return true if deleted correctly
     */
    public boolean deleteEnvironment(AppEnvironment environment) {
        return environmentsRepository.deleteEnvironment(environment);
    }
}
