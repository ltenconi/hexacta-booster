package com.hexacta.booster.codegeneration.configuration;

import com.hexacta.booster.codegeneration.metamodel.Class;
import com.hexacta.booster.project.configuration.ProjectType;

/**
 * 
 */
public final class ClassFinder {

    /**
     * 
     */
    private ClassFinder() {
    }

    public static Class find(final String aClassName, final ClassList classList, final ProjectType projectType)
            throws NotSupportedClassException {

        if (classList.hasClass(aClassName))
            return classList.getClass(aClassName);
        else
            return findForType(aClassName, projectType);
    }

    public static Class findForType(final String aTypeName, final ProjectType projectType)
            throws NotSupportedClassException {
        if (projectType.isJava())
            return ClassFinderJava.findForType(aTypeName);
        else
            return ClassFinderDotNet.findForType(aTypeName);
    }

}
