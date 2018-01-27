/*
 * [legal notice here]
 */
package com.hexacta.booster.codegeneration.persistence.strategy;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.hexacta.booster.codegeneration.configuration.CodeGeneratorConfiguration;
import com.hexacta.booster.codegeneration.metamodel.Class;
import com.hexacta.booster.codegeneration.persistence.ormmodel.OrmMapping;
import com.hexacta.booster.utilities.MetaModelUtils;
import com.hexacta.booster.utilities.OrmMappingHelper;

/**
 * 
 * 
 * Created on 19/02/2009
 * 
 * @author fmrodriguez
 * 
 */
public class EntityBranchStrategy extends MappingHierarchyCuttingStrategy {

    /**
     * {@inheritDoc}
     */
    @Override
    public void cut(final List<OrmMapping> ormHierarchyMappings,
            final CodeGeneratorConfiguration codeGeneratorConfiguration) {

        if (codeGeneratorConfiguration.getGenerateCodeFor().length() == 1) {
            Class entityClass = codeGeneratorConfiguration.getGenerateCodeFor().iterator().next();
            OrmMapping ormMappingHierarchy = OrmMappingHelper.getOrmMappingHierarchy(entityClass, ormHierarchyMappings);
            ormHierarchyMappings.remove(ormMappingHierarchy);
            List<Class> hierarchyBranchClasses = MetaModelUtils.getHierarchyBranchClasses(entityClass);
            performCut(ormMappingHierarchy, hierarchyBranchClasses, entityClass);
            ormHierarchyMappings.add(ormMappingHierarchy);

        } // Si la generación no es solo para una clase, entonces no se
        // realiza el corte

    }

    /**
     * @param ormMappingHierarchy
     * @param hierarchyBranch
     * @param entityClass
     * @return
     */
    private void performCut(final OrmMapping ormMappingHierarchy, final List<Class> hierarchyBranch,
            final Class entityClass) {

        if (!ormMappingHierarchy.getMappingClass().getName().equalsIgnoreCase(entityClass.getName())) {
            cutSubClassMappings(ormMappingHierarchy, hierarchyBranch, entityClass);
        } else {
            ormMappingHierarchy.setSubClassOrmMapping(new ArrayList<OrmMapping>());
        }

    }

    /**
     * @param ormMapping
     * @param hierarchyBranchClasses
     */
    private void cutSubClassMappings(final OrmMapping ormMapping, final List<Class> hierarchyBranchClasses,
            final Class entityClass) {

        List<OrmMapping> subClassMappings = ormMapping.getSubClassOrmMapping();
        boolean mappingOnHierarchyBranchfound = false;
        Iterator<OrmMapping> iterator = subClassMappings.iterator();
        while (!mappingOnHierarchyBranchfound) {
            OrmMapping subClassMapping = iterator.next();
            Class subClass = subClassMapping.getMappingClass();
            if (isOnHierarchyBranchClasses(subClass, hierarchyBranchClasses)) {
                mappingOnHierarchyBranchfound = true;
                List<OrmMapping> branchSubClassMappings = new ArrayList<OrmMapping>();
                performCut(subClassMapping, hierarchyBranchClasses, entityClass);
                branchSubClassMappings.add(subClassMapping);
                ormMapping.setSubClassOrmMapping(branchSubClassMappings);
            }
        }

    }

    /**
     * @param subClass
     * @param hierarchyBranchClasses
     * @return
     */
    private boolean isOnHierarchyBranchClasses(final Class subClass, final List<Class> hierarchyBranchClasses) {

        for (Class aClass : hierarchyBranchClasses) {
            if (aClass.getName().equalsIgnoreCase(subClass.getName()))
                return true;
        }

        return false;
    }

}
