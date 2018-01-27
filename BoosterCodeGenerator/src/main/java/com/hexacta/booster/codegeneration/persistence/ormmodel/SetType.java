package com.hexacta.booster.codegeneration.persistence.ormmodel;

import com.hexacta.booster.codegeneration.persistence.configuration.hibernate.XmlHibernateCollectionGenerator;
import com.hexacta.booster.codegeneration.persistence.configuration.hibernate.XmlHibernateCollectionGeneratorContext;
import com.hexacta.booster.codegeneration.persistence.configuration.nhibernate.XmlNHibernateCollectionGenerator;
import com.hexacta.booster.codegeneration.persistence.configuration.nhibernate.XmlNHibernateCollectionGeneratorContext;

/**
 * 
 */
public class SetType extends CollectionType {

    public SetType(final ElementType elementType) {
        super(elementType);

    }

    @Override
    public void generateCodeIn(final XmlHibernateCollectionGenerator hibernateCollectionGenerator,
            final XmlHibernateCollectionGeneratorContext hibernateCollectionGeneratorContext) {
        hibernateCollectionGenerator.generateCodeFor(this, hibernateCollectionGeneratorContext);

    }

    @Override
    public boolean isSetType() {
        return true;
    }

    @Override
    public void generateCodeIn(final XmlNHibernateCollectionGenerator xmlNHibernateCollectionGenerator,
            final XmlNHibernateCollectionGeneratorContext hibernateCollectionGeneratorContext) {
        xmlNHibernateCollectionGenerator.generateCodeFor(this, hibernateCollectionGeneratorContext);

    }

}
