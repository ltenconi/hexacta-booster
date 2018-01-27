package com.hexacta.booster.codegeneration.metamodel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 */
public class Class implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = -1161674392878257489L;

    /**
     * 
     */

    private String name;

    private String simpleName;

    private Class superClass;

    private Field[] declaredFields;

    private Field[] fields;

    private boolean isArray;

    private boolean isPrimitive;

    private boolean isAbstract;

    private String packageName;

    private Constructor[] constructors;

    private Method[] methods;

    public Class(final String aName) {
        name = aName;
    }

    public Class(final String aName, final String aSimpleName, final Class aSuperClass, final Field[] aDeclaredFields,
            final boolean isArray, final boolean isPrimitive, final String aPackageName,
            final Constructor[] constructors, final boolean isAbstract, final Method[] methods) {
        name = aName;
        simpleName = aSimpleName;
        superClass = aSuperClass;
        declaredFields = aDeclaredFields;
        this.isArray = isArray;
        this.isPrimitive = isPrimitive;
        packageName = aPackageName;
        this.constructors = constructors;
        this.isAbstract = isAbstract;
        this.methods = methods;
    }

    public Class(final String aName, final String aSimpleName, final Class aSuperClass, final Field[] aDeclaredFields,
            final boolean isArray, final boolean isPrimitive, final String aPackageName,
            final Constructor[] constructors, final boolean isAbstract, final Method[] methods, final Field[] allFields) {
        name = aName;
        simpleName = aSimpleName;
        superClass = aSuperClass;
        declaredFields = aDeclaredFields;
        this.isArray = isArray;
        this.isPrimitive = isPrimitive;
        packageName = aPackageName;
        this.constructors = constructors;
        this.isAbstract = isAbstract;
        this.methods = methods;
        fields = allFields;
    }

    public void setArray(final boolean issArray) {
        isArray = issArray;
    }

    public void setConstructors(final Constructor[] constructors) {
        this.constructors = constructors;
    }

    public void setDeclaredFields(final Field[] declaredFields) {
        this.declaredFields = declaredFields;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public void setPackageName(final String packageName) {
        this.packageName = packageName;
    }

    public void setPrimitive(final boolean issPrimitive) {
        isPrimitive = issPrimitive;
    }

    public void setSimpleName(final String simpleName) {
        this.simpleName = simpleName;
    }

    public void setSuperClass(final Class superClass) {
        this.superClass = superClass;
    }

    public void setAbstract(final boolean issAbstract) {
        isAbstract = issAbstract;
    }

    public void setMethods(final Method[] methods) {
        this.methods = methods;
    }

    public String getName() {
        return name;
    }

    public String getSimpleName() {
        return simpleName;
    }

    public Class getSuperclass() {
        return superClass;
    }

    public Field[] getDeclaredFields() {
        return declaredFields;
    }

    public Method[] getMethods() {
        return methods;
    }

    public boolean isPrimitive() {
        return isPrimitive;
    }

    public Field getDeclaredField(final String aFieldName) {
        for (Field declaredField : declaredFields) {
            if (declaredField.getName().equalsIgnoreCase(aFieldName))
                return declaredField;
        }
        return null;
    }

    public Field getField(final String aFieldName) {
        for (Field field : fields) {
            if (field.getName().equalsIgnoreCase(aFieldName))
                return field;
        }
        return null;

    }

    public String getPackage() {
        return packageName;
    }

    public boolean isArray() {
        return isArray;
    }

    public Constructor[] getConstructors() {
        return constructors;
    }

    public boolean isAbstract() {
        return isAbstract;
    }

    public List<Method> getGetters() {
        List<Method> getters = new ArrayList<Method>();
        for (Method aMethod : methods) {
            if (aMethod.isGetter()) {
                getters.add(aMethod);
            }
        }
        return getters;
    }

    /**
     * @return the fields
     */
    public Field[] getFields() {
        return fields;
    }

    /**
     * @param fields
     *            the fields to set
     */
    public void setFields(final Field[] fields) {
        this.fields = fields;
    }
}
