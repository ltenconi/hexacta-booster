package com.hexacta.booster.codegeneration.persistence.layer;

/**
 * This class generates the tests for PersistenceSession
 */

import java.util.Iterator;

import org.apache.log4j.Logger;

import com.hexacta.booster.codegeneration.configuration.ClassFinder;
import com.hexacta.booster.codegeneration.configuration.CodeGeneratorConfiguration;
import com.hexacta.booster.codegeneration.configuration.NotSupportedClassException;
import com.hexacta.booster.codegeneration.metamodel.Class;
import com.hexacta.booster.codegeneration.metamodel.Constructor;
import com.hexacta.booster.codegeneration.metamodel.Field;
import com.hexacta.booster.codegeneration.metamodel.Type;
import com.hexacta.booster.project.configuration.JavaProjectType;

/**
 * 
 */
public class PersistenceSessionTestGenerator {
    /**
     * Log4j logger.
     */
    static final Logger logger = Logger.getLogger(PersistenceSessionTestGenerator.class);

    public PersistenceSessionTestGenerator() {
        // PropertyConfigurator.configure("log4j.properties");
    }

    public PersistenceSessionTest generate(final CodeGeneratorConfiguration aCodeGeneratorConfiguration)
            throws NotSupportedClassException {

        PersistenceSessionTest persistenceSessionTest = new PersistenceSessionTest();
        generatePackage(persistenceSessionTest);
        generateImports(persistenceSessionTest);
        generateClass(persistenceSessionTest, aCodeGeneratorConfiguration);

        return persistenceSessionTest;

    }

    private void generatePackage(final PersistenceSessionTest aPersistenceSessionTest) {

        aPersistenceSessionTest.append("package "
        /* + aCodeGeneratorConfiguration.getProyectPackageName() */
        + "persistence.tests;\n");
    }

    private void generateImports(final PersistenceSessionTest aPersistenceSessionTest) {

        String imports = new String("\n" + "import java.util.Collection; \n " + "import java.util.Iterator; \n"
                + "import java.lang.reflect.Field;\n" + "import " + /*
                                                                     * aCodeGeneratorConfiguration.
                                                                     * getProyectPackageName
                                                                     * () +
                                                                     */"persistence.PersistenceSession; \n" + "import "
                + /*
                   * aCodeGeneratorConfiguration.getProyectPackageName( ) +
                   */"persistence.PersistenceSystem; \n" + "import junit.framework.TestCase; \n");

        aPersistenceSessionTest.append(imports);

    }

    private void generateClass(final PersistenceSessionTest aPersistenceSessionTest,
            final CodeGeneratorConfiguration aCodeGeneratorConfiguration) throws NotSupportedClassException {

        String classText = new String("\n" + "public class " + "PersistenceSessionTest extends TestCase{" + "\n\n"
                + generateTestCreate() + "\n" + generateTestPersist(aCodeGeneratorConfiguration) + "\n"
                + generateTestDelete(aCodeGeneratorConfiguration) + "\n"
                + generateTestFind(aCodeGeneratorConfiguration) + "\n"
                + generateTestUpdate(aCodeGeneratorConfiguration) + "\n"
                + generateTestFindAll(aCodeGeneratorConfiguration) + "\n"
                + generateDeleteDataBase(aCodeGeneratorConfiguration) + "\n" + "}\n");
        aPersistenceSessionTest.append(classText);

    }

