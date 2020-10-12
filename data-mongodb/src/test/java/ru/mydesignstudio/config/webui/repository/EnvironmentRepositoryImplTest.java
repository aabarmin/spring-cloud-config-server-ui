package ru.mydesignstudio.config.webui.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import ru.mydesignstudio.config.webui.config.MapperConfig;
import ru.mydesignstudio.config.webui.model.AppEnvironment;
import ru.mydesignstudio.config.webui.model.AppPropertyValue;
import ru.mydesignstudio.config.webui.mongo.EnvironmentMongoRepository;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

@DataMongoTest
@SpringJUnitConfig({EnvironmentRepositoryImpl.class, MapperConfig.class})
@EnableMongoRepositories(basePackageClasses = EnvironmentMongoRepository.class)
class EnvironmentRepositoryImplTest {
    @Autowired
    private EnvironmentsRepository environmentsRepository;

    @Test
    void check_contextStarts() {
        assertNotNull(environmentsRepository);
    }

    @Test
    void check_marshalling() {
        final AppEnvironment environment = new AppEnvironment();
        environment.setKey("test.key");
        environment.setName("test.name");
        environment.setDescription("test.description");
        environment.setVersion(42);
        environment.setCommon(createProperties(10));
        environment.setServices(Map.of(
                "service1", createProperties(10),
                "service2", createProperties(20),
                "service3", createProperties(30)
        ));

        final AppEnvironment savedEnvironment = environmentsRepository.saveEnvironment(environment);

        assertAll(
                () -> assertNotNull(savedEnvironment),
                () -> assertEquals(environment.getKey(), savedEnvironment.getKey()),
                () -> assertEquals(environment.getName(), savedEnvironment.getName()),
                () -> assertEquals(environment.getDescription(), savedEnvironment.getDescription()),
                () -> assertEquals(environment.getVersion(), savedEnvironment.getVersion()),
                () -> assertEquals(environment.getCommon(), savedEnvironment.getCommon()),
                () -> assertEquals(environment.getServices(), savedEnvironment.getServices())
        );
    }

    private Set<AppPropertyValue> createProperties(int count) {
        return IntStream.range(1, count + 1)
                .mapToObj(value -> createProperty("prop" + value, "value" + value))
                .collect(Collectors.toSet());
    }

    private AppPropertyValue createProperty(final String key, final String value) {
        final AppPropertyValue property = new AppPropertyValue();
        property.setPropertyKey(key);
        property.setPropertyValue(value);
        return property;
    }
}