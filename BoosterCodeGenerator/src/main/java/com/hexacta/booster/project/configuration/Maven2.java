package com.hexacta.booster.project.configuration;

import com.hexacta.booster.codegeneration.BuildToolGenerator;

/**
 * 
 */
public class Maven2 extends Maven {

    public Maven2() {

    }

    @Override
    public boolean isMaven2() {
        return true;
    }

    @Override
    public void generateCodeIn(final BuildToolGenerator buildToolGenerator) {
        buildToolGenerator.generateCodeForMaven2(this);

    }

}
