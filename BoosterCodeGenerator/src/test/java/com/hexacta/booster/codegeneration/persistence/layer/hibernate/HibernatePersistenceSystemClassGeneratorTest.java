package com.hexacta.booster.codegeneration.persistence.layer.hibernate;

import junit.framework.TestCase;
import ar.com.hexacta.utils.reflection.ReflectionUtils;

/**
 * 
 */
public class HibernatePersistenceSystemClassGeneratorTest extends TestCase {

    public void testCreation() {
        HibernatePersistenceSystemClassGenerator hibernatePersistenceSystemClassGenerator = new HibernatePersistenceSystemClassGenerator();
        assertNotNull(hibernatePersistenceSystemClassGenerator);
    }

    public void testGenerate() {

        HibernatePersistenceSystemClassGenerator hibernatePersistenceSystemClassGenerator = new HibernatePersistenceSystemClassGenerator();
        String generatedClassText = hibernatePersistenceSystemClassGenerator.generate();

        String expectedClassText = (String) ReflectionUtils.executePrivateMethod(
                hibernatePersistenceSystemClassGenerator, "generatePackage", new Class[] {}, new Object[] {})
                + (String) ReflectionUtils.executePrivateMethod(hibernatePersistenceSystemClassGenerator,
                        "generateImports", new Class[] {}, new Object[] {})
                + (String) ReflectionUtils.executePrivateMethod(hibernatePersistenceSystemClassGenerator,
                        "generateClass", new Class[] {}, new Object[] {});

        assertEquals(expectedClassText, generatedClassText);
    }

    public void testGeneratePackage() {

        HibernatePersistenceSystemClassGenerator hibernatePersistenceSystemClassGenerator = new HibernatePersistenceSystemClassGenerator();
        String generatedText = (String) ReflectionUtils.executePrivateMethod(hibernatePersistenceSystemClassGenerator,
                "generatePackage", new Class[] {}, new Object[] {});

        // String expectedText = "package test.model.persistence;\n";
        String expectedText = "package " + "persistence;\n";
        assertEquals(expectedText, generatedText);

    }

    public void testGenerateImports() {

        HibernatePersistenceSystemClassGenerator hibernatePersistenceSystemClassGenerator = new HibernatePersistenceSystemClassGenerator();
        String generatedText = (String) ReflectionUtils.executePrivateMethod(hibernatePersistenceSystemClassGenerator,
                "generateImports", new Class[] {}, new Object[] {});

        String expectedText = "import java.util.HashMap;\n" + "import org.hibernate.Session;\n"
                + "import org.hibernate.SessionFactory;\n" + "import org.hibernate.cfg.Configuration;\n";

        assertEquals(expectedText, generatedText);

    }

    public void testGenerateClass() {

        HibernatePersistenceSystemClassGenerator hibernatePersistenceSystemClassGenerator = new HibernatePersistenceSystemClassGenerator();
        String generatedText = (String) ReflectionUtils.executePrivateMethod(hibernatePersistenceSystemClassGenerator,
                "generateClass", new Class[] {}, new Object[] {});

        String expectedText = "\n"
                + "public class PersistenceSystem {\n"
                + (String) ReflectionUtils.executePrivateMethod(hibernatePersistenceSystemClassGenerator,
                        "generateAttributes")
                + "\n"
                + (String) ReflectionUtils.executePrivateMethod(hibernatePersistenceSystemClassGenerator,
                        "generateConstructorMethod")
                + "\n"
                + (String) ReflectionUtils.executePrivateMethod(hibernatePersistenceSystemClassGenerator,
                        "generateGetPersistenceSession") + "\n" + "}";

        assertEquals(expectedText, generatedText);
    }

    public void testGenerateAttributes() {

        HibernatePersistenceSystemClassGenerator hibernatePersistenceSystemClassGenerator = new HibernatePersistenceSystemClassGenerator();
        String generatedText = (String) ReflectionUtils.executePrivateMethod(hibernatePersistenceSystemClassGenerator,
                "generateAttributes");

        String expectedText = "\n" + "	Configuration hibernateConfiguration;\n"
                + "	SessionFactory hibernateSessionFactory;\n";

        assertEquals(expectedText, generatedText);
    }

    public void testGenerateGetPersistenceSession() {

        HibernatePersistenceSystemClassGenerator hibernatePersistenceSystemClassGenerator = new HibernatePersistenceSystemClassGenerator();
        String generatedText = (String) ReflectionUtils.executePrivateMethod(hibernatePersistenceSystemClassGenerator,
                "generateConstructorMethod");

        String expectedText = "	public PersistenceSystem() {\n" + "		try {\n"
                + "			// Create the SessionFactory from hibernate.cfg.xml\n"
                + "			Configuration configuration = new Configuration();\n"
                + "			hibernateSessionFactory = configuration.configure().buildSessionFactory();\n"
                + "		} catch (Throwable ex) {\n" + "			System.err.println(" + '"'
                + "Persistence System creation failed." + '"' + ");\n"
                + "			throw new ExceptionInInitializerError(ex);\n" + "		}\n" + "	}\n";

        assertEquals(expectedText, generatedText);

    }

    public void testGenerateConstructorMethod() {

        HibernatePersistenceSystemClassGenerator hibernatePersistenceSystemClassGenerator = new HibernatePersistenceSystemClassGenerator();
        String generatedText = (String) ReflectionUtils.executePrivateMethod(hibernatePersistenceSystemClassGenerator,
                "generateGetPersistenceSession");

        String expectedText = "	public PersistenceSession getPersistenceSession(){\n"
                + "		Session hibernateSession = hibernateSessionFactory.openSession();\n"
                + "		return new PersistenceSession(hibernateSession);\n" + "	}\n";

        assertEquals(expectedText, generatedText);

    }
}
