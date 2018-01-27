package com.hexacta.booster.project.configuration;

/**
 * 
 */
public abstract class VersioningSystem {

    public boolean isSvn() {
        return false;
    }

    public boolean isCvs() {
        return false;
    }

    public boolean isTfsVS() {
        return false;
    }

    public boolean isNoVersioningSystem() {
        return false;
    }
}
