package com.hexacta.booster.codegeneration.persistence;

import org.apache.log4j.Logger;

import com.hexacta.booster.codegeneration.BoosterGenerator;
import com.hexacta.booster.codegeneration.GenerationInfo;
import com.hexacta.booster.codegeneration.configuration.CodeGeneratorConfiguration;
import com.hexacta.booster.codegeneration.persistence.layer.nhibernate.NHibernateDAOGenerator;
import com.hexacta.booster.codegeneration.persistence.layer.nhibernate.NHibernateDAOTestGenerator;
import com.hexacta.booster.project.configuration.NHibernate;
import com.hexacta.booster.project.configuration.ProjectConfigurationTool;
import com.hexacta.booster.utilities.CodeGeneratorHelper;

/**
 * 
 * 
 * 
 * Created on 26/01/2009
 * 
 * @author clopez
 * 
 */
public class DotNetDAOGenerator implements BoosterGenerator {

    static final Logger logger = Logger.getLogger(DotNetDAOGenerator.class);

    public DotNetDAOGenerator() {

    }

    public void generate(final ProjectConfigurationTool proyectConfigurationTool,
            final CodeGeneratorConfiguration codeGeneratorConfiguration, final GenerationInfo generationInfo) {

        proyectConfigurationTool.getOrmTool().generateCodeForDaosIn(this, codeGeneratorConfiguration, generationInfo);

    }

    public void generateTest(final ProjectConfigurationTool projectConfigurationTool,
            final CodeGeneratorConfiguration codeGeneratorConfiguration, final GenerationInfo generationInfo) {

        projectConfigurationTool.getOrmTool().generateDaosTestsIn(this, codeGeneratorConfiguration, generationInfo);

    }

    public void generateFor(final NHibernate hibernate, final CodeGeneratorConfiguration codeGeneratorConfiguration,
            final GenerationInfo generationInfo) {

        NHibernateDAOGenerator aNHibernateDAOGenerator = new NHibernateDAOGenerator();
        CodeGeneratorHelper.generateForMetaModel(aNHibernateDAOGenerator, codeGeneratorConfiguration, generationInfo);

    }

    public void generateTestFor(final NHibernate hibernate,
            final CodeGeneratorConfiguration codeGeneratorConfiguration, final GenerationInfo generationInfo) {

        NHibernateDAOTestGenerator aNHibernateDAOTestGenerator = new NHibernateDAOTestGenerator();
        CodeGeneratorHelper.generateForMetaModel(aNHibernateDAOTestGenerator, codeGeneratorConfiguration,
                generationInfo);

    }

}
