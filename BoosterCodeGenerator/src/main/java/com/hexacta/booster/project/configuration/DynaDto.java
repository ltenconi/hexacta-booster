package com.hexacta.booster.project.configuration;

import com.hexacta.booster.codegeneration.GenerationInfo;
import com.hexacta.booster.codegeneration.configuration.CodeGeneratorConfiguration;
import com.hexacta.booster.codegeneration.service.DtoGenerator;
import com.hexacta.booster.codegeneration.service.ServiceGenerator;

/**
 * 
 */
public class DynaDto extends DtoTool {

    public DynaDto() {
    }

    @Override
    public boolean isDynaDto() {
        return true;
    }

    @Override
    public void generateIn(final DtoGenerator dtoGenerator,
            final CodeGeneratorConfiguration codeGeneratorConfiguration, final GenerationInfo generationInfo) {
        dtoGenerator.generateFor(this, codeGeneratorConfiguration, generationInfo);
    }

    @Override
    public void generateCodeForServiceIn(final ServiceGenerator serviceGenerator, final Spring spring,
            final CodeGeneratorConfiguration codeGeneratorConfiguration, final GenerationInfo generationInfo) {

        serviceGenerator.generateFor(this, spring, codeGeneratorConfiguration, generationInfo);

    }

}
