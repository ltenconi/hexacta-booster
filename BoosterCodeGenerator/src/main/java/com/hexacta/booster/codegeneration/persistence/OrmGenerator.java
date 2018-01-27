package com.hexacta.booster.codegeneration.persistence;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.w3c.dom.Document;

import com.hexacta.booster.codegeneration.BoosterGenerator;
import com.hexacta.booster.codegeneration.GenerationInfo;
import com.hexacta.booster.codegeneration.configuration.CodeGeneratorConfiguration;
import com.hexacta.booster.codegeneration.configuration.NotSupportedClassException;
import com.hexacta.booster.codegeneration.metamodel.Class;
import com.hexacta.booster.codegeneration.persistence.configuration.hibernate.XmlHibernateMappingGenerator;
import com.hexacta.booster.codegeneration.persistence.configuration.hibernate.XmlMappingWriter;
import com.hexacta.booster.codegeneration.persistence.configuration.nhibernate.XmlNHibernateMappingGenerator;
import com.hexacta.booster.codegeneration.persistence.ormmodel.OrmData;
import com.hexacta.booster.codegeneration.persistence.ormmodel.OrmDataGenerator;
import com.hexacta.booster.codegeneration.persistence.ormmodel.OrmMapping;
import com.hexacta.booster.codegeneration.persistence.strategy.MappingHierarchyCuttingStrategy;
import com.hexacta.booster.project.configuration.Hibernate;
import com.hexacta.booster.project.configuration.JpaHibernate;
import com.hexacta.booster.project.configuration.NHibernate;
import com.hexacta.booster.project.configuration.NoOrmTool;
import com.hexacta.booster.project.configuration.ProjectConfigurationTool;
import com.hexacta.booster.project.configuration.TopLink;
import com.hexacta.booster.utilities.OrmMappingHelper;

/**
 * 
 */
public class OrmGenerator implements BoosterGenerator {

    public OrmGenerator() {
    }

    public void generate(final ProjectConfigurationTool projectConfigurationTool,
            final CodeGeneratorConfiguration codeGeneratorConfiguration, final GenerationInfo generationInfo)
            throws NotSupportedClassException {

        projectConfigurationTool.getOrmTool().generateCodeIn(this, projectConfigurationTool,
                codeGeneratorConfiguration, generationInfo);

    }

    public void generateCodeFor(final Hibernate hibernate, final ProjectConfigurationTool projectConfigurationTool,
            final CodeGeneratorConfiguration codeGeneratorConfiguration, final GenerationInfo generationInfo)
            throws NotSupportedClassException {

        List<OrmMapping> ormHierarchyMappings = generateOrmMappings(codeGeneratorConfiguration,
                projectConfigurationTool);
        MappingHierarchyCuttingStrategy mappingHierarchyCuttingStrategy = codeGeneratorConfiguration
                .getMappingHierarchyCuttingStrategy();
        mappingHierarchyCuttingStrategy.cut(ormHierarchyMappings, codeGeneratorConfiguration);
        generateHibernateMappings(ormHierarchyMappings, codeGeneratorConfiguration, projectConfigurationTool,
                generationInfo);

    }

    public void generateCodeFor(final NHibernate nHibernate, final ProjectConfigurationTool projectConfigurationTool,
            final CodeGeneratorConfiguration codeGeneratorConfiguration, final GenerationInfo generationInfo)
            throws NotSupportedClassException {

        List<OrmMapping> ormMappings;
        ormMappings = generateOrmMappings(codeGeneratorConfiguration, projectConfigurationTool);
        generateNHibernateMappings(projectConfigurationTool, codeGeneratorConfiguration, ormMappings, generationInfo);

    }

    private void generateNHibernateMappings(final ProjectConfigurationTool projectConfigurationTool,
            final CodeGeneratorConfiguration codeGeneratorConfiguration, final List<OrmMapping> ormMappings,
            final GenerationInfo generationInfo) {

        List<OrmMapping> mappingsGenerated = new ArrayList<OrmMapping>();

        for (Iterator<Class> iterator = codeGeneratorConfiguration.getGenerateCodeFor().iterator(); iterator.hasNext();) {
            Class aClass = iterator.next();
            OrmMapping mapping = OrmMappingHelper.getOrmMappingHierarchy(aClass, ormMappings);
            if (!mappingsGenerated.contains(mapping)) {
                XmlNHibernateMappingGenerator hibernateMappingGenerator = new XmlNHibernateMappingGenerator();
                Document hibernateMapping = hibernateMappingGenerator.generateXmlMappingFor(mapping,
                        codeGeneratorConfiguration, projectConfigurationTool);
                XmlMappingWriter xmlMappingWriter = new XmlMappingWriter();
                xmlMappingWriter.saveToDisk(hibernateMapping, mapping.getMappingClass().getName(),
                        codeGeneratorConfiguration.getDirectoryInfo().getMappingsPath(), generationInfo,
                        new NHibernate());
                mappingsGenerated.add(mapping);
            }
        }

    }

    public void generateCodeFor(final JpaHibernate jpaHibernate,
            final CodeGeneratorConfiguration codeGeneratorConfiguration, final GenerationInfo generationInfo) {

    }

    public void generateCodeFor(final NoOrmTool noOrmTool, final CodeGeneratorConfiguration codeGeneratorConfiguration,
            final GenerationInfo generationInfo) {

    }

    public void generateCodeFor(final TopLink topLink, final CodeGeneratorConfiguration codeGeneratorConfiguration,
            final GenerationInfo generationInfo) {

    }

    public List<OrmMapping> generateOrmMappings(final CodeGeneratorConfiguration codeGeneratorConfiguration,
            final ProjectConfigurationTool projectConfigurationTool) throws NotSupportedClassException {

        List<OrmMapping> ormMappings;
        OrmDataGenerator ormDataGenerator = new OrmDataGenerator();
        OrmData ormData = ormDataGenerator.generateOrmData(codeGeneratorConfiguration, projectConfigurationTool
                .getProjectType());
        ormMappings = ormData.getOrmMappingsHierarchy();
        return ormMappings;

    }

    public void generateHibernateMappings(final List<OrmMapping> ormMappings,
            final CodeGeneratorConfiguration codeGeneratorConfiguration,
            final ProjectConfigurationTool projectConfigurationTool, final GenerationInfo generationInfo) {

        List<OrmMapping> mappingsGenerated = new ArrayList<OrmMapping>();

        for (Iterator<Class> iterator = codeGeneratorConfiguration.getGenerateCodeFor().iterator(); iterator.hasNext();) {
            Class aClass = iterator.next();
            OrmMapping mapping = OrmMappingHelper.getOrmMappingHierarchy(aClass, ormMappings);
            if (!mappingsGenerated.contains(mapping)) {
                XmlHibernateMappingGenerator hibernateMappingGenerator = new XmlHibernateMappingGenerator();
                Document hibernateMapping = hibernateMappingGenerator.generateXmlMappingFor(mapping,
                        codeGeneratorConfiguration, projectConfigurationTool);
                XmlMappingWriter xmlMappingWriter = new XmlMappingWriter();
                xmlMappingWriter.saveToDisk(hibernateMapping, mapping.getMappingClass().getName(),
                        codeGeneratorConfiguration.getDirectoryInfo().getMappingsPath(), generationInfo,
                        new Hibernate());
                mappingsGenerated.add(mapping);
            }
        }
    }

}