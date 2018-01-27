package com.hexacta.booster.project.configuration;

import com.hexacta.booster.codegeneration.BuildToolGenerator;

/**
 * 
 */
public class NoBuildTool extends BuildTool {

    public NoBuildTool() {

    }

    @Override
    public boolean isNoBuildTool() {
        return true;
    }

    @Override
    public void generateCodeIn(final BuildToolGenerator buildToolGenerator) {
        buildToolGenerator.generateCodeForNoBuildTool(this);
    }
}
