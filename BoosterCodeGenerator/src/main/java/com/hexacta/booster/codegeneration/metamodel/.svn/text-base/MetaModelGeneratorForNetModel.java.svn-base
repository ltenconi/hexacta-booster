package com.hexacta.booster.codegeneration.metamodel;

import java.io.File;
import java.util.List;

import org.apache.log4j.Logger;
import org.codehaus.plexus.util.cli.CommandLineException;
import org.codehaus.plexus.util.cli.CommandLineUtils;
import org.codehaus.plexus.util.cli.Commandline;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;

import com.hexacta.booster.codegeneration.configuration.ClassFinderDotNet;
import com.hexacta.booster.codegeneration.configuration.ClassList;
import com.hexacta.booster.codegeneration.configuration.NotSupportedClassException;

/**
 * 
 */
public class MetaModelGeneratorForNetModel {
    /**
     * Log4j logger.
     */
    static final Logger logger = Logger.getLogger(MetaModelGeneratorForNetModel.class);

    public ClassList generate(final String netModelPath)
            throws NotSupportedClassException {

        //MetaModelGeneratorForNetModel.executeXMLMetaModelGenerator(executableXMLPath, netModelPath);
        return generateMetaModelfromXML(netModelPath + "temp/");
    }

    public ClassList generateMetaModelfromXML(final String xmlPath) throws NotSupportedClassException {

        ClassList aMetaClassList = new ClassList();
        ClassList classList = new ClassList();

        File directory = new File(xmlPath);
        File[] files = directory.listFiles();

        for (File file : files) {
            String aXMLMetaModelFilePath = file.toString();
            Document anXmlDoc = getXmlDoc(aXMLMetaModelFilePath);

            if (anXmlDoc != null) {
                String currentClassName = anXmlDoc.getRootElement().getAttributeValue("name");
                if (classList.hasClass(currentClassName)) {
                    aMetaClassList.addClass(classList.getClass(currentClassName));
                } else {
                    aMetaClassList.addClass(generate(anXmlDoc, classList, xmlPath));
                }
            } else {
                logger.error("No se pudo recuperar el archivo: " + aXMLMetaModelFilePath);
            }
        }

        // borrar los xml

        return aMetaClassList;
    }

    public Class generate(final Document anXmlDoc, final ClassList classList, final String xmlPath)
            throws NotSupportedClassException {

        if (anXmlDoc != null) {

            String currentClassName = anXmlDoc.getRootElement().getAttributeValue("fullName");

            if (!classList.hasClass(currentClassName)) {

                Class metaModelClass = new Class(currentClassName);
                classList.addClass(metaModelClass);

                metaModelClass.setName(anXmlDoc.getRootElement().getAttributeValue("fullName"));
                metaModelClass.setSuperClass(getSuperClass(anXmlDoc, classList, xmlPath));
                metaModelClass.setSimpleName(anXmlDoc.getRootElement().getAttributeValue("simpleName"));
                metaModelClass
                        .setArray(anXmlDoc.getRootElement().getAttributeValue("isArray").equalsIgnoreCase("true") ? true
                                : false);
                metaModelClass.setPrimitive(anXmlDoc.getRootElement().getAttributeValue("isPrimitive")
                        .equalsIgnoreCase("true") ? true : false);
                metaModelClass.setAbstract(anXmlDoc.getRootElement().getAttributeValue("isAbstract").equalsIgnoreCase(
                        "true") ? true : false);
                metaModelClass.setPackageName(anXmlDoc.getRootElement().getAttributeValue("namespace"));
                metaModelClass.setDeclaredFields(generateDeclaredFields(anXmlDoc, classList, xmlPath));
                metaModelClass.setConstructors(generateConstructors(anXmlDoc, classList));
                metaModelClass.setMethods(generateMethods(anXmlDoc));

                return metaModelClass;
            } else
                return classList.getClass(currentClassName);
        }
        return null;
    }

    private Method[] generateMethods(final Document anXmlDoc) {

        List<?> methodsNodeList = anXmlDoc.getRootElement().getChildren("methods");

        Element methodsTag = (Element) methodsNodeList.get(0);

        List<?> methodsList = methodsTag.getChildren("method");

        Method[] metaModelClassMethods = new Method[methodsList.size()];

        for (int i = 0; i < methodsList.size(); i++) {

            Element aMethod = (Element) methodsList.get(i);

            String methodName = aMethod.getAttributeValue("name");

            Method method = new Method(methodName);

            metaModelClassMethods[i] = method;

        }
        return metaModelClassMethods;
    }

    private Constructor[] generateConstructors(final Document anXmlDoc,
            @SuppressWarnings("unused") final ClassList classList) {

        List<?> constructorsNodeList = anXmlDoc.getRootElement().getChildren("constructors");

        Element constructorsTag = (Element) constructorsNodeList.get(0);

        List<?> constructorList = constructorsTag.getChildren("constructor");

        Constructor[] metaModelClassConstructors = new Constructor[constructorList.size()];

        for (int i = 0; i < constructorList.size(); i++) {

            Element aConstructor = (Element) constructorList.get(i);

            String constructorName = anXmlDoc.getRootElement().getAttributeValue("simpleName");

            List<?> parametersList = aConstructor.getChildren("parameters");

            Element parameterTag = (Element) parametersList.get(0);

            List<?> parameterList = parameterTag.getChildren("parameter");

            Type[] parametersType = new Type[parameterList.size()];

            for (int j = 0; j < parameterList.size(); j++) {

                Element aParameter = (Element) parameterList.get(j);
                String type = aParameter.getAttributeValue("type");
                Type aType = new Type(type, null, false, false, null, false);
                parametersType[j] = aType;
            }

            Constructor aConstructorModel = new Constructor(constructorName, parametersType);
            metaModelClassConstructors[i] = aConstructorModel;

        }
        return metaModelClassConstructors;
    }

