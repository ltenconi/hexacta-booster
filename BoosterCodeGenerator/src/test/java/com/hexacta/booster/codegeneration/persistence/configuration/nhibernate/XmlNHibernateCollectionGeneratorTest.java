package com.hexacta.booster.codegeneration.persistence.configuration.nhibernate;

import junit.framework.TestCase;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import test.hexacta.booster.providers.CodeGeneratorConfigurationProvider;

import com.hexacta.booster.codegeneration.configuration.ClassFinder;
import com.hexacta.booster.codegeneration.configuration.CodeGeneratorConfiguration;
import com.hexacta.booster.codegeneration.configuration.NotSupportedClassException;
import com.hexacta.booster.codegeneration.metamodel.Class;
import com.hexacta.booster.codegeneration.persistence.configuration.hibernate.XmlHibernateCollectionGenerator;
import com.hexacta.booster.codegeneration.persistence.configuration.hibernate.XmlHibernateCollectionGeneratorContext;
import com.hexacta.booster.codegeneration.persistence.ormmodel.ArrayType;
import com.hexacta.booster.codegeneration.persistence.ormmodel.BasicType;
import com.hexacta.booster.codegeneration.persistence.ormmodel.BiDirectional;
import com.hexacta.booster.codegeneration.persistence.ormmodel.CollectionType;
import com.hexacta.booster.codegeneration.persistence.ormmodel.CompositeType;
import com.hexacta.booster.codegeneration.persistence.ormmodel.EntityReference;
import com.hexacta.booster.codegeneration.persistence.ormmodel.ListType;
import com.hexacta.booster.codegeneration.persistence.ormmodel.ManyToMany;
import com.hexacta.booster.codegeneration.persistence.ormmodel.ManyToOne;
import com.hexacta.booster.codegeneration.persistence.ormmodel.MapType;
import com.hexacta.booster.codegeneration.persistence.ormmodel.OneToMany;
import com.hexacta.booster.codegeneration.persistence.ormmodel.OrmMapping;
import com.hexacta.booster.codegeneration.persistence.ormmodel.OrmProperty;
import com.hexacta.booster.codegeneration.persistence.ormmodel.SetType;
import com.hexacta.booster.codegeneration.persistence.ormmodel.UniDirectional;
import com.hexacta.booster.project.configuration.JavaProjectType;
import com.sun.org.apache.xerces.internal.dom.DocumentImpl;

/**
 * 
 */
public class XmlNHibernateCollectionGeneratorTest extends TestCase {

    private XmlNHibernateMappingGeneratorContext generateXmlNHibernateMappingGeneratorContext(
            final OrmMapping ormMapping) {

        CodeGeneratorConfigurationProvider codeGeneratorConfigurationProvider = new CodeGeneratorConfigurationProvider();

        Document xmlMapping = new DocumentImpl();

        Element hibernateMapping = xmlMapping.createElement("hibernate-mapping");

        hibernateMapping.setAttribute("default-access", "field");
        hibernateMapping.setAttribute("default-cascade", "save-update");

        Element currentElement = xmlMapping.createElement("class");
        currentElement.setAttribute("name", ormMapping.getMappingClass().getName());
        currentElement.setAttribute("table", ormMapping.getMappingClass().getSimpleName());

        hibernateMapping.appendChild(currentElement);
        xmlMapping.appendChild(hibernateMapping);

        XmlNHibernateMappingGeneratorContext hibernateMappingGeneratorContext = new XmlNHibernateMappingGeneratorContext(
                xmlMapping, ormMapping, currentElement, codeGeneratorConfigurationProvider
                        .getCodeGeneratorConfiguration());

        return hibernateMappingGeneratorContext;

    }

    public void testGenerateCodeForOrmPropertyXmlHibernateCollectionGeneratorContext()
            throws NotSupportedClassException {

        CodeGeneratorConfigurationProvider codeGeneratorConfigurationProvider = new CodeGeneratorConfigurationProvider();
        CodeGeneratorConfiguration codeGeneratorConfiguration = codeGeneratorConfigurationProvider
                .getCodeGeneratorConfiguration();

        // BasicType
        OrmMapping ormMapping = new OrmMapping(ClassFinder.find("test.model.Address", codeGeneratorConfiguration
                .getClassList(), new JavaProjectType()), "addressId");
        XmlNHibernateMappingGeneratorContext xmlNHibernateMappingGeneratorContext = generateXmlNHibernateMappingGeneratorContext(ormMapping);
        XmlHibernateCollectionGeneratorContext hibernateCollectionGeneratorContext = new XmlHibernateCollectionGeneratorContext(
                xmlNHibernateMappingGeneratorContext.getXmlMapping(), new MapType(new EntityReference(ClassFinder.find(
                        "test.model.House", codeGeneratorConfiguration.getClassList(), new JavaProjectType())),
                        new BasicType(ClassFinder.find("java.lang.String", codeGeneratorConfiguration.getClassList(),
                                new JavaProjectType()))), ormMapping, codeGeneratorConfiguration);
        OrmProperty ormProperty = new OrmProperty("name", hibernateCollectionGeneratorContext.getCollectionType());
        XmlHibernateCollectionGenerator hibernateCollectionGenerator = new XmlHibernateCollectionGenerator();
        hibernateCollectionGenerator.generateCodeFor(ormProperty, hibernateCollectionGeneratorContext);

        Element property = hibernateCollectionGeneratorContext.getProperty();

        assertEquals(3, property.getChildNodes().getLength());
        assertEquals(1, property.getAttributes().getLength());
        assertEquals("name", property.getAttributes().getNamedItem("name").getNodeValue());

        assertEquals("key", property.getChildNodes().item(0).getNodeName());
        assertEquals(0, property.getChildNodes().item(0).getChildNodes().getLength());
        assertEquals(1, property.getChildNodes().item(0).getAttributes().getLength());
        assertEquals("AddressId", property.getChildNodes().item(0).getAttributes().getNamedItem("column")
                .getNodeValue());

        assertEquals("map-key-many-to-many", property.getChildNodes().item(1).getNodeName());
        assertEquals(0, property.getChildNodes().item(1).getChildNodes().getLength());
        assertEquals(1, property.getChildNodes().item(1).getAttributes().getLength());
        assertEquals("test.model.House", property.getChildNodes().item(1).getAttributes().getNamedItem("class")
                .getNodeValue());

        assertEquals("element", property.getChildNodes().item(2).getNodeName());
        assertEquals(0, property.getChildNodes().item(2).getChildNodes().getLength());
        assertEquals(1, property.getChildNodes().item(2).getAttributes().getLength());
        assertEquals("java.lang.String", property.getChildNodes().item(2).getAttributes().getNamedItem("type")
                .getNodeValue());

    }

