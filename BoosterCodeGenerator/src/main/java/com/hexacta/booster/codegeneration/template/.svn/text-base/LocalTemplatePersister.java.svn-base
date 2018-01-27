/**
 *
 */
package com.hexacta.booster.codegeneration.template;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.exolab.castor.xml.Marshaller;
import org.exolab.castor.xml.Unmarshaller;

import com.hexacta.booster.codegeneration.template.exception.InvalidTemplateDataFileException;
import com.hexacta.booster.codegeneration.template.exception.TemplateDataNotFoundException;
import com.hexacta.booster.codegeneration.template.exception.TemplateNotFoundException;
import com.hexacta.booster.codegeneration.template.model.BoosterTemplate;
import com.hexacta.booster.codegeneration.template.model.IMetaTemplate;
import com.hexacta.booster.codegeneration.template.model.MetaTemplate;
import com.hexacta.booster.codegeneration.template.model.MetaVariable;
import com.hexacta.booster.codegeneration.template.model.TemplateCategory;
import com.hexacta.booster.codegeneration.template.model.TemplateData;
import com.hexacta.booster.exception.InfraestructureException;

/**
 * @author ltenconi
 * 
 */
public class LocalTemplatePersister implements ITemplatePersister {

    /**
     *
     */
    public static final String SRC_MAIN_RESOURCES = "./src/main/resources/";

    private static final Logger log = Logger.getLogger(LocalTemplatePersister.class);

    private String localRepositoryPath;

