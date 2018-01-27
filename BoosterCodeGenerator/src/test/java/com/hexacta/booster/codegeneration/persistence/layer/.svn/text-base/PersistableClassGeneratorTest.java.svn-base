package com.hexacta.booster.codegeneration.persistence.layer;

import junit.framework.TestCase;

import org.apache.log4j.Logger;

import test.hexacta.booster.providers.CodeGeneratorConfigurationProvider;
import test.model.Table;
import ar.com.hexacta.utils.reflection.ReflectionUtils;

import com.hexacta.booster.codegeneration.configuration.ClassList;
import com.hexacta.booster.codegeneration.configuration.CodeGeneratorConfiguration;

/**
 * 
 */
public class PersistableClassGeneratorTest extends TestCase {

    /**
     * Log4j logger.
     */

    static final Logger logger = Logger.getLogger(PersistableClassGeneratorTest.class.getSimpleName());

    public void testCreation() {

        PersistableClassGenerator generator = new PersistableClassGenerator();
        assertNotNull(generator);
    }

    public void testGenerate() {

        CodeGeneratorConfiguration codeGeneratorConfiguration = new CodeGeneratorConfigurationProvider()
                .getCodeGeneratorConfiguration();
        Class<test.model.Address> aClass = test.model.Address.class;

        PersistableClassGenerator generator = new PersistableClassGenerator();
        PersistableClass generatedState = generator.generate(aClass, codeGeneratorConfiguration);

        PersistableClass expectedState = new PersistableClass(aClass, getPersistableModelName(aClass,
                codeGeneratorConfiguration));

        ReflectionUtils.executePrivateMethod(generator, "generatePackage", new Class[] { PersistableClass.class, },
                new Object[] { expectedState });
        ReflectionUtils.executePrivateMethod(generator, "generateImports", new Class[] { PersistableClass.class },
                new Object[] { expectedState });
        ReflectionUtils.executePrivateMethod(generator, "generateClass", new Class[] { PersistableClass.class,
                CodeGeneratorConfiguration.class }, new Object[] { expectedState, codeGeneratorConfiguration });

        assertEquals(expectedState.getText(), generatedState.getText());

    }

    public void testGenerateAttributes() {

        try {

            Class<test.model.Address> aClass = test.model.Address.class;
            CodeGeneratorConfiguration aCodeGeneratorConfiguration = new CodeGeneratorConfigurationProvider()
                    .getCodeGeneratorConfiguration();

            PersistableClassGenerator generator = new PersistableClassGenerator();
            PersistableClass generatedState = new PersistableClass(aClass, getPersistableModelName(aClass,
                    aCodeGeneratorConfiguration));

            String generatedString = (String) ReflectionUtils.executePrivateMethod(generator, "generateAttributes",
                    new Class[] { PersistableClass.class, CodeGeneratorConfiguration.class }, new Object[] {
                            generatedState, aCodeGeneratorConfiguration });

            assertEquals("	 int addressId;\n" + "	 java.lang.String name;\n" + "	 int number;\n"
                    + "	 java.util.Set<test.model.persistence.dataobject.Person> people;\n"
                    + "	 test.model.persistence.dataobject.House house;\n" + " 	 public long addressId;\n",
                    generatedString);

        } catch (Exception e) {
            logger.error("Se ha levantado una excepción: " + e.getMessage());
            fail();
        }
    }

    private String getPersistableModelName(final Class<?> aClass,
            final CodeGeneratorConfiguration aCodeGeneratorConfiguration) {
        ClassList classList = aCodeGeneratorConfiguration.getClassList();
        if (classList.hasClass(aClass.getName()))
            return aClass.getPackage().getName() + ".persistable." + aClass.getSimpleName();
        else
            return aClass.getName();
    }

