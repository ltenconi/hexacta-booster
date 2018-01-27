package com.hexacta.booster.codegeneration.persistence.layer;

import java.util.Iterator;

import junit.framework.TestCase;
import test.hexacta.booster.providers.CodeGeneratorConfigurationProvider;
import ar.com.hexacta.utils.reflection.ReflectionUtils;

import com.hexacta.booster.codegeneration.configuration.CodeGeneratorConfiguration;
import com.hexacta.booster.codegeneration.metamodel.Class;

/**
 * 
 */
public class MappingAdministratorGeneratorTest extends TestCase {

    public void testCreation() {

        MappingAdministratorGenerator aGenerator = new MappingAdministratorGenerator();
        assertNotNull(aGenerator);
    }

    public void testGenerate() {

        CodeGeneratorConfigurationProvider aProvider = new CodeGeneratorConfigurationProvider();
        CodeGeneratorConfiguration aConfiguration = aProvider.getCodeGeneratorConfiguration();

        MappingAdministratorGenerator aGenerator = new MappingAdministratorGenerator();

        MappingAdministrator generatedMappingAdministrator = aGenerator.generate(aConfiguration);

        MappingAdministrator expectedMappingAdministrator = new MappingAdministrator();

        ReflectionUtils.executePrivateMethod(aGenerator, "generatePackage",
                new java.lang.Class[] { MappingAdministrator.class }, new Object[] { expectedMappingAdministrator });

        ReflectionUtils.executePrivateMethod(aGenerator, "generateImports",
                new java.lang.Class[] { MappingAdministrator.class }, new Object[] { expectedMappingAdministrator });

        ReflectionUtils.executePrivateMethod(aGenerator, "generateClass", new java.lang.Class[] {
                MappingAdministrator.class, CodeGeneratorConfiguration.class }, new Object[] {
                expectedMappingAdministrator, aConfiguration });

        String generatedString = generatedMappingAdministrator.getText();

        String expectedString = expectedMappingAdministrator.getText();

        assertEquals(expectedString, generatedString);
    }

    public void testGeneratePackage() {

        MappingAdministrator aMapping = new MappingAdministrator();
        MappingAdministratorGenerator aGenerator = new MappingAdministratorGenerator();

        ReflectionUtils.executePrivateMethod(aGenerator, "generatePackage",
                new java.lang.Class[] { MappingAdministrator.class }, new Object[] { aMapping });

        // String expectedString = "package " +
        // aConfiguration.getProyectPackageName()+".persistence;\n";
        String expectedString = "package persistence;\n";
        String generatedString = aMapping.getText();

        assertEquals(expectedString, generatedString);
    }

    public void testGenerateImports() {

        MappingAdministrator aMapping = new MappingAdministrator();
        MappingAdministratorGenerator aGenerator = new MappingAdministratorGenerator();

        ReflectionUtils.executePrivateMethod(aGenerator, "generateImports",
                new java.lang.Class[] { MappingAdministrator.class }, new Object[] { aMapping });

        String expectedString = "\n" + "import java.lang.reflect.Field;\n" + "import java.util.ArrayList;\n"
                + "import java.util.Collection;\n" + "import java.util.Iterator;\n" + "import java.util.List;\n";
        String generatedString = aMapping.getText();

        assertEquals(expectedString, generatedString);

    }