    public void testGenerateCodeForBiDirectionalOneToManyXmlHibernateCollectionGeneratorContext()
            throws NotSupportedClassException {

        CodeGeneratorConfigurationProvider codeGeneratorConfigurationProvider = new CodeGeneratorConfigurationProvider();
        CodeGeneratorConfiguration codeGeneratorConfiguration = codeGeneratorConfigurationProvider
                .getCodeGeneratorConfiguration();
        Class classNameA = ClassFinder.find("test.model.Address", codeGeneratorConfiguration.getClassList(),
                new JavaProjectType());
        Class classNameB = ClassFinder.find("test.model.Person", codeGeneratorConfiguration.getClassList(),
                new JavaProjectType());

        OneToMany oneToMany = new OneToMany();

        BiDirectional bidirectional = new BiDirectional("people", "address", new SetType(
                new EntityReference(classNameB)), new EntityReference(classNameA), classNameA, classNameB);

        XmlNHibernateMappingGeneratorContext anHibernateMappingGeneratorContext = generateXmlNHibernateMappingGeneratorContext(new OrmMapping(
                classNameA, "addressId"));

        XmlHibernateCollectionGeneratorContext xmlHibernateCollectionGeneratorContext = new XmlHibernateCollectionGeneratorContext(
                anHibernateMappingGeneratorContext.getXmlMapping(), (CollectionType) bidirectional.getVarTypeA(),
                anHibernateMappingGeneratorContext.getOrmMapping(), codeGeneratorConfigurationProvider
                        .getCodeGeneratorConfiguration());

        XmlHibernateCollectionGenerator xmlHibernateCollectionGenerator = new XmlHibernateCollectionGenerator();
        xmlHibernateCollectionGenerator.generateCodeFor(bidirectional, oneToMany,
                xmlHibernateCollectionGeneratorContext);

        Element property = xmlHibernateCollectionGeneratorContext.getProperty();

        assertEquals("set", property.getTagName());
        assertEquals(2, property.getChildNodes().getLength());
        assertEquals(2, property.getChildNodes().getLength());
        assertEquals(2, property.getAttributes().getLength());
        assertEquals("true", property.getAttributes().getNamedItem("inverse").getNodeValue());
        assertEquals("people", property.getAttributes().getNamedItem("name").getNodeValue());

        // element key
        assertEquals("key", property.getChildNodes().item(0).getNodeName());
        assertEquals(1, property.getChildNodes().item(0).getAttributes().getLength());
        assertEquals("AddressId", property.getChildNodes().item(0).getAttributes().getNamedItem("column")
                .getNodeValue());
        assertEquals(0, property.getChildNodes().item(0).getChildNodes().getLength());

        // element one-to-many
        assertEquals("one-to-many", property.getChildNodes().item(1).getNodeName());
        assertEquals(1, property.getChildNodes().item(1).getAttributes().getLength());
        assertEquals("test.model.Person", property.getChildNodes().item(1).getAttributes().getNamedItem("class")
                .getNodeValue());
        assertEquals(0, property.getChildNodes().item(1).getChildNodes().getLength());

    }

