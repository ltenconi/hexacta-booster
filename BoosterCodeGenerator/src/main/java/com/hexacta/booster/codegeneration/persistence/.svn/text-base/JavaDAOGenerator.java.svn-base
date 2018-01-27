package com.hexacta.booster.codegeneration.persistence;

import org.apache.log4j.Logger;

import com.hexacta.booster.codegeneration.BoosterGenerator;
import com.hexacta.booster.codegeneration.GenerationInfo;
import com.hexacta.booster.codegeneration.configuration.CodeGeneratorConfiguration;
import com.hexacta.booster.codegeneration.persistence.layer.hibernate.GenericDAOInterfaceGenerator;
import com.hexacta.booster.codegeneration.persistence.layer.hibernate.HibernateDAOGenerator;
import com.hexacta.booster.codegeneration.persistence.layer.hibernate.HibernateDAOTest4Generator;
import com.hexacta.booster.codegeneration.persistence.layer.hibernate.HibernateDAOTestGenerator;
import com.hexacta.booster.codegeneration.persistence.layer.hibernate.HibernateGenericDAOGenerator;
import com.hexacta.booster.codegeneration.persistence.layer.hibernate.HibernateSpringGenericDAOGenerator;
import com.hexacta.booster.codegeneration.persistence.layer.hibernate.SpringAbstractDAOJUnit3TestGenerator;
import com.hexacta.booster.codegeneration.persistence.layer.hibernate.SpringAbstractDAOJUnit4TestGenerator;
import com.hexacta.booster.codegeneration.persistence.layer.hibernate.SpringHibernateDAOGenerator;
import com.hexacta.booster.codegeneration.persistence.layer.hibernate.SpringHibernateDaoTest4Generator;
import com.hexacta.booster.codegeneration.persistence.layer.hibernate.SpringHibernateDaoTestGenerator;
import com.hexacta.booster.codegeneration.persistence.layer.jpahibernate.JpaHibernateSpringGenericDAOImplGenerator;
import com.hexacta.booster.codegeneration.persistence.layer.jpahibernate.JpaHibernateSpringGenericDAOInterfaceGenerator;
import com.hexacta.booster.codegeneration.persistence.layer.jpahibernate.SpringJpaDAOGenerator;
import com.hexacta.booster.codegeneration.persistence.layer.jpahibernate.SpringJpaDAOTestGenerator;
import com.hexacta.booster.project.configuration.Hibernate;
import com.hexacta.booster.project.configuration.JUnit3;
import com.hexacta.booster.project.configuration.JUnit4;
import com.hexacta.booster.project.configuration.JpaHibernate;
import com.hexacta.booster.project.configuration.NoFramework;
import com.hexacta.booster.project.configuration.ProjectConfigurationTool;
import com.hexacta.booster.project.configuration.Spring;
import com.hexacta.booster.utilities.CodeGeneratorHelper;
import com.hexacta.booster.utilities.SpringUtils;

/**
 * 
 */
public class JavaDAOGenerator implements BoosterGenerator {

    static final Logger logger = Logger.getLogger(JavaDAOGenerator.class.getSimpleName());

    public JavaDAOGenerator() {

    }

    public void generate(final ProjectConfigurationTool projectConfigurationTool,
            final CodeGeneratorConfiguration codeGeneratorConfiguration, final GenerationInfo generationInfo) {

        projectConfigurationTool.getOrmTool().generateCodeIn(this, projectConfigurationTool,
                codeGeneratorConfiguration, generationInfo);

    }

    /**
     * Generate Test.
     * 
     * @param projectConfigurationTool
     * 
     **/

    public void generateTests(final ProjectConfigurationTool projectConfigurationTool,
            final CodeGeneratorConfiguration codeGeneratorConfiguration, final GenerationInfo generationInfo) {

        projectConfigurationTool.getOrmTool().generateTestsCodeIn(this, projectConfigurationTool,
                codeGeneratorConfiguration, generationInfo);

    }

