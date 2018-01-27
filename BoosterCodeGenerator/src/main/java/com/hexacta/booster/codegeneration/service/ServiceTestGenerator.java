/*
 * [legal notice here]
 */
package com.hexacta.booster.codegeneration.service;

import org.apache.log4j.Logger;

import com.hexacta.booster.codegeneration.BoosterGenerator;
import com.hexacta.booster.codegeneration.GenerationInfo;
import com.hexacta.booster.codegeneration.TextFile;
import com.hexacta.booster.codegeneration.configuration.CodeGeneratorConfiguration;
import com.hexacta.booster.codegeneration.configuration.NotSupportedClassException;
import com.hexacta.booster.codegeneration.service.spring.SpringAbstractServiceJUnit3TestGenerator;
import com.hexacta.booster.codegeneration.service.spring.SpringAbstractServiceJUnit4TestGenerator;
import com.hexacta.booster.codegeneration.service.spring.SpringServiceTest4Generator;
import com.hexacta.booster.codegeneration.service.spring.SpringServiceTestGenerator;
import com.hexacta.booster.exception.InfraestructureException;
import com.hexacta.booster.project.configuration.JUnit3;
import com.hexacta.booster.project.configuration.JUnit4;
import com.hexacta.booster.project.configuration.ProjectConfigurationTool;
import com.hexacta.booster.project.configuration.Spring;
import com.hexacta.booster.utilities.CodeGeneratorHelper;
import com.hexacta.booster.utilities.MetaModelUtils;
import com.hexacta.booster.utilities.TextFileWriter;

/**
 * This class generates service tests depending op testing tool version.
 * 
 * Created on 18/02/2009
 * 
 * @author vmartz
 * 
 */
public class ServiceTestGenerator implements BoosterGenerator {

    static final Logger logger = Logger.getLogger(ServiceTestGenerator.class.getSimpleName());

    /**
     * {@inheritDoc}
     */
    public void generate(final ProjectConfigurationTool projectConfigurationTool,
            final CodeGeneratorConfiguration codeGeneratorConfiguration, final GenerationInfo generationInfo)
            throws NotSupportedClassException {

        projectConfigurationTool.getFramework().generateCodeIn(this, projectConfigurationTool,
                codeGeneratorConfiguration, generationInfo);
    }

    /**
     * @param unit3
     * @param spring
     * @param projectConfigurationTool
     * @param codeGeneratorConfiguration
     * @param generationInfo
     */
    public void generateCodeFor(final JUnit3 unit3, final Spring spring,
            final ProjectConfigurationTool projectConfigurationTool,
            final CodeGeneratorConfiguration codeGeneratorConfiguration, final GenerationInfo generationInfo) {

        SpringServiceTestGenerator serviceTestGenerator = new SpringServiceTestGenerator();
        CodeGeneratorHelper.generateForMetaModel(serviceTestGenerator, codeGeneratorConfiguration, generationInfo);

        // generateTransactionalTests(codeGeneratorConfiguration,
        // generationInfo);

        // addBeansOnServiceTestContextXML(codeGeneratorConfiguration,
        // generationInfo);

        SpringAbstractServiceJUnit3TestGenerator aSpringAbstractServiceJUnit3TestGenerator = new SpringAbstractServiceJUnit3TestGenerator();
        String groupId = MetaModelUtils.getGroupId(codeGeneratorConfiguration.getClassList().iterator().next());

        try {
            TextFile springAbstractServiceJUnit3Test = aSpringAbstractServiceJUnit3TestGenerator.generate(groupId,
                    codeGeneratorConfiguration);
            TextFileWriter textFileWriter = new TextFileWriter();
            boolean successful = textFileWriter.write(springAbstractServiceJUnit3Test, generationInfo);
            if (successful) {
                generationInfo.addService(springAbstractServiceJUnit3Test.getPath()
                        + springAbstractServiceJUnit3Test.getName());
            }

        } catch (Exception e) {
            logger.error(e);
            throw new InfraestructureException(e);
        }

    }

    /**
     * @param unit4
     * @param spring
     * @param projectConfigurationTool
     * @param codeGeneratorConfiguration
     * @param generationInfo
     */
    public void generateCodeFor(final JUnit4 unit4, final Spring spring,
            final ProjectConfigurationTool projectConfigurationTool,
            final CodeGeneratorConfiguration codeGeneratorConfiguration, final GenerationInfo generationInfo) {

        SpringServiceTest4Generator serviceTest4Generator = new SpringServiceTest4Generator();
        CodeGeneratorHelper.generateForMetaModel(serviceTest4Generator, codeGeneratorConfiguration, generationInfo);

        // generateTransactionalTests(codeGeneratorConfiguration,
        // generationInfo);

        // addBeansOnServiceTestContextXML(codeGeneratorConfiguration,
        // generationInfo);

        SpringAbstractServiceJUnit4TestGenerator aSpringAbstractServiceJUnit4TestGenerator = new SpringAbstractServiceJUnit4TestGenerator();
        String groupId = MetaModelUtils.getGroupId(codeGeneratorConfiguration.getClassList().iterator().next());

        try {
            TextFile springAbstractServiceJUnit4Test = aSpringAbstractServiceJUnit4TestGenerator.generate(groupId,
                    codeGeneratorConfiguration);
            TextFileWriter textFileWriter = new TextFileWriter();
            boolean successful = textFileWriter.write(springAbstractServiceJUnit4Test, generationInfo);
            if (successful) {
                generationInfo.addService(springAbstractServiceJUnit4Test.getPath()
                        + springAbstractServiceJUnit4Test.getName());
            }

        } catch (Exception e) {
            logger.error(e);
            throw new InfraestructureException(e);
        }

    }

    // private void generateTransactionalTests(final CodeGeneratorConfiguration
    // codeGeneratorConfiguration,
    // final GenerationInfo generationInfo) {
    // TransactionalTestGenerator transactionalTestGenerator = new
    // TransactionalTestGenerator();
    // CodeGeneratorHelper
    // .generateForMetaModel(transactionalTestGenerator,
    // codeGeneratorConfiguration, generationInfo);
    // }
    //
    // private void addBeansOnServiceTestContextXML(final
    // CodeGeneratorConfiguration codeGeneratorConfiguration,
    // final GenerationInfo generationInfo) {
    // SpringUtils springUtils = new SpringUtils();
    // springUtils.addBeansOnServiceTestContextXML(codeGeneratorConfiguration.
    // getGenerateCodeFor(),
    // codeGeneratorConfiguration.getDirectoryInfo(), generationInfo);
    // }

}
