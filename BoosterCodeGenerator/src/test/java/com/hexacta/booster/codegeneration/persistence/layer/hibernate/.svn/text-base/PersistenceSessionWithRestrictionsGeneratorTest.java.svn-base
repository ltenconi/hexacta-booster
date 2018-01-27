package com.hexacta.booster.codegeneration.persistence.layer.hibernate;

import junit.framework.TestCase;
import ar.com.hexacta.utils.reflection.ReflectionUtils;

import com.hexacta.booster.codegeneration.configuration.CodeGeneratorConfiguration;
import com.hexacta.booster.codegeneration.persistence.layer.PersistenceSession;
import com.hexacta.booster.codegeneration.persistence.strategy.NoCuttingStrategy;

/**
 * 
 * @author jmarquez
 * 
 */
public class PersistenceSessionWithRestrictionsGeneratorTest extends TestCase {

    public void testCreatePersistenceSessionWithRestrictionsGenerator() {
        PersistenceSessionWithRestrictionsGenerator persistenceSessionWithRestrictionsGenerator = new PersistenceSessionWithRestrictionsGenerator();

        assertNotNull(persistenceSessionWithRestrictionsGenerator);
    }

    public void testGenerate() {
        PersistenceSessionWithRestrictionsGenerator aGenerator = new PersistenceSessionWithRestrictionsGenerator();

        CodeGeneratorConfiguration aCodeGeneratorConfiguration = new CodeGeneratorConfiguration(null, null, null, null,
                null, null, null, null, new NoCuttingStrategy());
        PersistenceSession generatedPersistenceSession = aGenerator.generate(aCodeGeneratorConfiguration);

        PersistenceSession expectedPersistenceSession = new PersistenceSession();
        ReflectionUtils.executePrivateMethod(aGenerator, "generatePackage", new Class[] { PersistenceSession.class, },
                new Object[] { expectedPersistenceSession });

        ReflectionUtils.executePrivateMethod(aGenerator, "generateImports", new Class[] { PersistenceSession.class },
                new Object[] { expectedPersistenceSession });

        ReflectionUtils.executePrivateMethod(aGenerator, "generateMethods", new Class[] { PersistenceSession.class },
                new Object[] { expectedPersistenceSession });

        assertEquals(expectedPersistenceSession.getText(), generatedPersistenceSession.getText());
    }

    public void testGeneratePackage() {

        PersistenceSessionWithRestrictionsGenerator aGenerator = new PersistenceSessionWithRestrictionsGenerator();

        PersistenceSession generatedPersistenceSession = new PersistenceSession();
        ReflectionUtils.executePrivateMethod(aGenerator, "generatePackage", new Class[] { PersistenceSession.class },
                new Object[] { generatedPersistenceSession });

        String expectedString = "package " + "persistence;\n\n";

        assertEquals(expectedString, generatedPersistenceSession.getText());
    }

    public void testGenerateImports() {

        PersistenceSessionWithRestrictionsGenerator aGenerator = new PersistenceSessionWithRestrictionsGenerator();
        PersistenceSession generatedPersistenceSession = new PersistenceSession();

        ReflectionUtils.executePrivateMethod(aGenerator, "generateImports", new Class[] { PersistenceSession.class },
                new Object[] { generatedPersistenceSession });

        String expectedString = "import java.util.Collection;\n" + "import org.hibernate.Criteria;\n"
                + "import org.hibernate.Session;\n" + "import org.hibernate.Transaction;\n"
                + "import org.hibernate.criterion.Example;\n" + "\n";

        assertEquals(expectedString, generatedPersistenceSession.getText());

    }

