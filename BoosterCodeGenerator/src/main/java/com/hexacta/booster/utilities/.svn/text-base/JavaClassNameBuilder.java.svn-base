package com.hexacta.booster.utilities;

import com.hexacta.booster.codegeneration.metamodel.Class;

/**
 * 
 */
public final class JavaClassNameBuilder {

    private JavaClassNameBuilder() {

    }

    public static String buildForDAO(final Class aClass) {

        return aClass.getSimpleName() + "DAO";
    }

    public static String buildForDAOTest(final Class aClass) {
        return aClass.getSimpleName() + "DAOTest";
    }

    public static String buildForDTOInterface(final Class aClass) {

        return "I" + aClass.getSimpleName() + "DTO";
    }

    public static String buildForServiceInterface(final Class aClass) {

        return aClass.getSimpleName() + "Service";
    }

    public static String buildForServiceInterfaceImpl(final Class aClass) {

        return aClass.getSimpleName() + "ServiceImpl";
    }

    public static String buildForTransactionalTest(final Class aClass) {

        return aClass.getSimpleName() + "ServiceTransactionalTest";
    }

    public static String buildForServiceTest(final Class aClass) {
        return aClass.getSimpleName() + "ServiceTest";
    }

    public static String buildForViewAction(final Class aClass) {
        return aClass.getSimpleName() + "Action";
    }

    public static String buildForActionValidation(final Class aClass) {
        return aClass.getSimpleName() + "Action-validation";
    }

    public static String buildForModelValidation(final Class aClass) {
        return aClass.getSimpleName() + "-validation";
    }

    public static String buildForEntityForm(final Class aClass) {
        return VarNameBuilder.buildForEntity(aClass) + "Form";
    }

    public static String buildForEntityList(final Class aClass) {
        return VarNameBuilder.buildForEntity(aClass) + "List";
    }

    public static String buildForViewActionTest(Class entityClass) {
        return entityClass.getSimpleName() + "ActionTest";
    }
    
    public static String buildForWebTest(final Class aClass) {
        return aClass.getSimpleName() + "WebTest";
    }
}
