package com.hexacta.booster.codegeneration.persistence.layer.hibernate;

/**
 * 
 */
public class HibernatePersistenceSystemClassGenerator {

    public HibernatePersistenceSystemClassGenerator() {
    }

    public String generate() {
        return generatePackage() + generateImports() + generateClass();
    }

    private String generateClass() {
        return "\n" + "public class PersistenceSystem {\n" + generateAttributes() + "\n" + generateConstructorMethod()
                + "\n" + generateGetPersistenceSession() + "\n" + "}";
    }

    private String generateConstructorMethod() {
        return "	public PersistenceSystem() {\n" + "		try {\n"
                + "			// Create the SessionFactory from hibernate.cfg.xml\n"
                + "			Configuration configuration = new Configuration();\n"
                + "			hibernateSessionFactory = configuration.configure().buildSessionFactory();\n"
                + "		} catch (Throwable ex) {\n" + "			System.err.println(" + '"'
                + "Persistence System creation failed." + '"' + ");\n"
                + "			throw new ExceptionInInitializerError(ex);\n" + "		}\n" + "	}\n";
    }

    private String generateGetPersistenceSession() {
        return "	public PersistenceSession getPersistenceSession(){\n"
                + "		Session hibernateSession = hibernateSessionFactory.openSession();\n"
                + "		return new PersistenceSession(hibernateSession);\n" + "	}\n";
    }

    private String generatePackage() {
        return "package " + "persistence;\n";
    }

    private String generateAttributes() {
        return "\n" + "	Configuration hibernateConfiguration;\n" + "	SessionFactory hibernateSessionFactory;\n";
    }

    private String generateImports() {
        return "import java.util.HashMap;\n" + "import org.hibernate.Session;\n"
                + "import org.hibernate.SessionFactory;\n" + "import org.hibernate.cfg.Configuration;\n";
    }
}
