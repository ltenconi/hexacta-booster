package com.hexacta.booster.codegeneration.metamodel;

import java.io.Serializable;

/**
 * 
 */
public class Method implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = -5513978612853614219L;

    private String name;

    private static final String IS = "is";

    private static final String GET = "get";

    public Method(final String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public boolean isGetter() {
        if (name.substring(0, 3).equalsIgnoreCase(GET) || name.substring(0, 2).equalsIgnoreCase(IS))
            return true;
        return false;
    }
}
