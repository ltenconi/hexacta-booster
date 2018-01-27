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
public class SpringAbstractServiceJUnit3TestGeneratorTest extends TestCase {

    public void testCreation() {

        SpringAbstractServiceJUnit3TestGenerator springAbstractServiceJUnit3TestGenerator = new SpringAbstractServiceJUnit3TestGenerator();
        assertNotNull(springAbstractServiceJUnit3TestGenerator);

    }

    public void testGenerate() {

        CodeGeneratorConfigurationProvider codeGeneratorConfigurationProvider = new CodeGeneratorConfigurationProvider();
        CodeGeneratorConfiguration codeGeneratorConfiguration = codeGeneratorConfigurationProvider
                .getCodeGeneratorConfiguration();

        SpringAbstractServiceJUnit3TestGenerator aSpringAbstractServiceJUnit3TestGenerator = new SpringAbstractServiceJUnit3TestGenerator();
        try {
            TextFile generated = aSpringAbstractServiceJUnit3TestGenerator.generate("com.proof.ar",
                    codeGeneratorConfiguration);
            assertFalse(generated.getText().contains("$"));
        } catch (Exception e) {
            fail();
        }
    }

}
