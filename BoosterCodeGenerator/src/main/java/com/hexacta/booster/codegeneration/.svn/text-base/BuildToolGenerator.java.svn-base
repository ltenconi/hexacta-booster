package com.hexacta.booster.codegeneration;

import com.hexacta.booster.codegeneration.configuration.CodeGeneratorConfiguration;
import com.hexacta.booster.project.configuration.Ant;
import com.hexacta.booster.project.configuration.Maven1;
import com.hexacta.booster.project.configuration.Maven2;
import com.hexacta.booster.project.configuration.NoBuildTool;
import com.hexacta.booster.project.configuration.ProjectConfigurationTool;

/**
 * 
 */
public class BuildToolGenerator implements BoosterGenerator {

    public BuildToolGenerator() {

    }

    public void generate(final ProjectConfigurationTool projectConfigurationTool,
            final CodeGeneratorConfiguration codeGeneratorConfiguration, final GenerationInfo generationInfo) {

        projectConfigurationTool.getBuildTool().generateCodeIn(this);

    }

    public void generateCodeForAnt(final Ant ant) {

    }

    public void generateCodeForMaven1(final Maven1 maven1) {

    }

    public void generateCodeForMaven2(@SuppressWarnings("unused") final Maven2 maven2) {

    }

    public void generateCodeForNoBuildTool(@SuppressWarnings("unused") final NoBuildTool noBuildTool) {

    }
}
