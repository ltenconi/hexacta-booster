/**
 * 
 */
package com.hexacta.booster.codegeneration.template;

import java.io.StringWriter;
import java.util.Map;
import java.util.Set;

import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;

import com.hexacta.booster.codegeneration.template.model.MetaVariable;
import com.hexacta.booster.exception.InfraestructureException;

/**
 * @author ltenconi
 * 
 */
public class VelocityMetaTemplateEngine implements IMetaTemplateEngine {

    /**
     * 
     */
    private static final String LOG_TAG_NAME = "[VelocityMetaTemplateEngine]";

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.hexacta.booster.codegeneration.template.IMetaTemplateEngine#merge
     * (com.hexacta.booster.codegeneration.template.MetaTemplateGeneration)
     */
    public String merge(final MetaTemplateGeneration metaTemplateGeneration) {
        try {

            Velocity.init();
            VelocityContext context = buildContext(metaTemplateGeneration.getMetaVariablesValue());
            StringWriter writer = new StringWriter();

            Velocity.evaluate(context, writer, LOG_TAG_NAME, metaTemplateGeneration.getMetaTemplate().getTemplate()
                    .getText());

            return writer.toString();

        } catch (Exception e) {
            throw new InfraestructureException(e);
        }

    }

    /**
     * @param metaVariablesValue
     * @return
     */
    private VelocityContext buildContext(final Map<MetaVariable, Object> metaVariablesValue) {

        VelocityContext context = new VelocityContext();
        Set<MetaVariable> metaVariables = metaVariablesValue.keySet();

        for (MetaVariable metaVariable : metaVariables) {
            context.put(metaVariable.getName(), metaVariablesValue.get(metaVariable));
        }

        return context;

    }
}
