package ru.mydesignstudio.config.webui.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.mydesignstudio.config.webui.model.AppProperty;
import ru.mydesignstudio.config.webui.repository.PropertiesRepository;
import ru.mydesignstudio.config.webui.service.exception.AppPropertyKeyDuplicationException;
import ru.mydesignstudio.config.webui.service.exception.AppPropertyKeyNotSetException;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PropertiesServiceTest {
    @Mock
    private PropertiesRepository propertiesRepository;
    @InjectMocks
    private PropertiesService propertiesService;

    @Test
    void check_contextStarts() {
        assertNotNull(propertiesService);
    }

    @Test
    void createProperty_shouldCreateANewProperty() {
        final AppProperty property = propertiesService.createProperty();

        assertAll(
                () -> assertNotNull(property)
        );
    }

    @Test
    void findAll_shouldReturnPropertiesFromRepository() {
        when(propertiesRepository.findAll()).thenReturn(List.of());

        final List<AppProperty> properties = propertiesService.findAll();

        assertAll(
                () -> assertNotNull(properties),
                () -> assertTrue(properties.isEmpty())
        );
    }

    @Test
    void findProperty_shouldReturnSomethingFromTheRepository() {
        when(propertiesRepository.findProperty(anyString())).thenReturn(Optional.empty());

        final Optional<AppProperty> optional = propertiesService.findProperty("testKey");

        assertAll(
                () -> assertNotNull(optional),
                () -> assertTrue(optional.isEmpty())
        );
    }

    @Test
    void saveProperty_thePropertyShouldBeSaved() {
        when(propertiesRepository.saveProperty(any(AppProperty.class))).thenAnswer(inv -> {
            return inv.getArgument(0);
        });

        final AppProperty property = new AppProperty();
        property.setKey("some key");
        final AppProperty savedProperty = propertiesService.saveProperty(property);

        assertAll(
                () -> assertNotNull(savedProperty)
        );

        verify(propertiesRepository, times(1)).saveProperty(any(AppProperty.class));
    }

    @Test
    void saveProperty_thereShouldBeAnExceptionIfKeyIsEmpty() {
        final AppProperty property = new AppProperty();

        assertThrows(
                AppPropertyKeyNotSetException.class,
                () -> propertiesService.saveProperty(property)
        );
    }

    @Test
    void saveProperty_thereShouldBeAnExceptionIfKeyIsDuplicated() {
        when(propertiesRepository.findProperty(anyString())).thenReturn(Optional.of(new AppProperty()));

        final AppProperty property = new AppProperty();
        property.setKey("exists");

        assertThrows(
                AppPropertyKeyDuplicationException.class,
                () -> propertiesService.saveProperty(property)
        );
    }
}