package ru.mydesignstudio.config.webui.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.mydesignstudio.config.webui.mapper.ConfigMapper;
import ru.mydesignstudio.config.webui.mapper.EnvironmentMapper;
import ru.mydesignstudio.config.webui.mapper.PropertyMapper;
import ru.mydesignstudio.config.webui.mapper.ServiceMapper;

@Configuration
public class MapperConfig {
    @Bean
    public ConfigMapper configMapper() {
        return ConfigMapper.INSTANCE;
    }

    @Bean
    public EnvironmentMapper environmentMapper() {
        return EnvironmentMapper.INSTANCE;
    }

    @Bean
    public PropertyMapper propertyMapper() {
        return PropertyMapper.INSTANCE;
    }

    @Bean
    public ServiceMapper serviceMapper() {
        return ServiceMapper.INSTANCE;
    }
}
