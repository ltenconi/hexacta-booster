package com.hexacta.booster.codegeneration.persistence.configuration.hibernate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import junit.framework.TestCase;

import org.apache.log4j.Logger;

import test.hexacta.booster.providers.CodeGeneratorConfigurationProvider;
import ar.com.hexacta.utils.reflection.ReflectionUtils;

import com.hexacta.booster.codegeneration.configuration.CodeGeneratorConfiguration;
import com.hexacta.booster.codegeneration.configuration.HibernateParams;
import com.hexacta.booster.codegeneration.persistence.ormmodel.OrmMapping;

/**
 * 
 */
public class HibernateConfigurationGeneratorTest extends TestCase {

    /**
     * Log4j logger.
     */
    static final Logger logger = Logger.getLogger(HibernateConfigurationGeneratorTest.class.getSimpleName());

    private HibernateParams hibernateParams = new HibernateParams("jdbc:mysql://hxbws014/anrDB",
            "com.mysql.jdbc.Driver", "org.hibernate.dialect.MySQLDialect", "root", "admin", 0);

    private String[] classes = { "test.model.House", "test.model.Student", "test.model.Person", "test.model.Address",
            "test.model.Employee", "test.model.Company", "test.model.Room", "test.model.Course" };

    private List<OrmMapping> createOrmMappingContext(final int value) {

        CodeGeneratorConfigurationProvider aCodeGeneratorConfigurationProvider = new CodeGeneratorConfigurationProvider();
        CodeGeneratorConfiguration aCodeGeneratorConfiguration = aCodeGeneratorConfigurationProvider
                .getCodeGeneratorConfiguration();

        int valueAux = value;

        if (valueAux < 0) {
            valueAux = 0;
        }
        if (valueAux > 8) {
            valueAux = 8;
        }

        List<OrmMapping> ormMappings = new ArrayList<OrmMapping>();
        for (int i = 0; i < valueAux; i++) {

            OrmMapping mapping = new OrmMapping(aCodeGeneratorConfiguration.getClassList().getClass(classes[i]),
                    classes[i] + "Id");
            ormMappings.add(mapping);
        }
        return ormMappings;
    }

