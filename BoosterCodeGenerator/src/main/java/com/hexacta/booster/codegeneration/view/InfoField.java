/*
 * [legal notice here]
 */
package com.hexacta.booster.codegeneration.view;

import com.hexacta.booster.codegeneration.metamodel.Type;

/**
 * This class extends the information about a field.
 * 
 * Created on 20/04/2009
 * 
 * @author vmartz
 * 
 */
public class InfoField {

    private String name;

    private Type type;

    private boolean collection;

    private boolean manyToOne;

    public InfoField() {

    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public Type getType() {
        return type;
    }

    public void setType(final Type type) {
        this.type = type;
    }

    public boolean isCollection() {
        return collection;
    }

    public void setCollection(final boolean collection) {
        this.collection = collection;
    }

    public boolean isManyToOne() {
        return manyToOne;
    }

    public void setManyToOne(final boolean manyToOne) {
        this.manyToOne = manyToOne;
    }

}
