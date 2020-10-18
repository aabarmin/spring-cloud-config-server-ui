package ru.mydesignstudio.config.webui.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.mydesignstudio.config.webui.converter.EnvironmentConfigConverter;
import ru.mydesignstudio.config.webui.model.AppEnvironment;
import ru.mydesignstudio.config.webui.model.ViewConfig;
import ru.mydesignstudio.config.webui.service.EnvironmentsService;

@RestController
public class EnvironmentConfigurationController {
    @Autowired
    private EnvironmentsService environmentsService;
    @Autowired
    private EnvironmentConfigConverter configConverter;

    @GetMapping("/environments/{environment-name}/config")
    public ViewConfig getConfigForEnvironment(@PathVariable("environment-name") String environmentName) {
        return environmentsService.findEnvironment(environmentName)
                .map(configConverter::toViewConfig)
                .orElseThrow(() -> new RuntimeException(String.format(
                        "Can't find environment with key %s",
                        environmentName
                )));
    }

    @PostMapping("/environments/{environment-name}/config")
    public boolean saveConfigForEnvironment(@PathVariable("environment-name") String environmentName,
                                            @RequestBody ViewConfig config) {

        environmentsService.findEnvironment(environmentName)
                .map(appEnvironment -> updateEnvironment(appEnvironment, config))
                .map(environmentsService::saveEnvironment);
        return true;
    }

    private AppEnvironment updateEnvironment(AppEnvironment appEnvironment, ViewConfig config) {
        return appEnvironment;
    }
}
