package ru.mydesignstudio.config.webui.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import ru.mydesignstudio.config.webui.document.EnvironmentDocument;
import ru.mydesignstudio.config.webui.model.AppEnvironment;

@Mapper
public interface EnvironmentMapper {
    EnvironmentMapper INSTANCE = Mappers.getMapper(EnvironmentMapper.class);

    AppEnvironment toDto(EnvironmentDocument document);

//    @Mapping(source = "key", target = "key")
    EnvironmentDocument toDocument(AppEnvironment environment);
}