    public void testGenerateClass() {

        CodeGeneratorConfigurationProvider aProvider = new CodeGeneratorConfigurationProvider();
        CodeGeneratorConfiguration aConfiguration = aProvider.getCodeGeneratorConfiguration();

        MappingAdministrator expectedMappingAdministrator = new MappingAdministrator();
        MappingAdministratorGenerator aGenerator = new MappingAdministratorGenerator();

        expectedMappingAdministrator.append("\n");
        expectedMappingAdministrator.append("public class MappingAdministrator {\n");
        ReflectionUtils.executePrivateMethod(aGenerator, "generateConstructor",
                new java.lang.Class[] { MappingAdministrator.class }, new Object[] { expectedMappingAdministrator });
        ReflectionUtils.executePrivateMethod(aGenerator, "generateCreateOrUpdateDataObject",
                new java.lang.Class[] { MappingAdministrator.class }, new Object[] { expectedMappingAdministrator });
        ReflectionUtils.executePrivateMethod(aGenerator, "generateUpdateDataObjectColaborators",
                new java.lang.Class[] { MappingAdministrator.class }, new Object[] { expectedMappingAdministrator });
        ReflectionUtils.executePrivateMethod(aGenerator, "generateIsBusinessClass", new java.lang.Class[] {
                MappingAdministrator.class, CodeGeneratorConfiguration.class }, new Object[] {
                expectedMappingAdministrator, aConfiguration });
        ReflectionUtils.executePrivateMethod(aGenerator, "generateCreateOrUpdateBusinessObject",
                new java.lang.Class[] { MappingAdministrator.class }, new Object[] { expectedMappingAdministrator });
        ReflectionUtils.executePrivateMethod(aGenerator, "generateUpdateBusinessObjectColaborators",
                new java.lang.Class[] { MappingAdministrator.class }, new Object[] { expectedMappingAdministrator });
        ReflectionUtils.executePrivateMethod(aGenerator, "generateIsDataClass", new java.lang.Class[] {
                MappingAdministrator.class, CodeGeneratorConfiguration.class }, new Object[] {
                expectedMappingAdministrator, aConfiguration });
        ReflectionUtils.executePrivateMethod(aGenerator, "generateGetAndDeleteDataObject",
                new java.lang.Class[] { MappingAdministrator.class }, new Object[] { expectedMappingAdministrator });
        ReflectionUtils.executePrivateMethod(aGenerator, "generateCreateOrUpdateAndGetBusinessObjects",
                new java.lang.Class[] { MappingAdministrator.class }, new Object[] { expectedMappingAdministrator });
        expectedMappingAdministrator.append("}");

        MappingAdministrator generatedMappingAdministrator = new MappingAdministrator();
        ReflectionUtils.executePrivateMethod(aGenerator, "generateClass", new java.lang.Class[] {
                MappingAdministrator.class, CodeGeneratorConfiguration.class }, new Object[] {
                generatedMappingAdministrator, aConfiguration });

        String generatedString = generatedMappingAdministrator.getText();

        String expectedString = expectedMappingAdministrator.getText();

        assertEquals(expectedString, generatedString);

    }

    public void testGenerateConstructor() {

        MappingAdministrator aMapping = new MappingAdministrator();
        MappingAdministratorGenerator aGenerator = new MappingAdministratorGenerator();

        ReflectionUtils.executePrivateMethod(aGenerator, "generateConstructor",
                new java.lang.Class[] { MappingAdministrator.class }, new Object[] { aMapping });
        String expectedString = "\n" + "	public MappingAdministrator() {\n" + "\n" + "	}" + "\n";

        String generatedString = aMapping.getText();

        assertEquals(expectedString, generatedString);
    }

    public void testGenerateCreateOrUpdateDataObject() {

        MappingAdministrator aMapping = new MappingAdministrator();
        MappingAdministratorGenerator aGenerator = new MappingAdministratorGenerator();

        ReflectionUtils.executePrivateMethod(aGenerator, "generateCreateOrUpdateDataObject",
                new java.lang.Class[] { MappingAdministrator.class }, new Object[] { aMapping });
        String expectedString = "	\n"
                + "	public Object createOrUpdateDataObject(Object businessObject,BusinessObjectDataObjectMapping businessObjectDataObjectMapping) {\n"
                + "	\n" + "		Object dataObject = null;\n" + "	\n"
                + "		if (businessObjectDataObjectMapping.containsBusinessObject(businessObject))\n"
                + "			dataObject = businessObjectDataObjectMapping.getDataObject(businessObject);\n" + "		else {\n"
                + "			try {\n" + "				dataObject = Class.forName(\n"
                + "						businessObject.getClass().getPackage().getName()\n" + "						+ \".persistence.dataobject.\"\n"
                + "						+ businessObject.getClass().getSimpleName())\n" + "						.newInstance();\n"
                + "				businessObjectDataObjectMapping.put(businessObject, dataObject);\n"
                + "			} catch (Exception e) {\n" + "				e.printStackTrace();\n" + "			}\n" + "		}\n"
                + "		updateDataObjectColaborators(businessObject, businessObjectDataObjectMapping,dataObject);\n"
                + "		businessObjectDataObjectMapping.put(businessObject,dataObject);\n" + "		return dataObject;\n"
                + "	}\n";

        String generatedString = aMapping.getText();

        assertEquals(expectedString, generatedString);
    }

