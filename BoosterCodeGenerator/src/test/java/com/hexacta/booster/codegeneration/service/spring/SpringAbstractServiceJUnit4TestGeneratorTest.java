/*
 * [legal notice here]
 */
package com.hexacta.booster.codegeneration.service.spring;

import junit.framework.TestCase;
import test.hexacta.booster.providers.CodeGeneratorConfigurationProvider;

import com.hexacta.booster.codegeneration.TextFile;
import com.hexacta.booster.codegeneration.configuration.CodeGeneratorConfiguration;

/**
 * This test checks that after a template replacing the generated textFile
 * doesn't cointain any "$".
 * 
 * Created on 04/03/2009
 * 
 * @author vmartz
 * 
 */
public class SpringAbstractServiceJUnit4TestGeneratorTest extends TestCase {

    public void testCreation() {

        SpringAbstractServiceJUnit4TestGenerator springAbstractServiceJUnit4TestGenerator = new SpringAbstractServiceJUnit4TestGenerator();
        assertNotNull(springAbstractServiceJUnit4TestGenerator);

    }

    public void testGenerate() {

        CodeGeneratorConfigurationProvider codeGeneratorConfigurationProvider = new CodeGeneratorConfigurationProvider();
        CodeGeneratorConfiguration codeGeneratorConfiguration = codeGeneratorConfigurationProvider
                .getCodeGeneratorConfiguration();

        SpringAbstractServiceJUnit4TestGenerator aSpringAbstractServiceJUnit4TestGenerator = new SpringAbstractServiceJUnit4TestGenerator();
        try {
            TextFile generated = aSpringAbstractServiceJUnit4TestGenerator.generate("com.proof.ar",
                    codeGeneratorConfiguration);
            assertFalse(generated.getText().contains("$"));
        } catch (Exception e) {
            fail();
        }
    }

}
