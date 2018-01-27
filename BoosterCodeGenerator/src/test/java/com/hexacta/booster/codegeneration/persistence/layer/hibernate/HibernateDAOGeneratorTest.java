/*
 * [legal notice here]
 */
package com.hexacta.booster.codegeneration.persistence.layer.hibernate;

import junit.framework.TestCase;
import test.hexacta.booster.providers.CodeGeneratorConfigurationProvider;

import com.hexacta.booster.codegeneration.TextFile;
import com.hexacta.booster.codegeneration.configuration.CodeGeneratorConfiguration;

/**
 * 
 * 
 * Created on 04/02/2009
 * 
 * @author fmrodriguez
 * 
 */
public class HibernateDAOGeneratorTest extends TestCase {

    public void testCreation() {

        HibernateDAOGenerator hibernateDAOGenerator = new HibernateDAOGenerator();

        assertNotNull(hibernateDAOGenerator);

    }

    public void testGenerate() {

        CodeGeneratorConfigurationProvider codeGeneratorConfigurationProvider = new CodeGeneratorConfigurationProvider();
        CodeGeneratorConfiguration codeGeneratorConfiguration = codeGeneratorConfigurationProvider
                .getCodeGeneratorConfiguration();

        HibernateDAOGenerator hibernateDAOGenerator = new HibernateDAOGenerator();
        try {
            TextFile generated = hibernateDAOGenerator.generate(codeGeneratorConfiguration.getClassList().iterator()
                    .next(), codeGeneratorConfiguration);
            assertFalse(generated.getText().contains("$"));
        } catch (Exception e) {
            fail();
        }

    }

}
