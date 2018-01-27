package com.hexacta.booster.project.configuration;

import com.hexacta.booster.codegeneration.BuildToolGenerator;

/**
 * 
 */
public class Ant extends BuildTool {

    public Ant() {

    }

    @Override
    public boolean isAnt() {
        return true;
    }

    @Override
    public void generateCodeIn(final BuildToolGenerator buildToolGenerator) {
        buildToolGenerator.generateCodeForAnt(this);
    }

}
