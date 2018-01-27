/**
 * 
 */
package com.hexacta.booster.codegeneration.warning;

import com.hexacta.booster.codegeneration.metamodel.Class;

/**
 * @author ltenconi
 * 
 */
public class ModelWarning extends Warning {

    Class location;

    /**
     * 
     */
    public ModelWarning(final String message, final Class location) {
        super(message);
        this.location = location;
    }

    /**
     * @return the warningClass
     */
    public Class getLocation() {
        return location;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.hexacta.booster.warning.Warning#isModelWarning()
     */
    @Override
    public boolean isModelWarning() {
        return true;
    }

    public boolean isAccessorMethodMissingWarning() {
        return false;
    }

    public boolean isEmptyConstructorMissingWarning() {
        return false;
    }

    public boolean isIdMissingWarning() {
        return false;
    }

    public boolean isEqualsMethodMissingWarning() {
        return false;
    }

    public boolean isHashCodeMethodMissingWarning() {
        return false;
    }

}
