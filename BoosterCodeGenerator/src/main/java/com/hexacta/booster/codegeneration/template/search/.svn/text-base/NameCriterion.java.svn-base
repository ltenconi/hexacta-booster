/*
 * [legal notice here]
 */
package com.hexacta.booster.codegeneration.template.search;

import com.hexacta.booster.codegeneration.template.model.IMetaTemplate;

/**
 * @author fmrodriguez
 * 
 */
public class NameCriterion extends SearchCriterion {

    /**
     * 
     */
    public NameCriterion(final String name) {
        criterion = name;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean applyCriterion(final IMetaTemplate metaTemplate) {
        return metaTemplate.getTemplateData().getTemplateName().equalsIgnoreCase(criterion);
    }
}