    public void testGenerateUpdateDataObjectColaborators() {

        MappingAdministrator aMapping = new MappingAdministrator();
        MappingAdministratorGenerator aGenerator = new MappingAdministratorGenerator();

        ReflectionUtils.executePrivateMethod(aGenerator, "generateUpdateDataObjectColaborators",
                new java.lang.Class[] { MappingAdministrator.class }, new Object[] { aMapping });
        String expectedString = "	\n"
                + "	private void updateDataObjectColaborators(Object businessObject,BusinessObjectDataObjectMapping businessObjectDataObjectMapping,Object dataObject) {\n"
                + "	\n"
                + "		Class dataClass = dataObject.getClass();\n"
                + "		Field[] fields = businessObject.getClass().getDeclaredFields();\n"
                + "	\n"
                + "		for (int i = 0; i < fields.length; i++) {\n"
                + "			fields[i].setAccessible(true);\n"
                + "			try {\n"
                + "				if (isBusinessClass(fields[i].getType())) {\n"
                + "					Field dataObjectField = dataClass.getDeclaredField(fields[i].getName());\n"
                + "					dataObjectField.setAccessible(true);\n"
                + "					Object businessColaboratorObject = fields[i].get(businessObject);\n"
                + "					Object dataColaboratorObject;\n"
                + "					if (businessColaboratorObject != null)\n"
                + "						dataColaboratorObject = createOrUpdateDataObject(businessColaboratorObject,businessObjectDataObjectMapping);\n"
                + "					else\n" + "						dataColaboratorObject = null;\n"
                + "					dataObjectField.set(dataObject, dataColaboratorObject);\n" + "				} else {\n"
                + "					Field dataObjectField = dataClass.getDeclaredField(fields[i].getName());\n"
                + "					dataObjectField.setAccessible(true);\n"
                + "					Object businessColaboratorObject = fields[i].get(businessObject);\n"
                + "					dataObjectField.set(dataObject, businessColaboratorObject);\n" + "				}\n"
                + "			} catch (Exception e) {\n" + "				e.printStackTrace();\n" + "			}\n" + "		}\n" + "	}\n";

        String generatedString = aMapping.getText();

        assertEquals(expectedString, generatedString);

    }

    public void testGenerateIsBusinessClass() {

        CodeGeneratorConfigurationProvider aProvider = new CodeGeneratorConfigurationProvider();
        CodeGeneratorConfiguration aConfiguration = aProvider.getCodeGeneratorConfiguration();

        MappingAdministrator aMapping = new MappingAdministrator();
        MappingAdministratorGenerator aGenerator = new MappingAdministratorGenerator();

        ReflectionUtils.executePrivateMethod(aGenerator, "generateIsBusinessClass", new java.lang.Class[] {
                MappingAdministrator.class, CodeGeneratorConfiguration.class },
                new Object[] { aMapping, aConfiguration });

        String expectedString = "	\n" + "	private boolean isBusinessClass(Class aClass) {\n" + "	\n" + "		return (   ";
        for (Iterator<?> iterator = aConfiguration.getClassList().iterator(); iterator.hasNext();) {
            Class aClass = (Class) iterator.next();
            // expectedString = expectedString +
            // " aClass.getName().equals("+aConfiguration
            // .getProyectPackageName()+"."+aClass.getSimpleName()+".class)";
            expectedString = expectedString + " aClass.getName().equals(" + aClass.getPackage() + "."
                    + aClass.getSimpleName() + ".class)";
            if (iterator.hasNext()) {
                expectedString = expectedString + "\n			|| ";
            }
        }
        expectedString = expectedString + ");\n";
        expectedString = expectedString + "	}\n";

        String generatedString = aMapping.getText();

        assertEquals(expectedString, generatedString);
    }

