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
public class HibernateDAOTest4GeneratorTest extends TestCase {

    public void testCreation() {

        HibernateDAOTest4Generator hibernateDAOTest4Generator = new HibernateDAOTest4Generator();

        assertNotNull(hibernateDAOTest4Generator);

    }

    public void testGenerate() throws Exception {

        CodeGeneratorConfigurationProvider codeGeneratorConfigurationProvider = new CodeGeneratorConfigurationProvider();
        CodeGeneratorConfiguration codeGeneratorConfiguration = codeGeneratorConfigurationProvider
                .getCodeGeneratorConfiguration();

        HibernateDAOTest4Generator hibernateDaoTest4Generator = new HibernateDAOTest4Generator();
        
            TextFile generated = hibernateDaoTest4Generator.generate(codeGeneratorConfiguration.getClassList()
                    .iterator().next(), codeGeneratorConfiguration);
            assertFalse(generated.getText().contains("$"));
     

    }

}
