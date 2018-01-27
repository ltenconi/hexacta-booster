package com.hexacta.booster.codegeneration.configuration;

import com.hexacta.booster.codegeneration.BoosterCodeGeneratorException;

/**
 * 
 */
public class NotSupportedClassException extends BoosterCodeGeneratorException {

    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;

    public NotSupportedClassException(final String aClassName) {
        super("Not Supported Class: " + aClassName);
    }
}
