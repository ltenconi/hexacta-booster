package com.hexacta.booster.codegeneration.persistence.ormmodel;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.hexacta.booster.codegeneration.configuration.ClassFinder;
import com.hexacta.booster.codegeneration.configuration.ClassIdMap;
import com.hexacta.booster.codegeneration.configuration.ClassList;
import com.hexacta.booster.codegeneration.configuration.CodeGeneratorConfiguration;
import com.hexacta.booster.codegeneration.configuration.CollectionVarTypeMap;
import com.hexacta.booster.codegeneration.configuration.NotSupportedClassException;
import com.hexacta.booster.codegeneration.metamodel.Class;
import com.hexacta.booster.codegeneration.metamodel.Field;
import com.hexacta.booster.codegeneration.metamodel.ParameterizedType;
import com.hexacta.booster.project.configuration.ProjectType;

/**
 * 
 */
public class OrmDataGenerator {

    /**
     * Log4j logger.
     */
    static final Logger logger = Logger.getLogger(OrmDataGenerator.class);

    public OrmDataGenerator() {
    }

    public OrmData generateOrmData(final CodeGeneratorConfiguration codeGeneratorConfiguration,
            final ProjectType projectType) throws NotSupportedClassException {

        OrmDataGeneratorContext ormDataGeneratorContext = new OrmDataGeneratorContext(codeGeneratorConfiguration,
                projectType);

        ClassList classList = ormDataGeneratorContext.getClassList();
        List<OrmMapping> ormMappings = ormDataGeneratorContext.getOrmMappings();
        Map<String, OrmMapping> ormMappingsMap = ormDataGeneratorContext.getOrmMappingsMap();

        for (Iterator<Class> iterator = classList.iterator(); iterator.hasNext();) {
            Class className = iterator.next();
            OrmMapping ormMapping = generateOrmMapping(className, ormDataGeneratorContext);
            ormMappings.add(ormMapping);
            ormMappingsMap.put(className.getName(), ormMapping);
        }

        ormDataGeneratorContext.setOrmMappingsHierarchy(appendSubClassesMappings(ormDataGeneratorContext));

        return new OrmData(ormMappings, ormDataGeneratorContext.getOrmMappingsHierarchy());

    }

    private List<OrmMapping> appendSubClassesMappings(final OrmDataGeneratorContext ormDataGeneratorContext)
            throws NotSupportedClassException {

        List<OrmMapping> rootOrmMappings = new ArrayList<OrmMapping>();
        List<OrmMapping> ormMappings = ormDataGeneratorContext.getOrmMappings();
        ClassList classList = ormDataGeneratorContext.getClassList();
        Map<String, OrmMapping> ormMappingsMap = ormDataGeneratorContext.getOrmMappingsMap();

        for (OrmMapping ormMapping : ormMappings) {
            Class modelClass = ClassFinder.find(ormMapping.getMappingClass().getName(), ormDataGeneratorContext
                    .getClassList(), ormDataGeneratorContext.getProjectType());
            if (classList.hasClass(modelClass.getSuperclass().getName())) {// has
                // superclass
                OrmMapping superOrmMapping = ormMappingsMap.get(modelClass.getSuperclass().getName());
                ormMapping.setSubMapping(true);
                superOrmMapping.addSubClassOrmMapping(ormMapping);
            } else {
                rootOrmMappings.add(ormMapping);
            }

        }
        return rootOrmMappings;

    }

    private OrmMapping generateOrmMapping(final Class className, final OrmDataGeneratorContext ormDataGeneratorContext)
            throws NotSupportedClassException {

        ClassIdMap classIdMap = ormDataGeneratorContext.getClassIdMap();

        OrmMapping ormMapping = new OrmMapping(className, classIdMap.getId(className.getName()));

        Class modelClass = ClassFinder.find(className.getName(), ormDataGeneratorContext.getClassList(),
                ormDataGeneratorContext.getProjectType());
        Field[] classFieldList = modelClass.getDeclaredFields();

        for (int i = 0; i < classFieldList.length; i++) {
            if (isProperty(classFieldList[i], ormDataGeneratorContext)) {// is
                // property
                if (!classIdMap.getId(className.getName()).equalsIgnoreCase(classFieldList[i].getName())) {// is
                    // not
                    // id
                    OrmProperty ormProperty = new OrmProperty(classFieldList[i].getName(), getFieldType(
                            classFieldList[i], ormDataGeneratorContext));
                    ormMapping.addOrmProperty(ormProperty);
                }
            } else {// is association
                OrmRelation ormRelation = getRelation(className.getName(), classFieldList, i, ormDataGeneratorContext);
                ormMapping.addOrmRelation(ormRelation);
            }
        }
        return ormMapping;
    }