    /**
     * @param noFramework
     * @param hibernate
     * @param projectConfigurationTool
     * @param codeGeneratorConfiguration
     * @param generationInfo
     */
    public void generateCodeFor(final NoFramework noFramework, final Hibernate hibernate,
            final ProjectConfigurationTool projectConfigurationTool,
            final CodeGeneratorConfiguration codeGeneratorConfiguration, final GenerationInfo generationInfo) {

        HibernateDAOGenerator hibernateDAOGenerator = new HibernateDAOGenerator();
        CodeGeneratorHelper.generateForMetaModel(hibernateDAOGenerator, codeGeneratorConfiguration, generationInfo);

        generateHibernateGenericDAO(codeGeneratorConfiguration, generationInfo);

    }

    /**
     * @param spring
     * @param hibernate
     * @param projectConfigurationTool
     * @param codeGeneratorConfiguration
     * @param generationInfo
     */
    public void generateCodeFor(final Spring spring, final Hibernate hibernate,
            final ProjectConfigurationTool projectConfigurationTool,
            final CodeGeneratorConfiguration codeGeneratorConfiguration, final GenerationInfo generationInfo) {

        SpringHibernateDAOGenerator springDaoGenerator = new SpringHibernateDAOGenerator();
        CodeGeneratorHelper.generateForMetaModel(springDaoGenerator, codeGeneratorConfiguration, generationInfo);

        SpringUtils.addBeansOnDAOContextXML(codeGeneratorConfiguration.getGenerateCodeFor(), codeGeneratorConfiguration
                .getDirectoryInfo(), hibernate, generationInfo);

        generateHibernateSpringDAOImpl(codeGeneratorConfiguration, generationInfo);
        generateGenericDAOInterface(codeGeneratorConfiguration, generationInfo);

    }

    /**
     * @param spring
     * @param jpaHibernate
     * @param projectConfigurationTool
     * @param codeGeneratorConfiguration
     * @param generationInfo
     */
    public void generateCodeFor(final Spring spring, final JpaHibernate jpaHibernate,
            final ProjectConfigurationTool projectConfigurationTool,
            final CodeGeneratorConfiguration codeGeneratorConfiguration, final GenerationInfo generationInfo) {

        SpringJpaDAOGenerator springJpaDaoGenerator = new SpringJpaDAOGenerator();
        CodeGeneratorHelper.generateForMetaModel(springJpaDaoGenerator, codeGeneratorConfiguration, generationInfo);

        SpringUtils.addBeansOnDAOContextXML(codeGeneratorConfiguration.getGenerateCodeFor(), codeGeneratorConfiguration
                .getDirectoryInfo(), jpaHibernate, generationInfo);

        generateJpaHibernateSpringGenericDAOImpl(codeGeneratorConfiguration, generationInfo);
        generateGenericDAOInterface(codeGeneratorConfiguration, generationInfo);
        // generateJpaHibernateSpringGenericDAOInterface(
        // codeGeneratorConfiguration, generationInfo);

    }

    /**
     * @param codeGeneratorConfiguration
     * @param generationInfo
     */
    private void generateJpaHibernateSpringGenericDAOInterface(
            final CodeGeneratorConfiguration codeGeneratorConfiguration, final GenerationInfo generationInfo) {
        JpaHibernateSpringGenericDAOInterfaceGenerator jpaHibernateSpringGenericDAOInterfaceGenerator = new JpaHibernateSpringGenericDAOInterfaceGenerator();
        CodeGeneratorHelper.generateArchetypeFile(jpaHibernateSpringGenericDAOInterfaceGenerator,
                codeGeneratorConfiguration, generationInfo);
    }

    /**
     * @param noFramework
     * @param jpaHibernate
     * @param projectConfigurationTool
     * @param codeGeneratorConfiguration
     * @param generationInfo
     */
    public void generateCodeFor(final NoFramework noFramework, final JpaHibernate jpaHibernate,
            final ProjectConfigurationTool projectConfigurationTool,
            final CodeGeneratorConfiguration codeGeneratorConfiguration, final GenerationInfo generationInfo) {

        HibernateDAOGenerator hibernateDAOGenerator = new HibernateDAOGenerator();
        CodeGeneratorHelper.generateForMetaModel(hibernateDAOGenerator, codeGeneratorConfiguration, generationInfo);

        generateHibernateGenericDAO(codeGeneratorConfiguration, generationInfo);

    }

