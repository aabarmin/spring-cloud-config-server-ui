package ru.mydesignstudio.config.webui.converter;

import org.springframework.stereotype.Component;
import ru.mydesignstudio.config.webui.model.AppService;
import ru.mydesignstudio.config.webui.model.ViewService;

@Component
public class ServiceConverter {
    public ViewService toViewService(AppService appService) {
        final ViewService viewService = new ViewService();
        viewService.setKey(appService.getKey());
        viewService.setName(appService.getName());
        viewService.setDescription(appService.getDescription());
        return viewService;
    }
}
