package com.hexacta.booster.utilities;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import org.apache.log4j.Logger;

import com.hexacta.booster.codegeneration.GenerationInfo;
import com.hexacta.booster.codegeneration.TextFile;
import com.hexacta.booster.codegeneration.warning.ExistFileWarning;
import com.hexacta.booster.exception.InfraestructureException;

/**
 * 
 * @author clopez
 * 
 */
public class TextFileWriter {

    private FileWriter fileWriter;

    private BufferedWriter bufferedWriter;

    private PrintWriter printWriter;

    /**
     * Log4j logger.
     */
    static final Logger logger = Logger.getLogger(TextFileWriter.class);

    public TextFileWriter() {

    }

    public boolean write(final TextFile textFile, final GenerationInfo generationInfo) {
        String completeFileName = null;
        boolean successful = false;
        try {
            File directory = new File(textFile.getPath());
            directory.mkdirs();

            completeFileName = textFile.getPath() + textFile.getName();
            File file = new File(completeFileName);
            if (file.exists()) {
                if (!FileVersionChecker.equalsByContent(file, textFile.getText())) {
                    generationInfo.getGenerationWarnings().add(new ExistFileWarning(file, textFile));
                    return false;
                } else {
                    generationInfo.addExistingEqualsFile(completeFileName);
                }
            } else {
                fileWriter = new FileWriter(completeFileName);
                bufferedWriter = new BufferedWriter(fileWriter);
                printWriter = new PrintWriter(bufferedWriter);
                printWriter.print(textFile.getText());
                printWriter.close();
                successful = true;
            }

        } catch (IOException e) {
            logger.info("Can't create output file " + completeFileName);
            throw new InfraestructureException(e);
        }
        logger.info("File " + textFile.getName() + " is created in:" + textFile.getPath());
        return successful;
    }
}
