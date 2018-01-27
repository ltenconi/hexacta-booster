/*
 * [legal notice here]
 */
package com.hexacta.booster.utilities;

import junit.framework.TestCase;

/**
 * This class tests the Helper who generates setter and getter methods given a
 * field name and a type name in setter case and a field name in getter case.
 * 
 * Created on 26/02/2009
 * 
 * @author vmartz
 * 
 */
public class TestGenerationHelperTest extends TestCase {

    public void testGetSetterMethodWithNoEmptyPropertyName() {

        String generatedSetter = TestGenerationHelper.getSetterMethod("name");

        assertEquals("setName", generatedSetter);
    }

    public void testGetSetterMethodWithEmptyPropertyName() {

        String generatedSetter = TestGenerationHelper.getSetterMethod("");

        assertEquals("set", generatedSetter);
    }

    public void testGetGetterMethodWhenPropertyTypeIsBoolean() {

        String generatedGetter = TestGenerationHelper.getGetterMethod("present", "boolean");

        assertEquals("isPresent", generatedGetter);

    }

    public void testGetGetterMethodWhenPropertyNameIsNotBoolean() {

        String generatedGetter = TestGenerationHelper.getGetterMethod("age", "int");

        assertEquals("getAge", generatedGetter);

    }

    public void testGetGetterMethodWhenPropertyTypeIsBooleanAndPropertyNameIsEmpty() {

        String generatedGetter = TestGenerationHelper.getGetterMethod("", "boolean");

        assertEquals("is", generatedGetter);

    }

    public void testGetGetterMethodWhenPropertyTypeIsNotBooleanAndPropertyNameIsEmpty() {

        String generatedGetter = TestGenerationHelper.getGetterMethod("", "int");

        assertEquals("get", generatedGetter);

    }
}