    public void testGenerateCodeForBiDirectionalManyToOneXmlHibernateCollectionGeneratorContext()
            throws NotSupportedClassException {

        CodeGeneratorConfigurationProvider codeGeneratorConfigurationProvider = new CodeGeneratorConfigurationProvider();
        CodeGeneratorConfiguration codeGeneratorConfiguration = codeGeneratorConfigurationProvider
                .getCodeGeneratorConfiguration();

        Class classNameA = ClassFinder.find("test.model.Person", codeGeneratorConfiguration.getClassList(),
                new JavaProjectType());
        Class classNameB = ClassFinder.find("test.model.Address", codeGeneratorConfiguration.getClassList(),
                new JavaProjectType());
        ManyToOne manyToOne = new ManyToOne();
        BiDirectional bidirectional = new BiDirectional("address", "people", new EntityReference(classNameA),
                new SetType(new EntityReference(classNameB)), classNameA, classNameB);

        XmlNHibernateMappingGeneratorContext anHibernateMappingGeneratorContext = generateXmlNHibernateMappingGeneratorContext(new OrmMapping(
                classNameB, "addressId"));

        XmlHibernateCollectionGeneratorContext xmlHibernateCollectionGeneratorContext = new XmlHibernateCollectionGeneratorContext(
                anHibernateMappingGeneratorContext.getXmlMapping(), (CollectionType) bidirectional.getVarTypeB(),
                anHibernateMappingGeneratorContext.getOrmMapping(), codeGeneratorConfigurationProvider
                        .getCodeGeneratorConfiguration());

        XmlHibernateCollectionGenerator xmlHibernateCollectionGenerator = new XmlHibernateCollectionGenerator();
        xmlHibernateCollectionGenerator.generateCodeFor(bidirectional, manyToOne,
                xmlHibernateCollectionGeneratorContext);

        Element property = xmlHibernateCollectionGeneratorContext.getProperty();

        assertEquals("set", property.getTagName());
        assertEquals(2, property.getChildNodes().getLength());
        assertEquals(2, property.getChildNodes().getLength());
        assertEquals(2, property.getAttributes().getLength());
        assertEquals("true", property.getAttributes().getNamedItem("inverse").getNodeValue());
        assertEquals("people", property.getAttributes().getNamedItem("name").getNodeValue());

        // element key
        assertEquals("key", property.getChildNodes().item(0).getNodeName());
        assertEquals(1, property.getChildNodes().item(0).getAttributes().getLength());
        assertEquals("AddressId", property.getChildNodes().item(0).getAttributes().getNamedItem("column")
                .getNodeValue());
        assertEquals(0, property.getChildNodes().item(0).getChildNodes().getLength());

        // element one-to-many
        assertEquals("one-to-many", property.getChildNodes().item(1).getNodeName());
        assertEquals(1, property.getChildNodes().item(1).getAttributes().getLength());
        assertEquals("test.model.Person", property.getChildNodes().item(1).getAttributes().getNamedItem("class")
                .getNodeValue());
        assertEquals(0, property.getChildNodes().item(1).getChildNodes().getLength());

    }

    public void testGenerateSideACodeFor() throws NotSupportedClassException {

        CodeGeneratorConfigurationProvider codeGeneratorConfigurationProvider = new CodeGeneratorConfigurationProvider();
        CodeGeneratorConfiguration codeGeneratorConfiguration = codeGeneratorConfigurationProvider
                .getCodeGeneratorConfiguration();

        ManyToMany manyToMany = new ManyToMany();
        Class classNameA = ClassFinder.find("test.model.Company", codeGeneratorConfiguration.getClassList(),
                new JavaProjectType());
        Class classNameB = ClassFinder.find("test.model.Employee", codeGeneratorConfiguration.getClassList(),
                new JavaProjectType());

        BiDirectional bidirectional = new BiDirectional("employees", "companies", new MapType(new BasicType(ClassFinder
                .find("java.lang.Integer", codeGeneratorConfiguration.getClassList(), new JavaProjectType())),
                new EntityReference(classNameB)), new SetType(new EntityReference(classNameA)), classNameA, classNameB);

        OrmMapping ormMappingA = new OrmMapping(classNameA, "companyId");

        XmlNHibernateMappingGeneratorContext anHibernateMappingGeneratorContext = generateXmlNHibernateMappingGeneratorContext(ormMappingA);

        XmlHibernateCollectionGeneratorContext xmlHibernateCollectionGeneratorContext = new XmlHibernateCollectionGeneratorContext(
                anHibernateMappingGeneratorContext.getXmlMapping(), (CollectionType) bidirectional.getVarTypeA(),
                anHibernateMappingGeneratorContext.getOrmMapping(), codeGeneratorConfiguration);

        XmlHibernateCollectionGenerator xmlHibernateCollectionGenerator = new XmlHibernateCollectionGenerator();

        xmlHibernateCollectionGenerator.generateSideACodeFor(bidirectional, manyToMany,
                xmlHibernateCollectionGeneratorContext);

        Element property = xmlHibernateCollectionGeneratorContext.getProperty();

        assertEquals("map", property.getTagName());
        assertEquals(3, property.getChildNodes().getLength());
        assertEquals(2, property.getAttributes().getLength());
        assertEquals("employees", property.getAttributes().getNamedItem("name").getNodeValue());
        assertEquals("Company_Employee", property.getAttributes().getNamedItem("table").getNodeValue());

        // element key
        assertEquals("key", property.getChildNodes().item(0).getNodeName());
        assertEquals(1, property.getChildNodes().item(0).getAttributes().getLength());
        assertEquals("CompanyId", property.getChildNodes().item(0).getAttributes().getNamedItem("column")
                .getNodeValue());
        assertEquals(0, property.getChildNodes().item(0).getChildNodes().getLength());

        // element map-key
        assertEquals("map-key", property.getChildNodes().item(1).getNodeName());
        assertEquals(1, property.getChildNodes().item(1).getAttributes().getLength());
        assertEquals("java.lang.Integer", property.getChildNodes().item(1).getAttributes().getNamedItem("type")
                .getNodeValue());
        assertEquals(0, property.getChildNodes().item(1).getChildNodes().getLength());

        // element many-to-many
        assertEquals("many-to-many", property.getChildNodes().item(2).getNodeName());
        assertEquals(2, property.getChildNodes().item(2).getAttributes().getLength());
        assertEquals("test.model.Employee", property.getChildNodes().item(2).getAttributes().getNamedItem("class")
                .getNodeValue());
        assertEquals("EmployeeId", property.getChildNodes().item(2).getAttributes().getNamedItem("column")
                .getNodeValue());
        assertEquals(0, property.getChildNodes().item(2).getChildNodes().getLength());

    }

