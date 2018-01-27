package com.hexacta.booster.codegeneration.persistence.layer;

/**
 * This class generates the MappingAdministrator class.
 */

import java.util.Iterator;

import com.hexacta.booster.codegeneration.configuration.CodeGeneratorConfiguration;
import com.hexacta.booster.codegeneration.metamodel.Class;

/**
 * 
 */
public class MappingAdministratorGenerator {

    public MappingAdministratorGenerator() {

    }

    public MappingAdministrator generate(final CodeGeneratorConfiguration aCodeGeneratorConfiguration) {

        MappingAdministrator mappingAdministrator = new MappingAdministrator();

        generatePackage(mappingAdministrator);
        generateImports(mappingAdministrator);
        generateClass(mappingAdministrator, aCodeGeneratorConfiguration);

        return mappingAdministrator;
    }

    private void generatePackage(final MappingAdministrator mappingAdministrator) {

        mappingAdministrator.append(
        // "package " +
                // aCodeGeneratorConfiguration.getProyectPackageName()+
                // ".persistence;");
                "package persistence;");
        mappingAdministrator.append("\n");

    }

    private void generateImports(final MappingAdministrator mappingAdministrator) {

        mappingAdministrator.append("\n");
        mappingAdministrator.append("import java.lang.reflect.Field;\n");
        mappingAdministrator.append("import java.util.ArrayList;\n");
        mappingAdministrator.append("import java.util.Collection;\n");
        mappingAdministrator.append("import java.util.Iterator;\n");
        mappingAdministrator.append("import java.util.List;\n");

    }

    private void generateClass(final MappingAdministrator mappingAdministrator,
            final CodeGeneratorConfiguration aCodeGeneratorConfiguration) {

        mappingAdministrator.append("\n");
        mappingAdministrator.append("public class MappingAdministrator {\n");

        generateConstructor(mappingAdministrator);
        generateCreateOrUpdateDataObject(mappingAdministrator);
        generateUpdateDataObjectColaborators(mappingAdministrator);
        generateIsBusinessClass(mappingAdministrator, aCodeGeneratorConfiguration);
        generateCreateOrUpdateBusinessObject(mappingAdministrator);
        generateUpdateBusinessObjectColaborators(mappingAdministrator);
        generateIsDataClass(mappingAdministrator, aCodeGeneratorConfiguration);
        generateGetAndDeleteDataObject(mappingAdministrator);
        generateCreateOrUpdateAndGetBusinessObjects(mappingAdministrator);

        mappingAdministrator.append("}");
    }

    private void generateConstructor(final MappingAdministrator mappingAdministrator) {
        mappingAdministrator.append("\n");
        mappingAdministrator.append("	public MappingAdministrator() {\n");
        mappingAdministrator.append("\n");
        mappingAdministrator.append("	}");
        mappingAdministrator.append("\n");

    }

    private void generateCreateOrUpdateDataObject(final MappingAdministrator mappingAdministrator) {

        mappingAdministrator.append("	\n");
        mappingAdministrator
                .append("	public Object createOrUpdateDataObject(Object businessObject,BusinessObjectDataObjectMapping businessObjectDataObjectMapping) {\n");
        mappingAdministrator.append("	\n");
        mappingAdministrator.append("		Object dataObject = null;\n");
        mappingAdministrator.append("	\n");
        mappingAdministrator.append("		if (businessObjectDataObjectMapping.containsBusinessObject(businessObject))\n");
        mappingAdministrator.append("			dataObject = businessObjectDataObjectMapping.getDataObject(businessObject);\n");
        mappingAdministrator.append("		else {\n");
        mappingAdministrator.append("			try {\n");
        mappingAdministrator.append("				dataObject = Class.forName(\n");
        mappingAdministrator.append("						businessObject.getClass().getPackage().getName()\n");
        mappingAdministrator.append("						+ \".persistence.dataobject.\"\n");
        mappingAdministrator.append("						+ businessObject.getClass().getSimpleName())\n");
        mappingAdministrator.append("						.newInstance();\n");
        mappingAdministrator.append("				businessObjectDataObjectMapping.put(businessObject, dataObject);\n");
        mappingAdministrator.append("			} catch (Exception e) {\n");
        mappingAdministrator.append("				e.printStackTrace();\n");
        mappingAdministrator.append("			}\n");
        mappingAdministrator.append("		}\n");
        mappingAdministrator
                .append("		updateDataObjectColaborators(businessObject, businessObjectDataObjectMapping,dataObject);\n");
        mappingAdministrator.append("		businessObjectDataObjectMapping.put(businessObject,dataObject);\n");
        mappingAdministrator.append("		return dataObject;\n");
        mappingAdministrator.append("	}\n");
    }

