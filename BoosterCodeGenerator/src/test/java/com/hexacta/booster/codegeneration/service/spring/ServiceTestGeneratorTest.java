package com.hexacta.booster.codegeneration.service.spring;

import junit.framework.TestCase;
import test.hexacta.booster.providers.CodeGeneratorConfigurationProvider;

import com.hexacta.booster.codegeneration.TextFile;
import com.hexacta.booster.codegeneration.configuration.CodeGeneratorConfiguration;

/**
 * 
 */
public class ServiceTestGeneratorTest extends TestCase {

    public void testGenerate() {

        CodeGeneratorConfigurationProvider codeGeneratorConfigurationProvider = new CodeGeneratorConfigurationProvider();
        CodeGeneratorConfiguration codeGeneratorConfiguration = null;
        codeGeneratorConfiguration = codeGeneratorConfigurationProvider.getCodeGeneratorConfiguration();

        SpringServiceTestGenerator serviceTestGenerator = new SpringServiceTestGenerator();
        try {

            TextFile generated = serviceTestGenerator.generate(codeGeneratorConfiguration.getClassList().iterator()
                    .next(), codeGeneratorConfiguration);
            assertFalse(generated.getText().contains("$"));

        } catch (Exception e) {
            fail();
        }

    }

}
