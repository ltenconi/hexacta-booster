package com.hexacta.booster.codegeneration.persistence.ormmodel;

import java.util.Iterator;
import java.util.List;

import junit.framework.TestCase;
import test.hexacta.booster.providers.CodeGeneratorConfigurationProvider;
import test.hexacta.booster.providers.ProjectConfigurationToolProvider;
import ar.com.hexacta.utils.reflection.ReflectionUtils;

import com.hexacta.booster.codegeneration.configuration.ClassFinder;
import com.hexacta.booster.codegeneration.configuration.ClassList;
import com.hexacta.booster.codegeneration.configuration.CodeGeneratorConfiguration;
import com.hexacta.booster.codegeneration.metamodel.Class;
import com.hexacta.booster.codegeneration.metamodel.Field;
import com.hexacta.booster.project.configuration.JavaProjectType;
import com.hexacta.booster.project.configuration.ProjectConfigurationTool;
import com.hexacta.booster.project.configuration.ProjectType;

/**
 * 
 */
public class OrmDataGeneratorTest extends TestCase {

    public void testGenerateOrmDataAddress() throws Exception {

        CodeGeneratorConfiguration codeGeneratorConfiguration = new CodeGeneratorConfigurationProvider()
                .getCodeGeneratorConfiguration();
        ProjectConfigurationTool projectConfigurationTool = new ProjectConfigurationToolProvider()
                .getProjectConfigurationTool();
        OrmDataGenerator ormDataGenerator = new OrmDataGenerator();

        OrmData ormData = (OrmData) ReflectionUtils.executePrivateMethod(ormDataGenerator, "generateOrmData",
                new java.lang.Class[] { CodeGeneratorConfiguration.class, ProjectType.class }, new Object[] {
                        codeGeneratorConfiguration, new JavaProjectType() });

        OrmMapping ormMapping = ormData.getMapping(ClassFinder.find("test.model.Address", codeGeneratorConfiguration
                .getClassList(), projectConfigurationTool.getProjectType()));

        List<OrmProperty> ormProperties = ormMapping.getOrmProperties();
        List<OrmRelation> ormRelations = ormMapping.getOrmRelations();

        assertEquals(2, ormRelations.size());
        assertEquals(2, ormProperties.size());

        Iterator<OrmRelation> iterator2 = ormRelations.iterator();

        OrmRelation relationA = iterator2.next();
        OrmRelation relationB = iterator2.next();

        assertTrue(relationA.getAssociationType().isBiDirectional());

        assertTrue(relationA.getCardinality().isOneToMany());
        assertEquals("Address", relationA.getAssociationType().getClassA().getSimpleName());
        assertEquals("Person", relationA.getAssociationType().getClassB().getSimpleName());
        assertTrue(((BiDirectional) relationA.getAssociationType()).getVarTypeB().isEntityReference());
        assertTrue(((BiDirectional) relationA.getAssociationType()).getVarTypeA().isSetType());

        assertTrue(relationB.getAssociationType().isBiDirectional());
        assertTrue(relationB.getCardinality().isOneToOne());

        assertEquals("House", relationB.getAssociationType().getClassA().getSimpleName());
        assertEquals("Address", relationB.getAssociationType().getClassB().getSimpleName());

        assertTrue(((BiDirectional) relationB.getAssociationType()).getVarTypeB().isEntityReference());
        assertTrue(((BiDirectional) relationB.getAssociationType()).getVarTypeA().isEntityReference());

        Iterator<OrmProperty> iterator = ormProperties.iterator();

        OrmProperty property = iterator.next();

        assertEquals("name", property.getName());
        assertEquals("String", ((BasicType) property.getVarType()).getElementClass().getSimpleName());

        property = iterator.next();

        assertEquals("number", property.getName());
        assertTrue(property.getVarType().isBasicType());
        assertEquals("int", ((BasicType) property.getVarType()).getElementClass().getName());
        assertEquals("int", ((BasicType) property.getVarType()).getElementClass().getSimpleName());
    }

    public void testGenerateOrmDataCompany() throws Exception {

        CodeGeneratorConfiguration codeGeneratorConfiguration = new CodeGeneratorConfigurationProvider()
                .getCodeGeneratorConfiguration();
        ProjectConfigurationTool projectConfigurationTool = new ProjectConfigurationToolProvider()
                .getProjectConfigurationTool();

        OrmDataGenerator ormDataGenerator = new OrmDataGenerator();

        OrmData ormData = (OrmData) ReflectionUtils.executePrivateMethod(ormDataGenerator, "generateOrmData",
                new java.lang.Class[] { CodeGeneratorConfiguration.class, ProjectType.class }, new Object[] {
                        codeGeneratorConfiguration, new JavaProjectType() });

        OrmMapping ormMapping = ormData.getMapping(ClassFinder.find("test.model.Company", codeGeneratorConfiguration
                .getClassList(), projectConfigurationTool.getProjectType()));

        List<OrmProperty> ormProperties = ormMapping.getOrmProperties();
        List<OrmRelation> ormRelations = ormMapping.getOrmRelations();

        assertEquals(3, ormRelations.size());
        assertEquals(1, ormProperties.size());

        Iterator<OrmRelation> iterator2 = ormRelations.iterator();

        OrmRelation relationB = iterator2.next();
        OrmRelation relationA = iterator2.next();
        OrmRelation relationC = iterator2.next();

        assertTrue(relationA.getAssociationType().isUniDirectional());
        assertTrue(relationA.getCardinality().isManyToOne());
        assertEquals("Company", relationA.getAssociationType().getClassA().getSimpleName());
        assertEquals("Address", relationA.getAssociationType().getClassB().getSimpleName());
        assertTrue(((UniDirectional) relationA.getAssociationType()).getVarTypeA().isEntityReference());

        assertTrue(relationC.getAssociationType().isUniDirectional());
        assertTrue(relationC.getCardinality().isManyToOne());
        assertEquals("Company", relationC.getAssociationType().getClassA().getSimpleName());
        assertEquals("Address", relationC.getAssociationType().getClassB().getSimpleName());
        assertTrue(((UniDirectional) relationC.getAssociationType()).getVarTypeA().isEntityReference());

        assertTrue(relationB.getAssociationType().isBiDirectional());
        assertTrue(relationB.getCardinality().isManyToMany());

        assertEquals("Company", relationB.getAssociationType().getClassB().getSimpleName());
        assertEquals("Employee", relationB.getAssociationType().getClassA().getSimpleName());
        assertTrue(((BiDirectional) relationB.getAssociationType()).getVarTypeA().isSetType());
        assertTrue(((BiDirectional) relationB.getAssociationType()).getVarTypeB().isMapType());

        Iterator<OrmProperty> iterator = ormProperties.iterator();

        OrmProperty property = iterator.next();
        assertEquals("name", property.getName());
        assertEquals("String", ((BasicType) property.getVarType()).getElementClass().getSimpleName());
    }

