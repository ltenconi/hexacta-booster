package com.hexacta.booster.codegeneration.persistence.layer;

import junit.framework.TestCase;

/**
 * 
 */
public class MappingAdministratorTest extends TestCase {

    public void testCreation() {

        MappingAdministrator aMappingAdministrator = new MappingAdministrator();

        assertNotNull(aMappingAdministrator);
        assertEquals("", aMappingAdministrator.getText());

    }

    public void testAppend() {

        MappingAdministrator aMappingAdministrator = new MappingAdministrator();

        assertEquals("", aMappingAdministrator.getText());

        aMappingAdministrator.append("aNewString");

        assertEquals("aNewString", aMappingAdministrator.getText());

    }

    public void testAnotherAppend() {

        MappingAdministrator aMappingAdministrator = new MappingAdministrator();

        assertEquals("", aMappingAdministrator.getText());

        aMappingAdministrator.append("");

        assertEquals("", aMappingAdministrator.getText());

    }

}
