/*
 * [legal notice here]
 */
package com.hexacta.booster.utilities;

import java.util.Iterator;
import java.util.List;

import com.hexacta.booster.codegeneration.metamodel.Class;
import com.hexacta.booster.codegeneration.persistence.ormmodel.OrmMapping;

/**
 * 
 * 
 * Created on 23/02/2009
 * 
 * @author fmrodriguez
 * 
 */
public final class OrmMappingHelper {

    /**
     * 
     */
    private OrmMappingHelper() {

    }

    /**
     * @param entityClass
     * @param ormMappings
     */
    public static OrmMapping getOrmMappingHierarchy(final Class entityClass, final List<OrmMapping> ormMappings) {

        for (OrmMapping ormMappingHierarchy : ormMappings) {
            if (hasMappingClass(ormMappingHierarchy, entityClass))
                return ormMappingHierarchy;
        }

        return null;
    }

    /**
     * @param ormMappingHierarchy
     * @param entityClass
     * @return
     */
    public static boolean hasMappingClass(final OrmMapping ormMappingHierarchy, final Class entityClass) {

        if (ormMappingHierarchy.getMappingClass().getName().equalsIgnoreCase(entityClass.getName()))
            return true;
        else {
            List<OrmMapping> ormSubMappings = ormMappingHierarchy.getSubClassOrmMapping();
            Iterator<OrmMapping> iterator = ormSubMappings.iterator();
            boolean end = false;
            while (!end) {
                if (iterator.hasNext()) {
                    OrmMapping ormSubMapping = iterator.next();
                    end = hasMappingClass(ormSubMapping, entityClass);
                    if (end)
                        return true;
                } else {
                    end = true;
                }
            }
            return false;
        }

    }

}