    private VarType getFieldType(final Field field, final OrmDataGeneratorContext ormDataGeneratorContext)
            throws NotSupportedClassException {

        ClassList classList = ormDataGeneratorContext.getClassList();

        if (field.getType().isPrimitive())
            return new BasicType(ClassFinder.find(field.getType().getName(), ormDataGeneratorContext.getClassList(),
                    ormDataGeneratorContext.getProjectType()));
        else if (classList.hasClass(field.getType().getName()))
            return new EntityReference(ClassFinder.find(field.getType().getName(), ormDataGeneratorContext
                    .getClassList(), ormDataGeneratorContext.getProjectType()));
        else if (isCollection(field, ormDataGeneratorContext))
            return getCollectionType(field, ormDataGeneratorContext);
        else
            return new BasicType(ClassFinder.find(field.getType().getName(), ormDataGeneratorContext.getClassList(),
                    ormDataGeneratorContext.getProjectType()));

    }

    private CollectionType getCollectionType(final Field field, final OrmDataGeneratorContext ormDataGeneratorContext)
            throws NotSupportedClassException {

        CollectionVarTypeMap collectionVarTypeMap = ormDataGeneratorContext.getCollectionVarTypeMap();

        if (collectionVarTypeMap.hasCollectionVarType(field.getDeclaringClass().getName(), field.getName()))
            return collectionVarTypeMap.getCollectionVarType(field.getDeclaringClass().getName(), field.getName());
        else if (field.getType().isArray())
            return new ArrayType(getElementType(field.getType().getComponentType().getName(), ormDataGeneratorContext),
                    ormDataGeneratorContext.getProjectType());
        else {
            if (field.getType().isParameterized()) {
                ParameterizedType aParameterizedType = (ParameterizedType) field.getType();
                String elementTypeName = aParameterizedType.getActualTypeArguments()[aParameterizedType
                        .getActualTypeArguments().length - 1].toString();
                ElementType elementType = getElementType(elementTypeName, ormDataGeneratorContext);
                if (ormDataGeneratorContext.getProjectType().isJava()) {
                    if (field.getType().getSimpleName().equalsIgnoreCase("Set"))
                        return new SetType(elementType);
                    else if (field.getType().getSimpleName().equalsIgnoreCase("Map")) {
                        ElementType keyType = getElementType(aParameterizedType.getActualTypeArguments()[0].toString(),
                                ormDataGeneratorContext);
                        return new MapType(keyType, elementType);
                    }
                    return new ListType(elementType);
                } else {// is Dot Net Parameterized Collection
                    if (field.getType().getSimpleName().equalsIgnoreCase("ISet")
                            || field.getType().getSimpleName().equalsIgnoreCase("IEsi")
                            || field.getType().getSimpleName().equalsIgnoreCase("ICollection"))
                        return new SetType(elementType);
                    else if (field.getType().getSimpleName().equalsIgnoreCase("IDictionary")
                            || field.getType().getSimpleName().equalsIgnoreCase("HashTable")) {
                        ElementType keyType = getElementType(aParameterizedType.getActualTypeArguments()[0].toString(),
                                ormDataGeneratorContext);
                        return new MapType(keyType, elementType);
                    }
                    return new ListType(elementType);
                }
            } else {
                if (ormDataGeneratorContext.getProjectType().isJava()) {
                    ElementType elementType = getElementType("java.lang.Object", ormDataGeneratorContext);
                    if (field.getType().getSimpleName().equalsIgnoreCase("Set"))
                        return new SetType(elementType);
                    else if (field.getType().getSimpleName().equalsIgnoreCase("Map")) {
                        ElementType keyType = getElementType("java.lang.Object", ormDataGeneratorContext);
                        return new MapType(keyType, elementType);
                    }
                    return new ListType(elementType);
                } else {
                    ElementType elementType = getElementType("System.Object", ormDataGeneratorContext);
                    if (field.getType().getSimpleName().equalsIgnoreCase("ISet")
                            || field.getType().getSimpleName().equalsIgnoreCase("IEsi")
                            || field.getType().getSimpleName().equalsIgnoreCase("ICollection"))
                        return new SetType(elementType);
                    else if (field.getType().getSimpleName().equalsIgnoreCase("IDictionary")
                            || field.getType().getSimpleName().equalsIgnoreCase("HashTable")) {
                        ElementType keyType = getElementType("System.Object", ormDataGeneratorContext);
                        return new MapType(keyType, elementType);
                    }
                    return new ListType(elementType);
                }
            }
        }
    }

