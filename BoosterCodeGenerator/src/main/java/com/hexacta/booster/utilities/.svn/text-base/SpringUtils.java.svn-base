package com.hexacta.booster.utilities;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Iterator;

import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.dom4j.tree.DefaultElement;

import com.hexacta.booster.codegeneration.GenerationInfo;
import com.hexacta.booster.codegeneration.configuration.ClassList;
import com.hexacta.booster.codegeneration.configuration.DirectoryInfo;
import com.hexacta.booster.codegeneration.metamodel.Class;
import com.hexacta.booster.project.configuration.OrmTool;

/**
 * 
 */
public final class SpringUtils {

    /**
     * Log4j logger.
     */
    static final Logger logger = Logger.getLogger(SpringUtils.class);

    private SpringUtils() {
    }

    public static void addBeansOnDAOContextXML(final ClassList aClassList, final DirectoryInfo directoryInfo,
            final OrmTool ormTool, final GenerationInfo generationInfo) {

        String inputXml = directoryInfo.getDaoContextFullPath();
        boolean modified = false;

        SAXReader saxReader = new SAXReader();
        try {
            org.dom4j.Document document = saxReader.read(inputXml);

            Element rootElement = document.getRootElement();
            for (Iterator<Class> iterator = aClassList.iterator(); iterator.hasNext();) {
                Class aClass = iterator.next();
                if (!aClass.isAbstract() && !contains(document, "persistence." + VarNameBuilder.buildForDAO(aClass))) {
                    String daoClassName = JavaClassNameBuilder.buildForDAO(aClass);
                    Element beanElement = generateBeanElementFor("persistence." + VarNameBuilder.buildForDAO(aClass),
                            PackageNameBuilder.buildForDAO(aClass) + "." + daoClassName, ormTool);
                    rootElement.add(beanElement);
                    modified = true;
                }
            }

            XMLWriter xmWriter = getXMLWriter(inputXml);
            xmWriter.write(document);
            xmWriter.close();
            if (modified) {
                generationInfo.addModifiedFile(inputXml);
            }

        } catch (IOException e) {
            logger.error("Could not configure " + directoryInfo.getDaoContextFullPath() + ": File is missing.");

        } catch (DocumentException e) {
            logger.error("Could not configure " + directoryInfo.getDaoContextFullPath() + ": File malformed.");

        }
    }

    private static Element generateBeanElementFor(final String idAttribute, final String classAttribute,
            final OrmTool ormTool) {
        Element element = null;
        if (ormTool.isHibernate()) {
            element = generateBeanElementOnDAOContextForHibernate(idAttribute, classAttribute);
        } else {
            if (ormTool.isJpaHibernate()) {
                element = generateBeanElementOnDAOContextForJpaHibernate(idAttribute, classAttribute);
            }
        }

        return element;
    }

    private static Element generateBeanElementOnDAOContextForJpaHibernate(final String idAttribute,
            final String classAttribute) {

        DefaultElement beanElement = new DefaultElement("bean");

        beanElement.addAttribute("id", idAttribute);
        beanElement.addAttribute("class", classAttribute);

        DefaultElement sessionFactoryElement = new DefaultElement("property");
        sessionFactoryElement.addAttribute("name", "entityManagerFactory");
        DefaultElement refElement = new DefaultElement("ref");
        refElement.addAttribute("bean", "entityManagerFactory");
        sessionFactoryElement.add(refElement);

        beanElement.add(sessionFactoryElement);

        return beanElement;
    }

    private static Element generateBeanElementOnDAOContextForHibernate(final String idAttribute,
            final String classAttribute) {
        DefaultElement beanElement = new DefaultElement("bean");

        beanElement.addAttribute("id", idAttribute);
        beanElement.addAttribute("class", classAttribute);

        DefaultElement sessionFactoryElement = new DefaultElement("property");
        sessionFactoryElement.addAttribute("name", "sessionFactory");
        DefaultElement refElement = new DefaultElement("ref");
        refElement.addAttribute("bean", "persistence.sessionFactory");
        sessionFactoryElement.add(refElement);

        beanElement.add(sessionFactoryElement);

        return beanElement;
    }

    private static boolean contains(final Document document, final String beanName) {
        return document.asXML().contains(beanName);
    }

