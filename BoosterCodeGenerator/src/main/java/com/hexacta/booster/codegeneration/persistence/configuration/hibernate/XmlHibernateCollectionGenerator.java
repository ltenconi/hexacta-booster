package com.hexacta.booster.codegeneration.persistence.configuration.hibernate;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.hexacta.booster.codegeneration.configuration.CodeGeneratorConfiguration;
import com.hexacta.booster.codegeneration.persistence.ormmodel.ArrayType;
import com.hexacta.booster.codegeneration.persistence.ormmodel.BasicType;
import com.hexacta.booster.codegeneration.persistence.ormmodel.BiDirectional;
import com.hexacta.booster.codegeneration.persistence.ormmodel.CollectionType;
import com.hexacta.booster.codegeneration.persistence.ormmodel.CompositeType;
import com.hexacta.booster.codegeneration.persistence.ormmodel.ElementType;
import com.hexacta.booster.codegeneration.persistence.ormmodel.EntityReference;
import com.hexacta.booster.codegeneration.persistence.ormmodel.ListType;
import com.hexacta.booster.codegeneration.persistence.ormmodel.ManyToMany;
import com.hexacta.booster.codegeneration.persistence.ormmodel.ManyToOne;
import com.hexacta.booster.codegeneration.persistence.ormmodel.MapType;
import com.hexacta.booster.codegeneration.persistence.ormmodel.OneToMany;
import com.hexacta.booster.codegeneration.persistence.ormmodel.OrmMapping;
import com.hexacta.booster.codegeneration.persistence.ormmodel.OrmProperty;
import com.hexacta.booster.codegeneration.persistence.ormmodel.SetType;
import com.hexacta.booster.codegeneration.persistence.ormmodel.UniDirectional;

/**
 * 
 */
public class XmlHibernateCollectionGenerator {

    public XmlHibernateCollectionGenerator() {

    }

    public Element generateCodeFor(final OrmProperty ormProperty,
            final XmlHibernateCollectionGeneratorContext hibernateCollectionGeneratorContext) {

        CodeGeneratorConfiguration codeGeneratorConfiguration = hibernateCollectionGeneratorContext
                .getCodeGeneratorConfiguration();

        Document xmlMapping = hibernateCollectionGeneratorContext.getXmlMapping();
        CollectionType collectionType = hibernateCollectionGeneratorContext.getCollectionType();
        OrmMapping ormMapping = hibernateCollectionGeneratorContext.getOrmMapping();
        /*
         * String projectPackageName =
         * hibernateCollectionGeneratorContext.getCodeGeneratorConfiguration
         * ().getProyectPackageName();
         */

        collectionType.generateCodeIn(this, hibernateCollectionGeneratorContext);

        Element property = hibernateCollectionGeneratorContext.getProperty();

        property.setAttribute("name", ormProperty.getName());
        ElementType collectionElementType = collectionType.getElementType();

        if (collectionElementType.isBasicType()) {
            Element keyElement = xmlMapping.createElement("key");
            keyElement.setAttribute("column", ormMapping.getMappingClass().getSimpleName() + "Id");
            property.insertBefore(keyElement, property.getFirstChild());

            Element collectionElement = xmlMapping.createElement("element");

            if (codeGeneratorConfiguration.getClassList().hasClass(collectionElementType.getElementClass().getName())) {
                collectionElement.setAttribute("type", ormProperty.getClass().getPackage().getName() + "."
                        + collectionElementType.getElementClass().getSimpleName());
            } else {
                collectionElement.setAttribute("type", collectionElementType.getElementClass().getName());
            }

            property.appendChild(collectionElement);
        }

        // if (collectionElementType.isCompositeType()) {
        // Element collectionElement = xmlMapping
        // .createElement("composite-element");
        // collectionElement.setAttribute("column",
        // collectionElementType.getClassName().getSimpleName());
        // property.appendChild(collectionElement);
        // }

        return property;
    }

    public Element generateCodeFor(final BiDirectional biDirectional, final OneToMany oneToMany,
            final XmlHibernateCollectionGeneratorContext hibernateCollectionGeneratorContext) {

        CollectionType collectionType = hibernateCollectionGeneratorContext.getCollectionType();
        Document xmlMapping = hibernateCollectionGeneratorContext.getXmlMapping();
        // String projectPackageName =
        // hibernateCollectionGeneratorContext.getCodeGeneratorConfiguration
        // ().getProyectPackageName();

        collectionType.generateCodeIn(this, hibernateCollectionGeneratorContext);

        Element property = hibernateCollectionGeneratorContext.getProperty();

        property.setAttribute("name", biDirectional.getFieldNameA());
        property.setAttribute("inverse", "true");

        Element key = xmlMapping.createElement("key");
        key.setAttribute("column", biDirectional.getClassA().getSimpleName() + "Id");
        property.insertBefore(key, property.getFirstChild());

        if (biDirectional.getVarTypeA().isIndexedCollection()) {
            // key.setAttribute("not-null","true");
            property.removeAttribute("inverse");
        }

        Element oneToManyElem = xmlMapping.createElement("one-to-many");
        oneToManyElem.setAttribute("class", biDirectional.getClassB().getName());
        property.appendChild(oneToManyElem);

        return property;
    }

