/**
 * 
 */
package com.hexacta.booster.codegeneration.template.exception;

import org.apache.velocity.exception.ParseErrorException;

/**
 * @author ltenconi
 * 
 */
public class TemplateParseErrorException extends Exception {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /**
     * @param e
     */
    public TemplateParseErrorException(final ParseErrorException e) {
        super(e);
    }

}
