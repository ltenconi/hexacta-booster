package com.hexacta.booster.codegeneration.persistence.ormmodel;

import com.hexacta.booster.codegeneration.metamodel.Class;
import com.hexacta.booster.codegeneration.persistence.configuration.hibernate.XmlHibernateCollectionGenerator;
import com.hexacta.booster.codegeneration.persistence.configuration.hibernate.XmlHibernateCollectionGeneratorContext;
import com.hexacta.booster.codegeneration.persistence.configuration.nhibernate.XmlNHibernateCollectionGenerator;
import com.hexacta.booster.codegeneration.persistence.configuration.nhibernate.XmlNHibernateCollectionGeneratorContext;

/**
 * 
 */
public class ListType extends CollectionType {

    private ElementType index;

    public ListType(final ElementType elementType) {
        super(elementType);
        index = new BasicType(new Class("int"));
    }

    @Override
    public void generateCodeIn(final XmlHibernateCollectionGenerator hibernateCollectionGenerator,
            final XmlHibernateCollectionGeneratorContext hibernateCollectionGeneratorContext) {
        hibernateCollectionGenerator.generateCodeFor(this, hibernateCollectionGeneratorContext);

    }

    @Override
    public boolean isIndexedCollection() {
        return true;
    }

    @Override
    public boolean isListType() {
        return true;
    }

    public void setIndex(final ElementType index) {
        this.index = index;
    }

    public ElementType getIndex() {
        return index;
    }

    @Override
    public void generateCodeIn(final XmlNHibernateCollectionGenerator xmlNHibernateCollectionGenerator,
            final XmlNHibernateCollectionGeneratorContext hibernateCollectionGeneratorContext) {
        xmlNHibernateCollectionGenerator.generateCodeFor(this, hibernateCollectionGeneratorContext);
    }
}