    /**
     * @param unit3
     * @param noFramework
     * @param jpaHibernate
     * @param projectConfigurationTool
     * @param codeGeneratorConfiguration
     * @param generationInfo
     */
    public void generateTestCodeFor(final JUnit3 unit3, final NoFramework noFramework, final JpaHibernate jpaHibernate,
            final ProjectConfigurationTool projectConfigurationTool,
            final CodeGeneratorConfiguration codeGeneratorConfiguration, final GenerationInfo generationInfo) {

        HibernateDAOTestGenerator hibernateDAOTestGenerator = new HibernateDAOTestGenerator();
        CodeGeneratorHelper.generateForMetaModel(hibernateDAOTestGenerator, codeGeneratorConfiguration, generationInfo);

    }

    /**
     * @param unit3
     * @param noFramework
     * @param hibernate
     * @param projectConfigurationTool
     * @param codeGeneratorConfiguration
     * @param generationInfo
     */
    public void generateTestCodeFor(final JUnit3 unit3, final NoFramework noFramework, final Hibernate hibernate,
            final ProjectConfigurationTool projectConfigurationTool,
            final CodeGeneratorConfiguration codeGeneratorConfiguration, final GenerationInfo generationInfo) {

        HibernateDAOTestGenerator hibernateDAOTestGenerator = new HibernateDAOTestGenerator();
        CodeGeneratorHelper.generateForMetaModel(hibernateDAOTestGenerator, codeGeneratorConfiguration, generationInfo);

    }

    /**
     * @param unit4
     * @param noFramework
     * @param jpaHibernate
     * @param projectConfigurationTool
     * @param codeGeneratorConfiguration
     * @param generationInfo
     */
    public void generateTestCodeFor(final JUnit4 unit4, final NoFramework noFramework, final JpaHibernate jpaHibernate,
            final ProjectConfigurationTool projectConfigurationTool,
            final CodeGeneratorConfiguration codeGeneratorConfiguration, final GenerationInfo generationInfo) {

    }

    /**
     * @param unit4
     * @param noFramework
     * @param hibernate
     * @param projectConfigurationTool
     * @param codeGeneratorConfiguration
     * @param generationInfo
     */
    public void generateTestCodeFor(final JUnit4 unit4, final NoFramework noFramework, final Hibernate hibernate,
            final ProjectConfigurationTool projectConfigurationTool,
            final CodeGeneratorConfiguration codeGeneratorConfiguration, final GenerationInfo generationInfo) {

        HibernateDAOTest4Generator hibernateDAOTest4Generator = new HibernateDAOTest4Generator();
        CodeGeneratorHelper
                .generateForMetaModel(hibernateDAOTest4Generator, codeGeneratorConfiguration, generationInfo);

    }

    /**
     * @param unit3
     * @param spring
     * @param hibernate
     * @param projectConfigurationTool
     * @param codeGeneratorConfiguration
     * @param generationInfo
     */
    public void generateTestCodeFor(final JUnit3 unit3, final Spring spring, final Hibernate hibernate,
            final ProjectConfigurationTool projectConfigurationTool,
            final CodeGeneratorConfiguration codeGeneratorConfiguration, final GenerationInfo generationInfo) {

        SpringHibernateDaoTestGenerator anHibernateDaoTestGenerator = new SpringHibernateDaoTestGenerator();
        CodeGeneratorHelper.generateForMetaModel(anHibernateDaoTestGenerator, codeGeneratorConfiguration,
                generationInfo);

        generateSpringAbstractJUnit3Test(codeGeneratorConfiguration, generationInfo);

    }

    /**
     * @param unit3
     * @param spring
     * @param jpaHibernate
     * @param projectConfigurationTool
     * @param codeGeneratorConfiguration
     * @param generationInfo
     */
    public void generateTestCodeFor(final JUnit3 unit3, final Spring spring, final JpaHibernate jpaHibernate,
            final ProjectConfigurationTool projectConfigurationTool,
            final CodeGeneratorConfiguration codeGeneratorConfiguration, final GenerationInfo generationInfo) {

        SpringJpaDAOTestGenerator springJpaDAOTestGenerator = new SpringJpaDAOTestGenerator();
        CodeGeneratorHelper.generateForMetaModel(springJpaDAOTestGenerator, codeGeneratorConfiguration, generationInfo);

    }

