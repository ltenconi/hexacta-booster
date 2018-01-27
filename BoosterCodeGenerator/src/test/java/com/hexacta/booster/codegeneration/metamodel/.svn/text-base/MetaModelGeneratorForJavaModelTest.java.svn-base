package com.hexacta.booster.codegeneration.metamodel;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import junit.framework.TestCase;

import org.apache.log4j.Logger;

import test.hexacta.booster.providers.CodeGeneratorConfigurationProvider;
import ar.com.hexacta.utils.reflection.ReflectionUtils;

import com.hexacta.booster.codegeneration.configuration.ClassList;
import com.hexacta.booster.codegeneration.configuration.CodeGeneratorConfiguration;

/**
 * 
 */
public class MetaModelGeneratorForJavaModelTest extends TestCase {
    /**
     * Log4j logger.
     */
    static final Logger logger = Logger.getLogger(MetaModelGeneratorForJavaModelTest.class.getSimpleName());

    public void testCreation() {

        MetaModelGeneratorForJavaModel generator = new MetaModelGeneratorForJavaModel();
        assertNotNull(generator);

    }

    public void testGenerateListOfClass() {
        try {

            MetaModelGeneratorForJavaModel generator = new MetaModelGeneratorForJavaModel();

            List<java.lang.Class<?>> aClassList = new ArrayList<java.lang.Class<?>>();

            aClassList.add(test.model.Student.class);
            aClassList.add(test.model.Person.class);
            aClassList.add(test.model.Company.class);
            aClassList.add(test.model.Address.class);
            aClassList.add(test.model.Employee.class);

            ClassList listClass = (ClassList) ReflectionUtils.executePrivateMethod(generator, "generate",
                    new java.lang.Class[] { List.class }, new Object[] { aClassList });

            assertFalse(listClass.isEmpty());

            Iterator<Class> iterator = listClass.iterator();

            while (iterator.hasNext()) {
                Class aClass = iterator.next();
                assertTrue(listClass.hasClass(aClass.getName()));
            }
        } catch (Exception e) {
            logger.error("Se ha levantado una excepción: " + e.getMessage());
        }
    }

    public void testGenerateClass() {
        try {

            CodeGeneratorConfiguration aCodeGeneratorConfiguration = new CodeGeneratorConfigurationProvider()
                    .getCodeGeneratorConfiguration();
            java.lang.Class<test.model.Course> aJavaClass = test.model.Course.class;

            MetaModelGeneratorForJavaModel generator = new MetaModelGeneratorForJavaModel();
            ClassList aList = aCodeGeneratorConfiguration.getClassList();
            Class modelClass = (Class) ReflectionUtils.executePrivateMethod(generator, "generate",
                    new java.lang.Class[] { java.lang.Class.class, ClassList.class },
                    new Object[] { aJavaClass, aList });

            assertEquals(aJavaClass.getDeclaredFields().length, modelClass.getDeclaredFields().length);
            assertEquals(aJavaClass.getConstructors().length, modelClass.getConstructors().length);
            assertEquals(aJavaClass.isPrimitive(), modelClass.isPrimitive());
            assertEquals(aJavaClass.isArray(), modelClass.isPrimitive());

            assertEquals(aJavaClass.getName(), modelClass.getName());
            assertEquals(aJavaClass.getSimpleName(), modelClass.getSimpleName());
            assertEquals(aJavaClass.getSuperclass().getName(), modelClass.getSuperclass().getName());

        } catch (Exception e) {
            logger.error("Se ha levantado una excepción: " + e.getMessage());
        }
    }

    public void testAnotherGenerateClass() {
        try {

            CodeGeneratorConfiguration aCodeGeneratorConfiguration = new CodeGeneratorConfigurationProvider()
                    .getCodeGeneratorConfiguration();
            java.lang.Class<test.model.Address> aJavaClass = test.model.Address.class;

            MetaModelGeneratorForJavaModel generator = new MetaModelGeneratorForJavaModel();
            Class modelClass = (Class) ReflectionUtils.executePrivateMethod(generator, "generate",
                    new java.lang.Class[] { java.lang.Class.class, ClassList.class }, new Object[] { aJavaClass,
                            aCodeGeneratorConfiguration.getClassList() });

            assertEquals(aJavaClass.getDeclaredFields().length, modelClass.getDeclaredFields().length);
            assertEquals(aJavaClass.getConstructors().length, modelClass.getConstructors().length);
            assertEquals(aJavaClass.isPrimitive(), modelClass.isPrimitive());
            assertEquals(aJavaClass.isArray(), modelClass.isPrimitive());

            assertEquals(aJavaClass.getName(), modelClass.getName());
            assertEquals(aJavaClass.getSimpleName(), modelClass.getSimpleName());
            assertEquals(aJavaClass.getSuperclass().getName(), modelClass.getSuperclass().getName());

        } catch (Exception e) {
            logger.error("Se ha levantado una excepción: " + e.getMessage());
        }
    }

