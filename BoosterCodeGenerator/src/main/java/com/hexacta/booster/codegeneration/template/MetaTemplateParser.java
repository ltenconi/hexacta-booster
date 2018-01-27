package com.hexacta.booster.codegeneration.template;

import java.io.FileReader;

import org.apache.commons.io.IOUtils;

import com.hexacta.booster.exception.InfraestructureException;

/***/
public final class MetaTemplateParser {

    private MetaTemplateParser() {
    }

    public static boolean isMetaTemplateXML(final String filename) {
        FileReader fileReader;

        try {
            fileReader = new FileReader(filename);
            String text = IOUtils.toString(fileReader);
            System.out.println(text);
            if (text.contains("<template-data>"))
                return true;
            return false;
        } catch (Exception e) {
            throw new InfraestructureException(e);
        }

    }
}