    public void testGenerateOrmDataHouse() throws Exception {

        CodeGeneratorConfiguration codeGeneratorConfiguration = new CodeGeneratorConfigurationProvider()
                .getCodeGeneratorConfiguration();
        ProjectConfigurationTool projectConfigurationTool = new ProjectConfigurationToolProvider()
                .getProjectConfigurationTool();

        OrmDataGenerator ormDataGenerator = new OrmDataGenerator();

        OrmData ormData = (OrmData) ReflectionUtils.executePrivateMethod(ormDataGenerator, "generateOrmData",
                new java.lang.Class[] { CodeGeneratorConfiguration.class, ProjectType.class }, new Object[] {
                        codeGeneratorConfiguration, new JavaProjectType() });

        OrmMapping ormMapping = ormData.getMapping(ClassFinder.find("test.model.House", codeGeneratorConfiguration
                .getClassList(), new JavaProjectType()));

        List<OrmProperty> ormProperties = ormMapping.getOrmProperties();
        List<OrmRelation> ormRelations = ormMapping.getOrmRelations();

        assertEquals(2, ormRelations.size());
        assertEquals(0, ormProperties.size());

        Iterator<OrmRelation> iterator2 = ormRelations.iterator();

        OrmRelation relationB = iterator2.next();
        OrmRelation relationA = iterator2.next();

        assertTrue(relationA.getAssociationType().isUniDirectional());
        assertTrue(relationA.getCardinality().isManyToMany());
        assertEquals("House", relationA.getAssociationType().getClassA().getSimpleName());
        assertEquals("Room", relationA.getAssociationType().getClassB().getSimpleName());
        assertTrue(((UniDirectional) relationA.getAssociationType()).getVarTypeA().isListType());

        assertTrue(relationB.getAssociationType().isBiDirectional());
        assertTrue(relationB.getCardinality().isOneToOne());

        assertEquals("House", relationB.getAssociationType().getClassA().getSimpleName());
        assertEquals("Address", relationB.getAssociationType().getClassB().getSimpleName());

        assertTrue(((BiDirectional) relationB.getAssociationType()).getVarTypeB().isEntityReference());
        assertTrue(((BiDirectional) relationB.getAssociationType()).getVarTypeA().isEntityReference());

    }

    public void testIsProperty() throws Exception {

        CodeGeneratorConfiguration codeGeneratorConfiguration = new CodeGeneratorConfigurationProvider()
                .getCodeGeneratorConfiguration();
        ProjectConfigurationTool projectConfigurationTool = new ProjectConfigurationToolProvider()
                .getProjectConfigurationTool();

        OrmDataGenerator ormDataGenerator = new OrmDataGenerator();
        OrmDataGeneratorContext ormDataGeneratorContext = new OrmDataGeneratorContext(codeGeneratorConfiguration,
                projectConfigurationTool.getProjectType());

        Class modelClass = codeGeneratorConfiguration.getClassList().getClass("test.model.Person");
        Field field = modelClass.getDeclaredField("name");
        Boolean isProperty = (Boolean) ReflectionUtils.executePrivateMethod(ormDataGenerator, "isProperty",
                new java.lang.Class[] { Field.class, OrmDataGeneratorContext.class }, new Object[] { field,
                        ormDataGeneratorContext });

        assertTrue(isProperty);

    }

    public void testIsNotProperty() throws Exception {

        CodeGeneratorConfiguration codeGeneratorConfiguration = new CodeGeneratorConfigurationProvider()
                .getCodeGeneratorConfiguration();
        ProjectConfigurationTool projectConfigurationTool = new ProjectConfigurationToolProvider()
                .getProjectConfigurationTool();

        OrmDataGenerator ormDataGenerator = new OrmDataGenerator();
        OrmDataGeneratorContext ormDataGeneratorContext = new OrmDataGeneratorContext(codeGeneratorConfiguration,
                projectConfigurationTool.getProjectType());

        Class modelClass = codeGeneratorConfiguration.getClassList().getClass("test.model.Address");

        Field field = modelClass.getDeclaredField("people");
        Boolean isProperty = (Boolean) ReflectionUtils.executePrivateMethod(ormDataGenerator, "isProperty",
                new java.lang.Class[] { Field.class, OrmDataGeneratorContext.class }, new Object[] { field,
                        ormDataGeneratorContext });

        assertFalse(isProperty);

    }

    public void testIsSetCollection() throws Exception {

        CodeGeneratorConfiguration codeGeneratorConfiguration = new CodeGeneratorConfigurationProvider()
                .getCodeGeneratorConfiguration();
        ProjectConfigurationTool projectConfigurationTool = new ProjectConfigurationToolProvider()
                .getProjectConfigurationTool();

        OrmDataGenerator ormDataGenerator = new OrmDataGenerator();
        OrmDataGeneratorContext ormDataGeneratorContext = new OrmDataGeneratorContext(codeGeneratorConfiguration,
                projectConfigurationTool.getProjectType());

        /** Field people, of type Set<Person>. Extracted from Address. */

        Class modelClass = codeGeneratorConfiguration.getClassList().getClass("test.model.Address");

        Field field = modelClass.getDeclaredField("people");
        Boolean isCollection = (Boolean) ReflectionUtils.executePrivateMethod(ormDataGenerator, "isCollection",
                new java.lang.Class[] { Field.class, OrmDataGeneratorContext.class }, new Object[] { field,
                        ormDataGeneratorContext });

        assertTrue(isCollection);
    }

    public void testIsStringNotCollection() throws Exception {

        CodeGeneratorConfiguration codeGeneratorConfiguration = new CodeGeneratorConfigurationProvider()
                .getCodeGeneratorConfiguration();
        ProjectConfigurationTool projectConfigurationTool = new ProjectConfigurationToolProvider()
                .getProjectConfigurationTool();

        OrmDataGenerator ormDataGenerator = new OrmDataGenerator();
        OrmDataGeneratorContext ormDataGeneratorContext = new OrmDataGeneratorContext(codeGeneratorConfiguration,
                projectConfigurationTool.getProjectType());

        /** Field name, of type String. Extracted from Person. */

        Class modelClass = codeGeneratorConfiguration.getClassList().getClass("test.model.Person");

        Field field = modelClass.getDeclaredField("name");
        Boolean isCollection = (Boolean) ReflectionUtils.executePrivateMethod(ormDataGenerator, "isCollection",
                new java.lang.Class[] { Field.class, OrmDataGeneratorContext.class }, new Object[] { field,
                        ormDataGeneratorContext });

        assertFalse(isCollection);
    }

