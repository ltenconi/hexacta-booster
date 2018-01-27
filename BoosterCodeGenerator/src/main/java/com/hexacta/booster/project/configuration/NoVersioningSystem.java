package com.hexacta.booster.project.configuration;

/**
 * 
 */
public class NoVersioningSystem extends VersioningSystem {

    public NoVersioningSystem() {

    }

    @Override
    public boolean isNoVersioningSystem() {
        return true;
    }

}
