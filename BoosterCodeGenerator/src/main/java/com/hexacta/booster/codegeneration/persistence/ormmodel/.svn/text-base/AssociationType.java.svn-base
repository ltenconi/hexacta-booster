package com.hexacta.booster.codegeneration.persistence.ormmodel;

import com.hexacta.booster.codegeneration.metamodel.Class;
import com.hexacta.booster.codegeneration.persistence.configuration.hibernate.XmlHibernateMappingGenerator;
import com.hexacta.booster.codegeneration.persistence.configuration.hibernate.XmlHibernateMappingGeneratorContext;
import com.hexacta.booster.codegeneration.persistence.configuration.nhibernate.XmlNHibernateMappingGenerator;
import com.hexacta.booster.codegeneration.persistence.configuration.nhibernate.XmlNHibernateMappingGeneratorContext;

/**
 * 
 */
public abstract class AssociationType {

    protected Class classA;

    protected Class classB;

    public abstract void generateCodeIn(XmlHibernateMappingGenerator xmlHibernateMappingGenerator,
            XmlHibernateMappingGeneratorContext mappingGeneratorContext, Cardinality cardinality);

    public boolean isBiDirectional() {
        return false;
    }

    public boolean isUniDirectional() {
        return false;
    }

    public Class getClassA() {
        return classA;
    }

    public Class getClassB() {
        return classB;
    }

    public abstract void generateCodeIn(XmlNHibernateMappingGenerator xmlNHibernateMappingGenerator,
            XmlNHibernateMappingGeneratorContext hibernateMappingGeneratorContext, Cardinality cardinality);

}
