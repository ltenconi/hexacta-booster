/*
 * [legal notice here]
 */
package com.hexacta.booster.codegeneration.template.search;

import com.hexacta.booster.codegeneration.template.model.IMetaTemplate;

/**
 * 
 * @author fmrodriguez
 * 
 */
public class CategoryCriterion extends SearchCriterion {

    /**
     * 
     */
    public CategoryCriterion(final String category) {
        criterion = category;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean applyCriterion(final IMetaTemplate metaTemplate) {
        return metaTemplate.getTemplateData().getCategory().getName().equalsIgnoreCase(criterion);
    }

}
