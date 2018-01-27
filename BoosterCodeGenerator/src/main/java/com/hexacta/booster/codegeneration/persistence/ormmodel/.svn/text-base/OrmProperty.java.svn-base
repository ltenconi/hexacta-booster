package com.hexacta.booster.codegeneration.persistence.ormmodel;

import com.hexacta.booster.codegeneration.persistence.configuration.hibernate.XmlHibernateMappingGenerator;
import com.hexacta.booster.codegeneration.persistence.configuration.hibernate.XmlHibernateMappingGeneratorContext;
import com.hexacta.booster.codegeneration.persistence.configuration.nhibernate.XmlNHibernateMappingGenerator;
import com.hexacta.booster.codegeneration.persistence.configuration.nhibernate.XmlNHibernateMappingGeneratorContext;

/**
 * 
 */
public class OrmProperty {

    private String name;

    private String table;

    private VarType varType;

    public OrmProperty(final String name, final VarType varType) {
        this.name = name;
        table = name.toUpperCase();
        this.varType = varType;
    }

    public String getName() {
        return name;
    }

    public String getTable() {
        return table;
    }

    public VarType getVarType() {
        return varType;
    }

    public void generateCodeIn(final XmlHibernateMappingGenerator hibernateMappingGenerator,
            final XmlHibernateMappingGeneratorContext hibernateMappingGeneratorContext) {
        hibernateMappingGenerator.generateCodeFor(this, hibernateMappingGeneratorContext);
    }

    public void generateCodeIn(final XmlNHibernateMappingGenerator xmlNHibernateMappingGenerator,
            final XmlNHibernateMappingGeneratorContext hibernateMappingGeneratorContext) {
        xmlNHibernateMappingGenerator.generateCodeFor(this, hibernateMappingGeneratorContext);

    }

}
