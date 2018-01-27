package com.hexacta.booster.codegeneration.persistence.configuration.hibernate;

import junit.framework.TestCase;

/**
 * 
 */
public class HibernateMappingResourceTest extends TestCase {

    public void testCreation() {

        HibernateMappingResource aMapping = new HibernateMappingResource("aResource");
        assertNotNull(aMapping);
        assertEquals("aResource", aMapping.getResource());
    }

    public void testAnotherCreation() {
        HibernateMappingResource anotherMapping = new HibernateMappingResource("");
        assertEquals("", anotherMapping.getResource());
    }

    public void testEquals() {

        HibernateMappingResource aMapping = new HibernateMappingResource("myResource");
        assertTrue(aMapping.equals(new HibernateMappingResource("myResource")));
    }

    public void testNotEquals() {
        HibernateMappingResource aMapping = new HibernateMappingResource("myResource");
        assertFalse(aMapping.equals(new Object()));
    }

    public void testAnotherNotEquals() {
        HibernateMappingResource aMapping = new HibernateMappingResource("myResource");
        assertFalse(aMapping.equals(new HibernateMappingResource("anotherResource")));
    }

}
