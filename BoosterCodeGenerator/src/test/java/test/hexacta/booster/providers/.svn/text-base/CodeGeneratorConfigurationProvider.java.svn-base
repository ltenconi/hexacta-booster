package test.hexacta.booster.providers;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.hexacta.booster.codegeneration.configuration.ClassIdMap;
import com.hexacta.booster.codegeneration.configuration.ClassList;
import com.hexacta.booster.codegeneration.configuration.CodeGeneratorConfiguration;
import com.hexacta.booster.codegeneration.configuration.CollectionVarTypeMap;
import com.hexacta.booster.codegeneration.configuration.DirectoryInfo;
import com.hexacta.booster.codegeneration.configuration.HibernateParams;
import com.hexacta.booster.codegeneration.configuration.Jar;
import com.hexacta.booster.codegeneration.configuration.PersistableMetaModelAttributes;
import com.hexacta.booster.codegeneration.metamodel.MetaModelGeneratorForJavaModel;
import com.hexacta.booster.codegeneration.metamodel.NotAnEntityClassException;
import com.hexacta.booster.codegeneration.metamodel.NotExistAttributeException;
import com.hexacta.booster.codegeneration.metamodel.PersistableMetaModelBuilder;
import com.hexacta.booster.codegeneration.persistence.strategy.NoCuttingStrategy;
import com.hexacta.booster.exception.ModelClassNotFound;

/**
 * 
 */
public class CodeGeneratorConfigurationProvider {

    HibernateParams hibernateParams = new HibernateParams("jdbc:mysql://hxbws014/anrdb2", "com.mysql.jdbc.Driver",
            "org.hibernate.dialect.MySQLDialect", "root", "admin", 0);

    /**
     * Log4j logger.
     */
    static final Logger logger = Logger.getLogger(CodeGeneratorConfigurationProvider.class.getSimpleName());

    public CodeGeneratorConfigurationProvider() {
    }

    private ClassList getClassList() {

        MetaModelGeneratorForJavaModel aMetaModelGenerator = new MetaModelGeneratorForJavaModel();
        List<java.lang.Class<?>> aClassList = new ArrayList<java.lang.Class<?>>();
        aClassList.add(test.model.House.class);
        aClassList.add(test.model.Student.class);
        aClassList.add(test.model.Address.class);
        aClassList.add(test.model.Person.class);
        aClassList.add(test.model.Employee.class);
        aClassList.add(test.model.Company.class);
        aClassList.add(test.model.Room.class);
        aClassList.add(test.model.Course.class);
        aClassList.add(test.model.Door.class); // NEW!!

        ClassList classList = aMetaModelGenerator.generate(aClassList);

        return classList;
    }

    private ClassIdMap getClassIdMap() {

        ClassIdMap classIdMap = new ClassIdMap();
        classIdMap.putId("test.model.House", "houseId");
        classIdMap.putId("test.model.Company", "companyId");
        classIdMap.putId("test.model.Student", "personId");
        classIdMap.putId("test.model.Person", "personId");
        classIdMap.putId("test.model.Address", "addressId");
        classIdMap.putId("test.model.Employee", "personId");
        classIdMap.putId("test.model.Room", "roomId");
        classIdMap.putId("test.model.Course", "courseId");
        classIdMap.putId("test.model.Door", "doorId");
        classIdMap.putId("test.model.Persona", "id");

        return classIdMap;

    }

    public CodeGeneratorConfiguration getCodeGeneratorConfiguration() {

        CollectionVarTypeMap collectionVarTypeMap = new CollectionVarTypeMap();

        DirectoryInfo directoryInfo = new DirectoryInfo();
        directoryInfo.setModelPath("./tests/");
        directoryInfo.setMappingsPath("./tests/test/model/resources/");

        ClassList metaModel = getClassList();
        ClassList persistableMetaModel = null;
        try {

            persistableMetaModel = PersistableMetaModelBuilder.build(getClassList(),
                    new PersistableMetaModelAttributes());

        } catch (NotExistAttributeException e) {
            logger.error(e);
        } catch (ModelClassNotFound e) {
            logger.error(e);
        } catch (NotAnEntityClassException e) {
            logger.error(e);
        }

        CodeGeneratorConfiguration codeGeneratorConfiguration = new CodeGeneratorConfiguration(metaModel, new Jar(),
                hibernateParams, getClassIdMap(), collectionVarTypeMap, directoryInfo, getClassList(),
                persistableMetaModel, new NoCuttingStrategy());

        return codeGeneratorConfiguration;
    }

    public CodeGeneratorConfiguration getCodeGeneratorConfiguration(final String source, final String destiny) {

        CollectionVarTypeMap collectionVarTypeMap = new CollectionVarTypeMap();

        DirectoryInfo directoryInfo = new DirectoryInfo();
        directoryInfo.setModelPath(source);
        directoryInfo.setMappingsPath(destiny);

        ClassList metaModel = getClassList();
        ClassList persistableMetaModel = null;
        try {
            persistableMetaModel = PersistableMetaModelBuilder.build(getClassList(),
                    new PersistableMetaModelAttributes());
        } catch (NotExistAttributeException e) {
            logger.error(e);
        } catch (ModelClassNotFound e) {
            logger.error(e);
        } catch (NotAnEntityClassException e) {
            logger.error(e);
        }

        CodeGeneratorConfiguration codeGeneratorConfiguration = new CodeGeneratorConfiguration(metaModel, new Jar(),
                hibernateParams, getClassIdMap(), collectionVarTypeMap, directoryInfo, getClassList(),
                persistableMetaModel, new NoCuttingStrategy());

        return codeGeneratorConfiguration;
    }

    public CodeGeneratorConfiguration getCodeGeneratorConfigurationForOneClass() {

        CollectionVarTypeMap collectionVarTypeMap = new CollectionVarTypeMap();

        DirectoryInfo directoryInfo = new DirectoryInfo();
        directoryInfo.setModelPath("./tests/");
        directoryInfo.setMappingsPath("./tests/test/model/resources/");
        directoryInfo.setViewPath("./src/test/java/test/view/generation/");

        ClassList metaModel = getOneClassList();
        ClassList persistableMetaModel = null;
        try {

            persistableMetaModel = PersistableMetaModelBuilder.build(getOneClassList(),
                    new PersistableMetaModelAttributes());

        } catch (NotExistAttributeException e) {
            logger.error(e);
        } catch (ModelClassNotFound e) {
            logger.error(e);
        } catch (NotAnEntityClassException e) {
            logger.error(e);
        }

        CodeGeneratorConfiguration codeGeneratorConfiguration = new CodeGeneratorConfiguration(metaModel, new Jar(),
                hibernateParams, getClassIdMap(), collectionVarTypeMap, directoryInfo, getOneClassList(),
                persistableMetaModel, new NoCuttingStrategy());

        return codeGeneratorConfiguration;
    }

    private ClassList getOneClassList() {

        MetaModelGeneratorForJavaModel aMetaModelGenerator = new MetaModelGeneratorForJavaModel();
        List<java.lang.Class<?>> aClassList = new ArrayList<java.lang.Class<?>>();

        aClassList.add(test.model.Persona.class);

        ClassList classList = aMetaModelGenerator.generate(aClassList);

        return classList;
    }

}
