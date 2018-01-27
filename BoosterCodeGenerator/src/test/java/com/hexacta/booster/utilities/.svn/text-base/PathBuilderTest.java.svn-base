package com.hexacta.booster.utilities;

import junit.framework.TestCase;
import test.hexacta.booster.providers.MetaModelProvider;

import com.hexacta.booster.codegeneration.configuration.DirectoryInfo;
import com.hexacta.booster.codegeneration.metamodel.Class;

/**
 * 
 */
public class PathBuilderTest extends TestCase {

    public void testBuildForDto() {

        Class aClass = MetaModelProvider.getMetaModel(test.model.Person.class);

        DirectoryInfo directoryInfo = new DirectoryInfo();
        directoryInfo.setDtosPath("c:/SAC/service/src/main/java/");

        String actualPath = PathBuilder.buildForDto(aClass, directoryInfo);

        assertEquals("c:/SAC/service/src/main/java/test/service/dto/", actualPath);

    }

    public void testAnotherBuildForDto() {

        Class aClass = MetaModelProvider.getMetaModel(test.model.stuff.Car.class);

        DirectoryInfo directoryInfo = new DirectoryInfo();
        directoryInfo.setDtosPath("c:/SAC/service/src/main/java/");

        String actualPath = PathBuilder.buildForDto(aClass, directoryInfo);

        assertEquals("c:/SAC/service/src/main/java/test/service/stuff/dto/", actualPath);

    }

    public void testBuildForDao() {

        Class aClass = MetaModelProvider.getMetaModel(test.model.Person.class);

        DirectoryInfo directoryInfo = new DirectoryInfo();
        directoryInfo.setDaosPath("c:/SAC/persistence/src/main/java/");

        String actualPath = PathBuilder.buildForDao(aClass, directoryInfo);

        assertEquals("c:/SAC/persistence/src/main/java/test/persistence/dao/", actualPath);

    }

    public void testAnotherBuildForDao() {

        Class aClass = MetaModelProvider.getMetaModel(test.model.stuff.Car.class);

        DirectoryInfo directoryInfo = new DirectoryInfo();
        directoryInfo.setDaosPath("c:/SAC/persistence/src/main/java/");

        String actualPath = PathBuilder.buildForDao(aClass, directoryInfo);

        assertEquals("c:/SAC/persistence/src/main/java/test/persistence/stuff/dao/", actualPath);

    }

    public void testBuildForTestDao() {

        Class aClass = MetaModelProvider.getMetaModel(test.model.Person.class);

        DirectoryInfo directoryInfo = new DirectoryInfo();
        directoryInfo.setTestDaosPath("c:/SAC/persistence/src/test/java/");

        String actualPath = PathBuilder.buildForTestDao(aClass, directoryInfo);

        assertEquals("c:/SAC/persistence/src/test/java/test/persistence/dao/test/", actualPath);

    }

    public void testBuildForServiceInterface() {

        Class aClass = MetaModelProvider.getMetaModel(test.model.Person.class);

        DirectoryInfo directoryInfo = new DirectoryInfo();
        directoryInfo.setServicePath("c:/SAC/service/src/main/java/");

        String actualPath = PathBuilder.buildForServiceInterface(aClass, directoryInfo);

        assertEquals("c:/SAC/service/src/main/java/test/service/", actualPath);

    }

    public void testBuildForServiceInterfaceImpl() {

        Class aClass = MetaModelProvider.getMetaModel(test.model.Person.class);

        DirectoryInfo directoryInfo = new DirectoryInfo();
        directoryInfo.setServicePath("c:/SAC/service/src/main/java/");

        String actualPath = PathBuilder.buildForServiceInterfaceImpl(aClass, directoryInfo);

        assertEquals("c:/SAC/service/src/main/java/test/service/impl/", actualPath);

    }

    public void testBuildForTransactionalService() {

        Class aClass = MetaModelProvider.getMetaModel(test.model.Person.class);

        DirectoryInfo directoryInfo = new DirectoryInfo();
        directoryInfo.setTestServicePath("c:/SAC/service/src/test/java/");

        String actualPath = PathBuilder.buildForTransactionalService(aClass, directoryInfo);

        assertEquals("c:/SAC/service/src/test/java/test/service/", actualPath);

    }