    private boolean isProperty(final Field field, final OrmDataGeneratorContext ormDataGeneratorContext)
            throws NotSupportedClassException {
        VarType varType = getFieldType(field, ormDataGeneratorContext);
        return !varType.isAssociation();
    }

    private boolean isCollection(final Field field, final OrmDataGeneratorContext ormDataGeneratorContext) {
        String fieldTypeName = field.getType().getSimpleName();
        if (ormDataGeneratorContext.getProjectType().isJava())
            return fieldTypeName.equalsIgnoreCase("Set") || fieldTypeName.equalsIgnoreCase("Map")
                    || fieldTypeName.equalsIgnoreCase("List") || field.getType().isArray();
        else
            return fieldTypeName.equalsIgnoreCase("ICollection") || fieldTypeName.equalsIgnoreCase("IDictionary")
                    || fieldTypeName.equalsIgnoreCase("ISet") || fieldTypeName.equalsIgnoreCase("IEsi")
                    || fieldTypeName.equalsIgnoreCase("HashTable") || fieldTypeName.equalsIgnoreCase("IList")
                    || fieldTypeName.equalsIgnoreCase("List") || fieldTypeName.equalsIgnoreCase("LinkedList")
                    || fieldTypeName.equalsIgnoreCase("SortedList") || field.getType().isArray();
    }

    private Cardinality getCardinality(final Field field, final OrmDataGeneratorContext ormDataGeneratorContext) {
        if (isCollection(field, ormDataGeneratorContext))
            return new ManyToMany();
        else
            return new ManyToOne();
    }

    private Cardinality getCardinality(final Field fieldClassA, final Field fieldClassB,
            final OrmDataGeneratorContext ormDataGeneratorContext) {
        if (isCollection(fieldClassA, ormDataGeneratorContext) && isCollection(fieldClassB, ormDataGeneratorContext))
            return new ManyToMany();
        else if (isCollection(fieldClassB, ormDataGeneratorContext))
            return new ManyToOne();
        else if (isCollection(fieldClassA, ormDataGeneratorContext))
            return new OneToMany();
        else
            return new OneToOne();
    }