    public void testGenerateDeclaredFields() {

        try {
            CodeGeneratorConfiguration aCodeGeneratorConfiguration = new CodeGeneratorConfigurationProvider()
                    .getCodeGeneratorConfiguration();
            java.lang.Class<test.model.Person> aJavaClass = test.model.Person.class;

            MetaModelGeneratorForJavaModel generator = new MetaModelGeneratorForJavaModel();
            Field[] declaredFields = (Field[]) ReflectionUtils.executePrivateMethod(generator,
                    "generateDeclaredFiedls", new java.lang.Class[] { java.lang.Class.class, ClassList.class },
                    new Object[] { aJavaClass, aCodeGeneratorConfiguration.getClassList() });

            assertEquals(aJavaClass.getDeclaredFields().length, declaredFields.length);
            assertEquals(aJavaClass.getDeclaredFields()[0].getName(), declaredFields[0].getName());
            assertEquals(aJavaClass.getDeclaredFields()[0].getDeclaringClass().getName(), declaredFields[0]
                    .getDeclaringClass().getName());
            assertEquals(aJavaClass.getDeclaredFields()[0].getType().getName(), declaredFields[0].getType().getName());
            assertEquals(aJavaClass.getDeclaredFields()[1].getName(), declaredFields[1].getName());
            assertEquals(aJavaClass.getDeclaredFields()[1].getDeclaringClass().getName(), declaredFields[1]
                    .getDeclaringClass().getName());
            assertEquals(aJavaClass.getDeclaredFields()[1].getType().getName(), declaredFields[1].getType().getName());
            assertEquals(aJavaClass.getDeclaredFields()[2].getName(), declaredFields[2].getName());
            assertEquals(aJavaClass.getDeclaredFields()[2].getDeclaringClass().getName(), declaredFields[2]
                    .getDeclaringClass().getName());
            assertEquals(aJavaClass.getDeclaredFields()[2].getType().getName(), declaredFields[2].getType().getName());

        } catch (Exception e) {
            logger.error("Se ha levantado una excepción: " + e.getMessage());
        }
    }

    public void testAnotherGenerateDeclaredFields() {
        try {
            CodeGeneratorConfiguration aCodeGeneratorConfiguration = new CodeGeneratorConfigurationProvider()
                    .getCodeGeneratorConfiguration();
            java.lang.Class<test.model.Course> aJavaClass = test.model.Course.class;

            MetaModelGeneratorForJavaModel generator = new MetaModelGeneratorForJavaModel();
            Field[] declaredFields = (Field[]) ReflectionUtils.executePrivateMethod(generator,
                    "generateDeclaredFiedls", new java.lang.Class[] { java.lang.Class.class, ClassList.class },
                    new Object[] { aJavaClass, aCodeGeneratorConfiguration.getClassList() });

            assertEquals(aJavaClass.getDeclaredFields().length, declaredFields.length);
            assertEquals(aJavaClass.getDeclaredFields()[0].getName(), declaredFields[0].getName());
            assertEquals(aJavaClass.getDeclaredFields()[0].getDeclaringClass().getName(), declaredFields[0]
                    .getDeclaringClass().getName());
            assertEquals(aJavaClass.getDeclaredFields()[0].getType().getName(), declaredFields[0].getType().getName());
            assertEquals(aJavaClass.getDeclaredFields()[1].getName(), declaredFields[1].getName());
            assertEquals(aJavaClass.getDeclaredFields()[1].getDeclaringClass().getName(), declaredFields[1]
                    .getDeclaringClass().getName());
            assertEquals(aJavaClass.getDeclaredFields()[1].getType().getName(), declaredFields[1].getType().getName());

        } catch (Exception e) {
            logger.error("Se ha levantado una excepción: " + e.getMessage());
        }
    }

