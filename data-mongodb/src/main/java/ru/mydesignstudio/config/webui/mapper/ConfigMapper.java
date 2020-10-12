package ru.mydesignstudio.config.webui.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.mydesignstudio.config.webui.document.ConfigDocument;
import ru.mydesignstudio.config.webui.model.AppConfig;

@Mapper
public interface ConfigMapper {
    ConfigMapper INSTANCE = Mappers.getMapper(ConfigMapper.class);

    ConfigDocument toDocument(AppConfig config);

    AppConfig toDto(ConfigDocument document);
}
