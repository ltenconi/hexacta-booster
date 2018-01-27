/*
 * [legal notice here]
 */
package com.hexacta.booster.codegeneration.template.model;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author fmrodriguez
 * 
 */
public class TemplateData {

    private Long id;

    private String templateName;

    private Long hits;

    private TemplateCategory category;

    private List<MetaVariable> metaVariables;

    private String description;

    private List<Tag> tags;

    /**
     *
     */
    public TemplateData() {
        templateName = "";
        hits = Long.valueOf(0);
        category = new TemplateCategory("");
        metaVariables = new ArrayList<MetaVariable>();
        description = "";
        tags = new ArrayList<Tag>();
    }

    /**
     * @param hits
     *            the hits to set
     */
    public void setHits(final Long hits) {
        this.hits = hits;
    }

    /**
     * @param category
     * @param metaVariables
     * @param templateName
     */
    public TemplateData(final TemplateCategory category, final List<MetaVariable> metaVariables,
            final String templateName, final String description) {
        super();
        this.category = category;
        this.metaVariables = metaVariables;
        this.templateName = templateName;
        hits = Long.valueOf(1);
        this.description = description;
    }

    public TemplateData(final TemplateCategory category, final String description, final Long hits,
            final List<MetaVariable> metaVariables, final List<Tag> tags, final String templateName) {
        super();
        this.category = category;
        this.description = description;
        this.hits = hits;
        this.metaVariables = metaVariables;
        this.tags = tags;
        this.templateName = templateName;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description
     *            the description to set
     */
    public void setDescription(final String description) {
        this.description = description;
    }

    /**
     * @return the hits
     */
    public Long getHits() {
        return hits;
    }

    /**
     * @param hits
     *            the hits to set
     */
    public void addHit() {
        hits++;
    }

    /**
     * @return the templateName
     */
    public String getTemplateName() {
        return templateName;
    }

    /**
     * @param templateName
     *            the templateName to set
     */
    public void setTemplateName(final String templateName) {
        this.templateName = templateName;
    }

    /**
     * @return the category
     */
    public TemplateCategory getCategory() {
        return category;
    }

    /**
     * @param category
     *            the category to set
     */
    public void setCategory(final TemplateCategory category) {
        this.category = category;
    }

    /**
     * @return the metaVariables
     */
    public List<MetaVariable> getMetaVariables() {
        return metaVariables;
    }

    /**
     * @param metaVariables
     *            the metaVariables to set
     */
    public void setMetaVariables(final List<MetaVariable> metaVariables) {
        this.metaVariables = metaVariables;
    }

    public void setTags(final List<Tag> tags) {
        this.tags = tags;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

}
