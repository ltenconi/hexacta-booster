package com.hexacta.booster.utilities;

import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.LinkedList;
import java.util.List;

/**
 * 
 * 
 * @author clopez
 * 
 */
public final class BoosterReflectionUtils {
    /**
     * Private constructor.
     */

    private BoosterReflectionUtils() {

    }

    /**
     * It returns the position in fields[] of any other field that is not the
     * field with name classId, if this one exists.
     * 
     * @param aClass
     *            Class owner of the fields.
     * @param classId
     *            name of a field of Class aClass.
     * @return return the position in fields[].
     */
    public static int modificablePropertyPosition(final Class<?> aClass, final String classId) {

        Field[] fieldsSet = aClass.getDeclaredFields();
        int i = 0;
        int modificablePropertyPosition = i;

        boolean found = false;
        while (i < fieldsSet.length && !found) {
            if (!fieldsSet[i].getName().equalsIgnoreCase(classId)) {
                modificablePropertyPosition = i;
                found = true;
            }
            i++;
        }
        return modificablePropertyPosition;
    }

    /**
     * It returns the class of (the field) fields[position], if this one exists.
     * 
     * @param aClass
     *            Class owner of the fields[].
     * @param position
     * 
     * @return return a class of field[position]
     */
    public static Class<?> getType(final Class<?> aClass, final int position) {

        Field[] fieldsSet = aClass.getDeclaredFields();
        Class<?> propertyType = null;

        if (position <= fieldsSet.length) {
            propertyType = fieldsSet[position].getType();

        }

        return propertyType;
    }

    /**
     * It returns the class of the parameters of constructor with numberOfParams
     * parameters in the class aClass.
     * 
     * @param aClass
     *            Class owner of the constructors.
     * @param numberOfParams
     *            Number of parameters of the looked constructor.
     * @return return An array with the class of the parameters.
     */
    public static Class<?>[] getConstructor(final Class<?> aClass, final int numberOfParams) {

        Constructor<?>[] constructors = aClass.getConstructors();
        boolean search = false;
        int i = 0;
        Constructor<?> betterConst = null;

        while (i < constructors.length && !search) {
            betterConst = constructors[i];
            if (betterConst.getParameterTypes().length == numberOfParams) {
                search = true;
            }
            i++;
        }
        if (!search)
            return null;
        Class<?>[] parametersType = betterConst.getParameterTypes();

        return parametersType;
    }

    /**
     * It returns.
     * 
     * @param aClass
     *            Class owner of the constructors.
     * @return return An array with the fields of the class.
     */
    public static Field[] getFields(final Class<?> aClass) {

        Field[] fields = aClass.getDeclaredFields();
        return fields;
    }

    /**
     * It returns.
     * 
     * @param aClass
     *            Class owner of the fields.
     * @return return An array with all the fields of the class, all the
     *         inherited too.
     */

    public static Field[] getAllInheritedFields(final Class<?> aClasss) {

        Class<?> aClass = aClasss;
        List<Field> accumulated = new LinkedList<Field>();

        while (aClass != null && !aClass.getName().equalsIgnoreCase("java.lang.Object")) {
            Field[] stepFields = aClass.getDeclaredFields();
            for (Field stepField : stepFields) {
                accumulated.add(stepField);
            }
            aClass = aClass.getSuperclass();
        }

        Field[] allFields = accumulated.toArray(new Field[accumulated.size()]); // stop
        // extract
        // AllFields
        return allFields;
    }

    public static Field getInheritedDeclaredFields(final Class<?> aClass, final String fieldName) {

        try {
            Field searchedField = null;
            if (aClass != null && !aClass.getName().equalsIgnoreCase("java.lang.Object")) {
                searchedField = aClass.getDeclaredField(fieldName);
            }

            return searchedField;
        } catch (NoSuchFieldException e) {
            return getInheritedDeclaredFields(aClass.getSuperclass(), fieldName);
        }
    }

    public static boolean inheritedClass(final Class<?> aClass) {

        Class<?> superClass = aClass.getSuperclass();

        return superClass != null && !superClass.getName().equalsIgnoreCase("java.lang.Object");

    }

    /**
     * It returns the number of constructors of the class aClass.
     * 
     * @param aClass
     *            Class owner of the constructors.
     * @return return number of constructors of aClass.
     */

    public static int numberOfConstructors(final Class<?> aClass) {

        Constructor<?>[] constructors = aClass.getConstructors();

        return constructors.length;
    }