    public void testGenerateSideBCodeFor() throws NotSupportedClassException {

        CodeGeneratorConfigurationProvider codeGeneratorConfigurationProvider = new CodeGeneratorConfigurationProvider();
        CodeGeneratorConfiguration codeGeneratorConfiguration = codeGeneratorConfigurationProvider
                .getCodeGeneratorConfiguration();

        ManyToMany manyToMany = new ManyToMany();
        Class classNameA = ClassFinder.find("test.model.Company", codeGeneratorConfiguration.getClassList(),
                new JavaProjectType());
        Class classNameB = ClassFinder.find("test.model.Employee", codeGeneratorConfiguration.getClassList(),
                new JavaProjectType());

        BiDirectional bidirectional = new BiDirectional("employees", "companies", new MapType(new BasicType(ClassFinder
                .find("java.lang.Integer", codeGeneratorConfiguration.getClassList(), new JavaProjectType())),
                new EntityReference(classNameB)), new SetType(new EntityReference(classNameA)), classNameA, classNameB);
        OrmMapping ormMappingB = new OrmMapping(classNameB, "personId");

        XmlNHibernateMappingGeneratorContext anHibernateMappingGeneratorContext = generateXmlNHibernateMappingGeneratorContext(ormMappingB);

        XmlHibernateCollectionGeneratorContext xmlHibernateCollectionGeneratorContext = new XmlHibernateCollectionGeneratorContext(
                anHibernateMappingGeneratorContext.getXmlMapping(), (CollectionType) bidirectional.getVarTypeB(),
                anHibernateMappingGeneratorContext.getOrmMapping(), codeGeneratorConfiguration);

        XmlHibernateCollectionGenerator xmlHibernateCollectionGenerator = new XmlHibernateCollectionGenerator();
        xmlHibernateCollectionGenerator.generateSideBCodeFor(bidirectional, manyToMany,
                xmlHibernateCollectionGeneratorContext);

        Element property = xmlHibernateCollectionGeneratorContext.getProperty();

        assertEquals("set", property.getTagName());
        assertEquals(2, property.getChildNodes().getLength());
        assertEquals(2, property.getAttributes().getLength());
        assertEquals("companies", property.getAttributes().getNamedItem("name").getNodeValue());
        assertEquals("Company_Employee", property.getAttributes().getNamedItem("table").getNodeValue());

        // element key
        assertEquals("key", property.getChildNodes().item(0).getNodeName());
        assertEquals(1, property.getChildNodes().item(0).getAttributes().getLength());
        assertEquals("EmployeeId", property.getChildNodes().item(0).getAttributes().getNamedItem("column")
                .getNodeValue());
        assertEquals(0, property.getChildNodes().item(0).getChildNodes().getLength());

        // element many to many
        assertEquals("many-to-many", property.getChildNodes().item(1).getNodeName());
        assertEquals(2, property.getChildNodes().item(1).getAttributes().getLength());
        assertEquals("CompanyId", property.getChildNodes().item(1).getAttributes().getNamedItem("column")
                .getNodeValue());
        assertEquals("test.model.Company", property.getChildNodes().item(1).getAttributes().getNamedItem("class")
                .getNodeValue());
        assertEquals(0, property.getChildNodes().item(1).getChildNodes().getLength());

    }