    public void testOtherGenerateDeclaredFields() {
        try {
            CodeGeneratorConfiguration aCodeGeneratorConfiguration = new CodeGeneratorConfigurationProvider()
                    .getCodeGeneratorConfiguration();
            java.lang.Class<test.model.Address> aJavaClass = test.model.Address.class;

            MetaModelGeneratorForJavaModel generator = new MetaModelGeneratorForJavaModel();
            Field[] declaredFields = (Field[]) ReflectionUtils.executePrivateMethod(generator,
                    "generateDeclaredFiedls", new java.lang.Class[] { java.lang.Class.class, ClassList.class },
                    new Object[] { aJavaClass, aCodeGeneratorConfiguration.getClassList() });

            assertEquals(aJavaClass.getDeclaredFields().length, declaredFields.length);
            assertEquals(aJavaClass.getDeclaredFields()[0].getName(), declaredFields[0].getName());
            assertEquals(aJavaClass.getDeclaredFields()[0].getDeclaringClass().getName(), declaredFields[0]
                    .getDeclaringClass().getName());
            assertEquals(aJavaClass.getDeclaredFields()[0].getType().getName(), declaredFields[0].getType().getName());
            assertEquals(aJavaClass.getDeclaredFields()[1].getName(), declaredFields[1].getName());
            assertEquals(aJavaClass.getDeclaredFields()[1].getDeclaringClass().getName(), declaredFields[1]
                    .getDeclaringClass().getName());
            assertEquals(aJavaClass.getDeclaredFields()[1].getType().getName(), declaredFields[1].getType().getName());
            assertEquals(aJavaClass.getDeclaredFields()[2].getName(), declaredFields[2].getName());
            assertEquals(aJavaClass.getDeclaredFields()[2].getDeclaringClass().getName(), declaredFields[2]
                    .getDeclaringClass().getName());
            assertEquals(aJavaClass.getDeclaredFields()[2].getType().getName(), declaredFields[2].getType().getName());
            assertEquals(aJavaClass.getDeclaredFields()[3].getName(), declaredFields[3].getName());
            assertEquals(aJavaClass.getDeclaredFields()[3].getDeclaringClass().getName(), declaredFields[3]
                    .getDeclaringClass().getName());
            assertEquals(aJavaClass.getDeclaredFields()[3].getType().getName(), declaredFields[3].getType().getName());
            assertEquals(aJavaClass.getDeclaredFields()[4].getName(), declaredFields[4].getName());
            assertEquals(aJavaClass.getDeclaredFields()[4].getDeclaringClass().getName(), declaredFields[4]
                    .getDeclaringClass().getName());
            assertEquals(aJavaClass.getDeclaredFields()[4].getType().getName(), declaredFields[4].getType().getName());

        } catch (Exception e) {
            logger.error("Se ha levantado una excepción: " + e.getMessage());
        }
    }

    // public void createClassForObjectClass() {
    // fail("Not yet implemented");
    // }

    public void testFindOrCreateClassForSuperclass() {

        try {

            CodeGeneratorConfiguration aCodeGeneratorConfiguration = new CodeGeneratorConfigurationProvider()
                    .getCodeGeneratorConfiguration();
            java.lang.Class<java.lang.Object> aJavaClass = java.lang.Object.class;

            MetaModelGeneratorForJavaModel generator = new MetaModelGeneratorForJavaModel();

            Class aClass = (Class) ReflectionUtils.executePrivateMethod(generator, "findOrCreateClassForSuperclass",
                    new java.lang.Class[] { java.lang.Class.class, ClassList.class }, new Object[] { aJavaClass,
                            aCodeGeneratorConfiguration.getClassList() });

            assertNull(aClass);

        } catch (Exception e) {
            logger.error("Se ha levantado una excepción: " + e.getMessage());
        }
    }