    public void testBuildForServiceImplementationMock() {

        Class aClass = MetaModelProvider.getMetaModel(test.model.Person.class);

        DirectoryInfo directoryInfo = new DirectoryInfo();
        directoryInfo.setTestServicePath("c:/SAC/service/src/test/java/");

        String actualPath = PathBuilder.buildForServiceImplementationMock(aClass, directoryInfo);

        assertEquals("c:/SAC/service/src/test/java/test/service/mock/", actualPath);

    }

    public void testBuildForServiceTest() {

        Class aClass = MetaModelProvider.getMetaModel(test.model.Person.class);

        DirectoryInfo directoryInfo = new DirectoryInfo();
        directoryInfo.setTestServicePath("c:/SAC/service/src/test/java/");

        String actualPath = PathBuilder.buildForServiceTest(aClass, directoryInfo);

        assertEquals("c:/SAC/service/src/test/java/test/service/", actualPath);

    }

    public void testBuildForHibernateGenericDAOTest() {

        String groupId = "com.hexacta";

        DirectoryInfo directoryInfo = new DirectoryInfo();
        directoryInfo.setDaosPath("c:/SAC/persistence/src/main/java/");

        String actualPath = PathBuilder.buildForHibernateGenericDao(groupId, directoryInfo);

        assertEquals("c:/SAC/persistence/src/main/java/com/hexacta/persistence/dao/", actualPath);

    }

    public void testBuildForViewResources() {
        DirectoryInfo directoryInfo = new DirectoryInfo();
        directoryInfo.setViewPath("d:/view/");
        String generated = PathBuilder.buildForViewResources(directoryInfo);

        assertEquals("d:/view/resources/", generated);
    }

    public void testBuildForViewCommon() {
        DirectoryInfo directoryInfo = new DirectoryInfo();
        directoryInfo.setViewPath("d:/view/");
        String generated = PathBuilder.buildForViewCommon(directoryInfo);

        assertEquals("d:/view/webapp/common/", generated);
    }

    public void testBuildForViewWebInf() {
        DirectoryInfo directoryInfo = new DirectoryInfo();
        directoryInfo.setViewPath("d:/view/");
        String generated = PathBuilder.buildForViewWebInf(directoryInfo);

        assertEquals("d:/view/webapp/WEB-INF/", generated);
    }

    public void testBuildForViewPages() {
        DirectoryInfo directoryInfo = new DirectoryInfo();
        directoryInfo.setViewPath("d:/view/");
        String generated = PathBuilder.buildForViewPages(directoryInfo);

        assertEquals("d:/view/webapp/WEB-INF/pages/", generated);
    }

    public void testBuildForViewActionWithBasePackage() {
        DirectoryInfo directoryInfo = new DirectoryInfo();
        directoryInfo.setViewPath("d:/view/");
        String generated = PathBuilder.buildForViewAction("com.hex", directoryInfo);

        assertEquals("d:/view/java/com/hex/webapp/action/", generated);
    }

    public void testBuildForViewActionWithoutBasePackage() {
        DirectoryInfo directoryInfo = new DirectoryInfo();
        directoryInfo.setViewPath("d:/view/");
        String generated = PathBuilder.buildForViewAction("", directoryInfo);

        assertEquals("d:/view/java/webapp/action/", generated);
    }

    public void testBuildForViewModelValidationWithBasePackage() {
        DirectoryInfo directoryInfo = new DirectoryInfo();
        directoryInfo.setViewPath("d:/view/");
        String generated = PathBuilder.buildForViewModelValidation(directoryInfo);

        assertEquals("d:/view/resources/validation/model/", generated);
    }

    public void testBuildForViewModelValidationWithoutBasePackage() {
        DirectoryInfo directoryInfo = new DirectoryInfo();
        directoryInfo.setViewPath("d:/view/");
        String generated = PathBuilder.buildForViewModelValidation(directoryInfo);

        assertEquals("d:/view/resources/validation/model/", generated);
    }

    public void testBuildForViewActionValidationWithBasePackage() {
        DirectoryInfo directoryInfo = new DirectoryInfo();
        directoryInfo.setViewPath("d:/view/");
        String generated = PathBuilder.buildForViewActionValidation(directoryInfo);

        assertEquals("d:/view/resources/validation/action/", generated);
    }

    public void testBuildForViewActionValidationWithoutBasePackage() {
        DirectoryInfo directoryInfo = new DirectoryInfo();
        directoryInfo.setViewPath("d:/view/");
        String generated = PathBuilder.buildForViewActionValidation(directoryInfo);

        assertEquals("d:/view/resources/validation/action/", generated);
    }

}
