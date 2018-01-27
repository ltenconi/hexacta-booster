/*
 * [legal notice here]
 */
package com.hexacta.booster.codegeneration.template.search;

import java.util.ArrayList;
import java.util.List;

import com.hexacta.booster.codegeneration.template.model.IMetaTemplate;
import com.hexacta.booster.codegeneration.template.model.Tag;

/**
 * @author fmrodriguez
 * 
 */
public class SearchCriteria {

    List<SearchCriterion> criteria;

    List<Tag> domainTags;

    /**
     * 
     */
    public SearchCriteria() {
        criteria = new ArrayList<SearchCriterion>();
        domainTags = new ArrayList<Tag>();
    }

    /**
     * @param domainTags
     *            the domainTags to set
     */
    public void setDomainTags(final List<Tag> domainTags) {
        this.domainTags = domainTags;
    }

    public void addSearhCriterion(final SearchCriterion searchCriterion) {
        criteria.add(searchCriterion);
    }

    public List<IMetaTemplate> result(final List<IMetaTemplate> metaTemplates) {

        if (criteria.size() == 0)
            return metaTemplates;
        else {
            List<IMetaTemplate> result = new ArrayList<IMetaTemplate>();
            for (IMetaTemplate metaTemplate : metaTemplates) {
                for (SearchCriterion criterion : criteria) {
                    if (onDomain(metaTemplate)) {
                        if (criterion.applyCriterion(metaTemplate)) {
                            result.add(metaTemplate);
                            break;
                        }
                    }
                }
            }
            return result;
        }
    }

    /**
     * @param metaTemplate
     * @return
     */
    private boolean onDomain(final IMetaTemplate metaTemplate) {
        if (domainTags.isEmpty())
            return true;
        else {
            for (Tag tag : metaTemplate.getTemplateData().getTags()) {
                if (domainTags.contains(tag))
                    return true;
            }
            return false;
        }
    }
}