    public void testGenerateCreateOrUpdateBusinessObject() {

        MappingAdministrator aMapping = new MappingAdministrator();
        MappingAdministratorGenerator aGenerator = new MappingAdministratorGenerator();

        ReflectionUtils.executePrivateMethod(aGenerator, "generateCreateOrUpdateBusinessObject",
                new java.lang.Class[] { MappingAdministrator.class }, new Object[] { aMapping });
        String expectedString = "	\n"
                + "	public Object createOrUpdateBusinessObject(Object dataObject,BusinessObjectDataObjectMapping businessObjectDataObjectMapping) {\n"
                + "	\n"
                + "		Object businessObject = null;\n"
                + "		if (businessObjectDataObjectMapping.containsDataObject(dataObject))\n"
                + "			businessObject = businessObjectDataObjectMapping.getBusinessObject(dataObject);\n"
                + "		else {\n"
                + "			try {\n"
                + "				businessObject = Class.forName(dataObject.getClass().getName().replace(\"persistence.dataobject.\", \"\")).newInstance();\n"
                + "				businessObjectDataObjectMapping.put(businessObject, dataObject);\n"
                + "			} catch (Exception e) {\n" + "				e.printStackTrace();\n" + "			}\n" + "		}\n"
                + "		updateBusinessObjectColaborators(dataObject,businessObjectDataObjectMapping, businessObject);\n"
                + "		businessObjectDataObjectMapping.put(businessObject,dataObject);\n" + "		return businessObject;\n"
                + "	}\n";

        String generatedString = aMapping.getText();

        assertEquals(expectedString, generatedString);
    }

    public void testGenerateUpdateBusinessObjectColaborators() {

        MappingAdministrator aMapping = new MappingAdministrator();
        MappingAdministratorGenerator aGenerator = new MappingAdministratorGenerator();

        ReflectionUtils.executePrivateMethod(aGenerator, "generateUpdateBusinessObjectColaborators",
                new java.lang.Class[] { MappingAdministrator.class }, new Object[] { aMapping });
        String expectedString = "	\n"
                + "	private void updateBusinessObjectColaborators(Object dataObject,BusinessObjectDataObjectMapping businessObjectDataObjectMapping,Object businessObject) {\n"
                + "	\n"
                + "		Class businessClass = businessObject.getClass();\n"
                + "		Field[] fields = dataObject.getClass().getDeclaredFields();\n"
                + "	\n"
                + "		for (int i = 0; i < fields.length; i++) {\n"
                + "			fields[i].setAccessible(true);\n"
                + "			try {\n"
                + "				if (isDataClass(fields[i].getType())) {\n"
                + "					Field businessObjectField = businessClass.getDeclaredField(fields[i].getName());\n"
                + "					businessObjectField.setAccessible(true);\n"
                + "					Object dataColaboratorObject = fields[i].get(dataObject);\n"
                + "					Object businessColaboratorObject;\n"
                + "					if (dataColaboratorObject != null)\n"
                + "						businessColaboratorObject = createOrUpdateDataObject(dataColaboratorObject,businessObjectDataObjectMapping);\n"
                + "					else\n" + "						businessColaboratorObject = null;\n"
                + "					businessObjectField.set(businessObject,businessColaboratorObject);\n"
                + "				} else if (!fields[i].getName().equalsIgnoreCase(\"ormId\")) {\n"
                + "					Field businessObjectField = businessClass.getDeclaredField(fields[i].getName());\n"
                + "					businessObjectField.setAccessible(true);\n"
                + "					Object dataColaboratorObject = fields[i].get(dataObject);\n"
                + "					businessObjectField.set(businessObject,dataColaboratorObject);\n" + "				}\n"
                + "			} catch (Exception e) {\n" + "				e.printStackTrace();\n" + "			}\n" + "		}\n" + "	}\n";

        String generatedString = aMapping.getText();

        assertEquals(expectedString, generatedString);
    }

