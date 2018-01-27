package com.hexacta.booster.codegeneration.persistence.layer.hibernate;

import java.util.Iterator;

import com.hexacta.booster.codegeneration.configuration.ClassList;
import com.hexacta.booster.codegeneration.configuration.CodeGeneratorConfiguration;
import com.hexacta.booster.codegeneration.metamodel.Class;
import com.hexacta.booster.codegeneration.persistence.layer.PersistenceSession;

/**
 * 
 */
public class PersistenceSessionWithoutRestrictionsGenerator {

    /**
     * This class generates the layer of persistence without restrictions.
     */

    public PersistenceSessionWithoutRestrictionsGenerator() {
        /**
         * Empty constructor.
         */
    }

    public PersistenceSession generate(final CodeGeneratorConfiguration codeGeneratorConfiguration) {

        PersistenceSession persistenceSession = new PersistenceSession();
        generatePackage(persistenceSession);
        generateImports(persistenceSession, codeGeneratorConfiguration);
        generateClass(persistenceSession, codeGeneratorConfiguration);

        return persistenceSession;
    }

    private void generatePackage(final PersistenceSession aPersistenceLayer) {

        aPersistenceLayer.append("package " + /*
                                               * aCodeGeneratorConfiguration.getProyectPackageName
                                               * () +
                                               */"persistence;\n");
    }

    private void generateImports(final PersistenceSession aPersistenceLayer,
            final CodeGeneratorConfiguration aCodeGeneratorConfiguration) {

        ClassList classList = aCodeGeneratorConfiguration.getClassList();
        /*
         * String daoPackage =
         * aCodeGeneratorConfiguration.getProyectPackageName() + ".daos";
         */

        String imports = "\n" + "import java.util.Collection;\n" + "import java.util.HashMap;\n"
                + "import java.util.Map;\n";

        for (Iterator<Class> iterator = classList.iterator(); iterator.hasNext();) {
            Class aClass = iterator.next();
            imports = imports + "import " + aClass.getName() + ";\n";
        }

        /*
         * for (Iterator<Class> iterator = classList.getIterator();
         * iterator.hasNext();) { Class aClass = iterator.next(); imports =
         * imports + "import " + daoPackage + "." + aClass.getSimpleName() +
         * "DAO;" + "\n"; }
         */

        aPersistenceLayer.append(imports);

    }

    private void generateClass(final PersistenceSession aPersistenceLayer,
            final CodeGeneratorConfiguration aCodeGeneratorConfiguration) {

        String classText = "\n" + "public class " + "PersistenceLayer {" + "\n"
                + generateAttributes(aCodeGeneratorConfiguration) + "\n"
                + generateConstructor(aCodeGeneratorConfiguration) + "\n" + generatePersistMethods() + "\n"
                + generateDeleteMethods() + "\n" + generateUpdateMethods(aCodeGeneratorConfiguration) + "\n"
                + generateFindMethods() + "\n" + generateFindAllMethods(aCodeGeneratorConfiguration) + "\n" + "}";

        aPersistenceLayer.append(classText);

    }

    private String generateAttributes(final CodeGeneratorConfiguration aCodeGeneratorConfiguration) {

        ClassList classList = aCodeGeneratorConfiguration.getClassList();

        String attributes = "\n";

        for (Iterator<Class> iterator = classList.iterator(); iterator.hasNext();) {
            Class aClass = iterator.next();
            attributes = attributes + "private " + aClass.getSimpleName() + "DAO "
                    + aClass.getSimpleName().toLowerCase() + "DAO" + ";\n";
        }
        attributes = attributes + "\n" + "private MappingAdministrator mappingAdministrator;\n"
                + "private BusinessObjectDataObjectMapping businessObjectDataObjectMapping;\n\n";
        return attributes;

    }

    private String generateConstructor(final CodeGeneratorConfiguration aCodeGeneratorConfiguration) {

        String constructorString = "\n" + "	public " + "PersistenceLayer" + "(){\n";
        ClassList aClassList = aCodeGeneratorConfiguration.getClassList();

        for (Iterator<Class> iterator = aClassList.iterator(); iterator.hasNext();) {
            Class aClass = iterator.next();
            constructorString = constructorString + "		" + aClass.getSimpleName().toLowerCase() + "DAO = new "
                    + aClass.getSimpleName() + "DAO();\n";
        }
        constructorString = constructorString + "		mappingAdministrator = new MappingAdministrator();\n"
                + "		businessObjectDataObjectMapping = new BusinessObjectDataObjectMapping();";

        constructorString = constructorString + "	}\n";

        return constructorString;
    }

    private String generatePersistMethods() {

        String persistMethodsString = "\n";
        // ClassList aClassList = aCodeGeneratorConfiguration.getClassList();
        // for (Iterator<Class> iterator = aClassList.getIterator();
        // iterator.hasNext();) {
        // Class aClass = iterator.next();
        // persistMethodsString = persistMethodsString +
        // "	public void persist("+ aClass.getSimpleName() + " " +
        // aClass.getSimpleName().toLowerCase() + ") {\n"
        // + "		" +/*aCodeGeneratorConfiguration.getProyectPackageName() +*/
        // "persistence.dataobject." + aClass.getSimpleName() +
        // " dataObject = ("
        // +/*aCodeGeneratorConfiguration.getProyectPackageName() +*/
        // "persistence.dataobject." + aClass.getSimpleName() +
        // ") mappingAdministrator.createOrUpdateDataObject(" +
        // aClass.getSimpleName().toLowerCase() +
        // ",businessObjectDataObjectMapping);\n"
        // + "		" + aClass.getSimpleName().toLowerCase() + "DAO.create(" +
        // "dataObject"/*aClass.getSimpleName().toLowerCase()*/ + ");\n"
        // + "	}\n";
        // }
        return persistMethodsString;
    }

