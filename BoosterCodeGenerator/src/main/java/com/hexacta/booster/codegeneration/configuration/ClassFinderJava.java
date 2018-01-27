package com.hexacta.booster.codegeneration.configuration;

import java.lang.reflect.Modifier;

import com.hexacta.booster.codegeneration.metamodel.Class;
import com.hexacta.booster.codegeneration.metamodel.DummyClass;

/**
 * 
 */
public final class ClassFinderJava {

    /**
     * 
     */
    private ClassFinderJava() {
    }

    public static Class findForType(final String aTypeName) {

       Class primitive = findPrimitiveType(aTypeName);
       
       if (primitive!=null) return primitive;
       
        if (aTypeName.equalsIgnoreCase("java.lang.Byte") || aTypeName.equalsIgnoreCase("Byte"))
            return new Class(java.lang.Byte.class.getName(), java.lang.Byte.class.getSimpleName(), null, null,
                    java.lang.Byte.class.isArray(), java.lang.Byte.class.isPrimitive(), java.lang.Byte.class
                            .getPackage().getName(), null, false, null);
       
        if (aTypeName.equalsIgnoreCase("java.lang.Float") || aTypeName.equalsIgnoreCase("Float"))
            return new Class(java.lang.Float.class.getName(), java.lang.Float.class.getSimpleName(), null, null,
                    java.lang.Float.class.isArray(), java.lang.Float.class.isPrimitive(), java.lang.Float.class
                            .getPackage().getName(), null, false, null);
        
        if (aTypeName.equalsIgnoreCase("java.lang.Double")|| aTypeName.equalsIgnoreCase("Double"))
            return new Class(java.lang.Double.class.getName(), java.lang.Double.class.getSimpleName(), null, null,
                    java.lang.Double.class.isArray(), java.lang.Double.class.isPrimitive(), java.lang.Double.class
                            .getPackage().getName(), null, false, null);
        
        if (aTypeName.equalsIgnoreCase("java.lang.Short") || aTypeName.equalsIgnoreCase("Short"))
            return new Class(java.lang.Short.class.getName(), java.lang.Short.class.getSimpleName(), null, null,
                    java.lang.Short.class.isArray(), java.lang.Short.class.isPrimitive(), java.lang.Short.class
                            .getPackage().getName(), null, false, null);
       
        if (aTypeName.equalsIgnoreCase("java.lang.Long") || aTypeName.equalsIgnoreCase("Long"))
            return new Class(java.lang.Long.class.getName(), java.lang.Long.class.getSimpleName(), null, null,
                    java.lang.Long.class.isArray(), java.lang.Long.class.isPrimitive(), java.lang.Long.class
                            .getPackage().getName(), null, false, null);
       
        if (aTypeName.equalsIgnoreCase("java.lang.Character") || aTypeName.equalsIgnoreCase("Character"))
            return new Class(java.lang.Character.class.getName(), java.lang.Character.class.getSimpleName(), null,
                    null, java.lang.Character.class.isArray(), java.lang.Character.class.isPrimitive(),
                    java.lang.Character.class.getPackage().getName(), null, false, null);
      
        if (aTypeName.equalsIgnoreCase("java.lang.Boolean") || aTypeName.equalsIgnoreCase("Boolean"))
            return new Class(java.lang.Boolean.class.getName(), java.lang.Boolean.class.getSimpleName(), null, null,
                    java.lang.Boolean.class.isArray(), java.lang.Boolean.class.isPrimitive(), java.lang.Boolean.class
                            .getPackage().getName(), null, false, null);
      
        if (aTypeName.equalsIgnoreCase("java.lang.Integer")||aTypeName.equalsIgnoreCase("Integer"))
            return new Class(java.lang.Integer.class.getName(), java.lang.Integer.class.getSimpleName(), null, null,
                    java.lang.Integer.class.isArray(), java.lang.Integer.class.isPrimitive(), java.lang.Integer.class
                            .getPackage().getName(), null, false, null);
       
        if (aTypeName.equalsIgnoreCase("java.lang.String") || aTypeName.equalsIgnoreCase("String"))
            return new Class(java.lang.String.class.getName(), java.lang.String.class.getSimpleName(), null, null,
                    java.lang.String.class.isArray(), java.lang.String.class.isPrimitive(), java.lang.String.class
                            .getPackage().getName(), null, false, null);
       
        if (aTypeName.equalsIgnoreCase("java.lang.Object") || aTypeName.equalsIgnoreCase("Object"))
            return new Class(java.lang.String.class.getName(), java.lang.String.class.getSimpleName(), null, null,
                    java.lang.String.class.isArray(), java.lang.String.class.isPrimitive(), java.lang.String.class
                            .getPackage().getName(), null, false, null);

        try {
            java.lang.Class<?> aJavaClass = java.lang.Class.forName(aTypeName);
            return new Class(aJavaClass.getName(), aJavaClass.getSimpleName(), findForType(aJavaClass.getSuperclass()
                    .getName()), null, false, aJavaClass.isPrimitive(), null, null, Modifier.isAbstract(aJavaClass
                    .getModifiers()), null);
        } catch (ClassNotFoundException e) {
            return new DummyClass(aTypeName);
        }

    }

    private static Class findPrimitiveType(final String aTypeName) {
        
        if (aTypeName.equalsIgnoreCase("int"))
            return new Class("int", "int", null, null, false, true, null, null, false, null);
       
        if (aTypeName.equalsIgnoreCase("boolean"))
            return new Class("boolean", "boolean", null, null, false, true, null, null, false, null);
       
        if (aTypeName.equalsIgnoreCase("char"))
            return new Class("char", "char", null, null, false, true, null, null, false, null);
        
        if (aTypeName.equalsIgnoreCase("byte"))
            return new Class("byte", "byte", null, null, false, true, null, null, false, null);
       
        if (aTypeName.equalsIgnoreCase("short"))
            return new Class("short", "short", null, null, false, true, null, null, false, null);
       
        if (aTypeName.equalsIgnoreCase("float"))
            return new Class("float", "float", null, null, false, true, null, null, false, null);
        
        if (aTypeName.equalsIgnoreCase("double"))
            return new Class("double", "double", null, null, false, true, null, null, false, null);
       
        if (aTypeName.equalsIgnoreCase("long"))
            return new Class("long", "long", null, null, false, true, null, null, false, null);
       
        return null;
    }

}
