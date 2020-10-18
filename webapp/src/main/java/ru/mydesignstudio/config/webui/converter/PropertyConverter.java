package ru.mydesignstudio.config.webui.converter;

import com.google.common.collect.Lists;
import org.springframework.stereotype.Component;
import ru.mydesignstudio.config.webui.model.AppProperty;
import ru.mydesignstudio.config.webui.model.ViewProperty;

@Component
public class PropertyConverter {
    public ViewProperty toViewProperty(AppProperty property) {
        final ViewProperty viewProperty = new ViewProperty();
        viewProperty.setKey(property.getKey());
        viewProperty.setDescription(property.getDescription());
        viewProperty.setCommon(property.isCommonProperty());
        viewProperty.setServices(Lists.newArrayList(property.getServices()));
        return viewProperty;
    }
}
