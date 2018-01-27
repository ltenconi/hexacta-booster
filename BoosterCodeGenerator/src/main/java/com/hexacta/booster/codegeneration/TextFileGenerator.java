package com.hexacta.booster.codegeneration;

import com.hexacta.booster.codegeneration.configuration.CodeGeneratorConfiguration;
import com.hexacta.booster.codegeneration.metamodel.Class;

/**
 * 
 */
public interface TextFileGenerator {

    TextFile generate(Class entityClass, CodeGeneratorConfiguration codeGeneratorConfiguration) throws Exception;

    void addGenerationInfo(final TextFile textFile, final GenerationInfo generationInfo);

}
