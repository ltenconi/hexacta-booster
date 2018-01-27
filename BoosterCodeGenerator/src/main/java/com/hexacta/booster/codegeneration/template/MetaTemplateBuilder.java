/*
 * [legal notice here]
 */
package com.hexacta.booster.codegeneration.template;

import java.util.ArrayList;
import java.util.List;

import com.hexacta.booster.codegeneration.template.model.BoosterTemplate;
import com.hexacta.booster.codegeneration.template.model.IMetaTemplate;
import com.hexacta.booster.codegeneration.template.model.MetaTemplate;
import com.hexacta.booster.codegeneration.template.model.MetaVariable;
import com.hexacta.booster.codegeneration.template.model.Tag;
import com.hexacta.booster.codegeneration.template.model.TemplateCategory;
import com.hexacta.booster.codegeneration.template.model.TemplateData;

/**
 * 
 */
public final class MetaTemplateBuilder {

    /**
     * 
     */
    private MetaTemplateBuilder() {
    }

    /**
     * @param metaTemplate
     * @return
     */
    public static MetaTemplate build(final IMetaTemplate metaTemplate) {

        MetaTemplate mTemplate = new MetaTemplate();

        mTemplate.setId(buildTemplateId(metaTemplate));
        mTemplate.setTemplate(buildBoosterTemplate(metaTemplate));
        mTemplate.setTemplateData(buildTemplateData(metaTemplate));

        return mTemplate;
    }

    private static String buildTemplateId(final IMetaTemplate metaTemplate) {
        return metaTemplate.getId();
    }

    private static TemplateData buildTemplateData(final IMetaTemplate metaTemplate) {
        TemplateData templateData = new TemplateData();

        templateData.setCategory(buildTemplateCategory(metaTemplate));
        templateData.setDescription(buildDescription(metaTemplate));
        templateData.setHits(buildHits(metaTemplate));
        templateData.setId(buildDataId(metaTemplate));
        templateData.setMetaVariables(buildMetaVariables(metaTemplate));
        templateData.setTags(buildTags(metaTemplate));
        templateData.setTemplateName(buildTemplateName(metaTemplate));
        return templateData;
    }

    private static String buildTemplateName(final IMetaTemplate metaTemplate) {
        return new String(metaTemplate.getTemplateName());
    }

    private static List<Tag> buildTags(final IMetaTemplate metaTemplate) {
        List<Tag> metaTemplateTags = metaTemplate.getTemplateData().getTags();
        List<Tag> tags = new ArrayList<Tag>();

        for (Tag tag : metaTemplateTags) {
            Tag e = new Tag();
            e.setId(tag.getId());
            e.setName(tag.getName());
            tags.add(e);
        }
        return tags;
    }

    private static List<MetaVariable> buildMetaVariables(final IMetaTemplate metaTemplate) {
        List<MetaVariable> metaVariables = new ArrayList<MetaVariable>();
        List<MetaVariable> metaTemplateVariables = metaTemplate.getTemplateData().getMetaVariables();

        for (MetaVariable metaVariable : metaTemplateVariables) {
            MetaVariable variable = new MetaVariable();
            variable.setDefaultValue(metaVariable.getDefaultValue());
            variable.setId(metaVariable.getId());
            variable.setMeaning(metaVariable.getMeaning());
            variable.setName(metaVariable.getName());
            metaVariables.add(variable);
        }
        return metaVariables;
    }

    private static Long buildDataId(final IMetaTemplate metaTemplate) {
        Long id = metaTemplate.getTemplateData().getId();
        return id;
    }

    private static Long buildHits(final IMetaTemplate metaTemplate) {
        Long hits = metaTemplate.getTemplateData().getHits();
        return hits;
    }

    private static String buildDescription(final IMetaTemplate metaTemplate) {
        String description = metaTemplate.getTemplateData().getDescription();
        return description;
    }

    private static TemplateCategory buildTemplateCategory(final IMetaTemplate metaTemplate) {
        TemplateCategory templateCategory = new TemplateCategory();
        Long id = metaTemplate.getTemplateData().getCategory().getId();
        templateCategory.setId(id);
        templateCategory.setName(metaTemplate.getTemplateData().getCategory().getName());
        return templateCategory;
    }

    private static BoosterTemplate buildBoosterTemplate(final IMetaTemplate metaTemplate) {
        BoosterTemplate boosterTemplate = new BoosterTemplate();
        Long id = metaTemplate.getTemplate().getId();
        boosterTemplate.setId(id);
        boosterTemplate.setText(metaTemplate.getTemplate().getText());
        return boosterTemplate;
    }

}
