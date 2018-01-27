/*
 * [legal notice here]
 */
package com.hexacta.booster.codegeneration.persistence.layer.hibernate;

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
public class SpringAbstractDAOJUnit3TestGeneratorTest extends TestCase {

    public void testCreation() {

        SpringAbstractDAOJUnit3TestGenerator aSpringAbstractDAOJUnit3TestGenerator = new SpringAbstractDAOJUnit3TestGenerator();
        assertNotNull(aSpringAbstractDAOJUnit3TestGenerator);

    }

    public void testGenerate() {

        CodeGeneratorConfigurationProvider codeGeneratorConfigurationProvider = new CodeGeneratorConfigurationProvider();
        CodeGeneratorConfiguration codeGeneratorConfiguration = codeGeneratorConfigurationProvider
                .getCodeGeneratorConfiguration();

        SpringAbstractDAOJUnit3TestGenerator aSpringAbstractDAOJUnit3TestGenerator = new SpringAbstractDAOJUnit3TestGenerator();
        try {
            TextFile generated = aSpringAbstractDAOJUnit3TestGenerator.generate("com.proof.ar",
                    codeGeneratorConfiguration);
            assertFalse(generated.getText().contains("$"));
        } catch (Exception e) {
            fail();
        }

    }

}
