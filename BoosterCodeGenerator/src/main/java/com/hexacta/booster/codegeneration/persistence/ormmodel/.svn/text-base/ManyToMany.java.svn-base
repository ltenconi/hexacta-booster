package com.hexacta.booster.codegeneration.persistence.ormmodel;

import com.hexacta.booster.codegeneration.persistence.configuration.hibernate.XmlHibernateMappingGenerator;
import com.hexacta.booster.codegeneration.persistence.configuration.hibernate.XmlHibernateMappingGeneratorContext;
import com.hexacta.booster.codegeneration.persistence.configuration.nhibernate.XmlNHibernateMappingGenerator;
import com.hexacta.booster.codegeneration.persistence.configuration.nhibernate.XmlNHibernateMappingGeneratorContext;

/**
 * 
 */
public class ManyToMany extends Cardinality {

    public ManyToMany() {

    }

    @Override
    public void generateCodeIn(final XmlHibernateMappingGenerator hibernateMappingGenerator,
            final XmlHibernateMappingGeneratorContext hibernateMappingGeneratorContext,
            final BiDirectional biDirectional) {
        hibernateMappingGenerator.generateCodeFor(biDirectional, this, hibernateMappingGeneratorContext);

    }

    @Override
    public void generateCodeIn(final XmlHibernateMappingGenerator hibernateMappingGenerator,
            final XmlHibernateMappingGeneratorContext xmlHibernateMappingGeneratorContext,
            final UniDirectional uniDirectional) {
        hibernateMappingGenerator.generateCodeFor(uniDirectional, this, xmlHibernateMappingGeneratorContext);

    }

    @Override
    public boolean isManyToMany() {
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
