/*
 * [legal notice here]
 */
package com.hexacta.booster.codegeneration.template.model;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * @author fmrodriguez
 * 
 */
public class MetaVariable {

    private Long id;

    private String name;

    private String meaning;

    private String defaultValue;

    /**
     * 
     */
    public MetaVariable() {
    }

    public MetaVariable(final String name) {
        this.name = name;
    }

    public MetaVariable(final String defaultValue, final String meaning, final String name) {
        super();
        this.defaultValue = defaultValue;
        this.meaning = meaning;
        this.name = name;
    }

    public MetaVariable(final String meaning, final String name) {
        super();
        this.meaning = meaning;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getMeaning() {
        return meaning;
    }

    public void setMeaning(final String meaning) {
        this.meaning = meaning;
    }

    public void setDefaultValue(final String defaultValue) {
        this.defaultValue = defaultValue;
    }

    public String getDefaultValue() {
        return defaultValue;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    /**
     * @see java.lang.Object#equals(Object)
     */
    @Override
    public boolean equals(final Object object) {
        if (!(object instanceof MetaVariable))
            return false;
        MetaVariable rhs = (MetaVariable) object;
        return new EqualsBuilder().append(name, rhs.name).isEquals();
    }

    /**
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        return new HashCodeBuilder(958978141, -683178891).append(name).toHashCode();
    }

}
