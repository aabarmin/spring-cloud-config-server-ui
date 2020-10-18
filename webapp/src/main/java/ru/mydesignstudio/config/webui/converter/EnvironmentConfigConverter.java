package ru.mydesignstudio.config.webui.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ru.mydesignstudio.config.webui.model.*;
import ru.mydesignstudio.config.webui.service.PropertiesService;
import ru.mydesignstudio.config.webui.service.ServicesService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class EnvironmentConfigConverter {
    @Value("${app.debug:false}")
    private boolean appDebug;
    @Autowired
    private PropertiesService propertiesService;
    @Autowired
    private PropertyConverter propertyConverter;
    @Autowired
    private ServicesService servicesService;
    @Autowired
    private ServiceConverter serviceConverter;

    public ViewConfig toViewConfig(AppEnvironment environment) {
        final ViewConfig viewConfig = new ViewConfig();
        viewConfig.setDebug(appDebug);
        viewConfig.setProperties(convertProperties());
        viewConfig.setServices(convertServices());
        viewConfig.setCommonConfig(convertCommon(environment));
        viewConfig.setServicesConfig(convertServices(environment));
        return viewConfig;
    }

    public AppEnvironment fromViewConfig(AppEnvironment environment, ViewConfig config) {

        return environment;
    }

    private List<ViewServiceConfig> convertServices(AppEnvironment environment) {
        return environment.getServices()
                .entrySet()
                .stream()
                .map(entry -> {
                    final ViewServiceConfig serviceConfig = new ViewServiceConfig();
                    Optional.ofNullable(entry.getKey())
                            .flatMap(servicesService::findService)
                            .map(serviceConverter::toViewService)
                            .ifPresent(viewService -> serviceConfig.setService(viewService));
                    final List<ViewPropertyValue> properties = entry.getValue()
                            .stream()
                            .map(propertyValue -> {
                                final ViewPropertyValue value = new ViewPropertyValue();
                                Optional.ofNullable(propertyValue.getPropertyKey())
                                        .flatMap(propertiesService::findProperty)
                                        .map(propertyConverter::toViewProperty)
                                        .ifPresent(prop -> value.setProperty(prop));
                                value.setValue(propertyValue.getPropertyValue());
                                return value;
                            })
                            .collect(Collectors.toList());
                    serviceConfig.setProperties(properties);
                    return serviceConfig;
                })
                .collect(Collectors.toList());
    }

    private List<ViewPropertyValue> convertCommon(AppEnvironment environment) {
        return environment.getCommon()
                .stream()
                .map(common -> {
                    final ViewPropertyValue property = new ViewPropertyValue();
                    property.setValue(common.getPropertyValue());
                    propertiesService.findProperty(common.getPropertyKey())
                            .map(propertyConverter::toViewProperty)
                            .ifPresent(viewProperty -> property.setProperty(viewProperty));
                    return property;
                })
                .collect(Collectors.toList());
    }

    private List<ViewService> convertServices() {
        return servicesService.findAll()
                .stream()
                .map(serviceConverter::toViewService)
                .collect(Collectors.toList());
    }

    private List<ViewProperty> convertProperties() {
        return propertiesService.findAll()
                .stream()
                .map(propertyConverter::toViewProperty)
                .collect(Collectors.toList());
    }
}
