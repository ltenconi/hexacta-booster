package com.hexacta.booster.utilities;

import junit.framework.TestCase;
import test.hexacta.booster.providers.MetaModelProvider;

import com.hexacta.booster.codegeneration.metamodel.Class;

/**
 * 
 */
public class JavaClassFileNameBuilderTest extends TestCase {

    public void testBuildForDAO() {

        Class aClass = MetaModelProvider.getMetaModel(test.model.Person.class);

        String actualDAOClassFileName = JavaClassFileNameBuilder.buildForDAO(aClass);
        assertEquals("PersonDAO.java", actualDAOClassFileName);

    }

    public void testBuildForDAOTest() {

        Class aClass = MetaModelProvider.getMetaModel(test.model.Person.class);

        String actualDAOTestClassFileName = JavaClassFileNameBuilder.buildForDAOTest(aClass);
        assertEquals("PersonDAOTest.java", actualDAOTestClassFileName);

    }

    public void testBuildForDTOInterface() {

        Class aClass = MetaModelProvider.getMetaModel(test.model.Person.class);

        String actualDTOClassFileName = JavaClassFileNameBuilder.buildForDTOInterface(aClass);
        assertEquals("IPersonDTO.java", actualDTOClassFileName);

    }

    public void testBuildForServiceInterface() {

        Class aClass = MetaModelProvider.getMetaModel(test.model.Person.class);

        String actualServiceInterfaceClassFileName = JavaClassFileNameBuilder.buildForServiceInterface(aClass);
        assertEquals("PersonService.java", actualServiceInterfaceClassFileName);

    }

    public void testBuildForServiceInterfaceImpl() {

        Class aClass = MetaModelProvider.getMetaModel(test.model.Person.class);

        String actualServiceInterfaceImplClassFileName = JavaClassFileNameBuilder.buildForServiceInterfaceImpl(aClass);
        assertEquals("PersonServiceImpl.java", actualServiceInterfaceImplClassFileName);

    }

    public void testBuildForTransactionalTest() {

        Class aClass = MetaModelProvider.getMetaModel(test.model.Person.class);

        String actualTransactionaTestClassFileName = JavaClassFileNameBuilder.buildForTransactionalTest(aClass);
        assertEquals("PersonServiceTransactionalTest.java", actualTransactionaTestClassFileName);

    }

    public void testBuildForViewAction() {

        Class aClass = MetaModelProvider.getMetaModel(test.model.Person.class);

        String actualViewActionFileName = JavaClassFileNameBuilder.buildForViewAction(aClass);
        assertEquals("PersonAction.java", actualViewActionFileName);

    }

    public void testBuildForViewActionValidation() {

        Class aClass = MetaModelProvider.getMetaModel(test.model.Person.class);

        String actualViewActionValidationFileName = JavaClassFileNameBuilder.buildForActionValidation(aClass);
        assertEquals("PersonAction-validation.xml", actualViewActionValidationFileName);

    }

    public void testBuildForViewModelValidation() {

        Class aClass = MetaModelProvider.getMetaModel(test.model.Person.class);

        String actualViewModelValidationFileName = JavaClassFileNameBuilder.buildForModelValidation(aClass);
        assertEquals("Person-validation.xml", actualViewModelValidationFileName);

    }

    public void testBuildForViewEntityForm() {

        Class aClass = MetaModelProvider.getMetaModel(test.model.Person.class);

        String actualViewEntityFormFileName = JavaClassFileNameBuilder.buildForEntityForm(aClass);
        assertEquals("personForm.jsp", actualViewEntityFormFileName);

    }

    public void testBuildForViewEntityList() {

        Class aClass = MetaModelProvider.getMetaModel(test.model.Person.class);

        String actualViewEntityListFileName = JavaClassFileNameBuilder.buildForEntityList(aClass);
        assertEquals("personList.jsp", actualViewEntityListFileName);

    }

}
