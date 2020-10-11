package ru.mydesignstudio.config.webui.service;

import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.mydesignstudio.config.webui.model.AppConfig;
import ru.mydesignstudio.config.webui.model.AppEnvironment;
import ru.mydesignstudio.config.webui.model.AppPropertyValue;

import java.util.Map;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class EffectiveConfigResolverTest {
    @Mock
    private ConfigService configService;
    @InjectMocks
    private DefaultEffectiveConfigResolver configResolver;

    @Test
    void check_contextStarts() {
        assertNotNull(configResolver);
    }

    @Test
    void resolve_ifNothingToResolveThereShouldBeAnEmptyCollection() {
        final Set<AppPropertyValue> values = configResolver.resolve(new AppEnvironment(), "");

        assertAll(
                () -> assertNotNull(values),
                () -> assertTrue(values.isEmpty())
        );
    }

    @Test
    void resolve_shouldTakeFromCommonConfiguration() {
        final AppEnvironment environment = new AppEnvironment();
        environment.setVersion(1);

        final AppConfig config = new AppConfig();
        config.setCommon(Set.of(
                createProperty("prop1", "value1"),
                createProperty("prop2", "value2")
        ));

        when(configService.findVersion(anyInt())).thenReturn(Optional.of(config));

        final Set<AppPropertyValue> properties = configResolver.resolve(environment, "service");

        assertAll(
                () -> assertNotNull(properties),
                () -> assertTrue(hasPropertyValue(properties, "prop1", "value1")),
                () -> assertTrue(hasPropertyValue(properties, "prop2", "value2"))
        );
    }

    @Test
    void resolve_shouldTakeFromServiceConfiguration() {
        final AppEnvironment environment = new AppEnvironment();
        environment.setVersion(1);

        final AppConfig config = new AppConfig();
        config.setCommon(Set.of(
                createProperty("prop1", "value1"),
                createProperty("prop2", "value2")
        ));
        config.setServices(Map.of(
                "service", Set.of(
                        createProperty("prop2", "overriden"),
                        createProperty("prop3", "value3")
                )
        ));

        when(configService.findVersion(anyInt())).thenReturn(Optional.of(config));

        final Set<AppPropertyValue> properties = configResolver.resolve(environment, "service");

        assertAll(
                () -> assertNotNull(properties),
                () -> assertTrue(hasPropertyValue(properties, "prop1", "value1")),
                () -> assertTrue(hasPropertyValue(properties, "prop2", "overriden")),
                () -> assertTrue(hasPropertyValue(properties, "prop3", "value3"))
        );
    }

    @Test
    void resolve_shouldTakeFromEnvironmentCommonConfiguration() {
        final AppConfig config = new AppConfig();
        config.setCommon(Set.of(
                createProperty("prop1", "value1"),
                createProperty("prop2", "value2")
        ));
        config.setServices(Map.of(
                "service", Set.of(
                        createProperty("prop3", "value3"),
                        createProperty("prop4", "value4")
                )
        ));

        final AppEnvironment environment = new AppEnvironment();
        environment.setVersion(1);
        environment.setCommon(Set.of(
                createProperty("prop4", "overriden")
        ));

        when(configService.findVersion(anyInt())).thenReturn(Optional.of(config));

        final Set<AppPropertyValue> properties = configResolver.resolve(environment, "service");

        assertAll(
                () -> assertNotNull(properties),
                () -> assertTrue(hasPropertyValue(properties, "prop1", "value1")),
                () -> assertTrue(hasPropertyValue(properties, "prop2", "value2")),
                () -> assertTrue(hasPropertyValue(properties, "prop3", "value3")),
                () -> assertTrue(hasPropertyValue(properties, "prop4", "overriden"))
        );
    }

    @Test
    void resolve_shouldTakeFormEnvironmentServiceConfiguration() {
        final AppConfig config = new AppConfig();
        config.setCommon(Set.of(
                createProperty("prop1", "value1"),
                createProperty("prop2", "value2")
        ));
        config.setServices(Map.of(
                "service", Set.of(
                        createProperty("prop3", "value3"),
                        createProperty("prop4", "value4")
                )
        ));

        final AppEnvironment environment = new AppEnvironment();
        environment.setVersion(1);
        environment.setCommon(Set.of(
                createProperty("prop4", "overriden1")
        ));
        environment.setServices(Map.of(
                "service", Set.of(
                        createProperty("prop4", "overriden2")
                )
        ));

        when(configService.findVersion(anyInt())).thenReturn(Optional.of(config));

        final Set<AppPropertyValue> properties = configResolver.resolve(environment, "service");

        assertAll(
                () -> assertNotNull(properties),
                () -> assertTrue(hasPropertyValue(properties, "prop1", "value1")),
                () -> assertTrue(hasPropertyValue(properties, "prop2", "value2")),
                () -> assertTrue(hasPropertyValue(properties, "prop3", "value3")),
                () -> assertTrue(hasPropertyValue(properties, "prop4", "overriden2"))
        );
    }

    private boolean hasPropertyValue(Set<AppPropertyValue> properties, String key, String expectedValue) {
        for (AppPropertyValue property : properties) {
            if (StringUtils.equals(property.getPropertyKey(), key)) {
                return StringUtils.equals(property.getPropertyValue(), expectedValue);
            }
        }
        return false;
    }

    private AppPropertyValue createProperty(String key, String value) {
        final AppPropertyValue property = new AppPropertyValue();
        property.setPropertyKey(key);
        property.setPropertyValue(value);
        return property;
    }
}