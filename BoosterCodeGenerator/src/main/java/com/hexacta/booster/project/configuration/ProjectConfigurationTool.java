package com.hexacta.booster.project.configuration;

/**
 * 
 */
public class ProjectConfigurationTool {

    private BuildTool buildTool;

    private VersioningSystem versioningSystem;

    private ProjectType projectType;

    private OrmTool ormTool;

    private DtoTool dtoTool;

    private PersistenceLayerType persistenceLayerType;

    private Framework framework;
    
    private TestingTool testingTool;

    private ViewLayerFramework viewLayerFramework;

    public void setBuildTool(final BuildTool buildTool) {
        this.buildTool = buildTool;
    }

    public void setVersioningSystem(final VersioningSystem versioningSystem) {
        this.versioningSystem = versioningSystem;
    }

    public void setProjectType(final ProjectType projectType) {
        this.projectType = projectType;
    }

    public void setOrmTool(final OrmTool ormTool) {
        this.ormTool = ormTool;
    }

    public void setDtoTool(final DtoTool dtoTool) {
        this.dtoTool = dtoTool;
    }

    public void setPersistenceLayerType(final PersistenceLayerType persistenceLayerType) {
        this.persistenceLayerType = persistenceLayerType;
    }

    public void setFramework(final Framework framework) {
        this.framework = framework;
    }

   

    public ProjectConfigurationTool(final BuildTool buildTool, final VersioningSystem versioningSystem,
            final ProjectType projectType, final OrmTool ormTool, final DtoTool dtoTool,
            final PersistenceLayerType persistenceLayerType, final Framework framework, final TestingTool testingTool, ViewLayerFramework viewLayerFramework) {

        this.buildTool = buildTool;
        this.versioningSystem = versioningSystem;
        this.projectType = projectType;
        this.ormTool = ormTool;
        this.dtoTool = dtoTool;
        this.persistenceLayerType = persistenceLayerType;
        this.framework = framework;
        this.testingTool = testingTool;
        this.setViewLayerFramework(viewLayerFramework);
    }

    /*
     * public void processConfigurationIn(CodeGenerator codeGenerator,
     * CodeGeneratorConfiguration codeGeneratorConfiguration){
     * buildTool.processBuildTooIn(codeGenerator,codeGeneratorConfiguration); }
     */

    /**
     * 
     */
    public ProjectConfigurationTool() {

    }

    public BuildTool getBuildTool() {
        return buildTool;
    }

    public VersioningSystem getVersioningSystem() {
        return versioningSystem;
    }

    public ProjectType getProjectType() {
        return projectType;
    }

    public OrmTool getOrmTool() {
        return ormTool;
    }

    public DtoTool getDtoTool() {
        return dtoTool;
    }

    public PersistenceLayerType getPersistenceLayerType() {
        return persistenceLayerType;
    }

    public Framework getFramework() {
        return framework;
    }

    public void setTestingTool(final TestingTool testingTool) {
        this.testingTool = testingTool;
    }

    public TestingTool getTestingTool() {
        return testingTool;
    }

    public void setViewLayerFramework(ViewLayerFramework viewLayerFramework) {
        this.viewLayerFramework = viewLayerFramework;
    }

    public ViewLayerFramework getViewLayerFramework() {
        return viewLayerFramework;
    }

}
