package com.hexacta.booster.codegeneration.persistence.configuration.nhibernate;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.log4j.Logger;
import org.w3c.dom.Document;

import com.hexacta.booster.codegeneration.GenerationInfo;
import com.sun.org.apache.xml.internal.serialize.OutputFormat;
import com.sun.org.apache.xml.internal.serialize.XMLSerializer;

/**
 * 
 */
public class XmlNHibernateMappingWriter {
    /**
     * Log4j logger.
     */
    static final Logger logger = Logger.getLogger(XmlNHibernateMappingWriter.class);

    public XmlNHibernateMappingWriter() {
    }

    public void saveToDisk(final Document aMapping, final String aMappingName, final String resourcePath,
            final GenerationInfo generationInfo) {

        try {

            File directory = new File(resourcePath);
            directory.mkdirs();

            String mappingFilePath = resourcePath + aMappingName + ".hbm.xml";
            FileOutputStream fos = new FileOutputStream(mappingFilePath);
            OutputFormat of = new OutputFormat("XML", "ISO-8859-1", true);
            of.setIndent(1);
            of.setIndenting(true);
            of.setOmitDocumentType(false);

            XMLSerializer serializer = new XMLSerializer(fos, of);

            serializer.asDOMSerializer();
            serializer.serialize(aMapping.getDocumentElement());
            fos.close();

            generationInfo.addMapping(mappingFilePath);

        } catch (FileNotFoundException e) {
            logger.error(e.getMessage());
        } catch (IOException e) {
            logger.error(e.getMessage());
        }

    }

}
