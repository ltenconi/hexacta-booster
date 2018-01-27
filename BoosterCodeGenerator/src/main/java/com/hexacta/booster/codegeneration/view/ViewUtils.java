/*
 * [legal notice here]
 */
package com.hexacta.booster.codegeneration.view;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.dom4j.tree.BaseElement;
import org.dom4j.tree.DefaultElement;

import com.hexacta.booster.codegeneration.GenerationInfo;
import com.hexacta.booster.codegeneration.TextFile;
import com.hexacta.booster.codegeneration.TextFileGenerator;
import com.hexacta.booster.codegeneration.configuration.CodeGeneratorConfiguration;
import com.hexacta.booster.codegeneration.configuration.DirectoryInfo;
import com.hexacta.booster.codegeneration.configuration.NotSupportedClassException;
import com.hexacta.booster.codegeneration.metamodel.Class;
import com.hexacta.booster.codegeneration.metamodel.Field;
import com.hexacta.booster.codegeneration.persistence.ormmodel.BiDirectional;
import com.hexacta.booster.codegeneration.persistence.ormmodel.OrmData;
import com.hexacta.booster.codegeneration.persistence.ormmodel.OrmDataGenerator;
import com.hexacta.booster.codegeneration.persistence.ormmodel.OrmProperty;
import com.hexacta.booster.codegeneration.persistence.ormmodel.OrmRelation;
import com.hexacta.booster.codegeneration.persistence.ormmodel.UniDirectional;
import com.hexacta.booster.codegeneration.persistence.ormmodel.VarType;
import com.hexacta.booster.exception.InfraestructureException;
import com.hexacta.booster.project.configuration.JavaProjectType;
import com.hexacta.booster.utilities.MetaModelUtils;
import com.hexacta.booster.utilities.PathBuilder;
import com.hexacta.booster.utilities.VarNameBuilder;

/**
 * This class contains utilities for the generation of view code.
 * 
 * Created on 14/04/2009
 * 
 * @author vmartz
 * 
 */
public class ViewUtils {

    static final Logger logger = Logger.getLogger(ViewUtils.class);

