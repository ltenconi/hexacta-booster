/**
 * 
 */
package com.hexacta.booster.codegeneration.template.exception;

/**
 * @author ltenconi
 * 
 */
public class TemplateDataNotFoundException extends Exception {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /**
     * 
     */
    public TemplateDataNotFoundException(final Exception e) {
        super(e);
    }

    public TemplateDataNotFoundException(final String msg) {
        super(msg);
    }

}
