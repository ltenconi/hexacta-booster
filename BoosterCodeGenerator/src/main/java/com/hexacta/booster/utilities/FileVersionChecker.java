/*
 * [legal notice here]
 */
package com.hexacta.booster.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.io.IOUtils;

import com.hexacta.booster.exception.InfraestructureException;

/**
 * 
 * 
 * Created on 03/03/2009
 * 
 * @author fmrodriguez
 * 
 */
public final class FileVersionChecker {

    /**
     * 
     */
    private static final String MD5 = "MD5";

    /**
     * 
     */
    private FileVersionChecker() {
    }

    public static boolean equalsByContent(final File file, final String content) throws IOException {

        InputStream is = new FileInputStream(file);
        byte[] bytes = IOUtils.toByteArray(is);

        MessageDigest m = null;
        try {
            m = MessageDigest.getInstance(MD5);
        } catch (NoSuchAlgorithmException e) {
            throw new InfraestructureException(e);
        }
        byte[] digest = m.digest(bytes);
        String md5FromFile = new BigInteger(1, digest).toString(16);
        m.reset();

        digest = m.digest(content.getBytes());
        String md5FromString = new BigInteger(1, digest).toString(16);

        if (md5FromFile.equalsIgnoreCase(md5FromString))
            return true;
        return false;

    }

    public static boolean equalsByContent(final File aFile, final File anotherFile) throws IOException {

        InputStream isFromFile = new FileInputStream(aFile);
        InputStream isFromAnotherFile = new FileInputStream(anotherFile);

        MessageDigest m = null;
        try {
            m = MessageDigest.getInstance(MD5);
        } catch (NoSuchAlgorithmException e) {
            throw new InfraestructureException(e);
        }
        byte[] digest = m.digest(IOUtils.toByteArray(isFromFile));
        String aFileMd5 = new BigInteger(1, digest).toString(16);
        m.reset();

        digest = m.digest(IOUtils.toByteArray(isFromAnotherFile));
        String anotherFileMd5 = new BigInteger(1, digest).toString(16);

        if (aFileMd5.equalsIgnoreCase(anotherFileMd5))
            return true;
        return false;

    }

}
