package com.hexacta.booster.codegeneration.persistence.ormmodel;

import java.util.ArrayList;
import java.util.List;

import com.hexacta.booster.codegeneration.metamodel.Class;

/**
 * 
 */
public class OrmMapping {

    private Class mapppingClass;

    private String tableName;

    private String classId;

    private boolean subMapping;

    private List<OrmProperty> ormProperties;

    private List<OrmRelation> ormRelations;

    private List<OrmMapping> subClassOrmMapping;

    public OrmMapping(final Class aClass, final String classId) {

        mapppingClass = aClass;
        tableName = aClass.getName().toUpperCase();
        ormProperties = new ArrayList<OrmProperty>();
        ormRelations = new ArrayList<OrmRelation>();
        subClassOrmMapping = new ArrayList<OrmMapping>();
        this.classId = classId;
        subMapping = false;

    }

    public Class getMappingClass() {
        return mapppingClass;
    }

    public String getTableName() {
        return tableName;
    }

    public String getClassId() {
        return classId;
    }

    public void setSubMapping(final boolean subMapping) {
        this.subMapping = subMapping;
    }

    public List<OrmProperty> getOrmProperties() {
        return ormProperties;
    }

    public List<OrmRelation> getOrmRelations() {
        return ormRelations;
    }

    public List<OrmMapping> getSubClassOrmMapping() {
        return subClassOrmMapping;
    }

    public void addOrmProperty(final OrmProperty ormProperty) {
        ormProperties.add(ormProperty);
    }

    public void addOrmRelation(final OrmRelation ormRelation) {
        ormRelations.add(ormRelation);
    }

    public void addSubClassOrmMapping(final OrmMapping ormMapping) {
        subClassOrmMapping.add(ormMapping);
    }

    public boolean isSubMapping() {
        return subMapping;
    }

    public boolean hasMapppingClass(final String className) {

        for (OrmMapping mapping : subClassOrmMapping) {
            if (mapping.getMappingClass().getName().equalsIgnoreCase(className))
                return true;
        }
        return false;
    }

    /**
     * @param subClassOrmMapping
     *            the subClassOrmMapping to set
     */
    public void setSubClassOrmMapping(final List<OrmMapping> subClassOrmMapping) {
        this.subClassOrmMapping = subClassOrmMapping;
    }

}
