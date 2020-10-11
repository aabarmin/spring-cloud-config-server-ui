package ru.mydesignstudio.config.webui.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.mydesignstudio.config.webui.model.AppConfig;
import ru.mydesignstudio.config.webui.model.AppPropertyValue;
import ru.mydesignstudio.config.webui.repository.ConfigRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ConfigServiceTest {
    @Mock
    private ConfigRepository configRepository;
    @InjectMocks
    private ConfigService configService;

    @Test
    void check_contextStarts() {
        assertNotNull(configService);
    }

    @Test
    void findAllVersions_shouldReturnAllVersions() {
        when(configService.findAll()).thenReturn(List.of(new AppConfig()));

        final List<AppConfig> allVersions = configService.findAll();

        assertAll(
                () -> assertNotNull(allVersions),
                () -> assertFalse(allVersions.isEmpty())
        );
    }

    @Test
    void findVersion_shouldReturnAVersion() {
        when(configRepository.findVersion(anyInt())).thenReturn(Optional.of(new AppConfig()));

        final Optional<AppConfig> config = configService.findVersion(1);

        assertAll(
                () -> assertNotNull(config),
                () -> assertFalse(config.isEmpty())
        );
    }

    @Test
    void saveVersion_thereShouldBeANewVersion() {
        final AppConfig savedConfig = new AppConfig();
        savedConfig.setVersion(1);
        savedConfig.setCreated(LocalDateTime.now());

        when(configRepository.findVersion(anyInt())).thenReturn(Optional.of(savedConfig));
        when(configRepository.saveConfig(any(AppConfig.class))).thenAnswer(inv -> {
            return inv.getArgument(0);
        });

        final Optional<AppConfig> version = configService.findVersion(1);

        assertTrue(version.isPresent());

        final AppConfig config = version.get();
        config.setCommon(Set.of(
                createProperty("common1", "value1"),
                createProperty("common2", "value2")
        ));
        config.setServices(Map.of(
                "service1", Set.of(
                        createProperty("prop1", "value1"),
                        createProperty("prop2", "value2")
                ),
                "service2", Set.of(
                        createProperty("prop3", "value3"),
                        createProperty("prop4", "value4")
                )
        ));

        final AppConfig updatedConfig = configService.saveConfig(config);

        assertAll(
                () -> assertNotNull(updatedConfig, "The updated config should be returned"),
                () -> assertEquals(2, updatedConfig.getVersion(), "Update config should have the next version"),
                () -> assertNotNull(updatedConfig.getCreated(), "The config should have a created date"),
                () -> assertEquals(1, updatedConfig.getParentId(), "A new version should be connected to the previous"),
                () -> assertTrue(savedConfig.getCreated().isBefore(updatedConfig.getCreated()), "A new version should be saved after the previous"),
                () -> assertNotNull(updatedConfig.getCommon(), "Common parts should be present"),
                () -> assertNotNull(updatedConfig.getServices(), "Service should be present"),
                () -> assertEquals(2, updatedConfig.getCommon().size(), "Size of the common part should be the same"),
                () -> assertEquals(2, updatedConfig.getServices().size(), "Size of services should be the same as before")
        );

        verify(configRepository, times(1)).saveConfig(any(AppConfig.class));
    }

    private AppPropertyValue createProperty(String key, String value) {
        final AppPropertyValue propertyValue = new AppPropertyValue();
        propertyValue.setPropertyKey(key);
        propertyValue.setPropertyValue(value);
        return propertyValue;
    }
}