    public void testIsMapCollection() throws Exception {

        CodeGeneratorConfiguration codeGeneratorConfiguration = new CodeGeneratorConfigurationProvider()
                .getCodeGeneratorConfiguration();
        ProjectConfigurationTool projectConfigurationTool = new ProjectConfigurationToolProvider()
                .getProjectConfigurationTool();
        OrmDataGenerator ormDataGenerator = new OrmDataGenerator();
        OrmDataGeneratorContext ormDataGeneratorContext = new OrmDataGeneratorContext(codeGeneratorConfiguration,
                projectConfigurationTool.getProjectType());

        /**
         * Field employees, of type Map<String,Employee>. Extracted from
         * Company.
         */

        Class modelClass = codeGeneratorConfiguration.getClassList().getClass("test.model.Company");

        Field field = modelClass.getDeclaredField("employees");
        Boolean isCollection = (Boolean) ReflectionUtils.executePrivateMethod(ormDataGenerator, "isCollection",
                new java.lang.Class[] { Field.class, OrmDataGeneratorContext.class }, new Object[] { field,
                        ormDataGeneratorContext });

        assertTrue(isCollection);
    }

    public void testIsListCollection() throws Exception {

        CodeGeneratorConfiguration codeGeneratorConfiguration = new CodeGeneratorConfigurationProvider()
                .getCodeGeneratorConfiguration();
        ProjectConfigurationTool projectConfigurationTool = new ProjectConfigurationToolProvider()
                .getProjectConfigurationTool();
        OrmDataGenerator ormDataGenerator = new OrmDataGenerator();
        OrmDataGeneratorContext ormDataGeneratorContext = new OrmDataGeneratorContext(codeGeneratorConfiguration,
                projectConfigurationTool.getProjectType());

        /** Field rooms, of type List<Room>. Extracted from House. */

        Class modelClass = codeGeneratorConfiguration.getClassList().getClass("test.model.House");

        Field field = modelClass.getDeclaredField("rooms");
        Boolean isCollection = (Boolean) ReflectionUtils.executePrivateMethod(ormDataGenerator, "isCollection",
                new java.lang.Class[] { Field.class, OrmDataGeneratorContext.class }, new Object[] { field,
                        ormDataGeneratorContext });

        assertTrue(isCollection);
    }

    public void testIsArrayCollection() throws Exception {

        CodeGeneratorConfiguration codeGeneratorConfiguration = new CodeGeneratorConfigurationProvider()
                .getCodeGeneratorConfiguration();
        ProjectConfigurationTool projectConfigurationTool = new ProjectConfigurationToolProvider()
                .getProjectConfigurationTool();
        OrmDataGenerator ormDataGenerator = new OrmDataGenerator();
        OrmDataGeneratorContext ormDataGeneratorContext = new OrmDataGeneratorContext(codeGeneratorConfiguration,
                projectConfigurationTool.getProjectType());

        /** Field courses, of type Course[]. Extracted from Student. */

        Class modelClass = codeGeneratorConfiguration.getClassList().getClass("test.model.Student");

        Field field = modelClass.getDeclaredField("courses");
        Boolean isCollection = (Boolean) ReflectionUtils.executePrivateMethod(ormDataGenerator, "isCollection",
                new java.lang.Class[] { Field.class, OrmDataGeneratorContext.class }, new Object[] { field,
                        ormDataGeneratorContext });

        assertTrue(isCollection);
    }

    public void testGetCardinalityUnidirectionalOneToMany() throws Exception {

        CodeGeneratorConfiguration codeGeneratorConfiguration = new CodeGeneratorConfigurationProvider()
                .getCodeGeneratorConfiguration();
        ProjectConfigurationTool projectConfigurationTool = new ProjectConfigurationToolProvider()
                .getProjectConfigurationTool();
        OrmDataGenerator ormDataGenerator = new OrmDataGenerator();
        OrmDataGeneratorContext ormDataGeneratorContext = new OrmDataGeneratorContext(codeGeneratorConfiguration,
                projectConfigurationTool.getProjectType());

        Class modelClass = codeGeneratorConfiguration.getClassList().getClass("test.model.Address");
        Field field = modelClass.getDeclaredField("people");
        Boolean isManyToMany = ((Cardinality) ReflectionUtils.executePrivateMethod(ormDataGenerator, "getCardinality",
                new java.lang.Class[] { Field.class, OrmDataGeneratorContext.class }, new Object[] { field,
                        ormDataGeneratorContext })).isManyToMany();

        assertTrue(isManyToMany);

    }

    public void testGetCardinalityUnidirectionalManyToOne() throws Exception {

        CodeGeneratorConfiguration codeGeneratorConfiguration = new CodeGeneratorConfigurationProvider()
                .getCodeGeneratorConfiguration();
        ProjectConfigurationTool projectConfigurationTool = new ProjectConfigurationToolProvider()
                .getProjectConfigurationTool();

        OrmDataGenerator ormDataGenerator = new OrmDataGenerator();
        OrmDataGeneratorContext ormDataGeneratorContext = new OrmDataGeneratorContext(codeGeneratorConfiguration,
                projectConfigurationTool.getProjectType());

        Class modelClass = codeGeneratorConfiguration.getClassList().getClass("test.model.Person");

        Field field = modelClass.getDeclaredField("name");
        Boolean isManyToOne = ((Cardinality) ReflectionUtils.executePrivateMethod(ormDataGenerator, "getCardinality",
                new java.lang.Class[] { Field.class, OrmDataGeneratorContext.class }, new Object[] { field,
                        ormDataGeneratorContext })).isManyToOne();

        assertTrue(isManyToOne);
    }

    public void testGetCardinalityBidirectionalManyToMany() throws Exception {

        CodeGeneratorConfiguration codeGeneratorConfiguration = new CodeGeneratorConfigurationProvider()
                .getCodeGeneratorConfiguration();
        ProjectConfigurationTool projectConfigurationTool = new ProjectConfigurationToolProvider()
                .getProjectConfigurationTool();
        OrmDataGenerator ormDataGenerator = new OrmDataGenerator();
        OrmDataGeneratorContext ormDataGeneratorContext = new OrmDataGeneratorContext(codeGeneratorConfiguration,
                projectConfigurationTool.getProjectType());

        Class modelClass = codeGeneratorConfiguration.getClassList().getClass("test.model.Address");
        Field field = modelClass.getDeclaredField("people");
        Boolean isManyToMany = ((Cardinality) ReflectionUtils.executePrivateMethod(ormDataGenerator, "getCardinality",
                new java.lang.Class[] { Field.class, Field.class, OrmDataGeneratorContext.class }, new Object[] {
                        field, field, ormDataGeneratorContext })).isManyToMany();

        assertTrue(isManyToMany);
    }

