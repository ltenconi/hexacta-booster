package com.hexacta.booster.codegeneration.persistence.layer.hibernate;

import java.util.Iterator;

import junit.framework.TestCase;

import org.apache.log4j.Logger;

import test.hexacta.booster.providers.CodeGeneratorConfigurationProvider;
import ar.com.hexacta.utils.reflection.ReflectionUtils;

import com.hexacta.booster.codegeneration.configuration.ClassList;
import com.hexacta.booster.codegeneration.configuration.CodeGeneratorConfiguration;
import com.hexacta.booster.codegeneration.metamodel.Class;
import com.hexacta.booster.codegeneration.persistence.layer.PersistenceSession;

/**
 * 
 * @author jmarquez
 * 
 */
public class PersistenceSessionWithoutRestrictionsGeneratorTest extends TestCase {

    /**
     * Log4j logger.
     */

    static final Logger logger = Logger.getLogger(PersistenceSessionWithoutRestrictionsGeneratorTest.class
            .getSimpleName());

    public void testCreation() {

        PersistenceSessionWithoutRestrictionsGenerator generator = new PersistenceSessionWithoutRestrictionsGenerator();
        assertNotNull(generator);
    }

    public void testGenerate() {

        /**
         * Create context and set the attributes
         */
        CodeGeneratorConfiguration aCodeGeneratorConfiguration = new CodeGeneratorConfigurationProvider()
                .getCodeGeneratorConfiguration();
        PersistenceSession generatedState = new PersistenceSession();
        PersistenceSession expectedState = new PersistenceSession();
        /**
         * Generated
         */
        PersistenceSessionWithoutRestrictionsGenerator generator = new PersistenceSessionWithoutRestrictionsGenerator();
        generatedState = generator.generate(aCodeGeneratorConfiguration);
        /**
         * Expected
         */
        assertNotNull(generator);

        ReflectionUtils.executePrivateMethod(generator, "generatePackage",
                new java.lang.Class[] { PersistenceSession.class }, new Object[] { expectedState });
        ReflectionUtils.executePrivateMethod(generator, "generateImports", new java.lang.Class[] {
                PersistenceSession.class, CodeGeneratorConfiguration.class }, new Object[] { expectedState,
                aCodeGeneratorConfiguration });
        ReflectionUtils.executePrivateMethod(generator, "generateClass", new java.lang.Class[] {
                PersistenceSession.class, CodeGeneratorConfiguration.class }, new Object[] { expectedState,
                aCodeGeneratorConfiguration });

        assertEquals(expectedState.getText(), generatedState.getText());

    }

    public void testGeneratePackage() {

        PersistenceSession generatedState = new PersistenceSession();
        /**
         * Generated
         */
        PersistenceSessionWithoutRestrictionsGenerator generator = new PersistenceSessionWithoutRestrictionsGenerator();
        ReflectionUtils.executePrivateMethod(generator, "generatePackage",
                new java.lang.Class[] { PersistenceSession.class }, new Object[] { generatedState });

        assertEquals("package " + /*
                                   * aCodeGeneratorConfiguration.getProyectPackageName
                                   * () +
                                   */"persistence;\n", generatedState.getText());

    }

    public void testSpecificGenerateImports() {
        /**
         * This test compares what generates the method at present with the
         * class layer of persistence used as mould. Originally the classes that
         * are in the package model.
         */
        try {
            CodeGeneratorConfiguration aCodeGeneratorConfiguration = new CodeGeneratorConfigurationProvider()
                    .getCodeGeneratorConfiguration();
            PersistenceSession generatedState = new PersistenceSession();
            /**
             * Generated
             */
            PersistenceSessionWithoutRestrictionsGenerator generator = new PersistenceSessionWithoutRestrictionsGenerator();
            ReflectionUtils.executePrivateMethod(generator, "generateImports", new java.lang.Class[] {
                    PersistenceSession.class, CodeGeneratorConfiguration.class }, new Object[] { generatedState,
                    aCodeGeneratorConfiguration });

            assertNotSame("\n" + "import java.util.Collection;\n" + "import java.util.HashMap;\n"
                    + "import java.util.Map;\n" + "import model.House;\n" + "import model.Company;\n"
                    + "import model.Student;\n" + "import model.Person;\n" + "import model.Address;\n"
                    + "import model.Employee;\n" + "import model.Door;\n" + "import model.daos.HouseDAO;\n"
                    + "import model.daos.CompanyDAO;\n" + "import model.daos.StudentDAO;\n"
                    + "import model.daos.PersonDAO;\n" + "import model.daos.AddressDAO;\n"
                    + "import model.daos.EmployeeDAO;\n" + "import model.daos.DoorDAO;\n", generatedState.getText());

        } catch (Exception e) {
            logger.error("Se ha levantado una excepción: " + e.getMessage());
            fail();
        }
    }

