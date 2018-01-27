package com.hexacta.booster.codegeneration.persistence.ormmodel;

import com.hexacta.booster.codegeneration.metamodel.Class;
import com.hexacta.booster.codegeneration.persistence.configuration.hibernate.XmlHibernateMappingGenerator;
import com.hexacta.booster.codegeneration.persistence.configuration.hibernate.XmlHibernateMappingGeneratorContext;
import com.hexacta.booster.codegeneration.persistence.configuration.nhibernate.XmlNHibernateMappingGenerator;
import com.hexacta.booster.codegeneration.persistence.configuration.nhibernate.XmlNHibernateMappingGeneratorContext;

/**
 * 
 */
public class UniDirectional extends AssociationType {

    private String fieldNameA;

    private VarType varTypeA;

    public UniDirectional(final String fieldNameA, final VarType varTypeA, final Class aClassA, final Class aClassB) {

        this.fieldNameA = fieldNameA;
        classA = aClassA;
        this.varTypeA = varTypeA;
        classB = aClassB;

    }

    public String getFieldNameA() {
        return fieldNameA;
    }

    public VarType getVarTypeA() {
        return varTypeA;
    }

    @Override
    public void generateCodeIn(final XmlHibernateMappingGenerator hibernateMappingGenerator,
            final XmlHibernateMappingGeneratorContext hibernateMappingGeneratorContext, final Cardinality cardinality) {
        cardinality.generateCodeIn(hibernateMappingGenerator, hibernateMappingGeneratorContext, this);

    }

    @Override
    public boolean isUniDirectional() {
        return true;
    }

    @Override
    public void generateCodeIn(final XmlNHibernateMappingGenerator xmlNHibernateMappingGenerator,
            final XmlNHibernateMappingGeneratorContext hibernateMappingGeneratorContext, final Cardinality cardinality) {
        cardinality.generateCodeIn(xmlNHibernateMappingGenerator, hibernateMappingGeneratorContext, this);
    }

}
