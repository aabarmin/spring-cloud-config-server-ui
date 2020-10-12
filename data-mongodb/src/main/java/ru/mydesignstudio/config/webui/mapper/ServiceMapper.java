package ru.mydesignstudio.config.webui.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.mydesignstudio.config.webui.document.ServiceDocument;
import ru.mydesignstudio.config.webui.model.AppService;

@Mapper
public interface ServiceMapper {
    ServiceMapper INSTANCE = Mappers.getMapper(ServiceMapper.class);

    ServiceDocument toDocument(AppService service);

    AppService toDto(ServiceDocument document);
}
