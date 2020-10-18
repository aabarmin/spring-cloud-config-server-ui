package ru.mydesignstudio.config.webui.model;

import java.util.ArrayList;
import java.util.List;

public class ViewServiceConfig {
    private ViewService service;
    private List<ViewPropertyValue> properties = new ArrayList<>();

    public ViewService getService() {
        return service;
    }

    public void setService(ViewService service) {
        this.service = service;
    }

    public List<ViewPropertyValue> getProperties() {
        return properties;
    }

    public void setProperties(List<ViewPropertyValue> properties) {
        this.properties = properties;
    }
}
