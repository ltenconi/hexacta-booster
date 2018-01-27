/*
 * [legal notice here]
 */
package com.hexacta.booster.codegeneration;

import java.util.List;

import junit.framework.TestCase;

/**
 * This class tests TemplatesNames.
 * 
 * Created on 01/06/2009
 * 
 * @author vmartz
 * 
 */
public class TemplatesNamesTest extends TestCase {

    public void testGetAllNames() {

        List<String> allNames = TemplatesNames.getAllNames();

        assertEquals(37, allNames.size());

    }

    public void testGetName() {

        String name = TemplatesNames.getName("LIST_VIEW");

        assertEquals("Booster.list-view.vm", name);

    }

}
