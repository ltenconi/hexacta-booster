package com.hexacta.booster.codegeneration.view.struts2;

import test.hexacta.booster.providers.CodeGeneratorConfigurationProvider;
import com.hexacta.booster.codegeneration.TextFile;
import com.hexacta.booster.codegeneration.configuration.CodeGeneratorConfiguration;
import junit.framework.TestCase;

public class Struts2ActionTestGeneratorTest extends TestCase {
    
    public final void testGenerate() throws Exception {
        
        CodeGeneratorConfigurationProvider codeGeneratorConfigurationProvider = new CodeGeneratorConfigurationProvider();
        CodeGeneratorConfiguration codeGeneratorConfiguration = null;
        codeGeneratorConfiguration = codeGeneratorConfigurationProvider.getCodeGeneratorConfigurationForOneClass();

        Struts2ActionTestGenerator strutsActionGenerator = new Struts2ActionTestGenerator();


        TextFile generated = strutsActionGenerator.generate(codeGeneratorConfiguration.getClassList().iterator()
                .next(), codeGeneratorConfiguration);
        
           
        assertFalse(generated.getText().contains("$"));

   
    }

}
