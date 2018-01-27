package com.hexacta.booster.codegeneration.persistence.configuration.hibernate;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import junit.framework.TestCase;

import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import ar.com.hexacta.utils.reflection.ReflectionUtils;

import com.hexacta.booster.codegeneration.configuration.ClassFinder;
import com.hexacta.booster.codegeneration.configuration.ClassList;
import com.hexacta.booster.codegeneration.configuration.HibernateParams;
import com.hexacta.booster.codegeneration.configuration.NotSupportedClassException;
import com.hexacta.booster.codegeneration.metamodel.MetaModelGeneratorForJavaModel;
import com.hexacta.booster.codegeneration.persistence.ormmodel.OrmMapping;
import com.hexacta.booster.project.configuration.JavaProjectType;
import com.sun.org.apache.xerces.internal.dom.DocumentImpl;
import com.sun.org.apache.xerces.internal.dom.ParentNode;

/**
 *
 */
public class HibernateConfigurationXmlGeneratorTest extends TestCase {

    /**
     * Log4j logger.
     */

    static final Logger logger = Logger.getLogger(HibernateConfigurationXmlGeneratorTest.class.getSimpleName());

    private HibernateParams hibernateParams = new HibernateParams("jdbc:mysql://hxbws014/anrDB",
            "com.mysql.jdbc.Driver", "org.hibernate.dialect.MySQLDialect", "root", "admin", 0);

    private String[] classes = { "test.model.House", "test.model.Student", "test.model.Person", "test.model.Address",
            "test.model.Employee", "test.model.Company", "test.model.Room", "test.model.Course" };

    private List<OrmMapping> createOrmMappingContext(final int valuee) throws NotSupportedClassException {

        int value = valuee;
        List<java.lang.Class<?>> aClassList = new ArrayList<java.lang.Class<?>>();

        aClassList.add(test.model.House.class);
        aClassList.add(test.model.Student.class);
        aClassList.add(test.model.Person.class);
        aClassList.add(test.model.Address.class);
        aClassList.add(test.model.Employee.class);
        aClassList.add(test.model.Company.class);
        aClassList.add(test.model.Room.class);
        aClassList.add(test.model.Course.class);

        MetaModelGeneratorForJavaModel metaModelGeneratorForJavaModel = new MetaModelGeneratorForJavaModel();
        ClassList classList = metaModelGeneratorForJavaModel.generate(aClassList);

        if (value <= 0) {
            value = 0;
        }
        if (value >= 8) {
            value = 8;
        }

        List<OrmMapping> ormMappings = new ArrayList<OrmMapping>();
        for (int i = 0; i < value; i++) {

            OrmMapping mapping = new OrmMapping(ClassFinder.find(classes[i], classList, new JavaProjectType()),
                    classes[i] + "Id");
            ormMappings.add(mapping);
        }
        return ormMappings;
    }

    private Map<String, HibernatePropertyName> makeMapProperties(final HibernateParams anHibernateParams,
            @SuppressWarnings("unused") final HibernateConfigurationGenerator anHibernateConfigGenerator)
            throws Exception {

        Map<String, HibernatePropertyName> properties = new HashMap<String, HibernatePropertyName>();

        HibernatePropertyName propDriver = new HibernatePropertyName("connection.driver_class", anHibernateParams
                .getDriver());
        properties.put("connection.driver_class", propDriver);

        HibernatePropertyName propDatabase = new HibernatePropertyName("connection.url", anHibernateParams
                .getDatabase());
        properties.put("connection.url", propDatabase);

        HibernatePropertyName propUser = new HibernatePropertyName("connection.username", anHibernateParams
                .getUsername());
        properties.put("connection.username", propUser);

        HibernatePropertyName propPassword = new HibernatePropertyName("connection.password", anHibernateParams
                .getPassword());
        properties.put("connection.password", propPassword);

        HibernatePropertyName propMaxConnections = new HibernatePropertyName("connection.pool_size", String
                .valueOf(anHibernateParams.getMaxConnections()));
        properties.put("connection.pool_size", propMaxConnections);

        HibernatePropertyName propDialect = new HibernatePropertyName("dialect", "org.hibernate.dialect.MySQLDialect");
        properties.put("dialect", propDialect);

        HibernatePropertyName propContext = new HibernatePropertyName("current_session_context_class", "thread");
        properties.put("current_session_context_class", propContext);

        HibernatePropertyName propCache = new HibernatePropertyName("cache.provider_class",
                "org.hibernate.cache.NoCacheProvider");
        properties.put("cache.provider_class", propCache);

        HibernatePropertyName propShow = new HibernatePropertyName("show_sql", "true");
        properties.put("show_sql", propShow);

        HibernatePropertyName propData = new HibernatePropertyName("hbm2ddl.auto", "update");
        properties.put("hbm2ddl.auto", propData);

        HibernatePropertyName propFlush = new HibernatePropertyName("hibernate.transaction.flush_before_completion",
                "true");
        properties.put("hibernate.transaction.flush_before_completion", propFlush);

        return properties;
    }