    public void testGeneralGenerateImports() {

        try {
            CodeGeneratorConfiguration aCodeGeneratorConfiguration = new CodeGeneratorConfigurationProvider()
                    .getCodeGeneratorConfiguration();
            PersistenceSession generatedState = new PersistenceSession();
            /**
             * Generated
             */
            PersistenceSessionWithoutRestrictionsGenerator generator = new PersistenceSessionWithoutRestrictionsGenerator();
            ReflectionUtils.executePrivateMethod(generator, "generateImports", new java.lang.Class[] {
                    PersistenceSession.class, CodeGeneratorConfiguration.class }, new Object[] { generatedState,
                    aCodeGeneratorConfiguration });
            /**
             * Expected
             */
            // String daoPackage =
            // aCodeGeneratorConfiguration.getProyectPackageName() + ".daos";
            String expectedString = new String("\n" + "import java.util.Collection;\n" + "import java.util.HashMap;\n"
                    + "import java.util.Map;\n");
            ClassList classList = aCodeGeneratorConfiguration.getClassList();
            for (Iterator<Class> iterator = classList.iterator(); iterator.hasNext();) {
                Class aClass = iterator.next();
                expectedString = expectedString + "import " + aClass.getName() + ";\n";
            }

            assertEquals(expectedString, generatedState.getText());

        } catch (Exception e) {
            logger.error("Se ha levantado una excepción: " + e.getMessage());
            fail();
        }
    }

    public void testGenerateAttributes() {

        try {
            CodeGeneratorConfiguration aCodeGeneratorConfiguration = new CodeGeneratorConfigurationProvider()
                    .getCodeGeneratorConfiguration();
            /**
             * Generated
             */
            PersistenceSessionWithoutRestrictionsGenerator generator = new PersistenceSessionWithoutRestrictionsGenerator();
            String generatedString = (String) ReflectionUtils.executePrivateMethod(generator, "generateAttributes",
                    new java.lang.Class[] { CodeGeneratorConfiguration.class },
                    new Object[] { aCodeGeneratorConfiguration });
            /**
             * Expected
             */
            String expectedString = "\n";
            ClassList classList = aCodeGeneratorConfiguration.getClassList();
            for (Iterator<Class> iterator = classList.iterator(); iterator.hasNext();) {
                Class aClass = iterator.next();
                expectedString = expectedString + "private " + aClass.getSimpleName() + "DAO "
                        + aClass.getSimpleName().toLowerCase() + "DAO" + ";\n";
            }
            expectedString = expectedString + "\n" + "private MappingAdministrator mappingAdministrator;\n"
                    + "private BusinessObjectDataObjectMapping businessObjectDataObjectMapping;\n\n";

            assertEquals(expectedString, generatedString);

        } catch (Exception e) {
            logger.error("Se ha levantado una excepción: " + e.getMessage());
            fail();
        }
    }

    public void testGenerateClass() {

        CodeGeneratorConfiguration aCodeGeneratorConfiguration = new CodeGeneratorConfigurationProvider()
                .getCodeGeneratorConfiguration();
        PersistenceSession generatedState = new PersistenceSession();
        /**
         * Generated
         */
        PersistenceSessionWithoutRestrictionsGenerator generator = new PersistenceSessionWithoutRestrictionsGenerator();
        ReflectionUtils.executePrivateMethod(generator, "generateClass", new java.lang.Class[] {
                PersistenceSession.class, CodeGeneratorConfiguration.class }, new Object[] { generatedState,
                aCodeGeneratorConfiguration });
        /**
         * Expected
         */
        String expectedText = "\n"
                + "public class "
                + "PersistenceLayer {"
                + "\n"
                + (String) ReflectionUtils.executePrivateMethod(generator, "generateAttributes",
                        new java.lang.Class[] { CodeGeneratorConfiguration.class },
                        new Object[] { aCodeGeneratorConfiguration })
                + "\n"
                + (String) ReflectionUtils.executePrivateMethod(generator, "generateConstructor",
                        new java.lang.Class[] { CodeGeneratorConfiguration.class },
                        new Object[] { aCodeGeneratorConfiguration })
                + "\n"
                + (String) ReflectionUtils.executePrivateMethod(generator, "generatePersistMethods",
                        new java.lang.Class[] {}, new Object[] {})
                + "\n"
                + (String) ReflectionUtils.executePrivateMethod(generator, "generateDeleteMethods",
                        new java.lang.Class[] {}, new Object[] {})
                + "\n"
                + (String) ReflectionUtils.executePrivateMethod(generator, "generateUpdateMethods",
                        new java.lang.Class[] { CodeGeneratorConfiguration.class },
                        new Object[] { aCodeGeneratorConfiguration })
                + "\n"
                + (String) ReflectionUtils.executePrivateMethod(generator, "generateFindMethods",
                        new java.lang.Class[] {}, new Object[] {})
                + "\n"
                + (String) ReflectionUtils.executePrivateMethod(generator, "generateFindAllMethods",
                        new java.lang.Class[] { CodeGeneratorConfiguration.class },
                        new Object[] { aCodeGeneratorConfiguration }) + "\n" + "}";

        assertEquals(expectedText, generatedState.getText());

    }

