/*
 * [legal notice here]
 */
package com.hexacta.booster.codegeneration.view.struts2;

import junit.framework.TestCase;
import test.hexacta.booster.providers.CodeGeneratorConfigurationProvider;

import com.hexacta.booster.codegeneration.TextFile;
import com.hexacta.booster.codegeneration.configuration.CodeGeneratorConfiguration;

/**
 * This class tests Struts2ListViewGenerator.
 * 
 * Created on 16/04/2009
 * 
 * @author vmartz
 * 
 */
public class Struts2ListViewGeneratorTest extends TestCase {

    public void testGenerate() {

        CodeGeneratorConfigurationProvider codeGeneratorConfigurationProvider = new CodeGeneratorConfigurationProvider();
        CodeGeneratorConfiguration codeGeneratorConfiguration = null;
        codeGeneratorConfiguration = codeGeneratorConfigurationProvider.getCodeGeneratorConfigurationForOneClass();

        Struts2ListViewGenerator struts2ListViewGenerator = new Struts2ListViewGenerator();

        try {

            TextFile generated = struts2ListViewGenerator.generate(codeGeneratorConfiguration.getClassList().iterator()
                    .next(), codeGeneratorConfiguration);

            assertFalse(generated.getText().contains("${fields}"));
            assertFalse(generated.getText().contains("${Entity}"));
            assertFalse(generated.getText().contains("${entity}"));
            assertFalse(generated.getText().contains("${entities}"));

            // System.out.println("text: \n" + generated.getText());
            // System.out.println("name: " + generated.getName());
            // System.out.println("path: " + generated.getPath());

        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

}
