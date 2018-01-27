/*
 * [legal notice here]
 */
package com.hexacta.booster.codegeneration.template.model;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * 
 * @author fmrodriguez
 * 
 */
public class TemplateCategory {

    private Long id;

    private String name;

    /**
     *
     */
    public TemplateCategory() {
    }

    /**
     * @param name
     */
    public TemplateCategory(final String name) {
        this.name = name;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name
     *            the name to set
     */
    public void setName(final String name) {
        this.name = name;
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
        if (!(object instanceof TemplateCategory))
            return false;
        TemplateCategory rhs = (TemplateCategory) object;
        return new EqualsBuilder().append(name, rhs.name).isEquals();
    }

    /**
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        return new HashCodeBuilder(-1776103565, -410326749).append(name).toHashCode();
    }

}
