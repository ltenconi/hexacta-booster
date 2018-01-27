package com.hexacta.booster.codegeneration.persistence;

import com.hexacta.booster.codegeneration.BoosterGenerator;
import com.hexacta.booster.codegeneration.GenerationInfo;
import com.hexacta.booster.codegeneration.configuration.CodeGeneratorConfiguration;
import com.hexacta.booster.codegeneration.configuration.NotSupportedClassException;
import com.hexacta.booster.project.configuration.DotNetProjectType;
import com.hexacta.booster.project.configuration.HibernateDaos;
import com.hexacta.booster.project.configuration.JavaProjectType;
import com.hexacta.booster.project.configuration.PersistenceLayerForModelWithRestrictions;
import com.hexacta.booster.project.configuration.PersistenceLayerForModelWithoutRestrictions;
import com.hexacta.booster.project.configuration.ProjectConfigurationTool;

/**
 * 
 */
public class PersistenceLayerGenerator implements BoosterGenerator {

    public PersistenceLayerGenerator() {

    }

    public void generate(final ProjectConfigurationTool projectConfigurationTool,
            final CodeGeneratorConfiguration codeGeneratorConfiguration, final GenerationInfo generationInfo)
            throws NotSupportedClassException {

        projectConfigurationTool.getPersistenceLayerType().generateCodeIn(this, projectConfigurationTool,
                codeGeneratorConfiguration, generationInfo);

    }

    public void generateCodeFor(final HibernateDaos hibernateDaos,
            final ProjectConfigurationTool projectConfigurationTool,
            final CodeGeneratorConfiguration codeGeneratorConfiguration, final GenerationInfo generationInfo)
            throws NotSupportedClassException {

        projectConfigurationTool.getProjectType().processIn(this, hibernateDaos, projectConfigurationTool,
                codeGeneratorConfiguration, generationInfo);

    }

    public void generateCodeFor(final HibernateDaos hibernateDaos, final DotNetProjectType dotNetProjectType,
            final ProjectConfigurationTool projectConfigurationTool,
            final CodeGeneratorConfiguration codeGeneratorConfiguration, final GenerationInfo generationInfo)
            throws NotSupportedClassException {

        DotNetDAOGenerator dotNetDAOGenerator = new DotNetDAOGenerator();
        dotNetDAOGenerator.generate(projectConfigurationTool, codeGeneratorConfiguration, generationInfo);

        dotNetDAOGenerator.generateTest(projectConfigurationTool, codeGeneratorConfiguration, generationInfo);

        OrmGenerator ormGenerator = new OrmGenerator();
        ormGenerator.generate(projectConfigurationTool, codeGeneratorConfiguration, generationInfo);

    }

    public void generateCodeFor(final HibernateDaos hibernateDaos, final JavaProjectType javaProjectType,
            final ProjectConfigurationTool projectConfigurationTool,
            final CodeGeneratorConfiguration codeGeneratorConfiguration, final GenerationInfo generationInfo)
            throws NotSupportedClassException {

        OrmGenerator ormGenerator = new OrmGenerator();
        ormGenerator.generate(projectConfigurationTool, codeGeneratorConfiguration, generationInfo);

        JavaDAOGenerator daoGenerator = new JavaDAOGenerator();

        daoGenerator.generate(projectConfigurationTool, codeGeneratorConfiguration, generationInfo);
        daoGenerator.generateTests(projectConfigurationTool, codeGeneratorConfiguration, generationInfo);

    }

    /****************************** HASTA ACA. ***********************************/

    public void generateCodeFor(
            final PersistenceLayerForModelWithoutRestrictions persistenceLayerForModelWithoutRestrictions,
            final ProjectConfigurationTool projectConfigurationTool,
            final CodeGeneratorConfiguration codeGeneratorConfiguration, final GenerationInfo generationInfo) {

        // IdFieldNameAssigner idFieldNameAssigner = new IdFieldNameAssigner();
        // ClassIdMap classIdMap =
        // idFieldNameAssigner.assign(codeGeneratorConfiguration.getClassList(),
        // projectConfigurationTool.getProjectType());
        //
        // /* generatePersistableClasses(codeGeneratorConfiguration); */
        //
        // PersistenceSessionWithoutRestrictionsGenerator
        // persistenceSessionWithoutRestrictionsGenerator = new
        // PersistenceSessionWithoutRestrictionsGenerator();
        // PersistenceSession persistenceSession =
        // persistenceSessionWithoutRestrictionsGenerator
        // .generate(codeGeneratorConfiguration);
        // TextFileWriter textFileWriter = new TextFileWriter();
        // textFileWriter.write(persistenceSession.getText(),
        // codeGeneratorConfiguration.getDirectoryInfo().getModelPath()
        // + "persistence/", "BusinessObjectDataObjectMapping.java");

    }

