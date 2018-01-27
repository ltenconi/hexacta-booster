package com.hexacta.booster.utilities;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import com.hexacta.booster.codegeneration.configuration.ClassList;
import com.hexacta.booster.codegeneration.metamodel.Class;
import com.hexacta.booster.codegeneration.metamodel.Field;

/**
 * 
 */
public final class MetaModelUtils {

    private MetaModelUtils() {
    }

    // Original method
    // public static Field[] getAllInheritedFields(final Class aClasss) {
    // Class aClass = aClasss;
    // List<Field> accumulated = new LinkedList<Field>();
    //
    // while (aClass != null &&
    // !aClass.getName().equalsIgnoreCase("java.lang.Object")) {
    // Field[] stepFields = aClass.getDeclaredFields();
    // for (Field stepField : stepFields) {
    // accumulated.add(stepField);
    // }
    // aClass = aClass.getSuperclass();
    // }
    //
    // // stop extract AllFields
    // Field[] allFields = accumulated.toArray(new Field[accumulated.size()]);
    // return allFields;
    // }

    public static Field[] getAllInheritedFields(final Class aClass) {

        List<Field> accumulated = getOrderedFields(aClass);

        Field[] allFields = accumulated.toArray(new Field[accumulated.size()]);
        return allFields;
    }

    private static List<Field> getOrderedFields(final Class aClass) {

        if (aClass == null || aClass.getName().equalsIgnoreCase("java.lang.Object"))
            return new LinkedList<Field>();
        else {
            List<Field> accumulated = getOrderedFields(aClass.getSuperclass());
            Field[] stepFields = aClass.getDeclaredFields();
            for (Field stepField : stepFields) {
                accumulated.add(stepField);
            }
            return accumulated;
        }
    }

    public static String getGroupId(final Class aClass) {

        String classPackage = aClass.getPackage();
        String groupId = classPackage.substring(0, classPackage.indexOf("model") - 1);
        return groupId;

    }

    public static boolean hasSuperClass(final Class aClass) {

        Class superClass = aClass.getSuperclass();

        if (superClass == null)
            return false;
        if (!superClass.getName().equalsIgnoreCase("java.lang.Object"))
            return true;
        return false;

    }

    /**
     * @param entityClass
     * @return
     */
    public static List<Class> getHierarchyBranchClasses(final Class entityClass) {

        List<Class> hierarchyBranch = new ArrayList<Class>();
        Class modelClass = entityClass;
        hierarchyBranch.add(modelClass);

        while (MetaModelUtils.hasSuperClass(modelClass)) {

            hierarchyBranch.add(modelClass.getSuperclass());
            modelClass = modelClass.getSuperclass();
        }

        return hierarchyBranch;
    }

    public static Field[] getAllInheritedPersistableFields(Class entityClass, final ClassList persistableMetaModel) {

        List<Field> accumulated = new LinkedList<Field>();

        while (entityClass != null && !entityClass.getName().equalsIgnoreCase("java.lang.Object")) {
            Class persistableClass = persistableMetaModel.getClass(entityClass.getName());
            Field[] stepFields = persistableClass.getDeclaredFields();
            for (Field stepField : stepFields) {
                accumulated.add(stepField);
            }
            entityClass = entityClass.getSuperclass();
        }

        // stop extract AllFields
        Field[] allFields = accumulated.toArray(new Field[accumulated.size()]);
        return allFields;
    }

}
