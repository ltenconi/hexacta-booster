package com.hexacta.booster.codegeneration.persistence.layer;

import junit.framework.TestCase;

import org.apache.log4j.Logger;

import ar.com.hexacta.utils.reflection.ReflectionUtils;

/**
 * 
 */
public class PersistableClassTest extends TestCase {

    /**
     * Log4j logger.
     */

    static final Logger logger = Logger.getLogger(PersistableClassTest.class.getSimpleName());

    public void testCreation() {
        try {
            Class<?> aClass = Class.class;
            String aClassName = "aClassName";
            PersistableClass aPersistableClass = new PersistableClass(aClass, aClassName);

            Class<?> modelClassValue = (Class<?>) ReflectionUtils.getPrivateField(aPersistableClass, "modelClass");
            String textValue = (String) ReflectionUtils.getPrivateField(aPersistableClass, "text");
            String classNameValue = (String) ReflectionUtils.getPrivateField(aPersistableClass, "className");

            assertNotNull(aPersistableClass);
            assertEquals(aClass, modelClassValue);
            assertEquals(aClassName, classNameValue);
            assertEquals("", textValue);

        } catch (Exception e) {
            logger.error("Se ha levantado una excepción: " + e.getMessage());
            fail();
        }
    }

    public void testAppend() {
        try {
            Class<?> aClass = Class.class;
            String aClassName = "aClassName";
            PersistableClass aPersistableClass = new PersistableClass(aClass, aClassName);

            String textValue = (String) ReflectionUtils.getPrivateField(aPersistableClass, "text");

            aPersistableClass.append("aString");

            String newTextValue = (String) ReflectionUtils.getPrivateField(aPersistableClass, "text");

            assertEquals("", textValue);
            assertEquals("aString", newTextValue);

        } catch (Exception e) {
            logger.error("Se ha levantado una excepción: " + e.getMessage());
            fail();
        }
    }

    public void testAnotherAppend() {
        try {
            Class<?> aClass = Class.class;
            String aClassName = "aClassName";
            PersistableClass aPersistableClass = new PersistableClass(aClass, aClassName);

            String textValue = (String) ReflectionUtils.getPrivateField(aPersistableClass, "text");

            aPersistableClass.append("");

            String newTextValue = (String) ReflectionUtils.getPrivateField(aPersistableClass, "text");

            assertEquals("", textValue);
            assertEquals("", newTextValue);

        } catch (Exception e) {
            logger.error("Se ha levantado una excepción: " + e.getMessage());
            fail();
        }
    }
}
