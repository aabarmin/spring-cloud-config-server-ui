package ru.mydesignstudio.config.webui.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.mydesignstudio.config.webui.document.PropertyDocument;
import ru.mydesignstudio.config.webui.model.AppProperty;

@Mapper
public interface PropertyMapper {
    PropertyMapper INSTANCE = Mappers.getMapper(PropertyMapper.class);

    PropertyDocument toDocument(AppProperty property);

    AppProperty toDto(PropertyDocument document);
}
