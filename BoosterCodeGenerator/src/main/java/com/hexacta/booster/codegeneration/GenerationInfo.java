package com.hexacta.booster.codegeneration;

import java.util.ArrayList;
import java.util.List;

import com.hexacta.booster.codegeneration.warning.GenerationWarning;

/**
 * 
 */
public class GenerationInfo {

    private List<String> mappings;

    private List<String> daos;

    private List<String> dtos;

    private List<String> services;

    private List<String> views;

    private List<String> modifiedFiles;

    private List<String> existingEqualsFiles;

    private List<GenerationWarning> generationWarnings;

    public GenerationInfo() {

        mappings = new ArrayList<String>();
        daos = new ArrayList<String>();
        services = new ArrayList<String>();
        modifiedFiles = new ArrayList<String>();
        dtos = new ArrayList<String>();
        existingEqualsFiles = new ArrayList<String>();
        generationWarnings = new ArrayList<GenerationWarning>();
        views = new ArrayList<String>();

    }

    public void setMappings(final List<String> mappings) {
        this.mappings = mappings;
    }

    public List<String> getMappings() {
        return mappings;
    }

    public void setDaos(final List<String> daos) {
        this.daos = daos;
    }

    public List<String> getDaos() {
        return daos;
    }

    public void setServices(final List<String> services) {
        this.services = services;
    }

    public List<String> getServices() {
        return services;
    }

    public void setModifiedFiles(final List<String> modifiedFiles) {
        this.modifiedFiles = modifiedFiles;
    }

    public List<String> getModifiedFiles() {
        return modifiedFiles;
    }

    public void setViews(final List<String> views) {
        this.views = views;
    }

    public List<String> getViews() {
        return views;
    }

    public void addMapping(final String mappingPath) {
        mappings.add(mappingPath);
    }

    public void addDao(final String daoPath) {
        daos.add(daoPath);
    }

    public void addDto(final String dtoPath) {
        dtos.add(dtoPath);
    }

    public void addService(final String servicePath) {
        services.add(servicePath);
    }

    public void addModifiedFile(final String mofifiedFilePath) {
        modifiedFiles.add(mofifiedFilePath);
    }

    public void addViews(final String viewPath) {
        views.add(viewPath);
    }

    public void setDtos(final List<String> dtos) {
        this.dtos = dtos;
    }

    public List<String> getDtos() {
        return dtos;
    }

    public void setGenerationWarnings(final List<GenerationWarning> generationWarnings) {
        this.generationWarnings = generationWarnings;
    }

    public List<GenerationWarning> getGenerationWarnings() {
        return generationWarnings;
    }

    public void setExistingEqualsFiles(final List<String> existingEqualsFiles) {
        this.existingEqualsFiles = existingEqualsFiles;
    }

    public List<String> getExistingEqualsFiles() {
        return existingEqualsFiles;
    }

    public void addExistingEqualsFile(final String filePath) {
        existingEqualsFiles.add(filePath);
    }

}