    public void testGenerateMethods() {

        PersistenceSessionWithRestrictionsGenerator aGenerator = new PersistenceSessionWithRestrictionsGenerator();
        PersistenceSession generatedPersistenceSession = new PersistenceSession();

        ReflectionUtils.executePrivateMethod(aGenerator, "generateMethods", new Class[] { PersistenceSession.class },
                new Object[] { generatedPersistenceSession });

        PersistenceSession expectedPersistenceSession = new PersistenceSession();
        ReflectionUtils.executePrivateMethod(aGenerator, "generateAttributes",
                new Class[] { PersistenceSession.class }, new Object[] { expectedPersistenceSession });
        ReflectionUtils.executePrivateMethod(aGenerator, "generateConstructorMethod",
                new Class[] { PersistenceSession.class }, new Object[] { expectedPersistenceSession });
        ReflectionUtils.executePrivateMethod(aGenerator, "generatePersistMethod",
                new Class[] { PersistenceSession.class }, new Object[] { expectedPersistenceSession });
        ReflectionUtils.executePrivateMethod(aGenerator, "generateDeleteMethod",
                new Class[] { PersistenceSession.class }, new Object[] { expectedPersistenceSession });
        ReflectionUtils.executePrivateMethod(aGenerator, "generateUpdateMethod",
                new Class[] { PersistenceSession.class }, new Object[] { expectedPersistenceSession });
        ReflectionUtils.executePrivateMethod(aGenerator, "generateBeginUnitOfWorkMethod",
                new Class[] { PersistenceSession.class }, new Object[] { expectedPersistenceSession });
        ReflectionUtils.executePrivateMethod(aGenerator, "generateCommitUnitOfWorkMethod",
                new Class[] { PersistenceSession.class }, new Object[] { expectedPersistenceSession });
        ReflectionUtils.executePrivateMethod(aGenerator, "generateRollbackUnitOfWorkMethod",
                new Class[] { PersistenceSession.class }, new Object[] { expectedPersistenceSession });
        ReflectionUtils.executePrivateMethod(aGenerator, "generateFlushMethod",
                new Class[] { PersistenceSession.class }, new Object[] { expectedPersistenceSession });
        ReflectionUtils.executePrivateMethod(aGenerator, "generateCloseMethod",
                new Class[] { PersistenceSession.class }, new Object[] { expectedPersistenceSession });
        ReflectionUtils.executePrivateMethod(aGenerator, "generateFindMethod",
                new Class[] { PersistenceSession.class }, new Object[] { expectedPersistenceSession });
        ReflectionUtils.executePrivateMethod(aGenerator, "generateFindAllMethod",
                new Class[] { PersistenceSession.class }, new Object[] { expectedPersistenceSession });

        ReflectionUtils.executePrivateMethod(aGenerator, "generateCreateCriteriaMethod",
                new Class[] { PersistenceSession.class }, new Object[] { expectedPersistenceSession });

        String expectedString = "public class " + "PersistenceSession {" + "\n\n"
                + expectedPersistenceSession.getText() + "}\n";

        assertEquals(expectedString, generatedPersistenceSession.getText());

    }

    public void testGenerateAttributes() throws Exception {
        PersistenceSessionWithRestrictionsGenerator aGenerator = new PersistenceSessionWithRestrictionsGenerator();

        PersistenceSession generatedText = new PersistenceSession();

        ReflectionUtils.executePrivateMethod(aGenerator, "generateAttributes",
                new Class[] { PersistenceSession.class }, new Object[] { generatedText });
        assertEquals("	private Session hibernateSession;\n" + "	private Transaction currentTransaction;\n"
                + "	private Transaction unitOfWorkTransaction;\n" + "	\n", generatedText.getText());

    }

    public void testGenerateConstructor() throws Exception {
        PersistenceSessionWithRestrictionsGenerator aGenerator = new PersistenceSessionWithRestrictionsGenerator();

        PersistenceSession generatedText = new PersistenceSession();

        ReflectionUtils.executePrivateMethod(aGenerator, "generateConstructorMethod",
                new Class[] { PersistenceSession.class }, new Object[] { generatedText });
        assertEquals("	public PersistenceSession(Session hibernateSession) {\n"
                + "		this.hibernateSession = hibernateSession;\n"
                + "		currentTransaction = this.hibernateSession.beginTransaction();\n" + "	}\n\n", generatedText
                .getText());
    }

    public void testGeneratePersistMethods() throws Exception {
        PersistenceSessionWithRestrictionsGenerator aGenerator = new PersistenceSessionWithRestrictionsGenerator();

        PersistenceSession generatedText = new PersistenceSession();

        ReflectionUtils.executePrivateMethod(aGenerator, "generatePersistMethod",
                new Class[] { PersistenceSession.class }, new Object[] { generatedText });
        assertEquals("	public void persist(Object anObject) {\n" + "			hibernateSession.save(anObject);\n" + "	}\n\n",
                generatedText.getText());
    }

    public void testDeleteMethod() throws Exception {
        PersistenceSessionWithRestrictionsGenerator aGenerator = new PersistenceSessionWithRestrictionsGenerator();

        PersistenceSession generatedText = new PersistenceSession();

        ReflectionUtils.executePrivateMethod(aGenerator, "generateDeleteMethod",
                new Class[] { PersistenceSession.class }, new Object[] { generatedText });
        assertEquals("	public void delete(Object anObject) {\n" + "			hibernateSession.delete(anObject);\n" + "	}\n\n",
                generatedText.getText());

    }

    public void testUpdateMethod() throws Exception {
        PersistenceSessionWithRestrictionsGenerator aGenerator = new PersistenceSessionWithRestrictionsGenerator();

        PersistenceSession generatedText = new PersistenceSession();

        ReflectionUtils.executePrivateMethod(aGenerator, "generateUpdateMethod",
                new Class[] { PersistenceSession.class }, new Object[] { generatedText });
        assertEquals("	public void update(Object anObject) {\n" + "			hibernateSession.update(anObject);\n" + "	}\n\n",
                generatedText.getText());

    }