    public void testGenerateIsDataClass() {

        CodeGeneratorConfigurationProvider aProvider = new CodeGeneratorConfigurationProvider();
        CodeGeneratorConfiguration aConfiguration = aProvider.getCodeGeneratorConfiguration();

        MappingAdministrator aMapping = new MappingAdministrator();
        MappingAdministratorGenerator aGenerator = new MappingAdministratorGenerator();

        ReflectionUtils.executePrivateMethod(aGenerator, "generateIsDataClass", new java.lang.Class[] {
                MappingAdministrator.class, CodeGeneratorConfiguration.class },
                new Object[] { aMapping, aConfiguration });

        String expectedString = "	\n" + "	private boolean isDataClass(Class aClass) {\n" + "	\n" + "		return (   ";

        for (Iterator<?> iterator = aConfiguration.getClassList().iterator(); iterator.hasNext();) {
            Class aClass = (Class) iterator.next();
            // expectedString = expectedString +
            // " aClass.getName().equals("+aConfiguration
            // .getProyectPackageName()
            // +".persistence.dataobject."+aClass.getSimpleName()+".class)";
            expectedString = expectedString + " aClass.getName().equals(" + aClass.getPackage()
                    + ".persistence.dataobject." + aClass.getSimpleName() + ".class)";
            if (iterator.hasNext()) {
                expectedString = expectedString + "\n			|| ";
            }
        }
        expectedString = expectedString + ");\n";
        expectedString = expectedString + "	}\n";

        String generatedString = aMapping.getText();

        assertEquals(expectedString, generatedString);
    }

    public void testGenerateGetAndDeleteDataObject() {

        MappingAdministrator aMapping = new MappingAdministrator();
        MappingAdministratorGenerator aGenerator = new MappingAdministratorGenerator();

        ReflectionUtils.executePrivateMethod(aGenerator, "generateGetAndDeleteDataObject",
                new java.lang.Class[] { MappingAdministrator.class }, new Object[] { aMapping });

        String expectedString = "	\n"
                + "	public Object getAndDeleteDataObject(Object businessObject,BusinessObjectDataObjectMapping businessObjectDataObjectMapping) {\n"
                + "	\n" + "		Object dataObject = businessObjectDataObjectMapping.getDataObject(businessObject);\n"
                + "		businessObjectDataObjectMapping.remove(dataObject);\n" + "		return dataObject;\n" + "	}\n";

        String generatedString = aMapping.getText();

        assertEquals(expectedString, generatedString);
    }

    public void testGenerateCreateOrUpdateAndGetBusinessObjects() {

        MappingAdministrator aMapping = new MappingAdministrator();
        MappingAdministratorGenerator aGenerator = new MappingAdministratorGenerator();

        ReflectionUtils.executePrivateMethod(aGenerator, "generateCreateOrUpdateAndGetBusinessObjects",
                new java.lang.Class[] { MappingAdministrator.class }, new Object[] { aMapping });
        String expectedString = "	\n"
                + "	public Collection createOrUpdateAndGetBusinessObjects(Collection dataObjects,BusinessObjectDataObjectMapping businessObjectDataObjectMapping) {\n"
                + "	\n" + "		List businessObjects = new ArrayList<Object>();\n" + "	\n"
                + "		for (Iterator iterator = dataObjects.iterator(); iterator.hasNext();) {\n"
                + "			Object dataObject = (Object) iterator.next();\n"
                + "			businessObjects.add(createOrUpdateBusinessObject(dataObject,businessObjectDataObjectMapping));\n"
                + "		}\n" + "		return businessObjects;\n" + "	}\n";

        String generatedString = aMapping.getText();

        assertEquals(expectedString, generatedString);
    }
}
