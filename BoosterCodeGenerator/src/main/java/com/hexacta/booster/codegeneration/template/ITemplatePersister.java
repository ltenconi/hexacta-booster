/**
 *
 */
package com.hexacta.booster.codegeneration.template;

import java.io.IOException;
import java.util.List;

import com.hexacta.booster.codegeneration.template.exception.InvalidTemplateDataFileException;
import com.hexacta.booster.codegeneration.template.exception.TemplateDataNotFoundException;
import com.hexacta.booster.codegeneration.template.exception.TemplateInitErrorException;
import com.hexacta.booster.codegeneration.template.exception.TemplateNotFoundException;
import com.hexacta.booster.codegeneration.template.exception.TemplateParseErrorException;
import com.hexacta.booster.codegeneration.template.model.IMetaTemplate;

/**
 * @author ltenconi
 * 
 */
public interface ITemplatePersister {

    /**
     * @param templateId
     */
    IMetaTemplate get(String templateId) throws TemplateParseErrorException, TemplateInitErrorException,
            TemplateDataNotFoundException, InvalidTemplateDataFileException;

    /**
     * @param metaTemplate
     */
    void add(IMetaTemplate metaTemplate) throws IOException;

    /**
     * @param metaTemplate
     */
    void update(IMetaTemplate metaTemplate) throws IOException;

    /**
     * @param metaTemplate
     * @throws TemplateNotFoundException
     * @throws TemplateDataNotFoundException
     * @throws IOException
     */
    void remove(IMetaTemplate metaTemplate) throws TemplateNotFoundException, TemplateDataNotFoundException,
            IOException;

    /**
     * @return
     * @throws InvalidTemplateDataFileException
     * @throws TemplateDataNotFoundException
     * @throws TemplateNotFoundException
     */
    List<IMetaTemplate> getAllMetaTemplates() throws TemplateNotFoundException, TemplateDataNotFoundException,
            InvalidTemplateDataFileException;

    // /**
    // * @param searchCriteria
    // * @throws InvalidTemplateDataFileException
    // * @throws TemplateDataNotFoundException
    // * @throws TemplateNotFoundException
    // */
    // List<IMetaTemplate> searchByCriteria(SearchCriteria searchCriteria)
    // throws TemplateNotFoundException,
    // TemplateDataNotFoundException, InvalidTemplateDataFileException;

}
