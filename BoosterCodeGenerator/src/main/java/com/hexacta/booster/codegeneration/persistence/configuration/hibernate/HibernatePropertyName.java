package com.hexacta.booster.codegeneration.persistence.configuration.hibernate;

/**
 * This class keeps the name and the content of an hibenate option.
 */

public class HibernatePropertyName {

    private String name;

    private String content;

    public HibernatePropertyName(final String aName, final String aContent) {
        name = aName;
        content = aContent;
    }

    public String getContent() {
        return content;
    }

    public String getName() {
        return name;
    }

    @Override
    public int hashCode() {

        return Integer.parseInt(name + content);
    }

    @Override
    public boolean equals(final Object anObject) {
        if (anObject.getClass().equals(HibernatePropertyName.class)) {
            HibernatePropertyName property = (HibernatePropertyName) anObject;

            if (name.equals(property.getName()) && content.equals(property.getContent()))
                return true;
        }
        return false;
    }
}
