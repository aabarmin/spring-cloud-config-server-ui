package ru.mydesignstudio.config.webui.model;

import java.util.ArrayList;
import java.util.List;

public class ViewConfig {
    private boolean debug;
    private List<ViewProperty> properties = new ArrayList<>();
    private List<ViewService> services = new ArrayList<>();
    private List<ViewPropertyValue> commonConfig = new ArrayList<>();
    private List<ViewServiceConfig> servicesConfig = new ArrayList<>();

    public boolean isDebug() {
        return debug;
    }

    public void setDebug(boolean debug) {
        this.debug = debug;
    }

    public List<ViewProperty> getProperties() {
        return properties;
    }

    public void setProperties(List<ViewProperty> properties) {
        this.properties = properties;
    }

    public List<ViewService> getServices() {
        return services;
    }

    public void setServices(List<ViewService> services) {
        this.services = services;
    }

    public List<ViewPropertyValue> getCommonConfig() {
        return commonConfig;
    }

    public void setCommonConfig(List<ViewPropertyValue> commonConfig) {
        this.commonConfig = commonConfig;
    }

    public List<ViewServiceConfig> getServicesConfig() {
        return servicesConfig;
    }

    public void setServicesConfig(List<ViewServiceConfig> servicesConfig) {
        this.servicesConfig = servicesConfig;
    }
}
