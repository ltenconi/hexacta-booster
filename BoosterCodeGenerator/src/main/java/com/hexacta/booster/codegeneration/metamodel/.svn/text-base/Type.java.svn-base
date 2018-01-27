package com.hexacta.booster.codegeneration.metamodel;

import java.io.Serializable;

/**
 * 
 */
public class Type implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = -8674121167004210978L;

    private String name;

    private String simpleName;

    private boolean isArray;

    private boolean isPrimitive;

    private Class componentType;

    private boolean isAbstract;

    public Type(final String aName, final String aSimpleName, final boolean isArray, final boolean isPrimitive,
            final Class componentType, final boolean isAbstract) {
        name = aName;
        simpleName = aSimpleName;
        this.isArray = isArray;
        this.isPrimitive = isPrimitive;
        this.componentType = componentType;
        this.isAbstract = isAbstract;
    }

    public boolean isPrimitive() {
        return isPrimitive;
    }

    public String getName() {
        return name;
    }

    public boolean isArray() {
        return isArray;
    }

    public String getSimpleName() {
        return simpleName;
    }

    public Class getComponentType() {
        return componentType;
    }

    public boolean isParameterized() {
        return false;
    }

    public boolean isAbstract() {
        return isAbstract;
    }
}
