package com.hexacta.booster.codegeneration.metamodel;

import java.lang.reflect.Modifier;
import java.util.List;

import org.apache.log4j.Logger;

import com.hexacta.booster.codegeneration.configuration.ClassList;

/**
 * 
 */
public class MetaModelGeneratorForJavaModel {

    static final Logger logger = Logger.getLogger(MetaModelGeneratorForJavaModel.class);

    public ClassList generate(final List<java.lang.Class<?>> aClassList) {

        ClassList aMetaClassList = new ClassList();
        ClassList classList = new ClassList();

        for (java.lang.Class<?> aClass : aClassList) {
            if (classList.hasClass(aClass.getName())) {
                aMetaClassList.addClass(classList.getClass(aClass.getName()));
            } else {
                aMetaClassList.addClass(generate(aClass, classList));
            }
        }
        return aMetaClassList;
    }

    public Class generate(final java.lang.Class<?> aClass, final ClassList classList) {

        if (!classList.hasClass(aClass.getName())) {

            Class metaModelClass = new Class(aClass.getName());
            classList.addClass(metaModelClass);

            metaModelClass.setSuperClass(getSuperClass(aClass, classList));
            metaModelClass.setSimpleName(aClass.getSimpleName());
            metaModelClass.setArray(aClass.isArray());
            metaModelClass.setPrimitive(aClass.isPrimitive());
            metaModelClass.setPackageName(getClassPackageName(aClass));
            metaModelClass.setDeclaredFields(generateDeclaredFiedls(aClass, classList));
            metaModelClass.setConstructors(generateConstructors(aClass, classList));
            metaModelClass.setAbstract(Modifier.isAbstract(aClass.getModifiers()));
            metaModelClass.setMethods(generateDeclaredMethods(aClass));
            metaModelClass.setFields(generateFields(aClass, classList));

            return metaModelClass;

        } else
            return classList.getClass(aClass.getName());
    }

    /**
     * @param class1
     * @return
     */
    private Field[] generateFields(final java.lang.Class<?> aClass, final ClassList aClassList) {
        java.lang.reflect.Field[] fields = aClass.getFields();
        Field[] metaModelField = new Field[fields.length];

        for (int i = 0; i < fields.length; i++) {
            if (isParameterizedType(fields[i].getGenericType().toString())) {
                metaModelField[i] = new Field(fields[i].getName(), generateParameterizedType(fields[i].getType(),
                        fields[i].getGenericType(), aClassList), generate(fields[i].getDeclaringClass(), aClassList));
            } else {
                if (!aClassList.hasClass(fields[i].getDeclaringClass().getName())) {
                    metaModelField[i] = new Field(fields[i].getName(), generateType(fields[i].getType(), aClassList),
                            generate(fields[i].getDeclaringClass(), aClassList));
                } else {
                    metaModelField[i] = new Field(fields[i].getName(), generateType(fields[i].getType(), aClassList),
                            aClassList.getClass(fields[i].getDeclaringClass().getName()));
                }
            }
        }
        return metaModelField;
    }

    private Method[] generateDeclaredMethods(final java.lang.Class<?> aClass) {

        try {
            Method[] methods = new Method[aClass.getDeclaredMethods().length];
            for (int i = 0; i < methods.length; i++) {
                methods[i] = new Method(aClass.getDeclaredMethods()[i].getName());
            }
            return methods;
        } catch (Throwable e) {
            return new Method[0];
        }

    }

    private String getClassPackageName(final java.lang.Class<?> aClass) {
        if (aClass.getPackage() != null)
            return aClass.getPackage().getName();
        else
            return null;
    }

    private Class getSuperClass(final java.lang.Class<?> aClass, final ClassList classList) {
        if (aClass.getName().equalsIgnoreCase("java.lang.Object"))
            return createClassForObjectClass(aClass, classList);
        else
            return findOrCreateClassForSuperclass(aClass, classList);
    }

    private Class findOrCreateClassForSuperclass(final java.lang.Class<?> aClass, final ClassList classList) {
        Class superClass;
        if (aClass.getSuperclass() != null) {
            if (classList.hasClass(aClass.getSuperclass().getName())) {
                superClass = classList.getClass(aClass.getSuperclass().getName());
            } else {
                superClass = generate(aClass.getSuperclass(), classList);
            }
        } else {
            superClass = null;
        }

        return superClass;
    }