    public void testGenerateBeginUnitOfWorkMethod() throws Exception {
        PersistenceSessionWithRestrictionsGenerator aGenerator = new PersistenceSessionWithRestrictionsGenerator();

        PersistenceSession generatedText = new PersistenceSession();

        ReflectionUtils.executePrivateMethod(aGenerator, "generateBeginUnitOfWorkMethod",
                new Class[] { PersistenceSession.class }, new Object[] { generatedText });
        assertEquals("	public void beginUnitOfWork() {\n"
                + "			unitOfWorkTransaction = hibernateSession.beginTransaction();\n" + "	}\n\n", generatedText
                .getText());

    }

    public void testGenerateCommitUnitOfWorkMethod() throws Exception {
        PersistenceSessionWithRestrictionsGenerator aGenerator = new PersistenceSessionWithRestrictionsGenerator();

        PersistenceSession generatedText = new PersistenceSession();

        ReflectionUtils.executePrivateMethod(aGenerator, "generateCommitUnitOfWorkMethod",
                new Class[] { PersistenceSession.class }, new Object[] { generatedText });
        assertEquals("	public void commitUnitOfWork() {\n" + "			unitOfWorkTransaction.commit();\n" + "	}\n\n",
                generatedText.getText());

    }

    public void testGenerateRollbackUnitOfWorkMethod() throws Exception {
        PersistenceSessionWithRestrictionsGenerator aGenerator = new PersistenceSessionWithRestrictionsGenerator();

        PersistenceSession generatedText = new PersistenceSession();

        ReflectionUtils.executePrivateMethod(aGenerator, "generateRollbackUnitOfWorkMethod",
                new Class[] { PersistenceSession.class }, new Object[] { generatedText });
        assertEquals("	public void rollbackUnitOfWork() {\n" + "			unitOfWorkTransaction.rollback();\n" + "	}\n\n",
                generatedText.getText());

    }

    public void testGenerateFlushMethod() throws Exception {
        PersistenceSessionWithRestrictionsGenerator aGenerator = new PersistenceSessionWithRestrictionsGenerator();

        PersistenceSession generatedText = new PersistenceSession();

        ReflectionUtils.executePrivateMethod(aGenerator, "generateFlushMethod",
                new Class[] { PersistenceSession.class }, new Object[] { generatedText });
        assertEquals("	public void flush() {\n" + "			currentTransaction.commit();\n"
                + "			currentTransaction = hibernateSession.beginTransaction();\n" + "	}\n\n", generatedText.getText());

    }

    public void testGenerateCloseMethod() throws Exception {

        PersistenceSessionWithRestrictionsGenerator aGenerator = new PersistenceSessionWithRestrictionsGenerator();

        PersistenceSession generatedText = new PersistenceSession();

        ReflectionUtils.executePrivateMethod(aGenerator, "generateCloseMethod",
                new Class[] { PersistenceSession.class }, new Object[] { generatedText });
        assertEquals("	public void close() {\n" + "			hibernateSession.close();\n" + "	}\n\n", generatedText.getText());
    }

    public void testGenerateFindMethod() throws Exception {

        PersistenceSessionWithRestrictionsGenerator aGenerator = new PersistenceSessionWithRestrictionsGenerator();

        PersistenceSession generatedText = new PersistenceSession();

        ReflectionUtils.executePrivateMethod(aGenerator, "generateFindMethod",
                new Class[] { PersistenceSession.class }, new Object[] { generatedText });
        assertEquals("	public Collection find(Object anObject) {\n"
                + "			Criteria criteria = hibernateSession.createCriteria(anObject.getClass());\n"
                + "			Example exampleRestriction = Example.create(anObject);\n"
                + "			criteria.add(exampleRestriction);\n" + "			return criteria.list();\n" + "	}\n\n", generatedText
                .getText());

    }

    public void testGenerateFinAllMethod() throws Exception {

        PersistenceSessionWithRestrictionsGenerator aGenerator = new PersistenceSessionWithRestrictionsGenerator();
        PersistenceSession generatedText = new PersistenceSession();

        ReflectionUtils.executePrivateMethod(aGenerator, "generateFindAllMethod",
                new Class[] { PersistenceSession.class }, new Object[] { generatedText });
        assertEquals("	public Collection findAll(Object anObject) {\n"
                + "			Criteria criteria = hibernateSession.createCriteria(anObject.getClass());\n"
                + "			return criteria.list();\n" + "	}\n\n", generatedText.getText());
    }

    public void testGenerateCreateCriteria() throws Exception {

        PersistenceSessionWithRestrictionsGenerator aGenerator = new PersistenceSessionWithRestrictionsGenerator();
        PersistenceSession generatedText = new PersistenceSession();

        ReflectionUtils.executePrivateMethod(aGenerator, "generateCreateCriteriaMethod",
                new Class[] { PersistenceSession.class }, new Object[] { generatedText });
        assertEquals("	public SearchCriteria createCriteria(Class aClass){\n"
                + "		return new SearchCriteria(hibernateSession.createCriteria(aClass));\n" + "	}\n", generatedText
                .getText());

    }
}
