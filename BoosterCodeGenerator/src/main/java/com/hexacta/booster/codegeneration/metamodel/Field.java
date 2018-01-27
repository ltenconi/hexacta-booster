package com.hexacta.booster.codegeneration.metamodel;

import java.io.Serializable;

/**
 * 
 */
public class Field implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = -5224477357996211135L;

    private String name;

    private Type type;

    private Class declaringClass;

    public Field(final String aName, final Type aType, final Class aDeclaringClass) {
        name = aName;
        type = aType;
        declaringClass = aDeclaringClass;
    }

    public String getName() {
        return name;
    }

    public Type getType() {
        return type;
    }

    public Class getDeclaringClass() {
        return declaringClass;
    }

}
