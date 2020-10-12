package ru.mydesignstudio.config.webui.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.mydesignstudio.config.webui.document.PropertyDocument;
import ru.mydesignstudio.config.webui.mapper.PropertyMapper;
import ru.mydesignstudio.config.webui.model.AppProperty;
import ru.mydesignstudio.config.webui.mongo.PropertyMongoRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class PropertyRepositoryImpl implements PropertiesRepository {
    @Autowired
    private PropertyMongoRepository mongoRepository;

    @Autowired
    private PropertyMapper propertyMapper;

    @Override
    public List<AppProperty> findAll() {
        return mongoRepository.findAll()
                .stream()
                .map(propertyMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<AppProperty> findProperty(String propertyKey) {
        return mongoRepository.findById(propertyKey)
                .map(propertyMapper::toDto);
    }

    @Override
    public AppProperty saveProperty(AppProperty property) {
        final PropertyDocument document = propertyMapper.toDocument(property);
        final PropertyDocument savedDocument = mongoRepository.save(document);
        return propertyMapper.toDto(savedDocument);
    }
}
