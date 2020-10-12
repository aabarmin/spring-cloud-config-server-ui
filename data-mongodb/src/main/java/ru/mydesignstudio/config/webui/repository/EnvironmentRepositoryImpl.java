package ru.mydesignstudio.config.webui.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.mydesignstudio.config.webui.document.EnvironmentDocument;
import ru.mydesignstudio.config.webui.mapper.EnvironmentMapper;
import ru.mydesignstudio.config.webui.model.AppEnvironment;
import ru.mydesignstudio.config.webui.mongo.EnvironmentMongoRepository;

import java.util.Optional;

@Repository
public class EnvironmentRepositoryImpl implements EnvironmentsRepository {
    @Autowired
    private EnvironmentMongoRepository mongoRepository;

    @Autowired
    private EnvironmentMapper environmentMapper;

    @Override
    public Optional<AppEnvironment> findEnvironment(String key) {
        return mongoRepository.findById(key)
                .map(environmentMapper::toDto);
    }

    @Override
    public AppEnvironment saveEnvironment(AppEnvironment environment) {
        final EnvironmentDocument document = environmentMapper.toDocument(environment);
        final EnvironmentDocument savedEnvironment = mongoRepository.save(document);
        return environmentMapper.toDto(savedEnvironment);
    }
}
