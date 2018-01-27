package com.hexacta.booster.utilities;

import java.io.File;

import junit.framework.TestCase;

import com.hexacta.booster.codegeneration.GenerationInfo;
import com.hexacta.booster.codegeneration.TextFile;
import com.hexacta.booster.exception.InfraestructureException;

/**
 * 
 */
public class TextFileWriterTest extends TestCase {

    public void testWrite() {

        TextFileWriter aWriter = new TextFileWriter();

        TextFile textFile = new TextFile("textoDeLaClase", "fileText.txt", "./textFolder/");

        aWriter.write(textFile, new GenerationInfo());

        File aFile = new File("./textFolder/" + "fileText.txt");

        assertTrue(aFile.exists());

        aFile.delete();
        File aFolder = new File("./textFolder/");
        aFolder.delete();
    }

    public void testWriteWithException() {

        try {
            TextFileWriter textFileWriter = new TextFileWriter();

            TextFile textFile = new TextFile("", "", "");

            textFileWriter.write(textFile, new GenerationInfo());

            fail("Debería levantar una InfraestructureException");

        } catch (InfraestructureException e) {
            assertTrue(true);
        }
    }

}
