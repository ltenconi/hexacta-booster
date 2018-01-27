package com.hexacta.booster.codegeneration.configuration;

import java.util.HashMap;

import com.hexacta.booster.codegeneration.persistence.ormmodel.CollectionType;

/**
 * 
 */
public class CollectionVarTypeMap {

    private HashMap<String, CollectionType> varType; // (class name + variable

    // name) --> collection
    // type

    public CollectionVarTypeMap() {
        varType = new HashMap<String, CollectionType>();
    }

    public void putVarType(final String key, final CollectionType collectionType) {
        varType.put(key, collectionType);
    }

    public CollectionType getCollectionVarType(final String className, final String varName) {
        return varType.get(className + varName);
    }

    public boolean hasCollectionVarType(final String className, final String varName) {
        return varType.containsKey(className + varName);
    }
}
