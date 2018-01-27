/*
 * [legal notice here]
 */
package com.hexacta.booster.codegeneration.view.struts2;

import junit.framework.TestCase;
import test.hexacta.booster.providers.CodeGeneratorConfigurationProvider;

import com.hexacta.booster.codegeneration.TextFile;
import com.hexacta.booster.codegeneration.configuration.CodeGeneratorConfiguration;

/**
 * This class tests Struts2ActionGenerator.
 * 
 * Created on 08/04/2009
 * 
 * @author vmartz
 * 
 */
public class Struts2ActionGeneratorTest extends TestCase {

    public void testGenerate() {

        CodeGeneratorConfigurationProvider codeGeneratorConfigurationProvider = new CodeGeneratorConfigurationProvider();
        CodeGeneratorConfiguration codeGeneratorConfiguration = null;
        codeGeneratorConfiguration = codeGeneratorConfigurationProvider.getCodeGeneratorConfigurationForOneClass();

        Struts2ActionGenerator strutsActionGenerator = new Struts2ActionGenerator();

        try {

            TextFile generated = strutsActionGenerator.generate(codeGeneratorConfiguration.getClassList().iterator()
                    .next(), codeGeneratorConfiguration);
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