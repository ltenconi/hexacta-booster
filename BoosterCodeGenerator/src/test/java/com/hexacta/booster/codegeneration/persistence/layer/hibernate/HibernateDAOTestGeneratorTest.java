/*
 * [legal notice here]
 */
package com.hexacta.booster.codegeneration.persistence.layer.hibernate;

import junit.framework.TestCase;
import test.hexacta.booster.providers.CodeGeneratorConfigurationProvider;

import com.hexacta.booster.codegeneration.TextFile;
import com.hexacta.booster.codegeneration.configuration.CodeGeneratorConfiguration;

/**
 * @author fmrodriguez
 * 
 */
public class HibernateDAOTestGeneratorTest extends TestCase {

    public void testCreation() {

        HibernateDAOTestGenerator hibernateDAOTestGenerator = new HibernateDAOTestGenerator();

        assertNotNull(hibernateDAOTestGenerator);

    }

    public void testGenerate() throws Exception {

        CodeGeneratorConfigurationProvider codeGeneratorConfigurationProvider = new CodeGeneratorConfigurationProvider();
        CodeGeneratorConfiguration codeGeneratorConfiguration = codeGeneratorConfigurationProvider
                .getCodeGeneratorConfiguration();

        HibernateDAOTestGenerator hibernateDAOTestGenerator = new HibernateDAOTestGenerator();
        
            TextFile generated = hibernateDAOTestGenerator.generate(codeGeneratorConfiguration.getClassList()
                    .iterator().next(), codeGeneratorConfiguration);
            assertFalse(generated.getText().contains("$"));
       

    }

}
