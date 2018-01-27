package com.hexacta.booster.codegeneration.persistence.layer;

import junit.framework.TestCase;

/**
 * 
 */
public class BusinessObjectDataObjectMappingGeneratorTest extends TestCase {

    public void testCreation() {
        BusinessObjectDataObjectMappingGenerator businessObjectDataObjectMappingGenerator = new BusinessObjectDataObjectMappingGenerator();
        assertNotNull(businessObjectDataObjectMappingGenerator);
    }

    public void testImports() {
        BusinessObjectDataObjectMappingGenerator businessObjectDataObjectMappingGenerator = new BusinessObjectDataObjectMappingGenerator();
        String expected = "import java.util.HashMap;\n" + "import java.util.Map;\n";
        assertEquals(expected, businessObjectDataObjectMappingGenerator.generateImports());

    }

    public void testGenerate() {
        BusinessObjectDataObjectMappingGenerator businessObjectDataObjectMappingGenerator = new BusinessObjectDataObjectMappingGenerator();
        String expected = businessObjectDataObjectMappingGenerator.generatePackage()
                + businessObjectDataObjectMappingGenerator.generateImports()
                + businessObjectDataObjectMappingGenerator.generateClass();

        assertEquals(expected, businessObjectDataObjectMappingGenerator.generate());

    }

    public void testGenerateClass() {
        BusinessObjectDataObjectMappingGenerator businessObjectDataObjectMappingGenerator = new BusinessObjectDataObjectMappingGenerator();
        String expected = "public class BusinessObjectDataObjectMapping {\n"
                + businessObjectDataObjectMappingGenerator.generateColaborators()
                + businessObjectDataObjectMappingGenerator.generateConstructor()
                + businessObjectDataObjectMappingGenerator.generateMethods() + "}\n";

        assertEquals(expected, businessObjectDataObjectMappingGenerator.generateClass());
    }

    public void testGeneratePackage() {
        BusinessObjectDataObjectMappingGenerator businessObjectDataObjectMappingGenerator = new BusinessObjectDataObjectMappingGenerator();
        String expected = "package " + /* projectPackageName + */"persistence;\n";

        assertEquals(expected, businessObjectDataObjectMappingGenerator.generatePackage());

    }

    public void testGenerateConstructor() {
        BusinessObjectDataObjectMappingGenerator businessObjectDataObjectMappingGenerator = new BusinessObjectDataObjectMappingGenerator();
        String expected =

        "\n" + "	public BusinessObjectDataObjectMapping() {\n" + "		businessToData = new HashMap<Object, Object>();\n"
                + "		dataToBusiness = new HashMap<Object, Object>();\n" + "	}\n";

        assertEquals(expected, businessObjectDataObjectMappingGenerator.generateConstructor());

    }

    public void testGenerateColaborators() {
        BusinessObjectDataObjectMappingGenerator businessObjectDataObjectMappingGenerator = new BusinessObjectDataObjectMappingGenerator();
        String expected =

        "\n" + "	Map<Object, Object> businessToData;\n" + "	Map<Object, Object> dataToBusiness;\n" + "\n";

        assertEquals(expected, businessObjectDataObjectMappingGenerator.generateColaborators());

    }

    public void testGenerateMethods() {
        BusinessObjectDataObjectMappingGenerator businessObjectDataObjectMappingGenerator = new BusinessObjectDataObjectMappingGenerator();
        String expected =

        "\n" + "	public void put(Object businessObject, Object dataObject){\n"
                + "		businessToData.put(businessObject,dataObject);\n"
                + "		dataToBusiness.put(dataObject,businessObject);\n" + "	}\n" + "\n"
                + "	public Object getBusinessObject(Object dataObject){\n"
                + "		return dataToBusiness.get(dataObject);\n" + "	}\n" + "\n"
                + "	public Object getDataObject(Object businessObject){\n"
                + "		return businessToData.get(businessObject);\n" + "	}\n" + "\n"
                + "	public void remove(Object object){\n" + "		if (businessToData.containsKey(object))\n"
                + "		{	Object dataObject = businessToData.get(object);\n" + "			businessToData.remove(object);\n"
                + "			dataToBusiness.remove(dataObject);\n" + "		}\n" + "		if (dataToBusiness.containsKey(object))\n"
                + "		{	Object businessObject = dataToBusiness.get(object);\n" + "			dataToBusiness.remove(object);\n"
                + "			businessToData.remove(businessObject);\n" + "		}\n" + "	}\n" + "\n"
                + "	public boolean containsBusinessObject(Object businessObject){\n"
                + "		return businessToData.containsKey(businessObject);\n" + "	}\n" + "\n"
                + "	public boolean containsDataObject(Object dataObject){\n"
                + "		return dataToBusiness.containsKey(dataObject);\n" + "	}\n";

        assertEquals(expected, businessObjectDataObjectMappingGenerator.generateMethods());
    }

}