    public void testGetCardinalityBidirectionalManyToOne() throws Exception {

        CodeGeneratorConfiguration codeGeneratorConfiguration = new CodeGeneratorConfigurationProvider()
                .getCodeGeneratorConfiguration();
        ProjectConfigurationTool projectConfigurationTool = new ProjectConfigurationToolProvider()
                .getProjectConfigurationTool();
        OrmDataGenerator ormDataGenerator = new OrmDataGenerator();
        OrmDataGeneratorContext ormDataGeneratorContext = new OrmDataGeneratorContext(codeGeneratorConfiguration,
                projectConfigurationTool.getProjectType());

        Class modelClass = codeGeneratorConfiguration.getClassList().getClass("test.model.Person");

        modelClass = codeGeneratorConfiguration.getClassList().getClass("test.model.Address");

        Field field = modelClass.getDeclaredField("name");
        Field fieldPeople = modelClass.getDeclaredField("people");
        Boolean isManyToOne = ((Cardinality) ReflectionUtils.executePrivateMethod(ormDataGenerator, "getCardinality",
                new java.lang.Class[] { Field.class, Field.class, OrmDataGeneratorContext.class }, new Object[] {
                        field, fieldPeople, ormDataGeneratorContext })).isManyToOne();

        assertTrue(isManyToOne);
    }

    public void testGetCardinalityBidirectionalOneToMany() throws Exception {

        CodeGeneratorConfiguration codeGeneratorConfiguration = new CodeGeneratorConfigurationProvider()
                .getCodeGeneratorConfiguration();
        ProjectConfigurationTool projectConfigurationTool = new ProjectConfigurationToolProvider()
                .getProjectConfigurationTool();
        OrmDataGenerator ormDataGenerator = new OrmDataGenerator();
        OrmDataGeneratorContext ormDataGeneratorContext = new OrmDataGeneratorContext(codeGeneratorConfiguration,
                projectConfigurationTool.getProjectType());

        Class modelClass = codeGeneratorConfiguration.getClassList().getClass("test.model.Person");

        modelClass = codeGeneratorConfiguration.getClassList().getClass("test.model.Address");

        Field field = modelClass.getDeclaredField("name");
        Field fieldPeople = modelClass.getDeclaredField("people");
        Boolean isOneToMany = ((Cardinality) ReflectionUtils.executePrivateMethod(ormDataGenerator, "getCardinality",
                new java.lang.Class[] { Field.class, Field.class, OrmDataGeneratorContext.class }, new Object[] {
                        fieldPeople, field, ormDataGeneratorContext })).isOneToMany();

        assertTrue(isOneToMany);
    }

    public void testGetCardinalityBidirectionalOneToOne() throws Exception {

        CodeGeneratorConfiguration codeGeneratorConfiguration = new CodeGeneratorConfigurationProvider()
                .getCodeGeneratorConfiguration();
        ProjectConfigurationTool projectConfigurationTool = new ProjectConfigurationToolProvider()
                .getProjectConfigurationTool();
        OrmDataGenerator ormDataGenerator = new OrmDataGenerator();
        OrmDataGeneratorContext ormDataGeneratorContext = new OrmDataGeneratorContext(codeGeneratorConfiguration,
                projectConfigurationTool.getProjectType());

        Class modelClass = codeGeneratorConfiguration.getClassList().getClass("test.model.Person");

        Field field = modelClass.getDeclaredField("name");
        Boolean isOneToOne = ((Cardinality) ReflectionUtils.executePrivateMethod(ormDataGenerator, "getCardinality",
                new java.lang.Class[] { Field.class, Field.class, OrmDataGeneratorContext.class }, new Object[] {
                        field, field, ormDataGeneratorContext })).isOneToOne();

        assertTrue(isOneToOne);
    }

    public void testIsBiDirectionalAssociation() throws Exception {

        CodeGeneratorConfiguration codeGeneratorConfiguration = new CodeGeneratorConfigurationProvider()
                .getCodeGeneratorConfiguration();
        ProjectConfigurationTool projectConfigurationTool = new ProjectConfigurationToolProvider()
                .getProjectConfigurationTool();
        OrmDataGenerator ormDataGenerator = new OrmDataGenerator();
        OrmDataGeneratorContext ormDataGeneratorContext = new OrmDataGeneratorContext(codeGeneratorConfiguration,
                projectConfigurationTool.getProjectType());

        /**
         * Field people from Address -> defines bidirectional association
         * between Person and Address
         */

        Class modelClass = codeGeneratorConfiguration.getClassList().getClass("test.model.Address");

        Field field = modelClass.getDeclaredField("people");
        Boolean isBiDirectionalAssociation = (Boolean) ReflectionUtils.executePrivateMethod(ormDataGenerator,
                "isBiDirectionalAssociation", new java.lang.Class[] { String.class, Field.class,
                        OrmDataGeneratorContext.class }, new Object[] { "test.model.Address", field,
                        ormDataGeneratorContext });

        assertTrue(isBiDirectionalAssociation);
    }

    public void testAnotherIsBiDirectionalAssociation() throws Exception {

        CodeGeneratorConfiguration codeGeneratorConfiguration = new CodeGeneratorConfigurationProvider()
                .getCodeGeneratorConfiguration();
        ProjectConfigurationTool projectConfigurationTool = new ProjectConfigurationToolProvider()
                .getProjectConfigurationTool();
        OrmDataGenerator ormDataGenerator = new OrmDataGenerator();
        OrmDataGeneratorContext ormDataGeneratorContext = new OrmDataGeneratorContext(codeGeneratorConfiguration,
                projectConfigurationTool.getProjectType());

        /** Field addressId from Address -> it doesn't define association. */

        Class modelClass = codeGeneratorConfiguration.getClassList().getClass("test.model.Address");

        Field field = modelClass.getDeclaredField("addressId");
        Boolean isBiDirectionalAssociation = (Boolean) ReflectionUtils.executePrivateMethod(ormDataGenerator,
                "isBiDirectionalAssociation", new java.lang.Class[] { String.class, Field.class,
                        OrmDataGeneratorContext.class }, new Object[] { "test.model.Address", field,
                        ormDataGeneratorContext });

        assertFalse(isBiDirectionalAssociation);
    }

    public void testOtherIsBiDirectionalAssociation() throws Exception {

        CodeGeneratorConfiguration codeGeneratorConfiguration = new CodeGeneratorConfigurationProvider()
                .getCodeGeneratorConfiguration();
        ProjectConfigurationTool projectConfigurationTool = new ProjectConfigurationToolProvider()
                .getProjectConfigurationTool();
        OrmDataGenerator ormDataGenerator = new OrmDataGenerator();
        OrmDataGeneratorContext ormDataGeneratorContext = new OrmDataGeneratorContext(codeGeneratorConfiguration,
                projectConfigurationTool.getProjectType());

        /**
         * Field address from Company -> it defines a uni directional
         * association
         */

        Class modelClass = codeGeneratorConfiguration.getClassList().getClass("test.model.Company");

        Field field = modelClass.getDeclaredField("address");
        Boolean isBiDirectionalAssociation = (Boolean) ReflectionUtils.executePrivateMethod(ormDataGenerator,
                "isBiDirectionalAssociation", new java.lang.Class[] { String.class, Field.class,
                        OrmDataGeneratorContext.class }, new Object[] { "test.model.Company", field,
                        ormDataGeneratorContext });

        assertFalse(isBiDirectionalAssociation);

    }