    public void testGenerateCodeForUniDirectionalOneToManyXmlHibernateCollectionGeneratorContext()
            throws NotSupportedClassException {

        CodeGeneratorConfigurationProvider codeGeneratorConfigurationProvider = new CodeGeneratorConfigurationProvider();
        CodeGeneratorConfiguration codeGeneratorConfiguration = codeGeneratorConfigurationProvider
                .getCodeGeneratorConfiguration();

        UniDirectional anUniDirectional = new UniDirectional(
                "fieldName",
                new ListType(new CompositeType()),
                ClassFinder.find("test.model.Person", codeGeneratorConfiguration.getClassList(), new JavaProjectType()),
                ClassFinder
                        .find("test.model.Address", codeGeneratorConfiguration.getClassList(), new JavaProjectType()));
        OneToMany aOneToMany = new OneToMany();

        XmlNHibernateMappingGeneratorContext anHibernateMappingGeneratorContext = generateXmlNHibernateMappingGeneratorContext(new OrmMapping(
                ClassFinder.find("test.model.Person", codeGeneratorConfiguration.getClassList(), new JavaProjectType()),
                "PersonId"));

        XmlHibernateCollectionGeneratorContext xmlHibernateCollectionGeneratorContext = new XmlHibernateCollectionGeneratorContext(
                anHibernateMappingGeneratorContext.getXmlMapping(), (CollectionType) anUniDirectional.getVarTypeA(),
                anHibernateMappingGeneratorContext.getOrmMapping(), codeGeneratorConfiguration);

        XmlHibernateCollectionGenerator xmlHibernateCollectionGenerator = new XmlHibernateCollectionGenerator();
        xmlHibernateCollectionGenerator.generateCodeFor(anUniDirectional, aOneToMany,
                xmlHibernateCollectionGeneratorContext);

        Element property = xmlHibernateCollectionGeneratorContext.getProperty();

        /** list tag */
        assertEquals("list", property.getNodeName());
        assertEquals(2, property.getAttributes().getLength());
        assertEquals("fieldName", property.getAttributes().getNamedItem("name").getNodeValue());
        assertEquals("Person_Address", property.getAttributes().getNamedItem("table").getNodeValue());
        /** list tag children */
        assertEquals(3, property.getChildNodes().getLength());
        /** first list child: key */
        assertEquals("key", property.getChildNodes().item(0).getNodeName());
        assertEquals(1, property.getChildNodes().item(0).getAttributes().getLength());
        assertEquals("PersonId", property.getElementsByTagName("key").item(0).getAttributes().getNamedItem("column")
                .getNodeValue());
        assertEquals(0, property.getChildNodes().item(0).getChildNodes().getLength());

        /** second list child: list-index */
        assertEquals("list-index", property.getChildNodes().item(1).getNodeName());
        assertEquals(1, property.getChildNodes().item(1).getAttributes().getLength());
        assertEquals("indx", property.getElementsByTagName("list-index").item(0).getAttributes().getNamedItem("column")
                .getNodeValue());
        assertEquals(0, property.getChildNodes().item(1).getChildNodes().getLength());

        /** third list child: many-to-many */
        assertEquals("many-to-many", property.getChildNodes().item(2).getNodeName());
        assertEquals(3, property.getElementsByTagName("many-to-many").item(0).getAttributes().getLength());
        assertEquals("test.model.Address", property.getElementsByTagName("many-to-many").item(0).getAttributes()
                .getNamedItem("class").getNodeValue());
        assertEquals("AddressId", property.getElementsByTagName("many-to-many").item(0).getAttributes().getNamedItem(
                "column").getNodeValue());
        assertEquals("true", property.getElementsByTagName("many-to-many").item(0).getAttributes().getNamedItem(
                "unique").getNodeValue());
        assertEquals(0, property.getChildNodes().item(2).getChildNodes().getLength());

    }

    public void testGenerateCodeForUniDirectionalManyToManyXmlHibernateCollectionGeneratorContext()
            throws NotSupportedClassException {

        CodeGeneratorConfigurationProvider codeGeneratorConfigurationProvider = new CodeGeneratorConfigurationProvider();
        CodeGeneratorConfiguration codeGeneratorConfiguration = codeGeneratorConfigurationProvider
                .getCodeGeneratorConfiguration();

        UniDirectional anUniDirectional = new UniDirectional(
                "fieldNameAA",
                new ListType(new CompositeType()),
                ClassFinder.find("test.model.Person", codeGeneratorConfiguration.getClassList(), new JavaProjectType()),
                ClassFinder
                        .find("test.model.Address", codeGeneratorConfiguration.getClassList(), new JavaProjectType()));
        ManyToMany aManyToMany = new ManyToMany();

        XmlNHibernateMappingGeneratorContext anHibernateMappingGeneratorContext = generateXmlNHibernateMappingGeneratorContext(new OrmMapping(
                ClassFinder.find("test.model.Person", codeGeneratorConfiguration.getClassList(), new JavaProjectType()),
                "PersonId"));

        XmlHibernateCollectionGeneratorContext xmlHibernateCollectionGeneratorContext = new XmlHibernateCollectionGeneratorContext(
                anHibernateMappingGeneratorContext.getXmlMapping(), (CollectionType) anUniDirectional.getVarTypeA(),
                anHibernateMappingGeneratorContext.getOrmMapping(), codeGeneratorConfiguration);

        XmlHibernateCollectionGenerator xmlHibernateCollectionGenerator = new XmlHibernateCollectionGenerator();
        xmlHibernateCollectionGenerator.generateCodeFor(anUniDirectional, aManyToMany,
                xmlHibernateCollectionGeneratorContext);

        Element property = xmlHibernateCollectionGeneratorContext.getProperty();

        assertEquals(3, property.getChildNodes().getLength());
        assertEquals("list", property.getTagName());
        assertEquals("fieldNameAA", property.getAttributes().getNamedItem("name").getNodeValue());
        assertEquals("Person_Address", property.getAttributes().getNamedItem("table").getNodeValue());

        /** first list child: key */
        assertEquals("key", property.getChildNodes().item(0).getNodeName());
        assertEquals(1, property.getChildNodes().item(0).getAttributes().getLength());
        assertEquals("PersonId", property.getChildNodes().item(0).getAttributes().getNamedItem("column").getNodeValue());
        assertEquals(0, property.getChildNodes().item(0).getChildNodes().getLength());

        /** second list child: list-index */
        assertEquals("list-index", property.getChildNodes().item(1).getNodeName());
        assertEquals(1, property.getElementsByTagName("list-index").item(0).getAttributes().getLength());
        assertEquals("indx", property.getElementsByTagName("list-index").item(0).getAttributes().getNamedItem("column")
                .getNodeValue());
        assertEquals(0, property.getChildNodes().item(1).getChildNodes().getLength());

        /** third list child: many-to-many */
        assertEquals("many-to-many", property.getChildNodes().item(2).getNodeName());
        assertEquals(2, property.getElementsByTagName("many-to-many").item(0).getAttributes().getLength());
        assertEquals("test.model.Address", property.getElementsByTagName("many-to-many").item(0).getAttributes()
                .getNamedItem("class").getNodeValue());
        assertEquals("AddressId", property.getElementsByTagName("many-to-many").item(0).getAttributes().getNamedItem(
                "column").getNodeValue());
        assertEquals(0, property.getChildNodes().item(2).getChildNodes().getLength());

    }

