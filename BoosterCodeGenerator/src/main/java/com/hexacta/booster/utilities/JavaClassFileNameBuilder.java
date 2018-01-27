package com.hexacta.booster.utilities;

import com.hexacta.booster.codegeneration.metamodel.Class;

/**
 * 
 */
public final class JavaClassFileNameBuilder {

    private static final String XML = ".xml";
    private static final String JSP = ".jsp";
    private static final String JAVA = ".java";

    /**
     * 
     */
    private JavaClassFileNameBuilder() {
    }

    public static String buildForDAO(final Class aClass) {

        return JavaClassNameBuilder.buildForDAO(aClass) + JAVA;
    }

    public static String buildForDAOTest(final Class aClass) {
        return JavaClassNameBuilder.buildForDAOTest(aClass) + JAVA;
    }

    public static String buildForDTOInterface(final Class aClass) {

        return JavaClassNameBuilder.buildForDTOInterface(aClass) + JAVA;
    }

    public static String buildForServiceInterface(final Class aClass) {

        return JavaClassNameBuilder.buildForServiceInterface(aClass) + JAVA;
    }

    public static String buildForServiceInterfaceImpl(final Class aClass) {

        return JavaClassNameBuilder.buildForServiceInterfaceImpl(aClass) + JAVA;
    }

    public static String buildForTransactionalTest(final Class aClass) {

        return JavaClassNameBuilder.buildForTransactionalTest(aClass) + JAVA;
    }

    public static String buildForServiceTest(final Class aClass) {
        return JavaClassNameBuilder.buildForServiceTest(aClass) + JAVA;
    }

    public static String buildForViewAction(final Class aClass) {
        return JavaClassNameBuilder.buildForViewAction(aClass) + JAVA;
    }

    public static String buildForActionValidation(final Class aClass) {
        return JavaClassNameBuilder.buildForActionValidation(aClass) + XML;
    }

    public static String buildForModelValidation(final Class aClass) {
    	return JavaClassNameBuilder.buildForModelValidation(aClass) + XML;
    }

    public static String buildForEntityForm(final Class aClass) {
        return JavaClassNameBuilder.buildForEntityForm(aClass) + JSP;
    }

    public static String buildForEntityList(final Class aClass) {
        return JavaClassNameBuilder.buildForEntityList(aClass) + JSP;
    }

    public static String buildForActionTest(Class entityClass) {
       return JavaClassNameBuilder.buildForViewActionTest(entityClass)+ JAVA;
    }
    
    public static String buildForWebTest(final Class aClass) {
    	return JavaClassNameBuilder.buildForWebTest(aClass) + XML;
    }

    
}