    private String generateTestDelete(final CodeGeneratorConfiguration aCodeGeneratorConfiguration)
            throws NotSupportedClassException {

        if (aCodeGeneratorConfiguration.getClassList().iterator().hasNext()) {

            Class classToPersist = aCodeGeneratorConfiguration.getClassList().iterator().next();

            String testFind =

            "	public void testDelete(){ \n" + "		deleteDataBase(); \n "
                    + "		PersistenceSystem persistenceSystem = new PersistenceSystem();\n"
                    + "		PersistenceSession persistenceSession = persistenceSystem.getPersistenceSession();\n" + "		"
                    + classToPersist.getName() + " " + classToPersist.getSimpleName().toLowerCase() + " = "
                    + generateConstructorCall(classToPersist, aCodeGeneratorConfiguration) + "; \n"
                    + "		persistenceSession.persist(" + classToPersist.getSimpleName().toLowerCase() + ");\n"
                    + "		persistenceSession.delete(" + classToPersist.getSimpleName().toLowerCase() + "); \n"
                    + "		Collection collection = persistenceSession.find("
                    + classToPersist.getSimpleName().toLowerCase() + "); \n" + "		deleteDataBase(); \n"
                    + "		assertEquals(0,collection.size()); \n" + "	}";

            return testFind;
        } else
            return "	public void testDelete(){\n" + "	}\n";

    }

    public String generateTestFind(final CodeGeneratorConfiguration aCodeGeneratorConfiguration)
            throws NotSupportedClassException {

        if (aCodeGeneratorConfiguration.getClassList().iterator().hasNext()) {

            Class classToPersist = aCodeGeneratorConfiguration.getClassList().iterator().next();

            String testFind = "\n" + "	public void testFind(){ \n" + "		deleteDataBase(); \n "
                    + "		PersistenceSystem persistenceSystem = new PersistenceSystem();\n"
                    + "		PersistenceSession persistenceSession = persistenceSystem.getPersistenceSession();\n" + "		"
                    + classToPersist.getName() + " " + classToPersist.getSimpleName().toLowerCase() + " = "
                    + generateConstructorCall(classToPersist, aCodeGeneratorConfiguration) + "; \n"
                    + "		persistenceSession.persist(" + classToPersist.getSimpleName().toLowerCase() + "); \n"
                    + "		Collection collection = persistenceSession.find("
                    + classToPersist.getSimpleName().toLowerCase() + "); \n"
                    + "		Object recoveredObject = collection.iterator().next();\n " + "		deleteDataBase(); \n"
                    + "		assertEquals(" + classToPersist.getSimpleName().toLowerCase() + ", recoveredObject); \n"
                    + "	}";

            return testFind;

        } else
            return "	public void testFind(){\n" + "	}\n";

    }

    private String generateTestPersist(final CodeGeneratorConfiguration aCodeGeneratorConfiguration)
            throws NotSupportedClassException {

        if (aCodeGeneratorConfiguration.getClassList().iterator().hasNext()) {

            Class classToPersist = aCodeGeneratorConfiguration.getClassList().iterator().next();

            String testPersist = "	public void testPersist(){ \n" + "		deleteDataBase(); \n "
                    + "		PersistenceSystem persistenceSystem = new PersistenceSystem();\n"
                    + "		PersistenceSession persistenceSession = persistenceSystem.getPersistenceSession();\n" + "		"
                    + classToPersist.getName() + " " + classToPersist.getSimpleName().toLowerCase() + " = "
                    + generateConstructorCall(classToPersist, aCodeGeneratorConfiguration) + "; \n"
                    + "		persistenceSession.persist(" + classToPersist.getSimpleName().toLowerCase() + "); \n"
                    + "		Collection collection = persistenceSession.find("
                    + classToPersist.getSimpleName().toLowerCase() + "); \n"
                    + "		Object recoveredObject = collection.iterator().next();\n " + "		deleteDataBase(); \n"
                    + "		assertEquals(" + classToPersist.getSimpleName().toLowerCase() + ", recoveredObject); \n"
                    + "	}";

            return testPersist;

        } else
            return "	public void testPersist(){\n" + "	}\n";

    }

    private String generateTestCreate() {

        String testCreate = "	public void testCreation(){                                    \n"
                + "		PersistenceSystem persistenceSystem = new PersistenceSystem();\n"
                + "		PersistenceSession persistenceSession = persistenceSystem.getPersistenceSession();\n"
                + " 		assertNotNull(persistenceSession);                             \n" + "	}\n";

        return testCreate;
    }

