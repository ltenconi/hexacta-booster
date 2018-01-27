package com.hexacta.booster.codegeneration.persistence.layer.hibernate;

import junit.framework.TestCase;
import test.hexacta.booster.providers.CodeGeneratorConfigurationProvider;

import com.hexacta.booster.codegeneration.TextFile;
import com.hexacta.booster.codegeneration.configuration.CodeGeneratorConfiguration;

/**
 * 
 */
public class SpringHibernateDaoTestGeneratorTest extends TestCase {

    public void testCreation() {

        SpringHibernateDaoTestGenerator generatedDaoTestGenerator = new SpringHibernateDaoTestGenerator();

        assertNotNull(generatedDaoTestGenerator);

    }

    public void testGenerate() throws Exception {

        CodeGeneratorConfigurationProvider codeGeneratorConfigurationProvider = new CodeGeneratorConfigurationProvider();
        CodeGeneratorConfiguration codeGeneratorConfiguration = codeGeneratorConfigurationProvider
                .getCodeGeneratorConfiguration();

        SpringHibernateDaoTestGenerator hibernateDaoTestGenerator = new SpringHibernateDaoTestGenerator();
       
            TextFile generated = hibernateDaoTestGenerator.generate(codeGeneratorConfiguration.getClassList()
                    .iterator().next(), codeGeneratorConfiguration);
            assertFalse(generated.getText().contains("$"));
       

    }

}