    public void testGetNameAssociatedClass() throws Exception {

        CodeGeneratorConfiguration codeGeneratorConfiguration = new CodeGeneratorConfigurationProvider()
                .getCodeGeneratorConfiguration();
        ProjectConfigurationTool projectConfigurationTool = new ProjectConfigurationToolProvider()
                .getProjectConfigurationTool();
        OrmDataGenerator ormDataGenerator = new OrmDataGenerator();
        OrmDataGeneratorContext ormDataGeneratorContext = new OrmDataGeneratorContext(codeGeneratorConfiguration,
                projectConfigurationTool.getProjectType());

        /** Field is a list of Room. */

        Class modelClass = codeGeneratorConfiguration.getClassList().getClass("test.model.House");

        Field field = modelClass.getDeclaredField("rooms");
        String generatedString = ((Class) ReflectionUtils.executePrivateMethod(ormDataGenerator, "getAssociatedClass",
                new java.lang.Class[] { Field.class, OrmDataGeneratorContext.class }, new Object[] { field,
                        ormDataGeneratorContext })).getSimpleName();

        assertEquals("Room", generatedString);

    }

    public void testGetAnotherNameAssociatedClass() throws Exception {

        CodeGeneratorConfiguration codeGeneratorConfiguration = new CodeGeneratorConfigurationProvider()
                .getCodeGeneratorConfiguration();
        ProjectConfigurationTool projectConfigurationTool = new ProjectConfigurationToolProvider()
                .getProjectConfigurationTool();
        OrmDataGenerator ormDataGenerator = new OrmDataGenerator();
        OrmDataGeneratorContext ormDataGeneratorContext = new OrmDataGeneratorContext(codeGeneratorConfiguration,
                projectConfigurationTool.getProjectType());

        /** Field is an array of Course */

        Class modelClass = codeGeneratorConfiguration.getClassList().getClass("test.model.Student");

        Field field = modelClass.getDeclaredField("courses");
        String generatedString = ((Class) ReflectionUtils.executePrivateMethod(ormDataGenerator, "getAssociatedClass",
                new java.lang.Class[] { Field.class, OrmDataGeneratorContext.class }, new Object[] { field,
                        ormDataGeneratorContext })).getSimpleName();

        assertEquals("Course", generatedString);
    }

    public void testGetOtherNameAssociatedClass() throws Exception {

        CodeGeneratorConfiguration codeGeneratorConfiguration = new CodeGeneratorConfigurationProvider()
                .getCodeGeneratorConfiguration();
        ProjectConfigurationTool projectConfigurationTool = new ProjectConfigurationToolProvider()
                .getProjectConfigurationTool();
        OrmDataGenerator ormDataGenerator = new OrmDataGenerator();
        OrmDataGeneratorContext ormDataGeneratorContext = new OrmDataGeneratorContext(codeGeneratorConfiguration,
                projectConfigurationTool.getProjectType());

        /** Field is int Type */

        Class modelClass = codeGeneratorConfiguration.getClassList().getClass("test.model.Room");

        Field field = modelClass.getDeclaredField("doorsCount");
        String generatedString = ((Class) ReflectionUtils.executePrivateMethod(ormDataGenerator, "getAssociatedClass",
                new java.lang.Class[] { Field.class, OrmDataGeneratorContext.class }, new Object[] { field,
                        ormDataGeneratorContext })).getSimpleName();

        assertEquals("int", generatedString);

    }

    public void testGetFieldTypeInCompany() throws Exception {

        CodeGeneratorConfiguration codeGeneratorConfiguration = new CodeGeneratorConfigurationProvider()
                .getCodeGeneratorConfiguration();
        ProjectConfigurationTool projectConfigurationTool = new ProjectConfigurationToolProvider()
                .getProjectConfigurationTool();
        OrmDataGenerator ormDataGenerator = new OrmDataGenerator();
        OrmDataGeneratorContext ormDataGeneratorContext = new OrmDataGeneratorContext(codeGeneratorConfiguration,
                projectConfigurationTool.getProjectType());

        /** Field employees */

        Class modelClass = codeGeneratorConfiguration.getClassList().getClass("test.model.Company");

        Field field = modelClass.getDeclaredField("employees");
        Boolean isMapType = ((VarType) ReflectionUtils.executePrivateMethod(ormDataGenerator, "getFieldType",
                new java.lang.Class[] { Field.class, OrmDataGeneratorContext.class }, new Object[] { field,
                        ormDataGeneratorContext })).isMapType();

        Boolean isEntityReference = ((CollectionType) ReflectionUtils.executePrivateMethod(ormDataGenerator,
                "getFieldType", new java.lang.Class[] { Field.class, OrmDataGeneratorContext.class }, new Object[] {
                        field, ormDataGeneratorContext })).getElementType().isEntityReference();

        String anEmployeeString = ((CollectionType) ReflectionUtils.executePrivateMethod(ormDataGenerator,
                "getFieldType", new java.lang.Class[] { Field.class, OrmDataGeneratorContext.class }, new Object[] {
                        field, ormDataGeneratorContext })).getElementType().getElementClass().getSimpleName();

        Boolean isOtherEntityReference = ((CollectionType) ReflectionUtils.executePrivateMethod(ormDataGenerator,
                "getFieldType", new java.lang.Class[] { Field.class, OrmDataGeneratorContext.class }, new Object[] {
                        field, ormDataGeneratorContext })).getElementType().isEntityReference();

        Boolean isBasicType = ((MapType) ReflectionUtils.executePrivateMethod(ormDataGenerator, "getFieldType",
                new java.lang.Class[] { Field.class, OrmDataGeneratorContext.class }, new Object[] { field,
                        ormDataGeneratorContext })).getIndexType().isBasicType();

        String aTypeString = ((MapType) ReflectionUtils.executePrivateMethod(ormDataGenerator, "getFieldType",
                new java.lang.Class[] { Field.class, OrmDataGeneratorContext.class }, new Object[] { field,
                        ormDataGeneratorContext })).getIndexType().getElementClass().getSimpleName();

        assertTrue(isMapType);
        assertTrue(isEntityReference);
        assertEquals("Employee", anEmployeeString);
        assertTrue(isOtherEntityReference);
        assertTrue(isBasicType);
        assertEquals("Integer", aTypeString);
    }