    // public void testGenerateDeleteMethods() {
    //		
    // try {
    // CodeGeneratorConfiguration aCodeGeneratorConfiguration = new
    // CodeGeneratorConfigurationProvider().getCodeGeneratorConfiguration();
    // /**
    // * Generated
    // */
    // PersistenceSessionWithoutRestrictionsGenerator generator = new
    // PersistenceSessionWithoutRestrictionsGenerator();
    // String generatedText =
    // (String)ReflectionUtils.executePrivateMethod(generator
    // ,"generateDeleteMethods",new
    // Class[]{CodeGeneratorConfiguration.class},new
    // Object[]{aCodeGeneratorConfiguration});
    // /**
    // * Expected
    // */
    // String expectedText = new String("\n");
    // ClassList classList = aCodeGeneratorConfiguration.getClassList();
    // for (Iterator<Class> iterator = classList.getIterator();
    // iterator.hasNext();) {
    // Class aClass = iterator.next();
    //			
    // expectedText = expectedText + "	public void delete("+
    // aClass.getSimpleName() + " " + aClass.getSimpleName().toLowerCase() +
    // ") {\n"
    // // + "		" + aCodeGeneratorConfiguration.getProyectPackageName() +
    // ".persistence.dataobject." + aClass.getSimpleName() + " dataObject = ("
    // // + aCodeGeneratorConfiguration.getProyectPackageName() +
    // ".persistence.dataobject." + aClass.getSimpleName() +
    // ") mappingAdministrator.getAndDeleteDataObject(" +
    // aClass.getSimpleName().toLowerCase() +
    // ",businessObjectDataObjectMapping);\n"
    // + "		" + aClass.getSimpleName().toLowerCase() + "DAO.delete(" +
    // "dataObject"/*aClass.getSimpleName().toLowerCase()*/ + ");\n"
    // + "	}\n";
    // }
    // assertEquals(expectedText,generatedText);
    //			
    // } catch (Exception e) {
    // fail("Error in testGenerateDeleteMethods. ");
    // }
    // }

