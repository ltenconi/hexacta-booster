package com.hexacta.booster.codegeneration.service.spring;

import junit.framework.TestCase;
import test.hexacta.booster.providers.CodeGeneratorConfigurationProvider;

import com.hexacta.booster.codegeneration.TextFile;
import com.hexacta.booster.codegeneration.configuration.CodeGeneratorConfiguration;

/**
 * 
 */
public class TransactionalTestGeneratorTest extends TestCase {

    public void testGenerate() {

        CodeGeneratorConfigurationProvider codeGeneratorConfigurationProvider = new CodeGeneratorConfigurationProvider();
        CodeGeneratorConfiguration codeConfiguration = null;
        codeConfiguration = codeGeneratorConfigurationProvider.getCodeGeneratorConfiguration();

        TransactionalTestGenerator transactionalTestGenerator = new TransactionalTestGenerator();
        try {
            TextFile generated = transactionalTestGenerator.generate(
                    codeConfiguration.getClassList().iterator().next(), codeConfiguration);
            assertFalse(generated.getText().contains("$"));
        } catch (Exception e) {
            fail();
        }

    }

}
