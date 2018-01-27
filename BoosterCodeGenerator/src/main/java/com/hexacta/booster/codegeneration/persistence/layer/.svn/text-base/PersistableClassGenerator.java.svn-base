package com.hexacta.booster.codegeneration.persistence.layer;

/**
 * This class generates a persistable class from a common class
 */

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import com.hexacta.booster.codegeneration.configuration.ClassIdMap;
import com.hexacta.booster.codegeneration.configuration.ClassList;
import com.hexacta.booster.codegeneration.configuration.CodeGeneratorConfiguration;
import com.hexacta.booster.utilities.BoosterReflectionUtils;

/**
 * 
 */
public class PersistableClassGenerator {

    public PersistableClassGenerator() {

    }

    public PersistableClass generate(final Class<?> aClass, final CodeGeneratorConfiguration aCodeGeneratorConfiguration) {

        PersistableClass persistableClass = new PersistableClass(aClass, getPersistableModelName(aClass,
                aCodeGeneratorConfiguration));
        generatePackage(persistableClass);
        generateImports(persistableClass);
        generateClass(persistableClass, aCodeGeneratorConfiguration);

        return persistableClass;

    }

    private void generateClass(final PersistableClass aPersistableClass,
            final CodeGeneratorConfiguration aCodeGeneratorConfiguration) {

        Class<?> modelClass = aPersistableClass.getModelClass();

        String classText = "\n" + "public class " + modelClass.getSimpleName();

        if (modelClass.getSuperclass() != null) {
            classText = classText + " extends "
                    + getPersistableModelName(modelClass.getSuperclass(), aCodeGeneratorConfiguration);
        } else if (modelClass.getInterfaces() != null) {
            Class<?>[] interfaces = modelClass.getInterfaces();
            classText = classText + " implements ";
            for (int i = 0; i < interfaces.length; i++) {
                classText = classText + interfaces[i].getName();
                if (i + 1 < interfaces.length) {
                    classText = classText + ",";
                }
            }

        }
        classText = classText + "{" + "\n\n" + generateAttributes(aPersistableClass, aCodeGeneratorConfiguration)
                + "\n" + generateDefaultConstructor(aPersistableClass.getModelClass()) + "\n"
                + generateSettersGetters(modelClass, aCodeGeneratorConfiguration) + "}\n";

        aPersistableClass.append(classText);

    }

    private String getPersistableModelName(final Class<?> aClass,
            final CodeGeneratorConfiguration aCodeGeneratorConfiguration) {
        ClassList classList = aCodeGeneratorConfiguration.getClassList();
        if (classList.hasClass(aClass.getName()))
            return aClass.getPackage().getName() + ".persistence.dataobject." + aClass.getSimpleName();
        else
            return aClass.getName();
    }

    private void generatePackage(final PersistableClass aPersistableClass) {

        // aPersistableClass.append("package " +
        // aPersistableClass.getModelClass().getName()+
        // ".persistence.dataobject;\n");
        aPersistableClass.append("package " + aPersistableClass.getModelClass().getPackage().getName()
                + ".persistence.dataobject;\n");

    }

    private void generateImports(final PersistableClass aPersistableClass) {

        String imports = "\n";
        aPersistableClass.append(imports);

    }

    private String generateAttributes(final PersistableClass aPersistableClass,
            final CodeGeneratorConfiguration aCodeGeneratorConfiguration) {

        Class<?> modelClass = aPersistableClass.getModelClass();

        String generatedAttributes = "";
        int numberOfAttributes = BoosterReflectionUtils.numberOfAttributes(modelClass);
        for (int i = 0; i < numberOfAttributes; i++) {
            generatedAttributes = generatedAttributes + "	"
                    + BoosterReflectionUtils.getFieldModifierName(modelClass, i) + " "
                    + getTypeName(modelClass, i, aCodeGeneratorConfiguration) + " "
                    + BoosterReflectionUtils.attributeAtPosition(modelClass, i) + ";\n";

        }

        if (modelClass.getSuperclass().equals(Object.class)) {
            ClassIdMap classIdMap = aCodeGeneratorConfiguration.getClassIdMap();
            generatedAttributes = generatedAttributes + " 	 public long " + classIdMap.getId(modelClass.getName())
                    + ";\n";
        }

        return generatedAttributes;
    }

    private String generateDefaultConstructor(final Class<?> aClass) {
        return "	public void " + aClass.getSimpleName() + "(){\n" + "	}\n";
    }

    private String generateSettersGetters(final Class<?> modelClass,
            final CodeGeneratorConfiguration aCodeGeneratorConfiguration) {

        String settersGetters = "";

        settersGetters = "	public void setOrmId(long ormId){\n" + "		this.ormId=ormId;\n" + "	}\n" + "	\n"
                + "	public long getOrmId(){\n" + "		return ormId;\n" + "	}\n" + "	\n";
        int numberOfAttributes = BoosterReflectionUtils.numberOfAttributes(modelClass);

        for (int i = 0; i < numberOfAttributes; i++) {

            String fieldName = BoosterReflectionUtils.attributeAtPosition(modelClass, i);
            String setter;
            String getter;

            setter = "	public void set" + firstCharToUpperCase(fieldName) + "("
                    + getTypeName(modelClass, i, aCodeGeneratorConfiguration) + " " + fieldName + "){\n" + "		this."
                    + fieldName + " = " + fieldName + ";\n" + "	}\n";

            getter = "	public " + getTypeName(modelClass, i, aCodeGeneratorConfiguration) + " get"
                    + firstCharToUpperCase(fieldName) + "(){\n" + "		return " + fieldName + ";\n" + "	}\n";

            settersGetters = settersGetters + setter + "\n" + getter + "\n";
        }
        return settersGetters;
    }

    private String getTypeName(final Class<?> modelClass, final int numberOfAttribute,
            final CodeGeneratorConfiguration aCodeGeneratorConfiguration) {

        if (BoosterReflectionUtils.getField(modelClass, numberOfAttribute).getGenericType().toString().contains("<")) {
            Type[] actualTypeArguments = ((ParameterizedType) BoosterReflectionUtils.getField(modelClass,
                    numberOfAttribute).getGenericType()).getActualTypeArguments();
            String actualArguments = "";
            for (int i = 0; i < actualTypeArguments.length; i++) {

                actualArguments = actualArguments
                        + getPersistableModelName((Class<?>) actualTypeArguments[i], aCodeGeneratorConfiguration);

                if (i + 1 < actualTypeArguments.length) {
                    actualArguments = actualArguments + ",";
                }
            }
            return getPersistableModelName(BoosterReflectionUtils.getField(modelClass, numberOfAttribute).getType(),
                    aCodeGeneratorConfiguration)
                    + "<" + actualArguments + ">";
        } else if (BoosterReflectionUtils.getField(modelClass, numberOfAttribute).getType().isArray())
            return getPersistableModelName(BoosterReflectionUtils.getField(modelClass, numberOfAttribute).getType()
                    .getComponentType(), aCodeGeneratorConfiguration)
                    + "[]";
        else
            return getPersistableModelName(BoosterReflectionUtils.getField(modelClass, numberOfAttribute).getType(),
                    aCodeGeneratorConfiguration);

    }

    private String firstCharToUpperCase(final String aString) {
        return aString.substring(0, 1).toUpperCase() + aString.substring(1, aString.length());

    }

}
