package com.hexacta.booster.codegeneration.service.spring;

import junit.framework.TestCase;
import test.hexacta.booster.providers.CodeGeneratorConfigurationProvider;

import com.hexacta.booster.codegeneration.TextFile;
import com.hexacta.booster.codegeneration.configuration.CodeGeneratorConfiguration;

/**
 * 
 */
public class ServiceInterfaceGeneratorTest extends TestCase {

    public void testGenerate() {

        CodeGeneratorConfigurationProvider codeGeneratorConfigurationProvider = new CodeGeneratorConfigurationProvider();
        CodeGeneratorConfiguration codeConfiguration = null;
        codeConfiguration = codeGeneratorConfigurationProvider.getCodeGeneratorConfiguration();

        ServiceInterfaceGenerator serviceInterfaceGenerator = new ServiceInterfaceGenerator();
        try {
            TextFile generated = serviceInterfaceGenerator.generate(codeConfiguration.getClassList().iterator().next(),
                    codeConfiguration);
            assertFalse(generated.getText().contains("$"));
        } catch (Exception e) {
            fail();
        }

    }

}
