package com.hexacta.booster.codegeneration.persistence.configuration.hibernate;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.sun.org.apache.xerces.internal.dom.DocumentImpl;
import com.sun.org.apache.xerces.internal.dom.ParentNode;
import com.sun.org.apache.xml.internal.serialize.OutputFormat;
import com.sun.org.apache.xml.internal.serialize.XMLSerializer;

/**
 * This class save to disk an Hibernate configuration in xml format. First it
 * generates an xml structure an then formats the options and the mapping
 * resources and puts then inside this xml structure.
 */

public class HibernateConfigurationXmlGenerator {

    private Document xmldoc;

    /**
     * Log4j logger.
     */
    static final Logger logger = Logger.getLogger(HibernateConfigurationXmlGenerator.class);

    public HibernateConfigurationXmlGenerator() {
        xmldoc = new DocumentImpl();
    }

    public void generateHibernateConfigurationXml(final HibernateConfiguration anHibernateConfiguration,
            final String aPath) {
        try {
            generateStructureXML();
            generatePropertiesXML(anHibernateConfiguration.getProperties());
            generateMappingListXML(anHibernateConfiguration.getMappings());
            saveToDisk(aPath);

        } catch (IOException e) {
            logger.error("Can't create hibernate.cfg.xml in location: " + aPath);
        }
    }

    private void generateStructureXML() {
        /** Xmldoc structure generation */

        Element hconf = xmldoc.createElement("hibernate-configuration");

        Element sfact = xmldoc.createElement("session-factory");
        hconf.appendChild(sfact);

        xmldoc.appendChild(hconf);
    }

    @SuppressWarnings("unchecked")
    private void generatePropertiesXML(final Map<String, HibernatePropertyName> properties) {
        /** Properties tag generation */

        NodeList session = xmldoc.getElementsByTagName("session-factory");
        Node e = session.item(0);

        int mapsize = properties.size();

        Iterator<?> keyValuePairs1 = properties.entrySet().iterator();
        for (int i = 0; i < mapsize; i++) {
            Map.Entry<String, HibernatePropertyName> entry = (Map.Entry<String, HibernatePropertyName>) keyValuePairs1
                    .next();
            String key = entry.getKey();
            HibernatePropertyName value = entry.getValue();

            Element property = xmldoc.createElement("property");
            property.setAttribute("name", key);
            ((ParentNode) property).setTextContent(value.getContent());

            e.appendChild(property);
        }
    }

    @SuppressWarnings("unchecked")
    private void generateMappingListXML(final Map<String, HibernateMappingResource> mappings) {
        /** Mapping tags generation */

        NodeList session = xmldoc.getElementsByTagName("session-factory");
        Node e = session.item(0);

        int mapsize = mappings.size();

        Iterator<?> keyValuePairs1 = mappings.entrySet().iterator();
        for (int i = 0; i < mapsize; i++) {
            Map.Entry<String, HibernateMappingResource> entry = (Map.Entry<String, HibernateMappingResource>) keyValuePairs1
                    .next();
            HibernateMappingResource value = entry.getValue();

            Element property = xmldoc.createElement("mapping");
            property.setAttribute("resource", value.getResource() + ".hbm.xml");

            e.appendChild(property);
        }
    }

    private void saveToDisk(final String resourcesPath) throws IOException {
        try {
            FileOutputStream fos;

            File directory = new File(resourcesPath);
            directory.mkdirs();

            fos = new FileOutputStream(resourcesPath + "hibernate.cfg.xml");
            OutputFormat of = new OutputFormat("XML", "UTF-8", true);
            of.setIndent(1);
            of.setIndenting(true);
            of.setDoctype("-//Hibernate/Hibernate Configuration DTD 3.0//EN",
                    "http://hibernate.sourceforge.net/hibernate-configuration-3.0.dtd");
            of.setOmitDocumentType(false);

            XMLSerializer serializer = new XMLSerializer(fos, of);
            serializer.asDOMSerializer();
            serializer.serialize(xmldoc.getDocumentElement());
            fos.close();
        } catch (IOException exc) {
            throw exc;
        }
    }
}
