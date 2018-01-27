package com.hexacta.booster.utilities;

import java.util.Iterator;

import com.hexacta.booster.codegeneration.configuration.ClassIdMap;
import com.hexacta.booster.codegeneration.configuration.ClassList;
import com.hexacta.booster.codegeneration.metamodel.Class;
import com.hexacta.booster.project.configuration.ProjectType;

/**
 * 
 */
public class IdFieldNameAssigner {

    static final String DOTNET_IDNAME = "id";

    static final String JAVA_IDNAME = "id";

    public IdFieldNameAssigner() {
    }

    public ClassIdMap assign(final ClassList classList, final ProjectType projectType) {

        ClassIdMap classIdMap = new ClassIdMap();
        String idName;

        if (projectType.isJava()) {
            idName = JAVA_IDNAME;
        } else { // is dotNet
            idName = DOTNET_IDNAME;
        }

        for (Iterator<Class> iterator = classList.iterator(); iterator.hasNext();) {
            Class modelClass = iterator.next();
            classIdMap.putId(modelClass.getName(), idName);

        }

        return classIdMap;

    }

}
