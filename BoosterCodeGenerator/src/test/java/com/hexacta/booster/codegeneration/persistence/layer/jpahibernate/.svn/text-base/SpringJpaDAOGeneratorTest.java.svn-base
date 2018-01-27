/**
 * 
 */
package com.hexacta.booster.codegeneration.persistence.layer.jpahibernate;

import junit.framework.TestCase;
import test.hexacta.booster.providers.CodeGeneratorConfigurationProvider;

import com.hexacta.booster.codegeneration.TextFile;
import com.hexacta.booster.codegeneration.configuration.CodeGeneratorConfiguration;

/**
 * @author ltenconi
 * 
 */
public class SpringJpaDAOGeneratorTest extends TestCase {

    public void testGenerate() {

        CodeGeneratorConfigurationProvider codeGeneratorConfigurationProvider = new CodeGeneratorConfigurationProvider();
        CodeGeneratorConfiguration codeGeneratorConfiguration = codeGeneratorConfigurationProvider
                .getCodeGeneratorConfiguration();

        SpringJpaDAOGenerator springDAOGenerator = new SpringJpaDAOGenerator();
        try {
            TextFile generated = springDAOGenerator.generate(codeGeneratorConfiguration.getClassList().iterator()
                    .next(), codeGeneratorConfiguration);
            assertFalse(generated.getText().contains("$"));
        } catch (Exception e) {
            fail();
        }

    }

}
