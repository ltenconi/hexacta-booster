package com.hexacta.booster.codegeneration.persistence.configuration.hibernate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hexacta.booster.codegeneration.configuration.HibernateParams;
import com.hexacta.booster.codegeneration.persistence.ormmodel.OrmMapping;

/**
 * This class generates an Hibernate Configuration from a list of OrmMapping.
 */

public class HibernateConfigurationGenerator {

    public HibernateConfigurationGenerator() {
    }

    public HibernateConfiguration generateConfiguration(final HibernateParams anHibernateParams,
            final List<OrmMapping> anOrmMappings) {

        Map<String, HibernatePropertyName> properties = setAllProperties(anHibernateParams);
        Map<String, HibernateMappingResource> mappings = setResourceMappings(anOrmMappings);
        HibernateConfiguration anHibernateConfiguration = new HibernateConfiguration(properties, mappings);
        return anHibernateConfiguration;
    }

    private Map<String, HibernatePropertyName> setAllProperties(final HibernateParams anHibernateParams) {

        Map<String, HibernatePropertyName> properties = new HashMap<String, HibernatePropertyName>();

        HibernatePropertyName propDriver = new HibernatePropertyName("connection.driver_class", anHibernateParams
                .getDriver());
        properties.put("connection.driver_class", propDriver);

        HibernatePropertyName propDatabase = new HibernatePropertyName("connection.url", anHibernateParams
                .getDatabase());
        properties.put("connection.url", propDatabase);

        HibernatePropertyName propUser = new HibernatePropertyName("connection.username", anHibernateParams
                .getUsername());
        properties.put("connection.username", propUser);

        HibernatePropertyName propPassword = new HibernatePropertyName("connection.password", anHibernateParams
                .getPassword());
        properties.put("connection.password", propPassword);

        HibernatePropertyName propMaxConnections = new HibernatePropertyName("connection.pool_size", String
                .valueOf(anHibernateParams.getMaxConnections()));
        properties.put("connection.pool_size", propMaxConnections);

        HibernatePropertyName propDialect = new HibernatePropertyName("dialect", anHibernateParams.getDialect());
        properties.put("dialect", propDialect);

        HibernatePropertyName propContext = new HibernatePropertyName("current_session_context_class", "thread");
        properties.put("current_session_context_class", propContext);

        HibernatePropertyName propCache = new HibernatePropertyName("cache.provider_class",
                "org.hibernate.cache.NoCacheProvider");
        properties.put("cache.provider_class", propCache);

        HibernatePropertyName propShow = new HibernatePropertyName("show_sql", "true");
        properties.put("show_sql", propShow);

        HibernatePropertyName propData = new HibernatePropertyName("hbm2ddl.auto", "create-drop");
        properties.put("hbm2ddl.auto", propData);

        HibernatePropertyName propFlush = new HibernatePropertyName("hibernate.transaction.flush_before_completion",
                "true");
        properties.put("hibernate.transaction.flush_before_completion", propFlush);

        return properties;

    }

    private Map<String, HibernateMappingResource> setResourceMappings(final List<OrmMapping> anOrmMappings) {

        Map<String, HibernateMappingResource> mappings = new HashMap<String, HibernateMappingResource>();

        for (OrmMapping ormMapping : anOrmMappings) {

            OrmMapping name = ormMapping;

            HibernateMappingResource mapping = new HibernateMappingResource(name.getMappingClass().getName());
            mappings.put(name.getMappingClass().getName(), mapping);

        }

        return mappings;
    }

}