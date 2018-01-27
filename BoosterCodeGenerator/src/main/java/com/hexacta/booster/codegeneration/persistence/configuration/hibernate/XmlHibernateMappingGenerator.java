package com.hexacta.booster.codegeneration.persistence.configuration.hibernate;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.hexacta.booster.codegeneration.configuration.CodeGeneratorConfiguration;
import com.hexacta.booster.codegeneration.persistence.ormmodel.BiDirectional;
import com.hexacta.booster.codegeneration.persistence.ormmodel.CollectionType;
import com.hexacta.booster.codegeneration.persistence.ormmodel.ElementType;
import com.hexacta.booster.codegeneration.persistence.ormmodel.ManyToMany;
import com.hexacta.booster.codegeneration.persistence.ormmodel.ManyToOne;
import com.hexacta.booster.codegeneration.persistence.ormmodel.OneToMany;
import com.hexacta.booster.codegeneration.persistence.ormmodel.OneToOne;
import com.hexacta.booster.codegeneration.persistence.ormmodel.OrmMapping;
import com.hexacta.booster.codegeneration.persistence.ormmodel.OrmProperty;
import com.hexacta.booster.codegeneration.persistence.ormmodel.OrmRelation;
import com.hexacta.booster.codegeneration.persistence.ormmodel.UniDirectional;
import com.hexacta.booster.codegeneration.persistence.ormmodel.VarType;
import com.hexacta.booster.project.configuration.ProjectConfigurationTool;
import com.sun.org.apache.xerces.internal.dom.DocumentImpl;

/**
 * 
 */
public class XmlHibernateMappingGenerator {

    private XmlHibernateCollectionGenerator hibernateCollectionGenerator;

    public XmlHibernateMappingGenerator() {
        hibernateCollectionGenerator = new XmlHibernateCollectionGenerator();
    }

    public Document generateXmlMappingFor(final OrmMapping ormMapping,
            final CodeGeneratorConfiguration codeGeneratorConfiguration,
            final ProjectConfigurationTool projectConfigurationTool) {

        Document xmlMapping = new DocumentImpl();

        Element hibernateMapping = xmlMapping.createElement("hibernate-mapping");

        hibernateMapping.setAttribute("default-access", "field");
        hibernateMapping.setAttribute("default-cascade", "save-update");

        Element currentElement = xmlMapping.createElement("class");
        currentElement.setAttribute("name", ormMapping.getMappingClass().getName());
        currentElement.setAttribute("table", ormMapping.getMappingClass().getSimpleName());

        hibernateMapping.appendChild(currentElement);
        xmlMapping.appendChild(hibernateMapping);

        XmlHibernateMappingGeneratorContext hibernateMappingGeneratorContext = new XmlHibernateMappingGeneratorContext(
                xmlMapping, ormMapping, currentElement, codeGeneratorConfiguration);
        return generateMappingFor(hibernateMappingGeneratorContext);

    }

    public Document generateMappingFor(final XmlHibernateMappingGeneratorContext hibernateMappingGeneratorContext) {

        OrmMapping ormMapping = hibernateMappingGeneratorContext.getOrmMapping();

        if (!ormMapping.isSubMapping()) {
            generateIdElement(hibernateMappingGeneratorContext);
        }

        if (!ormMapping.getSubClassOrmMapping().isEmpty() && !hasDiscriminator(hibernateMappingGeneratorContext)) {
            generateDiscriminatorElement(hibernateMappingGeneratorContext);
        }

        for (OrmProperty ormProperty : ormMapping.getOrmProperties()) {
            OrmProperty prop = ormProperty;
            prop.generateCodeIn(this, hibernateMappingGeneratorContext);
        }

        for (OrmRelation ormRelation : ormMapping.getOrmRelations()) {
            OrmRelation rel = ormRelation;
            rel.generateCodeIn(this, hibernateMappingGeneratorContext);
        }

        for (OrmMapping subOrmMapping : ormMapping.getSubClassOrmMapping()) {

            setToGenerateCodeForSubMapping(hibernateMappingGeneratorContext, subOrmMapping);
            generateMappingFor(hibernateMappingGeneratorContext);

        }

        return hibernateMappingGeneratorContext.getXmlMapping();

    }

    public void generateIdElement(final XmlHibernateMappingGeneratorContext hibernateMappingGeneratorContext) {

        Document xmlMapping = hibernateMappingGeneratorContext.getXmlMapping();
        Node currentElement = hibernateMappingGeneratorContext.getCurrentElement();
        OrmMapping ormMapping = hibernateMappingGeneratorContext.getOrmMapping();

        Element idElement = xmlMapping.createElement("id");
        idElement.setAttribute("name", ormMapping.getClassId());
        idElement.setAttribute("column", ormMapping.getClassId().toUpperCase());

        Element generator = xmlMapping.createElement("generator");
        generator.setAttribute("class", "native");

        idElement.appendChild(generator);

        currentElement.appendChild(idElement);

    }

