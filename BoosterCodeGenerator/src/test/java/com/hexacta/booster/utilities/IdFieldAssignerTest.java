package com.hexacta.booster.utilities;

import junit.framework.TestCase;
import test.hexacta.booster.providers.CodeGeneratorConfigurationProvider;

import com.hexacta.booster.codegeneration.configuration.ClassFinder;
import com.hexacta.booster.codegeneration.configuration.ClassIdMap;
import com.hexacta.booster.codegeneration.configuration.ClassList;
import com.hexacta.booster.codegeneration.configuration.CodeGeneratorConfiguration;
import com.hexacta.booster.codegeneration.configuration.NotSupportedClassException;
import com.hexacta.booster.codegeneration.metamodel.Class;
import com.hexacta.booster.project.configuration.JavaProjectType;

/**
 * 
 */
public class IdFieldAssignerTest extends TestCase {

    public void testCreation() {
        IdFieldNameAssigner idFieldNameAssigner = new IdFieldNameAssigner();
        assertNotNull(idFieldNameAssigner);
    }

    public void testAssignClassList() throws NotSupportedClassException {

        CodeGeneratorConfiguration codeGeneratorConfiguration = new CodeGeneratorConfigurationProvider()
                .getCodeGeneratorConfiguration();

        IdFieldNameAssigner idFieldNameAssigner = new IdFieldNameAssigner();

        Class student = ClassFinder.find("test.model.Student", codeGeneratorConfiguration.getClassList(),
                new JavaProjectType());
        Class person = ClassFinder.find("test.model.Person", codeGeneratorConfiguration.getClassList(),
                new JavaProjectType());
        Class address = ClassFinder.find("test.model.Address", codeGeneratorConfiguration.getClassList(),
                new JavaProjectType());
        Class employee = ClassFinder.find("test.model.Employee", codeGeneratorConfiguration.getClassList(),
                new JavaProjectType());

        ClassList classNameList = new ClassList();
        classNameList.addClass(person);
        classNameList.addClass(address);
        classNameList.addClass(employee);
        classNameList.addClass(student);

        ClassIdMap classIdMap = idFieldNameAssigner.assign(classNameList, new JavaProjectType());

        assertEquals("id", classIdMap.getId(person.getName()));
        assertEquals("id", classIdMap.getId(address.getName()));
        assertEquals("id", classIdMap.getId(employee.getName()));
        assertEquals("id", classIdMap.getId(student.getName()));

    }

}