    private Field[] generateDeclaredFields(final Document anXmlDoc, final ClassList classList, final String xmlPath)
            throws NotSupportedClassException {

        List<?> fieldsNodeList = anXmlDoc.getRootElement().getChildren("fields");

        Element fieldsTag = (Element) fieldsNodeList.get(0);

        List<?> fieldsList = fieldsTag.getChildren("field");

        Field[] declaredFields = new Field[fieldsList.size()];

        for (int i = 0; i < fieldsList.size(); i++) {

            Element fieldElement = (Element) fieldsList.get(i);

            String fieldName = fieldElement.getAttributeValue("name");
            String declaringClass = fieldElement.getAttributeValue("declaringClass");

            Element typeElement = fieldElement.getChild("type");
            if (typeElement == null) {
                typeElement = fieldElement.getChild("parameterizedType");
            }

            String name = typeElement.getAttributeValue("fullName");
            String simpleName = typeElement.getAttributeValue("name");
            boolean isArray = typeElement.getAttributeValue("isArray").equalsIgnoreCase("true") ? true : false;
            boolean isPrimitive = typeElement.getAttributeValue("isPrimitive").equalsIgnoreCase("true") ? true : false;
            Class componentType = null;
            if (isArray) {
                try {
                    componentType = ClassFinderDotNet.findForType(typeElement.getAttributeValue("componentType"));
                } catch (Exception e) {
                    componentType = generate(getXmlDoc(xmlPath + typeElement.getAttributeValue("componentType")
                            + ".xml"), classList, xmlPath);
                }

            }
            boolean isAbstract = typeElement.getAttributeValue("isAbstract").equalsIgnoreCase("true") ? true : false;

            Type aType;
            if (fieldElement.getChild("parameterizedType") != null) {
                List<?> actualTypeArgumentElements = typeElement.getChildren("actualTypeArgument");
                String[] actualTypeArguments = new String[actualTypeArgumentElements.size()];
                int j = 0;
                for (Object name2 : actualTypeArgumentElements) {
                    Element anActualTypeArgumentElement = (Element) name2;
                    actualTypeArguments[j] = anActualTypeArgumentElement.getAttributeValue("Name");
                    j++;
                }

                aType = new ParameterizedType(name, simpleName, isArray, isPrimitive, componentType, isAbstract,
                        actualTypeArguments);
            } else {
                aType = new Type(name, simpleName, isArray, isPrimitive, componentType, isAbstract);
            }

            Field field = new Field(fieldName, aType, classList.getClass(declaringClass));

            declaredFields[i] = field;
        }

        return declaredFields;
    }

    private Class getSuperClass(final Document anXmlDoc, final ClassList classList, final String xmlPath)
            throws NotSupportedClassException {

        String currentSuperClass = anXmlDoc.getRootElement().getAttributeValue("superClass");

        if (currentSuperClass.equalsIgnoreCase("System.Object"))
            return createClassForObjectClass(currentSuperClass);
        else
            return findOrCreateClassForSuperclass(anXmlDoc, classList, xmlPath);
    }

    private Class findOrCreateClassForSuperclass(final Document anXmlDoc, final ClassList classList,
            final String xmlPath) throws NotSupportedClassException {

        Class superClass;

        if (!anXmlDoc.getRootElement().getAttributeValue("superClass").toString().equalsIgnoreCase("System.Object")) {
            if (classList.hasClass(anXmlDoc.getRootElement().getAttributeValue("superClass").toString())) {
                superClass = classList.getClass(anXmlDoc.getRootElement().getAttributeValue("superClass").toString());
            } else {
                Document anotherXmlDoc = getXmlDoc(xmlPath
                        + anXmlDoc.getRootElement().getAttributeValue("superClass").toString() + ".xml");
                superClass = generate(anotherXmlDoc, classList, xmlPath);
            }
        } else {
            superClass = null;
        }

        return superClass;
    }

    private Class createClassForObjectClass(final String currentSuperClass) {

        Class superClass;
        superClass = new Class(currentSuperClass, "Object", null, null, false, false, "System", new Constructor[0],
                false, null);
        return superClass;
    }

    private Document getXmlDoc(final String aNetFileClass) {
        try {
            SAXBuilder builder = new SAXBuilder(false);
            Document document = builder.build(aNetFileClass);
            return document;
        } catch (Exception e) {
            logger.error("No se pudo recuperar el archivo: " + aNetFileClass);
        }
        return null;
    }

    private static void executeXMLMetaModelGenerator(final String executablePath, final String dllModelPath) {

        Commandline cl = new Commandline();
        cl.addArguments(new String[] { executablePath + " \"" + dllModelPath });
        CommandLineUtils.StringStreamConsumer stderr = new CommandLineUtils.StringStreamConsumer();
        try {
            CommandLineUtils.executeCommandLine(cl, new CommandLineUtils.StringStreamConsumer(), stderr);
        } catch (CommandLineException e) {
            logger.error(e.getMessage());
        }

    }

}
