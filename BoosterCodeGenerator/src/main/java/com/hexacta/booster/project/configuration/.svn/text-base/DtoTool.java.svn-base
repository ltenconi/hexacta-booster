package com.hexacta.booster.project.configuration;

import com.hexacta.booster.codegeneration.GenerationInfo;
import com.hexacta.booster.codegeneration.configuration.CodeGeneratorConfiguration;
import com.hexacta.booster.codegeneration.service.DtoGenerator;
import com.hexacta.booster.codegeneration.service.ServiceGenerator;

/**
 * 
 */
public abstract class DtoTool {

    public boolean isNoDtoTool() {
        return false;
    }

    public boolean isDynaDto() {
        return false;
    }

    public abstract void generateIn(DtoGenerator dtoGenerator, CodeGeneratorConfiguration codeGeneratorConfiguration,
            GenerationInfo generationInfo);

    public abstract void generateCodeForServiceIn(ServiceGenerator serviceGenerator, Spring spring,
            CodeGeneratorConfiguration codeGeneratorConfiguration, GenerationInfo generationInfo);

}