    /**
     * @param strutsFileGenerator
     * @param codeGeneratorConfiguration
     * @param generationInfo
     */
    public static void generateForMetamodelToAddInStrutsXML(final TextFileGenerator textFileGenerator,
            final CodeGeneratorConfiguration codeGeneratorConfiguration, final GenerationInfo generationInfo) {
        try {
            for (Iterator<Class> iterator = codeGeneratorConfiguration.getGenerateCodeFor().iterator(); iterator
                    .hasNext();) {

                Class aClass = iterator.next();

                TextFile generated = textFileGenerator.generate(aClass, codeGeneratorConfiguration);

                boolean added = addToXML(generated.getText(), generated.getPath(), generated.getName());

                if (added) {
                    textFileGenerator.addGenerationInfo(generated, generationInfo);
                }
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new InfraestructureException(e);
        }

    }

    /**
     * @param generatedText
     * @param path
     * @param name
     */
    public static boolean addToXML(final String generatedText, final String path, final String name) {

        String strutsXml = path + name;
        SAXReader saxReader = new SAXReader();

        StringReader generatedTextReader = new StringReader(generatedText);
        boolean added = false;

        try {
            Document strutsDocument = saxReader.read(strutsXml);
            Document addedDocument = saxReader.read(generatedTextReader);

            Element packageDefault = searchPackageDefaultElement(strutsDocument);

            if (packageDefault != null) {

                added = addNodesToElement(packageDefault, addedDocument.getRootElement().elements());

            } else {
                logger.error("Could not add elements to " + strutsXml
                        + ": File doesn't contain a package node with name=\"default\".");
                return false;

            }

            XMLWriter xmWriter = getXMLWriter(strutsXml);
            xmWriter.write(strutsDocument);
            xmWriter.close();
            return added;

        } catch (DocumentException e) {
            logger.error("Could not configure " + strutsXml + ": File malformed.");
            throw new InfraestructureException(e);
        } catch (IOException e) {
            logger.error("Could not configure " + strutsXml + ": File is missing.");
            throw new InfraestructureException(e);
        }
    }

    /**
     * @param packageDefault
     * @param addedDocument
     */
    public static boolean addNodesToElement(final Element packageDefault, final List<Element> elements) {
        boolean added = false;
        for (Element element : elements) {
            if (!containsElement(packageDefault, element)) {
                Element e = element.createCopy();
                packageDefault.add(e);
                added = true;
            } else {
                logger.info("Element package default already contains the element " + element.asXML()
                        + "\n then it isn't going to be added");

            }
        }
        return added;
    }

    /**
     * @param packageDefault
     * @param element
     * @return
     */
    public static boolean containsElement(final Element packageDefault, final Element element) {

        List<Element> packageDefaultElements = packageDefault.elements();
        for (Element packageDefaultElement : packageDefaultElements) {
            if (areEquals(packageDefaultElement, element))
                return true;
        }

        return false;
    }

    /**
     * @param packageDefaultElement
     * @param element
     * @return
     */
    public static boolean areEquals(final Element packageDefaultElement, final Element element) {
        String packageDefaultElementString = packageDefaultElement.asXML().replace("\n", "").replace(" ", "");
        String elementString = element.asXML().replace("\n", "").replace(" ", "");
        boolean equals = packageDefaultElementString.equals(elementString);

        return equals;
    }

    private static XMLWriter getXMLWriter(final String outputXMLPath) throws FileNotFoundException,
            UnsupportedEncodingException {

        OutputFormat outFormat = OutputFormat.createPrettyPrint();
        FileOutputStream out = new FileOutputStream(new File(outputXMLPath));
        XMLWriter xmlWriter = new XMLWriter(out, outFormat);
        return xmlWriter;

    }

    /**
     * @param strutsDocument
     * @return
     */
    private static Element searchPackageDefaultElement(final Document strutsDocument) {

        Element e = strutsDocument.getRootElement();

        List<Element> elementList = e.elements();

        for (Element element : elementList) {
            String elementName = element.getQName().getName();
            if (elementName == "package") {
                String elementNameValue = element.attribute("name").getValue();
                if (elementNameValue.equals("default"))
                    return element;
            }
        }
        return null;
    }

    /**
     * @param menuConfigGenerator
     * @param codeGeneratorConfiguration
     * @param generationInfo
     */
    public static void generateForMetamodelToReplace(final TextFileGenerator textFileGenerator,
            final CodeGeneratorConfiguration codeGeneratorConfiguration, final GenerationInfo generationInfo,
            final String textToReplace) {

        try {
            for (Iterator<Class> iterator = codeGeneratorConfiguration.getGenerateCodeFor().iterator(); iterator
                    .hasNext();) {

                Class aClass = iterator.next();

                TextFile generated = textFileGenerator.generate(aClass, codeGeneratorConfiguration);

                boolean alreadyExists = textExistsInFile(generated.getText().replace(textToReplace, ""), generated
                        .getPath(), generated.getName());

                if (!alreadyExists) {
                    replaceInFile(textToReplace, generated.getText(), generated.getPath(), generated.getName());
                    textFileGenerator.addGenerationInfo(generated, generationInfo);
                }
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new InfraestructureException(e);
        }

    }

    /**
     * @param textToReplace
     * @param text
     * @param path
     * @param name
     * @return
     */
    public static boolean textExistsInFile(final String text, final String path, final String name) throws Exception {

        String pathName = path + name;
        String actualFile = FileUtils.readFileToString(new File(pathName));

        if (actualFile.contains(text))
            return true;
        else
            return false;
    }

    /**
     * @param text
     * @param path
     * @param name
     */
    private static void replaceInFile(final String textToReplace, final String text, final String path,
            final String name) throws Exception {

        String pathName = path + name;
        String actualFile = FileUtils.readFileToString(new File(pathName));
        actualFile = actualFile.replace(textToReplace, text);
        FileUtils.writeStringToFile(new File(pathName), actualFile);

    }

    /**
     * @param appResourcesGenerator
     * @param codeGeneratorConfiguration
     * @param generationInfo
     */
    public static void generateForMetamodelToAppendApplicationResources(final TextFileGenerator textFileGenerator,
            final CodeGeneratorConfiguration codeGeneratorConfiguration, final GenerationInfo generationInfo) {

        try {
            for (Iterator<Class> iterator = codeGeneratorConfiguration.getGenerateCodeFor().iterator(); iterator
                    .hasNext();) {

                Class aClass = iterator.next();

                TextFile generated = textFileGenerator.generate(aClass, codeGeneratorConfiguration);

                cleanExistingFile(aClass, generated.getPath() + generated.getName());
                appendToFile(generated.getText(), generated.getPath(), generated.getName());

                textFileGenerator.addGenerationInfo(generated, generationInfo);
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new InfraestructureException(e);
        }

    }

    public static void cleanExistingFile(final Class aClass, final String fileName) {

        try {
            List<String> newfileLines = new ArrayList<String>();
            List<String> fileLines = FileUtils.readLines(new File(fileName));

            boolean wasBlankLine = false;
            for (String stringLine : fileLines) {
                String newLine = deleteClassInformation(aClass, stringLine);
                if (newLine != null) {
                    if (!newLine.trim().equals("")) {
                        newfileLines.add(newLine);
                        wasBlankLine = false;
                    } else { // linea actual vacia
                        if (!wasBlankLine) { // linea anterior no vacia
                            newfileLines.add(newLine);
                            wasBlankLine = true;
                        }
                    }
                }
            }
            FileUtils.writeLines(new File(fileName), newfileLines);

        } catch (IOException e) {
            logger.error(e.getMessage());
        }

    }

    private static String deleteClassInformation(final Class aClass, final String line) {

        String entity = aClass.getSimpleName();
        String entityLower = VarNameBuilder.buildForEntity(aClass);

        if (isStartLine(entity, line) || isEndLine(entity, line) || isEntityLine(entityLower, line))
            return null;
        else
            return line;
    }

    private static boolean isStartLine(final String entity, final String line) {
        String existingLine = line.replace(" ", "").trim();
        String expectedLine = "#--" + entity + "-START";

        if (existingLine.equals(expectedLine))
            return true;
        else
            return false;
    }

    private static boolean isEndLine(final String entity, final String line) {
        String existingLine = line.replace(" ", "").trim();
        String expectedLine = "#--" + entity + "-END";

        if (existingLine.equals(expectedLine))
            return true;
        else
            return false;
    }

    private static boolean isEntityLine(final String entityLower, final String line) {

        if (line.length() != 0 && line.length() >= entityLower.length()) {
            String headLine = line.substring(0, entityLower.length());
            if (headLine.equals(entityLower))
                return true;
            else
                return false;
        } else
            return false;

    }

    public static void appendToFile(final String text, final String path, final String name) throws Exception {

        BufferedWriter bw = null;
        bw = new BufferedWriter(new FileWriter(path + name, true));
        bw.write(text);
        bw.newLine();
        bw.flush();

    }

    public static String buildXML(final String strutsText) {
        return "<actions>" + strutsText + "</actions>";
    }

    /**
     * @param persistableClass
     * @param codeGeneratorConfiguration
     * @return
     */
    public static List<InfoField> getInfoFields(final Class persistableClass,
            final CodeGeneratorConfiguration codeGeneratorConfiguration) throws NotSupportedClassException {

        OrmDataGenerator ormDataGenerator = new OrmDataGenerator();
        OrmData ormData = ormDataGenerator.generateOrmData(codeGeneratorConfiguration, new JavaProjectType());

        Field[] fields = MetaModelUtils.getAllInheritedFields(persistableClass);

        List<InfoField> infoFields = new ArrayList<InfoField>();

        for (Field field : fields) {

            InfoField infoField = new InfoField();
            infoField.setName(field.getName());
            infoField.setType(field.getType());

            boolean isCollection = isCollection(persistableClass, field, ormData);
            boolean isManyToOne = isManyToOne(persistableClass, field, ormData);

            infoField.setCollection(isCollection);
            infoField.setManyToOne(isManyToOne);

            infoFields.add(infoField);

        }

        return infoFields;
    }

    /**
     * @param field
     * @param ormData
     * @return
     */
    private static boolean isManyToOne(final Class persistableClass, final Field field, final OrmData ormData) {
        List<OrmRelation> ormRelations = ormData.getMapping(persistableClass).getOrmRelations();

        for (OrmRelation ormRelation : ormRelations) {

            String relationFieldName;

            if (ormRelation.getAssociationType().isUniDirectional()) {
                UniDirectional uniDirectional = (UniDirectional) ormRelation.getAssociationType();
                relationFieldName = uniDirectional.getFieldNameA();

            } else {
                BiDirectional biDirectional = (BiDirectional) ormRelation.getAssociationType();
                relationFieldName = biDirectional.getFieldNameA();
            }

            if (relationFieldName.equals(field.getName()))
                return ormRelation.getCardinality().isManyToOne();
        }
        return false;
    }

    /**
     * @param field
     * @param ormData
     * @return
     */
    private static boolean isCollection(final Class persistableClass, final Field field, final OrmData ormData) {

        List<OrmProperty> ormProperties = ormData.getMapping(persistableClass).getOrmProperties();

        for (OrmProperty ormProperty : ormProperties) {

            if (ormProperty.getName() == field.getName())
                return ormProperty.getVarType().isCollection();
        }

        List<OrmRelation> ormRelations = ormData.getMapping(persistableClass).getOrmRelations();

        for (OrmRelation ormRelation : ormRelations) {

            VarType varType;
            String relationFieldName;

            if (ormRelation.getAssociationType().isUniDirectional()) {
                UniDirectional uniDirectional = (UniDirectional) ormRelation.getAssociationType();
                varType = uniDirectional.getVarTypeA();
                relationFieldName = uniDirectional.getFieldNameA();

            } else {
                BiDirectional biDirectional = (BiDirectional) ormRelation.getAssociationType();
                varType = biDirectional.getVarTypeA();
                relationFieldName = biDirectional.getFieldNameA();
            }

            if (relationFieldName.equals(field.getName()))
                return varType.isCollection();
        }
        return false;
    }

    /**
     * @param codeGeneratorConfiguration
     * @param generationInfo
     */
    public static void addActionBeansToApplicationContextStruts(
            final CodeGeneratorConfiguration codeGeneratorConfiguration, final GenerationInfo generationInfo) {
        DirectoryInfo directoryInfo = codeGeneratorConfiguration.getDirectoryInfo();
        String inputXml = PathBuilder.buildForViewWebInf(directoryInfo) + "applicationContext-struts.xml";
        boolean modified = false;
        SAXReader saxReader = new SAXReader();
        try {
            Document document = saxReader.read(inputXml);

            Element rootElement = document.getRootElement();
            for (Iterator<Class> iterator = codeGeneratorConfiguration.getGenerateCodeFor().iterator(); iterator
                    .hasNext();) {
                Class aClass = iterator.next();
                if (!aClass.isAbstract() && !contains(document, VarNameBuilder.buildForAction(aClass))) {
                    Element beanElement = generateBeanElementForApplicationContextStruts(aClass);
                    rootElement.add(beanElement);
                    modified = true;
                }
            }

            if (modified) {
                XMLWriter xmWriter = getXMLWriter(inputXml);
                xmWriter.write(document);
                xmWriter.close();
                /**
                 * Lo abre nuevamente para eliminar la cadena xmlns="" que
                 * automáticamente se agrega como attributo del bean al ser
                 * agregado como hijo de un tag que lleva un formato para Spring
                 */
                String file = FileUtils.readFileToString(new File(inputXml));
                file = file.replace("xmlns=\"\" ", "");
                FileUtils.writeStringToFile(new File(inputXml), file);

                generationInfo.addModifiedFile(inputXml);
            }

        } catch (IOException e) {
            logger.error("Could not configure " + inputXml + ": File is missing.");
            throw new InfraestructureException(e);
        } catch (DocumentException e) {
            logger.error("Could not configure " + inputXml + ": File malformed.");
            throw new InfraestructureException(e);
        }

    }

    /**
     * @param string
     * @param string2
     * @param class1
     * @return
     */
    private static Element generateBeanElementForApplicationContextStruts(final Class aClass) {

        BaseElement beanElement = new BaseElement("bean");

        beanElement.addAttribute("id", VarNameBuilder.buildForAction(aClass));
        beanElement.addAttribute("class", MetaModelUtils.getGroupId(aClass) + ".webapp.action."
                + aClass.getSimpleName() + "Action");
        beanElement.addAttribute("scope", "prototype");

        DefaultElement propertyElement = new DefaultElement("property");
        propertyElement.addAttribute("name", VarNameBuilder.buildForService(aClass));
        propertyElement.addAttribute("ref", "service." + VarNameBuilder.buildForService(aClass));

        beanElement.add(propertyElement);

        return beanElement;
    }

    private static boolean contains(final Document document, final String beanName) {
        return document.asXML().contains(beanName);
    }
}
