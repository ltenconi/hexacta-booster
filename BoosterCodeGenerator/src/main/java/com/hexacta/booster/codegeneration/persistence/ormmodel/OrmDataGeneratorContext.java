package com.hexacta.booster.codegeneration.persistence.ormmodel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hexacta.booster.codegeneration.configuration.ClassIdMap;
import com.hexacta.booster.codegeneration.configuration.ClassList;
import com.hexacta.booster.codegeneration.configuration.CodeGeneratorConfiguration;
import com.hexacta.booster.codegeneration.configuration.CollectionVarTypeMap;
import com.hexacta.booster.project.configuration.ProjectType;

/**
 * 
 */
public class OrmDataGeneratorContext {

    private List<OrmMapping> ormMappings;

    private List<OrmMapping> ormMappingsHierarchy;

    private ClassList persistableClassList;

    private HashMap<String, AssociationType> biDirectionalAssociations;

    private Map<String, OrmMapping> ormMappingsMap;

    private ClassIdMap classIdMap;

    private CollectionVarTypeMap collectionVarTypeMap;

    private ProjectType projectType;

    public OrmDataGeneratorContext(final CodeGeneratorConfiguration codeGeneratorConfiguration,
            final ProjectType projectType) {

        biDirectionalAssociations = new HashMap<String, AssociationType>();
        ormMappings = new ArrayList<OrmMapping>();
        ormMappingsMap = new HashMap<String, OrmMapping>();
        persistableClassList = codeGeneratorConfiguration.getPersistableMetaModel();
        classIdMap = codeGeneratorConfiguration.getClassIdMap();
        collectionVarTypeMap = codeGeneratorConfiguration.getCollectionVarTypeMap();
        this.projectType = projectType;

    }

    public HashMap<String, AssociationType> getBiDirectionalAssociations() {
        return biDirectionalAssociations;
    }

    public ProjectType getProjectType() {
        return projectType;
    }

    public ClassIdMap getClassIdMap() {
        return classIdMap;
    }

    public ClassList getClassList() {
        return persistableClassList;
    }

    public CollectionVarTypeMap getCollectionVarTypeMap() {
        return collectionVarTypeMap;
    }

    public List<OrmMapping> getOrmMappings() {
        return ormMappings;
    }

    public List<OrmMapping> getOrmMappingsHierarchy() {
        return ormMappingsHierarchy;
    }

    public Map<String, OrmMapping> getOrmMappingsMap() {
        return ormMappingsMap;
    }

    public void setOrmMappings(final List<OrmMapping> ormMappings) {
        this.ormMappings = ormMappings;
    }

    public void setOrmMappingsHierarchy(final List<OrmMapping> ormMappingsHierarchy) {
        this.ormMappingsHierarchy = ormMappingsHierarchy;
    }
}
