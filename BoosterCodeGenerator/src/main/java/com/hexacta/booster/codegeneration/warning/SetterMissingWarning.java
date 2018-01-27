/**
 * 
 */
package com.hexacta.booster.codegeneration.warning;

import com.hexacta.booster.codegeneration.metamodel.Class;
import com.hexacta.booster.codegeneration.metamodel.Field;

/**
 * @author ltenconi
 * 
 */
public class SetterMissingWarning extends AccessorMethodMissingWarning {

    /**
     * @param message
     * @param location
     * @param field
     */
    public SetterMissingWarning(final String message, final Class location, final Field field) {
        super(message, location, field);
    }

    /*
     * (non-Javadoc)
     * 
     * @seecom.hexacta.booster.warning.AccessorMethodMissingWarning#
     * isSetterMissingWarning()
     */
    @Override
    public boolean isSetterMissingWarning() {
        return true;
    }
}
