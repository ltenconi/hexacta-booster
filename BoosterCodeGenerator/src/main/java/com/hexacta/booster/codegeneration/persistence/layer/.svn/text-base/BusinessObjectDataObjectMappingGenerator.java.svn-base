package com.hexacta.booster.codegeneration.persistence.layer;

/**
 * 
 * 
 * 
 * Created on 27/01/2009
 * 
 * @author clopez
 * 
 */
public class BusinessObjectDataObjectMappingGenerator {

    public BusinessObjectDataObjectMappingGenerator() {

    }

    public String generateConstructor() {
        return "\n" + "	public BusinessObjectDataObjectMapping() {\n"
                + "		businessToData = new HashMap<Object, Object>();\n"
                + "		dataToBusiness = new HashMap<Object, Object>();\n" + "	}\n";
    }

    public String generateMethods() {
        return "\n" + "	public void put(Object businessObject, Object dataObject){\n"
                + "		businessToData.put(businessObject,dataObject);\n"
                + "		dataToBusiness.put(dataObject,businessObject);\n" + "	}\n" + "\n"
                + "	public Object getBusinessObject(Object dataObject){\n"
                + "		return dataToBusiness.get(dataObject);\n" + "	}\n" + "\n"
                + "	public Object getDataObject(Object businessObject){\n"
                + "		return businessToData.get(businessObject);\n" + "	}\n" + "\n"
                + "	public void remove(Object object){\n" + "		if (businessToData.containsKey(object))\n"
                + "		{	Object dataObject = businessToData.get(object);\n" + "			businessToData.remove(object);\n"
                + "			dataToBusiness.remove(dataObject);\n" + "		}\n" + "		if (dataToBusiness.containsKey(object))\n"
                + "		{	Object businessObject = dataToBusiness.get(object);\n" + "			dataToBusiness.remove(object);\n"
                + "			businessToData.remove(businessObject);\n" + "		}\n" + "	}\n" + "\n"
                + "	public boolean containsBusinessObject(Object businessObject){\n"
                + "		return businessToData.containsKey(businessObject);\n" + "	}\n" + "\n"
                + "	public boolean containsDataObject(Object dataObject){\n"
                + "		return dataToBusiness.containsKey(dataObject);\n" + "	}\n";
    }

    public String generateColaborators() {
        return "\n" + "	Map<Object, Object> businessToData;\n" + "	Map<Object, Object> dataToBusiness;\n" + "\n";
    }

    public String generatePackage() {
        return "package " + "persistence;\n";
    }

    public String generateClass() {
        return "public class BusinessObjectDataObjectMapping {\n" + generateColaborators() + generateConstructor()
                + generateMethods() + "}\n";
    }

    public String generate() {
        return generatePackage() + generateImports() + generateClass();
    }

    public String generateImports() {
        return "import java.util.HashMap;\n" + "import java.util.Map;\n";
    }

}
