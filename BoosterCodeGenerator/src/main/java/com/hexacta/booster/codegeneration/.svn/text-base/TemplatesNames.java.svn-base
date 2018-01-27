package com.hexacta.booster.codegeneration;

import java.util.ArrayList;
import java.util.List;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import java.util.Set;

/**
 * @author jmarquez
 * 
 */
public abstract class TemplatesNames {

    private static final String BUNDLE_NAME = "templates_names";

    private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(BUNDLE_NAME);

    private TemplatesNames() {

    }

    public static String getName(final String key) {
        try {
            return RESOURCE_BUNDLE.getString(key);
        } catch (MissingResourceException e) {
            return '!' + key + '!';
        }
    }

    public static List<String> getAllNames() {
        Set<String> keys = RESOURCE_BUNDLE.keySet();

        List<String> templateNames = new ArrayList<String>();

        for (String key : keys) {
            templateNames.add(getName(key));
        }

        return templateNames;

    }
}
