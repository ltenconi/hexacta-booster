package com.hexacta.booster.codegeneration.persistence.configuration.hibernate;

import junit.framework.TestCase;

/**
 * 
 */
public class HibernatePropertyNameTest extends TestCase {

    public void testCreation() {
        HibernatePropertyName aProperty = new HibernatePropertyName("aName", "aContent");
        assertNotNull(aProperty);
        assertEquals("aName", aProperty.getName());
        assertEquals("aContent", aProperty.getContent());
    }

    public void testAnotherCreation() {
        HibernatePropertyName anotherProperty = new HibernatePropertyName("anEmptyName", "");

        assertEquals("anEmptyName", anotherProperty.getName());
        assertEquals("", anotherProperty.getContent());
    }

    public void testEquals() {

        HibernatePropertyName aProperty = new HibernatePropertyName("aName", "aContent");
        assertTrue(aProperty.equals(new HibernatePropertyName("aName", "aContent")));
    }

    public void testNotEquals() {
        HibernatePropertyName aProperty = new HibernatePropertyName("aName", "aContent");
        assertFalse(aProperty.equals(new Object()));
    }

    public void testAnotherNotEquals() {
        HibernatePropertyName aProperty = new HibernatePropertyName("aName", "aContent");
        assertFalse(aProperty.equals(new HibernatePropertyName("anotherName", "anotherContent")));
    }

}
