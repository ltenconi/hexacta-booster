package com.hexacta.booster.codegeneration.persistence.ormmodel;

import com.hexacta.booster.codegeneration.persistence.configuration.hibernate.XmlHibernateCollectionGenerator;
import com.hexacta.booster.codegeneration.persistence.configuration.hibernate.XmlHibernateCollectionGeneratorContext;
import com.hexacta.booster.codegeneration.persistence.configuration.nhibernate.XmlNHibernateCollectionGenerator;
import com.hexacta.booster.codegeneration.persistence.configuration.nhibernate.XmlNHibernateCollectionGeneratorContext;

/**
 * 
 */
public class MapType extends CollectionType {

    private ElementType indexType;

    public MapType(final ElementType indexType, final ElementType elementType) {
        super(elementType);
        this.indexType = indexType;
    }

    public ElementType getIndexType() {
        return indexType;
    }

    @Override
    public void generateCodeIn(final XmlHibernateCollectionGenerator generator,
            final XmlHibernateCollectionGeneratorContext hibernateCollectionGeneratorContext) {
        generator.generateCodeFor(this, hibernateCollectionGeneratorContext);
    }

    @Override
    public boolean isIndexedCollection() {
        return true;
    }

    @Override
    public boolean isMapType() {
        return true;
    }

    @Override
    public void generateCodeIn(final XmlNHibernateCollectionGenerator xmlNHibernateCollectionGenerator,
            final XmlNHibernateCollectionGeneratorContext hibernateCollectionGeneratorContext) {
        xmlNHibernateCollectionGenerator.generateCodeFor(this, hibernateCollectionGeneratorContext);

    }

}
