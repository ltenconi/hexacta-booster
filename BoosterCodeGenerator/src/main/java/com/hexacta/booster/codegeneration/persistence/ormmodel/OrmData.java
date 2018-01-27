package com.hexacta.booster.codegeneration.persistence.ormmodel;

import java.util.List;

import com.hexacta.booster.codegeneration.metamodel.Class;

/**
 * 
 */
public class OrmData {

    private List<OrmMapping> ormMappings;

    private List<OrmMapping> ormMappingsHierarchy;

    public OrmData(final List<OrmMapping> ormMappings, final List<OrmMapping> ormMappingsHierarchy) {
        this.ormMappings = ormMappings;
        this.ormMappingsHierarchy = ormMappingsHierarchy;
    }

    public List<OrmMapping> getOrmMappings() {
        return ormMappings;
    }

    public List<OrmMapping> getOrmMappingsHierarchy() {
        return ormMappingsHierarchy;
    }

    public OrmMapping getMapping(final Class aClass) {
        for (OrmMapping ormMapping : ormMappings) {
            if (ormMapping.getMappingClass().getName().equals(aClass.getName()))
                return ormMapping;
        }
        return null;
    }

}
