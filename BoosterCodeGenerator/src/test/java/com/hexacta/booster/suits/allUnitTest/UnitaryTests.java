package com.hexacta.booster.suits.allUnitTest;

import junit.framework.Test;
import junit.framework.TestSuite;

import org.apache.log4j.Logger;

/**
 * 
 */
public final class UnitaryTests {

    /**
     * Log4j logger.
     */
    private static final Logger logger = Logger.getLogger(UnitaryTests.class.getSimpleName());

    private UnitaryTests() {

    }

    public static Test suite() {

        logger.info("Comienzo de ~ TESTS ~");

        TestSuite suite = new TestSuite("Unitary Test Suite");
        // $JUnit-BEGIN$

        /*
         * suite.addTestSuite(com.hexacta.booster.codegeneration.persistence.
         * configuration.hibernate.HibernateConfigurationGeneratorTest.class);
         * suite.addTestSuite(com.hexacta.booster.codegeneration.persistence.
         * configuration.hibernate.HibernateConfigurationTest.class); suite
         * .addTestSuite
         * (com.hexacta.booster.codegeneration.persistence.configuration
         * .hibernate.HibernateConfigurationXmlGeneratorTest.class);
         * 
         * suite
         * .addTestSuite(com.hexacta.booster.codegeneration.persistence.layer
         * .hibernate.SpringHibernateDaoTestGeneratorTest.class); suite
         * .addTestSuite
         * (com.hexacta.booster.codegeneration.persistence.configuration
         * .hibernate.HibernateMappingResourceTest.class); suite
         * .addTestSuite(com
         * .hexacta.booster.codegeneration.persistence.configuration
         * .hibernate.HibernateConfigurationGeneratorTest.class); suite
         * .addTestSuite
         * (com.hexacta.booster.codegeneration.persistence.configuration
         * .hibernate.HibernatePropertyNameTest.class); suite
         * .addTestSuite(com.hexacta
         * .booster.codegeneration.persistence.configuration
         * .hibernate.XmlHibernateCollectionGeneratorTest.class); suite
         * .addTestSuite
         * (com.hexacta.booster.codegeneration.persistence.configuration
         * .hibernate.XmlHibernateMappingGeneratorTest.class);
         * 
         * suite.addTestSuite(com.hexacta.booster.codegeneration.persistence.
         * ormmodel.OrmDataGeneratorTest.class);
         * 
         * 
         * 
         * suite.addTestSuite(com.hexacta.booster.codegeneration.persistence.layer
         * .PersistableClassGeneratorTest.class); suite
         * .addTestSuite(com.hexacta
         * .booster.codegeneration.persistence.layer.hibernate
         * .PersistenceSessionWithRestrictionsGeneratorTest.class); suite
         * .addTestSuite(com.hexacta.booster.codegeneration.persistence.layer.
         * PersistenceSessionTestGeneratorTest.class);
         * suite.addTestSuite(com.hexacta
         * .booster.codegeneration.persistence.layer
         * .PersistableClassTest.class);
         * suite.addTestSuite(com.hexacta.booster.codegeneration
         * .persistence.layer.PersistenceSessionTestTest.class);
         * suite.addTestSuite
         * (com.hexacta.booster.codegeneration.persistence.layer
         * .MappingAdministratorTest.class); suite
         * .addTestSuite(com.hexacta.booster
         * .codegeneration.persistence.layer.MappingAdministratorGeneratorTest
         * .class);
         * suite.addTestSuite(com.hexacta.booster.codegeneration.persistence
         * .layer.PersistenceSessionTestTest.class); suite
         * .addTestSuite(com.hexacta.booster.codegeneration.persistence.layer.
         * BusinessObjectDataObjectMappingGeneratorTest.class);
         * 
         * suite.addTestSuite(com.hexacta.booster.utilities.IdFieldAssignerTest.
         * class);suite.addTestSuite(com.hexacta.booster.utilities.
         * JavaClassFileNameBuilderTest.class);
         * suite.addTestSuite(com.hexacta.booster
         * .utilities.JavaClassNameBuilderTest.class);
         * suite.addTestSuite(com.hexacta
         * .booster.utilities.VarNameBuilderTest.class);
         * suite.addTestSuite(com.hexacta
         * .booster.utilities.PackageBuilderTest.class);
         * suite.addTestSuite(com.hexacta
         * .booster.utilities.PathBuilderTest.class);
         * 
         * suite
         * .addTestSuite(com.hexacta.booster.codegeneration.persistence.layer
         * .hibernate.PersistenceSessionWithoutRestrictionsGeneratorTest.class);
         * 
         * suite.addTestSuite(com.hexacta.booster.codegeneration.metamodel.
         * MetaModelGeneratorForJavaModelTest.class);
         * 
         * logger.info("Finalización de ~ TESTS ~");
         */
        return suite;

    }

}
