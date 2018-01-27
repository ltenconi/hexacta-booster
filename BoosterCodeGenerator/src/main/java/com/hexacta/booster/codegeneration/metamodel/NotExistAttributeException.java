/*
 * [legal notice here]
 */
package com.hexacta.booster.codegeneration.metamodel;

/**
 * 
 * 
 * Created on 06/02/2009
 * 
 * @author fmrodriguez
 * 
 */
public class NotExistAttributeException extends Exception {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private String className;

    private String attribute;

    /**
     * 
     */
    public NotExistAttributeException(final String className, final String attribute) {
        super();
        this.className = className;
        this.attribute = attribute;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getMessage() {
        return attribute + " on " + className;
    }
}
