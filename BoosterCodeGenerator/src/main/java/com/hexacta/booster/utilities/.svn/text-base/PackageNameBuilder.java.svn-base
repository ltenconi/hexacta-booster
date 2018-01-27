package com.hexacta.booster.utilities;

import com.hexacta.booster.codegeneration.configuration.CodeGeneratorConfiguration;
import com.hexacta.booster.codegeneration.metamodel.Class;

/**
 * 
 */
public final class PackageNameBuilder {

    /**
     * 
     */
    private PackageNameBuilder() {
    }

    public static String buildForDAO(final Class aClass) {
        return aClass.getPackage().replaceFirst("model", "persistence") + ".dao";
    }

    public static String buildForDAOTest(final Class aClass) {
        return aClass.getPackage().replaceFirst("model", "persistence") + ".dao.test";
    }

    public static String buildForAbstractDAOTest(final CodeGeneratorConfiguration codeGeneratorConfiguration) {
        String groupId = MetaModelUtils.getGroupId(codeGeneratorConfiguration.getClassList().iterator().next());
        return groupId + ".persistence.dao.test";
    }

    public static String buildForDTO(final Class aClass) {
        return aClass.getPackage().replaceFirst("model", "service") + ".dto";
    }

    public static String buildForServiceInterface(final Class aClass) {
        return aClass.getPackage().replaceFirst("model", "service");
    }

    public static String buildForServiceInterfaceImpl(final Class aClass) {
        return aClass.getPackage().replaceFirst("model", "service") + ".impl";
    }

    public static String buildForTransactionalTest(final Class aClass) {
        return aClass.getPackage().replaceFirst("model", "service");
    }

    public static String buildForServiceImplementationMock(final Class aClass) {
        return PackageNameBuilder.buildForServiceInterface(aClass) + ".mock";
    }

    public static String buildForServiceTest(final Class aClass) {
        return aClass.getPackage().replaceFirst("model", "service");
    }

    public static String buildForAbstractServiceTest(final CodeGeneratorConfiguration codeGeneratorConfiguration) {
        String groupId = MetaModelUtils.getGroupId(codeGeneratorConfiguration.getClassList().iterator().next());
        return groupId + ".service";
    }

    public static String buildForView(Class entityClass) {
        return entityClass.getPackage().replaceFirst(".model", "") + ".webapp.action";
        
    }

}
