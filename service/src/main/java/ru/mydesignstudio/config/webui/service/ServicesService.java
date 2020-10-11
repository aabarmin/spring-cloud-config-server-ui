package ru.mydesignstudio.config.webui.service;

import com.google.common.base.Preconditions;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import ru.mydesignstudio.config.webui.model.AppService;
import ru.mydesignstudio.config.webui.repository.ServicesRepository;
import ru.mydesignstudio.config.webui.service.exception.AppServiceKeyDuplicationException;
import ru.mydesignstudio.config.webui.service.exception.AppServiceKeyNotSetException;

import java.util.List;
import java.util.Optional;

/**
 * A service to manage services of the application.
 */
@Service
public class ServicesService {
    @Autowired
    private ServicesRepository servicesRepository;

    /**
     * Create a new service instance.
     *
     * @return a new instance of a service.
     */
    public AppService createService() {
        return new AppService();
    }

    /**
     * Find all saved service instances.
     *
     * @return a collection of saved service instances.
     */
    public List<AppService> findAll() {
        return servicesRepository.findAll();
    }

    /**
     * Find an instance of a service by its key.
     *
     * @param key of a service to look for.
     * @return an optional with a service.
     */
    public Optional<AppService> findService(@NonNull final String key) {
        Preconditions.checkArgument(StringUtils.isNoneEmpty(key), "Key should not be empty");

        return servicesRepository.findService(key);
    }

    /**
     * Save a service and return a saved instance.
     *
     * @param service to be saved
     * @return a saved instance
     */
    public AppService saveService(@NonNull final AppService service) {
        Preconditions.checkArgument(service != null, "Service should not be null");

        if (StringUtils.isEmpty(service.getKey())) {
            throw new AppServiceKeyNotSetException();
        }

        if (servicesRepository.findService(service.getKey()).isPresent()) {
            throw new AppServiceKeyDuplicationException();
        }

        return servicesRepository.saveService(service);
    }
}
