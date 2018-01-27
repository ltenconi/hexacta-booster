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
public class Tag {

    private Long id;

    private String name;

    /**
     * 
     */
    public Tag() {
    }

    public Tag(final String name) {
        super();
        this.name = name;
    }

    /**
     * @param name
     *            the name to set
     */
    public void setName(final String name) {
        this.name = name;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        return new HashCodeBuilder(1279260875, -1247833269).append(name).toHashCode();
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
        if (!(object instanceof Tag))
            return false;
        Tag rhs = (Tag) object;
        return new EqualsBuilder().append(name, rhs.name).isEquals();
    }

}
