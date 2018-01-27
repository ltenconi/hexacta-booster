package com.hexacta.booster.codegeneration.configuration;

import java.util.HashSet;
import java.util.Set;

/**
 * 
 */
public class AttributesSubset {

    private Set<String> subset;

    public AttributesSubset() {
        subset = new HashSet<String>();
    }

    public AttributesSubset(final Set<String> subset) {
        this.subset = subset;
    }

    public boolean contains(final String attributeName) {
        return subset.contains(attributeName);
    }

    public void add(final String attributeName) {
        subset.add(attributeName);
    }

    public int size() {
        return subset.size();
    }

    public Set<String> getSubSet() {
        return subset;
    }

}
