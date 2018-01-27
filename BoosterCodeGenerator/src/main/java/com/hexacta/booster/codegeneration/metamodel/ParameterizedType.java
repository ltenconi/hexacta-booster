package com.hexacta.booster.codegeneration.metamodel;

import java.io.Serializable;

/**
 * 
 */
public class ParameterizedType extends Type implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 153210485558196141L;

    String[] actualTypeArguments;

    public ParameterizedType(final String aName, final String aSimpleName, final boolean isArray,
            final boolean isPrimitive, final Class componentType, final boolean isAbstract,
            final String[] actualTypeArguments) {
        super(aName, aSimpleName, isArray, isPrimitive, componentType, isAbstract);
        this.actualTypeArguments = actualTypeArguments;
    }

    public String[] getActualTypeArguments() {
        return actualTypeArguments;
    }

    @Override
    public boolean isParameterized() {
        return true;
    }
}
