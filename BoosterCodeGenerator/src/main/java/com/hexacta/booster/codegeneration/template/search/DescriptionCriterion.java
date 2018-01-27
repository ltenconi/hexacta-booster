/*
 * [legal notice here]
 */
package com.hexacta.booster.codegeneration.template.search;

import com.hexacta.booster.codegeneration.template.model.IMetaTemplate;

/**
 * @author fmrodriguez
 * 
 */
public class DescriptionCriterion extends SearchCriterion {

    /**
     * 
     */
    public DescriptionCriterion(final String description) {
        criterion = description;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean applyCriterion(final IMetaTemplate metaTemplate) {
        return metaTemplate.getTemplateData().getDescription().contains(criterion);
    }

}
