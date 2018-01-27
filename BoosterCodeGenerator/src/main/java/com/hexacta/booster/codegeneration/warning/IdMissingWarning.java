/**
 * 
 */
package com.hexacta.booster.codegeneration.warning;

import com.hexacta.booster.codegeneration.metamodel.Class;

/**
 * @author ltenconi
 * 
 */
public class IdMissingWarning extends ModelWarning {

    /**
     * 
     */
    public IdMissingWarning(final String message, final Class location) {
        super(message, location);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.hexacta.booster.warning.ModelWarning#isIdMissingWarning()
     */
    @Override
    public boolean isIdMissingWarning() {
        return true;
    }

}