    public void testAnotherFindOrCreateClassForSuperclass() {

        try {

            CodeGeneratorConfiguration aCodeGeneratorConfiguration = new CodeGeneratorConfigurationProvider()
                    .getCodeGeneratorConfiguration();
            java.lang.Class<test.model.Person> aJavaClass = test.model.Person.class;

            MetaModelGeneratorForJavaModel generator = new MetaModelGeneratorForJavaModel();

            Class aClass = (Class) ReflectionUtils.executePrivateMethod(generator, "findOrCreateClassForSuperclass",
                    new java.lang.Class[] { java.lang.Class.class, ClassList.class }, new Object[] { aJavaClass,
                            aCodeGeneratorConfiguration.getClassList() });

            assertNotNull(aClass);
            assertEquals("java.lang.Object", aClass.getName());
            assertEquals("Object", aClass.getSimpleName());
            assertEquals("java.lang", aClass.getPackage());
            assertFalse(aClass.isArray());
            assertFalse(aClass.isPrimitive());
        } catch (Exception e) {
            logger.error("Se ha levantado una excepción: " + e.getMessage());
        }
    }

    public void testOtherFindOrCreateClassForSuperclass() {

        try {

            CodeGeneratorConfiguration aCodeGeneratorConfiguration = new CodeGeneratorConfigurationProvider()
                    .getCodeGeneratorConfiguration();
            java.lang.Class<test.model.Student> aJavaClass = test.model.Student.class;

            MetaModelGeneratorForJavaModel generator = new MetaModelGeneratorForJavaModel();

            Class aClass = (Class) ReflectionUtils.executePrivateMethod(generator, "findOrCreateClassForSuperclass",
                    new java.lang.Class[] { java.lang.Class.class, ClassList.class }, new Object[] { aJavaClass,
                            aCodeGeneratorConfiguration.getClassList() });

            assertNotNull(aClass);
            assertEquals("test.model.Person", aClass.getName());
            assertEquals("Person", aClass.getSimpleName());
            assertEquals("test.model", aClass.getPackage());
            assertFalse(aClass.isArray());
            assertFalse(aClass.isPrimitive());
        } catch (Exception e) {
            logger.error("Se ha levantado una excepción: " + e.getMessage());
        }
    }

    public void testGenerateConstructors() {

        try {

            CodeGeneratorConfiguration aCodeGeneratorConfiguration = new CodeGeneratorConfigurationProvider()
                    .getCodeGeneratorConfiguration();
            java.lang.Class<test.model.Door> aJavaClass = test.model.Door.class;

            MetaModelGeneratorForJavaModel generator = new MetaModelGeneratorForJavaModel();

            Constructor[] constructors = (Constructor[]) ReflectionUtils.executePrivateMethod(generator,
                    "generateConstructors", new java.lang.Class[] { java.lang.Class.class, ClassList.class },
                    new Object[] { aJavaClass, aCodeGeneratorConfiguration.getClassList() });

            assertEquals(aJavaClass.getConstructors().length, constructors.length);
            assertEquals(4, constructors.length);
            assertEquals(aJavaClass.getConstructors()[0].getName(), constructors[0].getName());
            assertEquals(aJavaClass.getConstructors()[0].getParameterTypes().length, constructors[0]
                    .getParameterTypes().length);
            assertEquals(aJavaClass.getConstructors()[1].getName(), constructors[1].getName());
            assertEquals(aJavaClass.getConstructors()[0].getParameterTypes().length, constructors[0]
                    .getParameterTypes().length);
            assertEquals(aJavaClass.getConstructors()[2].getName(), constructors[2].getName());
            assertEquals(aJavaClass.getConstructors()[2].getParameterTypes().length, constructors[2]
                    .getParameterTypes().length);
            assertEquals(aJavaClass.getConstructors()[3].getName(), constructors[3].getName());
            assertEquals(aJavaClass.getConstructors()[3].getParameterTypes().length, constructors[3]
                    .getParameterTypes().length);
        } catch (Exception e) {
            logger.error("Se ha levantado una excepción: " + e.getMessage());
        }
    }

    public void testAnotherGenerateConstructors() {

        try {

            CodeGeneratorConfiguration aCodeGeneratorConfiguration = new CodeGeneratorConfigurationProvider()
                    .getCodeGeneratorConfiguration();
            java.lang.Class<test.model.Company> aJavaClass = test.model.Company.class;

            MetaModelGeneratorForJavaModel generator = new MetaModelGeneratorForJavaModel();

            Constructor[] constructors = (Constructor[]) ReflectionUtils.executePrivateMethod(generator,
                    "generateConstructors", new java.lang.Class[] { java.lang.Class.class, ClassList.class },
                    new Object[] { aJavaClass, aCodeGeneratorConfiguration.getClassList() });

            assertEquals(aJavaClass.getConstructors().length, constructors.length);
            assertEquals(1, constructors.length);
            assertEquals(aJavaClass.getConstructors()[0].getName(), constructors[0].getName());
            assertEquals(aJavaClass.getConstructors()[0].getParameterTypes().length, constructors[0]
                    .getParameterTypes().length);

        } catch (Exception e) {
            logger.error("Se ha levantado una excepción: " + e.getMessage());
        }

    }

