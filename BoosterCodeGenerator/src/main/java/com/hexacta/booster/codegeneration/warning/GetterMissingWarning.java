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
public class GetterMissingWarning extends AccessorMethodMissingWarning {

    /**
     * @param message
     * @param location
     * @param field
     */
    public GetterMissingWarning(final String message, final Class location, final Field field) {
        super(message, location, field);
    }

    /*
     * (non-Javadoc)
     * 
     * @seecom.hexacta.booster.warning.AccessorMethodMissingWarning#
     * isGetterMissingWarning()
     */
    @Override
    public boolean isGetterMissingWarning() {
        return true;
    }

}
