package com.hexacta.booster.utilities;

import junit.framework.TestCase;
import test.hexacta.booster.providers.MetaModelProvider;

import com.hexacta.booster.codegeneration.metamodel.Class;

/**
 * 
 */
public class JavaClassNameBuilderTest extends TestCase {

    public void testBuildForDAO() {

        Class aClass = MetaModelProvider.getMetaModel(test.model.Person.class);

        String actualDAOClassName = JavaClassNameBuilder.buildForDAO(aClass);
        assertEquals("PersonDAO", actualDAOClassName);

    }

    public void testBuildForDAOTest() {

        Class aClass = MetaModelProvider.getMetaModel(test.model.Person.class);

        String actualDAOTestClassName = JavaClassNameBuilder.buildForDAOTest(aClass);
        assertEquals("PersonDAOTest", actualDAOTestClassName);

    }

    public void testBuildForDTOInterface() {

        Class aClass = MetaModelProvider.getMetaModel(test.model.Person.class);

        String actualDTOClassName = JavaClassNameBuilder.buildForDTOInterface(aClass);
        assertEquals("IPersonDTO", actualDTOClassName);

    }

    public void testBuildForServiceInterface() {

        Class aClass = MetaModelProvider.getMetaModel(test.model.Person.class);

        String actualServiceInterfaceClassName = JavaClassNameBuilder.buildForServiceInterface(aClass);
        assertEquals("PersonService", actualServiceInterfaceClassName);

    }

    public void testBuildForServiceInterfaceImpl() {

        Class aClass = MetaModelProvider.getMetaModel(test.model.Person.class);

        String actualServiceInterfaceImplClassName = JavaClassNameBuilder.buildForServiceInterfaceImpl(aClass);
        assertEquals("PersonServiceImpl", actualServiceInterfaceImplClassName);

    }

    public void testBuildForTransactionalTest() {

        Class aClass = MetaModelProvider.getMetaModel(test.model.Person.class);

        String actualTransactionaTestClassName = JavaClassNameBuilder.buildForTransactionalTest(aClass);
        assertEquals("PersonServiceTransactionalTest", actualTransactionaTestClassName);

    }

    public void testBuildForViewAction() {

        Class aClass = MetaModelProvider.getMetaModel(test.model.Person.class);

        String actualViewAction = JavaClassNameBuilder.buildForViewAction(aClass);
        assertEquals("PersonAction", actualViewAction);

    }

    public void testBuildForActionValidation() {

        Class aClass = MetaModelProvider.getMetaModel(test.model.Person.class);

        String actualViewActionValidation = JavaClassNameBuilder.buildForActionValidation(aClass);
        assertEquals("PersonAction-validation", actualViewActionValidation);

    }

    public void testBuildForModelValidation() {

        Class aClass = MetaModelProvider.getMetaModel(test.model.Person.class);

        String actualViewMOdelValidation = JavaClassNameBuilder.buildForModelValidation(aClass);
        assertEquals("Person-validation", actualViewMOdelValidation);

    }

    public void testBuildForEntityForm() {

        Class aClass = MetaModelProvider.getMetaModel(test.model.Person.class);

        String actualEntityForm = JavaClassNameBuilder.buildForEntityForm(aClass);
        assertEquals("personForm", actualEntityForm);

    }

    public void testBuildForEntityList() {

        Class aClass = MetaModelProvider.getMetaModel(test.model.Person.class);

        String actualEntityList = JavaClassNameBuilder.buildForEntityList(aClass);
        assertEquals("personList", actualEntityList);

    }

}