    public void testGetSuperClass() {
        try {
            CodeGeneratorConfiguration aCodeGeneratorConfiguration = new CodeGeneratorConfigurationProvider()
                    .getCodeGeneratorConfiguration();
            java.lang.Class<test.model.Student> aJavaClass = test.model.Student.class;

            MetaModelGeneratorForJavaModel generator = new MetaModelGeneratorForJavaModel();
            ClassList aList = aCodeGeneratorConfiguration.getClassList();
            Class superClass = (Class) ReflectionUtils.executePrivateMethod(generator, "getSuperClass",
                    new java.lang.Class[] { java.lang.Class.class, ClassList.class },
                    new Object[] { aJavaClass, aList });

            assertEquals("test.model.Person", superClass.getName());
            assertEquals("Person", superClass.getSimpleName());
            assertNotNull(superClass.getSuperclass());
            assertEquals("java.lang.Object", superClass.getSuperclass().getName());
            assertFalse(superClass.isArray());
            assertFalse(superClass.isPrimitive());

        } catch (Exception e) {
            logger.error("Se ha levantado una excepción: " + e.getMessage());
        }
    }

    public void testAnotherGetSuperClass() {
        try {

            CodeGeneratorConfiguration aCodeGeneratorConfiguration = new CodeGeneratorConfigurationProvider()
                    .getCodeGeneratorConfiguration();
            java.lang.Class<java.lang.Object> aJavaClass = java.lang.Object.class;

            MetaModelGeneratorForJavaModel generator = new MetaModelGeneratorForJavaModel();
            ClassList aList = aCodeGeneratorConfiguration.getClassList();
            Class superClass = (Class) ReflectionUtils.executePrivateMethod(generator, "getSuperClass",
                    new java.lang.Class[] { java.lang.Class.class, ClassList.class },
                    new Object[] { aJavaClass, aList });

            assertEquals("java.lang.Object", superClass.getName());
            assertEquals("Object", superClass.getSimpleName());
            assertNull(superClass.getSuperclass());
            assertFalse(superClass.isArray());
            assertFalse(superClass.isPrimitive());
            assertEquals("java.lang", superClass.getPackage());

        } catch (Exception e) {
            logger.error("Se ha levantado una excepción: " + e.getMessage());
        }
    }

    public void testGenerateParametricedType() {

        try {

            CodeGeneratorConfiguration aCodeGeneratorConfiguration = new CodeGeneratorConfigurationProvider()
                    .getCodeGeneratorConfiguration();

            java.lang.Class<test.model.Address> aJavaClass = test.model.Address.class;
            java.lang.reflect.Method method = aJavaClass.getMethod("setPeople", new java.lang.Class[] { Set.class });
            java.lang.reflect.Type[] aType = method.getGenericParameterTypes();
            method.getParameterTypes();

            MetaModelGeneratorForJavaModel generator = new MetaModelGeneratorForJavaModel();

            ClassList aList = aCodeGeneratorConfiguration.getClassList();
            // /wrong number of arguments: la linea de abajo!!
            ParameterizedType parametrizedType = (ParameterizedType) ReflectionUtils.executePrivateMethod(generator,
                    "generateParameterizedType", new java.lang.Class[] { java.lang.Class.class,
                            java.lang.reflect.Type.class, ClassList.class },
                    new Object[] { aJavaClass, aType[0], aList });

            assertEquals("test.model.Address", parametrizedType.getName());
            assertFalse(parametrizedType.isArray());
            assertTrue(parametrizedType.isParameterized());
            assertFalse(parametrizedType.isPrimitive());

        } catch (Exception e) {
            logger.error("Se ha levantado una excepción: " + e.getMessage());
        }
    }

