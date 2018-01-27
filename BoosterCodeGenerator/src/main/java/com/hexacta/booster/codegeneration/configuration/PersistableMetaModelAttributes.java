package com.hexacta.booster.codegeneration.configuration;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * 
 */
public class PersistableMetaModelAttributes {

    private Map<String, AttributesSubset> map;

    public PersistableMetaModelAttributes() {
        map = new HashMap<String, AttributesSubset>();
    }

    public void add(final String className, final AttributesSubset subset) {
        map.put(className, subset);
    }

    public AttributesSubset getAttributesSubset(final String className) {
        return map.get(className);
    }

    public boolean contains(final String className) {
        return map.containsKey(className);
    }

    public Set<String> getClasses() {
        return map.keySet();
    }

}