    private Map<String, HibernateMappingResource> makeMapMappings(final List<OrmMapping> anOrmMappings) {

        Map<String, HibernateMappingResource> mappings = new HashMap<String, HibernateMappingResource>();

        for (OrmMapping ormMapping : anOrmMappings) {

            OrmMapping anOrmMapping = ormMapping;

            HibernateMappingResource mapping = new HibernateMappingResource(anOrmMapping.getMappingClass().getName());
            mappings.put(anOrmMapping.getMappingClass().getName(), mapping);

        }
        return mappings;
    }

    private Node generateCommonStructure(final Document anXmlDoc) {

        Element hconf = anXmlDoc.createElement("hibernate-configuration");

        Element sfact = anXmlDoc.createElement("session-factory");
        hconf.appendChild(sfact);

        anXmlDoc.appendChild(hconf);

        NodeList session = anXmlDoc.getElementsByTagName("session-factory");
        Node aNode = session.item(0);

        return aNode;
    }

    @SuppressWarnings("unchecked")
    private Document makeXmlDocProperties(final Map<String, HibernatePropertyName> properties) {

        Document anXmlDoc = new DocumentImpl();
        Node aNode = generateCommonStructure(anXmlDoc);

        int mapsize = properties.size();

        Iterator<?> keyValuePairs1 = properties.entrySet().iterator();
        for (int i = 0; i < mapsize; i++) {
            Map.Entry<String, HibernatePropertyName> entry = (Map.Entry<String, HibernatePropertyName>) keyValuePairs1
                    .next();
            String key = entry.getKey();
            HibernatePropertyName value = entry.getValue();

            Element property = anXmlDoc.createElement("property");
            property.setAttribute("name", key);
            ((ParentNode) property).setTextContent(value.getContent());

            aNode.appendChild(property);
        }
        return anXmlDoc;
    }

    @SuppressWarnings("unchecked")
    private Document makeXmlDocMappings(final Map<String, HibernateMappingResource> mappings) {

        Document anXmlDoc = new DocumentImpl();
        Node aNode = generateCommonStructure(anXmlDoc);

        int mapsize = mappings.size();

        Iterator<?> keyValuePairs1 = mappings.entrySet().iterator();
        for (int i = 0; i < mapsize; i++) {
            Map.Entry<String, HibernateMappingResource> entry = (Map.Entry<String, HibernateMappingResource>) keyValuePairs1
                    .next();
            HibernateMappingResource value = entry.getValue();

            Element property = anXmlDoc.createElement("mapping");
            property.setAttribute("resource", value.getResource() + ".hbm.xml");

            aNode.appendChild(property);
        }
        return anXmlDoc;
    }

    public void testCreation() {
        try {
            HibernateConfigurationXmlGenerator anXmlGenerator = new HibernateConfigurationXmlGenerator();
            Document generatedXmldoc = (Document) ReflectionUtils.getPrivateField(anXmlGenerator, "xmldoc");

            assertNotNull(anXmlGenerator);
            assertNotNull(generatedXmldoc);

        } catch (Exception e) {
            logger.error("Se levantó una excepción: " + e.getMessage());
            fail();
        }
    }

    // public void testGenerateHibernateConfigurationXml() {
    // try {
    // List<OrmMapping> ormMappings = createOrmMappingContext(5);
    //
    // new File("./src/test/resources/").mkdir();
    // String aPath = "./src/test/resources/";
    //
    // HibernateConfigurationGenerator anHibernateConfigurationGenerator = new
    // HibernateConfigurationGenerator();
    // HibernateConfiguration anHibernateConfiguration =
    // anHibernateConfigurationGenerator.generateConfiguration(
    // hibernateParams, ormMappings);
    //
    // HibernateConfigurationXmlGenerator anXmlGenerator = new
    // HibernateConfigurationXmlGenerator();
    //anXmlGenerator.generateHibernateConfigurationXml(anHibernateConfiguration,
    // aPath);
    //
    // HibernateConfigurationXmlGenerator anotherXmlGenerator = new
    // HibernateConfigurationXmlGenerator();
    // ReflectionUtils.executePrivateMethod(anotherXmlGenerator,
    // "generateStructureXML");
    // ReflectionUtils.executePrivateMethod(anotherXmlGenerator,
    // "generatePropertiesXML",
    // new Class[] { Map.class }, new Object[] {
    // anHibernateConfiguration.getProperties() });
    // ReflectionUtils.executePrivateMethod(anotherXmlGenerator,
    // "generateMappingListXML",
    // new Class[] { Map.class }, new Object[] {
    // anHibernateConfiguration.getMappings() });
    //
    // Document expectedXmldoc = (Document)
    // ReflectionUtils.getPrivateField(anotherXmlGenerator, "xmldoc");
    //
    // Document generatedXmldoc = (Document)
    // ReflectionUtils.getPrivateField(anXmlGenerator, "xmldoc");
    //
    // assertTrue(((ParentNode) expectedXmldoc).isEqualNode(generatedXmldoc));
    // assertEquals(1,
    // generatedXmldoc.getElementsByTagName("hibernate-configuration"
    // ).getLength());
    // assertEquals(1,
    // generatedXmldoc.getElementsByTagName("session-factory").getLength());
    // assertEquals(ormMappings.size(),
    // generatedXmldoc.getElementsByTagName("mapping").getLength());
    // assertEquals(11,
    // generatedXmldoc.getElementsByTagName("property").getLength());
    //
    // new File(aPath + "hibernate.cfg.xml").delete();
    // new File("./src/test/resources/").delete();
    //
    // } catch (Exception e) {
    // logger.error("Se levantó una excepción: " + e.getMessage());
    // fail();
    // }
    // }

