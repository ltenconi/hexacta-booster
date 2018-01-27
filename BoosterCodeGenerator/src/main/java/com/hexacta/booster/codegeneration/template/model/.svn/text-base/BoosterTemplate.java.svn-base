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
public class BoosterTemplate {

    private Long id;

    private String text;

    public BoosterTemplate() {
        // TODO Auto-generated constructor stub
    }

    /**
     * @param template
     */
    public BoosterTemplate(final String text) {
        super();
        this.text = text;
    }

    public void setText(final String template) {
        text = template;
    }

    public String getText() {
        return text;
    }

    /**
     * @see java.lang.Object#equals(Object)
     */
    @Override
    public boolean equals(final Object object) {
        if (!(object instanceof BoosterTemplate))
            return false;
        BoosterTemplate rhs = (BoosterTemplate) object;
        return new EqualsBuilder().append(text, rhs.text).isEquals();
    }

    /**
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        return new HashCodeBuilder(-1706511287, 446571099).appendSuper(super.hashCode()).append(text).toHashCode();
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

}