    /*
     *
     *
     *
     *
     */
    public void init(final String aLocalRepositotyPath) {
        localRepositoryPath = aLocalRepositotyPath;
        File directory = new File(aLocalRepositotyPath);
        if (!directory.exists()) {
            directory.mkdirs();
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.hexacta.booster.codegeneration.template.ITemplatePersister#add(com.
     * hexacta.booster.codegeneration.template.MetaTemplate)
     */
    public void add(final IMetaTemplate metaTemplate) throws IOException {
        update(MetaTemplateBuilder.build(metaTemplate));

    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.hexacta.booster.codegeneration.template.ITemplatePersister#get(java
     * .lang.String)
     */
    public IMetaTemplate get(final String templateId) throws InvalidTemplateDataFileException {

        IMetaTemplate metaTemplate = getFromLFileSystem(templateId);
        if (metaTemplate != null)
            return metaTemplate;
        else
            return getFromResource(templateId);

    }

    public IMetaTemplate getFromLFileSystem(final String templateId) throws InvalidTemplateDataFileException {
        String templateText = getStringFromFile(localRepositoryPath + templateId);
        if (templateText != null) {
            TemplateData data;
            try {
                data = getTemplateData(templateId);
                return new MetaTemplate(templateId, new BoosterTemplate(templateText), data);
            } catch (TemplateDataNotFoundException e) {
                return new MetaTemplate(templateId, new BoosterTemplate(templateText));
            }

        } else
            return null;
    }

    /**
     * @param templateId
     * @return
     * @throws TemplateNotFoundException
     */
    private IMetaTemplate getFromResource(final String templateId) {
        String templateText;
        templateText = getStringFromResource(templateId);
        if (templateText == null)
            return null;
        if (isBoosterCodeGeneratorTemplate(templateId)) {
            TemplateData templateData = new TemplateData();
            templateData.setCategory(new TemplateCategory("Booster Templates"));
            templateData.setDescription("Template" + templateId + " from Booster Code Generator Resources");
            templateData.setHits(new Long(0));
            templateData.setTemplateName(templateId.replace("Booster.", "").replace(".vm", ""));
            templateData.setMetaVariables(retrieveMetaVariables(templateText));
            return new MetaTemplate(templateId, new BoosterTemplate(templateText), templateData);
        } else
            return new MetaTemplate(templateId, new BoosterTemplate(templateText));

    }

    /**
     * @param templateText
     * @return
     */
    private List<MetaVariable> retrieveMetaVariables(final String templateText) {

        char[] bytes = templateText.toCharArray();
        int i = 0;
        List<MetaVariable> metaVariables = new ArrayList<MetaVariable>();

        while (i < bytes.length) {
            if (bytes[i] == '$') {
                i = i + 1;
                String varName = "";

                if (bytes[i] == '{') {
                    while (i < bytes.length && bytes[i] != '}' && bytes[i] != '\n') {
                        i = i + 1;
                        varName = varName + bytes[i];
                    }
                    if (i < bytes.length && bytes[i] == '}') {
                        varName = varName.replaceAll("}", "");
                        MetaVariable metaVariable = new MetaVariable(varName);
                        metaVariable.setDefaultValue(varName);
                        metaVariable.setMeaning("");
                        metaVariables.add(metaVariable);
                    }
                } else {
                    varName = varName + bytes[i];
                    i = i + 1;
                    while (i < bytes.length && bytes[i] != ' ' && bytes[i] != '\n') {
                        varName = varName + bytes[i];
                        i = i + 1;
                    }
                    varName = varName.replaceAll(" ", "");
                    varName = varName.replaceAll("\n", "");

                    MetaVariable metaVariable = new MetaVariable(varName);
                    metaVariable.setDefaultValue(varName);
                    metaVariable.setMeaning("");

                    metaVariables.add(metaVariable);
                }

            }
            i = i + 1;

        }
        return metaVariables;
    }

    private boolean isBoosterCodeGeneratorTemplate(final String templateId) {
        return templateId.startsWith("Booster.") && templateId.endsWith(".vm");
    }

    /**
     * {@inheritDoc}
     * 
     * @throws InvalidTemplateDataFileException
     * @throws TemplateDataNotFoundException
     * @throws TemplateNotFoundException
     */
    public List<IMetaTemplate> getAllMetaTemplates() throws TemplateNotFoundException, TemplateDataNotFoundException,
            InvalidTemplateDataFileException {

        List<IMetaTemplate> metaTemplates = new ArrayList<IMetaTemplate>();
        File directorio = new File(localRepositoryPath);
        if (directorio.exists()) {
            File[] files = directorio.listFiles();
            for (File file : files) {
                String name = file.getName();
                MetaTemplate metaTemplate = null;
                if (name.endsWith(".vm")) {
                    String templateId = name;
                    metaTemplate = (MetaTemplate) get(templateId);
                    metaTemplates.add(metaTemplate);
                }
            }
        } else {
            directorio.mkdirs();
        }
        return metaTemplates;
    }

    /**
     * @param string
     * @return
     */
    private String getStringFromFile(final String filePath) {
        File file = new File(filePath);
        if (file.exists()) {
            try {
                FileReader fileReader = new FileReader(file);
                return IOUtils.toString(fileReader);
            } catch (FileNotFoundException e) {
                throw new InfraestructureException(e);
            } catch (IOException e) {
                throw new InfraestructureException(e);
            }
        } else
            return null;
    }

    /**
     * @param string
     * @return
     * @throws TemplateNotFoundException
     * @throws IOException
     */
    private String getStringFromResource(final String fileName) {

        URL url = this.getClass().getClassLoader().getResource(fileName);
        InputStreamReader inputStreamReader;
        try {
            inputStreamReader = new InputStreamReader(url.openStream());
        } catch (IOException e1) {
            return null;
        }
        try {
            return IOUtils.toString(inputStreamReader);
        } catch (FileNotFoundException e) {
            throw new InfraestructureException(e);
        } catch (IOException e) {
            throw new InfraestructureException(e);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.hexacta.booster.codegeneration.template.ITemplatePersister#update(com
     * .hexacta.booster.codegeneration.template.IMetaTemplate)
     */
    public void update(final IMetaTemplate iMetaTemplate) throws IOException {
        MetaTemplate metaTemplate = MetaTemplateBuilder.build(iMetaTemplate);
        try {

            // TemplateData templateData = metaTemplate.getTemplateData();
            if (existMetaTemplateWithSameName(metaTemplate)) {
                metaTemplate.setTemplateName(metaTemplate.getTemplateName() + System.currentTimeMillis());
                // System.out.println("cambie nombre a .. " +
                // metaTemplate.getTemplateName());
            }

            FileWriter templateFileWriter = new FileWriter(localRepositoryPath + metaTemplate.getId());

            templateFileWriter.write(metaTemplate.getTemplate().getText());

            templateFileWriter.close();

            String filePath = localRepositoryPath + metaTemplate.getId() + ".xml";

            FileWriter filePathWriter = new FileWriter(filePath);

            Marshaller marshaller = new Marshaller(filePathWriter);
            marshaller.marshal(metaTemplate.getTemplateData());

            filePathWriter.close();

        } catch (Exception e) {
            throw new IOException(e);
        }

    }

    /**
     * @param metaTemplate
     * @return
     */
    private boolean existMetaTemplateWithSameName(final IMetaTemplate metaTemplate) {

        try {
            List<IMetaTemplate> allTemplates = getAllMetaTemplates();
            for (IMetaTemplate metaTemplateInRepository : allTemplates) {

                if (metaTemplateInRepository.getTemplateData().getCategory().equals(
                        metaTemplate.getTemplateData().getCategory())
                        && !metaTemplate.getId().equals(metaTemplateInRepository.getId())) {
                    if (metaTemplateInRepository.getTemplateData().getTemplateName().equals(
                            metaTemplate.getTemplateData().getTemplateName()))
                        return true;
                }
            }
        } catch (Exception e) {
            log.error(e);
        }
        return false;
    }

    /**
     * @param templateId
     * @return
     * @throws TemplateDataNotFoundException
     * @throws InvalidTemplateDataFileException
     */
    private TemplateData getTemplateData(final String templateId) throws TemplateDataNotFoundException,
            InvalidTemplateDataFileException {

        File file = new File(localRepositoryPath + templateId + ".xml");

        try {

            FileReader reader = new FileReader(file);

            TemplateData data = (TemplateData) Unmarshaller.unmarshal(TemplateData.class, reader);

            reader.close();

            return data;

        } catch (FileNotFoundException e) {
            throw new TemplateDataNotFoundException(e);
        } catch (Exception e) {
            throw new InvalidTemplateDataFileException(e);
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @throws TemplateNotFoundException
     * @throws TemplateDataNotFoundException
     * @throws IOException
     */
    public void remove(final IMetaTemplate metaTemplate) throws TemplateNotFoundException,
            TemplateDataNotFoundException, IOException {

        String templatePath = localRepositoryPath + metaTemplate.getId();
        File template = new File(templatePath);
        if (template.exists()) {
            try {
                FileUtils.forceDelete(template);
            } catch (Exception e) {
                template.deleteOnExit();
            }
        } else
            throw new TemplateNotFoundException(templatePath);

        String templateDataPath = templatePath + ".xml";
        File templateData = new File(templateDataPath);
        if (templateData.exists()) {
            try {
                FileUtils.forceDelete(templateData);
            } catch (Exception e) {
                templateData.deleteOnExit();
            }

        } else
            throw new TemplateDataNotFoundException(templateDataPath);

    }

    // public Set<Tag> getAllTags() throws TemplateNotFoundException,
    // TemplateDataNotFoundException,
    // InvalidTemplateDataFileException {
    // List<IMetaTemplate> allMetaTemplates = getAllMetaTemplates();
    // Set<Tag> tags = new HashSet<Tag>();
    // for (IMetaTemplate metaTemplate : allMetaTemplates) {
    // tags.addAll(metaTemplate.getTemplateData().getTags());
    // }
    // return tags;
    // }

}
