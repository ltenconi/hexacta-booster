/*
 * [legal notice here]
 */
package com.hexacta.booster.codegeneration.view;

import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.util.List;

import junit.framework.TestCase;

import org.apache.commons.io.FileUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.dom4j.tree.BaseElement;
import org.dom4j.tree.DefaultElement;

import test.hexacta.booster.providers.CodeGeneratorConfigurationProvider;

import com.hexacta.booster.codegeneration.GenerationInfo;
import com.hexacta.booster.codegeneration.TextFile;
import com.hexacta.booster.codegeneration.configuration.CodeGeneratorConfiguration;
import com.hexacta.booster.codegeneration.metamodel.Class;
import com.hexacta.booster.codegeneration.view.struts2.Struts2MenuGenerator;
import com.hexacta.booster.codegeneration.view.struts2.Struts2StrutsFileGenerator;
import com.hexacta.booster.utilities.PathBuilder;
import com.hexacta.booster.utilities.VarNameBuilder;

/**
 * This class tests ViewUtils class.
 * 
 * Created on 17/04/2009
 * 
 * @author vmartz
 * 
 */
public class ViewUtilsTest extends TestCase {

    /**
     * {@inheritDoc}
     */
    @Override
    protected void tearDown() throws Exception {
        CodeGeneratorConfigurationProvider codeGeneratorConfigurationProvider = new CodeGeneratorConfigurationProvider();
        CodeGeneratorConfiguration codeGeneratorConfiguration = null;
        codeGeneratorConfiguration = codeGeneratorConfigurationProvider.getCodeGeneratorConfigurationForOneClass();

        cleanFile(
                PathBuilder.buildForViewResources(codeGeneratorConfiguration.getDirectoryInfo()),
                "struts.xml",
                "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n\n<struts>\n   <package name=\"default222\"/>\n   <package name=\"default\">\n   </package>\n</struts>");

        cleanFile(PathBuilder.buildForViewCommon(codeGeneratorConfiguration.getDirectoryInfo()), "menu.jsp",
                "<ul>\n</ul>");

        cleanFile(PathBuilder.buildForViewResources(codeGeneratorConfiguration.getDirectoryInfo()),
                "ApplicationResources.properties", "# Tiene que agregar abajo");

        String string = "# -- display page messages --\n" + "# -- Persona-START" + "\n" + "\n   " + "\npersona.id= Id"
                + "\n# -- Persona-END" + "\n" + "\n  " + "\n# -- active users page --"
                + "\nactiveUsers.title=Active Users";

        cleanFile(PathBuilder.buildForViewResources(codeGeneratorConfiguration.getDirectoryInfo()),
                "ApplicationResourcesWithContent.properties", string);

        cleanFile(PathBuilder.buildForViewWebInf(codeGeneratorConfiguration.getDirectoryInfo()),
                "applicationContext-struts.xml", "<beans>\n</beans>");

        cleanFile(
                PathBuilder.buildForViewResources(codeGeneratorConfiguration.getDirectoryInfo()),
                "struts.xml",
                "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n\n<struts>\n   <package name=\"default222\"/>\n   <package name=\"default\">\n   </package>\n</struts>");

    }

    public void testGenerateForMetamodelToAddInStrutsXML() throws IOException {

        CodeGeneratorConfigurationProvider codeGeneratorConfigurationProvider = new CodeGeneratorConfigurationProvider();
        CodeGeneratorConfiguration codeGeneratorConfiguration = null;
        codeGeneratorConfiguration = codeGeneratorConfigurationProvider.getCodeGeneratorConfigurationForOneClass();

        Struts2StrutsFileGenerator struts2StrutsFileGenerator = new Struts2StrutsFileGenerator();

        GenerationInfo generationInfo = new GenerationInfo();

        ViewUtils.generateForMetamodelToAddInStrutsXML(struts2StrutsFileGenerator, codeGeneratorConfiguration,
                generationInfo);

        assertEquals(1, generationInfo.getModifiedFiles().size());

        // cleanFile(
        // PathBuilder.buildForViewResources(codeGeneratorConfiguration.
        // getDirectoryInfo()),
        // "struts.xml",
        // "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n\n<struts>\n   <package name=\"default222\"/>\n   <package name=\"default\">\n   </package>\n</struts>"
        // );

    }