    public void testAnotherGenerateDeleteMethods() {
        /**
         * This test compares what generates the method at present with the
         * class layer of persistence used as mould. Originally the classes that
         * are in the package model.
         */

        /**
         * Generated
         */
        PersistenceSessionWithoutRestrictionsGenerator generator = new PersistenceSessionWithoutRestrictionsGenerator();
        String generatedText = (String) ReflectionUtils.executePrivateMethod(generator, "generateDeleteMethods",
                new java.lang.Class[] {}, new Object[] {});
        /**
         * Expected
         */
        String expectedText = "\n"
                + "	public void delete(House house) {\n"
                + "		model.persistence.dataobject.House dataObject = (model.persistence.dataobject.House) mappingAdministrator.getAndDeleteDataObject(house,businessObjectDataObjectMapping);\n"
                + "		houseDAO.delete(dataObject);\n"
                + "	}\n"
                + "	public void delete(Company company) {\n"
                + "		model.persistence.dataobject.Company dataObject = (model.persistence.dataobject.Company) mappingAdministrator.getAndDeleteDataObject(company,businessObjectDataObjectMapping);\n"
                + "		companyDAO.delete(dataObject);\n"
                + "	}\n"
                + "	public void delete(Student student) {\n"
                + "		model.persistence.dataobject.Student dataObject = (model.persistence.dataobject.Student) mappingAdministrator.getAndDeleteDataObject(student,businessObjectDataObjectMapping);\n"
                + "		studentDAO.delete(dataObject);\n"
                + "	}\n"
                + "	public void delete(Person person) {\n"
                + "		model.persistence.dataobject.Person dataObject = (model.persistence.dataobject.Person) mappingAdministrator.getAndDeleteDataObject(person,businessObjectDataObjectMapping);\n"
                + "		personDAO.delete(dataObject);\n"
                + "	}\n"
                + "	public void delete(Address address) {\n"
                + "		model.persistence.dataobject.Address dataObject = (model.persistence.dataobject.Address) mappingAdministrator.getAndDeleteDataObject(address,businessObjectDataObjectMapping);\n"
                + "		addressDAO.delete(dataObject);\n"
                + "	}\n"
                + "	public void delete(Employee employee) {\n"
                + "		model.persistence.dataobject.Employee dataObject = (model.persistence.dataobject.Employee) mappingAdministrator.getAndDeleteDataObject(employee,businessObjectDataObjectMapping);\n"
                + "		employeeDAO.delete(dataObject);\n"
                + "	}\n"
                + "	public void delete(Door door) {\n"
                + "		model.persistence.dataobject.Door dataObject = (model.persistence.dataobject.Door) mappingAdministrator.getAndDeleteDataObject(door,businessObjectDataObjectMapping);\n"
                + "		doorDAO.delete(dataObject);\n" + "	}\n";

        assertNotSame(expectedText, generatedText);

    }

    // public void testGeneratePersistMethods() {
    //		
    // try {
    // CodeGeneratorConfiguration aCodeGeneratorConfiguration = new
    // CodeGeneratorConfigurationProvider().getCodeGeneratorConfiguration();
    // /**
    // * Generated
    // */
    // PersistenceSessionWithoutRestrictionsGenerator generator = new
    // PersistenceSessionWithoutRestrictionsGenerator();
    // String generatedText =
    // (String)ReflectionUtils.executePrivateMethod(generator
    // ,"generatePersistMethods",new
    // Class[]{CodeGeneratorConfiguration.class},new
    // Object[]{aCodeGeneratorConfiguration});
    // /**
    // * Expected
    // */
    // String expectedText = new String("\n");
    // ClassList classList = aCodeGeneratorConfiguration.getClassList();
    // for (Iterator<Class> iterator = classList.getIterator();
    // iterator.hasNext();) {
    // Class aClass = iterator.next();
    // expectedText = expectedText + "	public void persist("+
    // aClass.getSimpleName() + " " + aClass.getSimpleName().toLowerCase() +
    // ") {\n"
    // //+ "		" + aCodeGeneratorConfiguration.getProyectPackageName() +
    // ".persistence.dataobject." + aClass.getSimpleName() + " dataObject = ("
    // //+ aCodeGeneratorConfiguration.getProyectPackageName() +
    // ".persistence.dataobject." + aClass.getSimpleName() +
    // ") mappingAdministrator.createOrUpdateDataObject(" +
    // aClass.getSimpleName().toLowerCase() +
    // ",businessObjectDataObjectMapping);\n"
    // + "		" + aClass.getSimpleName().toLowerCase() + "DAO.create(" +
    // "dataObject"/*aClass.getSimpleName().toLowerCase()*/ + ");\n"
    // + "	}\n";
    // }
    // assertEquals(expectedText,generatedText);
    // } catch (Exception e) {
    // fail("Error in testGeneratePersistMethods. ");
    // }
    // }