    public void testGetFieldTypeInStudent() throws Exception {

        CodeGeneratorConfiguration codeGeneratorConfiguration = new CodeGeneratorConfigurationProvider()
                .getCodeGeneratorConfiguration();
        ProjectConfigurationTool projectConfigurationTool = new ProjectConfigurationToolProvider()
                .getProjectConfigurationTool();
        OrmDataGenerator ormDataGenerator = new OrmDataGenerator();
        OrmDataGeneratorContext ormDataGeneratorContext = new OrmDataGeneratorContext(codeGeneratorConfiguration,
                projectConfigurationTool.getProjectType());

        /** Field courses */

        Class modelClass = codeGeneratorConfiguration.getClassList().getClass("test.model.Student");
        // Field classField = modelClass.getDeclaredField("courses");

        Field field = modelClass.getDeclaredField("courses");
        Boolean isArrayType = ((VarType) ReflectionUtils.executePrivateMethod(ormDataGenerator, "getFieldType",
                new java.lang.Class[] { Field.class, OrmDataGeneratorContext.class }, new Object[] { field,
                        ormDataGeneratorContext })).isArrayType();

        Boolean isEntityReference = ((CollectionType) ReflectionUtils.executePrivateMethod(ormDataGenerator,
                "getFieldType", new java.lang.Class[] { Field.class, OrmDataGeneratorContext.class }, new Object[] {
                        field, ormDataGeneratorContext })).getElementType().isEntityReference();

        String aCourseString = ((CollectionType) ReflectionUtils.executePrivateMethod(ormDataGenerator, "getFieldType",
                new java.lang.Class[] { Field.class, OrmDataGeneratorContext.class }, new Object[] { field,
                        ormDataGeneratorContext })).getElementType().getElementClass().getSimpleName();

        assertTrue(isArrayType);
        assertTrue(isEntityReference);
        assertEquals("Course", aCourseString);
    }

    public void testGetFieldTypeInEmployee() throws Exception {

        CodeGeneratorConfiguration codeGeneratorConfiguration = new CodeGeneratorConfigurationProvider()
                .getCodeGeneratorConfiguration();
        ProjectConfigurationTool projectConfigurationTool = new ProjectConfigurationToolProvider()
                .getProjectConfigurationTool();
        OrmDataGenerator ormDataGenerator = new OrmDataGenerator();
        OrmDataGeneratorContext ormDataGeneratorContext = new OrmDataGeneratorContext(codeGeneratorConfiguration,
                projectConfigurationTool.getProjectType());

        /** Field companies */

        Class modelClass = codeGeneratorConfiguration.getClassList().getClass("test.model.Employee");
        Field classField = modelClass.getDeclaredField("companies");

        Boolean isSetType = ((VarType) ReflectionUtils.executePrivateMethod(ormDataGenerator, "getFieldType",
                new java.lang.Class[] { Field.class, OrmDataGeneratorContext.class }, new Object[] { classField,
                        ormDataGeneratorContext })).isSetType();

        Boolean isEntityReference = ((CollectionType) ReflectionUtils.executePrivateMethod(ormDataGenerator,
                "getFieldType", new java.lang.Class[] { Field.class, OrmDataGeneratorContext.class }, new Object[] {
                        classField, ormDataGeneratorContext })).getElementType().isEntityReference();

        String aCompanyString = ((CollectionType) ReflectionUtils.executePrivateMethod(ormDataGenerator,
                "getFieldType", new java.lang.Class[] { Field.class, OrmDataGeneratorContext.class }, new Object[] {
                        classField, ormDataGeneratorContext })).getElementType().getElementClass().getSimpleName();

        assertTrue(isSetType);
        assertTrue(isEntityReference);
        assertEquals("Company", aCompanyString);
    }

    public void testGetFieldTypeInHouse() throws Exception {

        CodeGeneratorConfiguration codeGeneratorConfiguration = new CodeGeneratorConfigurationProvider()
                .getCodeGeneratorConfiguration();
        ProjectConfigurationTool projectConfigurationTool = new ProjectConfigurationToolProvider()
                .getProjectConfigurationTool();
        OrmDataGenerator ormDataGenerator = new OrmDataGenerator();
        OrmDataGeneratorContext ormDataGeneratorContext = new OrmDataGeneratorContext(codeGeneratorConfiguration,
                projectConfigurationTool.getProjectType());

        /** Field rooms */

        Class modelClass = codeGeneratorConfiguration.getClassList().getClass("test.model.House");
        Field classField = modelClass.getDeclaredField("rooms");

        Boolean isListType = ((VarType) ReflectionUtils.executePrivateMethod(ormDataGenerator, "getFieldType",
                new java.lang.Class[] { Field.class, OrmDataGeneratorContext.class }, new Object[] { classField,
                        ormDataGeneratorContext })).isListType();

        Boolean isEntityReference = ((CollectionType) ReflectionUtils.executePrivateMethod(ormDataGenerator,
                "getFieldType", new java.lang.Class[] { Field.class, OrmDataGeneratorContext.class }, new Object[] {
                        classField, ormDataGeneratorContext })).getElementType().isEntityReference();

        String aRoomString = ((CollectionType) ReflectionUtils.executePrivateMethod(ormDataGenerator, "getFieldType",
                new java.lang.Class[] { Field.class, OrmDataGeneratorContext.class }, new Object[] { classField,
                        ormDataGeneratorContext })).getElementType().getElementClass().getSimpleName();

        assertTrue(isListType);
        assertTrue(isEntityReference);
        assertEquals("Room", aRoomString);

        /** Field address */

        modelClass = codeGeneratorConfiguration.getClassList().getClass("test.model.House");
        Field fieldAddress = modelClass.getDeclaredField("address");

        Boolean isAnotherEntityReference = ((VarType) ReflectionUtils.executePrivateMethod(ormDataGenerator,
                "getFieldType", new java.lang.Class[] { Field.class, OrmDataGeneratorContext.class }, new Object[] {
                        fieldAddress, ormDataGeneratorContext })).isEntityReference();
        assertTrue(isAnotherEntityReference);
    }

    public void testGetFieldTypeInAddress() throws Exception {

        CodeGeneratorConfiguration codeGeneratorConfiguration = new CodeGeneratorConfigurationProvider()
                .getCodeGeneratorConfiguration();
        ProjectConfigurationTool projectConfigurationTool = new ProjectConfigurationToolProvider()
                .getProjectConfigurationTool();
        OrmDataGenerator ormDataGenerator = new OrmDataGenerator();
        OrmDataGeneratorContext ormDataGeneratorContext = new OrmDataGeneratorContext(codeGeneratorConfiguration,
                projectConfigurationTool.getProjectType());

        /** Field addressId */

        Class modelClass = codeGeneratorConfiguration.getClassList().getClass("test.model.Address");

        Field field = modelClass.getDeclaredField("addressId");

        Boolean isBasicType = ((VarType) ReflectionUtils.executePrivateMethod(ormDataGenerator, "getFieldType",
                new java.lang.Class[] { Field.class, OrmDataGeneratorContext.class }, new Object[] { field,
                        ormDataGeneratorContext })).isBasicType();

        String anIntString = ((BasicType) ReflectionUtils.executePrivateMethod(ormDataGenerator, "getFieldType",
                new java.lang.Class[] { Field.class, OrmDataGeneratorContext.class }, new Object[] { field,
                        ormDataGeneratorContext })).getElementClass().getSimpleName();

        assertTrue(isBasicType);
        assertEquals("int", anIntString);

    }

