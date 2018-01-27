package com.hexacta.booster.codegeneration.persistence.configuration.nhibernate;

import junit.framework.TestCase;

import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import test.hexacta.booster.providers.CodeGeneratorConfigurationProvider;
import test.hexacta.booster.providers.ProjectConfigurationToolProvider;

import com.hexacta.booster.codegeneration.configuration.ClassFinder;
import com.hexacta.booster.codegeneration.configuration.CodeGeneratorConfiguration;
import com.hexacta.booster.codegeneration.configuration.NotSupportedClassException;
import com.hexacta.booster.codegeneration.metamodel.Class;
import com.hexacta.booster.codegeneration.persistence.ormmodel.BasicType;
import com.hexacta.booster.codegeneration.persistence.ormmodel.BiDirectional;
import com.hexacta.booster.codegeneration.persistence.ormmodel.CompositeType;
import com.hexacta.booster.codegeneration.persistence.ormmodel.EntityReference;
import com.hexacta.booster.codegeneration.persistence.ormmodel.ListType;
import com.hexacta.booster.codegeneration.persistence.ormmodel.ManyToMany;
import com.hexacta.booster.codegeneration.persistence.ormmodel.ManyToOne;
import com.hexacta.booster.codegeneration.persistence.ormmodel.MapType;
import com.hexacta.booster.codegeneration.persistence.ormmodel.OneToMany;
import com.hexacta.booster.codegeneration.persistence.ormmodel.OneToOne;
import com.hexacta.booster.codegeneration.persistence.ormmodel.OrmData;
import com.hexacta.booster.codegeneration.persistence.ormmodel.OrmDataGenerator;
import com.hexacta.booster.codegeneration.persistence.ormmodel.OrmMapping;
import com.hexacta.booster.codegeneration.persistence.ormmodel.SetType;
import com.hexacta.booster.codegeneration.persistence.ormmodel.UniDirectional;
import com.hexacta.booster.project.configuration.JavaProjectType;
import com.hexacta.booster.project.configuration.ProjectConfigurationTool;
import com.sun.org.apache.xerces.internal.dom.DocumentImpl;

/**
 * 
 */
public class XmlNHibernateMappingGeneratorTest extends TestCase {

    private static final String HIBERNATE_MAPPING = "hibernate-mapping";

    /**
     * Log4j logger.
     */

    static final Logger logger = Logger.getLogger(XmlNHibernateMappingGeneratorTest.class.getSimpleName());

