/*
 * [legal notice here]
 */
package com.hexacta.booster.codegeneration.template;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import junit.framework.TestCase;

import org.apache.velocity.VelocityContext;

import ar.com.hexacta.utils.reflection.ReflectionUtils;

import com.hexacta.booster.codegeneration.template.model.BoosterTemplate;
import com.hexacta.booster.codegeneration.template.model.MetaTemplate;
import com.hexacta.booster.codegeneration.template.model.MetaVariable;
import com.hexacta.booster.codegeneration.template.model.TemplateData;

/**
 * @author fmrodriguez
 * 
 */
public class VelocityMetaTemplateEngineTest extends TestCase {

    /**
     * Test method for
     * {@link com.hexacta.booster.codegeneration.template.VelocityMetaTemplateEngine#merge(com.hexacta.booster.codegeneration.template.MetaTemplateGeneration)}
     * .
     */
    public final void testMerge() {

        MetaVariable metaVar = buildMetaVariable();
        MetaVariable anotherMetaVar = buildAnotherMetaVariable();
        List<MetaVariable> metaVariables = new ArrayList<MetaVariable>();
        metaVariables.add(metaVar);
        metaVariables.add(anotherMetaVar);

        Map<MetaVariable, Object> metaVariablesValue = buildMetaVariableValues(metaVariables);

        TemplateData templateData = new TemplateData();
        templateData.setMetaVariables(metaVariables);

        MetaTemplate metaTemplate = new MetaTemplate("templateTest.vm",
                new BoosterTemplate("Este es un template de test con dos variables $" + metaVar.getName() + " $"
                        + anotherMetaVar.getName()), templateData);

        MetaTemplateGeneration metaTemplateGeneration = new MetaTemplateGeneration(metaTemplate, metaVariablesValue);

        VelocityMetaTemplateEngine velocityMetaTemplateEngine = new VelocityMetaTemplateEngine();

        String expectedMerge = "Este es un template de test con dos variables " + metaVariablesValue.get(metaVar) + " "
                + metaVariablesValue.get(anotherMetaVar);
        String actualMerge = velocityMetaTemplateEngine.merge(metaTemplateGeneration);

        assertEquals(expectedMerge, actualMerge);

    }

    public void testbuildContext() {

        MetaVariable metaVar = buildMetaVariable();
        MetaVariable anotherMetaVar = buildAnotherMetaVariable();
        List<MetaVariable> metaVariables = new ArrayList<MetaVariable>();
        metaVariables.add(metaVar);
        metaVariables.add(anotherMetaVar);
        Map<MetaVariable, Object> metaVariablesValue = buildMetaVariableValues(metaVariables);

        VelocityContext expectedContext = new VelocityContext();
        expectedContext.put(metaVar.getName(), metaVariablesValue.get(metaVar));
        expectedContext.put(anotherMetaVar.getName(), metaVariablesValue.get(anotherMetaVar));

        VelocityMetaTemplateEngine velocityMetaTemplateEngine = new VelocityMetaTemplateEngine();

        Object[] args = new Object[] { metaVariablesValue };
        VelocityContext actualContext = (VelocityContext) ReflectionUtils.executePrivateMethod(
                velocityMetaTemplateEngine, VelocityMetaTemplateEngine.class, "buildContext",
                new Class<?>[] { Map.class }, args);

        assertEquals(expectedContext.getKeys().length, actualContext.getKeys().length);

        assertEquals(expectedContext.get(metaVar.getName()), actualContext.get(metaVar.getName()));
        assertEquals(expectedContext.get(anotherMetaVar.getName()), actualContext.get(anotherMetaVar.getName()));

        assertEquals(metaVariablesValue.get(metaVar), actualContext.get(metaVar.getName()));
        assertEquals(metaVariablesValue.get(anotherMetaVar), actualContext.get(anotherMetaVar.getName()));

    }

    /**
     * @return
     */
    private Map<MetaVariable, Object> buildMetaVariableValues(final List<MetaVariable> metaVariables) {

        Map<MetaVariable, Object> metaVariablesValue = new HashMap<MetaVariable, Object>();
        int index = 1;
        for (MetaVariable metaVariable : metaVariables) {
            metaVariablesValue.put(metaVariable, "value" + index);
            index++;
        }

        return metaVariablesValue;

    }

    private MetaVariable buildAnotherMetaVariable() {
        return new MetaVariable("name2", "meaning2", "dafault");
    }

    private MetaVariable buildMetaVariable() {
        return new MetaVariable("name1", "meaning1", "default");
    }

}