    public void testAnotherGeneratePersistMethods() {
        /**
         * This test compares what generates the method at present with the
         * class layer of persistence used as mould. Originally the classes that
         * are in the package model.
         */

        /**
         * Generated
         */
        PersistenceSessionWithoutRestrictionsGenerator generator = new PersistenceSessionWithoutRestrictionsGenerator();
        String generatedText = (String) ReflectionUtils.executePrivateMethod(generator, "generatePersistMethods",
                new java.lang.Class[] {}, new Object[] {});
        /**
         * Expected
         */
        String expectedText = "\n"
                +

                "	public void persist(House house) {\n"
                + "		model.persistence.dataobject.House dataObject = (model.persistence.dataobject.House) mappingAdministrator.createOrUpdateDataObject(house,businessObjectDataObjectMapping);\n"
                + "		houseDAO.create(dataObject);\n"
                + "	}\n"
                + "	public void persist(Company company) {\n"
                + "		model.persistence.dataobject.Company dataObject = (model.persistence.dataobject.Company) mappingAdministrator.createOrUpdateDataObject(company,businessObjectDataObjectMapping);\n"
                + "		companyDAO.create(dataObject);\n"
                + "	}\n"
                + "	public void persist(Student student) {\n"
                + "		model.persistence.dataobject.Student dataObject = (model.persistence.dataobject.Student) mappingAdministrator.createOrUpdateDataObject(student,businessObjectDataObjectMapping);\n"
                + "		studentDAO.create(dataObject);\n"
                + "	}\n"
                + "	public void persist(Person person) {\n"
                + "		model.persistence.dataobject.Person dataObject = (model.persistence.dataobject.Person) mappingAdministrator.createOrUpdateDataObject(person,businessObjectDataObjectMapping);\n"
                + "		personDAO.create(dataObject);\n"
                + "	}\n"
                + "	public void persist(Address address) {\n"
                + "		model.persistence.dataobject.Address dataObject = (model.persistence.dataobject.Address) mappingAdministrator.createOrUpdateDataObject(address,businessObjectDataObjectMapping);\n"
                + "		addressDAO.create(dataObject);\n"
                + "	}\n"
                + "	public void persist(Employee employee) {\n"
                + "		model.persistence.dataobject.Employee dataObject = (model.persistence.dataobject.Employee) mappingAdministrator.createOrUpdateDataObject(employee,businessObjectDataObjectMapping);\n"
                + "		employeeDAO.create(dataObject);\n"
                + "	}\n"
                + "	public void persist(Door door) {\n"
                + "		model.persistence.dataobject.Door dataObject = (model.persistence.dataobject.Door) mappingAdministrator.createOrUpdateDataObject(door,businessObjectDataObjectMapping);\n"
                + "		doorDAO.create(dataObject);\n" + "	}\n";

        assertNotSame(expectedText, generatedText);

    }

    public void testGenerateUpdateMethods() {

        try {
            CodeGeneratorConfiguration aCodeGeneratorConfiguration = new CodeGeneratorConfigurationProvider()
                    .getCodeGeneratorConfiguration();
            /**
             * Generated
             */
            PersistenceSessionWithoutRestrictionsGenerator generator = new PersistenceSessionWithoutRestrictionsGenerator();
            String generatedText = (String) ReflectionUtils.executePrivateMethod(generator, "generateUpdateMethods",
                    new java.lang.Class[] { CodeGeneratorConfiguration.class },
                    new Object[] { aCodeGeneratorConfiguration });
            /**
             * Expected
             */
            String expectedText = "\n";
            ClassList classList = aCodeGeneratorConfiguration.getClassList();
            for (Iterator<?> iterator = classList.iterator(); iterator.hasNext();) {
                Class aClass = (Class) iterator.next();

                expectedText = expectedText + "	public void update(" + aClass.getSimpleName() + " "
                        + aClass.getSimpleName().toLowerCase() + ") {\n"
                        // + "		" +
                        // aCodeGeneratorConfiguration.getProyectPackageName() +
                        // ".persistence.dataobject." + aClass.getSimpleName() +
                        // " dataObject = ("
                        // + aCodeGeneratorConfiguration.getProyectPackageName()
                        // + ".persistence.dataobject." + aClass.getSimpleName()
                        // + ") mappingAdministrator.createOrUpdateDataObject("
                        // + aClass.getSimpleName().toLowerCase() +
                        // ",businessObjectDataObjectMapping);\n"
                        + "		" + aClass.getSimpleName().toLowerCase() + "DAO.update(" + "dataObject" + ");\n" + "	}\n";

            }

            assertEquals(expectedText, generatedText);
        } catch (Exception e) {
            logger.error("Se ha levantado una excepción: " + e.getMessage());
            fail();
        }
    }