    /*
     * public void testIsNotParameterizedType() throws Exception {
     * 
     * CodeGeneratorConfiguration codeGeneratorConfiguration = new
     * CodeGeneratorConfigurationProvider().getCodeGeneratorConfiguration();
     * OrmDataGenerator ormDataGenerator = new OrmDataGenerator();
     * OrmDataGeneratorContext ormDataGeneratorContext = new
     * OrmDataGeneratorContext
     * (codeGeneratorConfiguration,projectConfigurationTool);
     * 
     * Class modelClass =
     * codeGeneratorConfiguration.getClassList().getClass("test.model.Student");
     * 
     * Boolean isAnotherParameterizedType =
     * (Boolean)ReflectionUtils.executePrivateMethod
     * (ormDataGenerator,"isParameterizedType", new
     * java.lang.Class[]{Type.class,OrmDataGeneratorContext.class}, new
     * Object[]{
     * modelClass.getDeclaredField("courses").getType(),ormDataGeneratorContext
     * }); //era get generic type
     * 
     * assertFalse(isAnotherParameterizedType);
     * 
     * Class anotherModelClass =
     * codeGeneratorConfiguration.getClassList().getClass("test.model.House");
     * 
     * Boolean isOtherParameterizedType =
     * (Boolean)ReflectionUtils.executePrivateMethod
     * (ormDataGenerator,"isParameterizedType", new
     * java.lang.Class[]{Type.class,OrmDataGeneratorContext.class}, new
     * Object[]{anotherModelClass.getDeclaredField("address").getType(),
     * ormDataGeneratorContext});//igual que antes
     * 
     * assertFalse(isOtherParameterizedType);
     * 
     * }
     */

    public void testIsEntityReference() throws Exception {

        CodeGeneratorConfiguration codeGeneratorConfiguration = new CodeGeneratorConfigurationProvider()
                .getCodeGeneratorConfiguration();
        ProjectConfigurationTool projectConfigurationTool = new ProjectConfigurationToolProvider()
                .getProjectConfigurationTool();
        OrmDataGenerator ormDataGenerator = new OrmDataGenerator();
        OrmDataGeneratorContext ormDataGeneratorContext = new OrmDataGeneratorContext(codeGeneratorConfiguration,
                projectConfigurationTool.getProjectType());

        ClassList classNameList = codeGeneratorConfiguration.getClassList();

        for (Iterator<com.hexacta.booster.codegeneration.metamodel.Class> iterator = classNameList.iterator(); iterator
                .hasNext();) {
            com.hexacta.booster.codegeneration.metamodel.Class className = iterator.next();

            Boolean isEntityReference = (Boolean) ReflectionUtils.executePrivateMethod(ormDataGenerator,
                    "isEntityReference",
                    new java.lang.Class[] { java.lang.String.class, OrmDataGeneratorContext.class }, new Object[] {
                            className.getName(), ormDataGeneratorContext });
            assertTrue(isEntityReference);
        }
    }

    public void testIsNotEntityReference() throws Exception {

        CodeGeneratorConfiguration codeGeneratorConfiguration = new CodeGeneratorConfigurationProvider()
                .getCodeGeneratorConfiguration();
        ProjectConfigurationTool projectConfigurationTool = new ProjectConfigurationToolProvider()
                .getProjectConfigurationTool();
        OrmDataGenerator ormDataGenerator = new OrmDataGenerator();
        OrmDataGeneratorContext ormDataGeneratorContext = new OrmDataGeneratorContext(codeGeneratorConfiguration,
                projectConfigurationTool.getProjectType());

        Boolean isEntityReference = (Boolean) ReflectionUtils.executePrivateMethod(ormDataGenerator,
                "isEntityReference", new java.lang.Class[] { java.lang.String.class, OrmDataGeneratorContext.class },
                new Object[] { "int", ormDataGeneratorContext });

        Boolean isAnotherEntityReference = (Boolean) ReflectionUtils.executePrivateMethod(ormDataGenerator,
                "isEntityReference", new java.lang.Class[] { java.lang.String.class, OrmDataGeneratorContext.class },
                new Object[] { "java.lang.String", ormDataGeneratorContext });

        assertFalse(isEntityReference);
        assertFalse(isAnotherEntityReference);
    }

    public void testGetElementType() throws Exception {

        CodeGeneratorConfiguration codeGeneratorConfiguration = new CodeGeneratorConfigurationProvider()
                .getCodeGeneratorConfiguration();
        ProjectConfigurationTool projectConfigurationTool = new ProjectConfigurationToolProvider()
                .getProjectConfigurationTool();
        OrmDataGenerator ormDataGenerator = new OrmDataGenerator();
        OrmDataGeneratorContext ormDataGeneratorContext = new OrmDataGeneratorContext(codeGeneratorConfiguration,
                projectConfigurationTool.getProjectType());

        Boolean isBasicType = ((ElementType) ReflectionUtils.executePrivateMethod(ormDataGenerator, "getElementType",
                new java.lang.Class[] { java.lang.String.class, OrmDataGeneratorContext.class }, new Object[] { "int",
                        ormDataGeneratorContext })).isBasicType();

        assertTrue(isBasicType);

        Boolean isEntityReference = ((ElementType) ReflectionUtils.executePrivateMethod(ormDataGenerator,
                "getElementType", new java.lang.Class[] { java.lang.String.class, OrmDataGeneratorContext.class },
                new Object[] { codeGeneratorConfiguration.getClassList().iterator().next().getName(),
                        ormDataGeneratorContext })).isEntityReference();

        assertTrue(isEntityReference);

    }

    public void testGetCollectionTypeInCompany() throws Exception {

        CodeGeneratorConfiguration codeGeneratorConfiguration = new CodeGeneratorConfigurationProvider()
                .getCodeGeneratorConfiguration();
        ProjectConfigurationTool projectConfigurationTool = new ProjectConfigurationToolProvider()
                .getProjectConfigurationTool();
        OrmDataGenerator ormDataGenerator = new OrmDataGenerator();
        OrmDataGeneratorContext ormDataGeneratorContext = new OrmDataGeneratorContext(codeGeneratorConfiguration,
                projectConfigurationTool.getProjectType());

        /** Field employees */

        Class modelClass = codeGeneratorConfiguration.getClassList().getClass("test.model.Company");
        Field classField = modelClass.getDeclaredField("employees");

        Boolean isMapType = ((VarType) ReflectionUtils.executePrivateMethod(ormDataGenerator, "getCollectionType",
                new java.lang.Class[] { Field.class, OrmDataGeneratorContext.class }, new Object[] { classField,
                        ormDataGeneratorContext })).isMapType();

        Boolean isEntityReference = ((CollectionType) ReflectionUtils.executePrivateMethod(ormDataGenerator,
                "getCollectionType", new java.lang.Class[] { Field.class, OrmDataGeneratorContext.class },
                new Object[] { classField, ormDataGeneratorContext })).getElementType().isEntityReference();

        String anEmployeeString = ((CollectionType) ReflectionUtils.executePrivateMethod(ormDataGenerator,
                "getCollectionType", new java.lang.Class[] { Field.class, OrmDataGeneratorContext.class },
                new Object[] { classField, ormDataGeneratorContext })).getElementType().getElementClass()
                .getSimpleName();

        Boolean isAnotherEntityReference = ((CollectionType) ReflectionUtils.executePrivateMethod(ormDataGenerator,
                "getCollectionType", new java.lang.Class[] { Field.class, OrmDataGeneratorContext.class },
                new Object[] { classField, ormDataGeneratorContext })).getElementType().isEntityReference();

        Boolean isAnotherBasicType = ((MapType) ReflectionUtils.executePrivateMethod(ormDataGenerator,
                "getCollectionType", new java.lang.Class[] { Field.class, OrmDataGeneratorContext.class },
                new Object[] { classField, ormDataGeneratorContext })).getIndexType().isBasicType();

        String anIntegerString = ((MapType) ReflectionUtils.executePrivateMethod(ormDataGenerator, "getCollectionType",
                new java.lang.Class[] { Field.class, OrmDataGeneratorContext.class }, new Object[] { classField,
                        ormDataGeneratorContext })).getIndexType().getElementClass().getSimpleName();

        assertTrue(isMapType);
        assertTrue(isEntityReference);
        assertEquals("Employee", anEmployeeString);
        assertTrue(isAnotherEntityReference);
        assertTrue(isAnotherBasicType);
        assertEquals("Integer", anIntegerString);
    }

