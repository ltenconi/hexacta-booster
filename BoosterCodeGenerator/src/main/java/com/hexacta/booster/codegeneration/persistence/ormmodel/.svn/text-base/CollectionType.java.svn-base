package com.hexacta.booster.codegeneration.persistence.ormmodel;

import com.hexacta.booster.codegeneration.metamodel.Class;
import com.hexacta.booster.codegeneration.persistence.configuration.hibernate.XmlHibernateCollectionGenerator;
import com.hexacta.booster.codegeneration.persistence.configuration.hibernate.XmlHibernateCollectionGeneratorContext;
import com.hexacta.booster.codegeneration.persistence.configuration.nhibernate.XmlNHibernateCollectionGenerator;
import com.hexacta.booster.codegeneration.persistence.configuration.nhibernate.XmlNHibernateCollectionGeneratorContext;

/**
 * 
 */
public abstract class CollectionType extends VarType {

    protected ElementType elementType;

    public abstract void generateCodeIn(XmlHibernateCollectionGenerator hibernateCollectionGenerator,
            XmlHibernateCollectionGeneratorContext hibernateCollectionGeneratorContext);

    public CollectionType(final ElementType elementType) {
        this.elementType = elementType;
    }

    public ElementType getElementType() {
        return elementType;
    }

    @Override
    public boolean isCollection() {
        return true;
    }

    @Override
    public boolean isAssociation() {
        return elementType.isAssociation();
    }

    @Override
    public Class getAssociatedClass() {
        return elementType.getAssociatedClass();
    }

    public abstract void generateCodeIn(XmlNHibernateCollectionGenerator xmlNHibernateCollectionGenerator,
            XmlNHibernateCollectionGeneratorContext hibernateCollectionGeneratorContext);

}