    public void testGenerateCodeForArrayTypeXmlHibernateCollectionGeneratorContext() throws NotSupportedClassException {

        CodeGeneratorConfigurationProvider codeGeneratorConfigurationProvider = new CodeGeneratorConfigurationProvider();
        CodeGeneratorConfiguration codeGeneratorConfiguration = codeGeneratorConfigurationProvider
                .getCodeGeneratorConfiguration();

        OrmMapping ormMapping = new OrmMapping(ClassFinder.find("test.model.Address", codeGeneratorConfiguration
                .getClassList(), new JavaProjectType()), "addressId");
        XmlNHibernateMappingGeneratorContext xmlNHibernateMappingGeneratorContext = generateXmlNHibernateMappingGeneratorContext(ormMapping);
        XmlHibernateCollectionGeneratorContext hibernateCollectionGeneratorContext = new XmlHibernateCollectionGeneratorContext(
                xmlNHibernateMappingGeneratorContext.getXmlMapping(), new ArrayType(new BasicType(ClassFinder.find(
                        "test.model.House", codeGeneratorConfiguration.getClassList(), new JavaProjectType())),
                        new JavaProjectType()), ormMapping, codeGeneratorConfiguration);
        XmlHibernateCollectionGenerator hibernateCollectionGenerator = new XmlHibernateCollectionGenerator();

        hibernateCollectionGenerator.generateCodeFor((ArrayType) hibernateCollectionGeneratorContext
                .getCollectionType(), hibernateCollectionGeneratorContext);

        Element property = hibernateCollectionGeneratorContext.getProperty();

        assertEquals(1, property.getChildNodes().getLength());
        assertEquals("array", property.getTagName());
        assertEquals(0, property.getAttributes().getLength());

        assertEquals("list-index", property.getChildNodes().item(0).getNodeName());
        assertEquals(1, property.getChildNodes().item(0).getAttributes().getLength());
        assertEquals("indx", property.getChildNodes().item(0).getAttributes().getNamedItem("column").getNodeValue());

        assertEquals(0, property.getChildNodes().item(0).getChildNodes().getLength());

    }

    public void testGenerateCodeForListTypeXmlHibernateCollectionGeneratorContext() throws NotSupportedClassException {

        CodeGeneratorConfigurationProvider codeGeneratorConfigurationProvider = new CodeGeneratorConfigurationProvider();
        CodeGeneratorConfiguration codeGeneratorConfiguration = codeGeneratorConfigurationProvider
                .getCodeGeneratorConfiguration();

        OrmMapping ormMapping = new OrmMapping(ClassFinder.find("test.model.Address", codeGeneratorConfiguration
                .getClassList(), new JavaProjectType()), "addressId");
        XmlNHibernateMappingGeneratorContext xmlNHibernateMappingGeneratorContext = generateXmlNHibernateMappingGeneratorContext(ormMapping);
        XmlHibernateCollectionGeneratorContext hibernateCollectionGeneratorContext = new XmlHibernateCollectionGeneratorContext(
                xmlNHibernateMappingGeneratorContext.getXmlMapping(), new ListType(new BasicType(ClassFinder.find(
                        "test.model.House", codeGeneratorConfiguration.getClassList(), new JavaProjectType()))),
                ormMapping, codeGeneratorConfiguration);
        XmlHibernateCollectionGenerator hibernateCollectionGenerator = new XmlHibernateCollectionGenerator();

        hibernateCollectionGenerator
                .generateCodeFor((ListType) hibernateCollectionGeneratorContext.getCollectionType(),
                        hibernateCollectionGeneratorContext);

        Element property = hibernateCollectionGeneratorContext.getProperty();

        assertEquals(1, property.getChildNodes().getLength());
        assertEquals("list", property.getTagName());
        assertEquals(0, property.getAttributes().getLength());

        assertEquals("list-index", property.getChildNodes().item(0).getNodeName());
        assertEquals(1, property.getChildNodes().item(0).getAttributes().getLength());
        assertEquals("indx", property.getChildNodes().item(0).getAttributes().getNamedItem("column").getNodeValue());

        assertEquals(0, property.getChildNodes().item(0).getChildNodes().getLength());

    }

