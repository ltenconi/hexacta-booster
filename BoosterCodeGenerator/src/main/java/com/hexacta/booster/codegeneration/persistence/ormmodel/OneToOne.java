package com.hexacta.booster.codegeneration.persistence.ormmodel;

import com.hexacta.booster.codegeneration.persistence.configuration.hibernate.XmlHibernateMappingGenerator;
import com.hexacta.booster.codegeneration.persistence.configuration.hibernate.XmlHibernateMappingGeneratorContext;
import com.hexacta.booster.codegeneration.persistence.configuration.nhibernate.XmlNHibernateMappingGenerator;
import com.hexacta.booster.codegeneration.persistence.configuration.nhibernate.XmlNHibernateMappingGeneratorContext;

/**
 * 
 */
public class OneToOne extends Cardinality {

    public OneToOne() {

    }

    @Override
    public void generateCodeIn(final XmlHibernateMappingGenerator hibernateMapFile,
            final XmlHibernateMappingGeneratorContext hibernateMappingGeneratorContext,
            final BiDirectional biDirectional) {
        hibernateMapFile.generateCodeFor(biDirectional, this, hibernateMappingGeneratorContext);
    }

    @Override
    public void generateCodeIn(final XmlHibernateMappingGenerator hibernateMapFile,
            final XmlHibernateMappingGeneratorContext hibernateMappingGeneratorContext,
            final UniDirectional uniDirectional) {
        hibernateMapFile.generateCodeFor(uniDirectional, this, hibernateMappingGeneratorContext);

    }

    @Override
    public boolean isOneToOne() {
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
