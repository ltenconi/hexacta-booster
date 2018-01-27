package com.hexacta.booster.codegeneration.persistence.configuration.hibernate;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.hexacta.booster.codegeneration.configuration.CodeGeneratorConfiguration;
import com.hexacta.booster.codegeneration.persistence.ormmodel.CollectionType;
import com.hexacta.booster.codegeneration.persistence.ormmodel.OrmMapping;

/**
 * 
 */
public class XmlHibernateCollectionGeneratorContext {

    private OrmMapping ormMapping;

    private CollectionType collectionType;

    private Document xmlMapping;

    private Element property;

    private CodeGeneratorConfiguration codeGeneratorConfiguration;

    public XmlHibernateCollectionGeneratorContext(final Document xmlMapping, final CollectionType collectionType,
            final OrmMapping ormMapping, final CodeGeneratorConfiguration codeGeneratorConfiguration) {
        this.collectionType = collectionType;
        this.xmlMapping = xmlMapping;
        this.ormMapping = ormMapping;
        this.codeGeneratorConfiguration = codeGeneratorConfiguration;
    }

    public CollectionType getCollectionType() {
        return collectionType;
    }

    public Element getProperty() {
        return property;
    }

    public Document getXmlMapping() {
        return xmlMapping;
    }

    public void setProperty(final Element property) {
        this.property = property;
    }

    public OrmMapping getOrmMapping() {
        return ormMapping;
    }

    public CodeGeneratorConfiguration getCodeGeneratorConfiguration() {
        return codeGeneratorConfiguration;
    }

}
