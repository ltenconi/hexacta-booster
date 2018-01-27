package com.hexacta.booster.codegeneration.persistence.configuration.hibernate;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.w3c.dom.Document;

import com.hexacta.booster.codegeneration.GenerationInfo;
import com.hexacta.booster.codegeneration.TextFile;
import com.hexacta.booster.codegeneration.warning.ExistFileWarning;
import com.hexacta.booster.exception.InfraestructureException;
import com.hexacta.booster.project.configuration.OrmTool;
import com.hexacta.booster.utilities.FileVersionChecker;
import com.sun.org.apache.xml.internal.serialize.OutputFormat;
import com.sun.org.apache.xml.internal.serialize.XMLSerializer;

/**
 * 
 */
public class XmlMappingWriter {
    /**
     * Log4j logger.
     */
    static final Logger logger = Logger.getLogger(XmlMappingWriter.class);

    public XmlMappingWriter() {
    }

    public void saveToDisk(final Document aMapping, final String aMappingName, final String resourcePath,
            final GenerationInfo generationInfo, final OrmTool ormTool) {
        try {

            File directory = new File(resourcePath);
            directory.mkdirs();

            String mappingFileName = aMappingName + ".hbm.xml";
            String mappingFilePath = resourcePath + mappingFileName;
            File mappingFile = new File(mappingFilePath);
            if (mappingFile.exists()) {
                String mappingFileTempPath = "./temp.xml";
                writeXMLDocument(aMapping, mappingFileTempPath, ormTool);
                File mappingTempFile = new File(mappingFileTempPath);
                if (!FileVersionChecker.equalsByContent(mappingFile, mappingTempFile)) {
                    generationInfo.getGenerationWarnings().add(
                            new ExistFileWarning(mappingFile, new TextFile(FileUtils.readFileToString(mappingTempFile),
                                    mappingFileName, resourcePath)));
                } else {
                    generationInfo.addExistingEqualsFile(mappingFilePath);
                }
                deleteFile(mappingTempFile);
            } else {
                writeXMLDocument(aMapping, mappingFilePath, ormTool);
                generationInfo.addMapping(mappingFilePath);
            }

        } catch (FileNotFoundException e) {
            logger.error(e.getMessage());
            throw new InfraestructureException(e);
        } catch (IOException e) {
            logger.error(e.getMessage());
            throw new InfraestructureException(e);
        }

    }

    private void writeXMLDocument(final Document aMapping, final String mappingFilePath, final OrmTool ormTool)
            throws FileNotFoundException, IOException {

        if (ormTool.isHibernate()) {
            writeXMLDocumentForHibernate(aMapping, mappingFilePath);
        } else if (ormTool.isNHibernate()) {
            writeXMLDocumentForNHibernate(aMapping, mappingFilePath);
        }
    }

    /**
     * @param mapping
     * @param mappingFilePath
     * @throws IOException
     */
    private void writeXMLDocumentForNHibernate(final Document mapping, final String mappingFilePath) throws IOException {

        FileOutputStream fos = new FileOutputStream(mappingFilePath);
        OutputFormat of = new OutputFormat("XML", "ISO-8859-1", true);
        of.setIndent(1);
        of.setIndenting(true);
        of.setOmitDocumentType(false);

        XMLSerializer serializer = new XMLSerializer(fos, of);

        serializer.asDOMSerializer();
        serializer.serialize(mapping.getDocumentElement());
        fos.close();

    }

    /**
     * @param mapping
     * @param mappingFilePath
     * @throws IOException
     */
    private void writeXMLDocumentForHibernate(final Document mapping, final String mappingFilePath) throws IOException {

        FileOutputStream fos = new FileOutputStream(mappingFilePath);
        OutputFormat of = new OutputFormat("XML", "ISO-8859-1", true);
        of.setIndent(1);
        of.setIndenting(true);
        of.setDoctype("-//Hibernate/Hibernate Mapping DTD 3.0//EN",
                "http://hibernate.sourceforge.net/hibernate-mapping-3.0.dtd");
        of.setOmitDocumentType(false);

        XMLSerializer serializer = new XMLSerializer(fos, of);
        serializer.asDOMSerializer();
        serializer.serialize(mapping.getDocumentElement());
        fos.close();
    }

    private void deleteFile(final File mappingTempFile) {
        mappingTempFile.deleteOnExit();
    }
}