    public void testGenerateClass() {
        try {
            Class<test.model.Address> aClass = test.model.Address.class;
            CodeGeneratorConfiguration aCodeGeneratorConfiguration = new CodeGeneratorConfigurationProvider()
                    .getCodeGeneratorConfiguration();

            PersistableClassGenerator generator = new PersistableClassGenerator();
            PersistableClass generatedState = new PersistableClass(aClass, getPersistableModelName(aClass,
                    aCodeGeneratorConfiguration));

            ReflectionUtils.executePrivateMethod(generator, "generateClass", new Class[] { PersistableClass.class,
                    CodeGeneratorConfiguration.class }, new Object[] { generatedState, aCodeGeneratorConfiguration });

            String expectedState = "\n"
                    + "public class "
                    + aClass.getSimpleName()
                    + " extends "
                    + getPersistableModelName(aClass.getSuperclass(), aCodeGeneratorConfiguration)
                    + "{"
                    + "\n\n"
                    + (String) ReflectionUtils.executePrivateMethod(generator, "generateAttributes", new Class[] {
                            PersistableClass.class, CodeGeneratorConfiguration.class }, new Object[] { generatedState,
                            aCodeGeneratorConfiguration })
                    + "\n"
                    + (String) ReflectionUtils.executePrivateMethod(generator, "generateDefaultConstructor",
                            new Class[] { Class.class }, new Object[] { aClass })
                    + "\n"
                    + (String) ReflectionUtils.executePrivateMethod(generator, "generateSettersGetters", new Class[] {
                            Class.class, CodeGeneratorConfiguration.class }, new Object[] { aClass,
                            aCodeGeneratorConfiguration }) + "}\n";

            assertEquals(expectedState, generatedState.getText());

        } catch (Exception e) {
            logger.error("Se ha levantado una excepción: " + e.getMessage());
            fail();
        }
    }

    public void testGeneratePackage() {

        Class<test.model.Address> aClass = test.model.Address.class;
        CodeGeneratorConfiguration aCodeGeneratorConfiguration = new CodeGeneratorConfigurationProvider()
                .getCodeGeneratorConfiguration();

        PersistableClassGenerator generator = new PersistableClassGenerator();
        PersistableClass generatedState = new PersistableClass(aClass, getPersistableModelName(aClass,
                aCodeGeneratorConfiguration));

        ReflectionUtils.executePrivateMethod(generator, "generatePackage", new Class[] { PersistableClass.class },
                new Object[] { generatedState });

        assertEquals("package test.model.persistence.dataobject;\n", generatedState.getText());

    }

    public void testGetPersistableModelName() {

        try {
            Class<test.model.Address> aClass = test.model.Address.class;
            CodeGeneratorConfiguration aCodeGeneratorConfiguration = new CodeGeneratorConfigurationProvider()
                    .getCodeGeneratorConfiguration();

            PersistableClassGenerator generator = new PersistableClassGenerator();

            String generatedResult = (String) ReflectionUtils.executePrivateMethod(generator,
                    "getPersistableModelName", new Class[] { Class.class, CodeGeneratorConfiguration.class },
                    new Object[] { aClass, aCodeGeneratorConfiguration });

            assertEquals(aClass.getPackage().getName() + ".persistence.dataobject." + aClass.getSimpleName(),
                    generatedResult);

        } catch (Exception e) {
            logger.error("Se ha levantado una excepción: " + e.getMessage());
            fail();
        }
    }

    public void testGenerateImports() {
        try {
            Class<test.model.Address> aClass = test.model.Address.class;
            CodeGeneratorConfiguration aCodeGeneratorConfiguration = new CodeGeneratorConfigurationProvider()
                    .getCodeGeneratorConfiguration();

            PersistableClassGenerator generator = new PersistableClassGenerator();
            PersistableClass generatedState = new PersistableClass(aClass, getPersistableModelName(aClass,
                    aCodeGeneratorConfiguration));

            ReflectionUtils.executePrivateMethod(generator, "generateImports", new Class[] { PersistableClass.class },
                    new Object[] { generatedState });

            assertEquals("\n", generatedState.getText());

        } catch (Exception e) {
            logger.error("Se ha levantado una excepción: " + e.getMessage());
            fail();
        }
    }

    public void testGenerateDefaultConstructor() {

        try {
            Class<test.model.Address> aClass = test.model.Address.class;

            PersistableClassGenerator generator = new PersistableClassGenerator();

            String generatedResult = (String) ReflectionUtils.executePrivateMethod(generator,
                    "generateDefaultConstructor", new Class[] { Class.class }, new Object[] { aClass });

            assertEquals("	public void Address(){\n" + "	}\n", generatedResult);

        } catch (Exception e) {
            logger.error("Se ha levantado una excepción: " + e.getMessage());
            fail();
        }
    }