    // public void testAnotherGenerateHibernateConfigurationXml() {
    // try {
    // List<OrmMapping> ormMappings = createOrmMappingContext(6);
    //
    // new File("./src/test/resources/").mkdir();
    // String aPath = "./src/test/resources/";
    //
    // HibernateConfigurationGenerator anHibernateConfigurationGenerator = new
    // HibernateConfigurationGenerator();
    // HibernateConfiguration anHibernateConfiguration =
    // anHibernateConfigurationGenerator.generateConfiguration(
    // hibernateParams, ormMappings);
    //
    // HibernateConfigurationXmlGenerator anXmlGenerator = new
    // HibernateConfigurationXmlGenerator();
    //anXmlGenerator.generateHibernateConfigurationXml(anHibernateConfiguration,
    // aPath);
    //
    // HibernateConfigurationXmlGenerator anotherXmlGenerator = new
    // HibernateConfigurationXmlGenerator();
    // ReflectionUtils.executePrivateMethod(anotherXmlGenerator,
    // "generateStructureXML");
    // ReflectionUtils.executePrivateMethod(anotherXmlGenerator,
    // "generatePropertiesXML",
    // new Class[] { Map.class }, new Object[] {
    // anHibernateConfiguration.getProperties() });
    // ReflectionUtils.executePrivateMethod(anotherXmlGenerator,
    // "generateMappingListXML",
    // new Class[] { Map.class }, new Object[] {
    // anHibernateConfiguration.getMappings() });
    //
    // Document expectedXmldoc = (Document)
    // ReflectionUtils.getPrivateField(anotherXmlGenerator, "xmldoc");
    //
    // Document generatedXmldoc = (Document)
    // ReflectionUtils.getPrivateField(anXmlGenerator, "xmldoc");
    //
    // assertTrue(((ParentNode) expectedXmldoc).isEqualNode(generatedXmldoc));
    // assertEquals(1,
    // generatedXmldoc.getElementsByTagName("hibernate-configuration"
    // ).getLength());
    // assertEquals(1,
    // generatedXmldoc.getElementsByTagName("session-factory").getLength());
    // assertEquals(ormMappings.size(),
    // generatedXmldoc.getElementsByTagName("mapping").getLength());
    // assertEquals(11,
    // generatedXmldoc.getElementsByTagName("property").getLength());
    //
    // new File(aPath + "hibernate.cfg.xml").delete();
    // new File("./src/test/resources/").delete();
    //
    // } catch (Exception e) {
    // logger.error("Se levantó una excepción: " + e.getMessage());
    // fail();
    // }
    // }

    public void testGenerateStructureXML() {
        try {
            HibernateConfigurationXmlGenerator anXmlGenerator = new HibernateConfigurationXmlGenerator();
            ReflectionUtils.executePrivateMethod(anXmlGenerator, "generateStructureXML");

            Document recoveredXmldoc = (Document) ReflectionUtils.getPrivateField(anXmlGenerator, "xmldoc");
            Node aNode = recoveredXmldoc.getElementsByTagName("session-factory").item(0);

            assertEquals(1, recoveredXmldoc.getElementsByTagName("hibernate-configuration").getLength());
            assertEquals(1, recoveredXmldoc.getElementsByTagName("session-factory").getLength());
            assertTrue(aNode.getParentNode().getNodeName().equals("hibernate-configuration"));

        } catch (Exception e) {
            logger.error("Se levantó una excepción: " + e.getMessage());
            fail();
        }
    }

