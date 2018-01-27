package com.hexacta.booster.codegeneration.configuration;

/**
 * 
 */
public class DirectoryInfo {

    private String modelPath;

    private String daosPath;

    private String testDaosPath;

    private String mappingsPath;

    private String daoContextFullPath;

    private String serviceContextFullPath;

    private String serviceTestContextFullPath;

    private String testResourcesPath;

    private String modelPackagePath;

    private String dtosPath;

    private String servicePath;

    private String testServicePath;

    private String viewPath;

    private String viewTestPath;

   
    public DirectoryInfo() {

    }

    public void setModelPath(final String modelPath) {
        this.modelPath = modelPath;
    }

    public String getModelPath() {
        return modelPath;
    }

    public void setDaosPath(final String daosPath) {
        this.daosPath = daosPath;
    }

    public String getDaosPath() {
        return daosPath;
    }

    public void setTestDaosPath(final String testDaosPath) {
        this.testDaosPath = testDaosPath;
    }

    public String getTestDaosPath() {
        return testDaosPath;
    }

    public void setMappingsPath(final String mappingsPath) {
        this.mappingsPath = mappingsPath;
    }

    public String getMappingsPath() {
        return mappingsPath;
    }

    public void setTestResourcesPath(final String testResourcesPath) {
        this.testResourcesPath = testResourcesPath;
    }

    public String getTestResourcesPath() {
        return testResourcesPath;
    }

    public void setModelPackagePath(final String modelPackageDir) {
        modelPackagePath = modelPackageDir;
    }

    public String getModelPackagePath() {
        return modelPackagePath;
    }

    public void setDtosPath(final String dtosPath) {
        this.dtosPath = dtosPath;
    }

    public String getDtosPath() {
        return dtosPath;
    }

    public void setServicePath(final String servicePath) {
        this.servicePath = servicePath;
    }

    public String getServicePath() {
        return servicePath;
    }

    public void setTestServicePath(final String testServicePath) {
        this.testServicePath = testServicePath;
    }

    public String getTestServicePath() {
        return testServicePath;
    }

    public void setDaoContextFullPath(final String daoContextFullPath) {
        this.daoContextFullPath = daoContextFullPath;
    }

    public String getDaoContextFullPath() {
        return daoContextFullPath;
    }

    public void setServiceContextFullPath(final String serviceContextFullPath) {
        this.serviceContextFullPath = serviceContextFullPath;
    }

    public String getServiceContextFullPath() {
        return serviceContextFullPath;
    }

    public void setServiceTestContextFullPath(final String serviceTestContextFullPath) {
        this.serviceTestContextFullPath = serviceTestContextFullPath;
    }

    public String getServiceTestContextFullPath() {
        return serviceTestContextFullPath;
    }

    public void setViewPath(final String viewPath) {
        this.viewPath = viewPath;
    }

    public String getViewPath() {
        return viewPath;
    }

    public String getViewTestPath() {
        return viewTestPath;
    }

    public void setViewTestPath(String viewTestPath) {
        this.viewTestPath = viewTestPath;
    }


}