    public void testGenerateSettersGetters() {

        try {
            Class<test.model.Address> aClass = test.model.Address.class;
            CodeGeneratorConfiguration aCodeGeneratorConfiguration = new CodeGeneratorConfigurationProvider()
                    .getCodeGeneratorConfiguration();

            PersistableClassGenerator generator = new PersistableClassGenerator();

            String generatedResult = (String) ReflectionUtils.executePrivateMethod(generator, "generateSettersGetters",
                    new Class[] { Class.class, CodeGeneratorConfiguration.class }, new Object[] { aClass,
                            aCodeGeneratorConfiguration });

            assertEquals("	public void setOrmId(long ormId){\n" + "		this.ormId=ormId;\n" + "	}\n" + "	\n"
                    + "	public long getOrmId(){\n" + "		return ormId;\n" + "	}\n" + "	\n"
                    + "	public void setAddressId(int addressId){\n" + "		this.addressId = addressId;\n" + "	}\n" + "\n"
                    + "	public int getAddressId(){\n" + "		return addressId;\n" + "	}\n" + "\n"
                    + "	public void setName(java.lang.String name){\n" + "		this.name = name;\n" + "	}\n" + "\n"
                    + "	public java.lang.String getName(){\n" + "		return name;\n" + "	}\n" + "\n"
                    + "	public void setNumber(int number){\n" + "		this.number = number;\n" + "	}\n" + "\n"
                    + "	public int getNumber(){\n" + "		return number;\n" + "	}\n" + "\n"
                    + "	public void setPeople(java.util.Set<test.model.persistence.dataobject.Person> people){\n"
                    + "		this.people = people;\n" + "	}\n" + "\n"
                    + "	public java.util.Set<test.model.persistence.dataobject.Person> getPeople(){\n"
                    + "		return people;\n" + "	}\n" + "\n"
                    + "	public void setHouse(test.model.persistence.dataobject.House house){\n"
                    + "		this.house = house;\n" + "	}\n" + "\n"
                    + "	public test.model.persistence.dataobject.House getHouse(){\n" + "		return house;\n" + "	}\n\n",
                    generatedResult);

        } catch (Exception e) {
            logger.error("Se ha levantado una excepción: " + e.getMessage());
            fail();
        }
    }

    public void testGetTypeName() {

        try {
            Class<test.model.Address> aClass = test.model.Address.class;
            CodeGeneratorConfiguration aCodeGeneratorConfiguration = new CodeGeneratorConfigurationProvider()
                    .getCodeGeneratorConfiguration();

            PersistableClassGenerator generator = new PersistableClassGenerator();

            String generatedResult = (String) ReflectionUtils.executePrivateMethod(generator, "getTypeName",
                    new Class[] { Class.class, int.class, CodeGeneratorConfiguration.class }, new Object[] { aClass, 1,
                            aCodeGeneratorConfiguration });

            assertEquals("java.lang.String", generatedResult);

        } catch (Exception e) {
            logger.error("Se ha levantado una excepción: " + e.getMessage());
            fail();
        }
    }

    public void testAnotherGetTypeName() {
        /**
         * Testing arrays with primitive components
         */
        try {
            Class<test.model.Table> aClass = test.model.Table.class;
            CodeGeneratorConfiguration aCodeGeneratorConfiguration = new CodeGeneratorConfigurationProvider()
                    .getCodeGeneratorConfiguration();

            PersistableClassGenerator generator = new PersistableClassGenerator();

            String generatedResult = (String) ReflectionUtils.executePrivateMethod(generator, "getTypeName",
                    new Class[] { Class.class, int.class, CodeGeneratorConfiguration.class }, new Object[] { aClass, 2,
                            aCodeGeneratorConfiguration });

            assertEquals("int[]", generatedResult);

        } catch (Exception e) {
            logger.error("Se ha levantado una excepción: " + e.getMessage());
            fail();
        }
    }

    public void testOtherGetTypeName() {
        /**
         * Testing with arrays
         */
        try {
            Class<Table> aClass = test.model.Table.class;
            CodeGeneratorConfiguration aCodeGeneratorConfiguration = new CodeGeneratorConfigurationProvider()
                    .getCodeGeneratorConfiguration();

            PersistableClassGenerator generator = new PersistableClassGenerator();
            // PersistableClass text = generator.generate(aClass,
            // aCodeGeneratorConfiguration);

            String generatedResult = (String) ReflectionUtils.executePrivateMethod(generator, "getTypeName",
                    new Class[] { Class.class, int.class, CodeGeneratorConfiguration.class }, new Object[] { aClass, 3,
                            aCodeGeneratorConfiguration });

            assertEquals("test.model.persistence.dataobject.Address[]", generatedResult);

        } catch (Exception e) {
            logger.error("Se ha levantado una excepción: " + e.getMessage());
            fail();
        }
    }

    public void testFirstCharToUpperCase() {

        try {

            PersistableClassGenerator generator = new PersistableClassGenerator();

            String aString = "someString";
            String generatedResult = (String) ReflectionUtils.executePrivateMethod(generator, "firstCharToUpperCase",
                    new Class[] { String.class }, new Object[] { aString });

            assertEquals("SomeString", generatedResult);

        } catch (Exception e) {
            logger.error("Se ha levantado una excepción: " + e.getMessage());
            fail();
        }
    }
}
