/**
 * 
 */
package com.hexacta.booster.codegeneration.warning;

import com.hexacta.booster.codegeneration.metamodel.Class;

/**
 * @author ltenconi
 * 
 */
public class EqualsMethodMissingWarning extends ModelWarning {

    /**
     * 
     */
    public EqualsMethodMissingWarning(final String message, final Class location) {
        super(message, location);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.hexacta.booster.util.warning.ModelWarning#isEqualsMissingWarning()
     */
    @Override
    public boolean isEqualsMethodMissingWarning() {
        return true;
    }
}
