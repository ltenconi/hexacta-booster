package com.hexacta.booster.codegeneration.view;

import org.apache.log4j.Logger;

import com.hexacta.booster.codegeneration.BoosterGenerator;
import com.hexacta.booster.codegeneration.GenerationInfo;
import com.hexacta.booster.codegeneration.configuration.CodeGeneratorConfiguration;
import com.hexacta.booster.codegeneration.configuration.NotSupportedClassException;
import com.hexacta.booster.codegeneration.view.struts2.Struts2ActionGenerator;
import com.hexacta.booster.codegeneration.view.struts2.Struts2ActionTestGenerator;
import com.hexacta.booster.codegeneration.view.struts2.Struts2ActionValidationGenerator;
import com.hexacta.booster.codegeneration.view.struts2.Struts2FormViewGenerator;
import com.hexacta.booster.codegeneration.view.struts2.Struts2ListViewGenerator;
import com.hexacta.booster.codegeneration.view.struts2.Struts2MenuConfigGenerator;
import com.hexacta.booster.codegeneration.view.struts2.Struts2MenuGenerator;
import com.hexacta.booster.codegeneration.view.struts2.Struts2ModelValidationGenerator;
import com.hexacta.booster.codegeneration.view.struts2.Struts2StrutsFileGenerator;
import com.hexacta.booster.project.configuration.ProjectConfigurationTool;
import com.hexacta.booster.project.configuration.Struts2;
import com.hexacta.booster.utilities.CodeGeneratorHelper;

/**
 * This class generates all files needed for the View using Struts 2.
 * 
 * Created on 07/04/2009
 * 
 * @author vmartz
 * 
 */
public class ViewGenerator implements BoosterGenerator {

    static final Logger logger = Logger.getLogger(ViewGenerator.class.getSimpleName());

    /**
     * This method generates all necessary files in a view layer in Struts2
     * {@inheritDoc}
     */
    public void generate(final ProjectConfigurationTool projectConfigurationTool,
            final CodeGeneratorConfiguration codeGeneratorConfiguration, final GenerationInfo generationInfo)
            throws NotSupportedClassException {

        if (projectConfigurationTool.getViewLayerFramework() instanceof Struts2) {
            generateAction(codeGeneratorConfiguration, generationInfo);
            generateActionTest(codeGeneratorConfiguration, generationInfo);//
            generateActionValidation(codeGeneratorConfiguration, generationInfo);
            generateModelValidation(codeGeneratorConfiguration, generationInfo);
            generateApplicationResources(codeGeneratorConfiguration, generationInfo);
            generateFormView(codeGeneratorConfiguration, generationInfo);
            generateListView(codeGeneratorConfiguration, generationInfo);
            generateMenuConfig(codeGeneratorConfiguration, generationInfo);
            generateMenu(codeGeneratorConfiguration, generationInfo);
            generateInsideStrutsFile(codeGeneratorConfiguration, generationInfo);
            generateInsideApplicationContextStrutsFile(codeGeneratorConfiguration, generationInfo);
            generateWebTests(codeGeneratorConfiguration, generationInfo);//
        }

    }

    /**
     * @param codeGeneratorConfiguration
     * @param generationInfo
     */
    private void generateAction(final CodeGeneratorConfiguration codeGeneratorConfiguration,
            final GenerationInfo generationInfo) {

        Struts2ActionGenerator strutsActionGenerator = new Struts2ActionGenerator();
        CodeGeneratorHelper.generateForMetaModel(strutsActionGenerator, codeGeneratorConfiguration, generationInfo);

    }

    /**
     * @param codeGeneratorConfiguration
     * @param generationInfo
     */
    private void generateActionTest(final CodeGeneratorConfiguration codeGeneratorConfiguration,
            final GenerationInfo generationInfo) {
        Struts2ActionTestGenerator struts2ActionTestGenerator = new Struts2ActionTestGenerator();
        CodeGeneratorHelper
                .generateForMetaModel(struts2ActionTestGenerator, codeGeneratorConfiguration, generationInfo);

    }

    /**
     * @param codeGeneratorConfiguration
     * @param generationInfo
     */
    private void generateActionValidation(final CodeGeneratorConfiguration codeGeneratorConfiguration,
            final GenerationInfo generationInfo) {

        Struts2ActionValidationGenerator strutsActionValidationGenerator = new Struts2ActionValidationGenerator();
        CodeGeneratorHelper.generateForMetaModel(strutsActionValidationGenerator, codeGeneratorConfiguration,
                generationInfo);

    }