    public void testGeneratePropertiesXML() {
        try {

            Map<String, HibernatePropertyName> properties = makeMapProperties(hibernateParams,
                    new HibernateConfigurationGenerator());

            HibernateConfigurationXmlGenerator anXmlGenerator = new HibernateConfigurationXmlGenerator();

            ReflectionUtils.executePrivateMethod(anXmlGenerator, "generateStructureXML");
            ReflectionUtils.executePrivateMethod(anXmlGenerator, "generatePropertiesXML", new Class[] { Map.class },
                    new Object[] { properties });

            Document expectedXmldoc = makeXmlDocProperties(properties);

            Document generatedXmldoc = (Document) ReflectionUtils.getPrivateField(anXmlGenerator, "xmldoc");

            assertTrue(((ParentNode) generatedXmldoc).isEqualNode(expectedXmldoc));

        } catch (Exception e) {
            logger.error("Se levantó una excepción: " + e.getMessage());
            fail();
        }
    }

    public void testGenerateMappingListXML() {
        try {
            List<OrmMapping> ormMappings = createOrmMappingContext(5);

            Map<String, HibernateMappingResource> mappings = makeMapMappings(ormMappings);

            HibernateConfigurationXmlGenerator anXmlGenerator = new HibernateConfigurationXmlGenerator();

            ReflectionUtils.executePrivateMethod(anXmlGenerator, "generateStructureXML");
            ReflectionUtils.executePrivateMethod(anXmlGenerator, "generateMappingListXML", new Class[] { Map.class },
                    new Object[] { mappings });

            Document generatedXmldoc = (Document) ReflectionUtils.getPrivateField(anXmlGenerator, "xmldoc");

            Document expectedXmldoc = makeXmlDocMappings(mappings);

            assertTrue(((ParentNode) generatedXmldoc).isEqualNode(expectedXmldoc));

        } catch (Exception e) {
            logger.error("Se levantó una excepción: " + e.getMessage());
            fail();
        }
    }

    public void testGenerateAnotherMappingListXML() {
        try {
            List<OrmMapping> ormMappings = createOrmMappingContext(0);

            Map<String, HibernateMappingResource> mappings = makeMapMappings(ormMappings);

            HibernateConfigurationXmlGenerator anXmlGenerator = new HibernateConfigurationXmlGenerator();

            ReflectionUtils.executePrivateMethod(anXmlGenerator, "generateStructureXML");
            ReflectionUtils.executePrivateMethod(anXmlGenerator, "generateMappingListXML", new Class[] { Map.class },
                    new Object[] { mappings });

            Document generatedXmldoc = (Document) ReflectionUtils.getPrivateField(anXmlGenerator, "xmldoc");

            Document expectedXmldoc = makeXmlDocMappings(mappings);

            assertTrue(((ParentNode) generatedXmldoc).isEqualNode(expectedXmldoc));

        } catch (Exception e) {
            logger.error("Se levantó una excepción: " + e.getMessage());
            fail();
        }
    }

    public void testSaveToDisk() throws NotSupportedClassException {

        List<OrmMapping> ormMappings = createOrmMappingContext(3);

        HibernateConfigurationGenerator anHibernateConfigGenerator = new HibernateConfigurationGenerator();
        HibernateConfiguration anHibernateConfiguration = anHibernateConfigGenerator.generateConfiguration(
                hibernateParams, ormMappings);

        new File("src/test/java/tempFolder").mkdirs();

        String aPath = "src/test/java/tempFolder/";

        HibernateConfigurationXmlGenerator anXmlGenerator = new HibernateConfigurationXmlGenerator();
        anXmlGenerator.generateHibernateConfigurationXml(anHibernateConfiguration, aPath);

        File aFile = new File(aPath + "hibernate.cfg.xml");

        assertTrue(aFile.exists());

        // Delete file and directory
        new File(aPath + "hibernate.cfg.xml").delete();
        new File("src/test/java/tempFolder").delete();
    }

    public void testAnotherSaveToDisk() {
        try { // Test SaveToDisk in another location
            List<OrmMapping> ormMappings = createOrmMappingContext(3);

            HibernateConfigurationGenerator anHibernateConfigGenerator = new HibernateConfigurationGenerator();
            HibernateConfiguration anHibernateConfiguration = anHibernateConfigGenerator.generateConfiguration(
                    hibernateParams, ormMappings);

            new File("tempFolder").mkdir();

            String aPath = "tempFolder/";

            HibernateConfigurationXmlGenerator anXmlGenerator = new HibernateConfigurationXmlGenerator();
            anXmlGenerator.generateHibernateConfigurationXml(anHibernateConfiguration, aPath);

            File aFile = new File(aPath + "hibernate.cfg.xml");

            assertTrue(aFile.exists());

            // Delete file and directory
            new File(aPath + "hibernate.cfg.xml").delete();
            new File("tempFolder").delete();

        } catch (Exception e) {
            logger.error("Se levantó una excepción: " + e.getMessage());
            fail();
        }
    }

}
