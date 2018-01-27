package com.hexacta.booster.codegeneration.persistence.ormmodel;

import com.hexacta.booster.codegeneration.configuration.ClassFinder;
import com.hexacta.booster.codegeneration.configuration.NotSupportedClassException;
import com.hexacta.booster.codegeneration.persistence.configuration.hibernate.XmlHibernateCollectionGenerator;
import com.hexacta.booster.codegeneration.persistence.configuration.hibernate.XmlHibernateCollectionGeneratorContext;
import com.hexacta.booster.codegeneration.persistence.configuration.nhibernate.XmlNHibernateCollectionGenerator;
import com.hexacta.booster.codegeneration.persistence.configuration.nhibernate.XmlNHibernateCollectionGeneratorContext;
import com.hexacta.booster.project.configuration.ProjectType;

/**
 * 
 */
public class ArrayType extends CollectionType {

    private ElementType index;

    public ArrayType(final ElementType elementType, final ProjectType projectType) throws NotSupportedClassException {
        super(elementType);
        if (projectType.isJava()) {
            setIndex(new BasicType(ClassFinder.findForType("int", projectType)));
        } else {
            setIndex(new BasicType(ClassFinder.findForType("System.Int32", projectType)));
        }
    }

    @Override
    public void generateCodeIn(final XmlHibernateCollectionGenerator hibernateCollectionGenerator,
            final XmlHibernateCollectionGeneratorContext hibernateCollectionGeneratorContext) {
        hibernateCollectionGenerator.generateCodeFor(this, hibernateCollectionGeneratorContext);
    }

    @Override
    public void generateCodeIn(final XmlNHibernateCollectionGenerator xmlNHibernateCollectionGenerator,
            final XmlNHibernateCollectionGeneratorContext hibernateCollectionGeneratorContext) {
        xmlNHibernateCollectionGenerator.generateCodeFor(this, hibernateCollectionGeneratorContext);

    }

    @Override
    public boolean isIndexedCollection() {
        return true;
    }

    @Override
    public boolean isArrayType() {
        return true;
    }

    private void setIndex(final ElementType index) {
        this.index = index;
    }

    public ElementType getIndex() {
        return index;
    }
}
