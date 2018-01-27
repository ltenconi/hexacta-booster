package com.hexacta.booster.codegeneration.template;

import java.util.List;

import com.hexacta.booster.codegeneration.TemplatesNames;
import com.hexacta.booster.codegeneration.template.exception.InvalidTemplateDataFileException;
import com.hexacta.booster.codegeneration.template.exception.TemplateDataNotFoundException;
import com.hexacta.booster.codegeneration.template.exception.TemplateInitErrorException;
import com.hexacta.booster.codegeneration.template.exception.TemplateParseErrorException;
import com.hexacta.booster.codegeneration.template.model.IMetaTemplate;
import com.hexacta.booster.exception.InfraestructureException;

/**
 * This class publishes the templates provided by default for Booster Code
 * Generator.
 * 
 * Created on 01/06/2009
 * 
 * @author vmartz
 * 
 */

public class TemplatePublisher {

    public void publish(final ITemplatePersister templatePersister) {

        List<String> templatesNames = TemplatesNames.getAllNames();

        LocalTemplatePersister localTemplatePersister = new LocalTemplatePersister();

        for (String templateName : templatesNames) {

            IMetaTemplate metaTemplate;

            try {
                if (!published(templateName,templatePersister)){
                    metaTemplate = localTemplatePersister.get(templateName);
                    templatePersister.add(metaTemplate);                    
                }
              
            } catch (Exception e) {
                throw new InfraestructureException(e);
            }

        }

    }

    private boolean published(String templateName, ITemplatePersister templatePersister) throws InvalidTemplateDataFileException, TemplateParseErrorException, TemplateInitErrorException, TemplateDataNotFoundException {
        if (templatePersister instanceof LocalTemplatePersister){
            if (((LocalTemplatePersister)templatePersister).getFromLFileSystem(templateName) == null) {//if not published
              return false;
            }
        }else{
            if (templatePersister.get(templateName) == null) {
               return false;
            }
        }
        return true;
    }
}
