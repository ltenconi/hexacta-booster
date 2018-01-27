package com.hexacta.booster.codegeneration.persistence.ormmodel;

import com.hexacta.booster.codegeneration.persistence.configuration.hibernate.XmlHibernateMappingGenerator;
import com.hexacta.booster.codegeneration.persistence.configuration.hibernate.XmlHibernateMappingGeneratorContext;
import com.hexacta.booster.codegeneration.persistence.configuration.nhibernate.XmlNHibernateMappingGenerator;
import com.hexacta.booster.codegeneration.persistence.configuration.nhibernate.XmlNHibernateMappingGeneratorContext;

/**
 * 
 */
public class OrmRelation {

    private AssociationType associationType;

    private Cardinality cardinality;

    public OrmRelation(final AssociationType associationType, final Cardinality cardinality) {

        this.associationType = associationType;
        this.cardinality = cardinality;

    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    public AssociationType getAssociationType() {
        return associationType;
    }

    public Cardinality getCardinality() {
        return cardinality;
    }

    public void generateCodeIn(final XmlHibernateMappingGenerator hibernateMappingGenerator,
            final XmlHibernateMappingGeneratorContext mappingGeneratorContext) {
        associationType.generateCodeIn(hibernateMappingGenerator, mappingGeneratorContext, cardinality);
    }

    public void generateCodeIn(final XmlNHibernateMappingGenerator xmlNHibernateMappingGenerator,
            final XmlNHibernateMappingGeneratorContext hibernateMappingGeneratorContext) {
        associationType.generateCodeIn(xmlNHibernateMappingGenerator, hibernateMappingGeneratorContext, cardinality);
    }

}
