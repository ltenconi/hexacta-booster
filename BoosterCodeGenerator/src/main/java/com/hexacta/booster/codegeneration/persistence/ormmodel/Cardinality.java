package com.hexacta.booster.codegeneration.persistence.ormmodel;

import com.hexacta.booster.codegeneration.persistence.configuration.hibernate.XmlHibernateMappingGenerator;
import com.hexacta.booster.codegeneration.persistence.configuration.hibernate.XmlHibernateMappingGeneratorContext;
import com.hexacta.booster.codegeneration.persistence.configuration.nhibernate.XmlNHibernateMappingGenerator;
import com.hexacta.booster.codegeneration.persistence.configuration.nhibernate.XmlNHibernateMappingGeneratorContext;

/**
 * 
 */
public abstract class Cardinality {

    public abstract void generateCodeIn(XmlHibernateMappingGenerator hibernateMapFile,
            XmlHibernateMappingGeneratorContext hibernateMappingGeneratorContext, BiDirectional biDirectional);

    public abstract void generateCodeIn(XmlHibernateMappingGenerator hibernateMapFile,
            XmlHibernateMappingGeneratorContext hibernateMappingGeneratorContext, UniDirectional uniDirectional);

    public boolean isOneToOne() {
        return false;
    }

    public boolean isManyToMany() {
        return false;
    }

    public boolean isOneToMany() {
        return false;
    }

    public boolean isManyToOne() {
        return false;
    }

    public abstract void generateCodeIn(XmlNHibernateMappingGenerator xmlNHibernateMappingGenerator,
            XmlNHibernateMappingGeneratorContext hibernateMappingGeneratorContext, BiDirectional biDirectional);

    public abstract void generateCodeIn(XmlNHibernateMappingGenerator xmlNHibernateMappingGenerator,
            XmlNHibernateMappingGeneratorContext hibernateMappingGeneratorContext, UniDirectional uniDirectional);

}