    public void setToGenerateCodeForSubMapping(
            final XmlHibernateMappingGeneratorContext hibernateMappingGeneratorContext, final OrmMapping subOrmMapping) {

        // String projectPackageName =
        // hibernateMappingGeneratorContext.getCodeGeneratorConfiguration
        // ().getProyectPackageName();

        hibernateMappingGeneratorContext.setOrmMapping(subOrmMapping);

        Document xmlMapping = hibernateMappingGeneratorContext.getXmlMapping();
        Element subClass = xmlMapping.createElement("subclass");
        subClass.setAttribute("name", subOrmMapping.getMappingClass().getName());
        subClass.setAttribute("discriminator-value", subOrmMapping.getMappingClass().getSimpleName().subSequence(0, 3)
                .toString().toUpperCase());

        hibernateMappingGeneratorContext.setCurrentElement(subClass);
        Node hibernateMapping = xmlMapping.getElementsByTagName("hibernate-mapping").item(0);

        hibernateMapping.getFirstChild().appendChild(hibernateMappingGeneratorContext.getCurrentElement());

    }

    public void generateDiscriminatorElement(final XmlHibernateMappingGeneratorContext hibernateMappingGeneratorContext) {

        Document xmlMapping = hibernateMappingGeneratorContext.getXmlMapping();
        Node currentElement = hibernateMappingGeneratorContext.getCurrentElement();

        Element discriminator = xmlMapping.createElement("discriminator");
        Element column = xmlMapping.createElement("column");
        column.setAttribute("name", "DISCRIMINATOR");
        discriminator.appendChild(column);

        currentElement.appendChild(discriminator);
    }

    public boolean hasDiscriminator(final XmlHibernateMappingGeneratorContext hibernateMappingGeneratorContext) {

        Document xmlMapping = hibernateMappingGeneratorContext.getXmlMapping();

        NodeList nodeList = xmlMapping.getElementsByTagName("discriminator");
        return nodeList.getLength() > 0;
    }

    public void generateCodeFor(final OrmProperty ormProperty,
            final XmlHibernateMappingGeneratorContext hibernateMappingGeneratorContext) {

        Document xmlMapping = hibernateMappingGeneratorContext.getXmlMapping();
        Node currentElement = hibernateMappingGeneratorContext.getCurrentElement();
        OrmMapping ormMapping = hibernateMappingGeneratorContext.getOrmMapping();
        VarType varType = ormProperty.getVarType();
        Element property;

        if (varType.isCollection()) {

            CollectionType collectionType = (CollectionType) varType;
            XmlHibernateCollectionGeneratorContext hibernateCollectionGeneratorContext = new XmlHibernateCollectionGeneratorContext(
                    xmlMapping, collectionType, ormMapping, hibernateMappingGeneratorContext
                            .getCodeGeneratorConfiguration());
            property = hibernateCollectionGenerator.generateCodeFor(ormProperty, hibernateCollectionGeneratorContext);

        } else if (((ElementType) varType).isBasicType()) {

            property = xmlMapping.createElement("property");
            property.setAttribute("name", ormProperty.getName());

        } else {
            /** composite type */

            property = xmlMapping.createElement("composite-elem-prop");
            property.setAttribute("name", ormProperty.getName());

        }

        currentElement.appendChild(property);

    }

    public void generateCodeFor(final BiDirectional biDirectional, final OneToOne oneToOne,
            final XmlHibernateMappingGeneratorContext hibernateMappingGeneratorContext) {

        Document xmlMapping = hibernateMappingGeneratorContext.getXmlMapping();
        Node currentElement = hibernateMappingGeneratorContext.getCurrentElement();
        OrmMapping ormMapping = hibernateMappingGeneratorContext.getOrmMapping();

        if (!ormMapping.getMappingClass().getName().equalsIgnoreCase(biDirectional.getClassB().getName())) {

            Element manyToOne = xmlMapping.createElement("many-to-one");
            manyToOne.setAttribute("name", biDirectional.getFieldNameA());
            manyToOne.setAttribute("column", biDirectional.getFieldNameA() + "Id");
            // manyToOne.setAttribute("column",
            // biDirectional.getClassB().getSimpleName()
            // + "Id");
            manyToOne.setAttribute("unique", "true");
            // manyToOne.setAttribute("not-null", "true");

            currentElement.appendChild(manyToOne);

        } else {

            Element oneToOneElem = xmlMapping.createElement("one-to-one");
            oneToOneElem.setAttribute("name", biDirectional.getFieldNameB());
            oneToOneElem.setAttribute("property-ref", biDirectional.getFieldNameA());

            currentElement.appendChild(oneToOneElem);

        }

    }