    /**
     * @param codeGeneratorConfiguration
     * @param generationInfo
     */
    private void generateModelValidation(final CodeGeneratorConfiguration codeGeneratorConfiguration,
            final GenerationInfo generationInfo) {

        Struts2ModelValidationGenerator strutsModelValidationGenerator = new Struts2ModelValidationGenerator();
        CodeGeneratorHelper.generateForMetaModel(strutsModelValidationGenerator, codeGeneratorConfiguration,
                generationInfo);

    }

    /**
     * @param codeGeneratorConfiguration
     * @param generationInfo
     */
    private void generateApplicationResources(final CodeGeneratorConfiguration codeGeneratorConfiguration,
            final GenerationInfo generationInfo) {

        ApplicationResourcesGenerator appResourcesGenerator = new ApplicationResourcesGenerator();
        ViewUtils.generateForMetamodelToAppendApplicationResources(appResourcesGenerator, codeGeneratorConfiguration,
                generationInfo);

        ApplicationResourcesEnglishGenerator appResourcesEnglishGenerator = new ApplicationResourcesEnglishGenerator();
        ViewUtils.generateForMetamodelToAppendApplicationResources(appResourcesEnglishGenerator,
                codeGeneratorConfiguration, generationInfo);

        ApplicationResourcesSpanishGenerator appResourcesSpanishGenerator = new ApplicationResourcesSpanishGenerator();
        ViewUtils.generateForMetamodelToAppendApplicationResources(appResourcesSpanishGenerator,
                codeGeneratorConfiguration, generationInfo);

    }

    /**
     * @param codeGeneratorConfiguration
     * @param generationInfo
     */
    private void generateFormView(final CodeGeneratorConfiguration codeGeneratorConfiguration,
            final GenerationInfo generationInfo) {

        Struts2FormViewGenerator struts2FormViewGenerator = new Struts2FormViewGenerator();
        CodeGeneratorHelper.generateForMetaModel(struts2FormViewGenerator, codeGeneratorConfiguration, generationInfo);

    }

    /**
     * @param codeGeneratorConfiguration
     * @param generationInfo
     */
    private void generateListView(final CodeGeneratorConfiguration codeGeneratorConfiguration,
            final GenerationInfo generationInfo) {
        Struts2ListViewGenerator struts2ListViewGenerator = new Struts2ListViewGenerator();
        CodeGeneratorHelper.generateForMetaModel(struts2ListViewGenerator, codeGeneratorConfiguration, generationInfo);

    }

    /**
     * @param codeGeneratorConfiguration
     * @param generationInfo
     */
    private void generateMenuConfig(final CodeGeneratorConfiguration codeGeneratorConfiguration,
            final GenerationInfo generationInfo) {

        Struts2MenuConfigGenerator menuConfigGenerator = new Struts2MenuConfigGenerator();
        String textToReplace = "</Menus>";
        ViewUtils.generateForMetamodelToReplace(menuConfigGenerator, codeGeneratorConfiguration, generationInfo,
                textToReplace);

    }

    /**
     * @param codeGeneratorConfiguration
     * @param generationInfo
     */
    private void generateMenu(final CodeGeneratorConfiguration codeGeneratorConfiguration,
            final GenerationInfo generationInfo) {

        Struts2MenuGenerator menuGenerator = new Struts2MenuGenerator();
        String textToReplace = "</ul>";
        ViewUtils.generateForMetamodelToReplace(menuGenerator, codeGeneratorConfiguration, generationInfo,
                textToReplace);

    }

    /**
     * @param codeGeneratorConfiguration
     * @param generationInfo2
     */
    private void generateInsideStrutsFile(final CodeGeneratorConfiguration codeGeneratorConfiguration,
            final GenerationInfo generationInfo) {

        Struts2StrutsFileGenerator strutsFileGenerator = new Struts2StrutsFileGenerator();
        ViewUtils.generateForMetamodelToAddInStrutsXML(strutsFileGenerator, codeGeneratorConfiguration, generationInfo);
    }

    /**
     * @param codeGeneratorConfiguration
     * @param generationInfo
     */
    private void generateInsideApplicationContextStrutsFile(
            final CodeGeneratorConfiguration codeGeneratorConfiguration, final GenerationInfo generationInfo) {

        ViewUtils.addActionBeansToApplicationContextStruts(codeGeneratorConfiguration, generationInfo);

    }

    /**
     * @param codeGeneratorConfiguration
     * @param generationInfo
     */
    public void generateWebTests(final CodeGeneratorConfiguration codeGeneratorConfiguration,
            final GenerationInfo generationInfo) {

        WebTestsGenerator webTestsGenerator = new WebTestsGenerator();
        CodeGeneratorHelper.generateForMetaModel(webTestsGenerator, codeGeneratorConfiguration, generationInfo);
    }

}