    private String generateDeleteMethods() {

        String deleteMethodsString = "\n";
        // ClassList aClassList = aCodeGeneratorConfiguration.getClassList();
        //		
        // for (Iterator<Class> iterator = aClassList.getIterator();
        // iterator.hasNext();) {
        // Class aClass = iterator.next();
        //			
        // deleteMethodsString = deleteMethodsString + "	public void delete("+
        // aClass.getSimpleName() + " " + aClass.getSimpleName().toLowerCase() +
        // ") {\n"
        // + "		" /*+ aCodeGeneratorConfiguration.getProyectPackageName() */+
        // "persistence.dataobject." + aClass.getSimpleName() +
        // " dataObject = ("
        // + /*aCodeGeneratorConfiguration.getProyectPackageName() +*/
        // "persistence.dataobject." + aClass.getSimpleName() +
        // ") mappingAdministrator.getAndDeleteDataObject(" +
        // aClass.getSimpleName().toLowerCase() +
        // ",businessObjectDataObjectMapping);\n"
        // + "		" + aClass.getSimpleName().toLowerCase() + "DAO.delete(" +
        // "dataObject"/*aClass.getSimpleName().toLowerCase()*/ + ");\n"
        // + "	}\n";
        //			
        // }

        return deleteMethodsString;

    }

    private String generateFindMethods() {

        String findMethods = "\n";
        // ClassList aClassList = aCodeGeneratorConfiguration.getClassList();

        // for (Iterator<Class> iterator = aClassList.getIterator();
        // iterator.hasNext();) {
        // Class aClass = iterator.next();
        //			
        // findMethods = findMethods + "	public Collection find("+
        // aClass.getSimpleName() + " " + aClass.getSimpleName().toLowerCase() +
        // ") {\n"

        /*
         * + "		" +aCodeGeneratorConfiguration.getProyectPackageName() +
         * ".persistence.dataobject." + aClass.getSimpleName() +
         * " dataObject = ("
         * +aCodeGeneratorConfiguration.getProyectPackageName() +
         * ".persistence.dataobject." + aClass.getSimpleName() +
         * ") mappingAdministrator.createOrUpdateDataObject(" +
         * aClass.getSimpleName().toLowerCase() +
         * ",businessObjectDataObjectMapping);\n" +
         * "		Collection dataObjects = " + aClass.getSimpleName().toLowerCase()
         * + "DAO.findByExample(dataObject);\n" +
         * "		return mappingAdministrator.createOrUpdateAndGetBusinessObjects(dataObjects, businessObjectDataObjectMapping);\n"
         * + "	}\n";
         */
        // }
        return findMethods;
    }

    private String generateFindAllMethods(final CodeGeneratorConfiguration aCodeGeneratorConfiguration) {

        String findMethods = "\n";
        ClassList aClassList = aCodeGeneratorConfiguration.getClassList();

        for (Iterator<Class> iterator = aClassList.iterator(); iterator.hasNext();) {
            Class aClass = iterator.next();

            findMethods = findMethods + "	public Collection findAll" + aClass.getSimpleName() + "() {\n"
                    + "		return mappingAdministrator.createOrUpdateAndGetBusinessObjects("
                    + aClass.getSimpleName().toLowerCase() + "DAO.findAll(), businessObjectDataObjectMapping);\n"
                    + "	}\n";
        }

        return findMethods;
    }

    private String generateUpdateMethods(final CodeGeneratorConfiguration aCodeGeneratorConfiguration) {

        String updateMethods = "\n";
        ClassList aClassList = aCodeGeneratorConfiguration.getClassList();

        for (Iterator<?> iterator = aClassList.iterator(); iterator.hasNext();) {
            Class aClass = (Class) iterator.next();

            updateMethods = updateMethods + "	public void update(" + aClass.getSimpleName() + " "
                    + aClass.getSimpleName().toLowerCase() + ") {\n"
                    // + "		"
                    // +aCodeGeneratorConfiguration.getProyectPackageName() +
                    // ".persistence.dataobject." + aClass.getSimpleName() +
                    // " dataObject = ("
                    // +aCodeGeneratorConfiguration.getProyectPackageName() +
                    // ".persistence.dataobject." + aClass.getSimpleName() +
                    // ") mappingAdministrator.createOrUpdateDataObject(" +
                    // aClass.getSimpleName().toLowerCase() +
                    // ",businessObjectDataObjectMapping);\n"
                    + "		" + aClass.getSimpleName().toLowerCase() + "DAO.update(" + "dataObject" + ");\n" + "	}\n";

        }

        return updateMethods;
    }

    /**
     * The end
     */
}
