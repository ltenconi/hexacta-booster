package com.hexacta.booster.codegeneration.persistence.configuration.hibernate;

import java.util.Map;

/**
 * This class keeps the hibernate configuration options. The "properties" map
 * keeps the options and the "mappings" map the mapping resources.
 */

public class HibernateConfiguration {

    private Map<String, HibernatePropertyName> properties;

    private Map<String, HibernateMappingResource> mappings;

    public HibernateConfiguration(final Map<String, HibernatePropertyName> properties,
            final Map<String, HibernateMappingResource> mappings) {

        this.properties = properties;
        this.mappings = mappings;
    }

    public String getPropertyContent(final String aKey) {
        return properties.get(aKey).getContent();
    }

    public String getMappingResource(final String aKey) {
        return mappings.get(aKey).getResource();
    }

    public Map<String, HibernatePropertyName> getProperties() {
        return properties;
    }

    public Map<String, HibernateMappingResource> getMappings() {
        return mappings;
    }
}
