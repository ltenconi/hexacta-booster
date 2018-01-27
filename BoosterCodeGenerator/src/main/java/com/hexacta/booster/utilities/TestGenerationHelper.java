package com.hexacta.booster.utilities;

import com.hexacta.booster.codegeneration.configuration.ClassFinder;
import com.hexacta.booster.codegeneration.configuration.CodeGeneratorConfiguration;
import com.hexacta.booster.codegeneration.configuration.NotSupportedClassException;
import com.hexacta.booster.codegeneration.metamodel.Class;
import com.hexacta.booster.codegeneration.metamodel.Field;
import com.hexacta.booster.codegeneration.metamodel.Type;
import com.hexacta.booster.project.configuration.JavaProjectType;
import com.hexacta.booster.project.configuration.ProjectType;

/**
 * 
 */
public final class TestGenerationHelper {

    private TestGenerationHelper() {

    }

    public static Type getType(final String modificablePropertyName, final Field[] fieldsSet) {

        boolean end = false;
        int i = 0;
        Type type = null;
        while (!end && i < fieldsSet.length) {
            if (fieldsSet[i].getName().equalsIgnoreCase(modificablePropertyName)) {
                end = true;
                type = fieldsSet[i].getType();
            }
            i++;
        }
        return type;
    }

    public static boolean isDotNetModificableField(final Class aClass,
            final CodeGeneratorConfiguration aCodeGeneratorConfiguration, final Field field) {

        // boolean isModelClass =
        // aCodeGeneratorConfiguration.getClassList().hasClass
        // (field.getType().getName());
        // boolean isAbstractModelClass = field.getType().isAbstract()&&
        // isModelClass;
        boolean isIdField = field.getName().equalsIgnoreCase(
                aCodeGeneratorConfiguration.getClassIdMap().getId(aClass.getName()));
        // return (((!isIdField) && (!isAbstractModelClass)) &&
        // (field.getType().isPrimitive() || isModelClass));
        return !isIdField && field.getType().isPrimitive()
                || field.getType().getName().equalsIgnoreCase("System.String");
    }

    private static boolean isJavaModificableField(final Class aClass,
            final CodeGeneratorConfiguration aCodeGeneratorConfiguration, final Field field) {

        boolean isModelClass = aCodeGeneratorConfiguration.getClassList().hasClass(field.getType().getName());
        boolean isAbstractModelClass = field.getType().isAbstract() && isModelClass;
        boolean isIdField = field.getName().equalsIgnoreCase(
                aCodeGeneratorConfiguration.getClassIdMap().getId(aClass.getName()));
        boolean isStringType = field.getType().getName().equalsIgnoreCase("java.lang.String");
        return !isIdField && !isAbstractModelClass && (field.getType().isPrimitive() || isStringType);

    }

    private static boolean isModificableField(final Class aClass,
            final CodeGeneratorConfiguration aCodeGeneratorConfiguration, final Field field,
            final ProjectType proyectType) {

        if (proyectType.isDotNet())
            return isDotNetModificableField(aClass, aCodeGeneratorConfiguration, field);
        else
            return isJavaModificableField(aClass, aCodeGeneratorConfiguration, field);

    }

    public static String generateDotNetFieldValue(final Type formalParameterType) {

        String parameterType = formalParameterType.getName();

        if (parameterType.equalsIgnoreCase("System.Byte"))
            return "byte.MinValue";
        if (parameterType.equalsIgnoreCase("System.Int32") || parameterType.equalsIgnoreCase("System.Int64"))
            return "int.MaxValue";
        if (parameterType.equalsIgnoreCase("System.Boolean"))
            return "false";
        if (parameterType.equalsIgnoreCase("System.char"))
            return "'k'";
        if (parameterType.equalsIgnoreCase("System.Int16"))
            return "short.MaxValue";
        if (parameterType.equalsIgnoreCase("System.Single"))
            return "Single.MaxValue";
        if (parameterType.equalsIgnoreCase("System.Double"))
            return "double.MaxValue";
        if (parameterType.equalsIgnoreCase("System.Decimal"))
            return "Decimal.MaxValue";
        if (parameterType.equalsIgnoreCase("System.String"))
            return " \"aString\" ";
        return null; // ver que tipos primitivos de .net faltan
    }