    public Element generateCodeFor(final BiDirectional biDirectional, final ManyToOne manyToOne,
            final XmlHibernateCollectionGeneratorContext hibernateCollectionGeneratorContext) {

        CollectionType collectionType = hibernateCollectionGeneratorContext.getCollectionType();
        Document xmlMapping = hibernateCollectionGeneratorContext.getXmlMapping();
        // String projectPackageName =
        // hibernateCollectionGeneratorContext.getCodeGeneratorConfiguration
        // ().getProyectPackageName();

        collectionType.generateCodeIn(this, hibernateCollectionGeneratorContext);

        Element property = hibernateCollectionGeneratorContext.getProperty();

        property.setAttribute("name", biDirectional.getFieldNameB());
        property.setAttribute("inverse", "true");

        Element key = xmlMapping.createElement("key");
        key.setAttribute("column", biDirectional.getClassB().getSimpleName() + "Id");
        property.insertBefore(key, property.getFirstChild());

        if (biDirectional.getVarTypeB().isIndexedCollection()) {
            // key.setAttribute("not-null","true");
            property.removeAttribute("inverse");
        }

        Element oneToManyElem = xmlMapping.createElement("one-to-many");
        oneToManyElem.setAttribute("class", biDirectional.getClassA().getName());
        property.appendChild(oneToManyElem);

        return property;
    }

    public Element generateSideACodeFor(final BiDirectional biDirectional, final ManyToMany manyToMany,
            final XmlHibernateCollectionGeneratorContext hibernateCollectionGeneratorContext) {

        CollectionType collectionType = hibernateCollectionGeneratorContext.getCollectionType();
        Document xmlMapping = hibernateCollectionGeneratorContext.getXmlMapping();

        collectionType.generateCodeIn(this, hibernateCollectionGeneratorContext);

        Element property = hibernateCollectionGeneratorContext.getProperty();

        property.setAttribute("name", biDirectional.getFieldNameA());
        property.setAttribute("table", biDirectional.getClassA().getSimpleName() + "_"
                + biDirectional.getClassB().getSimpleName());

        Element key = xmlMapping.createElement("key");
        key.setAttribute("column", biDirectional.getClassA().getSimpleName() + "Id");
        property.insertBefore(key, property.getFirstChild());

        Element manyToManyElem = xmlMapping.createElement("many-to-many");
        manyToManyElem.setAttribute("column", biDirectional.getClassB().getSimpleName() + "Id");
        manyToManyElem.setAttribute("class", biDirectional.getClassB().getName());
        property.appendChild(manyToManyElem);

        return property;

    }

    public Element generateSideBCodeFor(final BiDirectional biDirectional, final ManyToMany manyToMany,
            final XmlHibernateCollectionGeneratorContext hibernateCollectionGeneratorContext) {

        CollectionType collectionType = hibernateCollectionGeneratorContext.getCollectionType();
        Document xmlMapping = hibernateCollectionGeneratorContext.getXmlMapping();

        collectionType.generateCodeIn(this, hibernateCollectionGeneratorContext);

        Element property = hibernateCollectionGeneratorContext.getProperty();

        property.setAttribute("name", biDirectional.getFieldNameB());
        property.setAttribute("table", biDirectional.getClassA().getSimpleName() + "_"
                + biDirectional.getClassB().getSimpleName());

        Element key = xmlMapping.createElement("key");
        key.setAttribute("column", biDirectional.getClassB().getSimpleName() + "Id");
        property.insertBefore(key, property.getFirstChild());

        Element manyToManyElem = xmlMapping.createElement("many-to-many");
        manyToManyElem.setAttribute("column", biDirectional.getClassA().getSimpleName() + "Id");
        manyToManyElem.setAttribute("class", biDirectional.getClassA().getName());
        property.appendChild(manyToManyElem);

        return property;

    }

    public Element generateCodeFor(final UniDirectional uniDirectional, final OneToMany oneToMany,
            final XmlHibernateCollectionGeneratorContext hibernateCollectionGeneratorContext) {

        CollectionType collectionType = hibernateCollectionGeneratorContext.getCollectionType();
        Document xmlMapping = hibernateCollectionGeneratorContext.getXmlMapping();
        // String projectPackageName =
        // hibernateCollectionGeneratorContext.getCodeGeneratorConfiguration
        // ().getProyectPackageName();

        collectionType.generateCodeIn(this, hibernateCollectionGeneratorContext);

        Element property = hibernateCollectionGeneratorContext.getProperty();

        property.setAttribute("name", uniDirectional.getFieldNameA());
        property.setAttribute("table", uniDirectional.getClassA().getSimpleName() + "_"
                + uniDirectional.getClassB().getSimpleName());

        Element key = xmlMapping.createElement("key");
        key.setAttribute("column", uniDirectional.getClassA().getSimpleName() + "Id");
        property.insertBefore(key, property.getFirstChild());

        Element manyToMany = xmlMapping.createElement("many-to-many");
        manyToMany.setAttribute("column", uniDirectional.getClassB().getSimpleName() + "Id");
        manyToMany.setAttribute("unique", "true");
        manyToMany.setAttribute("class", uniDirectional.getClassB().getName());
        property.appendChild(manyToMany);

        return property;

    }

