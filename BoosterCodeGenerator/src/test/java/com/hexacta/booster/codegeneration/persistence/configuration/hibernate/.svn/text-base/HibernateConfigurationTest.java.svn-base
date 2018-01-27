package com.hexacta.booster.codegeneration.persistence.configuration.hibernate;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import junit.framework.TestCase;

/*
 * 
 */
public class HibernateConfigurationTest extends TestCase {

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

    private Map<String, HibernatePropertyName> makeProperties(final int n) {
        Map<String, HibernatePropertyName> properties = new HashMap<String, HibernatePropertyName>();

        for (int i = 0; i < n; i++) {
            HibernatePropertyName aProperty = new HibernatePropertyName("propertyName" + i, "propertyContent" + i);
            properties.put("propertyName" + i, aProperty);
        }
        return properties;
    }

    private Map<String, HibernateMappingResource> makeMappings(final int n) {
        Map<String, HibernateMappingResource> mappings = new HashMap<String, HibernateMappingResource>();

        for (int i = 0; i < n; i++) {
            HibernateMappingResource aMapping = new HibernateMappingResource("mappingResource" + i);
            mappings.put("mappingResource" + i, aMapping);
        }
        return mappings;
    }

    public void testCreation() {

        Map<String, HibernatePropertyName> properties = makeProperties(5);
        Map<String, HibernateMappingResource> mappings = makeMappings(5);

        HibernateConfiguration anHibernateConfiguration = new HibernateConfiguration(properties, mappings);

        assertNotNull(anHibernateConfiguration);
        assertEquals(properties.size(), anHibernateConfiguration.getProperties().size());
        assertEquals(mappings.size(), anHibernateConfiguration.getMappings().size());
        assertTrue(compareMap(properties, anHibernateConfiguration.getProperties()));
        assertTrue(compareMap(mappings, anHibernateConfiguration.getMappings()));

    }

    public void testAnotherCreation() {

        Map<String, HibernatePropertyName> properties = makeProperties(0);
        Map<String, HibernateMappingResource> mappings = makeMappings(0);

        HibernateConfiguration anHibernateConfiguration = new HibernateConfiguration(properties, mappings);

        assertNotNull(anHibernateConfiguration);
        assertEquals(properties.size(), anHibernateConfiguration.getProperties().size());
        assertEquals(mappings.size(), anHibernateConfiguration.getMappings().size());
        assertTrue(compareMap(properties, anHibernateConfiguration.getProperties()));
        assertTrue(compareMap(mappings, anHibernateConfiguration.getMappings()));
    }
}