    private Map<String, HibernatePropertyName> makeProperties(final HibernateParams anHibernateParams) throws Exception {

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

    private Map<String, HibernateMappingResource> makeMappings(final List<OrmMapping> anOrmMappings) {

        Map<String, HibernateMappingResource> mappings = new HashMap<String, HibernateMappingResource>();

        for (OrmMapping ormMapping : anOrmMappings) {

            OrmMapping anOrmMapping = ormMapping;

            HibernateMappingResource mapping = new HibernateMappingResource(anOrmMapping.getMappingClass().getName());
            mappings.put(anOrmMapping.getMappingClass().getName(), mapping);

        }
        return mappings;
    }

    private boolean compareMap(final Map<?, ?> aMap, final Map<?, ?> anotherMap) {

        if (aMap.size() == anotherMap.size()) {
            int mapsize = aMap.size();

            Iterator<?> keyValuePairs1 = aMap.entrySet().iterator();
            for (int i = 0; i < mapsize; i++) {
                Map.Entry<?, ?> entry = (Map.Entry<?, ?>) keyValuePairs1.next();
                Object key = entry.getKey();
                Object value = entry.getValue();

                Object gettedEntry = anotherMap.get(key);
                if (!value.equals(gettedEntry))
                    return false;
            }
            return true;
        } else
            return false;
    }

    public void testCreation() {

        HibernateConfigurationGenerator anHibernateConfigurationGenerator = new HibernateConfigurationGenerator();
        assertNotNull(anHibernateConfigurationGenerator);
    }

    @SuppressWarnings("unchecked")
    public void testGenerateConfiguration() throws Exception {

        HibernateConfigurationGenerator anHibernateConfigurationGenerator = new HibernateConfigurationGenerator();
        List<OrmMapping> ormMappings = createOrmMappingContext(3);
        HibernateConfiguration generatedHibernateConfiguration = anHibernateConfigurationGenerator
                .generateConfiguration(hibernateParams, ormMappings);

        HibernateConfigurationGenerator expectedHibernateConfigurationGenerator = new HibernateConfigurationGenerator();
        Map<String, HibernatePropertyName> properties = (Map<String, HibernatePropertyName>) ReflectionUtils
                .executePrivateMethod(expectedHibernateConfigurationGenerator, "setAllProperties",
                        new Class[] { HibernateParams.class }, new Object[] { hibernateParams });
        Map<String, HibernateMappingResource> mappings = (Map<String, HibernateMappingResource>) ReflectionUtils
                .executePrivateMethod(expectedHibernateConfigurationGenerator, "setResourceMappings",
                        new Class[] { List.class }, new Object[] { ormMappings });
        HibernateConfiguration expectedHibernateConfiguration = new HibernateConfiguration(properties, mappings);

        assertEquals(ormMappings.size(), generatedHibernateConfiguration.getMappings().size());
        assertEquals(11, generatedHibernateConfiguration.getProperties().size());
        assertTrue(compareMap(expectedHibernateConfiguration.getProperties(), generatedHibernateConfiguration
                .getProperties()));
        assertTrue(compareMap(expectedHibernateConfiguration.getMappings(), generatedHibernateConfiguration
                .getMappings()));

    }

    public void testAnotherGenerateConfiguration() {

        HibernateConfigurationGenerator anHibernateConfigurationGenerator = new HibernateConfigurationGenerator();
        List<OrmMapping> ormMappings = createOrmMappingContext(0);
        HibernateConfiguration anHibernateConfiguration = anHibernateConfigurationGenerator.generateConfiguration(
                hibernateParams, ormMappings);

        assertEquals(0, anHibernateConfiguration.getMappings().size());
        assertEquals(ormMappings.size(), anHibernateConfiguration.getMappings().size());
        assertEquals(11, anHibernateConfiguration.getProperties().size());

    }

    @SuppressWarnings("unchecked")
    public void testSetAllProperties() {

        try {
            HibernateConfigurationGenerator anHibernateConfigGenerator = new HibernateConfigurationGenerator();

            Map<String, Object> generatedProperties = (Map<String, Object>) ReflectionUtils.executePrivateMethod(
                    anHibernateConfigGenerator, "setAllProperties", new Class[] { HibernateParams.class },
                    new Object[] { hibernateParams });

            Map expectedProperties = makeProperties(hibernateParams);

            assertTrue(compareMap(expectedProperties, generatedProperties));
            assertEquals(11, generatedProperties.size());

        } catch (Exception e) {
            logger.error("Se levantó una excepción: " + e.getMessage());
            fail();
        }
    }

    @SuppressWarnings("unchecked")
    public void testSetResourceMappings() {
        try {
            List<OrmMapping> ormMappings = createOrmMappingContext(5);

            HibernateConfigurationGenerator anHibernateConfigGenerator = new HibernateConfigurationGenerator();

            Map<String, HibernateMappingResource> generatedMappings = (Map<String, HibernateMappingResource>) ReflectionUtils
                    .executePrivateMethod(anHibernateConfigGenerator, "setResourceMappings",
                            new Class[] { List.class }, new Object[] { ormMappings });

            Map<String, HibernateMappingResource> expectedMappings = makeMappings(ormMappings);

            assertTrue(compareMap(expectedMappings, generatedMappings));

        } catch (Exception e) {
            logger.error("Se levantó una excepción: " + e.getMessage());
            fail();
        }
    }

    @SuppressWarnings("unchecked")
    public void testSetAnotherResourceMappings() {
        try {
            List<OrmMapping> ormMappings = createOrmMappingContext(0);

            HibernateConfigurationGenerator anHibernateConfigGenerator = new HibernateConfigurationGenerator();

            Map<String, HibernateMappingResource> generatedMappings = (Map<String, HibernateMappingResource>) ReflectionUtils
                    .executePrivateMethod(anHibernateConfigGenerator, "setResourceMappings",
                            new Class[] { List.class }, new Object[] { ormMappings });

            Map<String, HibernateMappingResource> expectedMappings = makeMappings(ormMappings);

            assertTrue(compareMap(expectedMappings, generatedMappings));
            assertEquals(0, generatedMappings.size());

        } catch (Exception e) {
            logger.error("Se levantó una excepción: " + e.getMessage());
            fail();
        }
    }
}
