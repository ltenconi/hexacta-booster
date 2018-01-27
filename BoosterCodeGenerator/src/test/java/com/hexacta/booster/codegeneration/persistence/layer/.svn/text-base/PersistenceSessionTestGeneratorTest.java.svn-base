package com.hexacta.booster.codegeneration.persistence.layer;

import java.util.Iterator;

import junit.framework.TestCase;

import org.apache.log4j.Logger;

import test.hexacta.booster.providers.CodeGeneratorConfigurationProvider;
import ar.com.hexacta.utils.reflection.ReflectionUtils;

import com.hexacta.booster.codegeneration.configuration.CodeGeneratorConfiguration;
import com.hexacta.booster.codegeneration.configuration.NotSupportedClassException;
import com.hexacta.booster.codegeneration.metamodel.Class;
import com.hexacta.booster.codegeneration.metamodel.Field;
import com.hexacta.booster.codegeneration.metamodel.NotAnEntityClassException;
import com.hexacta.booster.codegeneration.metamodel.Type;

/**
 * 
 */
public class PersistenceSessionTestGeneratorTest extends TestCase {

    /**
     * Log4j logger.
     */

    static final Logger logger = Logger.getLogger(PersistenceSessionTestGeneratorTest.class.getSimpleName());

    @Override
    protected void setUp() throws Exception {
        super.setUp();
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    public void testCreation() {

        PersistenceSessionTestGenerator aPersistenceSessionTestGenerator = new PersistenceSessionTestGenerator();
        assertNotNull(aPersistenceSessionTestGenerator);
    }

    public void testGenerator() throws NotSupportedClassException, NotAnEntityClassException {

        PersistenceSessionTestGenerator aPersistenceSessionTestGenerator = new PersistenceSessionTestGenerator();
        CodeGeneratorConfiguration aCodeGeneratorConfiguration = new CodeGeneratorConfigurationProvider()
                .getCodeGeneratorConfiguration();
        PersistenceSessionTest actualPersistenceSessionTest = aPersistenceSessionTestGenerator
                .generate(aCodeGeneratorConfiguration);

        PersistenceSessionTest expectedPersistenceSessionTest = new PersistenceSessionTest();

        ReflectionUtils
                .executePrivateMethod(aPersistenceSessionTestGenerator, "generatePackage",
                        new java.lang.Class[] { PersistenceSessionTest.class },
                        new Object[] { expectedPersistenceSessionTest });
        ReflectionUtils
                .executePrivateMethod(aPersistenceSessionTestGenerator, "generateImports",
                        new java.lang.Class[] { PersistenceSessionTest.class },
                        new Object[] { expectedPersistenceSessionTest });
        ReflectionUtils.executePrivateMethod(aPersistenceSessionTestGenerator, "generateClass", new java.lang.Class[] {
                PersistenceSessionTest.class, CodeGeneratorConfiguration.class }, new Object[] {
                expectedPersistenceSessionTest, aCodeGeneratorConfiguration });

        assertEquals(expectedPersistenceSessionTest.getText(), actualPersistenceSessionTest.getText());

    }

    public void testGeneratePackage() {

        PersistenceSessionTestGenerator aPersistenceSessionTestGenerator = new PersistenceSessionTestGenerator();

        PersistenceSessionTest actualPersistenceSessionTest = new PersistenceSessionTest();

        ReflectionUtils.executePrivateMethod(aPersistenceSessionTestGenerator, "generatePackage",
                new java.lang.Class[] { PersistenceSessionTest.class }, new Object[] { actualPersistenceSessionTest });

        String expectedPackege = "package "
        /* + aCodeGeneratorConfiguration.getProyectPackageName() */
        + "persistence.tests;\n";

        assertEquals(expectedPackege, actualPersistenceSessionTest.getText());

    }

    public void testGenerateImports() {

        PersistenceSessionTestGenerator aPersistenceSessionTestGenerator = new PersistenceSessionTestGenerator();

        PersistenceSessionTest actualPersistenceSessionTest = new PersistenceSessionTest();

        ReflectionUtils.executePrivateMethod(aPersistenceSessionTestGenerator, "generateImports",
                new java.lang.Class[] { PersistenceSessionTest.class }, new Object[] { actualPersistenceSessionTest });

        String expectedImports = "\n" + "import java.util.Collection; \n " + "import java.util.Iterator; \n"
                + "import java.lang.reflect.Field;\n" + "import " + /*
                                                                     * aCodeGeneratorConfiguration.
                                                                     * getProyectPackageName
                                                                     * () +
                                                                     */"persistence.PersistenceSession; \n" + "import "
                + /*
                   * aCodeGeneratorConfiguration.getProyectPackageName () +
                   */"persistence.PersistenceSystem; \n" + "import junit.framework.TestCase; \n";

        assertEquals(expectedImports, actualPersistenceSessionTest.getText());

    }

    public void testGenerateClass() {

        PersistenceSessionTestGenerator aPersistenceSessionTestGenerator = new PersistenceSessionTestGenerator();
        CodeGeneratorConfiguration aCodeGeneratorConfiguration = new CodeGeneratorConfigurationProvider()
                .getCodeGeneratorConfiguration();
        PersistenceSessionTest actualPersistenceSessionTest = new PersistenceSessionTest();

        ReflectionUtils.executePrivateMethod(aPersistenceSessionTestGenerator, "generateClass", new java.lang.Class[] {
                PersistenceSessionTest.class, CodeGeneratorConfiguration.class }, new Object[] {
                actualPersistenceSessionTest, aCodeGeneratorConfiguration });

        String expectedClassText = "\n" + "public class " + "PersistenceSessionTest extends TestCase{" + "\n\n";

        expectedClassText = expectedClassText
                + (String) ReflectionUtils.executePrivateMethod(aPersistenceSessionTestGenerator, "generateTestCreate",
                        new java.lang.Class[] {}, new Object[] {}) + "\n";
        expectedClassText = expectedClassText
                + (String) ReflectionUtils.executePrivateMethod(aPersistenceSessionTestGenerator,
                        "generateTestPersist", new java.lang.Class[] { CodeGeneratorConfiguration.class },
                        new Object[] { aCodeGeneratorConfiguration }) + "\n";
        expectedClassText = expectedClassText
                + (String) ReflectionUtils.executePrivateMethod(aPersistenceSessionTestGenerator, "generateTestDelete",
                        new java.lang.Class[] { CodeGeneratorConfiguration.class },
                        new Object[] { aCodeGeneratorConfiguration }) + "\n";
        expectedClassText = expectedClassText
                + (String) ReflectionUtils.executePrivateMethod(aPersistenceSessionTestGenerator, "generateTestFind",
                        new java.lang.Class[] { CodeGeneratorConfiguration.class },
                        new Object[] { aCodeGeneratorConfiguration }) + "\n";
        expectedClassText = expectedClassText
                + (String) ReflectionUtils.executePrivateMethod(aPersistenceSessionTestGenerator, "generateTestUpdate",
                        new java.lang.Class[] { CodeGeneratorConfiguration.class },
                        new Object[] { aCodeGeneratorConfiguration }) + "\n";
        expectedClassText = expectedClassText
                + (String) ReflectionUtils.executePrivateMethod(aPersistenceSessionTestGenerator,
                        "generateTestFindAll", new java.lang.Class[] { CodeGeneratorConfiguration.class },
                        new Object[] { aCodeGeneratorConfiguration }) + "\n";
        expectedClassText = expectedClassText
                + (String) ReflectionUtils.executePrivateMethod(aPersistenceSessionTestGenerator,
                        "generateDeleteDataBase", new java.lang.Class[] { CodeGeneratorConfiguration.class },
                        new Object[] { aCodeGeneratorConfiguration }) + "\n";
        expectedClassText = expectedClassText + "}\n";

        assertEquals(expectedClassText, actualPersistenceSessionTest.getText());

    }

    public void testGenerateTestCreate() {

        PersistenceSessionTestGenerator aPersistenceSessionTestGenerator = new PersistenceSessionTestGenerator();

        String actualTestCreate = (String) ReflectionUtils.executePrivateMethod(aPersistenceSessionTestGenerator,
                "generateTestCreate", new java.lang.Class[] {}, new Object[] {});

        assertEquals("	public void testCreation(){                                    \n"
                + "		PersistenceSystem persistenceSystem = new PersistenceSystem();\n"
                + "		PersistenceSession persistenceSession = persistenceSystem.getPersistenceSession();\n"
                + " 		assertNotNull(persistenceSession);                             \n" + "	}\n", actualTestCreate);

    }

    public void testGenerateTestPersist() {

        PersistenceSessionTestGenerator aPersistenceSessionTestGenerator = new PersistenceSessionTestGenerator();
        CodeGeneratorConfiguration aCodeGeneratorConfiguration = new CodeGeneratorConfigurationProvider()
                .getCodeGeneratorConfiguration();

        String actualTestPersist = (String) ReflectionUtils.executePrivateMethod(aPersistenceSessionTestGenerator,
                "generateTestPersist", new java.lang.Class[] { CodeGeneratorConfiguration.class },
                new Object[] { aCodeGeneratorConfiguration });

        String expectedTestPersist;

        if (aCodeGeneratorConfiguration.getClassList().iterator().hasNext()) {

            com.hexacta.booster.codegeneration.metamodel.Class classToPersist = aCodeGeneratorConfiguration
                    .getClassList().iterator().next();

            expectedTestPersist = "	public void testPersist(){ \n"
                    + "		deleteDataBase(); \n "
                    + "		PersistenceSystem persistenceSystem = new PersistenceSystem();\n"
                    + "		PersistenceSession persistenceSession = persistenceSystem.getPersistenceSession();\n"
                    + "		"
                    + classToPersist.getName()
                    + " "
                    + classToPersist.getSimpleName().toLowerCase()
                    + " = "
                    + (String) ReflectionUtils.executePrivateMethod(aPersistenceSessionTestGenerator,
                            "generateConstructorCall", new java.lang.Class[] { Class.class,
                                    CodeGeneratorConfiguration.class }, new Object[] { classToPersist,
                                    aCodeGeneratorConfiguration }) + "; \n" + "		persistenceSession.persist("
                    + classToPersist.getSimpleName().toLowerCase() + "); \n"
                    + "		Collection collection = persistenceSession.find("
                    + classToPersist.getSimpleName().toLowerCase() + "); \n"
                    + "		Object recoveredObject = collection.iterator().next();\n " + "		deleteDataBase(); \n"
                    + "		assertEquals(" + classToPersist.getSimpleName().toLowerCase() + ", recoveredObject); \n"
                    + "	}";

        } else {
            expectedTestPersist = "	public void testPersist(){\n" + "	}\n";
        }

        assertEquals(expectedTestPersist, actualTestPersist);

    }

    public void testGenerateTestDelete() {

        PersistenceSessionTestGenerator aPersistenceSessionTestGenerator = new PersistenceSessionTestGenerator();
        CodeGeneratorConfiguration aCodeGeneratorConfiguration = new CodeGeneratorConfigurationProvider()
                .getCodeGeneratorConfiguration();

        try {
            String actualTestDelete = (String) ReflectionUtils.executePrivateMethod(aPersistenceSessionTestGenerator,
                    "generateTestDelete", new java.lang.Class[] { CodeGeneratorConfiguration.class },
                    new Object[] { aCodeGeneratorConfiguration });

            String expectedTestDelete;

            if (aCodeGeneratorConfiguration.getClassList().iterator().hasNext()) {

                com.hexacta.booster.codegeneration.metamodel.Class classToPersist = aCodeGeneratorConfiguration
                        .getClassList().iterator().next();

                expectedTestDelete =

                "	public void testDelete(){ \n"
                        + "		deleteDataBase(); \n "
                        + "		PersistenceSystem persistenceSystem = new PersistenceSystem();\n"
                        + "		PersistenceSession persistenceSession = persistenceSystem.getPersistenceSession();\n"
                        + "		"
                        + classToPersist.getName()
                        + " "
                        + classToPersist.getSimpleName().toLowerCase()
                        + " = "
                        + (String) ReflectionUtils.executePrivateMethod(aPersistenceSessionTestGenerator,
                                "generateConstructorCall", new java.lang.Class[] { Class.class,
                                        CodeGeneratorConfiguration.class }, new Object[] { classToPersist,
                                        aCodeGeneratorConfiguration }) + "; \n" + "		persistenceSession.persist("
                        + classToPersist.getSimpleName().toLowerCase() + ");\n" + "		persistenceSession.delete("
                        + classToPersist.getSimpleName().toLowerCase() + "); \n"
                        + "		Collection collection = persistenceSession.find("
                        + classToPersist.getSimpleName().toLowerCase() + "); \n" + "		deleteDataBase(); \n"
                        + "		assertEquals(0,collection.size()); \n" + "	}";

            } else {
                expectedTestDelete = "	public void testDelete(){\n" + "	}\n";
            }

            assertEquals(expectedTestDelete, actualTestDelete);
        } catch (Exception e) {
            logger.error("Se ha levantado una excepción: " + e.getMessage());
            fail();
        }
    }

    public void testGenerateTestFind() {

        PersistenceSessionTestGenerator aPersistenceSessionTestGenerator = new PersistenceSessionTestGenerator();
        CodeGeneratorConfiguration aCodeGeneratorConfiguration = new CodeGeneratorConfigurationProvider()
                .getCodeGeneratorConfiguration();

        try {
            String actualTestFind = (String) ReflectionUtils.executePrivateMethod(aPersistenceSessionTestGenerator,
                    "generateTestFind", new java.lang.Class[] { CodeGeneratorConfiguration.class },
                    new Object[] { aCodeGeneratorConfiguration });

            String expectedTestFind;

            if (aCodeGeneratorConfiguration.getClassList().iterator().hasNext()) {

                com.hexacta.booster.codegeneration.metamodel.Class classToPersist = aCodeGeneratorConfiguration
                        .getClassList().iterator().next();

                expectedTestFind = "\n"
                        + "	public void testFind(){ \n"
                        + "		deleteDataBase(); \n "
                        + "		PersistenceSystem persistenceSystem = new PersistenceSystem();\n"
                        + "		PersistenceSession persistenceSession = persistenceSystem.getPersistenceSession();\n"
                        + "		"
                        + classToPersist.getName()
                        + " "
                        + classToPersist.getSimpleName().toLowerCase()
                        + " = "
                        + (String) ReflectionUtils.executePrivateMethod(aPersistenceSessionTestGenerator,
                                "generateConstructorCall", new java.lang.Class[] { Class.class,
                                        CodeGeneratorConfiguration.class }, new Object[] { classToPersist,
                                        aCodeGeneratorConfiguration }) + "; \n" + "		persistenceSession.persist("
                        + classToPersist.getSimpleName().toLowerCase() + "); \n"
                        + "		Collection collection = persistenceSession.find("
                        + classToPersist.getSimpleName().toLowerCase() + "); \n"
                        + "		Object recoveredObject = collection.iterator().next();\n " + "		deleteDataBase(); \n"
                        + "		assertEquals(" + classToPersist.getSimpleName().toLowerCase() + ", recoveredObject); \n"
                        + "	}";

            } else {
                expectedTestFind = "	public void testFind(){\n" + "	}\n";
            }

            assertEquals(expectedTestFind, actualTestFind);
        } catch (Exception e) {
            logger.error("Se ha levantado una excepción: " + e.getMessage());
            fail();
        }
    }

    public void testGenerateDeleteDataBase() {

        PersistenceSessionTestGenerator aPersistenceSessionTestGenerator = new PersistenceSessionTestGenerator();
        CodeGeneratorConfiguration aCodeGeneratorConfiguration = new CodeGeneratorConfigurationProvider()
                .getCodeGeneratorConfiguration();

        try {
            String actualDeleteDataBase = (String) ReflectionUtils.executePrivateMethod(
                    aPersistenceSessionTestGenerator, "generateDeleteDataBase",
                    new java.lang.Class[] { CodeGeneratorConfiguration.class },
                    new Object[] { aCodeGeneratorConfiguration });

            String expectedDeleteDataBase = "\n" + "	private void deleteDataBase(){\n"
                    + "		PersistenceSystem persistenceSystem = new PersistenceSystem();\n"
                    + "		PersistenceSession aPersistenceSession = persistenceSystem.getPersistenceSession();\n";

            for (Iterator<com.hexacta.booster.codegeneration.metamodel.Class> iterator = aCodeGeneratorConfiguration
                    .getClassList().iterator(); iterator.hasNext();) {
                com.hexacta.booster.codegeneration.metamodel.Class aClass = iterator.next();

                expectedDeleteDataBase = expectedDeleteDataBase + "\n" + "        // " + aClass.getSimpleName() + "\n"
                        + "        Collection objects" + aClass.getSimpleName() + " = aPersistenceSession.findAll(new "
                        + aClass.getName() + "());\n" + "\n" + "		" + "for (Iterator iterator = objects"
                        + aClass.getSimpleName() + ".iterator(); iterator.hasNext();) { \n " + "		" + aClass.getName()
                        + " object = (" + aClass.getName() + ") iterator.next(); \n "
                        + "		aPersistenceSession.delete(object); \n" + "		} \n";
            }
            expectedDeleteDataBase = expectedDeleteDataBase + "	}\n";

            assertEquals(expectedDeleteDataBase, actualDeleteDataBase);
        } catch (Exception e) {
            logger.error("Se ha levantado una excepción: " + e.getMessage());
            fail();
        }
    }

    public void testFindAll() {

        PersistenceSessionTestGenerator aPersistenceSessionTestGenerator = new PersistenceSessionTestGenerator();
        CodeGeneratorConfiguration aCodeGeneratorConfiguration = new CodeGeneratorConfigurationProvider()
                .getCodeGeneratorConfiguration();

        String expectedText = "	public void testFindAll(){\n" + "\n" + "		deleteDataBase();\n"
                + "		PersistenceSystem persistenceSystem = new PersistenceSystem();\n"
                + "		PersistenceSession persistenceSession = persistenceSystem.getPersistenceSession();\n"
                + "		test.model.House house = new test.model.House();\n" + "		persistenceSession.persist(house);\n"
                + "		house = new test.model.House();\n" + "		persistenceSession.persist(house);\n"
                + "		Collection collection = persistenceSession.findAll(house);\n" + "		deleteDataBase();\n" + "\n"
                + "		assertEquals(2,collection.size());\n" + "	}\n";

        assertEquals(expectedText, (String) ReflectionUtils.executePrivateMethod(aPersistenceSessionTestGenerator,
                "generateTestFindAll", new java.lang.Class[] { CodeGeneratorConfiguration.class },
                new Object[] { aCodeGeneratorConfiguration }));
    }

    public void testGenerateTestUpdate() {

        PersistenceSessionTestGenerator aPersistenceSessionTestGenerator = new PersistenceSessionTestGenerator();
        CodeGeneratorConfiguration aCodeGeneratorConfiguration = new CodeGeneratorConfigurationProvider()
                .getCodeGeneratorConfiguration();

        String actualDeleteDataBase = (String) ReflectionUtils.executePrivateMethod(aPersistenceSessionTestGenerator,
                "generateTestUpdate", new java.lang.Class[] { CodeGeneratorConfiguration.class },
                new Object[] { aCodeGeneratorConfiguration });

        String expectedDeleteDataBase;

        int modificablePropertyPosition = 0;
        Type propertyType = null;
        String objectVarName = null;
        Class updateClass = null;

        try {
            updateClass = aCodeGeneratorConfiguration.getClassList().iterator().next();
            objectVarName = updateClass.getSimpleName().toLowerCase();
            int numberOfAttributes = updateClass.getDeclaredFields().length;
            if (numberOfAttributes == 1) {
                expectedDeleteDataBase = "public void testUpdate() throws Exception{\n" + "\n" + "     "
                        + "assert(true); \n" + "} \n";

            } else {
                modificablePropertyPosition = modificablePropertyPosition(updateClass, aCodeGeneratorConfiguration
                        .getClassIdMap().getId(updateClass.getName()));
                propertyType = updateClass.getDeclaredFields()[modificablePropertyPosition].getType();

            }
        } catch (Exception e) {
            logger.error("Se ha levantado una excepción: " + e.getMessage());
        }
        expectedDeleteDataBase = "	public void testUpdate() throws Exception{\n" + "\n" + "		deleteDataBase();\n"
                + "		PersistenceSystem persistenceSystem = new PersistenceSystem();\n"
                + "		PersistenceSession persistenceSession = persistenceSystem.getPersistenceSession();\n" + "		"
                + updateClass.getName()
                + " "
                + objectVarName
                + " = "
                + (String) ReflectionUtils.executePrivateMethod(aPersistenceSessionTestGenerator,
                        "generateConstructorCall", new java.lang.Class[] { Class.class,
                                CodeGeneratorConfiguration.class }, new Object[] { updateClass,
                                aCodeGeneratorConfiguration })
                + "; \n"
                + "		persistenceSession.persist("
                + objectVarName
                + "); \n"
                + "\n"
                + "		"
                + "Field modificableField = "
                + objectVarName
                + ".getClass().getDeclaredFields()["
                + Integer.toString(modificablePropertyPosition)
                + "]; \n"
                + "		"
                + "modificableField.setAccessible(true); \n"
                + "		"
                + "modificableField.set("
                + objectVarName
                + ","
                + (String) ReflectionUtils.executePrivateMethod(aPersistenceSessionTestGenerator,
                        "generateActualParameter",
                        new java.lang.Class[] { Type.class, CodeGeneratorConfiguration.class }, new Object[] {
                                propertyType, aCodeGeneratorConfiguration })
                + ");\n"
                + "		persistenceSession.update("
                + objectVarName
                + "); \n"
                + "\n"
                + "		Collection collection = persistenceSession.find("
                + objectVarName
                + ");\n"
                + "		Object recoveredObject = collection.iterator().next();\n"
                + "		deleteDataBase();\n"
                + "		assertEquals("
                + objectVarName
                + ",recoveredObject);\n"
                + "		assertEquals(1, collection.size());\n" + "	} \n";
        assertEquals(expectedDeleteDataBase, actualDeleteDataBase);

    }

    public static int modificablePropertyPosition(final Class aClass, final String classId) {

        Field[] fieldsSet = aClass.getDeclaredFields();
        int i = 0;
        int modificablePropertyPosition = i;

        boolean found = false;
        while (i < fieldsSet.length && !found) {
            if (!fieldsSet[i].getName().equalsIgnoreCase(classId)) {
                modificablePropertyPosition = i;
                found = true;
            }
            i++;
        }
        return modificablePropertyPosition;
    }
}