    public void generateCodeFor(
            final PersistenceLayerForModelWithRestrictions persistenceLayerForModelWithRestrictions,
            final ProjectConfigurationTool projectConfigurationTool,
            final CodeGeneratorConfiguration codeGeneratorConfiguration, final GenerationInfo generationInfo)
            throws NotSupportedClassException {

        // OrmGenerator ormGenerator = new OrmGenerator();
        // ormGenerator.generate(projectConfigurationTool,
        // codeGeneratorConfiguration, generationInfo);
        //
        // // ormGenerator.generateDaosFor(new Hibernate(),
        // // codeGeneratorConfiguration);
        // // ormGenerator.generateDaosTestsFor(new Hibernate(),
        // // codeGeneratorConfiguration);
        //
        // // ormGenerator.generateHibernateCommonUtilClass(
        // // codeGeneratorConfiguration);
        //
        // PersistenceSessionWithRestrictionsGenerator
        // persistenceSessionWithRestrictionsGenerator = new
        // PersistenceSessionWithRestrictionsGenerator();
        // PersistenceSession persistenceSession =
        // persistenceSessionWithRestrictionsGenerator
        // .generate(codeGeneratorConfiguration);
        // TextFileWriter textFileWriter = new TextFileWriter();
        // textFileWriter.write(persistenceSession.getText(),
        // codeGeneratorConfiguration.getDirectoryInfo().getModelPath()
        // + /*
        // * codeGeneratorConfiguration.getProyectPackageName().replace('.'
        // * ,'/')
        // */"persistence/", "PersistenceSession.java");
        //
        // generatePersistenceLayerTest(codeGeneratorConfiguration);
        //
        // HibernatePersistenceSystemClassGenerator
        // hibernatePersistenceSystemClassGenerator = new
        // HibernatePersistenceSystemClassGenerator();
        // String persistenceSystemClassText =
        // hibernatePersistenceSystemClassGenerator.generate();
        // textFileWriter.write(persistenceSystemClassText,
        // codeGeneratorConfiguration.getDirectoryInfo().getModelPath()
        // + /*
        // * codeGeneratorConfiguration.getProyectPackageName().replace('.'
        // * ,'/')
        // */"persistence/", "PersistenceSystem.java");
        //
        // SearchCriteriaGenerator aSearchCriteriaGenerator = new
        // SearchCriteriaGenerator();
        // SearchCriteria aSearchCriteria = aSearchCriteriaGenerator.generate();
        // textFileWriter.write(aSearchCriteria.getText(),
        // codeGeneratorConfiguration.getDirectoryInfo().getModelPath()
        // + /*
        // * codeGeneratorConfiguration.getProyectPackageName().replace('.'
        // * ,'/')
        // */"persistence/", "SearchCriteria.java");

    }

    private void generatePersistenceLayerTest(final CodeGeneratorConfiguration codeGeneratorConfiguration)
            throws NotSupportedClassException {

        // PersistenceSessionTestGenerator persistenceSessionTestGenerator = new
        // PersistenceSessionTestGenerator();
        // PersistenceSessionTest persistenceSessionTest =
        // persistenceSessionTestGenerator
        // .generate(codeGeneratorConfiguration);
        // TextFileWriter textFileWriter = new TextFileWriter();
        // textFileWriter.write(persistenceSessionTest.getText(),
        // codeGeneratorConfiguration.getDirectoryInfo()
        // .getModelPath()
        // + /*
        // * codeGeneratorConfiguration.getProyectPackageName().replace('.'
        // * ,'/') +
        // */"persistence/tests/", "PersistenceSessionTest.java");

    }

    public void generateMappingAdministrator(final CodeGeneratorConfiguration codeGeneratorConfiguration) {

        // MappingAdministratorGenerator aMappingAdministratorGenerator = new
        // MappingAdministratorGenerator();
        // MappingAdministrator aMappingAdministrator =
        // aMappingAdministratorGenerator
        // .generate(codeGeneratorConfiguration);
        // TextFileWriter textFileWriter = new TextFileWriter();
        // textFileWriter.write(aMappingAdministrator.getText(),
        // codeGeneratorConfiguration.getDirectoryInfo()
        // .getModelPath()
        // + /*
        // * codeGeneratorConfiguration.getProyectPackageName().replace('.'
        // * ,'/') +
        // */"persistence/", "MappingAdministrator.java");

    }

}
