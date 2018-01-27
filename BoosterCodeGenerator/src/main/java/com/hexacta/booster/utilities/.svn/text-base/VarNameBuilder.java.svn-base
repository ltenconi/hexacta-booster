package com.hexacta.booster.utilities;

import com.hexacta.booster.codegeneration.metamodel.Class;

/**
 * 
 */
public final class VarNameBuilder {

    /**
     * 
     */
    private VarNameBuilder() {

    }

    public static String buildForDTO(final Class entityClass) {

        return entityClass.getSimpleName().substring(0, 1).toLowerCase()
                + entityClass.getSimpleName().substring(1, entityClass.getSimpleName().length()) + "DTO";
    }

    public static String buildForDAO(final Class entityClass) {

        String daoClassName = JavaClassNameBuilder.buildForDAO(entityClass);
        return daoClassName.substring(0, 1).toLowerCase() + daoClassName.substring(1, daoClassName.length());
    }

    public static String buildForEntity(final Class entityClass) {

        return entityClass.getSimpleName().substring(0, 1).toLowerCase()
                + entityClass.getSimpleName().substring(1, entityClass.getSimpleName().length());
    }

    public static String buildForService(final Class entityClass) {

        String serviceInterfaceName = JavaClassNameBuilder.buildForServiceInterface(entityClass);
        return serviceInterfaceName.substring(0, 1).toLowerCase()
                + serviceInterfaceName.substring(1, serviceInterfaceName.length());
    }

    public static String buildForAction(final Class aClass) {
        String actionString = JavaClassNameBuilder.buildForViewAction(aClass);
        return actionString.substring(0, 1).toLowerCase() + actionString.substring(1, actionString.length());
    }

}
