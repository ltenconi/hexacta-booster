package com.hexacta.booster.codegeneration;

/**
 * 
 */
public class BoosterCodeGeneratorException extends Exception {

    private static final long serialVersionUID = 1L;

    public BoosterCodeGeneratorException(final String msg) {
        super("Booster Code Generator: " + msg);
    }

}
