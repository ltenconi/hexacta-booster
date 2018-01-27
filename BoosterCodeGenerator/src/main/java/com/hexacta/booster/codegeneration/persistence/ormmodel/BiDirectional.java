package com.hexacta.booster.codegeneration.persistence.ormmodel;

import com.hexacta.booster.codegeneration.metamodel.Class;
import com.hexacta.booster.codegeneration.persistence.configuration.hibernate.XmlHibernateMappingGenerator;
import com.hexacta.booster.codegeneration.persistence.configuration.hibernate.XmlHibernateMappingGeneratorContext;
import com.hexacta.booster.codegeneration.persistence.configuration.nhibernate.XmlNHibernateMappingGenerator;
import com.hexacta.booster.codegeneration.persistence.configuration.nhibernate.XmlNHibernateMappingGeneratorContext;

/**
 * 
 */
public class BiDirectional extends AssociationType {

    private String fieldNameA;

    private String fieldNameB;

    private VarType varTypeA;

    private VarType varTypeB;

    public BiDirectional(final String fieldNameA, final String fieldNameB, final VarType varTypeA,
            final VarType varTypeB, final Class aClassA, final Class aClassB) {
        this.fieldNameA = fieldNameA;
        this.fieldNameB = fieldNameB;
        classA = aClassA;
        classB = aClassB;
        this.varTypeA = varTypeA;
        this.varTypeB = varTypeB;
    }

    public String getFieldNameA() {
        return fieldNameA;
    }

    public String getFieldNameB() {
        return fieldNameB;
    }

    public VarType getVarTypeA() {
        return varTypeA;
    }

    public VarType getVarTypeB() {
        return varTypeB;
    }

    @Override
    public void generateCodeIn(final XmlHibernateMappingGenerator xmlHibernateMappingGenerator,
            final XmlHibernateMappingGeneratorContext hibernateMappingGeneratorContext, final Cardinality cardinality) {
        cardinality.generateCodeIn(xmlHibernateMappingGenerator, hibernateMappingGeneratorContext, this);

    }

    @Override
    public void generateCodeIn(final XmlNHibernateMappingGenerator xmlNHibernateMappingGenerator,
            final XmlNHibernateMappingGeneratorContext hibernateMappingGeneratorContext, final Cardinality cardinality) {
        cardinality.generateCodeIn(xmlNHibernateMappingGenerator, hibernateMappingGeneratorContext, this);

    }

    @Override
    public boolean isBiDirectional() {
        return true;
    }

}
