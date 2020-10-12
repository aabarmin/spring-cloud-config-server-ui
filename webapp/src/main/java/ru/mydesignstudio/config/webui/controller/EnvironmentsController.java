package ru.mydesignstudio.config.webui.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import ru.mydesignstudio.config.webui.model.AppEnvironment;
import ru.mydesignstudio.config.webui.service.EnvironmentsService;

import javax.validation.Valid;

@Controller
public class EnvironmentsController {
    @Autowired
    private EnvironmentsService environmentsService;

    @ModelAttribute
    public void setAttributes(ModelAndView modelAndView) {
        modelAndView.addObject("nav_active", "environments");
        modelAndView.addObject("edit_key_disabled", false);
    }

    @GetMapping("/environments")
    public ModelAndView index(ModelAndView modelAndView) {
        modelAndView.setViewName("environments/index");
        modelAndView.addObject("environments", environmentsService.findAll());
        return modelAndView;
    }

    @GetMapping("/environments/new")
    public ModelAndView create(ModelAndView modelAndView) {
        modelAndView.setViewName("environments/edit");
        modelAndView.addObject("environment", environmentsService.create());
        return modelAndView;
    }

    @GetMapping("/environments/{key}")
    public ModelAndView edit(ModelAndView modelAndView, @PathVariable("key") String key) {
        modelAndView.setViewName("environments/edit");
        modelAndView.addObject("edit_key_disabled", true);
        environmentsService.findEnvironment(key)
                .ifPresent(environments -> modelAndView.addObject("environment", environments));
        return modelAndView;
    }

    @GetMapping("/environments/{key}/delete")
    public ModelAndView delete(ModelAndView modelAndView, @PathVariable("key") String key) {
        environmentsService.findEnvironment(key)
                .map(environment -> environmentsService.deleteEnvironment(environment));

        modelAndView.setViewName("redirect:/environments");
        return modelAndView;
    }

    @PostMapping("/environments")
    public ModelAndView save(ModelAndView modelAndView, @Valid @ModelAttribute("environment") AppEnvironment environment, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("environments/edit");
            modelAndView.addObject("environment", environment);
            return modelAndView;
        }

        environmentsService.saveEnvironment(environment);

        return new ModelAndView("redirect:/environments", modelAndView.getModel());
    }
}
