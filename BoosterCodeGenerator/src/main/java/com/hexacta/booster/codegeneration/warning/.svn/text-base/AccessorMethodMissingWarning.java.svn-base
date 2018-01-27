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
public abstract class AccessorMethodMissingWarning extends ModelWarning {

    Field field;

    /**
     * 
     */
    public AccessorMethodMissingWarning(final String message, final Class location, final Field field) {
        super(message, location);
        this.field = field;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.hexacta.booster.warning.ModelWarning#isAccessorMethodMissingWarning()
     */
    @Override
    public boolean isAccessorMethodMissingWarning() {
        return true;
    }

    public boolean isSetterMissingWarning() {
        return false;
    }

    public boolean isGetterMissingWarning() {
        return false;
    }

    /**
     * @return the field
     */
    public Field getField() {
        return field;
    }
}