    public void testGenerateFindAllMethods() {

        try {
            CodeGeneratorConfiguration aCodeGeneratorConfiguration = new CodeGeneratorConfigurationProvider()
                    .getCodeGeneratorConfiguration();
            /**
             * Generated
             */
            PersistenceSessionWithoutRestrictionsGenerator generator = new PersistenceSessionWithoutRestrictionsGenerator();
            String generatedText = (String) ReflectionUtils.executePrivateMethod(generator, "generateFindAllMethods",
                    new java.lang.Class[] { CodeGeneratorConfiguration.class },
                    new Object[] { aCodeGeneratorConfiguration });
            /**
             * Expected
             */
            ClassList classList = aCodeGeneratorConfiguration.getClassList();
            String expectedText = "\n";
            for (Iterator<Class> iterator = classList.iterator(); iterator.hasNext();) {
                Class aClass = iterator.next();

                expectedText = expectedText + "	public Collection findAll" + aClass.getSimpleName() + "() {\n"
                        + "		return mappingAdministrator.createOrUpdateAndGetBusinessObjects("
                        + aClass.getSimpleName().toLowerCase() + "DAO.findAll(), businessObjectDataObjectMapping);\n"
                        + "	}\n";
            }
            assertEquals(expectedText, generatedText);
        } catch (Exception e) {
            logger.error("Se ha levantado una excepción: " + e.getMessage());
            fail();
        }
    }

    // public void testGenerateFindMethods() {
    //		
    // try {
    // CodeGeneratorConfiguration aCodeGeneratorConfiguration = new
    // CodeGeneratorConfigurationProvider().getCodeGeneratorConfiguration();
    // /**
    // * Generated
    // */
    // PersistenceSessionWithoutRestrictionsGenerator generator = new
    // PersistenceSessionWithoutRestrictionsGenerator();
    // String generatedText =
    // (String)ReflectionUtils.executePrivateMethod(generator
    // ,"generateFindMethods",new Class[]{CodeGeneratorConfiguration.class},new
    // Object[]{aCodeGeneratorConfiguration});
    // /**
    // * Expected
    // */
    // String expectedText = new String("\n");
    // ClassList classList = aCodeGeneratorConfiguration.getClassList();
    // for (Iterator<Class> iterator = classList.getIterator();
    // iterator.hasNext();) {
    // Class aClass = iterator.next();
    //				
    // expectedText = expectedText + "	public Collection find("+
    // aClass.getSimpleName() + " " + aClass.getSimpleName().toLowerCase() +
    // ") {\n"
    //
    // //+ "		" + aCodeGeneratorConfiguration.getProyectPackageName() +
    // ".persistence.dataobject." + aClass.getSimpleName() + " dataObject = ("
    // //+ aCodeGeneratorConfiguration.getProyectPackageName() +
    // ".persistence.dataobject." + aClass.getSimpleName() +
    // ") mappingAdministrator.createOrUpdateDataObject(" +
    // aClass.getSimpleName().toLowerCase()
    // + ",businessObjectDataObjectMapping);\n"
    // + "		Collection dataObjects = " + aClass.getSimpleName().toLowerCase() +
    // "DAO.findByExample(dataObject);\n"
    // +
    // "		return mappingAdministrator.createOrUpdateAndGetBusinessObjects(dataObjects, businessObjectDataObjectMapping);\n"
    // + "	}\n";
    // }
    // assertEquals(expectedText,generatedText);
    // } catch (Exception e) {
    // fail("Error in testGenerateFindMethods. ");
    // }
    // }
    public void testAnotherGenerateUpdateMethods() {
        /**
         * This test compares what generates the method at present with the
         * class layer of persistence used as mould. Originally the classes that
         * are in the package model.
         */
        try {
            CodeGeneratorConfiguration aCodeGeneratorConfiguration = new CodeGeneratorConfigurationProvider()
                    .getCodeGeneratorConfiguration();
            /**
             * Generated
             */
            PersistenceSessionWithoutRestrictionsGenerator generator = new PersistenceSessionWithoutRestrictionsGenerator();
            String generatedText = (String) ReflectionUtils.executePrivateMethod(generator, "generateUpdateMethods",
                    new java.lang.Class[] { CodeGeneratorConfiguration.class },
                    new Object[] { aCodeGeneratorConfiguration });
            /**
             * Expected
             */
            String expectedText = "\n"
                    +

                    "	public void update(House house) {\n"
                    + "		model.persistence.dataobject.House dataObject = (model.persistence.dataobject.House) mappingAdministrator.createOrUpdateDataObject(house,businessObjectDataObjectMapping);\n"
                    + "		houseDAO.update(dataObject);\n"
                    + "	}\n"
                    + "	public void update(Company company) {\n"
                    + "		model.persistence.dataobject.Company dataObject = (model.persistence.dataobject.Company) mappingAdministrator.createOrUpdateDataObject(company,businessObjectDataObjectMapping);\n"
                    + "		companyDAO.update(dataObject);\n"
                    + "	}\n"
                    + "	public void update(Student student) {\n"
                    + "		model.persistence.dataobject.Student dataObject = (model.persistence.dataobject.Student) mappingAdministrator.createOrUpdateDataObject(student,businessObjectDataObjectMapping);\n"
                    + "		studentDAO.update(dataObject);\n"
                    + "	}\n"
                    + "	public void update(Person person) {\n"
                    + "		model.persistence.dataobject.Person dataObject = (model.persistence.dataobject.Person) mappingAdministrator.createOrUpdateDataObject(person,businessObjectDataObjectMapping);\n"
                    + "		personDAO.update(dataObject);\n"
                    + "	}\n"
                    + "	public void update(Address address) {\n"
                    + "		model.persistence.dataobject.Address dataObject = (model.persistence.dataobject.Address) mappingAdministrator.createOrUpdateDataObject(address,businessObjectDataObjectMapping);\n"
                    + "		addressDAO.update(dataObject);\n"
                    + "	}\n"
                    + "	public void update(Employee employee) {\n"
                    + "		model.persistence.dataobject.Employee dataObject = (model.persistence.dataobject.Employee) mappingAdministrator.createOrUpdateDataObject(employee,businessObjectDataObjectMapping);\n"
                    + "		employeeDAO.update(dataObject);\n"
                    + "	}\n"
                    + "	public void update(Door door) {\n"
                    + "		model.persistence.dataobject.Door dataObject = (model.persistence.dataobject.Door) mappingAdministrator.createOrUpdateDataObject(door,businessObjectDataObjectMapping);\n"
                    + "		doorDAO.update(dataObject);\n" + "	}\n";

            assertNotSame(expectedText, generatedText);
        } catch (Exception e) {
            logger.error("Se ha levantado una excepción: " + e.getMessage());
            fail();
        }
    }

