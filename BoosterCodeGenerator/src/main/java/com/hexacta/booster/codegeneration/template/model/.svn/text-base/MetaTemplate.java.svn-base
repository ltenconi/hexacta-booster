/**
 *
 */
package com.hexacta.booster.codegeneration.template.model;

import java.util.ArrayList;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * @author ltenconi
 * 
 */
public class MetaTemplate implements IMetaTemplate {

    private String id;

    private BoosterTemplate template;

    private TemplateData templateData;

    public MetaTemplate() {
    }

    /**
     * @param id
     * @param template
     * @param templateData
     */
    public MetaTemplate(final String id, final BoosterTemplate template, final TemplateData templateData) {
        super();
        this.id = id;
        this.template = template;
        this.templateData = templateData;
    }

    /**
     * @param templateId
     * @param boosterTemplate
     */
    public MetaTemplate(final String templateId, final BoosterTemplate boosterTemplate) {
        super();
        id = templateId;
        template = boosterTemplate;
        templateData = new TemplateData(new TemplateCategory("Unnamed Category"), "", Long.valueOf(0),
                new ArrayList<MetaVariable>(), new ArrayList<Tag>(), "Unnamed Template");
    }

    public String getId() {
        return id;
    }

    public BoosterTemplate getTemplate() {
        return template;
    }

    public void setTemplate(final BoosterTemplate template) {
        this.template = template;
    }

    public void updateTemplate(final BoosterTemplate boosterTemplate) {
        template = boosterTemplate;
    }

    public TemplateData getTemplateData() {
        return templateData;
    }

    /**
     * {@inheritDoc}
     */
    public void setId(final String id) {
        this.id = id;

    }

    /**
     * {@inheritDoc}
     */
    public void addHit() {
        templateData.addHit();

    }

    /**
     * @param templateData
     *            the templateData to set
     */
    public void setTemplateData(final TemplateData templateData) {
        this.templateData = templateData;
    }

    public void setTemplateName(final String name) {
        templateData.setTemplateName(name);
    }

    public String getTemplateName() {
        return templateData.getTemplateName();
    }

    /**
     * @see java.lang.Object#equals(Object)
     */
    @Override
    public boolean equals(final Object object) {
        if (!(object instanceof MetaTemplate))
            return false;
        MetaTemplate rhs = (MetaTemplate) object;
        return new EqualsBuilder().append(id, rhs.id).isEquals();
    }

    /**
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        return new HashCodeBuilder(487813135, -1614083995).append(id).toHashCode();
    }

}
