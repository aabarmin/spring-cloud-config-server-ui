package ru.mydesignstudio.config.webui.service;

import com.google.common.base.Preconditions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import ru.mydesignstudio.config.webui.model.AppConfig;
import ru.mydesignstudio.config.webui.model.AppEnvironment;
import ru.mydesignstudio.config.webui.repository.ConfigRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ConfigService {
    @Autowired
    private ConfigRepository configRepository;
    @Autowired
    private EnvironmentsService environmentsService;

    /**
     * Find all versions of the configuration.
     *
     * @return all the configurations.
     */
    public List<AppConfig> findAll() {
        return configRepository.findAll();
    }

    /**
     * Return a config's version by its number.
     *
     * @param number of a version to find
     * @return an optional with a config's version
     */
    public Optional<AppConfig> findVersion(int number) {
        Preconditions.checkArgument(number > 0, "Version number should be more than zero");

        return configRepository.findVersion(number);
    }

    /**
     * Save a new version of a configuration.
     *
     * @param config to be updated
     * @return a new version of a configuration
     */
    public AppConfig saveConfig(final @NonNull AppConfig config) {
        Preconditions.checkArgument(config != null, "Config should not be null");

        final AppConfig savedConfig = new AppConfig();
        savedConfig.setCreated(LocalDateTime.now());
        savedConfig.setVersion(config.getVersion() + 1);
        savedConfig.setParentId(config.getVersion());
        savedConfig.setCommon(config.getCommon());
        savedConfig.setServices(config.getServices());

        return configRepository.saveConfig(savedConfig);
    }

    /**
     * Promote a given configuration to the given environment. The method returns the version of an assinged config.
     *
     * @param config a config to be promoted
     * @param environment to be used with this config
     * @return a version of a promoted config
     */
    public int promote(@NonNull AppConfig config, @NonNull AppEnvironment environment) {
        Preconditions.checkArgument(config != null, "Config should not be null");
        Preconditions.checkArgument(environment != null, "Environment should not be null");

        environment.setVersion(config.getVersion());
        environmentsService.saveEnvironment(environment);
        return config.getVersion();
    }
}