    private OrmRelation getRelation(final String className, final Field[] classFieldList, final int i,
            final OrmDataGeneratorContext ormDataGeneratorContext) throws NotSupportedClassException {
        AssociationType associationType;
        Cardinality cardinality;
        OrmRelation ormRelation;

        Field fieldClassA = classFieldList[i];
        VarType typeFieldA = getFieldType(fieldClassA, ormDataGeneratorContext);
        Map<String, AssociationType> biDirectionalAssociations = ormDataGeneratorContext.getBiDirectionalAssociations();

        if (isBiDirectionalAssociation(className, classFieldList[i], ormDataGeneratorContext)) {
            Field fieldClassB = getFieldClassB(className, classFieldList[i], ormDataGeneratorContext);
            VarType typeFieldB = getFieldType(fieldClassB, ormDataGeneratorContext);

            String key = fieldClassB.getName() + fieldClassA.getName();
            if (biDirectionalAssociations.containsKey(key)) {
                associationType = biDirectionalAssociations.get(key);
                cardinality = getCardinality(fieldClassB, fieldClassA, ormDataGeneratorContext);
            } else {
                Class classA = ClassFinder.find(fieldClassA.getDeclaringClass().getName(), ormDataGeneratorContext
                        .getClassList(), ormDataGeneratorContext.getProjectType());
                Class classB = ClassFinder.find(fieldClassB.getDeclaringClass().getName(), ormDataGeneratorContext
                        .getClassList(), ormDataGeneratorContext.getProjectType());
                associationType = new BiDirectional(fieldClassA.getName(), fieldClassB.getName(), typeFieldA,
                        typeFieldB, classA, classB);
                biDirectionalAssociations.put(fieldClassA.getName() + fieldClassB.getName(), associationType);
                cardinality = getCardinality(fieldClassA, fieldClassB, ormDataGeneratorContext);
            }

            ormRelation = new OrmRelation(associationType, cardinality);

        } else {// is unidirectional association
            String nameFieldA = classFieldList[i].getName();

            Class classA = ClassFinder.find(classFieldList[i].getDeclaringClass().getName(), ormDataGeneratorContext
                    .getClassList(), ormDataGeneratorContext.getProjectType());
            Class classB = getAssociatedClass(fieldClassA, ormDataGeneratorContext);
            associationType = new UniDirectional(nameFieldA, typeFieldA, classA, classB);
            cardinality = getCardinality(fieldClassA, ormDataGeneratorContext);
            ormRelation = new OrmRelation(associationType, cardinality);

        }
        return ormRelation;

    }

    private boolean isBiDirectionalAssociation(final String className, final Field classField,
            final OrmDataGeneratorContext ormDataGeneratorContext) throws NotSupportedClassException {

        if (classField.getType().isPrimitive())
            return false;

        String nameClassB = getAssociatedClass(classField, ormDataGeneratorContext).getName();
        Class modelClassB;
        boolean bidirectional = false;

        modelClassB = ClassFinder.find(nameClassB, ormDataGeneratorContext.getClassList(), ormDataGeneratorContext
                .getProjectType());
        Field[] fieldsClassB = modelClassB.getDeclaredFields();

        int i = 0;
        while (!bidirectional && i < fieldsClassB.length) {
            if (className.equalsIgnoreCase(getAssociatedClass(fieldsClassB[i], ormDataGeneratorContext).getName())) {
                bidirectional = true;
            }
            i++;
        }

        return bidirectional;
    }

    private Field getFieldClassB(final String className, final Field classField,
            final OrmDataGeneratorContext ormDataGeneratorContext) throws NotSupportedClassException {
        Field fieldClassB = null;
        String nameClassB = getAssociatedClass(classField, ormDataGeneratorContext).getName();
        Class modelClassB;
        boolean bidirectional = false;

        modelClassB = ClassFinder.find(nameClassB, ormDataGeneratorContext.getClassList(), ormDataGeneratorContext
                .getProjectType());
        Field[] fieldsClassB = modelClassB.getDeclaredFields();

        int i = 0;
        while (!bidirectional && i < fieldsClassB.length) {
            if (className.equalsIgnoreCase(getAssociatedClass(fieldsClassB[i], ormDataGeneratorContext).getName())) {
                bidirectional = true;
                fieldClassB = fieldsClassB[i];
            }
            i++;
        }

        return fieldClassB;
    }

    private Class getAssociatedClass(final Field fieldClassA, final OrmDataGeneratorContext ormDataGeneratorContext)
            throws NotSupportedClassException {
        VarType varType = getFieldType(fieldClassA, ormDataGeneratorContext);
        return varType.getAssociatedClass();
    }

    private ElementType getElementType(final String elementTypeName,
            final OrmDataGeneratorContext ormDataGeneratorContext) throws NotSupportedClassException {
        if (isEntityReference(elementTypeName, ormDataGeneratorContext))
            return new EntityReference(ClassFinder.find(elementTypeName, ormDataGeneratorContext.getClassList(),
                    ormDataGeneratorContext.getProjectType()));
        else
            return new BasicType(ClassFinder.find(elementTypeName, ormDataGeneratorContext.getClassList(),
                    ormDataGeneratorContext.getProjectType()));
    }

    private boolean isEntityReference(final String aTypeName, final OrmDataGeneratorContext ormDataGeneratorContext) {
        ClassList classList = ormDataGeneratorContext.getClassList();
        return classList.hasClass(aTypeName);
    }

}
