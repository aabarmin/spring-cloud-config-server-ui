package ru.mydesignstudio.config.webui.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.mydesignstudio.config.webui.document.ConfigDocument;
import ru.mydesignstudio.config.webui.mapper.ConfigMapper;
import ru.mydesignstudio.config.webui.model.AppConfig;
import ru.mydesignstudio.config.webui.mongo.ConfigMongoRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class ConfigRepositoryImpl implements ConfigRepository {
    @Autowired
    private ConfigMongoRepository mongoRepository;

    @Autowired
    private ConfigMapper configMapper;

    @Override
    public List<AppConfig> findAll() {
        return mongoRepository.findAll()
                .stream()
                .map(configMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<AppConfig> findVersion(int number) {
        return mongoRepository.findById(String.valueOf(number))
                .map(configMapper::toDto);
    }

    @Override
    public AppConfig saveConfig(AppConfig config) {
        final ConfigDocument document = configMapper.toDocument(config);
        final ConfigDocument savedDocument = mongoRepository.save(document);
        return configMapper.toDto(savedDocument);
    }
}