    public void testGetCollectionTypeInStudent() throws Exception {

        CodeGeneratorConfiguration codeGeneratorConfiguration = new CodeGeneratorConfigurationProvider()
                .getCodeGeneratorConfiguration();
        ProjectConfigurationTool projectConfigurationTool = new ProjectConfigurationToolProvider()
                .getProjectConfigurationTool();
        OrmDataGenerator ormDataGenerator = new OrmDataGenerator();
        OrmDataGeneratorContext ormDataGeneratorContext = new OrmDataGeneratorContext(codeGeneratorConfiguration,
                projectConfigurationTool.getProjectType());

        /** Field courses */

        Class modelClass = codeGeneratorConfiguration.getClassList().getClass("test.model.Student");
        Field classField = modelClass.getDeclaredField("courses");

        Boolean isArrayType = ((VarType) ReflectionUtils.executePrivateMethod(ormDataGenerator, "getCollectionType",
                new java.lang.Class[] { Field.class, OrmDataGeneratorContext.class }, new Object[] { classField,
                        ormDataGeneratorContext })).isArrayType();

        Boolean isEntityReference = ((CollectionType) ReflectionUtils.executePrivateMethod(ormDataGenerator,
                "getCollectionType", new java.lang.Class[] { Field.class, OrmDataGeneratorContext.class },
                new Object[] { classField, ormDataGeneratorContext })).getElementType().isEntityReference();

        String aCourseString = ((CollectionType) ReflectionUtils.executePrivateMethod(ormDataGenerator,
                "getCollectionType", new java.lang.Class[] { Field.class, OrmDataGeneratorContext.class },
                new Object[] { classField, ormDataGeneratorContext })).getElementType().getElementClass()
                .getSimpleName();

        assertTrue(isArrayType);
        assertTrue(isEntityReference);
        assertEquals("Course", aCourseString);
    }

    public void testGetCollectionTypeInEmployee() throws Exception {

        CodeGeneratorConfiguration codeGeneratorConfiguration = new CodeGeneratorConfigurationProvider()
                .getCodeGeneratorConfiguration();
        ProjectConfigurationTool projectConfigurationTool = new ProjectConfigurationToolProvider()
                .getProjectConfigurationTool();
        OrmDataGenerator ormDataGenerator = new OrmDataGenerator();
        OrmDataGeneratorContext ormDataGeneratorContext = new OrmDataGeneratorContext(codeGeneratorConfiguration,
                projectConfigurationTool.getProjectType());

        /** Field companies */

        Class modelClass = codeGeneratorConfiguration.getClassList().getClass("test.model.Employee");
        Field classField = modelClass.getDeclaredField("companies");

        Boolean isSetType = ((VarType) ReflectionUtils.executePrivateMethod(ormDataGenerator, "getCollectionType",
                new java.lang.Class[] { Field.class, OrmDataGeneratorContext.class }, new Object[] { classField,
                        ormDataGeneratorContext })).isSetType();

        Boolean isEntityReference = ((CollectionType) ReflectionUtils.executePrivateMethod(ormDataGenerator,
                "getCollectionType", new java.lang.Class[] { Field.class, OrmDataGeneratorContext.class },
                new Object[] { classField, ormDataGeneratorContext })).getElementType().isEntityReference();

        String aCompanyString = ((CollectionType) ReflectionUtils.executePrivateMethod(ormDataGenerator,
                "getCollectionType", new java.lang.Class[] { Field.class, OrmDataGeneratorContext.class },
                new Object[] { classField, ormDataGeneratorContext })).getElementType().getElementClass()
                .getSimpleName();

        assertTrue(isSetType);
        assertTrue(isEntityReference);
        assertEquals("Company", aCompanyString);
    }

    public void testGetCollectionTypeInHouse() throws Exception {

        CodeGeneratorConfiguration codeGeneratorConfiguration = new CodeGeneratorConfigurationProvider()
                .getCodeGeneratorConfiguration();
        ProjectConfigurationTool projectConfigurationTool = new ProjectConfigurationToolProvider()
                .getProjectConfigurationTool();
        OrmDataGenerator ormDataGenerator = new OrmDataGenerator();
        OrmDataGeneratorContext ormDataGeneratorContext = new OrmDataGeneratorContext(codeGeneratorConfiguration,
                projectConfigurationTool.getProjectType());

        /** Field rooms */

        Class modelClass = codeGeneratorConfiguration.getClassList().getClass("test.model.House");
        Field classField = modelClass.getDeclaredField("rooms");

        Boolean isListType = ((VarType) ReflectionUtils.executePrivateMethod(ormDataGenerator, "getCollectionType",
                new java.lang.Class[] { Field.class, OrmDataGeneratorContext.class }, new Object[] { classField,
                        ormDataGeneratorContext })).isListType();

        Boolean isEntityReference = ((CollectionType) ReflectionUtils.executePrivateMethod(ormDataGenerator,
                "getCollectionType", new java.lang.Class[] { Field.class, OrmDataGeneratorContext.class },
                new Object[] { classField, ormDataGeneratorContext })).getElementType().isEntityReference();

        String aRoomString = ((CollectionType) ReflectionUtils.executePrivateMethod(ormDataGenerator,
                "getCollectionType", new java.lang.Class[] { Field.class, OrmDataGeneratorContext.class },
                new Object[] { classField, ormDataGeneratorContext })).getElementType().getElementClass()
                .getSimpleName();

        assertTrue(isListType);
        assertTrue(isEntityReference);
        assertEquals("Room", aRoomString);
    }
}
