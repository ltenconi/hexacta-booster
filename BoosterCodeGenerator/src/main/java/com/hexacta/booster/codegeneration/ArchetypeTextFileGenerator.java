/*
 * [legal notice here]
 */
package com.hexacta.booster.codegeneration;

import com.hexacta.booster.codegeneration.configuration.CodeGeneratorConfiguration;

/**
 */
public interface ArchetypeTextFileGenerator {

    TextFile generate(final String groupId, final CodeGeneratorConfiguration codeGeneratorConfiguration)
            throws Exception;

    /**
     * @param textFile
     * @param generationInfo
     */
    void addGenerationInfo(TextFile textFile, GenerationInfo generationInfo);

}
