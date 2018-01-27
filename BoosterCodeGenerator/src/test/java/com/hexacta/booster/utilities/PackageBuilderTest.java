package com.hexacta.booster.utilities;

import junit.framework.TestCase;
import test.hexacta.booster.providers.MetaModelProvider;

import com.hexacta.booster.codegeneration.metamodel.Class;

/**
 * 
 */
public class PackageBuilderTest extends TestCase {

    public void testBuildForDAO() {

        Class aClass = MetaModelProvider.getMetaModel(test.model.Person.class);

        String actualDAOPackage = PackageNameBuilder.buildForDAO(aClass);
        assertEquals("test.persistence.dao", actualDAOPackage);

    }

    public void testBuildForDAOTest() {

        Class aClass = MetaModelProvider.getMetaModel(test.model.Person.class);

        String actualDAOTestPackage = PackageNameBuilder.buildForDAO(aClass);
        assertEquals("test.persistence.dao", actualDAOTestPackage);
    }

    public void testBuildForDTO() {

        Class aClass = MetaModelProvider.getMetaModel(test.model.Person.class);

        String actualDTOPackage = PackageNameBuilder.buildForDTO(aClass);
        assertEquals("test.service.dto", actualDTOPackage);
    }

    public void testBuildForServiceInterface() {

        Class aClass = MetaModelProvider.getMetaModel(test.model.Person.class);

        String actualServiceInterfacePackage = PackageNameBuilder.buildForServiceInterface(aClass);
        assertEquals("test.service", actualServiceInterfacePackage);
    }

    public void testBuildForServiceInterfaceImpl() {

        Class aClass = MetaModelProvider.getMetaModel(test.model.Person.class);

        String actualServiceInterfaceImplPackage = PackageNameBuilder.buildForServiceInterfaceImpl(aClass);
        assertEquals("test.service.impl", actualServiceInterfaceImplPackage);
    }

    public void testBuildForTransactionalTest() {

        Class aClass = MetaModelProvider.getMetaModel(test.model.Person.class);

        String actualTransactionalTestPackage = PackageNameBuilder.buildForTransactionalTest(aClass);
        assertEquals("test.service", actualTransactionalTestPackage);
    }

    public void testBuildForServiceImplementationMock() {

        Class aClass = MetaModelProvider.getMetaModel(test.model.Person.class);

        String actualServiceImplementationMockPackage = PackageNameBuilder.buildForServiceImplementationMock(aClass);
        assertEquals("test.service.mock", actualServiceImplementationMockPackage);
    }

}