    private void generateUpdateDataObjectColaborators(final MappingAdministrator mappingAdministrator) {

        mappingAdministrator.append("	\n");
        mappingAdministrator
                .append("	private void updateDataObjectColaborators(Object businessObject,BusinessObjectDataObjectMapping businessObjectDataObjectMapping,Object dataObject) {\n");
        mappingAdministrator.append("	\n");
        mappingAdministrator.append("		Class dataClass = dataObject.getClass();\n");
        mappingAdministrator.append("		Field[] fields = businessObject.getClass().getDeclaredFields();\n");
        mappingAdministrator.append("	\n");
        mappingAdministrator.append("		for (int i = 0; i < fields.length; i++) {\n");
        mappingAdministrator.append("			fields[i].setAccessible(true);\n");
        mappingAdministrator.append("			try {\n");
        mappingAdministrator.append("				if (isBusinessClass(fields[i].getType())) {\n");
        mappingAdministrator.append("					Field dataObjectField = dataClass.getDeclaredField(fields[i].getName());\n");
        mappingAdministrator.append("					dataObjectField.setAccessible(true);\n");
        mappingAdministrator.append("					Object businessColaboratorObject = fields[i].get(businessObject);\n");
        mappingAdministrator.append("					Object dataColaboratorObject;\n");
        mappingAdministrator.append("					if (businessColaboratorObject != null)\n");
        mappingAdministrator
                .append("						dataColaboratorObject = createOrUpdateDataObject(businessColaboratorObject,businessObjectDataObjectMapping);\n");
        mappingAdministrator.append("					else\n");
        mappingAdministrator.append("						dataColaboratorObject = null;\n");
        mappingAdministrator.append("					dataObjectField.set(dataObject, dataColaboratorObject);\n");
        mappingAdministrator.append("				} else {\n");
        mappingAdministrator.append("					Field dataObjectField = dataClass.getDeclaredField(fields[i].getName());\n");
        mappingAdministrator.append("					dataObjectField.setAccessible(true);\n");
        mappingAdministrator.append("					Object businessColaboratorObject = fields[i].get(businessObject);\n");
        mappingAdministrator.append("					dataObjectField.set(dataObject, businessColaboratorObject);\n");
        mappingAdministrator.append("				}\n");
        mappingAdministrator.append("			} catch (Exception e) {\n");
        mappingAdministrator.append("				e.printStackTrace();\n");
        mappingAdministrator.append("			}\n");
        mappingAdministrator.append("		}\n");
        mappingAdministrator.append("	}\n");
    }

    private void generateIsBusinessClass(final MappingAdministrator mappingAdministrator,
            final CodeGeneratorConfiguration codeGeneratorConfiguration) {

        mappingAdministrator.append("	\n");
        mappingAdministrator.append("	private boolean isBusinessClass(Class aClass) {\n");
        mappingAdministrator.append("	\n");
        mappingAdministrator.append("		return (   ");
        for (Iterator<Class> iterator = codeGeneratorConfiguration.getClassList().iterator(); iterator.hasNext();) {
            Class aClass = iterator.next();
            // mappingAdministrator.append(" aClass.getName().equals("+
            // codeGeneratorConfiguration
            // .getProyectPackageName()+"."+aClass.getSimpleName()+".class)");
            mappingAdministrator.append(" aClass.getName().equals(" + aClass.getPackage() + "."
                    + aClass.getSimpleName() + ".class)");
            if (iterator.hasNext()) {
                mappingAdministrator.append("\n			|| ");
            }
        }
        mappingAdministrator.append(");\n");
        mappingAdministrator.append("	}\n");
    }

    private void generateCreateOrUpdateBusinessObject(final MappingAdministrator mappingAdministrator) {

        mappingAdministrator.append("	\n");
        mappingAdministrator
                .append("	public Object createOrUpdateBusinessObject(Object dataObject,BusinessObjectDataObjectMapping businessObjectDataObjectMapping) {\n");
        mappingAdministrator.append("	\n");
        mappingAdministrator.append("		Object businessObject = null;\n");
        mappingAdministrator.append("		if (businessObjectDataObjectMapping.containsDataObject(dataObject))\n");
        mappingAdministrator
                .append("			businessObject = businessObjectDataObjectMapping.getBusinessObject(dataObject);\n");
        mappingAdministrator.append("		else {\n");
        mappingAdministrator.append("			try {\n");
        mappingAdministrator
                .append("				businessObject = Class.forName(dataObject.getClass().getName().replace(\"persistence.dataobject.\", \"\")).newInstance();\n");
        mappingAdministrator.append("				businessObjectDataObjectMapping.put(businessObject, dataObject);\n");
        mappingAdministrator.append("			} catch (Exception e) {\n");
        mappingAdministrator.append("				e.printStackTrace();\n");
        mappingAdministrator.append("			}\n");
        mappingAdministrator.append("		}\n");
        mappingAdministrator
                .append("		updateBusinessObjectColaborators(dataObject,businessObjectDataObjectMapping, businessObject);\n");
        mappingAdministrator.append("		businessObjectDataObjectMapping.put(businessObject,dataObject);\n");
        mappingAdministrator.append("		return businessObject;\n");
        mappingAdministrator.append("	}\n");
    }

