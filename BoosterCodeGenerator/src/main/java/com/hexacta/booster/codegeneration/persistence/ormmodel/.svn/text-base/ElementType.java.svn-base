package com.hexacta.booster.codegeneration.persistence.ormmodel;

import com.hexacta.booster.codegeneration.metamodel.Class;
import com.hexacta.booster.codegeneration.persistence.configuration.hibernate.XmlHibernateCollectionGenerator;
import com.hexacta.booster.codegeneration.persistence.configuration.hibernate.XmlHibernateCollectionGeneratorContext;
import com.hexacta.booster.codegeneration.persistence.configuration.nhibernate.XmlNHibernateCollectionGenerator;
import com.hexacta.booster.codegeneration.persistence.configuration.nhibernate.XmlNHibernateCollectionGeneratorContext;

/**
 * 
 * 
 * 
 * Created on 26/01/2009
 * 
 * @author clopez
 * 
 */
public abstract class ElementType extends VarType {

    protected Class aClass;

    public ElementType(final Class aClass) {
        this.aClass = aClass;
    }

    public Class getElementClass() {
        return aClass;
    }

    @Override
    public Class getAssociatedClass() {
        return getElementClass();
    }

    public abstract void generateIndexCodeIn(XmlHibernateCollectionGenerator hibernateCollectionGenerator,
            XmlHibernateCollectionGeneratorContext hibernateCollectionGeneratorContext, MapType mapType);

    public abstract void generateIndexCodeIn(XmlNHibernateCollectionGenerator xmlNHibernateCollectionGenerator,
            XmlNHibernateCollectionGeneratorContext hibernateCollectionGeneratorContext, MapType mapType);

}
