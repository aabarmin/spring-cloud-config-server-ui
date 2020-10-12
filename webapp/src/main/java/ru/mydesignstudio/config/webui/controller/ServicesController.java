package ru.mydesignstudio.config.webui.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import ru.mydesignstudio.config.webui.model.AppService;
import ru.mydesignstudio.config.webui.service.ServicesService;

import javax.validation.Valid;

@Controller
public class ServicesController {
    @Autowired
    private ServicesService servicesService;

    @ModelAttribute
    public void setAttributes(ModelAndView modelAndView) {
        modelAndView.addObject("nav_active", "services");
        modelAndView.addObject("edit_key_disabled", false);
    }

    @GetMapping("/services")
    public ModelAndView index(ModelAndView modelAndView) {
        modelAndView.setViewName("services/index");
        modelAndView.addObject("services", servicesService.findAll());
        return modelAndView;
    }

    @GetMapping("/services/new")
    public ModelAndView create(ModelAndView modelAndView) {
        modelAndView.setViewName("services/edit");
        modelAndView.addObject("service", servicesService.createService());
        return modelAndView;
    }

    @GetMapping("/services/{key}")
    public ModelAndView edit(ModelAndView modelAndView, @PathVariable("key") String key) {
        modelAndView.setViewName("services/edit");
        modelAndView.addObject("edit_key_disabled", true);
        servicesService.findService(key)
                .ifPresent(service -> modelAndView.addObject("service", service));
        return modelAndView;
    }

    @GetMapping("/services/{key}/delete")
    public ModelAndView delete(ModelAndView modelAndView, @PathVariable("key") String key) {
        servicesService.findService(key)
                .map(service -> servicesService.deleteService(service));

        modelAndView.setViewName("redirect:/services");
        return modelAndView;
    }

    @PostMapping("/services")
    public ModelAndView save(ModelAndView modelAndView, @Valid @ModelAttribute("service") AppService service, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("services/edit");
            modelAndView.addObject("service", service);
            return modelAndView;
        }

        servicesService.saveService(service);

        return new ModelAndView("redirect:/services", modelAndView.getModel());
    }
}
