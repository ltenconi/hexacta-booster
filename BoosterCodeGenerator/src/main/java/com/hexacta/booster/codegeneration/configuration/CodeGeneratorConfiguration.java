package com.hexacta.booster.codegeneration.configuration;

import com.hexacta.booster.codegeneration.persistence.strategy.MappingHierarchyCuttingStrategy;

/**
 * 
 */
public class CodeGeneratorConfiguration {

    private ClassList classList;

    /** User model (list of classes). */
    private ClassList generateFor;

    /** Classes to generate code for. **/
    private JavaDistributionFile javaDistributionFile;

    /** Output file (extension name). */
    private HibernateParams hibernateParams;

    /** Configuration parameters for Hibernate. */
    private ClassIdMap classIdMap;

    /** Classes id mapped by class name. */
    private CollectionVarTypeMap collectionVarTypeMap;

    /** Collection Types mapped by (class Name + variable name). */
    private DirectoryInfo directoryInfo;

    private ClassList persistableMetaModel;

    private MappingHierarchyCuttingStrategy mappingHierarchyCuttingStrategy;

    public CodeGeneratorConfiguration(final ClassList classList, final JavaDistributionFile javaDistributionFile,
            final HibernateParams hibernateParams, final ClassIdMap classIdMap,
            final CollectionVarTypeMap collectionVarTypeMap, final DirectoryInfo directoryInfo,
            final ClassList generateFor, final ClassList persistableMetaModel,
            final MappingHierarchyCuttingStrategy mappingHierarchyCuttingStrategy) {
        this.classList = classList;
        this.javaDistributionFile = javaDistributionFile;
        this.hibernateParams = hibernateParams;
        this.classIdMap = classIdMap;
        this.collectionVarTypeMap = collectionVarTypeMap;
        this.directoryInfo = directoryInfo;
        this.generateFor = generateFor;
        this.persistableMetaModel = persistableMetaModel;
        this.mappingHierarchyCuttingStrategy = mappingHierarchyCuttingStrategy;

    }

    public ClassIdMap getClassIdMap() {
        return classIdMap;
    }

    public ClassList getGenerateCodeFor() {
        return generateFor;
    }

    public ClassList getClassList() {
        return classList;
    }

    public JavaDistributionFile getJavaDistributionFile() {
        return javaDistributionFile;
    }

    public HibernateParams getHibernateParams() {
        return hibernateParams;
    }

    public CollectionVarTypeMap getCollectionVarTypeMap() {
        return collectionVarTypeMap;
    }

    public void setDirectoryInfo(final DirectoryInfo directoryInfo) {
        this.directoryInfo = directoryInfo;
    }

    public DirectoryInfo getDirectoryInfo() {
        return directoryInfo;
    }

    public ClassList getPersistableMetaModel() {
        return persistableMetaModel;
    }

    public void setPersistableMetaModel(final ClassList persistableMetaModel) {
        this.persistableMetaModel = persistableMetaModel;
    }

    public void setMappingHierarchyCuttingStrategy(final MappingHierarchyCuttingStrategy mappingHierarchyCuttingStrategy) {
        this.mappingHierarchyCuttingStrategy = mappingHierarchyCuttingStrategy;
    }

    public MappingHierarchyCuttingStrategy getMappingHierarchyCuttingStrategy() {
        return mappingHierarchyCuttingStrategy;
    }

}
