package com.hexacta.booster.utilities;

import java.util.Iterator;

import org.apache.log4j.Logger;

import com.hexacta.booster.codegeneration.ArchetypeTextFileGenerator;
import com.hexacta.booster.codegeneration.GenerationInfo;
import com.hexacta.booster.codegeneration.TextFile;
import com.hexacta.booster.codegeneration.TextFileGenerator;
import com.hexacta.booster.codegeneration.configuration.CodeGeneratorConfiguration;
import com.hexacta.booster.codegeneration.metamodel.Class;
import com.hexacta.booster.exception.InfraestructureException;

/**
 * 
 * 
 * 
 * Created on 26/01/2009
 * 
 * @author clopez
 * 
 */
public final class CodeGeneratorHelper {

    private CodeGeneratorHelper() {
    }

    static final Logger logger = Logger.getLogger(CodeGeneratorHelper.class);

    public static void generateForMetaModel(final TextFileGenerator textFileGenerator,
            final CodeGeneratorConfiguration codeGeneratorConfiguration, final GenerationInfo generationInfo) {

        TextFileWriter aFileWriter = new TextFileWriter();
        for (Iterator<Class> iterator = codeGeneratorConfiguration.getGenerateCodeFor().iterator(); iterator.hasNext();) {
            Class aClass = iterator.next();
            if (!aClass.isAbstract()) {
                try {
                    TextFile textFile = textFileGenerator.generate(aClass, codeGeneratorConfiguration);
                    boolean successful = aFileWriter.write(textFile, generationInfo);
                    if (successful) {
                        textFileGenerator.addGenerationInfo(textFile, generationInfo);
                    }
                } catch (Exception e) {
                    logger.error(e);
                    throw new InfraestructureException(e);
                }
            }
        }

    }

    public static void generateArchetypeFile(final ArchetypeTextFileGenerator fixedTextFileGenerator,
            final CodeGeneratorConfiguration codeGeneratorConfiguration, final GenerationInfo generationInfo) {

        String groupId = MetaModelUtils.getGroupId(codeGeneratorConfiguration.getClassList().iterator().next());

        try {
            TextFile textFile = fixedTextFileGenerator.generate(groupId, codeGeneratorConfiguration);
            TextFileWriter textFileWriter = new TextFileWriter();
            boolean successful = textFileWriter.write(textFile, generationInfo);
            if (successful) {
                fixedTextFileGenerator.addGenerationInfo(textFile, generationInfo);
            }
        } catch (Exception e) {
            logger.error(e);
            throw new InfraestructureException(e);
        }
    }

}