    private XmlNHibernateMappingGeneratorContext generateXmlNHibernateMappingGeneratorContext(
            final OrmMapping ormMapping) {

        CodeGeneratorConfigurationProvider codeGeneratorConfigurationProvider = new CodeGeneratorConfigurationProvider();

        Document xmlMapping = new DocumentImpl();

        Element hibernateMapping = xmlMapping.createElement(HIBERNATE_MAPPING);

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

    public void testCreation() {

        XmlNHibernateMappingGenerator xmlNHibernateMappingGenerator = new XmlNHibernateMappingGenerator();
        assertNotNull(xmlNHibernateMappingGenerator);
        assertEquals(xmlNHibernateMappingGenerator.getClass().getSimpleName(), XmlNHibernateMappingGenerator.class
                .getSimpleName());

    }

    public void testGenerateXmlMappingForOrmMapping() throws NotSupportedClassException {

        /** Generated mapping */

        OrmDataGenerator ormDataGenerator = new OrmDataGenerator();
        CodeGeneratorConfiguration codeGeneratorConfiguration = new CodeGeneratorConfigurationProvider()
                .getCodeGeneratorConfiguration();
        ProjectConfigurationTool projectConfigurationTool = new ProjectConfigurationToolProvider()
                .getProjectConfigurationTool();
        OrmData ormData = ormDataGenerator.generateOrmData(codeGeneratorConfiguration, new JavaProjectType());
        XmlNHibernateMappingGenerator xmlNHibernateMappingGenerator = new XmlNHibernateMappingGenerator();
        Document generatedXmlMapping = xmlNHibernateMappingGenerator.generateXmlMappingFor(ormData
                .getMapping(codeGeneratorConfiguration.getClassList().getClass("test.model.Address")),
                codeGeneratorConfiguration, projectConfigurationTool);

        assertEquals(1, generatedXmlMapping.getChildNodes().getLength());
        assertEquals(1, generatedXmlMapping.getElementsByTagName("class").getLength());
        assertEquals(5, generatedXmlMapping.getElementsByTagName("class").item(0).getChildNodes().getLength());

        /** Hibernate Mapping Element */

        assertEquals(1, generatedXmlMapping.getElementsByTagName(HIBERNATE_MAPPING).getLength());
        assertEquals(1, generatedXmlMapping.getElementsByTagName(HIBERNATE_MAPPING).item(0).getChildNodes().getLength());
        assertEquals(5, generatedXmlMapping.getElementsByTagName(HIBERNATE_MAPPING).item(0).getAttributes().getLength());
        assertEquals("field", generatedXmlMapping.getElementsByTagName(HIBERNATE_MAPPING).item(0).getAttributes()
                .getNamedItem("default-access").getNodeValue());
        assertEquals("save-update", generatedXmlMapping.getElementsByTagName(HIBERNATE_MAPPING).item(0).getAttributes()
                .getNamedItem("default-cascade").getNodeValue());
        assertEquals("urn:nhibernate-mapping-2.2", generatedXmlMapping.getElementsByTagName(HIBERNATE_MAPPING).item(0)
                .getAttributes().getNamedItem("xmlns").getNodeValue());
        assertEquals("Model", generatedXmlMapping.getElementsByTagName(HIBERNATE_MAPPING).item(0).getAttributes()
                .getNamedItem("assembly").getNodeValue());
        assertEquals("test.model", generatedXmlMapping.getElementsByTagName(HIBERNATE_MAPPING).item(0).getAttributes()
                .getNamedItem("namespace").getNodeValue());

        /** Class Element */

        assertEquals(1, generatedXmlMapping.getElementsByTagName("class").getLength());
        assertEquals(5, generatedXmlMapping.getElementsByTagName("class").item(0).getChildNodes().getLength());
        assertEquals(2, generatedXmlMapping.getElementsByTagName("class").item(0).getAttributes().getLength());
        assertEquals("test.model.Address", generatedXmlMapping.getElementsByTagName("class").item(0).getAttributes()
                .getNamedItem("name").getNodeValue());
        assertEquals("Address", generatedXmlMapping.getElementsByTagName("class").item(0).getAttributes().getNamedItem(
                "table").getNodeValue());

        /** Id Element */

        assertEquals(1, generatedXmlMapping.getElementsByTagName("id").getLength());
        assertEquals(1, generatedXmlMapping.getElementsByTagName("id").item(0).getChildNodes().getLength());
        assertEquals(2, generatedXmlMapping.getElementsByTagName("id").item(0).getAttributes().getLength());
        assertEquals("addressId", generatedXmlMapping.getElementsByTagName("id").item(0).getAttributes().getNamedItem(
                "name").getNodeValue());
        assertEquals("ADDRESSID", generatedXmlMapping.getElementsByTagName("id").item(0).getAttributes().getNamedItem(
                "column").getNodeValue());

        /** Property Elements */

        assertEquals(2, generatedXmlMapping.getElementsByTagName("property").getLength());
        assertEquals(0, generatedXmlMapping.getElementsByTagName("property").item(0).getChildNodes().getLength());
        assertEquals(1, generatedXmlMapping.getElementsByTagName("property").item(0).getAttributes().getLength());
        if (generatedXmlMapping.getElementsByTagName("property").item(0).getAttributes().getNamedItem("name")
                .getNodeValue().equals("name")) {
            assertEquals("name", generatedXmlMapping.getElementsByTagName("property").item(0).getAttributes()
                    .getNamedItem("name").getNodeValue());
            assertEquals("number", generatedXmlMapping.getElementsByTagName("property").item(1).getAttributes()
                    .getNamedItem("name").getNodeValue());
        } else {
            assertEquals("name", generatedXmlMapping.getElementsByTagName("property").item(1).getAttributes()
                    .getNamedItem("name").getNodeValue());
            assertEquals("number", generatedXmlMapping.getElementsByTagName("property").item(0).getAttributes()
                    .getNamedItem("name").getNodeValue());
        }

        /** Set element */

        assertEquals(1, generatedXmlMapping.getElementsByTagName("set").getLength());
        assertEquals(2, generatedXmlMapping.getElementsByTagName("set").item(0).getChildNodes().getLength());
        assertEquals(2, generatedXmlMapping.getElementsByTagName("set").item(0).getAttributes().getLength());
        assertEquals("true", generatedXmlMapping.getElementsByTagName("set").item(0).getAttributes().getNamedItem(
                "inverse").getNodeValue());
        assertEquals("people", generatedXmlMapping.getElementsByTagName("set").item(0).getAttributes().getNamedItem(
                "name").getNodeValue());

        /** Key Element */

        assertEquals(1, generatedXmlMapping.getElementsByTagName("key").getLength());
        assertEquals(0, generatedXmlMapping.getElementsByTagName("key").item(0).getChildNodes().getLength());
        assertEquals(1, generatedXmlMapping.getElementsByTagName("key").item(0).getAttributes().getLength());
        assertEquals("AddressId", generatedXmlMapping.getElementsByTagName("key").item(0).getAttributes().getNamedItem(
                "column").getNodeValue());

        /** OneToMany Element */

        assertEquals(1, generatedXmlMapping.getElementsByTagName("one-to-many").getLength());
        assertEquals(0, generatedXmlMapping.getElementsByTagName("one-to-many").item(0).getChildNodes().getLength());
        assertEquals(1, generatedXmlMapping.getElementsByTagName("one-to-many").item(0).getAttributes().getLength());
        assertEquals("test.model.Person", generatedXmlMapping.getElementsByTagName("one-to-many").item(0)
                .getAttributes().getNamedItem("class").getNodeValue());

        /** OneToOne Element */

        assertEquals(1, generatedXmlMapping.getElementsByTagName("one-to-one").getLength());
        assertEquals(0, generatedXmlMapping.getElementsByTagName("one-to-one").item(0).getChildNodes().getLength());
        assertEquals(2, generatedXmlMapping.getElementsByTagName("one-to-one").item(0).getAttributes().getLength());
        assertEquals("house", generatedXmlMapping.getElementsByTagName("one-to-one").item(0).getAttributes()
                .getNamedItem("name").getNodeValue());
        assertEquals("address", generatedXmlMapping.getElementsByTagName("one-to-one").item(0).getAttributes()
                .getNamedItem("property-ref").getNodeValue());

    }

    public void testGenerateIdElement() {

        /** Actual Id */

        CodeGeneratorConfigurationProvider codeGeneratorConfigurationProvider = new CodeGeneratorConfigurationProvider();
        CodeGeneratorConfiguration aCodeGeneratorConfiguration = codeGeneratorConfigurationProvider
                .getCodeGeneratorConfiguration();
        XmlNHibernateMappingGenerator xmlNHibernateMappingGenerator = new XmlNHibernateMappingGenerator();
        OrmMapping ormMapping = new OrmMapping(aCodeGeneratorConfiguration.getClassList()
                .getClass("test.model.Address"), "addressId");
        XmlNHibernateMappingGeneratorContext xmlNHibernateMappingGeneratorContext = generateXmlNHibernateMappingGeneratorContext(ormMapping);
        xmlNHibernateMappingGenerator.generateIdElement(xmlNHibernateMappingGeneratorContext);
        Document generatedXmlMapping = xmlNHibernateMappingGeneratorContext.getXmlMapping();

        /** Expected Id */

        assertEquals(1, generatedXmlMapping.getElementsByTagName("id").getLength());
        assertEquals(1, generatedXmlMapping.getElementsByTagName("id").item(0).getChildNodes().getLength());
        assertEquals(2, generatedXmlMapping.getElementsByTagName("id").item(0).getAttributes().getLength());
        assertEquals("addressId", generatedXmlMapping.getElementsByTagName("id").item(0).getAttributes().getNamedItem(
                "name").getNodeValue());
        assertEquals("ADDRESSID", generatedXmlMapping.getElementsByTagName("id").item(0).getAttributes().getNamedItem(
                "column").getNodeValue());

    }

    public void testSetToGenerateCodeForSubMapping() {

        /** Actual */
        CodeGeneratorConfigurationProvider codeGeneratorConfigurationProvider = new CodeGeneratorConfigurationProvider();
        CodeGeneratorConfiguration aCodeGeneratorConfiguration = codeGeneratorConfigurationProvider
                .getCodeGeneratorConfiguration();

        XmlNHibernateMappingGenerator xmlNHibernateMappingGenerator = new XmlNHibernateMappingGenerator();
        OrmMapping ormMapping = new OrmMapping(aCodeGeneratorConfiguration.getClassList()
                .getClass("test.model.Student"), "personId");
        OrmMapping subOrmMapping = new OrmMapping(aCodeGeneratorConfiguration.getClassList().getClass(
                "test.model.Student"), "personId");

        XmlNHibernateMappingGeneratorContext xmlNHibernateMappingGeneratorContext = generateXmlNHibernateMappingGeneratorContext(ormMapping);
        xmlNHibernateMappingGenerator.setToGenerateCodeForSubMapping(xmlNHibernateMappingGeneratorContext,
                subOrmMapping);

        /** Expected */

        Document xmlMapping = xmlNHibernateMappingGeneratorContext.getXmlMapping();

        assertEquals(1, xmlMapping.getElementsByTagName("subclass").getLength());
        assertEquals(subOrmMapping.getMappingClass().getName(), xmlMapping.getElementsByTagName("subclass").item(0)
                .getAttributes().getNamedItem("name").getNodeValue());
        assertEquals(subOrmMapping.getMappingClass().getSimpleName().subSequence(0, 3).toString().toUpperCase(),
                xmlMapping.getElementsByTagName("subclass").item(0).getAttributes().getNamedItem("discriminator-value")
                        .getNodeValue());
        assertEquals("test.model.Student", xmlNHibernateMappingGeneratorContext.getOrmMapping().getMappingClass()
                .getName());

    }

    public void testGenerateMappingForXmlHibernateMappingContext() throws NotSupportedClassException {

        /** Generated mapping */

        OrmDataGenerator ormDataGenerator = new OrmDataGenerator();
        CodeGeneratorConfiguration codeGeneratorConfiguration = new CodeGeneratorConfigurationProvider()
                .getCodeGeneratorConfiguration();
        OrmData ormData = ormDataGenerator.generateOrmData(codeGeneratorConfiguration, new JavaProjectType());
        XmlNHibernateMappingGenerator xmlNHibernateMappingGenerator = new XmlNHibernateMappingGenerator();
        Document generatedXmlMapping = xmlNHibernateMappingGenerator
                .generateMappingFor(generateXmlNHibernateMappingGeneratorContext(ormData
                        .getMapping(codeGeneratorConfiguration.getClassList().getClass("test.model.Address"))));

        /** Expected */

        /** Id Element */

        assertEquals(1, generatedXmlMapping.getElementsByTagName("id").getLength());
        assertEquals(1, generatedXmlMapping.getElementsByTagName("id").item(0).getChildNodes().getLength());
        assertEquals(2, generatedXmlMapping.getElementsByTagName("id").item(0).getAttributes().getLength());
        assertEquals("addressId", generatedXmlMapping.getElementsByTagName("id").item(0).getAttributes().getNamedItem(
                "name").getNodeValue());
        assertEquals("ADDRESSID", generatedXmlMapping.getElementsByTagName("id").item(0).getAttributes().getNamedItem(
                "column").getNodeValue());

        /** Property Elements */

        assertEquals(2, generatedXmlMapping.getElementsByTagName("property").getLength());
        assertEquals(0, generatedXmlMapping.getElementsByTagName("property").item(0).getChildNodes().getLength());
        assertEquals(1, generatedXmlMapping.getElementsByTagName("property").item(0).getAttributes().getLength());
        if (generatedXmlMapping.getElementsByTagName("property").item(0).getAttributes().getNamedItem("name")
                .getNodeValue().equals("name")) {
            assertEquals("name", generatedXmlMapping.getElementsByTagName("property").item(0).getAttributes()
                    .getNamedItem("name").getNodeValue());
            assertEquals("number", generatedXmlMapping.getElementsByTagName("property").item(1).getAttributes()
                    .getNamedItem("name").getNodeValue());
        } else {
            assertEquals("name", generatedXmlMapping.getElementsByTagName("property").item(1).getAttributes()
                    .getNamedItem("name").getNodeValue());
            assertEquals("number", generatedXmlMapping.getElementsByTagName("property").item(0).getAttributes()
                    .getNamedItem("name").getNodeValue());
        }

        /** Set element */

        assertEquals(1, generatedXmlMapping.getElementsByTagName("set").getLength());
        assertEquals(2, generatedXmlMapping.getElementsByTagName("set").item(0).getChildNodes().getLength());
        assertEquals(2, generatedXmlMapping.getElementsByTagName("set").item(0).getAttributes().getLength());
        assertEquals("true", generatedXmlMapping.getElementsByTagName("set").item(0).getAttributes().getNamedItem(
                "inverse").getNodeValue());
        assertEquals("people", generatedXmlMapping.getElementsByTagName("set").item(0).getAttributes().getNamedItem(
                "name").getNodeValue());

        /** Key Element */

        assertEquals(1, generatedXmlMapping.getElementsByTagName("key").getLength());
        assertEquals(0, generatedXmlMapping.getElementsByTagName("key").item(0).getChildNodes().getLength());
        assertEquals(1, generatedXmlMapping.getElementsByTagName("key").item(0).getAttributes().getLength());
        assertEquals("AddressId", generatedXmlMapping.getElementsByTagName("key").item(0).getAttributes().getNamedItem(
                "column").getNodeValue());

        /** OneToMany Element */

        assertEquals(1, generatedXmlMapping.getElementsByTagName("one-to-many").getLength());
        assertEquals(0, generatedXmlMapping.getElementsByTagName("one-to-many").item(0).getChildNodes().getLength());
        assertEquals(1, generatedXmlMapping.getElementsByTagName("one-to-many").item(0).getAttributes().getLength());
        assertEquals("test.model.Person", generatedXmlMapping.getElementsByTagName("one-to-many").item(0)
                .getAttributes().getNamedItem("class").getNodeValue());

        /** OneToOne Element */

        assertEquals(1, generatedXmlMapping.getElementsByTagName("one-to-one").getLength());
        assertEquals(0, generatedXmlMapping.getElementsByTagName("one-to-one").item(0).getChildNodes().getLength());
        assertEquals(2, generatedXmlMapping.getElementsByTagName("one-to-one").item(0).getAttributes().getLength());
        assertEquals("house", generatedXmlMapping.getElementsByTagName("one-to-one").item(0).getAttributes()
                .getNamedItem("name").getNodeValue());
        assertEquals("address", generatedXmlMapping.getElementsByTagName("one-to-one").item(0).getAttributes()
                .getNamedItem("property-ref").getNodeValue());

    }

    public void testGenereteCodeForBidirectionalOneToOne() {

        CodeGeneratorConfigurationProvider codeGeneratorConfigurationProvider = new CodeGeneratorConfigurationProvider();
        CodeGeneratorConfiguration aCodeGeneratorConfiguration = codeGeneratorConfigurationProvider
                .getCodeGeneratorConfiguration();
        OneToOne oneToOne = new OneToOne();
        Class classNameA = aCodeGeneratorConfiguration.getClassList().getClass("test.model.Address");
        Class classNameB = aCodeGeneratorConfiguration.getClassList().getClass("test.model.House");

        OrmMapping ormMappingA = new OrmMapping(classNameA, "addressId");
        OrmMapping ormMappingB = new OrmMapping(classNameB, "houseId");

        XmlNHibernateMappingGeneratorContext contextClassA = generateXmlNHibernateMappingGeneratorContext(ormMappingA);
        XmlNHibernateMappingGeneratorContext contextClassB = generateXmlNHibernateMappingGeneratorContext(ormMappingB);

        BiDirectional bidirectional = new BiDirectional("house", "address", new EntityReference(classNameB),
                new EntityReference(classNameA), classNameA, classNameB);

        XmlNHibernateMappingGenerator xmlNHibernateMappingGenerator = new XmlNHibernateMappingGenerator();
        xmlNHibernateMappingGenerator.generateCodeFor(bidirectional, oneToOne, contextClassA);

        Document generatedXmlMappingClassA = contextClassA.getXmlMapping();

        assertEquals(1, generatedXmlMappingClassA.getElementsByTagName("many-to-one").getLength());
        assertEquals(0, generatedXmlMappingClassA.getElementsByTagName("many-to-one").item(0).getChildNodes()
                .getLength());
        assertEquals(3, generatedXmlMappingClassA.getElementsByTagName("many-to-one").item(0).getAttributes()
                .getLength());
        assertEquals("houseId", generatedXmlMappingClassA.getElementsByTagName("many-to-one").item(0).getAttributes()
                .getNamedItem("column").getNodeValue());
        assertEquals("house", generatedXmlMappingClassA.getElementsByTagName("many-to-one").item(0).getAttributes()
                .getNamedItem("name").getNodeValue());
        assertEquals("true", generatedXmlMappingClassA.getElementsByTagName("many-to-one").item(0).getAttributes()
                .getNamedItem("unique").getNodeValue());

        xmlNHibernateMappingGenerator.generateCodeFor(bidirectional, oneToOne, contextClassB);
        Document generatedXmlMappingClassB = contextClassB.getXmlMapping();

        assertEquals(1, generatedXmlMappingClassB.getElementsByTagName("one-to-one").getLength());
        assertEquals(0, generatedXmlMappingClassB.getElementsByTagName("one-to-one").item(0).getChildNodes()
                .getLength());
        assertEquals(2, generatedXmlMappingClassB.getElementsByTagName("one-to-one").item(0).getAttributes()
                .getLength());
        assertEquals("house", generatedXmlMappingClassB.getElementsByTagName("one-to-one").item(0).getAttributes()
                .getNamedItem("property-ref").getNodeValue());
        assertEquals("address", generatedXmlMappingClassB.getElementsByTagName("one-to-one").item(0).getAttributes()
                .getNamedItem("name").getNodeValue());

    }

    public void testGenerateCodeForBidirectionalManyToOne() {

        CodeGeneratorConfigurationProvider codeGeneratorConfigurationProvider = new CodeGeneratorConfigurationProvider();
        CodeGeneratorConfiguration aCodeGeneratorConfiguration = codeGeneratorConfigurationProvider
                .getCodeGeneratorConfiguration();

        Class classNameA = aCodeGeneratorConfiguration.getClassList().getClass("test.model.Person");
        Class classNameB = aCodeGeneratorConfiguration.getClassList().getClass("test.model.Address");
        ManyToOne manyToOne = new ManyToOne();
        BiDirectional bidirectional = new BiDirectional("address", "people", new EntityReference(classNameA),
                new SetType(new EntityReference(classNameB)), classNameA, classNameB);

        OrmMapping ormMappingA = new OrmMapping(classNameA, "personId");
        OrmMapping ormMappingB = new OrmMapping(classNameB, "addressId");

        XmlNHibernateMappingGenerator xlNHibernateMappingGenerator = new XmlNHibernateMappingGenerator();

        XmlNHibernateMappingGeneratorContext contextClassA = generateXmlNHibernateMappingGeneratorContext(ormMappingA);
        XmlNHibernateMappingGeneratorContext contextClassB = generateXmlNHibernateMappingGeneratorContext(ormMappingB);

        xlNHibernateMappingGenerator.generateCodeFor(bidirectional, manyToOne, contextClassA);

        Document generatedXmlMappingClassA = contextClassA.getXmlMapping();

        assertEquals(1, generatedXmlMappingClassA.getElementsByTagName("many-to-one").getLength());
        assertEquals(0, generatedXmlMappingClassA.getElementsByTagName("many-to-one").item(0).getChildNodes()
                .getLength());
        assertEquals(2, generatedXmlMappingClassA.getElementsByTagName("many-to-one").item(0).getAttributes()
                .getLength());
        assertEquals("addressId", generatedXmlMappingClassA.getElementsByTagName("many-to-one").item(0).getAttributes()
                .getNamedItem("column").getNodeValue());
        assertEquals("address", generatedXmlMappingClassA.getElementsByTagName("many-to-one").item(0).getAttributes()
                .getNamedItem("name").getNodeValue());

        xlNHibernateMappingGenerator.generateCodeFor(bidirectional, manyToOne, contextClassB);

        Document generatedXmlMappingClassB = contextClassB.getXmlMapping();

        assertEquals(1, generatedXmlMappingClassB.getElementsByTagName("set").getLength());
        assertEquals(2, generatedXmlMappingClassB.getElementsByTagName("set").item(0).getChildNodes().getLength());
        assertEquals(2, generatedXmlMappingClassB.getElementsByTagName("set").item(0).getAttributes().getLength());
        assertEquals("true", generatedXmlMappingClassB.getElementsByTagName("set").item(0).getAttributes()
                .getNamedItem("inverse").getNodeValue());
        assertEquals("people", generatedXmlMappingClassB.getElementsByTagName("set").item(0).getAttributes()
                .getNamedItem("name").getNodeValue());

        /** element key */
        assertEquals("key", generatedXmlMappingClassB.getElementsByTagName("set").item(0).getChildNodes().item(0)
                .getNodeName());
        assertEquals(0, generatedXmlMappingClassB.getElementsByTagName("set").item(0).getChildNodes().item(0)
                .getChildNodes().getLength());
        assertEquals(1, generatedXmlMappingClassB.getElementsByTagName("set").item(0).getChildNodes().item(0)
                .getAttributes().getLength());
        assertEquals("AddressId", generatedXmlMappingClassB.getElementsByTagName("set").item(0).getChildNodes().item(0)
                .getAttributes().getNamedItem("column").getNodeValue());

        /** element one-to-many */
        assertEquals("one-to-many", generatedXmlMappingClassB.getElementsByTagName("set").item(0).getChildNodes().item(
                1).getNodeName());
        assertEquals(0, generatedXmlMappingClassB.getElementsByTagName("set").item(0).getChildNodes().item(1)
                .getChildNodes().getLength());
        assertEquals(1, generatedXmlMappingClassB.getElementsByTagName("set").item(0).getChildNodes().item(1)
                .getAttributes().getLength());
        assertEquals("test.model.Person", generatedXmlMappingClassB.getElementsByTagName("set").item(0).getChildNodes()
                .item(1).getAttributes().getNamedItem("class").getNodeValue());

    }

    public void testGenereteCodeForBidirectionalManyToMany() {

        CodeGeneratorConfigurationProvider codeGeneratorConfigurationProvider = new CodeGeneratorConfigurationProvider();
        CodeGeneratorConfiguration aCodeGeneratorConfiguration = codeGeneratorConfigurationProvider
                .getCodeGeneratorConfiguration();
        ManyToMany manyToMany = new ManyToMany();
        Class classNameA = aCodeGeneratorConfiguration.getClassList().getClass("test.model.Company");
        Class classNameB = aCodeGeneratorConfiguration.getClassList().getClass("test.model.Employee");

        BiDirectional bidirectional = new BiDirectional("employees", "companies", new MapType(new BasicType(new Class(
                "java.lang.Integer")), new EntityReference(classNameB)), new SetType(new EntityReference(classNameA)),
                classNameA, classNameB);

        OrmMapping ormMappingA = new OrmMapping(classNameA, "companyId");
        OrmMapping ormMappingB = new OrmMapping(classNameB, "personId");

        XmlNHibernateMappingGenerator xmlNHibernateMappingGenerator = new XmlNHibernateMappingGenerator();

        XmlNHibernateMappingGeneratorContext contextClassA = generateXmlNHibernateMappingGeneratorContext(ormMappingA);
        XmlNHibernateMappingGeneratorContext contextClassB = generateXmlNHibernateMappingGeneratorContext(ormMappingB);

        xmlNHibernateMappingGenerator.generateCodeFor(bidirectional, manyToMany, contextClassA);

        Document generatedXmlMappingClassA = contextClassA.getXmlMapping();

        assertEquals(1, generatedXmlMappingClassA.getElementsByTagName("map").getLength());
        assertEquals(3, generatedXmlMappingClassA.getElementsByTagName("map").item(0).getChildNodes().getLength());
        assertEquals(2, generatedXmlMappingClassA.getElementsByTagName("map").item(0).getAttributes().getLength());
        assertEquals("employees", generatedXmlMappingClassA.getElementsByTagName("map").item(0).getAttributes()
                .getNamedItem("name").getNodeValue());
        assertEquals("Company_Employee", generatedXmlMappingClassA.getElementsByTagName("map").item(0).getAttributes()
                .getNamedItem("table").getNodeValue());

        /** element key */
        assertEquals("key", generatedXmlMappingClassA.getElementsByTagName("map").item(0).getChildNodes().item(0)
                .getNodeName());
        assertEquals(0, generatedXmlMappingClassA.getElementsByTagName("map").item(0).getChildNodes().item(0)
                .getChildNodes().getLength());
        assertEquals(1, generatedXmlMappingClassA.getElementsByTagName("map").item(0).getChildNodes().item(0)
                .getAttributes().getLength());
        assertEquals("CompanyId", generatedXmlMappingClassA.getElementsByTagName("map").item(0).getChildNodes().item(0)
                .getAttributes().getNamedItem("column").getNodeValue());

        /** element map-key */
        assertEquals("index", generatedXmlMappingClassA.getElementsByTagName("map").item(0).getChildNodes().item(1)
                .getNodeName());
        assertEquals(0, generatedXmlMappingClassA.getElementsByTagName("map").item(0).getChildNodes().item(1)
                .getChildNodes().getLength());
        assertEquals(1, generatedXmlMappingClassA.getElementsByTagName("map").item(0).getChildNodes().item(1)
                .getAttributes().getLength());
        assertEquals("java.lang.Integer", generatedXmlMappingClassA.getElementsByTagName("map").item(0).getChildNodes()
                .item(1).getAttributes().getNamedItem("type").getNodeValue());

        /** element many-to-many */
        assertEquals("many-to-many", generatedXmlMappingClassA.getElementsByTagName("map").item(0).getChildNodes()
                .item(2).getNodeName());
        assertEquals(0, generatedXmlMappingClassA.getElementsByTagName("map").item(0).getChildNodes().item(2)
                .getChildNodes().getLength());
        assertEquals(2, generatedXmlMappingClassA.getElementsByTagName("map").item(0).getChildNodes().item(2)
                .getAttributes().getLength());
        assertEquals("test.model.Employee", generatedXmlMappingClassA.getElementsByTagName("map").item(0)
                .getChildNodes().item(2).getAttributes().getNamedItem("class").getNodeValue());
        assertEquals("EmployeeId", generatedXmlMappingClassA.getElementsByTagName("map").item(0).getChildNodes()
                .item(2).getAttributes().getNamedItem("column").getNodeValue());

        xmlNHibernateMappingGenerator.generateCodeFor(bidirectional, manyToMany, contextClassB);

        Document generatedXmlMappingClassB = contextClassB.getXmlMapping();

        assertEquals(1, generatedXmlMappingClassB.getElementsByTagName("set").getLength());
        assertEquals(2, generatedXmlMappingClassB.getElementsByTagName("set").item(0).getChildNodes().getLength());
        assertEquals(3, generatedXmlMappingClassB.getElementsByTagName("set").item(0).getAttributes().getLength());
        assertEquals("true", generatedXmlMappingClassB.getElementsByTagName("set").item(0).getAttributes()
                .getNamedItem("inverse").getNodeValue());
        assertEquals("companies", generatedXmlMappingClassB.getElementsByTagName("set").item(0).getAttributes()
                .getNamedItem("name").getNodeValue());
        assertEquals("Company_Employee", generatedXmlMappingClassB.getElementsByTagName("set").item(0).getAttributes()
                .getNamedItem("table").getNodeValue());

        /** element key */
        assertEquals("key", generatedXmlMappingClassB.getElementsByTagName("set").item(0).getChildNodes().item(0)
                .getNodeName());
        assertEquals(0, generatedXmlMappingClassB.getElementsByTagName("set").item(0).getChildNodes().item(0)
                .getChildNodes().getLength());
        assertEquals(1, generatedXmlMappingClassB.getElementsByTagName("set").item(0).getChildNodes().item(0)
                .getAttributes().getLength());
        assertEquals("EmployeeId", generatedXmlMappingClassB.getElementsByTagName("set").item(0).getChildNodes()
                .item(0).getAttributes().getNamedItem("column").getNodeValue());

        /** element many to many */
        assertEquals("many-to-many", generatedXmlMappingClassB.getElementsByTagName("set").item(0).getChildNodes()
                .item(1).getNodeName());
        assertEquals(0, generatedXmlMappingClassB.getElementsByTagName("set").item(0).getChildNodes().item(1)
                .getChildNodes().getLength());
        assertEquals(2, generatedXmlMappingClassB.getElementsByTagName("set").item(0).getChildNodes().item(1)
                .getAttributes().getLength());
        assertEquals("CompanyId", generatedXmlMappingClassB.getElementsByTagName("set").item(0).getChildNodes().item(1)
                .getAttributes().getNamedItem("column").getNodeValue());
        assertEquals("test.model.Company", generatedXmlMappingClassB.getElementsByTagName("set").item(0)
                .getChildNodes().item(1).getAttributes().getNamedItem("class").getNodeValue());

    }

    public void testGenereteCodeForBidirectionalOneToMany() {

        CodeGeneratorConfigurationProvider codeGeneratorConfigurationProvider = new CodeGeneratorConfigurationProvider();
        CodeGeneratorConfiguration aCodeGeneratorConfiguration = codeGeneratorConfigurationProvider
                .getCodeGeneratorConfiguration();

        Class classNameA = aCodeGeneratorConfiguration.getClassList().getClass("test.model.Address");
        Class classNameB = aCodeGeneratorConfiguration.getClassList().getClass("test.model.Person");

        OneToMany oneToMany = new OneToMany();

        BiDirectional bidirectional = new BiDirectional("people", "address", new SetType(
                new EntityReference(classNameB)), new EntityReference(classNameA), classNameA, classNameB);

        OrmMapping ormMappingA = new OrmMapping(classNameA, "addressId");
        OrmMapping ormMappingB = new OrmMapping(classNameB, "personId");

        XmlNHibernateMappingGenerator xmlNHibernateMappingGenerator = new XmlNHibernateMappingGenerator();

        XmlNHibernateMappingGeneratorContext contextClassA = generateXmlNHibernateMappingGeneratorContext(ormMappingA);
        XmlNHibernateMappingGeneratorContext contextClassB = generateXmlNHibernateMappingGeneratorContext(ormMappingB);

        xmlNHibernateMappingGenerator.generateCodeFor(bidirectional, oneToMany, contextClassA);

        Document generatedXmlMappingClassA = contextClassA.getXmlMapping();

        assertEquals(1, generatedXmlMappingClassA.getElementsByTagName("set").getLength());
        assertEquals(2, generatedXmlMappingClassA.getElementsByTagName("set").item(0).getChildNodes().getLength());
        assertEquals(2, generatedXmlMappingClassA.getElementsByTagName("set").item(0).getAttributes().getLength());
        assertEquals("true", generatedXmlMappingClassA.getElementsByTagName("set").item(0).getAttributes()
                .getNamedItem("inverse").getNodeValue());
        assertEquals("people", generatedXmlMappingClassA.getElementsByTagName("set").item(0).getAttributes()
                .getNamedItem("name").getNodeValue());

        /** element key */
        assertEquals("key", generatedXmlMappingClassA.getElementsByTagName("set").item(0).getChildNodes().item(0)
                .getNodeName());
        assertEquals(0, generatedXmlMappingClassA.getElementsByTagName("set").item(0).getChildNodes().item(0)
                .getChildNodes().getLength());
        assertEquals(1, generatedXmlMappingClassA.getElementsByTagName("set").item(0).getChildNodes().item(0)
                .getAttributes().getLength());
        assertEquals("AddressId", generatedXmlMappingClassA.getElementsByTagName("set").item(0).getChildNodes().item(0)
                .getAttributes().getNamedItem("column").getNodeValue());

        /** element one-to-many */
        assertEquals("one-to-many", generatedXmlMappingClassA.getElementsByTagName("set").item(0).getChildNodes().item(
                1).getNodeName());
        assertEquals(0, generatedXmlMappingClassA.getElementsByTagName("set").item(0).getChildNodes().item(1)
                .getChildNodes().getLength());
        assertEquals(1, generatedXmlMappingClassA.getElementsByTagName("set").item(0).getChildNodes().item(1)
                .getAttributes().getLength());
        assertEquals("test.model.Person", generatedXmlMappingClassA.getElementsByTagName("set").item(0).getChildNodes()
                .item(1).getAttributes().getNamedItem("class").getNodeValue());

        xmlNHibernateMappingGenerator.generateCodeFor(bidirectional, oneToMany, contextClassB);

        Document generatedXmlMappingClassB = contextClassB.getXmlMapping();

        assertEquals(1, generatedXmlMappingClassB.getElementsByTagName("many-to-one").getLength());
        assertEquals(0, generatedXmlMappingClassB.getElementsByTagName("many-to-one").item(0).getChildNodes()
                .getLength());
        assertEquals(2, generatedXmlMappingClassB.getElementsByTagName("many-to-one").item(0).getAttributes()
                .getLength());
        assertEquals("addressId", generatedXmlMappingClassB.getElementsByTagName("many-to-one").item(0).getAttributes()
                .getNamedItem("column").getNodeValue());
        assertEquals("address", generatedXmlMappingClassB.getElementsByTagName("many-to-one").item(0).getAttributes()
                .getNamedItem("name").getNodeValue());

    }

    public void testGenerateCodeForUniDirectionalManyToMany() throws Exception {

        CodeGeneratorConfigurationProvider codeGeneratorConfigurationProvider = new CodeGeneratorConfigurationProvider();
        CodeGeneratorConfiguration aCodeGeneratorConfiguration = codeGeneratorConfigurationProvider
                .getCodeGeneratorConfiguration();

        UniDirectional anUniDirectional = new UniDirectional("fieldNameAA", new ListType(new CompositeType()),
                aCodeGeneratorConfiguration.getClassList().getClass("test.model.Person"), aCodeGeneratorConfiguration
                        .getClassList().getClass("test.model.Address"));
        ManyToMany aManyToMany = new ManyToMany();

        XmlNHibernateMappingGeneratorContext anHibernateMappingGeneratorContext = generateXmlNHibernateMappingGeneratorContext(new OrmMapping(
                aCodeGeneratorConfiguration.getClassList().getClass("test.model.Person"), "claseAA_id"));
        XmlNHibernateMappingGenerator anXmlNHibernateMappingGenerator = new XmlNHibernateMappingGenerator();

        anXmlNHibernateMappingGenerator.generateCodeFor(anUniDirectional, aManyToMany,
                anHibernateMappingGeneratorContext);

        Document generatedXmlMapping = anHibernateMappingGeneratorContext.getXmlMapping();

        /** general tags */
        assertEquals(1, generatedXmlMapping.getElementsByTagName(HIBERNATE_MAPPING).getLength());
        assertEquals(1, generatedXmlMapping.getElementsByTagName(HIBERNATE_MAPPING).item(0).getChildNodes().getLength());
        assertEquals(2, generatedXmlMapping.getElementsByTagName(HIBERNATE_MAPPING).item(0).getAttributes().getLength());
        assertEquals("field", generatedXmlMapping.getElementsByTagName(HIBERNATE_MAPPING).item(0).getAttributes()
                .getNamedItem("default-access").getNodeValue());
        assertEquals("save-update", generatedXmlMapping.getElementsByTagName(HIBERNATE_MAPPING).item(0).getAttributes()
                .getNamedItem("default-cascade").getNodeValue());
        /** class tag */
        assertEquals(1, generatedXmlMapping.getElementsByTagName("class").getLength());
        assertEquals(1, generatedXmlMapping.getElementsByTagName("class").item(0).getChildNodes().getLength());
        assertEquals(2, generatedXmlMapping.getElementsByTagName("class").item(0).getAttributes().getLength());
        assertEquals("test.model.Person", generatedXmlMapping.getElementsByTagName("class").item(0).getAttributes()
                .getNamedItem("name").getNodeValue());
        assertEquals("Person", generatedXmlMapping.getElementsByTagName("class").item(0).getAttributes().getNamedItem(
                "table").getNodeValue());
        /** list tag */
        assertEquals("list", generatedXmlMapping.getElementsByTagName("class").item(0).getChildNodes().item(0)
                .getNodeName());
        assertEquals("fieldNameAA", generatedXmlMapping.getElementsByTagName("class").item(0).getChildNodes().item(0)
                .getAttributes().getNamedItem("name").getNodeValue());
        assertEquals("Person_Address", generatedXmlMapping.getElementsByTagName("class").item(0).getChildNodes()
                .item(0).getAttributes().getNamedItem("table").getNodeValue());
        /** list tag children */
        assertEquals(3, generatedXmlMapping.getElementsByTagName("list").item(0).getChildNodes().getLength());
        /** first list child: key */
        assertEquals("key", generatedXmlMapping.getElementsByTagName("list").item(0).getChildNodes().item(0)
                .getNodeName());
        assertEquals(1, generatedXmlMapping.getElementsByTagName("key").item(0).getAttributes().getLength());
        assertEquals("PersonId", generatedXmlMapping.getElementsByTagName("key").item(0).getAttributes().getNamedItem(
                "column").getNodeValue());
        assertEquals(0, generatedXmlMapping.getElementsByTagName("key").item(0).getChildNodes().getLength());
        /** second list child: list-index */
        assertEquals("index", generatedXmlMapping.getElementsByTagName("list").item(0).getChildNodes().item(1)
                .getNodeName());
        assertEquals(1, generatedXmlMapping.getElementsByTagName("index").item(0).getAttributes().getLength());
        assertEquals("indx", generatedXmlMapping.getElementsByTagName("index").item(0).getAttributes().getNamedItem(
                "column").getNodeValue());
        assertEquals(0, generatedXmlMapping.getElementsByTagName("index").item(0).getChildNodes().getLength());
        /** third list child: many-to-many */
        assertEquals("many-to-many", generatedXmlMapping.getElementsByTagName("list").item(0).getChildNodes().item(2)
                .getNodeName());
        assertEquals(2, generatedXmlMapping.getElementsByTagName("many-to-many").item(0).getAttributes().getLength());
        assertEquals("test.model.Address", generatedXmlMapping.getElementsByTagName("many-to-many").item(0)
                .getAttributes().getNamedItem("class").getNodeValue());
        assertEquals("AddressId", generatedXmlMapping.getElementsByTagName("many-to-many").item(0).getAttributes()
                .getNamedItem("column").getNodeValue());
        assertEquals(0, generatedXmlMapping.getElementsByTagName("many-to-many").item(0).getChildNodes().getLength());
    }

    public void testGenerateCodeForUniDirectionalManyToOne() throws Exception {

        CodeGeneratorConfigurationProvider codeGeneratorConfigurationProvider = new CodeGeneratorConfigurationProvider();
        CodeGeneratorConfiguration aCodeGeneratorConfiguration = codeGeneratorConfigurationProvider
                .getCodeGeneratorConfiguration();

        UniDirectional anUniDirectional = new UniDirectional("fieldName", new ListType(new CompositeType()),
                aCodeGeneratorConfiguration.getClassList().getClass("test.model.Person"), aCodeGeneratorConfiguration
                        .getClassList().getClass("test.model.Address"));
        ManyToOne aManyToOne = new ManyToOne();

        XmlNHibernateMappingGeneratorContext anHibernateMappingGeneratorContext = generateXmlNHibernateMappingGeneratorContext(new OrmMapping(
                aCodeGeneratorConfiguration.getClassList().getClass("test.model.Person"), "claseAA_id"));
        XmlNHibernateMappingGenerator anXmlNHibernateMappingGenerator = new XmlNHibernateMappingGenerator();

        anXmlNHibernateMappingGenerator.generateCodeFor(anUniDirectional, aManyToOne,
                anHibernateMappingGeneratorContext);

        Document generatedXmlMapping = anHibernateMappingGeneratorContext.getXmlMapping();

        /** general tags */
        assertEquals(1, generatedXmlMapping.getElementsByTagName(HIBERNATE_MAPPING).getLength());
        assertEquals(1, generatedXmlMapping.getElementsByTagName(HIBERNATE_MAPPING).item(0).getChildNodes().getLength());
        assertEquals(2, generatedXmlMapping.getElementsByTagName(HIBERNATE_MAPPING).item(0).getAttributes().getLength());
        assertEquals("field", generatedXmlMapping.getElementsByTagName(HIBERNATE_MAPPING).item(0).getAttributes()
                .getNamedItem("default-access").getNodeValue());
        assertEquals("save-update", generatedXmlMapping.getElementsByTagName(HIBERNATE_MAPPING).item(0).getAttributes()
                .getNamedItem("default-cascade").getNodeValue());
        /** class tag */
        assertEquals(1, generatedXmlMapping.getElementsByTagName("class").getLength());
        assertEquals(1, generatedXmlMapping.getElementsByTagName("class").item(0).getChildNodes().getLength());
        assertEquals(2, generatedXmlMapping.getElementsByTagName("class").item(0).getAttributes().getLength());
        assertEquals("test.model.Person", generatedXmlMapping.getElementsByTagName("class").item(0).getAttributes()
                .getNamedItem("name").getNodeValue());
        assertEquals("Person", generatedXmlMapping.getElementsByTagName("class").item(0).getAttributes().getNamedItem(
                "table").getNodeValue());
        /** many-to-one tag */
        assertEquals("many-to-one", generatedXmlMapping.getElementsByTagName("class").item(0).getChildNodes().item(0)
                .getNodeName());
        assertEquals(2, generatedXmlMapping.getElementsByTagName("many-to-one").item(0).getAttributes().getLength());
        assertEquals(0, generatedXmlMapping.getElementsByTagName("many-to-one").item(0).getChildNodes().getLength());
        assertEquals("fieldName", generatedXmlMapping.getElementsByTagName("many-to-one").item(0).getAttributes()
                .getNamedItem("name").getNodeValue());
        assertEquals("fieldNameId", generatedXmlMapping.getElementsByTagName("many-to-one").item(0).getAttributes()
                .getNamedItem("column").getNodeValue());
    }

    public void testGenerateCodeForUniDirectionalOneToMany() throws Exception {

        CodeGeneratorConfigurationProvider codeGeneratorConfigurationProvider = new CodeGeneratorConfigurationProvider();
        CodeGeneratorConfiguration aCodeGeneratorConfiguration = codeGeneratorConfigurationProvider
                .getCodeGeneratorConfiguration();

        UniDirectional anUniDirectional = new UniDirectional("fieldName", new ListType(new CompositeType()),
                ClassFinder
                        .find("test.model.Person", aCodeGeneratorConfiguration.getClassList(), new JavaProjectType()),
                ClassFinder.find("test.model.Address", aCodeGeneratorConfiguration.getClassList(),
                        new JavaProjectType()));
        OneToMany aOneToMany = new OneToMany();

        XmlNHibernateMappingGeneratorContext anHibernateMappingGeneratorContext = generateXmlNHibernateMappingGeneratorContext(new OrmMapping(
                ClassFinder
                        .find("test.model.Person", aCodeGeneratorConfiguration.getClassList(), new JavaProjectType()),
                "claseAA_id"));
        XmlNHibernateMappingGenerator anXmlNHibernateMappingGenerator = new XmlNHibernateMappingGenerator();

        anXmlNHibernateMappingGenerator.generateCodeFor(anUniDirectional, aOneToMany,
                anHibernateMappingGeneratorContext);

        Document generatedXmlMapping = anHibernateMappingGeneratorContext.getXmlMapping();

        /** general tags */
        assertEquals(1, generatedXmlMapping.getElementsByTagName(HIBERNATE_MAPPING).getLength());
        assertEquals(1, generatedXmlMapping.getElementsByTagName(HIBERNATE_MAPPING).item(0).getChildNodes().getLength());
        assertEquals(2, generatedXmlMapping.getElementsByTagName(HIBERNATE_MAPPING).item(0).getAttributes().getLength());
        assertEquals("field", generatedXmlMapping.getElementsByTagName(HIBERNATE_MAPPING).item(0).getAttributes()
                .getNamedItem("default-access").getNodeValue());
        assertEquals("save-update", generatedXmlMapping.getElementsByTagName(HIBERNATE_MAPPING).item(0).getAttributes()
                .getNamedItem("default-cascade").getNodeValue());
        /** class tag */
        assertEquals(1, generatedXmlMapping.getElementsByTagName("class").getLength());
        assertEquals(1, generatedXmlMapping.getElementsByTagName("class").item(0).getChildNodes().getLength());
        assertEquals(2, generatedXmlMapping.getElementsByTagName("class").item(0).getAttributes().getLength());
        assertEquals("test.model.Person", generatedXmlMapping.getElementsByTagName("class").item(0).getAttributes()
                .getNamedItem("name").getNodeValue());
        assertEquals("Person", generatedXmlMapping.getElementsByTagName("class").item(0).getAttributes().getNamedItem(
                "table").getNodeValue());
        /** list tag */
        assertEquals("list", generatedXmlMapping.getElementsByTagName("class").item(0).getChildNodes().item(0)
                .getNodeName());
        assertEquals("fieldName", generatedXmlMapping.getElementsByTagName("list").item(0).getAttributes()
                .getNamedItem("name").getNodeValue());
        assertEquals("Person_Address", generatedXmlMapping.getElementsByTagName("list").item(0).getAttributes()
                .getNamedItem("table").getNodeValue());
        /** list tag children */
        assertEquals(3, generatedXmlMapping.getElementsByTagName("list").item(0).getChildNodes().getLength());
        /** first list child: key */
        assertEquals("key", generatedXmlMapping.getElementsByTagName("list").item(0).getChildNodes().item(0)
                .getNodeName());
        assertEquals(1, generatedXmlMapping.getElementsByTagName("key").item(0).getAttributes().getLength());
        assertEquals("PersonId", generatedXmlMapping.getElementsByTagName("key").item(0).getAttributes().getNamedItem(
                "column").getNodeValue());
        assertEquals(0, generatedXmlMapping.getElementsByTagName("key").item(0).getChildNodes().getLength());
        /** second list child: list-index */
        assertEquals("index", generatedXmlMapping.getElementsByTagName("list").item(0).getChildNodes().item(1)
                .getNodeName());
        assertEquals(1, generatedXmlMapping.getElementsByTagName("index").item(0).getAttributes().getLength());
        assertEquals("indx", generatedXmlMapping.getElementsByTagName("index").item(0).getAttributes().getNamedItem(
                "column").getNodeValue());
        assertEquals(0, generatedXmlMapping.getElementsByTagName("index").item(0).getChildNodes().getLength());
        /** third list child: many-to-many */
        assertEquals("many-to-many", generatedXmlMapping.getElementsByTagName("list").item(0).getChildNodes().item(2)
                .getNodeName());
        assertEquals(3, generatedXmlMapping.getElementsByTagName("many-to-many").item(0).getAttributes().getLength());
        assertEquals("test.model.Address", generatedXmlMapping.getElementsByTagName("many-to-many").item(0)
                .getAttributes().getNamedItem("class").getNodeValue());
        assertEquals("AddressId", generatedXmlMapping.getElementsByTagName("many-to-many").item(0).getAttributes()
                .getNamedItem("column").getNodeValue());
        assertEquals("true", generatedXmlMapping.getElementsByTagName("many-to-many").item(0).getAttributes()
                .getNamedItem("unique").getNodeValue());
        assertEquals(0, generatedXmlMapping.getElementsByTagName("many-to-many").item(0).getChildNodes().getLength());
    }

    public void testGenerateCodeForUnidirectionalOneToOne() {

        /** Actual */

        CodeGeneratorConfigurationProvider codeGeneratorConfigurationProvider = new CodeGeneratorConfigurationProvider();
        CodeGeneratorConfiguration aCodeGeneratorConfiguration = codeGeneratorConfigurationProvider
                .getCodeGeneratorConfiguration();

        XmlNHibernateMappingGenerator xmlNHibernateMappingGenerator = new XmlNHibernateMappingGenerator();
        OrmMapping ormMapping = new OrmMapping(aCodeGeneratorConfiguration.getClassList()
                .getClass("test.model.Company"), "companyId");
        XmlNHibernateMappingGeneratorContext xmlNHibernateMappingGeneratorContext = generateXmlNHibernateMappingGeneratorContext(ormMapping);
        UniDirectional uniDirectional = new UniDirectional("address", new EntityReference(aCodeGeneratorConfiguration
                .getClassList().getClass("test.model.Address")), aCodeGeneratorConfiguration.getClassList().getClass(
                "test.model.Company"), aCodeGeneratorConfiguration.getClassList().getClass("test.model.Address"));
        OneToOne oneToOne = new OneToOne();
        xmlNHibernateMappingGenerator.generateCodeFor(uniDirectional, oneToOne, xmlNHibernateMappingGeneratorContext);

        Document xmlMapping = xmlNHibernateMappingGeneratorContext.getXmlMapping();

        /** Expected */

        assertEquals(1, xmlMapping.getElementsByTagName("many-to-one").getLength());
        assertEquals(3, xmlMapping.getElementsByTagName("many-to-one").item(0).getAttributes().getLength());
        assertEquals(uniDirectional.getFieldNameA(), xmlMapping.getElementsByTagName("many-to-one").item(0)
                .getAttributes().getNamedItem("name").getNodeValue());
        assertEquals(uniDirectional.getFieldNameA() + "Id", xmlMapping.getElementsByTagName("many-to-one").item(0)
                .getAttributes().getNamedItem("column").getNodeValue());
        assertEquals("true", xmlMapping.getElementsByTagName("many-to-one").item(0).getAttributes().getNamedItem(
                "unique").getNodeValue());

    }

    public void testGenerateDiscriminatorElement() {
        try {

            CodeGeneratorConfigurationProvider codeGeneratorConfigurationProvider = new CodeGeneratorConfigurationProvider();
            CodeGeneratorConfiguration aCodeGeneratorConfiguration = codeGeneratorConfigurationProvider
                    .getCodeGeneratorConfiguration();

            // Context
            XmlNHibernateMappingGenerator xmlNHibernateMappingGenerator = new XmlNHibernateMappingGenerator();
            OrmMapping ormMapping = new OrmMapping(aCodeGeneratorConfiguration.getClassList().getClass(
                    "test.model.Address"), "addressId");
            XmlNHibernateMappingGeneratorContext xmlNHibernateMappingGeneratorContext = generateXmlNHibernateMappingGeneratorContext(ormMapping);

            /** Actual or Generated */
            xmlNHibernateMappingGenerator.generateDiscriminatorElement(xmlNHibernateMappingGeneratorContext);

            Document generatedXmlMapping = xmlNHibernateMappingGeneratorContext.getXmlMapping();

            /** generatedXmlMapping. */
            assertEquals(1, generatedXmlMapping.getElementsByTagName("discriminator").getLength());
            assertEquals(0, generatedXmlMapping.getElementsByTagName("discriminator").item(0).getAttributes()
                    .getLength());
            assertEquals(1, generatedXmlMapping.getElementsByTagName("discriminator").item(0).getChildNodes()
                    .getLength());
            assertEquals("column", generatedXmlMapping.getElementsByTagName("discriminator").item(0).getChildNodes()
                    .item(0).getNodeName());

            assertEquals(1, generatedXmlMapping.getElementsByTagName("column").getLength());
            assertEquals(1, generatedXmlMapping.getElementsByTagName("column").item(0).getAttributes().getLength());
            assertEquals("DISCRIMINATOR", generatedXmlMapping.getElementsByTagName("column").item(0).getAttributes()
                    .getNamedItem("name").getNodeValue());

        } catch (Exception e) {
            logger.error("Se levantó una excepción: " + e.getMessage());
            fail();
        }
    }

    public void testHasDiscriminator() {
        try {
            CodeGeneratorConfigurationProvider codeGeneratorConfigurationProvider = new CodeGeneratorConfigurationProvider();
            CodeGeneratorConfiguration aCodeGeneratorConfiguration = codeGeneratorConfigurationProvider
                    .getCodeGeneratorConfiguration();

            /** Context */
            XmlNHibernateMappingGenerator xmlNHibernateMappingGenerator = new XmlNHibernateMappingGenerator();
            OrmMapping ormMapping = new OrmMapping(aCodeGeneratorConfiguration.getClassList().getClass(
                    "test.model.Address"), "addressId");
            XmlNHibernateMappingGeneratorContext xmlNHibernateMappingGeneratorContext = generateXmlNHibernateMappingGeneratorContext(ormMapping);
            xmlNHibernateMappingGenerator.generateDiscriminatorElement(xmlNHibernateMappingGeneratorContext);

            /** Test */

            assertTrue(xmlNHibernateMappingGenerator.hasDiscriminator(xmlNHibernateMappingGeneratorContext));

        } catch (Exception e) {
            logger.error("Se levantó una excepción: " + e.getMessage());
            fail();
        }
    }

    public void testAnotherHasDiscriminator() {
        try {
            CodeGeneratorConfigurationProvider codeGeneratorConfigurationProvider = new CodeGeneratorConfigurationProvider();
            CodeGeneratorConfiguration aCodeGeneratorConfiguration = codeGeneratorConfigurationProvider
                    .getCodeGeneratorConfiguration();

            /** Context */
            XmlNHibernateMappingGenerator xmlNHibernateMappingGenerator = new XmlNHibernateMappingGenerator();
            OrmMapping ormMapping = new OrmMapping(aCodeGeneratorConfiguration.getClassList().getClass(
                    "test.model.Address"), "addressId");
            XmlNHibernateMappingGeneratorContext xmlNHibernateMappingGeneratorContext = generateXmlNHibernateMappingGeneratorContext(ormMapping);

            /** Test */

            assertFalse(xmlNHibernateMappingGenerator.hasDiscriminator(xmlNHibernateMappingGeneratorContext));

        } catch (Exception e) {
            logger.error("Se levantó una excepción: " + e.getMessage());
            fail();
        }
    }
}
