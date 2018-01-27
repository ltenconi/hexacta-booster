/*
 * [legal notice here]
 */
package com.hexacta.booster.codegeneration.view;

import junit.framework.TestCase;
import test.hexacta.booster.providers.CodeGeneratorConfigurationProvider;

import com.hexacta.booster.codegeneration.TextFile;
import com.hexacta.booster.codegeneration.configuration.CodeGeneratorConfiguration;

/**
 * This class tests ApplicationResourcesGenerator.
 * 
 * Created on 14/04/2009
 * 
 * @author vmartz
 * 
 */
public class ApplicationResourcesGeneratorTest extends TestCase {

    public void testGenerate() {

        CodeGeneratorConfigurationProvider codeGeneratorConfigurationProvider = new CodeGeneratorConfigurationProvider();
        CodeGeneratorConfiguration codeGeneratorConfiguration = null;
        codeGeneratorConfiguration = codeGeneratorConfigurationProvider.getCodeGeneratorConfigurationForOneClass();

        ApplicationResourcesGenerator appResourcesGenerator = new ApplicationResourcesGenerator();

        try {

            TextFile generated = appResourcesGenerator.generate(codeGeneratorConfiguration.getClassList().iterator()
                    .next(), codeGeneratorConfiguration);

            assertFalse(generated.getText().contains("$"));

        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    public void testGenerateSpanish() {

        CodeGeneratorConfigurationProvider codeGeneratorConfigurationProvider = new CodeGeneratorConfigurationProvider();
        CodeGeneratorConfiguration codeGeneratorConfiguration = null;
        codeGeneratorConfiguration = codeGeneratorConfigurationProvider.getCodeGeneratorConfigurationForOneClass();

        ApplicationResourcesSpanishGenerator appResourcesSpanishGenerator = new ApplicationResourcesSpanishGenerator();

        try {

            TextFile generated = appResourcesSpanishGenerator.generate(codeGeneratorConfiguration.getClassList()
                    .iterator().next(), codeGeneratorConfiguration);

            assertFalse(generated.getText().contains("$"));

        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    public void testGenerateEnglish() {

        CodeGeneratorConfigurationProvider codeGeneratorConfigurationProvider = new CodeGeneratorConfigurationProvider();
        CodeGeneratorConfiguration codeGeneratorConfiguration = null;
        codeGeneratorConfiguration = codeGeneratorConfigurationProvider.getCodeGeneratorConfigurationForOneClass();

        ApplicationResourcesEnglishGenerator appResourcesEnglishGenerator = new ApplicationResourcesEnglishGenerator();

        try {

            TextFile generated = appResourcesEnglishGenerator.generate(codeGeneratorConfiguration.getClassList()
                    .iterator().next(), codeGeneratorConfiguration);

            assertFalse(generated.getText().contains("$"));

        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

}