    public static void addBeansOnServiceContextXML(final ClassList aClassList, final DirectoryInfo directoryInfo,
            final GenerationInfo generationInfo) {

        String inputXml = directoryInfo.getServiceContextFullPath();
        boolean modified = false;
        SAXReader saxReader = new SAXReader();
        try {
            org.dom4j.Document document = saxReader.read(inputXml);

            Element rootElement = document.getRootElement();
            for (Iterator<Class> iterator = aClassList.iterator(); iterator.hasNext();) {
                Class aClass = iterator.next();
                if (!aClass.isAbstract() && !contains(document, "service." + VarNameBuilder.buildForService(aClass))) {
                    Element beanElement = generateBeanElementForService("service."
                            + VarNameBuilder.buildForService(aClass), PackageNameBuilder
                            .buildForServiceInterfaceImpl(aClass)
                            + "." + JavaClassNameBuilder.buildForServiceInterfaceImpl(aClass), aClass);
                    rootElement.add(beanElement);
                    modified = true;
                }
            }

            XMLWriter xmWriter = getXMLWriter(inputXml);
            xmWriter.write(document);
            xmWriter.close();
            if (modified) {
                generationInfo.addModifiedFile(inputXml);
            }

        } catch (IOException e) {
            logger.error("Could not configure " + directoryInfo.getServiceContextFullPath() + ": File is missing.");

        } catch (DocumentException e) {
            logger.error("Could not configure " + directoryInfo.getServiceContextFullPath() + ": File malformed.");

        }
    }

    private static Element generateBeanElementForService(final String beanId, final String beanClass,
            final Class entityClass) {

        DefaultElement beanElement = new DefaultElement("bean");

        beanElement.addAttribute("id", beanId);
        beanElement.addAttribute("class", beanClass);

        DefaultElement daoElement = new DefaultElement("property");
        daoElement.addAttribute("name", VarNameBuilder.buildForDAO(entityClass));
        DefaultElement refElementDAO = new DefaultElement("ref");
        refElementDAO.addAttribute("bean", "persistence." + VarNameBuilder.buildForDAO(entityClass));
        daoElement.add(refElementDAO);

        DefaultElement factoryElement = new DefaultElement("property");
        factoryElement.addAttribute("name", "factory");
        DefaultElement refElementFactory = new DefaultElement("ref");
        refElementFactory.addAttribute("bean", "service.builderFactory");
        factoryElement.add(refElementFactory);

        DefaultElement beanMapperElement = new DefaultElement("property");
        beanMapperElement.addAttribute("name", "beanMapper");
        DefaultElement refElementBeanMapper = new DefaultElement("ref");
        refElementBeanMapper.addAttribute("bean", "service.beanMapper");
        beanMapperElement.add(refElementBeanMapper);

        beanElement.add(daoElement);
        beanElement.add(factoryElement);
        beanElement.add(beanMapperElement);

        return beanElement;
    }

    private static XMLWriter getXMLWriter(final String outputXMLPath) throws FileNotFoundException,
            UnsupportedEncodingException {

        OutputFormat outFormat = OutputFormat.createPrettyPrint();
        FileOutputStream out = new FileOutputStream(new File(outputXMLPath));
        XMLWriter xmlWriter = new XMLWriter(out, outFormat);
        return xmlWriter;

    }

    public static void addBeansOnServiceTestContextXML(final ClassList aClassList, final DirectoryInfo directoryInfo,
            final GenerationInfo generationInfo) {

        String inputXml = directoryInfo.getServiceTestContextFullPath();
        boolean modified = false;
        SAXReader saxReader = new SAXReader();
        try {
            org.dom4j.Document document = saxReader.read(inputXml);

            Element rootElement = document.getRootElement();
            for (Iterator<Class> iterator = aClassList.iterator(); iterator.hasNext();) {
                Class aClass = iterator.next();
                if (!aClass.isAbstract() && !contains(document, "service." + VarNameBuilder.buildForService(aClass))) {
                    Element beanElement = generateBeanElementForService("service."
                            + VarNameBuilder.buildForService(aClass), PackageNameBuilder
                            .buildForServiceImplementationMock(aClass)
                            + "." + JavaClassNameBuilder.buildForServiceInterfaceImpl(aClass), aClass);
                    rootElement.add(beanElement);
                    modified = true;
                }
            }

            XMLWriter xmWriter = getXMLWriter(inputXml);
            xmWriter.write(document);
            xmWriter.close();
            if (modified) {
                generationInfo.addModifiedFile(inputXml);
            }

        } catch (IOException e) {
            logger.error("Could not configure " + directoryInfo.getServiceTestContextFullPath() + ": File is missing.");

        } catch (DocumentException e) {
            logger.error("Could not configure " + directoryInfo.getServiceTestContextFullPath() + ": File malformed.");

        }
    }

}
