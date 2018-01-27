package com.hexacta.booster.project.configuration;

import com.hexacta.booster.codegeneration.BuildToolGenerator;

/**
 * 
 */
public class Maven1 extends Maven {

    public Maven1() {

    }

    @Override
    public boolean isMaven1() {
        return true;
    }

    @Override
    public void generateCodeIn(final BuildToolGenerator buildToolGenerator) {
        buildToolGenerator.generateCodeForMaven1(this);
    }
}