    /**
     * It returns the number of fields of the class aClass.
     * 
     * @param aClass
     *            Class owner of the fields.
     * @return return number of fields of the class aClass.
     */
    public static int numberOfAttributes(final Class<?> aClass) {

        Field[] attributes = aClass.getDeclaredFields();

        return attributes.length;
    }

    /**
     * It returns a new instance of an array of type aClass, which has
     * componentsNumber components.
     * 
     * @param aClass
     *            Class of the new array.
     * @param componentsNumber
     *            Size of the new array
     * @return return a new instance of class array.
     */
    public static Object createArray(final Class<?> aClass, final int componentsNumber) {
        return Array.newInstance(aClass, componentsNumber);

    }

    /**
     * It returns the name of an attribute at Position Number.
     * 
     * @param modelClass
     *            Class owner of the attribute.
     * @param number
     *            Position of needed attribute.
     * @return name Name of the attribute.
     */

    public static String attributeAtPosition(final Class<?> modelClass, final int number) {

        Field[] declaredFields = modelClass.getDeclaredFields();
        String name = "";
        if (number <= declaredFields.length) {
            name = declaredFields[number].getName();

        }
        return name;
    }

    /**
     * It returns the field of an attribute at Position Number.
     * 
     * @param modelClass
     *            Class owner of the attribute.
     * @param number
     *            Position of needed attribute.
     * @return Field of the attribute.
     */
    public static Field getField(final Class<?> modelClass, final int number) {

        Field[] declaredFields = modelClass.getDeclaredFields();
        if (number <= declaredFields.length)
            return declaredFields[number];
        return declaredFields[0];
    }

    /**
     * It returns the name of an attribute at position Number.
     * 
     * @param modelClass
     *            Class owner of the attribute.
     * @param number
     *            Position of needed attribute.
     * @return Name of the attribute.
     */

    public static String getNameField(final Class<?> modelClass, final int number) {

        Field[] declaredFields = modelClass.getDeclaredFields();
        String name = "";
        if (number <= declaredFields.length)
            return declaredFields[number].getName();
        return name;
    }

    /**
     * It returns the names of modifier of attribute at position number.
     * 
     * @param modelClass
     *            Class owner of the attribute.
     * @param number
     *            Position of attribute.
     * @return Names of modifier of attribute.
     */
    public static String getFieldModifierName(final Class<?> modelClass, final int number) {

        Field[] declaredFields = modelClass.getDeclaredFields();
        String modifier = "";
        if (number <= declaredFields.length) {
            modifier = java.lang.reflect.Modifier.toString(declaredFields[number].getModifiers());
        }
        return modifier;
    }

    /**
     * It returns the name of type of attribute at position number.
     * 
     * @param modelClass
     *            Class owner of the attribute.
     * @param number
     *            Position of attribute.
     * @return Name of type of attribute.
     */
    public static String getNameType(final Class<?> modelClass, final int number) {

        Field[] declaredFields = modelClass.getDeclaredFields();
        String name = "";
        if (number <= declaredFields.length) {
            name = declaredFields[number].getType().getName();
        }
        return name;
    }

    /**
     * It returns the name of class of attribute at position number.
     * 
     * @param modelClass
     *            Class owner of the attribute.
     * @param number
     *            Position of attribute.
     * @return Name of class of attribute.
     */
    public static String getNameDeclaredClass(final Class<?> modelClass, final int number) {

        Field[] declaredFields = modelClass.getDeclaredFields();
        String name = "";
        if (number <= declaredFields.length) {
            name = declaredFields[number].getDeclaringClass().getName();
        }
        return name;
    }

    /**
     * It returns true if the type of attribute at position number is a
     * primitive type.
     * 
     * @param modelClass
     *            Class owner of the attribute.
     * @param number
     *            Position of attribute.
     * @return True / False.
     */
    public static boolean isPrimitiveType(final Class<?> modelClass, final int number) {

        Field[] declaredFields = modelClass.getDeclaredFields();
        boolean primitive = false;
        if (number <= declaredFields.length) {
            primitive = declaredFields[number].getType().isPrimitive();
        }
        return primitive;
    }

    /**
     * It returns the field of attribute which name is name.
     * 
     * @param modelClass
     *            Class owner of the attribute.
     * @param name
     *            Name of attribute.
     * @return The field of the attribute.
     */
    public static Field getFieldNamed(final Class<?> modelClass, final String name) {

        Field classFieldA = null;

        try {
            classFieldA = modelClass.getDeclaredField(name);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return classFieldA;
    }

    public static Constructor<?>[] getConstructors(final Class<?> clazz) {

        Constructor<?>[] cons = null;
        try {
            cons = clazz.getConstructors();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return cons;
    }

}