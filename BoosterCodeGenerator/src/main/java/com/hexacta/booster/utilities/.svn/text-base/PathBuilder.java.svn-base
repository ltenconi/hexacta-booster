package com.hexacta.booster.utilities;

import com.hexacta.booster.codegeneration.configuration.DirectoryInfo;
import com.hexacta.booster.codegeneration.metamodel.Class;

/**
 * 
 */
public final class PathBuilder {

    /**
     * 
     */
    private PathBuilder() {

    }

    public static String buildForDto(final Class aClass, final DirectoryInfo directoryInfo) {

        return directoryInfo.getDtosPath() + aClass.getPackage().replaceFirst("model", "service").replace('.', '/')
                + "/dto/";

    }

    public static String buildForDao(final Class entityClass, final DirectoryInfo directoryInfo) {

        return directoryInfo.getDaosPath()
                + entityClass.getPackage().replaceFirst("model", "persistence").replace('.', '/') + "/dao/";

    }

    public static String buildForTestDao(final Class entityClass, final DirectoryInfo directoryInfo) {

        return directoryInfo.getTestDaosPath()
                + entityClass.getPackage().replaceFirst("model", "persistence").replace('.', '/') + "/dao/test/";

    }

    public static String buildForServiceInterface(final Class entityClass, final DirectoryInfo directoryInfo) {

        return directoryInfo.getServicePath()
                + entityClass.getPackage().replaceFirst("model", "service").replace('.', '/') + "/";
    }

    public static String buildForServiceInterfaceImpl(final Class entityClass, final DirectoryInfo directoryInfo) {

        return directoryInfo.getServicePath()
                + entityClass.getPackage().replaceFirst("model", "service").replace('.', '/') + "/impl/";
    }

    public static String buildForTransactionalService(final Class entityClass, final DirectoryInfo directoryInfo) {

        return directoryInfo.getTestServicePath()
                + entityClass.getPackage().replaceFirst("model", "service").replace('.', '/') + "/";
    }

    public static String buildForServiceImplementationMock(final Class entityClass, final DirectoryInfo directoryInfo) {

        return directoryInfo.getTestServicePath()
                + entityClass.getPackage().replaceFirst("model", "service").replace('.', '/') + "/mock/";
    }

    public static String buildForServiceTest(final Class entityClass, final DirectoryInfo directoryInfo) {

        return directoryInfo.getTestServicePath()
                + entityClass.getPackage().replaceFirst("model", "service").replace('.', '/') + "/";
    }

    public static String buildForHibernateGenericDao(final String groupId, final DirectoryInfo directoryInfo) {

        String packageName = groupId + ".persistence.dao";
        String hibernateGenericDAOPath = directoryInfo.getDaosPath() + packageName.replace(".", "/") + "/";
        return hibernateGenericDAOPath;

    }

    public static String buildForAbstractDAOTest(final String groupId, final DirectoryInfo directoryInfo) {

        String packageName = groupId + ".persistence.dao.test";
        String abstractDAOTestPath = directoryInfo.getTestDaosPath() + packageName.replace(".", "/") + "/";
        return abstractDAOTestPath;

    }

    public static String buildForAbstractServiceTest(final String groupId, final DirectoryInfo directoryInfo) {
        String packageName = groupId + ".service";
        String abstractServiceTestPath = directoryInfo.getTestServicePath() + packageName.replace(".", "/") + "/";
        return abstractServiceTestPath;
    }

    public static String buildForViewResources(final DirectoryInfo directoryInfo) {
        return directoryInfo.getViewPath() + "resources/";
    }

    public static String buildForViewCommon(final DirectoryInfo directoryInfo) {
        return directoryInfo.getViewPath() + "webapp/common/";
    }

    public static String buildForViewWebInf(final DirectoryInfo directoryInfo) {
        return directoryInfo.getViewPath() + "webapp/WEB-INF/";
    }

    public static String buildForViewPages(final DirectoryInfo directoryInfo) {
        return PathBuilder.buildForViewWebInf(directoryInfo) + "pages/";
    }

    public static String buildForViewAction(final String basePackage, final DirectoryInfo directoryInfo) {
        if (!basePackage.equals(""))
            return directoryInfo.getViewPath() + "java/" + basePackage.replace(".", "/") + "/webapp/action/";
        else
            return directoryInfo.getViewPath() + "java/webapp/action/";

    }

    public static String buildForViewModelValidation(final DirectoryInfo directoryInfo) {
        return PathBuilder.buildForViewResources(directoryInfo) + "validation/model/";
    }

    public static String buildForViewActionValidation(final DirectoryInfo directoryInfo) {
        return PathBuilder.buildForViewResources(directoryInfo) + "validation/action/";
    }

    public static String buildforActionTest(final String basePackage, final DirectoryInfo directoryInfo) {
        if (!basePackage.equals(""))
            return directoryInfo.getViewTestPath() + basePackage.replace(".", "/") + "/webapp/action/";
        else
            return directoryInfo.getViewTestPath() + "webapp/action/";
    }

    public static String buildforWebTest(final String basePackage, final DirectoryInfo directoryInfo) {
        if (!basePackage.equals(""))
            return directoryInfo.getViewTestPath() + basePackage.replace(".", "/") + "/webapp/webtest/";
        else
            return directoryInfo.getViewTestPath() + "webapp/webtest/";
    }

    public static String buildForHibernateSpringGenericDao(final String groupId, final DirectoryInfo directoryInfo) {

        String packageName = groupId + ".persistence.dao.impl";
        String hibernateGenericDAOPath = directoryInfo.getDaosPath() + packageName.replace(".", "/") + "/";
        return hibernateGenericDAOPath;
    }

    public static String buildForJPAHibernateSpringGenericDaoImpl(final String groupId,
            final DirectoryInfo directoryInfo) {
        String packageName = groupId + ".persistence.dao.impl";
        String hibernateGenericDAOPath = directoryInfo.getDaosPath() + packageName.replace(".", "/") + "/";
        return hibernateGenericDAOPath;
    }

    public static String buildForGenericDao(final String groupId, final DirectoryInfo directoryInfo) {
        String packageName = groupId + ".persistence.dao";
        String hibernateGenericDAOPath = directoryInfo.getDaosPath() + packageName.replace(".", "/") + "/";
        return hibernateGenericDAOPath;
    }

}
