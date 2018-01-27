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
public class ContentCriterion extends SearchCriterion {

    /**
     * 
     */
    public ContentCriterion(final String content) {
        criterion = content;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean applyCriterion(final IMetaTemplate metaTemplate) {
        return metaTemplate.getTemplate().getText().contains(criterion);
    }

}
