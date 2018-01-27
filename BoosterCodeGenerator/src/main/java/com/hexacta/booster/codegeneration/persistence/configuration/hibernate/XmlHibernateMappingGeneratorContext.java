package com.hexacta.booster.codegeneration.persistence.configuration.hibernate;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import com.hexacta.booster.codegeneration.configuration.CodeGeneratorConfiguration;
import com.hexacta.booster.codegeneration.persistence.ormmodel.OrmMapping;

/**
 * 
 */
public class XmlHibernateMappingGeneratorContext {

    private Document xmlMapping;

    private OrmMapping ormMapping;

    private Node currentElement;

    /**
     * the mapping element on which the code generation happens.
     */
    private CodeGeneratorConfiguration codeGeneratorConfiguration;

    public XmlHibernateMappingGeneratorContext(final Document xmlMapping, final OrmMapping ormMapping,
            final Node currentElment, final CodeGeneratorConfiguration codeGeneratorConfiguration) {
        this.ormMapping = ormMapping;
        this.xmlMapping = xmlMapping;
        currentElement = currentElment;
        this.codeGeneratorConfiguration = codeGeneratorConfiguration;

    }

    public Node getCurrentElement() {
        return currentElement;
    }

    public OrmMapping getOrmMapping() {
        return ormMapping;
    }

    public Document getXmlMapping() {
        return xmlMapping;
    }

    public void setCurrentElement(final Element subClass) {
        currentElement = subClass;

    }

    public void setOrmMapping(final OrmMapping subOrmMapping) {
        ormMapping = subOrmMapping;

    }

    public CodeGeneratorConfiguration getCodeGeneratorConfiguration() {
        return codeGeneratorConfiguration;
    }

}
