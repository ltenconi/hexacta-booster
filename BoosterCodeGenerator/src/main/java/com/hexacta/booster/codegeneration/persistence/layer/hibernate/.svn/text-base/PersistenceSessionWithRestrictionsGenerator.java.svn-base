package com.hexacta.booster.codegeneration.persistence.layer.hibernate;

import com.hexacta.booster.codegeneration.configuration.CodeGeneratorConfiguration;
import com.hexacta.booster.codegeneration.persistence.layer.PersistenceSession;

/**
 * 
 */
public class PersistenceSessionWithRestrictionsGenerator {

    public PersistenceSession generate(final CodeGeneratorConfiguration codeGeneratorConfiguration) {

        PersistenceSession persistenceSession = new PersistenceSession();
        generatePackage(persistenceSession);
        generateImports(persistenceSession);
        generateMethods(persistenceSession);

        return persistenceSession;
    }

    private void generatePackage(final PersistenceSession aPersistenceSession) {
        aPersistenceSession.append("package " + "persistence;\n\n");
    }

    private void generateImports(final PersistenceSession aPersistenceSession) {
        aPersistenceSession.append("import java.util.Collection;\n");
        aPersistenceSession.append("import org.hibernate.Criteria;\n");
        aPersistenceSession.append("import org.hibernate.Session;\n");
        aPersistenceSession.append("import org.hibernate.Transaction;\n");
        aPersistenceSession.append("import org.hibernate.criterion.Example;\n");
        aPersistenceSession.append("\n");
    }

    private void generateMethods(final PersistenceSession aPersistenceSession) {

        aPersistenceSession.append("public class " + "PersistenceSession {" + "\n\n");
        generateAttributes(aPersistenceSession);
        generateConstructorMethod(aPersistenceSession);
        generatePersistMethod(aPersistenceSession);
        generateDeleteMethod(aPersistenceSession);
        generateUpdateMethod(aPersistenceSession);
        generateBeginUnitOfWorkMethod(aPersistenceSession);
        generateCommitUnitOfWorkMethod(aPersistenceSession);
        generateRollbackUnitOfWorkMethod(aPersistenceSession);
        generateFlushMethod(aPersistenceSession);
        generateCloseMethod(aPersistenceSession);
        generateFindMethod(aPersistenceSession);
        generateFindAllMethod(aPersistenceSession);
        generateCreateCriteriaMethod(aPersistenceSession);
        aPersistenceSession.append("}\n");
    }

    private void generateAttributes(final PersistenceSession aPersistenceSession) {
        aPersistenceSession.append("	private Session hibernateSession;\n");
        aPersistenceSession.append("	private Transaction currentTransaction;\n");
        aPersistenceSession.append("	private Transaction unitOfWorkTransaction;\n");
        aPersistenceSession.append("	\n");
    }

    private void generateConstructorMethod(final PersistenceSession aPersistenceSession) {

        aPersistenceSession.append("	public PersistenceSession(Session hibernateSession) {\n");
        aPersistenceSession.append("		this.hibernateSession = hibernateSession;\n");
        aPersistenceSession.append("		currentTransaction = this.hibernateSession.beginTransaction();\n");
        aPersistenceSession.append("	}\n\n");
    }

    private void generatePersistMethod(final PersistenceSession aPersistenceSession) {

        aPersistenceSession.append("	public void persist(Object anObject) {\n");
        aPersistenceSession.append("			hibernateSession.save(anObject);\n");
        aPersistenceSession.append("	}\n\n");

    }

    private void generateDeleteMethod(final PersistenceSession aPersistenceSession) {

        aPersistenceSession.append("	public void delete(Object anObject) {\n");
        aPersistenceSession.append("			hibernateSession.delete(anObject);\n");
        aPersistenceSession.append("	}\n\n");
    }

    private void generateUpdateMethod(final PersistenceSession aPersistenceSession) {

        aPersistenceSession.append("	public void update(Object anObject) {\n");
        aPersistenceSession.append("			hibernateSession.update(anObject);\n");
        aPersistenceSession.append("	}\n\n");

    }

    private void generateBeginUnitOfWorkMethod(final PersistenceSession aPersistenceSession) {

        aPersistenceSession.append("	public void beginUnitOfWork() {\n");
        aPersistenceSession.append("			unitOfWorkTransaction = hibernateSession.beginTransaction();\n");
        aPersistenceSession.append("	}\n\n");

    }

    private void generateCommitUnitOfWorkMethod(final PersistenceSession aPersistenceSession) {

        aPersistenceSession.append("	public void commitUnitOfWork() {\n");
        aPersistenceSession.append("			unitOfWorkTransaction.commit();\n");
        aPersistenceSession.append("	}\n\n");

    }

    private void generateRollbackUnitOfWorkMethod(final PersistenceSession aPersistenceSession) {

        aPersistenceSession.append("	public void rollbackUnitOfWork() {\n");
        aPersistenceSession.append("			unitOfWorkTransaction.rollback();\n");
        aPersistenceSession.append("	}\n\n");

    }

    private void generateFlushMethod(final PersistenceSession aPersistenceSession) {

        aPersistenceSession.append("	public void flush() {\n");
        aPersistenceSession.append("			currentTransaction.commit();\n");
        aPersistenceSession.append("			currentTransaction = hibernateSession.beginTransaction();\n");
        aPersistenceSession.append("	}\n\n");

    }

    private void generateCloseMethod(final PersistenceSession aPersistenceSession) {

        aPersistenceSession.append("	public void close() {\n");
        aPersistenceSession.append("			hibernateSession.close();\n");
        aPersistenceSession.append("	}\n\n");

    }

    private void generateFindMethod(final PersistenceSession aPersistenceSession) {

        aPersistenceSession.append("	public Collection find(Object anObject) {\n");
        aPersistenceSession.append("			Criteria criteria = hibernateSession.createCriteria(anObject.getClass());\n");
        aPersistenceSession.append("			Example exampleRestriction = Example.create(anObject);\n");
        aPersistenceSession.append("			criteria.add(exampleRestriction);\n");
        aPersistenceSession.append("			return criteria.list();\n");
        aPersistenceSession.append("	}\n\n");

    }

    private void generateFindAllMethod(final PersistenceSession aPersistenceSession) {

        aPersistenceSession.append("	public Collection findAll(Object anObject) {\n");
        aPersistenceSession.append("			Criteria criteria = hibernateSession.createCriteria(anObject.getClass());\n");
        aPersistenceSession.append("			return criteria.list();\n");
        aPersistenceSession.append("	}\n\n");
    }

    private void generateCreateCriteriaMethod(final PersistenceSession aPersistenceSession) {
        aPersistenceSession.append("	public SearchCriteria createCriteria(Class aClass){\n");
        aPersistenceSession.append("		return new SearchCriteria(hibernateSession.createCriteria(aClass));\n");
        aPersistenceSession.append("	}\n");
    }
}