    public static String generateJavaFieldValue(final Type formalParameterType,
            final CodeGeneratorConfiguration aCodeGeneratorConfiguration) throws NotSupportedClassException {

        String parameterType = formalParameterType.getName();

        if (parameterType.equalsIgnoreCase("java.lang.String"))
            return '"' + "aString" + '"';
        if (parameterType.equalsIgnoreCase("int") || parameterType.equalsIgnoreCase("java.lang.Integer"))
            return Integer.toString(1234);
        if (parameterType.equalsIgnoreCase("boolean") || parameterType.equalsIgnoreCase("java.lang.Boolean"))
            return "false";
        if (parameterType.equalsIgnoreCase("long"))
            return Long.toString(98765);
        if (parameterType.equalsIgnoreCase("java.lang.Long"))
            return "new Long(1234)";
        if (parameterType.equalsIgnoreCase("char") || parameterType.equalsIgnoreCase("java.lang.Character"))
            return "'k'";
        if (parameterType.equalsIgnoreCase("byte") || parameterType.equalsIgnoreCase("java.lang.Byte"))
            return "new Byte(\"1\")";
        if (parameterType.equalsIgnoreCase("short") || parameterType.equalsIgnoreCase("java.lang.Short"))
            return "new Short(\"1\")";
        if (parameterType.equalsIgnoreCase("float") || parameterType.equalsIgnoreCase("java.lang.Float"))
            return "Float.valueOf( \"3.14\" )";
        if (parameterType.equalsIgnoreCase("double") || parameterType.equalsIgnoreCase("java.lang.Double"))
            return "Double.valueOf( \"3.14\" )";
        if (parameterType.equalsIgnoreCase("java.util.Map"))
            return "new HashMap() ";
        if (parameterType.equalsIgnoreCase("java.util.Set"))
            return "new HashSet()";
        if (parameterType.equalsIgnoreCase("java.util.List"))
            return "new ArrayList()";
        if (formalParameterType.isArray())
            return "new " + formalParameterType.getComponentType().getName() + "[6]";

        return generateConstructorCallForModelCLass(ClassFinder.find(parameterType, aCodeGeneratorConfiguration
                .getClassList(), new JavaProjectType()));

    }

    public static String generateAnotherJavaFieldValue(final Type formalParameterType,
            final CodeGeneratorConfiguration aCodeGeneratorConfiguration) throws NotSupportedClassException {

        String parameterType = formalParameterType.getName();

        if (parameterType.equalsIgnoreCase("java.lang.String"))
            return '"' + "anotherString" + '"';
        if (parameterType.equalsIgnoreCase("int") || parameterType.equalsIgnoreCase("java.lang.Integer"))
            return Integer.toString(4321);
        if (parameterType.equalsIgnoreCase("boolean") || parameterType.equalsIgnoreCase("java.lang.Boolean"))
            return "true";
        if (parameterType.equalsIgnoreCase("long"))
            return Long.toString(95435);
        if (parameterType.equalsIgnoreCase("java.lang.Long"))
            return "new Long(4321)";
        if (parameterType.equalsIgnoreCase("char") || parameterType.equalsIgnoreCase("java.lang.Character"))
            return "'h'";
        if (parameterType.equalsIgnoreCase("byte") || parameterType.equalsIgnoreCase("java.lang.Byte"))
            return "new Byte(\"2\")";
        if (parameterType.equalsIgnoreCase("short") || parameterType.equalsIgnoreCase("java.lang.Short"))
            return "new Short(\"2\")";
        if (parameterType.equalsIgnoreCase("float") || parameterType.equalsIgnoreCase("java.lang.Float"))
            return "Float.valueOf( \"5.21\" )";
        if (parameterType.equalsIgnoreCase("double") || parameterType.equalsIgnoreCase("java.lang.Double"))
            return "Double.valueOf( \"5.21\" )";
        if (parameterType.equalsIgnoreCase("java.util.Map"))
            return "new HashMap() ";
        if (parameterType.equalsIgnoreCase("java.util.Set"))
            return "new HashSet()";
        if (parameterType.equalsIgnoreCase("java.util.List"))
            return "new ArrayList()";
        if (formalParameterType.isArray())
            return "new " + formalParameterType.getComponentType().getName() + "[5]";

        return generateConstructorCallForModelCLass(ClassFinder.find(parameterType, aCodeGeneratorConfiguration
                .getClassList(), new JavaProjectType()));

    }

    private static String generateConstructorCallForModelCLass(final Class aModelClass) {
        return "new " + aModelClass.getSimpleName() + "()";
    }

    public static String getModificablePropertyName(final Field[] fieldsSet, final Class aClass,
            final CodeGeneratorConfiguration aCodeGeneratorConfiguration, final ProjectType projectType) {
        int modificablePropertyPosition = 0;
        String propertyName = null;
        boolean foundModificablePosition = false;

        while (modificablePropertyPosition < fieldsSet.length && !foundModificablePosition) {
            if (isModificableField(aClass, aCodeGeneratorConfiguration, fieldsSet[modificablePropertyPosition],
                    projectType)) {
                foundModificablePosition = true;
                propertyName = fieldsSet[modificablePropertyPosition].getName();
            }
            modificablePropertyPosition++;
        }
        return propertyName;

    }

    public static String getIdContainerClass(final Class aClass) {

        if (aClass.getSuperclass() == null || aClass.getSuperclass().getName().equalsIgnoreCase("java.lang.Object") )
            return aClass.getSimpleName();
        else
            return getIdContainerClass(aClass.getSuperclass());

    }

    public static String getSetterMethod(final String modificablePropertyName) {

        if (modificablePropertyName.length() != 0) {
            String firstLetter = modificablePropertyName.substring(0, 1).toUpperCase();
            String restOfWord = modificablePropertyName.substring(1);

            return "set" + firstLetter + restOfWord;
        } else
            return "set";

    }

    public static String getGetterMethod(final String modificablePropertyName, final String propertyType) {

        if (modificablePropertyName.length() != 0) {
            String firstLetter = modificablePropertyName.substring(0, 1).toUpperCase();
            String restOfWord = modificablePropertyName.substring(1);

            if (propertyType.equalsIgnoreCase("boolean"))
                return "is" + firstLetter + restOfWord;
            else
                return "get" + firstLetter + restOfWord;
        } else
            return propertyType.equalsIgnoreCase("boolean") ? "is" : "get";
    }

}