    public void testAnotherGenerateFindAllMethods() {
        /**
         * This test compares what generates the method at present with the
         * class layer of persistence used as mould. Originally the classes that
         * are in the package model.
         */
        try {
            CodeGeneratorConfiguration aCodeGeneratorConfiguration = new CodeGeneratorConfigurationProvider()
                    .getCodeGeneratorConfiguration();
            /**
             * Generated
             */
            PersistenceSessionWithoutRestrictionsGenerator generator = new PersistenceSessionWithoutRestrictionsGenerator();
            String generatedText = (String) ReflectionUtils.executePrivateMethod(generator, "generateFindAllMethods",
                    new java.lang.Class[] { CodeGeneratorConfiguration.class },
                    new Object[] { aCodeGeneratorConfiguration });
            /**
             * Expected
             */
            String expectedText = "\n"
                    +

                    "	public Collection findAllHouse() {\n"
                    + "		return mappingAdministrator.createOrUpdateAndGetBusinessObjects(houseDAO.findAll(), businessObjectDataObjectMapping);\n"
                    + "	}\n"
                    + "	public Collection findAllCompany() {\n"
                    + "		return mappingAdministrator.createOrUpdateAndGetBusinessObjects(companyDAO.findAll(), businessObjectDataObjectMapping);\n"
                    + "	}\n"
                    + "	public Collection findAllStudent() {\n"
                    + "		return mappingAdministrator.createOrUpdateAndGetBusinessObjects(studentDAO.findAll(), businessObjectDataObjectMapping);\n"
                    + "	}\n"
                    + "	public Collection findAllPerson() {\n"
                    + "		return mappingAdministrator.createOrUpdateAndGetBusinessObjects(personDAO.findAll(), businessObjectDataObjectMapping);\n"
                    + "	}\n"
                    + "	public Collection findAllAddress() {\n"
                    + "		return mappingAdministrator.createOrUpdateAndGetBusinessObjects(addressDAO.findAll(), businessObjectDataObjectMapping);\n"
                    + "	}\n"
                    + "	public Collection findAllEmployee() {\n"
                    + "		return mappingAdministrator.createOrUpdateAndGetBusinessObjects(employeeDAO.findAll(), businessObjectDataObjectMapping);\n"
                    + "	}\n"
                    +

                    "	public Collection findAllDoor() {\n"
                    + "		return mappingAdministrator.createOrUpdateAndGetBusinessObjects(doorDAO.findAll(), businessObjectDataObjectMapping);\n"
                    + "	}\n";
            assertNotSame(expectedText, generatedText);
        } catch (Exception e) {
            logger.error("Se ha levantado una excepción: " + e.getMessage());
            fail();
        }
    }

