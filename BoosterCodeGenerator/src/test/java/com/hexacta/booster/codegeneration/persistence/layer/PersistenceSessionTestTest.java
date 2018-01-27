package com.hexacta.booster.codegeneration.persistence.layer;

import junit.framework.TestCase;

import org.apache.log4j.Logger;

import ar.com.hexacta.utils.reflection.ReflectionUtils;

/**
 * 
 */
public class PersistenceSessionTestTest extends TestCase {
    /**
     * This class tests the persistenceLayerTest class, which only has an
     * internal collaborator: text
     */

    /**
     * Log4j logger.
     */
    static final Logger logger = Logger.getLogger(PersistenceSessionTestTest.class.getSimpleName());

    public void testCreation() {
        try {
            PersistenceSessionTest aPersistenceLayerTest = new PersistenceSessionTest();

            String textValue = (String) ReflectionUtils.getPrivateField(aPersistenceLayerTest, "text");

            assertNotNull(aPersistenceLayerTest);
            assertEquals("", textValue);

        } catch (Exception e) {
            logger.error("Se ha levantado una excepción: " + e.getMessage());
            fail();
        }
    }

    public void testAppend() {
        try {
            PersistenceSessionTest aPersistenceLayerTest = new PersistenceSessionTest();

            String textValue = (String) ReflectionUtils.getPrivateField(aPersistenceLayerTest, "text");
            aPersistenceLayerTest.append("aString");

            String newTextValue = (String) ReflectionUtils.getPrivateField(aPersistenceLayerTest, "text");

            assertEquals("", textValue);
            assertEquals("aString", newTextValue);

        } catch (Exception e) {
            logger.error("Se ha levantado una excepción: " + e.getMessage());
            fail();
        }
    }

    public void testAnotherAppend() {
        try {
            PersistenceSessionTest aPersistenceLayerTest = new PersistenceSessionTest();

            String textValue = (String) ReflectionUtils.getPrivateField(aPersistenceLayerTest, "text");

            aPersistenceLayerTest.append("");

            String newTextValue = (String) ReflectionUtils.getPrivateField(aPersistenceLayerTest, "text");

            assertEquals("", textValue);
            assertEquals("", newTextValue);

        } catch (Exception e) {
            logger.error("Se ha levantado una excepción: " + e.getMessage());
            fail();
        }
    }
}
