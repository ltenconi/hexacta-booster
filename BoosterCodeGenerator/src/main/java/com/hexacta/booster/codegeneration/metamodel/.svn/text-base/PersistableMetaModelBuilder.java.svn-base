package com.hexacta.booster.codegeneration.metamodel;

import java.util.Iterator;
import java.util.Set;

import com.hexacta.booster.codegeneration.configuration.AttributesSubset;
import com.hexacta.booster.codegeneration.configuration.ClassList;
import com.hexacta.booster.codegeneration.configuration.PersistableMetaModelAttributes;
import com.hexacta.booster.exception.ModelClassNotFound;
import com.hexacta.booster.utilities.DeepCopy;

/**
 * 
 */
public final class PersistableMetaModelBuilder {

    /**
     * 
     */
    private PersistableMetaModelBuilder() {
    }

    /**
     * retorna el metamodelo de la clase solamente con los atributos incluidos
     * en el subconjunto de atributos.
     */

    private static void cutNotPersistableAttributes(final Class aMetaModelClass,
            final PersistableMetaModelAttributes persistableMetaModelAttributes) {

        if (persistableMetaModelAttributes.contains(aMetaModelClass.getSimpleName())) {
            AttributesSubset attributesSubset = persistableMetaModelAttributes.getAttributesSubset(aMetaModelClass
                    .getSimpleName());
            Field[] allFields = aMetaModelClass.getDeclaredFields();
            Field[] persistableFields = new Field[attributesSubset.size()];

            int j = 0;
            for (Field field : allFields) {
                if (attributesSubset.contains(field.getName())) {
                    persistableFields[j] = field;
                    j++;
                }
            }
            aMetaModelClass.setDeclaredFields(persistableFields);
        }
    }

    /**
     * Retorna el MetaModelo persistible del metamodelo pasado como parametro.
     * 
     * @throws ModelClassNotFound
     * 
     * @throws ModelClassNotFound
     * @throws NotAnEntityClassException
     * */

    public static ClassList build(final ClassList metaModel,
            final PersistableMetaModelAttributes persistableMetaModelAttributes) throws NotExistAttributeException,
            ModelClassNotFound, NotAnEntityClassException {

        ClassList persistableClasses = (ClassList) DeepCopy.copy(metaModel);

        checkPersistableMetaModelAttributes(persistableMetaModelAttributes, metaModel);

        for (Iterator<Class> iterator = persistableClasses.iterator(); iterator.hasNext();) {
            Class persistableClass = iterator.next();
            cutNotPersistableAttributes(persistableClass, persistableMetaModelAttributes);
        }
        return persistableClasses;
    }

    /**
     * @param persistableMetaModelAttributes
     * @param metaModel
     * @throws ModelClassNotFound
     * @throws NotExistAttributeException
     * @throws NotAnEntityClassException
     */
    private static void checkPersistableMetaModelAttributes(
            final PersistableMetaModelAttributes persistableMetaModelAttributes, final ClassList metaModel)
            throws ModelClassNotFound, NotExistAttributeException, NotAnEntityClassException {

        Set<String> persistableClassNames = persistableMetaModelAttributes.getClasses();

        for (String className : persistableClassNames) {
            Class metaModelClass = metaModel.getClassBySimpleName(className);
            if (metaModelClass != null) {

                Set<String> attributes = persistableMetaModelAttributes.getAttributesSubset(className).getSubSet();

                Field[] allFields = metaModelClass.getDeclaredFields();
                for (String attribute : attributes) {
                    if (!contains(allFields, attribute)) {
                        throw new NotExistAttributeException(metaModelClass.getName(), attribute);
                    }
                }
            }
        }

    }

    // /**
    // * @param attributesSubset
    // * @param allFields
    // * @throws NotExistAttributeException
    // */
    // private static void checkAttributesSubSet(final AttributesSubset
    // attributesSubset, final Class persistableClass)
    // throws NotExistAttributeException {
    //
    // Field[] allFields = persistableClass.getDeclaredFields();
    // Set<String> attributes = attributesSubset.getSubSet();
    // for (String attribute : attributes) {
    // if (!contains(allFields, attribute)) {
    // throw new NotExistAttributeException(persistableClass.getName(),
    // attribute);
    // }
    // }
    // }

    /**
     * @param allFields
     * @param attribute
     * @return
     */
    private static boolean contains(final Field[] allFields, final String attribute) {

        boolean contains = false;
        int i = 0;
        while (allFields.length > i && !contains) {
            if (allFields[i].getName().equalsIgnoreCase(attribute)) {
                contains = true;
            }
            i++;
        }
        return contains;
    }
}