    public void testGenerateForMetamodelToReplace() throws IOException {

        CodeGeneratorConfigurationProvider codeGeneratorConfigurationProvider = new CodeGeneratorConfigurationProvider();
        CodeGeneratorConfiguration codeGeneratorConfiguration = null;
        codeGeneratorConfiguration = codeGeneratorConfigurationProvider.getCodeGeneratorConfigurationForOneClass();

        Struts2MenuGenerator menuGen = new Struts2MenuGenerator();

        GenerationInfo generationInfo = new GenerationInfo();

        ViewUtils.generateForMetamodelToReplace(menuGen, codeGeneratorConfiguration, generationInfo, "</ul>");

        assertEquals(1, generationInfo.getModifiedFiles().size());

        ViewUtils.generateForMetamodelToReplace(menuGen, codeGeneratorConfiguration, generationInfo, "</ul>");

        assertEquals(1, generationInfo.getModifiedFiles().size());

        // cleanFile(PathBuilder.buildForViewCommon(codeGeneratorConfiguration.
        // getDirectoryInfo()), "menu.jsp",
        // "<ul>\n</ul>");
    }

    /**
     * @param string
     * @param buildForViewCommon
     * @param string2
     * @throws IOException
     */
    private void cleanFile(final String path, final String name, final String text) throws IOException {

        String pathName = path + name;
        FileUtils.writeStringToFile(new File(pathName), text);

    }

    public void testGenerateForMetamodelToAppendApplicationResources() throws IOException {
        CodeGeneratorConfigurationProvider codeGeneratorConfigurationProvider = new CodeGeneratorConfigurationProvider();
        CodeGeneratorConfiguration codeGeneratorConfiguration = null;
        codeGeneratorConfiguration = codeGeneratorConfigurationProvider.getCodeGeneratorConfigurationForOneClass();

        ApplicationResourcesGenerator appResGenerator = new ApplicationResourcesGenerator();

        GenerationInfo generationInfo = new GenerationInfo();

        ViewUtils.generateForMetamodelToAppendApplicationResources(appResGenerator, codeGeneratorConfiguration,
                generationInfo);

        assertEquals(1, generationInfo.getModifiedFiles().size());

        //cleanFile(PathBuilder.buildForViewResources(codeGeneratorConfiguration
        // .getDirectoryInfo()),
        // "ApplicationResources.properties", "# Tiene que agregar abajo");
    }

    public void testGenerateForMetamodelToAppendApplicationResourcesWithSomeContent() throws Exception {
        CodeGeneratorConfigurationProvider codeGeneratorConfigurationProvider = new CodeGeneratorConfigurationProvider();
        CodeGeneratorConfiguration codeGeneratorConfiguration = null;
        codeGeneratorConfiguration = codeGeneratorConfigurationProvider.getCodeGeneratorConfigurationForOneClass();

        ApplicationResourcesGenerator appResGenerator = new ApplicationResourcesGenerator();

        Class aClass = codeGeneratorConfiguration.getGenerateCodeFor().getClass("test.model.Persona");

        TextFile generated = appResGenerator.generate(aClass, codeGeneratorConfiguration);

        ViewUtils.cleanExistingFile(aClass, PathBuilder.buildForViewResources(codeGeneratorConfiguration
                .getDirectoryInfo())
                + "ApplicationResourcesWithContent.properties");

        String cleanedFile = FileUtils.readFileToString(new File(PathBuilder
                .buildForViewResources(codeGeneratorConfiguration.getDirectoryInfo())
                + "ApplicationResourcesWithContent.properties"));

        assertFalse(cleanedFile.contains(VarNameBuilder.buildForEntity(aClass)));
        assertFalse(cleanedFile.contains(aClass.getSimpleName()));

        ViewUtils.appendToFile(generated.getText(), PathBuilder.buildForViewResources(codeGeneratorConfiguration
                .getDirectoryInfo()), "ApplicationResourcesWithContent.properties");

        String appenedFile = FileUtils.readFileToString(new File(PathBuilder
                .buildForViewResources(codeGeneratorConfiguration.getDirectoryInfo())
                + "ApplicationResourcesWithContent.properties"));

        assertTrue(appenedFile.contains(VarNameBuilder.buildForEntity(aClass)));
        assertTrue(appenedFile.contains(aClass.getSimpleName()));

        // String string = "# -- display page messages --\n" +
        // "# -- Persona-START" + "\n" + "\n   " + "\npersona.id= Id"
        // + "\n# -- Persona-END" + "\n" + "\n  " +
        // "\n# -- active users page --"
        // + "\nactiveUsers.title=Active Users";

        //cleanFile(PathBuilder.buildForViewResources(codeGeneratorConfiguration
        // .getDirectoryInfo()),
        // "ApplicationResourcesWithContent.properties", string);

    }

    public void testTextExistsInFile() throws Exception {

        File aFile = new File("./file.txt");
        FileUtils.writeStringToFile(aFile, "Este es el texto del archivo");

        boolean exists = ViewUtils.textExistsInFile("es el text", "./", "file.txt");

        assertTrue(exists);

        exists = ViewUtils.textExistsInFile("Virsch", "./", "file.txt");

        assertTrue(!exists);

        aFile.delete();

    }

