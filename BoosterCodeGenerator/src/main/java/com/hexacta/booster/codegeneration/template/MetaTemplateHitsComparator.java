/*
 * [legal notice here]
 */
package com.hexacta.booster.codegeneration.template;

import java.util.Comparator;

import com.hexacta.booster.codegeneration.template.model.IMetaTemplate;

/**
 * @author fmrodriguez
 * 
 */
public class MetaTemplateHitsComparator implements Comparator<IMetaTemplate> {

    /**
     * {@inheritDoc}
     */
    public int compare(final IMetaTemplate o1, final IMetaTemplate o2) {
        if (o1.getTemplateData().getHits() > o2.getTemplateData().getHits())
            return -1;
        else if (o1.getTemplateData().getHits() == o2.getTemplateData().getHits())
            return 0;
        else
            return 1;
    }

}
