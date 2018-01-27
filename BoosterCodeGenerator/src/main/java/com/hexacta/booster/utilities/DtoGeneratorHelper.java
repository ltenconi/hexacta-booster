package com.hexacta.booster.utilities;

import java.util.ArrayList;
import java.util.List;

import com.hexacta.booster.codegeneration.configuration.ClassList;
import com.hexacta.booster.codegeneration.metamodel.Field;
import com.hexacta.booster.codegeneration.metamodel.ParameterizedType;
import com.hexacta.booster.codegeneration.metamodel.Type;

/**
 * 
 */
public final class DtoGeneratorHelper {

    private static final String END_PARENTHESIS = ")";

    private static final String SPACE = " ";

    private static final String START_PARETHESIS = "(";

    private static final String SET = " set";

    private static final String VOID = " void";

    private static final String BOOLEAN = "boolean";

    private static final String PARETHESIS = "()";

    private static final String GET = " get";

    private static final String IS = " is";

    private static final String PUBLIC_MODIFIER = "	public ";

    private static final String EMPTY_STRING = "";

    private static final String IMPORT_SENTECE = "import ";

    private static final String END_LINE = ";\n";

    /**
     * 
     */
    private DtoGeneratorHelper() {

    }

    /**
     * Returns a List of DTOFields from a list of fields. A dtofield is a field
     * when its type is a simple type.
     * 
     * @param declaredFields
     *            the list of fields
     * @param classList
     *            the classList
     * @return a list of simple fields
     */
    public static List<Field> getDTOFields(final Field[] declaredFields, final ClassList classList) {

        List<Field> simpleFields = new ArrayList<Field>();

        for (Field field : declaredFields) {
            if (isDtoField(field, classList)) {
                simpleFields.add(field);

            }
        }
        return simpleFields;
    }

    /**
     * Returns a List of SimpleFields from a list of fields. A field is a simple
     * field when its type is a simple type and the field isn't the id field.
     * 
     * @param declaredFields
     *            the list of fields
     * @param classList
     *            the classList
     * @return a list of simple fields
     */
    public static List<Field> getSimpleFields(final Field[] declaredFields, final ClassList classList) {

        List<Field> simpleFields = new ArrayList<Field>();

        List<Field> dtoFields = getDTOFields(declaredFields, classList);

        for (Field field : dtoFields) {
            if (!isIdField(field)) {
                simpleFields.add(field);

            }
        }
        return simpleFields;
    }

    private static boolean isIdField(final Field field) {
        return field.getName() == "id";
    }

    public static String getGetters(final Field[] declaredFields, final ClassList classList) {
        String getters = EMPTY_STRING;
        for (Field field : declaredFields) {
            if (isDtoField(field, classList)) {
                getters = getters + PUBLIC_MODIFIER + field.getType().getSimpleName();
                if (field.getType().getSimpleName().equalsIgnoreCase(BOOLEAN)) {
                    getters = getters + IS;
                } else {
                    getters = getters + GET;
                }
                getters = getters + field.getName().substring(0, 1).toUpperCase() + field.getName().substring(1)
                        + PARETHESIS + END_LINE;
            }
        }
        return getters;
    }

    public static String getImportsFor(final Field[] declaredFields, final ClassList classList) {
        String imports = EMPTY_STRING;
        for (Field field : declaredFields) {
            if (!field.getType().isPrimitive() && isDtoField(field, classList)) {
                if (field.getType().isArray()) {
                    imports = imports + IMPORT_SENTECE + field.getType().getComponentType().getName() + END_LINE;
                } else if (field.getType().isParameterized()) {
                    String[] actualTypeArguments = ((ParameterizedType) field.getType()).getActualTypeArguments();
                    for (String actualArgument : actualTypeArguments) {
                        imports = imports + IMPORT_SENTECE + actualArgument + END_LINE;
                    }
                } else {
                    imports = imports + IMPORT_SENTECE + field.getType().getName() + END_LINE;
                }
            }
        }
        return imports;
    }

    private static boolean isDtoField(final Field field, final ClassList classList) {
        if (!isAssociation(field, classList))
            return true;
        return false;
    }

    private static boolean isAssociation(final Field field, final ClassList classList) {
        Type fieldType = field.getType();

        if (fieldType.isArray())
            return classList.hasClass(fieldType.getComponentType().getName());
        else if (fieldType.isPrimitive())
            return false;
        else if (fieldType.isParameterized()) {
            ParameterizedType parameterizedType = (ParameterizedType) fieldType;
            String[] actualTypeArguments = parameterizedType.getActualTypeArguments();
            for (String typeName : actualTypeArguments) {
                if (classList.hasClass(typeName))
                    return true;
            }
            return false;
        } else
            return classList.hasClass(fieldType.getName());

    }

    public static Object getSetters(final Field[] declaredFields, final ClassList classList) {
        String setters = EMPTY_STRING;
        for (Field field : declaredFields) {
            if (isDtoField(field, classList)) {
                setters = setters + PUBLIC_MODIFIER + VOID + SET + field.getName().substring(0, 1).toUpperCase()
                        + field.getName().substring(1) + START_PARETHESIS + field.getType().getSimpleName() + SPACE
                        + field.getName().toLowerCase() + END_PARENTHESIS + END_LINE;
            }
        }
        return setters;
    }

    public static boolean containsIdField(Field[] fields) {
        for (Field field : fields) {
            if (field.getName().equalsIgnoreCase("id")){
                return true;
            }
         }
        return false;
    }

}
