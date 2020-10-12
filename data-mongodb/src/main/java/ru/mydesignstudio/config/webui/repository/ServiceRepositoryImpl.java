package ru.mydesignstudio.config.webui.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.mydesignstudio.config.webui.document.ServiceDocument;
import ru.mydesignstudio.config.webui.mapper.ServiceMapper;
import ru.mydesignstudio.config.webui.model.AppService;
import ru.mydesignstudio.config.webui.mongo.ServiceMongoRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class ServiceRepositoryImpl implements ServicesRepository {
    @Autowired
    private ServiceMongoRepository mongoRepository;

    @Autowired
    private ServiceMapper serviceMapper;

    @Override
    public Optional<AppService> findService(String key) {
        return mongoRepository.findById(key)
                .map(serviceMapper::toDto);
    }

    @Override
    public AppService saveService(AppService service) {
        final ServiceDocument document = serviceMapper.toDocument(service);
        final ServiceDocument savedDocument = mongoRepository.save(document);
        return serviceMapper.toDto(savedDocument);
    }

    @Override
    public List<AppService> findAll() {
        return mongoRepository.findAll()
                .stream()
                .map(serviceMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public boolean deleteService(AppService service) {
        mongoRepository.deleteById(service.getKey());
        return true;
    }
}
