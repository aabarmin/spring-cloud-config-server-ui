package ru.mydesignstudio.config.webui.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import ru.mydesignstudio.config.webui.model.AppProperty;
import ru.mydesignstudio.config.webui.model.AppService;
import ru.mydesignstudio.config.webui.service.PropertiesService;
import ru.mydesignstudio.config.webui.service.ServicesService;

import javax.validation.Valid;

@Controller
public class PropertiesController {
    @Autowired
    private PropertiesService propertiesService;

    @Autowired
    private ServicesService servicesService;

    @ModelAttribute
    public void setAttributes(ModelAndView modelAndView) {
        modelAndView.addObject("nav_active", "properties");
        modelAndView.addObject("edit_key_disabled", false);
        modelAndView.addObject("services", servicesService.findAll());
    }

    @GetMapping("/properties")
    public ModelAndView index(ModelAndView modelAndView) {
        modelAndView.setViewName("properties/index");
        modelAndView.addObject("properties", propertiesService.findAll());
        return modelAndView;
    }

    @GetMapping("/properties/new")
    public ModelAndView create(ModelAndView modelAndView) {
        modelAndView.setViewName("properties/edit");
        modelAndView.addObject("property", propertiesService.createProperty());
        return modelAndView;
    }

    @GetMapping("/properties/{key}")
    public ModelAndView edit(ModelAndView modelAndView, @PathVariable("key") String key) {
        modelAndView.setViewName("properties/edit");
        modelAndView.addObject("edit_key_disabled", true);
        propertiesService.findProperty(key)
                .ifPresent(property -> modelAndView.addObject("property", property));
        return modelAndView;
    }

    @GetMapping("/properties/{key}/delete")
    public ModelAndView delete(ModelAndView modelAndView, @PathVariable("key") String key) {
        propertiesService.findProperty(key)
                .map(property -> propertiesService.deleteProperty(property));

        modelAndView.setViewName("redirect:/properties");
        return modelAndView;
    }

    @PostMapping("/properties")
    public ModelAndView save(ModelAndView modelAndView, @Valid @ModelAttribute("property") AppProperty property, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("properties/edit");
            modelAndView.addObject("property", property);
            return modelAndView;
        }

        propertiesService.saveProperty(property);

        return new ModelAndView("redirect:/properties", modelAndView.getModel());
    }
}
