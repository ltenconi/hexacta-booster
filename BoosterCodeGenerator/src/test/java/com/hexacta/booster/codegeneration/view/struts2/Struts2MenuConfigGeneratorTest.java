/*
 * [legal notice here]
 */
package com.hexacta.booster.codegeneration.view.struts2;

import junit.framework.TestCase;
import test.hexacta.booster.providers.CodeGeneratorConfigurationProvider;

import com.hexacta.booster.codegeneration.TextFile;
import com.hexacta.booster.codegeneration.configuration.CodeGeneratorConfiguration;

/**
 * This class tests Struts2MenuConfigGenerator.
 * 
 * Created on 15/04/2009
 * 
 * @author vmartz
 * 
 */
public class Struts2MenuConfigGeneratorTest extends TestCase {

    public void testGenerate() {

        CodeGeneratorConfigurationProvider codeGeneratorConfigurationProvider = new CodeGeneratorConfigurationProvider();
        CodeGeneratorConfiguration codeGeneratorConfiguration = null;
        codeGeneratorConfiguration = codeGeneratorConfigurationProvider.getCodeGeneratorConfigurationForOneClass();

        Struts2MenuConfigGenerator strutsMenuConfigGenerator = new Struts2MenuConfigGenerator();

        try {

            TextFile generated = strutsMenuConfigGenerator.generate(codeGeneratorConfiguration.getClassList()
                    .iterator().next(), codeGeneratorConfiguration);
            assertFalse(generated.getText().contains("$"));
            //
            // System.out.println("text: \n" + generated.getText());
            // System.out.println("name: " + generated.getName());
            // System.out.println("path: " + generated.getPath());

        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

}