    public void generateCodeFor(final BiDirectional biDirectional, final OneToMany oneToMany,
            final XmlHibernateMappingGeneratorContext hibernateMappingGeneratorContext) {

        Document xmlMapping = hibernateMappingGeneratorContext.getXmlMapping();
        Node currentElement = hibernateMappingGeneratorContext.getCurrentElement();
        OrmMapping ormMapping = hibernateMappingGeneratorContext.getOrmMapping();

        if (!ormMapping.getMappingClass().getName().equalsIgnoreCase(biDirectional.getClassB().getName())) {
            XmlHibernateCollectionGeneratorContext hibernateCollectionGeneratorContext = new XmlHibernateCollectionGeneratorContext(
                    xmlMapping, (CollectionType) biDirectional.getVarTypeA(), ormMapping,
                    hibernateMappingGeneratorContext.getCodeGeneratorConfiguration());
            Element collection = hibernateCollectionGenerator.generateCodeFor(biDirectional, oneToMany,
                    hibernateCollectionGeneratorContext);

            currentElement.appendChild(collection);

        } else {

            Element manyToOne = xmlMapping.createElement("many-to-one");
            manyToOne.setAttribute("name", biDirectional.getFieldNameB());
            manyToOne.setAttribute("column", biDirectional.getFieldNameB() + "Id");
            // manyToOne.setAttribute("column",
            // biDirectional.getClassA().getSimpleName()
            // + "Id");

            if (biDirectional.getVarTypeA().isIndexedCollection()) {
                // manyToOne.setAttribute("not-null", "true");
                manyToOne.setAttribute("insert", "false");
                manyToOne.setAttribute("update", "false");
            }

            currentElement.appendChild(manyToOne);

        }

    }

    public void generateCodeFor(final BiDirectional biDirectional, final ManyToOne manyToOne,
            final XmlHibernateMappingGeneratorContext hibernateMappingGeneratorContext) {

        Document xmlMapping = hibernateMappingGeneratorContext.getXmlMapping();
        Node currentElement = hibernateMappingGeneratorContext.getCurrentElement();
        OrmMapping ormMapping = hibernateMappingGeneratorContext.getOrmMapping();

        if (!ormMapping.getMappingClass().getName().equalsIgnoreCase(biDirectional.getClassB().getName())) {

            Element manyToOneElem = xmlMapping.createElement("many-to-one");
            manyToOneElem.setAttribute("name", biDirectional.getFieldNameA());
            manyToOneElem.setAttribute("column", biDirectional.getFieldNameA() + "Id");
            // manyToOneElem.setAttribute("column",
            // biDirectional.getClassB().getSimpleName()
            // + "Id");

            if (biDirectional.getVarTypeB().isIndexedCollection()) {
                // manyToOneElem.setAttribute("not-null", "true");
                manyToOneElem.setAttribute("insert", "false");
                manyToOneElem.setAttribute("update", "false");
            }

            currentElement.appendChild(manyToOneElem);

        } else {

            XmlHibernateCollectionGeneratorContext hibernateCollectionGeneratorContext = new XmlHibernateCollectionGeneratorContext(
                    xmlMapping, (CollectionType) biDirectional.getVarTypeB(), ormMapping,
                    hibernateMappingGeneratorContext.getCodeGeneratorConfiguration());
            Element collection = hibernateCollectionGenerator.generateCodeFor(biDirectional, manyToOne,
                    hibernateCollectionGeneratorContext);

            currentElement.appendChild(collection);

        }
    }

