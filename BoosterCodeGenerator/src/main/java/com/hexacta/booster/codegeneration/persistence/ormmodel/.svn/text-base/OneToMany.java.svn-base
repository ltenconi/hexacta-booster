package com.hexacta.booster.codegeneration.persistence.ormmodel;

import com.hexacta.booster.codegeneration.persistence.configuration.hibernate.XmlHibernateMappingGenerator;
import com.hexacta.booster.codegeneration.persistence.configuration.hibernate.XmlHibernateMappingGeneratorContext;
import com.hexacta.booster.codegeneration.persistence.configuration.nhibernate.XmlNHibernateMappingGenerator;
import com.hexacta.booster.codegeneration.persistence.configuration.nhibernate.XmlNHibernateMappingGeneratorContext;

/**
 * 
 */
public class OneToMany extends Cardinality {

    public OneToMany() {

    }

    @Override
    public void generateCodeIn(final XmlHibernateMappingGenerator hibernateMapFile,
            final XmlHibernateMappingGeneratorContext xmlHibernateMappingGeneratorContext,
            final BiDirectional biDirectional) {
        hibernateMapFile.generateCodeFor(biDirectional, this, xmlHibernateMappingGeneratorContext);

    }

    @Override
    public void generateCodeIn(final XmlHibernateMappingGenerator hibernateMapFile,
            final XmlHibernateMappingGeneratorContext xmlHibernateMappingGeneratorContext,
            final UniDirectional uniDirectional) {
        hibernateMapFile.generateCodeFor(uniDirectional, this, xmlHibernateMappingGeneratorContext);

    }

    @Override
    public boolean isOneToMany() {
        return true;
    }

    @Override
    public void generateCodeIn(final XmlNHibernateMappingGenerator xmlNHibernateMappingGenerator,
            final XmlNHibernateMappingGeneratorContext hibernateMappingGeneratorContext,
            final BiDirectional biDirectional) {
        xmlNHibernateMappingGenerator.generateCodeFor(biDirectional, this, hibernateMappingGeneratorContext);
    }

    @Override
    public void generateCodeIn(final XmlNHibernateMappingGenerator xmlNHibernateMappingGenerator,
            final XmlNHibernateMappingGeneratorContext hibernateMappingGeneratorContext,
            final UniDirectional uniDirectional) {
        xmlNHibernateMappingGenerator.generateCodeFor(uniDirectional, this, hibernateMappingGeneratorContext);

    }

}
