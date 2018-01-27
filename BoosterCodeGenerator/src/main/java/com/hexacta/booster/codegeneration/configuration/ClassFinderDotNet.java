package com.hexacta.booster.codegeneration.configuration;

import com.hexacta.booster.codegeneration.metamodel.Class;
import com.hexacta.booster.codegeneration.metamodel.DummyClass;

/**
 * 
 * @author clopez
 * 
 */
public final class ClassFinderDotNet {

    private ClassFinderDotNet() {

    }

    public static Class findForType(final String aTypeName) throws NotSupportedClassException {

        if (aTypeName.equalsIgnoreCase("System.Int32"))
            return new Class("System.Int32", "Int32", null, null, false, true, null, null, false, null);
        if (aTypeName.equalsIgnoreCase("System.String"))
            return new Class("System.String", "String", null, null, false, true, null, null, false, null);
        if (aTypeName.equalsIgnoreCase("System.Nullable"))
            return new Class("System.Nullable", "Nullable", null, null, false, true, null, null, false, null);
        if (aTypeName.equalsIgnoreCase("System.Decimal"))
            return new Class("System.Decimal", "System.Decimal", null, null, false, true, null, null, false, null);
        if (aTypeName.equalsIgnoreCase("System.Byte"))
            return new Class("System.Byte", "Byte", null, null, false, true, null, null, false, null);
        if (aTypeName.equalsIgnoreCase("System.Int64"))
            return new Class("System.Int64", "Int64", null, null, false, true, null, null, false, null);
        if (aTypeName.equalsIgnoreCase("System.Boolean"))
            return new Class("System.Boolean", "Boolean", null, null, false, true, null, null, false, null);
        if (aTypeName.equalsIgnoreCase("System.char"))
            return new Class("System.char", "char", null, null, false, true, null, null, false, null);
        if (aTypeName.equalsIgnoreCase("System.Int16"))
            return new Class("System.Int16", "Int16", null, null, false, true, null, null, false, null);
        if (aTypeName.equalsIgnoreCase("System.Single"))
            return new Class("System.Single", "Single", null, null, false, true, null, null, false, null);
        if (aTypeName.equalsIgnoreCase("System.Double"))
            return new Class("System.Double", "Double", null, null, false, true, null, null, false, null);
        if (aTypeName.equalsIgnoreCase("System.Guid"))
            return new Class("System.Guid", "Guid", null, null, false, true, null, null, false, null);
        if (aTypeName.equalsIgnoreCase("System.DateTime"))
            return new Class("System.DateTime", "DateTime", null, null, false, true, null, null, false, null);
        if (aTypeName.equalsIgnoreCase("System.Object"))
            return new Class("System.Object", "Object", null, null, false, true, null, null, false, null);

        return new DummyClass(aTypeName);

    }

}