    public void testGenerateCodeForMapTypeXmlHibernateCollectionGeneratorContext() throws NotSupportedClassException {

        CodeGeneratorConfigurationProvider codeGeneratorConfigurationProvider = new CodeGeneratorConfigurationProvider();
        CodeGeneratorConfiguration codeGeneratorConfiguration = codeGeneratorConfigurationProvider
                .getCodeGeneratorConfiguration();

        OrmMapping ormMapping = new OrmMapping(ClassFinder.find("test.model.Address", codeGeneratorConfiguration
                .getClassList(), new JavaProjectType()), "addressId");
        XmlNHibernateMappingGeneratorContext xml = generateXmlNHibernateMappingGeneratorContext(ormMapping);
        XmlHibernateCollectionGeneratorContext hibernateCollectionGeneratorContext = new XmlHibernateCollectionGeneratorContext(
                xml.getXmlMapping(), new MapType(new BasicType(ClassFinder.find("test.model.Address",
                        codeGeneratorConfiguration.getClassList(), new JavaProjectType())), new EntityReference(
                        ClassFinder.find("test.model.House", codeGeneratorConfiguration.getClassList(),
                                new JavaProjectType()))), ormMapping, codeGeneratorConfiguration);
        XmlHibernateCollectionGenerator hibernateCollectionGenerator = new XmlHibernateCollectionGenerator();

        hibernateCollectionGenerator.generateCodeFor((MapType) hibernateCollectionGeneratorContext.getCollectionType(),
                hibernateCollectionGeneratorContext);

        Element property = hibernateCollectionGeneratorContext.getProperty();

        assertEquals(1, property.getChildNodes().getLength());
        assertEquals("map", property.getTagName());
        assertEquals(0, property.getAttributes().getLength());

        assertEquals("map-key", property.getChildNodes().item(0).getNodeName());
        assertEquals(0, property.getChildNodes().item(0).getChildNodes().getLength());
        assertEquals(1, property.getChildNodes().item(0).getAttributes().getLength());
        assertEquals("type", property.getChildNodes().item(0).getAttributes().item(0).getNodeName());
        assertEquals("test.model.Address", property.getChildNodes().item(0).getAttributes().item(0).getNodeValue());

    }

    public void testGenerateCodeForSetTypeXmlHibernateCollectionGeneratorContext() throws NotSupportedClassException {

        CodeGeneratorConfigurationProvider codeGeneratorConfigurationProvider = new CodeGeneratorConfigurationProvider();
        CodeGeneratorConfiguration codeGeneratorConfiguration = codeGeneratorConfigurationProvider
                .getCodeGeneratorConfiguration();

        OrmMapping ormMapping = new OrmMapping(ClassFinder.find("test.model.Address", codeGeneratorConfiguration
                .getClassList(), new JavaProjectType()), "addressId");
        XmlNHibernateMappingGeneratorContext xmlNHibernateMappingGeneratorContext = generateXmlNHibernateMappingGeneratorContext(ormMapping);
        XmlHibernateCollectionGeneratorContext hibernateCollectionGeneratorContext = new XmlHibernateCollectionGeneratorContext(
                xmlNHibernateMappingGeneratorContext.getXmlMapping(), new SetType(new BasicType(ClassFinder.find(
                        "test.model.House", codeGeneratorConfiguration.getClassList(), new JavaProjectType()))),
                ormMapping, codeGeneratorConfiguration);
        XmlHibernateCollectionGenerator hibernateCollectionGenerator = new XmlHibernateCollectionGenerator();

        hibernateCollectionGenerator.generateCodeFor((SetType) hibernateCollectionGeneratorContext.getCollectionType(),
                hibernateCollectionGeneratorContext);

        Element property = hibernateCollectionGeneratorContext.getProperty();

        assertEquals(0, property.getChildNodes().getLength());
        assertEquals("set", property.getTagName());
        assertEquals(0, property.getAttributes().getLength());

    }

    public void testGenerateIndexCodeForMapTypeBasicTypeXmlHibernateCollectionGeneratorContext()
            throws NotSupportedClassException {

        CodeGeneratorConfigurationProvider codeGeneratorConfigurationProvider = new CodeGeneratorConfigurationProvider();
        CodeGeneratorConfiguration codeGeneratorConfiguration = codeGeneratorConfigurationProvider
                .getCodeGeneratorConfiguration();

        OrmMapping ormMapping = new OrmMapping(ClassFinder.find("test.model.Address", codeGeneratorConfiguration
                .getClassList(), new JavaProjectType()), "addressId");
        XmlNHibernateMappingGeneratorContext xmlNHibernateMappingGeneratorContext = generateXmlNHibernateMappingGeneratorContext(ormMapping);
        XmlHibernateCollectionGeneratorContext hibernateCollectionGeneratorContext = new XmlHibernateCollectionGeneratorContext(
                xmlNHibernateMappingGeneratorContext.getXmlMapping(), new MapType(new BasicType(ClassFinder.find(
                        "test.model.Address", codeGeneratorConfiguration.getClassList(), new JavaProjectType())),
                        new EntityReference(ClassFinder.find("test.model.House", codeGeneratorConfiguration
                                .getClassList(), new JavaProjectType()))), ormMapping, codeGeneratorConfiguration);
        XmlHibernateCollectionGenerator hibernateCollectionGenerator = new XmlHibernateCollectionGenerator();
        Element property = hibernateCollectionGeneratorContext.getXmlMapping().createElement("map");
        hibernateCollectionGeneratorContext.setProperty(property);
        hibernateCollectionGenerator.generateIndexCodeFor((MapType) hibernateCollectionGeneratorContext
                .getCollectionType(), (BasicType) ((MapType) hibernateCollectionGeneratorContext.getCollectionType())
                .getIndexType(), hibernateCollectionGeneratorContext);

        BasicType basicType = (BasicType) ((MapType) hibernateCollectionGeneratorContext.getCollectionType())
                .getIndexType();

        assertEquals("map", property.getNodeName());
        assertEquals(0, property.getAttributes().getLength());

        // MapKey
        assertEquals(1, property.getChildNodes().getLength());
        assertEquals("map-key", property.getChildNodes().item(0).getNodeName());
        assertEquals(1, property.getChildNodes().item(0).getAttributes().getLength());
        assertEquals(basicType.getElementClass().getName(), property.getChildNodes().item(0).getAttributes()
                .getNamedItem("type").getNodeValue());
        assertEquals(0, property.getChildNodes().item(0).getChildNodes().getLength());

    }