    public void testAddActionBeansToApplicationContextStruts() throws Exception {
        CodeGeneratorConfigurationProvider codeGeneratorConfigurationProvider = new CodeGeneratorConfigurationProvider();
        CodeGeneratorConfiguration codeGeneratorConfiguration = null;
        codeGeneratorConfiguration = codeGeneratorConfigurationProvider.getCodeGeneratorConfigurationForOneClass();

        cleanFile(PathBuilder.buildForViewWebInf(codeGeneratorConfiguration.getDirectoryInfo()),
                "applicationContext-struts.xml", "<beans>\n</beans>");

        GenerationInfo generationInfo = new GenerationInfo();

        ViewUtils.addActionBeansToApplicationContextStruts(codeGeneratorConfiguration, generationInfo);

        assertEquals(1, generationInfo.getModifiedFiles().size());

        SAXReader saxReader = new SAXReader();
        Document document = saxReader.read(PathBuilder
                .buildForViewWebInf(codeGeneratorConfiguration.getDirectoryInfo())
                + "applicationContext-struts.xml");

        Element root = document.getRootElement();

        assertEquals(1, root.elements().size());
        Element bean = (Element) root.elements().get(0);
        assertEquals("bean", bean.getName());
        assertEquals(3, bean.attributeCount());
        assertEquals("personaAction", bean.attributeValue("id"));
        assertEquals("test.webapp.action.PersonaAction", bean.attributeValue("class"));
        assertEquals("prototype", bean.attributeValue("scope"));

        Element property = (Element) bean.elements().get(0);
        assertEquals("property", property.getName());
        assertEquals("service.personaService", property.attributeValue("ref"));

        /**
         * Lo ejecutamos nuevamente para chequear que si el bean ya está
         * generado no genere uno nuevo.
         */

        ViewUtils.addActionBeansToApplicationContextStruts(codeGeneratorConfiguration, generationInfo);

        assertEquals(1, generationInfo.getModifiedFiles().size());

        // cleanFile(PathBuilder.buildForViewWebInf(codeGeneratorConfiguration.
        // getDirectoryInfo()),
        // "applicationContext-struts.xml", "<beans>\n</beans>");

    }

    public void testAddNodesToElement() {
        try {
            String strutsString = "<package></package>";
            String actionsString = "<actions>" + "<action name=\"persons\" class=\"personAction\" method=\"list\">"
                    + "<result>/WEB-INF/pages/personList.jsp</result>" + "</action>"
                    + "<action name=\"editPerson\" class=\"personAction\" method=\"edit\">"
                    + "<result>/WEB-INF/pages/personForm.jsp</result>" + "</action>"
                    + "<action name=\"savePerson\" class=\"personAction\" method=\"save\">" + "</action>"
                    + "</actions>";

            StringReader strutsStringReader = new StringReader(strutsString);
            StringReader actionsStringReader = new StringReader(actionsString);

            SAXReader saxReader = new SAXReader();

            Document struts = saxReader.read(strutsStringReader);
            Document actions = saxReader.read(actionsStringReader);

            Element packageDefault = struts.getRootElement();
            List<Element> elements = actions.getRootElement().elements();

            assertEquals(0, packageDefault.elements().size());

            ViewUtils.addNodesToElement(packageDefault, elements);

            assertEquals(3, packageDefault.elements().size());

            ViewUtils.addNodesToElement(packageDefault, elements);

            assertEquals(3, packageDefault.elements().size());

        } catch (DocumentException e) {
            fail(e.getMessage());
        }

    }

    public void testAreEquals() {

        BaseElement beanElement1 = new BaseElement("bean");

        beanElement1.addAttribute("attb1", "info1");
        beanElement1.addAttribute("attb2", "info2");
        beanElement1.addAttribute("attb3", "info3");

        BaseElement beanElement2 = new BaseElement("bean");

        beanElement2.addAttribute("attb1", "info1");
        beanElement2.addAttribute("attb2", "info2");
        beanElement2.addAttribute("attb3", "info3");

        boolean areEquals = ViewUtils.areEquals(beanElement1, beanElement2);

        assertTrue(areEquals);

        BaseElement beanElement3 = new BaseElement("bean");

        beanElement3.addAttribute("attb1", "info1");
        beanElement3.addAttribute("attb2", "info2");
        beanElement3.addAttribute("attb3", "info4");

        boolean areEqualsAgain = ViewUtils.areEquals(beanElement1, beanElement3);

        assertFalse(areEqualsAgain);

        BaseElement beanElement4 = new BaseElement("bean");

        beanElement4.addAttribute("attb1", "info1");
        beanElement4.addAttribute("attb2", "info2");
        beanElement4.addAttribute("attb3", "info3");

        DefaultElement propertyElement = new DefaultElement("property");
        propertyElement.addAttribute("name", "aName");
        propertyElement.addAttribute("ref", "aRef");
        beanElement4.add(propertyElement);

        boolean areEquals4 = ViewUtils.areEquals(beanElement1, beanElement4);

        assertFalse(areEquals4);
    }