    public void testAnotherGenerateFindMethods() {
        /**
         * This test compares what generates the method at present with the
         * class layer of persistence used as mould. Originally the classes that
         * are in the package model.
         */
        try {

            /**
             * Generated
             */
            PersistenceSessionWithoutRestrictionsGenerator generator = new PersistenceSessionWithoutRestrictionsGenerator();
            String generatedText = (String) ReflectionUtils.executePrivateMethod(generator, "generateFindMethods",
                    new java.lang.Class[] {}, new Object[] {});
            /**
             * Expected
             */
            String expectedText = "\n"
                    +

                    "	public Collection find(House house) {\n"
                    + "		model.persistence.dataobject.House dataObject = (model.persistence.dataobject.House) mappingAdministrator.createOrUpdateDataObject(house,businessObjectDataObjectMapping);\n"
                    + "		Collection dataObjects = houseDAO.findByExample(dataObject);\n"
                    + "		return mappingAdministrator.createOrUpdateAndGetBusinessObjects(dataObjects, businessObjectDataObjectMapping);\n"
                    + "	}\n"
                    + "	public Collection find(Company company) {\n"
                    + "		model.persistence.dataobject.Company dataObject = (model.persistence.dataobject.Company) mappingAdministrator.createOrUpdateDataObject(company,businessObjectDataObjectMapping);\n"
                    + "		Collection dataObjects = companyDAO.findByExample(dataObject);\n"
                    + "		return mappingAdministrator.createOrUpdateAndGetBusinessObjects(dataObjects, businessObjectDataObjectMapping);\n"
                    + "	}\n"
                    + "	public Collection find(Student student) {\n"
                    + "		model.persistence.dataobject.Student dataObject = (model.persistence.dataobject.Student) mappingAdministrator.createOrUpdateDataObject(student,businessObjectDataObjectMapping);\n"
                    + "		Collection dataObjects = studentDAO.findByExample(dataObject);\n"
                    + "		return mappingAdministrator.createOrUpdateAndGetBusinessObjects(dataObjects, businessObjectDataObjectMapping);\n"
                    + "	}\n"
                    + "	public Collection find(Person person) {\n"
                    + "		model.persistence.dataobject.Person dataObject = (model.persistence.dataobject.Person) mappingAdministrator.createOrUpdateDataObject(person,businessObjectDataObjectMapping);\n"
                    + "		Collection dataObjects = personDAO.findByExample(dataObject);\n"
                    + "		return mappingAdministrator.createOrUpdateAndGetBusinessObjects(dataObjects, businessObjectDataObjectMapping);\n"
                    + "	}\n"
                    + "	public Collection find(Address address) {\n"
                    + "		model.persistence.dataobject.Address dataObject = (model.persistence.dataobject.Address) mappingAdministrator.createOrUpdateDataObject(address,businessObjectDataObjectMapping);\n"
                    + "		Collection dataObjects = addressDAO.findByExample(dataObject);\n"
                    + "		return mappingAdministrator.createOrUpdateAndGetBusinessObjects(dataObjects, businessObjectDataObjectMapping);\n"
                    + "	}\n"
                    + "	public Collection find(Employee employee) {\n"
                    + "		model.persistence.dataobject.Employee dataObject = (model.persistence.dataobject.Employee) mappingAdministrator.createOrUpdateDataObject(employee,businessObjectDataObjectMapping);\n"
                    + "		Collection dataObjects = employeeDAO.findByExample(dataObject);\n"
                    + "		return mappingAdministrator.createOrUpdateAndGetBusinessObjects(dataObjects, businessObjectDataObjectMapping);\n"
                    + "	}\n"
                    + "	public Collection find(Door door) {\n"
                    + "		model.persistence.dataobject.Door dataObject = (model.persistence.dataobject.Door) mappingAdministrator.createOrUpdateDataObject(door,businessObjectDataObjectMapping);\n"
                    + "		Collection dataObjects = doorDAO.findByExample(dataObject);\n"
                    + "		return mappingAdministrator.createOrUpdateAndGetBusinessObjects(dataObjects, businessObjectDataObjectMapping);\n"
                    + "	}\n";

            assertNotSame(expectedText, generatedText);
        } catch (Exception e) {
            logger.error("Se ha levantado una excepción: " + e.getMessage());
            fail();
        }
    }
}