    public void testGenerateIndexCodeForMapTypeCompositeTypeXmlHibernateCollectionGeneratorContext()
            throws NotSupportedClassException {

        CodeGeneratorConfigurationProvider codeGeneratorConfigurationProvider = new CodeGeneratorConfigurationProvider();
        CodeGeneratorConfiguration codeGeneratorConfiguration = codeGeneratorConfigurationProvider
                .getCodeGeneratorConfiguration();

        OrmMapping ormMapping = new OrmMapping(ClassFinder.find("test.model.Address", codeGeneratorConfiguration
                .getClassList(), new JavaProjectType()), "addressId");
        XmlNHibernateMappingGeneratorContext xmlNHibernateMappingGeneratorContext = generateXmlNHibernateMappingGeneratorContext(ormMapping);
        XmlHibernateCollectionGeneratorContext hibernateCollectionGeneratorContext = new XmlHibernateCollectionGeneratorContext(
                xmlNHibernateMappingGeneratorContext.getXmlMapping(), new MapType(new CompositeType(),
                        new EntityReference(ClassFinder.find("test.model.House", codeGeneratorConfiguration
                                .getClassList(), new JavaProjectType()))), ormMapping, codeGeneratorConfiguration);
        XmlHibernateCollectionGenerator hibernateCollectionGenerator = new XmlHibernateCollectionGenerator();
        Element property = hibernateCollectionGeneratorContext.getXmlMapping().createElement("map");
        hibernateCollectionGeneratorContext.setProperty(property);
        hibernateCollectionGenerator.generateIndexCodeFor((MapType) hibernateCollectionGeneratorContext
                .getCollectionType(), (CompositeType) ((MapType) hibernateCollectionGeneratorContext
                .getCollectionType()).getIndexType(), hibernateCollectionGeneratorContext);

        CompositeType compositeType = (CompositeType) ((MapType) hibernateCollectionGeneratorContext
                .getCollectionType()).getIndexType();

        assertEquals(1, property.getChildNodes().getLength());
        assertEquals("composite-map-key", property.getChildNodes().item(0).getNodeName());
        assertEquals(1, property.getChildNodes().item(0).getAttributes().getLength());
        assertEquals(compositeType.getElementClass().getName(), property.getChildNodes().item(0).getAttributes()
                .getNamedItem("type").getNodeValue());
        assertEquals(0, property.getChildNodes().item(0).getChildNodes().getLength());

    }

    public void testGenerateIndexCodeForMapTypeEntityReferenceXmlHibernateCollectionGeneratorContext()
            throws NotSupportedClassException {

        CodeGeneratorConfigurationProvider codeGeneratorConfigurationProvider = new CodeGeneratorConfigurationProvider();
        CodeGeneratorConfiguration codeGeneratorConfiguration = codeGeneratorConfigurationProvider
                .getCodeGeneratorConfiguration();

        OrmMapping ormMapping = new OrmMapping(ClassFinder.find("test.model.Address", codeGeneratorConfiguration
                .getClassList(), new JavaProjectType()), "addressId");
        XmlNHibernateMappingGeneratorContext xmlNHibernateMappingGeneratorContext = generateXmlNHibernateMappingGeneratorContext(ormMapping);
        XmlHibernateCollectionGeneratorContext hibernateCollectionGeneratorContext = new XmlHibernateCollectionGeneratorContext(
                xmlNHibernateMappingGeneratorContext.getXmlMapping(), new MapType(new EntityReference(ClassFinder.find(
                        "test.model.House", codeGeneratorConfiguration.getClassList(), new JavaProjectType())),
                        new EntityReference(ClassFinder.find("test.model.House", codeGeneratorConfiguration
                                .getClassList(), new JavaProjectType()))), ormMapping, codeGeneratorConfiguration);
        XmlHibernateCollectionGenerator hibernateCollectionGenerator = new XmlHibernateCollectionGenerator();
        Element property = hibernateCollectionGeneratorContext.getXmlMapping().createElement("map");
        hibernateCollectionGeneratorContext.setProperty(property);
        hibernateCollectionGenerator.generateIndexCodeFor((MapType) hibernateCollectionGeneratorContext
                .getCollectionType(), (EntityReference) ((MapType) hibernateCollectionGeneratorContext
                .getCollectionType()).getIndexType(), hibernateCollectionGeneratorContext);

        EntityReference entityReference = (EntityReference) ((MapType) hibernateCollectionGeneratorContext
                .getCollectionType()).getIndexType();

        assertEquals("map", property.getNodeName());
        assertEquals(0, property.getAttributes().getLength());

        // Map Key Many to Many

        assertEquals(1, property.getChildNodes().getLength());
        assertEquals("map-key-many-to-many", property.getChildNodes().item(0).getNodeName());
        assertEquals(1, property.getChildNodes().item(0).getAttributes().getLength());
        assertEquals(entityReference.getElementClass().getName(), property.getChildNodes().item(0).getAttributes()
                .getNamedItem("class").getNodeValue());

    }

}