    private String generateTestUpdate(final CodeGeneratorConfiguration aCodeGeneratorConfiguration)
            throws NotSupportedClassException {

        String modificablePropertyPosition = null;
        Type propertyType = null;
        String objectVarName = null;
        Class updateClass = null;

        try {
            updateClass = aCodeGeneratorConfiguration.getClassList().iterator().next();
            objectVarName = updateClass.getSimpleName().toLowerCase();
            Field[] fieldsSet = ClassFinder.find(updateClass.getName(), aCodeGeneratorConfiguration.getClassList(),
                    new JavaProjectType()).getDeclaredFields(); // by reflection

            if (fieldsSet.length == 1)
                return "public void testUpdate() throws Exception{\n" + "\n" + "     " + "assert(true); \n" + "} \n";
            else {
                int i = 0;
                modificablePropertyPosition = Integer.toString(i);
                propertyType = fieldsSet[i].getType();
                boolean found = false;
                while (i < fieldsSet.length && !found) {
                    if (!fieldsSet[i].getName().equalsIgnoreCase(
                            aCodeGeneratorConfiguration.getClassIdMap().getId(updateClass.getName()))) {
                        modificablePropertyPosition = Integer.toString(i);
                        propertyType = fieldsSet[i].getType();
                        found = true;
                    }
                    i++;
                }
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return "	public void testUpdate() throws Exception{\n" + "\n" + "		deleteDataBase();\n"
                + "		PersistenceSystem persistenceSystem = new PersistenceSystem();\n"
                + "		PersistenceSession persistenceSession = persistenceSystem.getPersistenceSession();\n" + "		"
                + updateClass.getName()
                + " "
                + objectVarName
                + " = "
                + generateConstructorCall(updateClass, aCodeGeneratorConfiguration)
                + "; \n"
                + "		persistenceSession.persist("
                + objectVarName
                + "); \n"
                + "\n"
                + "		"
                + "Field modificableField = "
                + objectVarName
                + ".getClass().getDeclaredFields()["
                + modificablePropertyPosition
                + "]; \n"
                + "		"
                + "modificableField.setAccessible(true); \n"
                + "		"
                + "modificableField.set("
                + objectVarName
                + ","
                + generateActualParameter(propertyType, aCodeGeneratorConfiguration)
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
    }

    private String generateTestFindAll(final CodeGeneratorConfiguration aCodeGeneratorConfiguration)
            throws NotSupportedClassException {

        if (aCodeGeneratorConfiguration.getClassList().iterator().hasNext()) {

            Class classToPersist = aCodeGeneratorConfiguration.getClassList().iterator().next();

            String testPersist = "	public void testFindAll(){\n" + "\n" + "		deleteDataBase();\n"
                    + "		PersistenceSystem persistenceSystem = new PersistenceSystem();\n"
                    + "		PersistenceSession persistenceSession = persistenceSystem.getPersistenceSession();\n" + "		"
                    + classToPersist.getName()
                    + " "
                    + classToPersist.getSimpleName().toLowerCase()
                    + " = "
                    + generateConstructorCall(classToPersist, aCodeGeneratorConfiguration)
                    + ";\n"
                    + "		persistenceSession.persist("
                    + classToPersist.getSimpleName().toLowerCase()
                    + ");\n"
                    + "		"
                    + classToPersist.getSimpleName().toLowerCase()
                    + " = "
                    + generateConstructorCall(classToPersist, aCodeGeneratorConfiguration)
                    + ";\n"
                    + "		persistenceSession.persist("
                    + classToPersist.getSimpleName().toLowerCase()
                    + ");\n"
                    + "		Collection collection = persistenceSession.findAll("
                    + classToPersist.getSimpleName().toLowerCase()
                    + ");\n"
                    + "		deleteDataBase();\n"
                    + "\n"
                    + "		assertEquals(2,collection.size());\n" + "	}\n";

            return testPersist;

        } else
            return "	public void testPersist(){\n" + "	}\n";

    }

    private String generateDeleteDataBase(final CodeGeneratorConfiguration aCodeGeneratorConfiguration) {

        String deleteDataBase = "\n" + "	private void deleteDataBase(){\n"
                + "		PersistenceSystem persistenceSystem = new PersistenceSystem();\n"
                + "		PersistenceSession aPersistenceSession = persistenceSystem.getPersistenceSession();\n";

        for (Iterator<Class> iterator = aCodeGeneratorConfiguration.getClassList().iterator(); iterator.hasNext();) {
            Class aClass = iterator.next();

            deleteDataBase = deleteDataBase + "\n" + "        // " + aClass.getSimpleName() + "\n"
                    + "        Collection objects" + aClass.getSimpleName() + " = aPersistenceSession.findAll(new "
                    + aClass.getName() + "());\n" + "\n" + "		" + "for (Iterator iterator = objects"
                    + aClass.getSimpleName() + ".iterator(); iterator.hasNext();) { \n " + "		" + aClass.getName()
                    + " object = (" + aClass.getName() + ") iterator.next(); \n "
                    + "		aPersistenceSession.delete(object); \n" + "		} \n";
        }

        deleteDataBase = deleteDataBase + "	}\n";
        return deleteDataBase;
    }

    private String generateConstructorCall(final Class className,
            final CodeGeneratorConfiguration aCodeGeneratorConfiguration) throws NotSupportedClassException {
        /**
         * new!!! MODIFIED return the call for the constructor with less
         * parameters
         * 
         */

        Constructor[] constructors = ClassFinder.find(className.getName(), aCodeGeneratorConfiguration.getClassList(),
                new JavaProjectType()).getConstructors();

        if (constructors.length == 0) {
            logger.fatal("See here -> find a class without constructor ");
        }
        /**
         * /// the below lines look for the constructor with less parameters or
         * the by defect constructor
         */

        Constructor betterConst = constructors[0];
        for (int i = 0; i < constructors.length; ++i) {
            Constructor ctor = constructors[i];
            if (ctor.getParameterTypes().length < betterConst.getParameterTypes().length) {
                betterConst = ctor;
            }
        }

        String constructorCall = "new " + className.getName() + "(";

        if (constructors.length != 0) {
            Type[] parametersType = betterConst.getParameterTypes();
            constructorCall = constructorCall + generateActualParameters(parametersType, aCodeGeneratorConfiguration);
        }
        return constructorCall + ")";
    }

    private String generateActualParameters(final Type[] parametersType,
            final CodeGeneratorConfiguration aCodeGeneratorConfiguration) throws NotSupportedClassException {

        String actualParameters = "";

        for (int i = 0; i < parametersType.length; i++) {

            Type formalParameterType = parametersType[i];

            actualParameters = actualParameters
                    + generateActualParameter(formalParameterType, aCodeGeneratorConfiguration);

            if (i < parametersType.length - 1) {
                actualParameters = actualParameters + ",";
            }
        }

        return actualParameters;
    }

    private String generateActualParameter(final Type formalParameterType,
            final CodeGeneratorConfiguration aCodeGeneratorConfiguration) throws NotSupportedClassException {

        String parameterType = formalParameterType.getName();

        if (parameterType.equalsIgnoreCase("java.lang.String"))
            return '"' + Integer.toString(1234) + '"';
        if (parameterType.equalsIgnoreCase("int"))
            return Integer.toString(1234);
        if (parameterType.equalsIgnoreCase("long"))
            return Long.toString(98765);
        if (parameterType.equalsIgnoreCase("java.lang.Boolean"))
            return Boolean.toString(true);
        if (parameterType.equalsIgnoreCase("java.util.Map"))
            return "new HashMap() ";
        if (parameterType.equalsIgnoreCase("java.util.Set"))
            return "new HashSet()";
        if (parameterType.equalsIgnoreCase("java.util.List"))
            return "new ArrayList()";
        if (formalParameterType.isArray())
            return "new " + formalParameterType.getComponentType().getName() + "[6]";

        return generateConstructorCall(ClassFinder.find(parameterType, aCodeGeneratorConfiguration.getClassList(),
                new JavaProjectType()), aCodeGeneratorConfiguration);
    }
}
