package com.hexacta.booster.codegeneration.persistence.ormmodel;

import com.hexacta.booster.codegeneration.metamodel.Class;
import com.hexacta.booster.codegeneration.persistence.configuration.hibernate.XmlHibernateCollectionGenerator;
import com.hexacta.booster.codegeneration.persistence.configuration.hibernate.XmlHibernateCollectionGeneratorContext;
import com.hexacta.booster.codegeneration.persistence.configuration.nhibernate.XmlNHibernateCollectionGenerator;
import com.hexacta.booster.codegeneration.persistence.configuration.nhibernate.XmlNHibernateCollectionGeneratorContext;

/**
 * 
 */
public class EntityReference extends ElementType {

    public EntityReference(final Class aClass) {
        super(aClass);
    }

    @Override
    public boolean isEntityReference() {
        return true;
    }

    @Override
    public boolean isAssociation() {
        return true;
    }

    @Override
    public void generateIndexCodeIn(final XmlHibernateCollectionGenerator hibernateCollectionGenerator,
            final XmlHibernateCollectionGeneratorContext hibernateCollectionGeneratorContext, final MapType mapType) {
        hibernateCollectionGenerator.generateIndexCodeFor(mapType, this, hibernateCollectionGeneratorContext);

    }

    @Override
    public void generateIndexCodeIn(final XmlNHibernateCollectionGenerator xmlNHibernateCollectionGenerator,
            final XmlNHibernateCollectionGeneratorContext hibernateCollectionGeneratorContext, final MapType mapType) {
        xmlNHibernateCollectionGenerator.generateIndexCodeFor(mapType, this, hibernateCollectionGeneratorContext);

    }

}
