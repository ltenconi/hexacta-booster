package com.hexacta.booster.codegeneration.service.spring;

import junit.framework.TestCase;
import test.hexacta.booster.providers.CodeGeneratorConfigurationProvider;

import com.hexacta.booster.codegeneration.TextFile;
import com.hexacta.booster.codegeneration.configuration.CodeGeneratorConfiguration;

/**
 * 
 */
public class ServiceImplementationGeneratorTest extends TestCase {

    public void testGenerate() {

        CodeGeneratorConfigurationProvider codeGeneratorConfigurationProvider = new CodeGeneratorConfigurationProvider();
        CodeGeneratorConfiguration codeConfiguration = null;
        codeConfiguration = codeGeneratorConfigurationProvider.getCodeGeneratorConfiguration();

        ServiceImplementationGenerator serviceImplementationGenerator = new ServiceImplementationGenerator();
        try {
            TextFile generated = serviceImplementationGenerator.generate(codeConfiguration.getClassList().iterator()
                    .next(), codeConfiguration);
            assertFalse(generated.getText().contains("$"));
        } catch (Exception e) {
            fail();
        }

    }
}