    /**
     * @param unit4
     * @param spring
     * @param jpaHibernate
     * @param projectConfigurationTool
     * @param codeGeneratorConfiguration
     * @param generationInfo
     */
    public void generateTestCodeFor(final JUnit4 unit4, final Spring spring, final JpaHibernate jpaHibernate,
            final ProjectConfigurationTool projectConfigurationTool,
            final CodeGeneratorConfiguration codeGeneratorConfiguration, final GenerationInfo generationInfo) {

    }

    /**
     * @param unit4
     * @param spring
     * @param hibernate
     * @param projectConfigurationTool
     * @param codeGeneratorConfiguration
     * @param generationInfo
     */
    public void generateTestCodeFor(final JUnit4 unit4, final Spring spring, final Hibernate hibernate,
            final ProjectConfigurationTool projectConfigurationTool,
            final CodeGeneratorConfiguration codeGeneratorConfiguration, final GenerationInfo generationInfo) {

        SpringHibernateDaoTest4Generator anHibernateDaoTest4Generator = new SpringHibernateDaoTest4Generator();
        CodeGeneratorHelper.generateForMetaModel(anHibernateDaoTest4Generator, codeGeneratorConfiguration,
                generationInfo);

        generateSpringAbstractDaoTestunit4Test(codeGeneratorConfiguration, generationInfo);
    }

    private void generateSpringAbstractDaoTestunit4Test(final CodeGeneratorConfiguration codeGeneratorConfiguration,
            final GenerationInfo generationInfo) {
        SpringAbstractDAOJUnit4TestGenerator aSpringAbstractDAOJUnit4TestGenerator = new SpringAbstractDAOJUnit4TestGenerator();
        CodeGeneratorHelper.generateArchetypeFile(aSpringAbstractDAOJUnit4TestGenerator, codeGeneratorConfiguration,
                generationInfo);

    }

    private void generateHibernateGenericDAO(final CodeGeneratorConfiguration codeGeneratorConfiguration,
            final GenerationInfo generationInfo) {
        HibernateGenericDAOGenerator hibernateGenericDAOGenerator = new HibernateGenericDAOGenerator();
        CodeGeneratorHelper.generateArchetypeFile(hibernateGenericDAOGenerator, codeGeneratorConfiguration, generationInfo);
    }

    private void generateJpaHibernateSpringGenericDAOImpl(final CodeGeneratorConfiguration codeGeneratorConfiguration,
            final GenerationInfo generationInfo) {
        JpaHibernateSpringGenericDAOImplGenerator jpaHibernateSpringGenericDAOImplGenerator = new JpaHibernateSpringGenericDAOImplGenerator();
        CodeGeneratorHelper.generateArchetypeFile(jpaHibernateSpringGenericDAOImplGenerator, codeGeneratorConfiguration,
                generationInfo);
    }

    private void generateHibernateSpringDAOImpl(final CodeGeneratorConfiguration codeGeneratorConfiguration,
            final GenerationInfo generationInfo) {
        HibernateSpringGenericDAOGenerator hibernateSpringGenericDAOGenerator = new HibernateSpringGenericDAOGenerator();
        CodeGeneratorHelper.generateArchetypeFile(hibernateSpringGenericDAOGenerator, codeGeneratorConfiguration,
                generationInfo);
    }

    private void generateSpringAbstractJUnit3Test(final CodeGeneratorConfiguration codeGeneratorConfiguration,
            final GenerationInfo generationInfo) {
        SpringAbstractDAOJUnit3TestGenerator aSpringAbstractDAOJUnit3TestGenerator = new SpringAbstractDAOJUnit3TestGenerator();
        CodeGeneratorHelper.generateArchetypeFile(aSpringAbstractDAOJUnit3TestGenerator, codeGeneratorConfiguration,
                generationInfo);
    }

    private void generateGenericDAOInterface(final CodeGeneratorConfiguration codeGeneratorConfiguration,
            final GenerationInfo generationInfo) {
        GenericDAOInterfaceGenerator genericDAOInterfaceGenerator = new GenericDAOInterfaceGenerator();
        CodeGeneratorHelper.generateArchetypeFile(genericDAOInterfaceGenerator, codeGeneratorConfiguration, generationInfo);

    }

}
