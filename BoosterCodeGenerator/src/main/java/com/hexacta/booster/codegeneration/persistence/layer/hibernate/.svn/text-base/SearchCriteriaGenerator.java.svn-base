package com.hexacta.booster.codegeneration.persistence.layer.hibernate;

/**
 * This class generates the text for a class SearchCriteria.
 */
public class SearchCriteriaGenerator {

    public SearchCriteriaGenerator() {

    }

    public SearchCriteria generate() {

        SearchCriteria state = new SearchCriteria();
        generatePackage(state);
        generateImports(state);
        generateClass(state);

        return state;
    }

    private void generatePackage(final SearchCriteria state) {
        String packages = "package persistence;\n";
        state.append(packages);
    }

    private void generateImports(final SearchCriteria state) {

        String imports = new String("import java.util.List;\n" + "import org.hibernate.Criteria;\n"
                + "import org.hibernate.criterion.Criterion;\n" + "import org.hibernate.criterion.Order;\n"
                + "import org.hibernate.criterion.Projection;\n");
        state.append(imports);
    }

    private void generateClass(final SearchCriteria state) {

        String classText = new String("\n" + "public class SearchCriteria {\n" + generateAttributes()
                + generateConstructor() + generateAddMethod() + generateAddOrderMethod()
                + generateSetProjectionMethod() + generateSetMaxResultMethod() + generateListMethod() + "" + "}");
        state.append(classText);
    }

    private String generateAttributes() {

        return "\n" + "	Criteria aCriteria;\n" + "\n";

    }

    private String generateConstructor() {

        return "\n" + "	public SearchCriteria(Criteria aCriteria) {\n" + "		this.aCriteria = aCriteria;\n" + "	}";
    }

    public String generateAddMethod() {

        return "\n" + "	public void add(Criterion aCriterion){\n" + "		aCriteria.add(aCriterion);\n" + "	}\n";
    }

    public String generateAddOrderMethod() {

        return "\n" + "	public void addOrder(Order anOrder){\n" + "		aCriteria.addOrder(anOrder);\n" + "	}\n";
    }

    public String generateSetProjectionMethod() {

        return "\n" + "	public void setProjection(Projection aProjection){\n"
                + "		aCriteria.setProjection(aProjection);\n" + "	}\n";
    }

    public String generateSetMaxResultMethod() {

        return "\n" + "	public void setMaxResult(int max){\n" + "		aCriteria.setMaxResults(max);\n" + "	}\n";
    }

    public String generateListMethod() {

        return "\n" + "	public List list(){\n" + "		return aCriteria.list();\n" + "	}\n";
    }
}