    public void testGenerateActualTypeArguments() {

        try {
            java.lang.Class<test.model.Door> aJavaClass = test.model.Door.class;
            java.lang.reflect.Constructor<?>[] constructors = aJavaClass.getConstructors();
            boolean search = false;
            int i = 0;
            java.lang.reflect.Constructor<?> betterConst = null;

            while (i < constructors.length && !search) {
                betterConst = constructors[i];
                if (betterConst.getParameterTypes().length == 2) {
                    search = true;
                }
                i++;
            }
            java.lang.reflect.Type[] parametersType = betterConst.getParameterTypes();
            MetaModelGeneratorForJavaModel generator = new MetaModelGeneratorForJavaModel();
            String[] generatedString = (String[]) ReflectionUtils.executePrivateMethod(generator,
                    "generateActualTypeArguments", new java.lang.Class[] { java.lang.reflect.Type[].class },
                    new Object[] { parametersType });

            assertNotNull(generatedString);
            assertEquals(2, generatedString.length);
            assertEquals("int", generatedString[0].toString());
            assertEquals("int", generatedString[1].toString());

        } catch (Exception e) {
            logger.error("Se ha levantado una excepción: " + e.getMessage());
        }

    }

    public void testAnotherGenerateActualTypeArguments() {

        try {
            java.lang.Class<test.model.Student> aJavaClass = test.model.Student.class;
            java.lang.reflect.Constructor<?>[] constructors = aJavaClass.getConstructors();
            boolean search = false;
            int i = 0;
            java.lang.reflect.Constructor<?> betterConst = null;

            while (i < constructors.length && !search) {
                betterConst = constructors[i];
                if (betterConst.getParameterTypes().length == 2) {
                    search = true;
                }
                i++;
            }
            java.lang.reflect.Type[] parametersType = betterConst.getParameterTypes();
            MetaModelGeneratorForJavaModel generator = new MetaModelGeneratorForJavaModel();
            String[] generatedString = (String[]) ReflectionUtils.executePrivateMethod(generator,
                    "generateActualTypeArguments", new java.lang.Class[] { java.lang.reflect.Type[].class },
                    new Object[] { parametersType });

            assertNotNull(generatedString);
            assertEquals(2, generatedString.length);
            assertEquals("int", generatedString[0].toString());

        } catch (Exception e) {
            logger.error("Se ha levantado una excepción: " + e.getMessage());
        }
    }

    public void testGenerateType() {

        try {
            CodeGeneratorConfiguration aCodeGeneratorConfiguration = new CodeGeneratorConfigurationProvider()
                    .getCodeGeneratorConfiguration();

            java.lang.Class<test.model.Student> aJavaClass = test.model.Student.class;
            MetaModelGeneratorForJavaModel generator = new MetaModelGeneratorForJavaModel();

            Type generatedType = (Type) ReflectionUtils.executePrivateMethod(generator, "generateType",
                    new java.lang.Class[] { java.lang.Class.class, ClassList.class }, new Object[] { aJavaClass,
                            aCodeGeneratorConfiguration.getClassList() });

            assertFalse(generatedType.isArray());
            assertFalse(generatedType.isParameterized());
            assertFalse(generatedType.isPrimitive());
            assertNull(generatedType.getComponentType());
            assertEquals(aJavaClass.getName(), generatedType.getName());
            assertEquals(aJavaClass.getSimpleName(), generatedType.getSimpleName());

        } catch (Exception e) {
            logger.error("Se ha levantado una excepción: " + e.getMessage());
        }
    }

    public void testAnotherGenerateType() {

        try {
            CodeGeneratorConfiguration aCodeGeneratorConfiguration = new CodeGeneratorConfigurationProvider()
                    .getCodeGeneratorConfiguration();

            java.lang.Class<test.model.Door> aJavaClass = test.model.Door.class;
            MetaModelGeneratorForJavaModel generator = new MetaModelGeneratorForJavaModel();

            Type generatedType = (Type) ReflectionUtils.executePrivateMethod(generator, "generateType",
                    new java.lang.Class[] { java.lang.Class.class, ClassList.class }, new Object[] { aJavaClass,
                            aCodeGeneratorConfiguration.getClassList() });

            assertFalse(generatedType.isArray());
            assertFalse(generatedType.isParameterized());
            assertFalse(generatedType.isPrimitive());
            assertNull(generatedType.getComponentType());
            assertEquals(aJavaClass.getName(), generatedType.getName());
            assertEquals(aJavaClass.getSimpleName(), generatedType.getSimpleName());

        } catch (Exception e) {
            logger.error("Se ha levantado una excepción: " + e.getMessage());
        }
    }
}