    private void generateUpdateBusinessObjectColaborators(final MappingAdministrator mappingAdministrator) {

        mappingAdministrator.append("	\n");
        mappingAdministrator
                .append("	private void updateBusinessObjectColaborators(Object dataObject,BusinessObjectDataObjectMapping businessObjectDataObjectMapping,Object businessObject) {\n");
        mappingAdministrator.append("	\n");
        mappingAdministrator.append("		Class businessClass = businessObject.getClass();\n");
        mappingAdministrator.append("		Field[] fields = dataObject.getClass().getDeclaredFields();\n");
        mappingAdministrator.append("	\n");
        mappingAdministrator.append("		for (int i = 0; i < fields.length; i++) {\n");
        mappingAdministrator.append("			fields[i].setAccessible(true);\n");
        mappingAdministrator.append("			try {\n");
        mappingAdministrator.append("				if (isDataClass(fields[i].getType())) {\n");
        mappingAdministrator
                .append("					Field businessObjectField = businessClass.getDeclaredField(fields[i].getName());\n");
        mappingAdministrator.append("					businessObjectField.setAccessible(true);\n");
        mappingAdministrator.append("					Object dataColaboratorObject = fields[i].get(dataObject);\n");
        mappingAdministrator.append("					Object businessColaboratorObject;\n");
        mappingAdministrator.append("					if (dataColaboratorObject != null)\n");
        mappingAdministrator
                .append("						businessColaboratorObject = createOrUpdateDataObject(dataColaboratorObject,businessObjectDataObjectMapping);\n");
        mappingAdministrator.append("					else\n");
        mappingAdministrator.append("						businessColaboratorObject = null;\n");
        mappingAdministrator.append("					businessObjectField.set(businessObject,businessColaboratorObject);\n");
        mappingAdministrator.append("				} else if (!fields[i].getName().equalsIgnoreCase(\"ormId\")) {\n");
        mappingAdministrator
                .append("					Field businessObjectField = businessClass.getDeclaredField(fields[i].getName());\n");
        mappingAdministrator.append("					businessObjectField.setAccessible(true);\n");
        mappingAdministrator.append("					Object dataColaboratorObject = fields[i].get(dataObject);\n");
        mappingAdministrator.append("					businessObjectField.set(businessObject,dataColaboratorObject);\n");
        mappingAdministrator.append("				}\n");
        mappingAdministrator.append("			} catch (Exception e) {\n");
        mappingAdministrator.append("				e.printStackTrace();\n");
        mappingAdministrator.append("			}\n");
        mappingAdministrator.append("		}\n");
        mappingAdministrator.append("	}\n");
    }

    private void generateIsDataClass(final MappingAdministrator mappingAdministrator,
            final CodeGeneratorConfiguration codeGeneratorConfiguration) {

        mappingAdministrator.append("	\n");
        mappingAdministrator.append("	private boolean isDataClass(Class aClass) {\n");
        mappingAdministrator.append("	\n");
        mappingAdministrator.append("		return (   ");
        for (Iterator<Class> iterator = codeGeneratorConfiguration.getClassList().iterator(); iterator.hasNext();) {
            Class aClass = iterator.next();
            mappingAdministrator.append(" aClass.getName().equals(" + aClass.getPackage() + ".persistence.dataobject."
                    + aClass.getSimpleName() + ".class)");
            if (iterator.hasNext()) {
                mappingAdministrator.append("\n			|| ");
            }
        }
        mappingAdministrator.append(");\n");
        mappingAdministrator.append("	}\n");
    }

    private void generateGetAndDeleteDataObject(final MappingAdministrator mappingAdministrator) {

        mappingAdministrator.append("	\n");
        mappingAdministrator
                .append("	public Object getAndDeleteDataObject(Object businessObject,BusinessObjectDataObjectMapping businessObjectDataObjectMapping) {\n");
        mappingAdministrator.append("	\n");
        mappingAdministrator
                .append("		Object dataObject = businessObjectDataObjectMapping.getDataObject(businessObject);\n");
        mappingAdministrator.append("		businessObjectDataObjectMapping.remove(dataObject);\n");
        mappingAdministrator.append("		return dataObject;\n");
        mappingAdministrator.append("	}\n");
    }

    private void generateCreateOrUpdateAndGetBusinessObjects(final MappingAdministrator mappingAdministrator) {

        mappingAdministrator.append("	\n");
        mappingAdministrator
                .append("	public Collection createOrUpdateAndGetBusinessObjects(Collection dataObjects,BusinessObjectDataObjectMapping businessObjectDataObjectMapping) {\n");
        mappingAdministrator.append("	\n");
        mappingAdministrator.append("		List businessObjects = new ArrayList<Object>();\n");
        mappingAdministrator.append("	\n");
        mappingAdministrator.append("		for (Iterator iterator = dataObjects.iterator(); iterator.hasNext();) {\n");
        mappingAdministrator.append("			Object dataObject = (Object) iterator.next();\n");
        mappingAdministrator
                .append("			businessObjects.add(createOrUpdateBusinessObject(dataObject,businessObjectDataObjectMapping));\n");
        mappingAdministrator.append("		}\n");
        mappingAdministrator.append("		return businessObjects;\n");
        mappingAdministrator.append("	}\n");
    }
}