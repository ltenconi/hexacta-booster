/**
 * 
 */
package com.hexacta.booster.codegeneration.warning;

/**
 * @author ltenconi
 * 
 */
public class Warning {

    String message;

    /**
     * 
     */
    public Warning(final String message) {
        this.message = message;
    }

    /**
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    public boolean isModelWarning() {
        return false;
    }

}