    public Element generateCodeFor(final UniDirectional uniDirectional, final ManyToMany manyToMany,
            final XmlHibernateCollectionGeneratorContext hibernateCollectionGeneratorContext) {

        CollectionType collectionType = hibernateCollectionGeneratorContext.getCollectionType();
        Document xmlMapping = hibernateCollectionGeneratorContext.getXmlMapping();

        collectionType.generateCodeIn(this, hibernateCollectionGeneratorContext);

        Element property = hibernateCollectionGeneratorContext.getProperty();

        property.setAttribute("name", uniDirectional.getFieldNameA());
        property.setAttribute("table", uniDirectional.getClassA().getSimpleName() + "_"
                + uniDirectional.getClassB().getSimpleName());

        Element key = xmlMapping.createElement("key");
        key.setAttribute("column", uniDirectional.getClassA().getSimpleName() + "Id");
        property.insertBefore(key, property.getFirstChild());

        Element manyToManyElem = xmlMapping.createElement("many-to-many");
        manyToManyElem.setAttribute("column", uniDirectional.getClassB().getSimpleName() + "Id");
        manyToManyElem.setAttribute("class", uniDirectional.getClassB().getName());
        property.appendChild(manyToManyElem);

        return property;

    }

    public void generateCodeFor(final ArrayType arrayType,
            final XmlHibernateCollectionGeneratorContext hibernateCollectionGeneratorContext) {

        Document xmlMapping = hibernateCollectionGeneratorContext.getXmlMapping();
        Element property = xmlMapping.createElement("array");
        Element index = xmlMapping.createElement("list-index");
        index.setAttribute("column", "indx");
        property.appendChild(index);
        hibernateCollectionGeneratorContext.setProperty(property);
    }

    public void generateCodeFor(final ListType listType,
            final XmlHibernateCollectionGeneratorContext hibernateCollectionGeneratorContext) {

        Document xmlMapping = hibernateCollectionGeneratorContext.getXmlMapping();
        Element property = xmlMapping.createElement("list");
        Element index = xmlMapping.createElement("list-index");
        index.setAttribute("column", "indx");
        property.appendChild(index);
        hibernateCollectionGeneratorContext.setProperty(property);
    }

    public void generateCodeFor(final MapType mapType,
            final XmlHibernateCollectionGeneratorContext hibernateCollectionGeneratorContext) {

        Document xmlMapping = hibernateCollectionGeneratorContext.getXmlMapping();
        hibernateCollectionGeneratorContext.setProperty(xmlMapping.createElement("map"));
        ElementType indexType = mapType.getIndexType();
        indexType.generateIndexCodeIn(this, hibernateCollectionGeneratorContext, mapType);
    }

    public void generateCodeFor(final SetType setType,
            final XmlHibernateCollectionGeneratorContext hibernateCollectionGeneratorContext) {

        Document xmlMapping = hibernateCollectionGeneratorContext.getXmlMapping();
        hibernateCollectionGeneratorContext.setProperty(xmlMapping.createElement("set"));
    }

    public void generateIndexCodeFor(final MapType mapType, final BasicType basicType,
            final XmlHibernateCollectionGeneratorContext hibernateCollectionGeneratorContext) {

        Document xmlMapping = hibernateCollectionGeneratorContext.getXmlMapping();
        Element mapKey = xmlMapping.createElement("map-key");
        mapKey.setAttribute("type", basicType.getElementClass().getName());
        Element property = hibernateCollectionGeneratorContext.getProperty();
        property.appendChild(mapKey);
    }

    public void generateIndexCodeFor(final MapType mapType, final CompositeType compositeType,
            final XmlHibernateCollectionGeneratorContext hibernateCollectionGeneratorContext) {

        Document xmlMapping = hibernateCollectionGeneratorContext.getXmlMapping();
        Element mapKey = xmlMapping.createElement("composite-map-key");
        mapKey.setAttribute("type", compositeType.getElementClass().getName());
        Element property = hibernateCollectionGeneratorContext.getProperty();
        property.appendChild(mapKey);
    }

    public void generateIndexCodeFor(final MapType mapType, final EntityReference entityReference,
            final XmlHibernateCollectionGeneratorContext hibernateCollectionGeneratorContext) {

        Document xmlMapping = hibernateCollectionGeneratorContext.getXmlMapping();
        Element mapKey = xmlMapping.createElement("map-key-many-to-many");
        mapKey.setAttribute("class", entityReference.getElementClass().getName());
        Element property = hibernateCollectionGeneratorContext.getProperty();
        property.appendChild(mapKey);
    }

}