    private Class createClassForObjectClass(final java.lang.Class<?> aClass, final ClassList classList) {
        Class superClass;
        superClass = new Class(java.lang.Object.class.getName(), java.lang.Object.class.getSimpleName(), null, null,
                false, false, java.lang.Object.class.getPackage().getName(), generateConstructors(aClass, classList),
                false, new Method[0]);
        return superClass;
    }

    private Constructor[] generateConstructors(final java.lang.Class<?> aClass, final ClassList classList) {
        java.lang.reflect.Constructor<?>[] constructors = aClass.getConstructors();
        Constructor[] metaModelClassConstructors = new Constructor[constructors.length];

        for (int i = 0; i < constructors.length; i++) {
            java.lang.Class<?>[] parameterTypes = constructors[i].getParameterTypes();
            Type[] metaModelParameterTypes = new Type[parameterTypes.length];

            for (int j = 0; j < parameterTypes.length; j++) {
                metaModelParameterTypes[j] = generateType(parameterTypes[j], classList);
            }

            metaModelClassConstructors[i] = new Constructor(constructors[i].getName(), metaModelParameterTypes);
        }

        return metaModelClassConstructors;
    }

    private Field[] generateDeclaredFiedls(final java.lang.Class<?> aClass, final ClassList aClassList) {
        java.lang.reflect.Field[] fields = aClass.getDeclaredFields();
        Field[] declaredFields = new Field[fields.length];

        for (int i = 0; i < fields.length; i++) {
            if (isParameterizedType(fields[i].getGenericType().toString())) {
                declaredFields[i] = new Field(fields[i].getName(), generateParameterizedType(fields[i].getType(),
                        fields[i].getGenericType(), aClassList), generate(fields[i].getDeclaringClass(), aClassList));
            } else {
                if (!aClassList.hasClass(fields[i].getDeclaringClass().getName())) {
                    declaredFields[i] = new Field(fields[i].getName(), generateType(fields[i].getType(), aClassList),
                            generate(fields[i].getDeclaringClass(), aClassList));
                } else {
                    declaredFields[i] = new Field(fields[i].getName(), generateType(fields[i].getType(), aClassList),
                            aClassList.getClass(fields[i].getDeclaringClass().getName()));
                }
            }
        }
        return declaredFields;
    }

    private ParameterizedType generateParameterizedType(final java.lang.Class<?> aFieldType,
            final java.lang.reflect.Type aGenericType, final ClassList classList) {
        java.lang.reflect.Type[] actualArgumentTypes = ((java.lang.reflect.ParameterizedType) aGenericType)
                .getActualTypeArguments();
        String[] metaActualArgumentTypes = generateActualTypeArguments(actualArgumentTypes);

        Class componentType = null;

        if (aFieldType.isArray()) {
            componentType = generate(aFieldType.getComponentType(), classList);
        }

        return new ParameterizedType(aFieldType.getName(), aFieldType.getSimpleName(), aFieldType.isArray(), aFieldType
                .isPrimitive(), componentType, Modifier.isAbstract(aFieldType.getModifiers()), metaActualArgumentTypes);
    }

    private String[] generateActualTypeArguments(final java.lang.reflect.Type[] actualArgumentTypes) {
        String[] metaActualArgumentTypes = new String[actualArgumentTypes.length];

        for (int j = 0; j < actualArgumentTypes.length; j++) {
            metaActualArgumentTypes[j] = actualArgumentTypes[j].toString().substring(6);
        }
        return metaActualArgumentTypes;
    }

    private Type generateType(final java.lang.Class<?> aClass, final ClassList classList) {
        Class componentType = null;
        if (aClass.isArray()) {
            componentType = generate(aClass.getComponentType(), classList);
        }

        return new Type(aClass.getName(), aClass.getSimpleName(), aClass.isArray(), aClass.isPrimitive(),
                componentType, Modifier.isAbstract(aClass.getModifiers()));
    }

    private boolean isParameterizedType(final String aType) {
        return aType.contains("<");
    }
}