    public void generateCodeFor(final BiDirectional biDirectional, final ManyToMany manyToMany,
            final XmlHibernateMappingGeneratorContext hibernateMappingGeneratorContext) {

        Document xmlMapping = hibernateMappingGeneratorContext.getXmlMapping();
        Node currentElement = hibernateMappingGeneratorContext.getCurrentElement();
        OrmMapping ormMapping = hibernateMappingGeneratorContext.getOrmMapping();

        if (!ormMapping.getMappingClass().getName().equalsIgnoreCase(biDirectional.getClassB().getName())) {

            XmlHibernateCollectionGeneratorContext hibernateCollectionGeneratorContext = new XmlHibernateCollectionGeneratorContext(
                    xmlMapping, (CollectionType) biDirectional.getVarTypeA(), ormMapping,
                    hibernateMappingGeneratorContext.getCodeGeneratorConfiguration());
            Element collection = hibernateCollectionGenerator.generateSideACodeFor(biDirectional, manyToMany,
                    hibernateCollectionGeneratorContext);

            if (!((CollectionType) biDirectional.getVarTypeA()).isIndexedCollection()) {
                collection.setAttribute("inverse", "true");
            }

            currentElement.appendChild(collection);

        } else {

            XmlHibernateCollectionGeneratorContext hibernateCollectionGeneratorContext = new XmlHibernateCollectionGeneratorContext(
                    xmlMapping, (CollectionType) biDirectional.getVarTypeB(), ormMapping,
                    hibernateMappingGeneratorContext.getCodeGeneratorConfiguration());
            Element collection = hibernateCollectionGenerator.generateSideBCodeFor(biDirectional, manyToMany,
                    hibernateCollectionGeneratorContext);

            if (((CollectionType) biDirectional.getVarTypeA()).isIndexedCollection()
                    && !((CollectionType) biDirectional.getVarTypeB()).isIndexedCollection()) {
                collection.setAttribute("inverse", "true");
            }

            currentElement.appendChild(collection);

        }

    }

    public void generateCodeFor(final UniDirectional uniDirectional, final OneToOne oneToOne,
            final XmlHibernateMappingGeneratorContext hibernateMappingGeneratorContext) {

        Document xmlMapping = hibernateMappingGeneratorContext.getXmlMapping();
        Node currentElement = hibernateMappingGeneratorContext.getCurrentElement();

        Element manyToOne = xmlMapping.createElement("many-to-one");
        manyToOne.setAttribute("name", uniDirectional.getFieldNameA());
        manyToOne.setAttribute("column", uniDirectional.getFieldNameA() + "Id");
        // manyToOne.setAttribute("column",
        // uniDirectional.getClassA().getSimpleName() + "Id");
        manyToOne.setAttribute("unique", "true");
        // manyToOne.setAttribute("not-null", "true");

        currentElement.appendChild(manyToOne);

    }

    public void generateCodeFor(final UniDirectional uniDirectional, final OneToMany oneToMany,
            final XmlHibernateMappingGeneratorContext hibernateMappingGeneratorContext) {

        Document xmlMapping = hibernateMappingGeneratorContext.getXmlMapping();
        Node currentElement = hibernateMappingGeneratorContext.getCurrentElement();

        XmlHibernateCollectionGeneratorContext hibernateCollectionGeneratorContext = new XmlHibernateCollectionGeneratorContext(
                xmlMapping, (CollectionType) uniDirectional.getVarTypeA(), hibernateMappingGeneratorContext
                        .getOrmMapping(), hibernateMappingGeneratorContext.getCodeGeneratorConfiguration());

        Element collection = hibernateCollectionGenerator.generateCodeFor(uniDirectional, oneToMany,
                hibernateCollectionGeneratorContext);

        currentElement.appendChild(collection);

    }

    public void generateCodeFor(final UniDirectional uniDirectional, final ManyToOne manyToOne,
            final XmlHibernateMappingGeneratorContext hibernateMappingGeneratorContext) {

        Document xmlMapping = hibernateMappingGeneratorContext.getXmlMapping();
        Node currentElement = hibernateMappingGeneratorContext.getCurrentElement();

        Element manyToOneElem = xmlMapping.createElement("many-to-one");
        manyToOneElem.setAttribute("name", uniDirectional.getFieldNameA());
        manyToOneElem.setAttribute("column", uniDirectional.getFieldNameA() + "Id");
        // manyToOneElem.setAttribute("column",
        // uniDirectional.getClassB().getSimpleName()
        // + "Id");
        // manyToOneElem.setAttribute("not-null", "true");

        currentElement.appendChild(manyToOneElem);

    }

    public void generateCodeFor(final UniDirectional uniDirectional, final ManyToMany manyToMany,
            final XmlHibernateMappingGeneratorContext hibernateMappingGeneratorContext) {

        Document xmlMapping = hibernateMappingGeneratorContext.getXmlMapping();
        Node currentElement = hibernateMappingGeneratorContext.getCurrentElement();

        XmlHibernateCollectionGeneratorContext hibernateCollectionGeneratorContext = new XmlHibernateCollectionGeneratorContext(
                xmlMapping, (CollectionType) uniDirectional.getVarTypeA(), hibernateMappingGeneratorContext
                        .getOrmMapping(), hibernateMappingGeneratorContext.getCodeGeneratorConfiguration());

        Element collection = hibernateCollectionGenerator.generateCodeFor(uniDirectional, manyToMany,
                hibernateCollectionGeneratorContext);

        currentElement.appendChild(collection);

    }

}