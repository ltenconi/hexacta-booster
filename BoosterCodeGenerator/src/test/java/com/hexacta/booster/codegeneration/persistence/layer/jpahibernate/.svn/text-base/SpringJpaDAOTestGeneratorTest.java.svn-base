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
public class SpringJpaDAOTestGeneratorTest extends TestCase {

    public void testGenerate() throws Exception {

        CodeGeneratorConfigurationProvider codeGeneratorConfigurationProvider = new CodeGeneratorConfigurationProvider();
        CodeGeneratorConfiguration codeGeneratorConfiguration = codeGeneratorConfigurationProvider
                .getCodeGeneratorConfiguration();

        SpringJpaDAOTestGenerator springDAOGenerator = new SpringJpaDAOTestGenerator();

        TextFile generated = springDAOGenerator.generate(codeGeneratorConfiguration.getClassList().iterator().next(),
                codeGeneratorConfiguration);
        assertFalse(generated.getText().contains("$"));

    }

}
