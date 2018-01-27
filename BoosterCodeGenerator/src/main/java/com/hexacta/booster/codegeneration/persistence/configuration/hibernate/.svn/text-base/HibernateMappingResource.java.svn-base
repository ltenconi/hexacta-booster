package com.hexacta.booster.codegeneration.persistence.configuration.hibernate;

/**
 * This class keeps the string of one mapping resource(name of a class).
 */

public class HibernateMappingResource {

    private String resource;

    public HibernateMappingResource(final String aResource) {
        resource = aResource;
    }

    public String getResource() {
        return resource;
    }

    @Override
    public int hashCode() {

        return Integer.parseInt(resource);
    }

    @Override
    public boolean equals(final Object anObject) {
        if (anObject.getClass().equals(HibernateMappingResource.class)) {
            HibernateMappingResource hibernateMappingResource = (HibernateMappingResource) anObject;

            if (resource.equals(hibernateMappingResource.getResource()))
                return true;
        }
        return false;
    }

}
