package com.hexacta.booster.utilities;

import org.apache.velocity.Template;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.exception.ParseErrorException;
import org.apache.velocity.exception.ResourceNotFoundException;

/**
 * 
 */
public final class VelocityHelper {

    private VelocityHelper() {

    }

    public static Template getTemplate(final String templateName) throws ResourceNotFoundException,
            ParseErrorException, Exception {

        Velocity.setProperty("resource.loader", "class");
        Velocity.setProperty("class.resource.loader.class",
                "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");

        Velocity.init();

        return Velocity.getTemplate(templateName);

    }

    /**
     * @param templateId
     * @return
     * @throws Exception
     * @throws ParseErrorException
     * @throws ResourceNotFoundException
     */
    public static Template getFromFileSystem(final String templateId, final String folderPath)
            throws ResourceNotFoundException, ParseErrorException, Exception {

        Velocity.init();

        return Velocity.getTemplate(folderPath + templateId);
    }

}
