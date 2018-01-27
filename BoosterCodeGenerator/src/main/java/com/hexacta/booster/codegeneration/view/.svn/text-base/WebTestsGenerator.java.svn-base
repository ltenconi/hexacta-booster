package com.hexacta.booster.codegeneration.view;

import java.util.HashMap;
import java.util.Map;

import com.hexacta.booster.codegeneration.GenerationInfo;
import com.hexacta.booster.codegeneration.TemplatesNames;
import com.hexacta.booster.codegeneration.TextFile;
import com.hexacta.booster.codegeneration.TextFileGenerator;
import com.hexacta.booster.codegeneration.configuration.ClassList;
import com.hexacta.booster.codegeneration.configuration.CodeGeneratorConfiguration;
import com.hexacta.booster.codegeneration.configuration.NotSupportedClassException;
import com.hexacta.booster.codegeneration.metamodel.Class;
import com.hexacta.booster.codegeneration.metamodel.Field;
import com.hexacta.booster.codegeneration.metamodel.Type;
import com.hexacta.booster.codegeneration.template.IMetaTemplateEngine;
import com.hexacta.booster.codegeneration.template.MetaTemplateGeneration;
import com.hexacta.booster.codegeneration.template.MetaTemplateSystem;
import com.hexacta.booster.codegeneration.template.model.MetaTemplate;
import com.hexacta.booster.codegeneration.template.model.MetaVariable;
import com.hexacta.booster.project.configuration.JavaProjectType;
import com.hexacta.booster.utilities.JavaClassFileNameBuilder;
import com.hexacta.booster.utilities.MetaModelUtils;
import com.hexacta.booster.utilities.PathBuilder;
import com.hexacta.booster.utilities.StringUtils;
import com.hexacta.booster.utilities.TestGenerationHelper;
import com.hexacta.booster.utilities.VarNameBuilder;

public class WebTestsGenerator implements TextFileGenerator {

    private long editId = -1;

    private long deleteId = -1;

    Map<MetaVariable, Object> metaVariablesValue;

    /**
     * @return the editId
     */
    public long getEditId() {
        return editId;
    }

    /**
     * @param editId
     *            the editId to set
     */
    public void setEditId(final long editId) {
        this.editId = editId;
    }

    /**
     * @return the deleteId
     */
    public long getDeleteId() {
        return deleteId;
    }

    /**
     * @param deleteId
     *            the deleteId to set
     */
    public void setDeleteId(final long deleteId) {
        this.deleteId = deleteId;
    }

    public void testGenerate() {

    }

    public WebTestsGenerator() {
        metaVariablesValue = new HashMap<MetaVariable, Object>();
    }

    @Override
    public void addGenerationInfo(final TextFile textFile, final GenerationInfo generationInfo) {
        generationInfo.addViews(textFile.getPath() + textFile.getName());

    }

    @Override
    public TextFile generate(final Class entityClass, final CodeGeneratorConfiguration codeGeneratorConfiguration)
            throws Exception {

        MetaTemplate serviceInterfaceTemplate = (MetaTemplate) MetaTemplateSystem.get(TemplatesNames
                .getName("WEB_TEST"));

        String groupId = MetaModelUtils.getGroupId(entityClass);

        buildFields(entityClass, codeGeneratorConfiguration);
        buildEntityNames(entityClass);

        setMetaVariable("editId", editId);
        setMetaVariable("deleteId", deleteId);

        MetaTemplateGeneration metaTemplateGeneration = new MetaTemplateGeneration(serviceInterfaceTemplate,
                metaVariablesValue);
        IMetaTemplateEngine metaTemplateEngine = MetaTemplateSystem.getMetaTemplateEngine();

        String serviceInterfaceText = metaTemplateEngine.merge(metaTemplateGeneration);

        TextFile textFile = new TextFile(serviceInterfaceText, JavaClassFileNameBuilder.buildForWebTest(entityClass),
                PathBuilder.buildforWebTest(groupId, codeGeneratorConfiguration.getDirectoryInfo()));

        return textFile;
    }

    private void setMetaVariable(final String metaVariableName, final Object value) {
        metaVariablesValue.put(new MetaVariable(metaVariableName), value);
    }

    /**
     * Generar mapa de campos y valores para agregar una nueva entidad. Elegir
     * variable modificable y valor asociado para la modificación de la entidad.
     * 
     * @param entityClass
     *            entidad a representar
     * @param codeGeneratorConfiguration
     *            configuración actual del code generator
     * @throws NotSupportedClassException
     */

    private void buildFields(final Class entityClass, final CodeGeneratorConfiguration codeGeneratorConfiguration)
            throws NotSupportedClassException {
        ClassList persistableClasses = codeGeneratorConfiguration.getPersistableMetaModel();
        Class persistableClass = persistableClasses.getClass(entityClass.getName());
        Field[] fieldsSet = persistableClass.getDeclaredFields();
        Map<String, Object> addFields = new HashMap<String, Object>();

        for (Field field : fieldsSet) {
            String fieldName = field.getName();
            Type fieldType = TestGenerationHelper.getType(fieldName, fieldsSet);
            addFields
                    .put(fieldName, TestGenerationHelper.generateJavaFieldValue(fieldType, codeGeneratorConfiguration));

        }
        setMetaVariable("addFields", addFields);

        String modificablePropertyName = TestGenerationHelper.getModificablePropertyName(fieldsSet, persistableClass,
                codeGeneratorConfiguration, new JavaProjectType());

        if (modificablePropertyName != null) {
            Type propertyType = TestGenerationHelper.getType(modificablePropertyName, fieldsSet);
            Object fieldValue = TestGenerationHelper.generateJavaFieldValue(propertyType, codeGeneratorConfiguration);
            setMetaVariable("modificable", true);
            setMetaVariable("modificablePropertyName", modificablePropertyName);
            setMetaVariable("modificableFieldValue", fieldValue);
        } else {
            setMetaVariable("modificable", false);
        }
    }

    /**
     * Setear el nombre de la entidad en minúsculas singular y plural, y primera
     * letra en mayúsculas singular y plural.
     * 
     * @param entityClass
     *            entidad a representar
     */

    private void buildEntityNames(final Class entityClass) {
        setMetaVariable("Entity", entityClass.getSimpleName());
        setMetaVariable("entity", VarNameBuilder.buildForEntity(entityClass));
        setMetaVariable("Entities", StringUtils.getStringPlural(entityClass.getSimpleName()));
        setMetaVariable("entities", StringUtils.getStringPlural(VarNameBuilder.buildForEntity(entityClass)));

    }

}
