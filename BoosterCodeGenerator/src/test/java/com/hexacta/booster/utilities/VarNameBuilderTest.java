package com.hexacta.booster.utilities;

import junit.framework.TestCase;
import test.hexacta.booster.providers.MetaModelProvider;

import com.hexacta.booster.codegeneration.metamodel.Class;

/**
 * ALGO.
 * 
 * @author clopez
 * 
 */
public class VarNameBuilderTest extends TestCase {

    public void testBuildForDTO() {

        Class entityClass = MetaModelProvider.getMetaModel(test.model.Person.class);

        String actualDTOVarName = VarNameBuilder.buildForDTO(entityClass);
        assertEquals("personDTO", actualDTOVarName);

    }

    public void testBuildForDAO() {

        Class entityClass = MetaModelProvider.getMetaModel(test.model.Person.class);

        String actualDAOVarName = VarNameBuilder.buildForDAO(entityClass);
        assertEquals("personDAO", actualDAOVarName);

    }

    public void testBuildForEntity() {

        Class entityClass = MetaModelProvider.getMetaModel(test.model.Person.class);

        String actualEntityVarName = VarNameBuilder.buildForEntity(entityClass);
        assertEquals("person", actualEntityVarName);

    }

    public void testBuildForService() {

        Class entityClass = MetaModelProvider.getMetaModel(test.model.Person.class);

        String actualServiceVarName = VarNameBuilder.buildForService(entityClass);
        assertEquals("personService", actualServiceVarName);

    }

    public void testBuildForAction() {

        Class entityClass = MetaModelProvider.getMetaModel(test.model.Person.class);

        String actualActionVarName = VarNameBuilder.buildForAction(entityClass);
        assertEquals("personAction", actualActionVarName);

    }

}
