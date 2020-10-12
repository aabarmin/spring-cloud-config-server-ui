package ru.mydesignstudio.config.webui.controller.advice;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class EnvironmentControllerAdvice {
    @Value("${app.environment}")
    private String appEnvironment;

    @Value("${app.name}")
    private String appName;

    @ModelAttribute
    public void appEnvironment(ModelAndView modelAndView) {
        modelAndView.addObject("app_environment", appEnvironment);
        modelAndView.addObject("app_name", appName);
    }
}
