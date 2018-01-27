/*
 * [legal notice here]
 */
package com.hexacta.booster.codegeneration.warning;

import java.io.File;

import com.hexacta.booster.codegeneration.TextFile;

/**
 * 
 * 
 * Created on 03/03/2009
 * 
 * @author fmrodriguez
 * 
 */
public class ExistFileWarning extends GenerationWarning {

    private File existentFile;

    private TextFile generatedFile;

    /**
     * @param message
     */
    public ExistFileWarning(final File existentFile, final TextFile generatedFile) {
        super(generatedFile.getPath() + generatedFile.getName());
        this.existentFile = existentFile;
        this.generatedFile = generatedFile;

    }

    @Override
    public boolean isExistFileWarning() {
        return true;
    }

    public void setExistentFile(final File existentFile) {
        this.existentFile = existentFile;
    }

    public File getExistentFile() {
        return existentFile;
    }

    public void setGeneratedFile(final TextFile generatedFile) {
        this.generatedFile = generatedFile;
    }

    public TextFile getGeneratedFile() {
        return generatedFile;
    }

}