    public void testContainsElement() throws Exception {

        String strutsString = "<package>" + "<action name=\"persons\" class=\"personAction\" method=\"list\">"
                + "<result>/WEB-INF/pages/personList.jsp</result>" + "</action>"
                + "<action name=\"editPerson\" class=\"personAction\" method=\"edit\">"
                + "<result>/WEB-INF/pages/personForm.jsp</result>" + "</action>"
                + "<action name=\"savePerson\" class=\"personAction\" method=\"save\">" + "</action>" + "</package>";

        String actionString = "<action name=\"persons\" class=\"personAction\" method=\"list\">"
                + "<result>/WEB-INF/pages/personList.jsp</result>" + "</action>";

        StringReader strutsStringReader = new StringReader(strutsString);
        StringReader actionStringReader = new StringReader(actionString);

        SAXReader saxReader = new SAXReader();

        Document strutsFile = saxReader.read(strutsStringReader);
        Document actionFile = saxReader.read(actionStringReader);

        Element packageDefault = strutsFile.getRootElement();
        Element action = actionFile.getRootElement();

        boolean contains = ViewUtils.containsElement(packageDefault, action);

        assertTrue(contains);

        String actionString2 = "<action name=\"persons\" class=\"personAction\" method=\"list\"></action>";
        StringReader actionStringReader2 = new StringReader(actionString2);
        Document actionFile2 = saxReader.read(actionStringReader2);
        Element action2 = actionFile2.getRootElement();

        contains = ViewUtils.containsElement(packageDefault, action2);

        assertFalse(contains);

    }

    public void testAddToXMLTwice() throws Exception {

        CodeGeneratorConfigurationProvider codeGeneratorConfigurationProvider = new CodeGeneratorConfigurationProvider();
        CodeGeneratorConfiguration codeGeneratorConfiguration = null;
        codeGeneratorConfiguration = codeGeneratorConfigurationProvider.getCodeGeneratorConfigurationForOneClass();

        cleanFile(
                PathBuilder.buildForViewResources(codeGeneratorConfiguration.getDirectoryInfo()),
                "struts.xml",
                "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n\n<struts>\n   <package name=\"default222\"/>\n   <package name=\"default\">\n   </package>\n</struts>");

        Struts2StrutsFileGenerator struts2StrutsFileGenerator = new Struts2StrutsFileGenerator();

        TextFile textFile = struts2StrutsFileGenerator.generate(codeGeneratorConfiguration.getGenerateCodeFor()
                .getClass("test.model.Persona"), codeGeneratorConfiguration);

        boolean added = ViewUtils.addToXML(textFile.getText(), PathBuilder
                .buildForViewResources(codeGeneratorConfiguration.getDirectoryInfo()), "struts.xml");

        assertTrue(added);

        /**
         * Ejecutamos nuevamente para chequear que no agrega elementos que ya
         * tiene
         */

        added = ViewUtils.addToXML(textFile.getText(), PathBuilder.buildForViewResources(codeGeneratorConfiguration
                .getDirectoryInfo()), "struts.xml");

        assertFalse(added);

        // cleanFile(
        // PathBuilder.buildForViewResources(codeGeneratorConfiguration.
        // getDirectoryInfo()),
        // "struts.xml",
        // "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n\n<struts>\n   <package name=\"default222\"/>\n   <package name=\"default\">\n   </package>\n</struts>"
        // );

    }

    public void testAddToXMLWithExistentElements() throws Exception {

        CodeGeneratorConfigurationProvider codeGeneratorConfigurationProvider = new CodeGeneratorConfigurationProvider();
        CodeGeneratorConfiguration codeGeneratorConfiguration = null;
        codeGeneratorConfiguration = codeGeneratorConfigurationProvider.getCodeGeneratorConfigurationForOneClass();

        Struts2StrutsFileGenerator struts2StrutsFileGenerator = new Struts2StrutsFileGenerator();

        TextFile textFile = struts2StrutsFileGenerator.generate(codeGeneratorConfiguration.getGenerateCodeFor()
                .getClass("test.model.Persona"), codeGeneratorConfiguration);

        boolean added = ViewUtils.addToXML(textFile.getText(), PathBuilder
                .buildForViewResources(codeGeneratorConfiguration.getDirectoryInfo()), "struts2.xml");

        assertFalse(added);

    }
}
