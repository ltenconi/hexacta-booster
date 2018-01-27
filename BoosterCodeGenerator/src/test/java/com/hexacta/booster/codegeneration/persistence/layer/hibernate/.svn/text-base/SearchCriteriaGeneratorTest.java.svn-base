package com.hexacta.booster.codegeneration.persistence.layer.hibernate;

import junit.framework.TestCase;

import org.apache.log4j.Logger;

import ar.com.hexacta.utils.reflection.ReflectionUtils;

/**
 * This class tests the class SearchCriteriaGenerator.
 */
public class SearchCriteriaGeneratorTest extends TestCase {

    /**
     * Log4j logger.
     */

    static final Logger logger = Logger.getLogger(SearchCriteriaGeneratorTest.class.getSimpleName());

    public void testCreate() {
        try {

            SearchCriteriaGenerator generator = new SearchCriteriaGenerator();
            assertNotNull(generator);

        } catch (Exception e) {
            logger.error("Se ha levantado una excepción: " + e.getMessage());
            fail();
        }
    }

    public void testGenerate() {
        try {
            /**
             * Generated
             */
            SearchCriteriaGenerator generator = new SearchCriteriaGenerator();
            SearchCriteria generatedState = generator.generate();
            /**
             * Expected
             */
            SearchCriteria expectedState = new SearchCriteria();
            ReflectionUtils.executePrivateMethod(generator, "generatePackage", new Class[] { SearchCriteria.class },
                    new Object[] { expectedState });
            ReflectionUtils.executePrivateMethod(generator, "generateImports", new Class[] { SearchCriteria.class },
                    new Object[] { expectedState });
            ReflectionUtils.executePrivateMethod(generator, "generateClass", new Class[] { SearchCriteria.class },
                    new Object[] { expectedState });

            assertEquals(expectedState.getText(), generatedState.getText());

        } catch (Exception e) {
            System.out.println(e.getMessage());
            fail();
        }
    }

    public void testGeneratePackage() {
        try {
            /**
             * Context
             */
            SearchCriteriaGenerator generator = new SearchCriteriaGenerator();
            SearchCriteria generatedState = new SearchCriteria();
            /**
             * Generated
             */
            ReflectionUtils.executePrivateMethod(generator, "generatePackage", new Class[] { SearchCriteria.class },
                    new Object[] { generatedState });

            assertEquals("package persistence;\n", generatedState.getText());

        } catch (Exception e) {
            System.out.println(e.getMessage());
            fail();
        }
    }

    public void testGenerateImports() {
        try {
            /**
             * Context
             */
            SearchCriteriaGenerator generator = new SearchCriteriaGenerator();
            SearchCriteria generatedState = new SearchCriteria();
            /**
             * Generated
             */
            ReflectionUtils.executePrivateMethod(generator, "generateImports", new Class[] { SearchCriteria.class },
                    new Object[] { generatedState });

            assertEquals("import java.util.List;\n" + "import org.hibernate.Criteria;\n"
                    + "import org.hibernate.criterion.Criterion;\n" + "import org.hibernate.criterion.Order;\n"
                    + "import org.hibernate.criterion.Projection;\n", generatedState.getText());

        } catch (Exception e) {
            System.out.println(e.getMessage());
            fail();
        }
    }

    public void testGenerateClass() {
        try {
            /**
             * Context
             */
            SearchCriteriaGenerator generator = new SearchCriteriaGenerator();
            SearchCriteria generatedState = new SearchCriteria();
            /**
             * Generated
             */
            ReflectionUtils.executePrivateMethod(generator, "generateClass", new Class[] { SearchCriteria.class },
                    new Object[] { generatedState });
            /**
             * Expected
             */
            String expectedString = "\n" + "public class SearchCriteria {\n"
                    + (String) ReflectionUtils.executePrivateMethod(generator, "generateAttributes")
                    + (String) ReflectionUtils.executePrivateMethod(generator, "generateConstructor")
                    + (String) ReflectionUtils.executePrivateMethod(generator, "generateAddMethod")
                    + (String) ReflectionUtils.executePrivateMethod(generator, "generateAddOrderMethod")
                    + (String) ReflectionUtils.executePrivateMethod(generator, "generateSetProjectionMethod")
                    + (String) ReflectionUtils.executePrivateMethod(generator, "generateSetMaxResultMethod")
                    + (String) ReflectionUtils.executePrivateMethod(generator, "generateListMethod") + "" + "}";

            assertEquals(expectedString, generatedState.getText());

        } catch (Exception e) {
            System.out.println(e.getMessage());
            fail();
        }
    }

    public void testGenerateAttributes() {
        try {

            SearchCriteriaGenerator generator = new SearchCriteriaGenerator();

            String generatedString = (String) ReflectionUtils.executePrivateMethod(generator, "generateAttributes");

            assertEquals("\n" + "	Criteria aCriteria;\n" + "\n", generatedString);

        } catch (Exception e) {
            System.out.println(e.getMessage());
            fail();
        }
    }

    public void testGenerateConstructor() {
        try {

            SearchCriteriaGenerator generator = new SearchCriteriaGenerator();

            String generatedString = (String) ReflectionUtils.executePrivateMethod(generator, "generateConstructor");

            assertEquals("\n" + "	public SearchCriteria(Criteria aCriteria) {\n" + "		this.aCriteria = aCriteria;\n"
                    + "	}", generatedString);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            fail();
        }
    }

    public void testGenerateAddMethod() {
        try {

            SearchCriteriaGenerator generator = new SearchCriteriaGenerator();

            String generatedString = (String) ReflectionUtils.executePrivateMethod(generator, "generateAddOrderMethod");

            assertEquals(
                    "\n" + "	public void addOrder(Order anOrder){\n" + "		aCriteria.addOrder(anOrder);\n" + "	}\n",
                    generatedString);

        } catch (Exception e) {
            System.out.println(e.getMessage());
            fail();
        }
    }

    public void testGenerateAddOrderMethod() {
        try {

            SearchCriteriaGenerator generator = new SearchCriteriaGenerator();

            String generatedString = (String) ReflectionUtils.executePrivateMethod(generator, "generateAddOrderMethod");

            assertEquals(
                    "\n" + "	public void addOrder(Order anOrder){\n" + "		aCriteria.addOrder(anOrder);\n" + "	}\n",
                    generatedString);

        } catch (Exception e) {
            System.out.println(e.getMessage());
            fail();
        }
    }

    public void testGenerateSetProjectionMethod() {
        try {

            SearchCriteriaGenerator generator = new SearchCriteriaGenerator();

            String generatedString = (String) ReflectionUtils.executePrivateMethod(generator,
                    "generateSetProjectionMethod");

            assertEquals("\n" + "	public void setProjection(Projection aProjection){\n"
                    + "		aCriteria.setProjection(aProjection);\n" + "	}\n", generatedString);

        } catch (Exception e) {
            System.out.println(e.getMessage());
            fail();
        }
    }

    public void testGenerateSetMaxResultMethod() {
        try {
            SearchCriteriaGenerator generator = new SearchCriteriaGenerator();

            String generatedString = (String) ReflectionUtils.executePrivateMethod(generator,
                    "generateSetMaxResultMethod");

            assertEquals("\n" + "	public void setMaxResult(int max){\n" + "		aCriteria.setMaxResults(max);\n" + "	}\n",
                    generatedString);

        } catch (Exception e) {
            System.out.println(e.getMessage());
            fail();
        }
    }

    public void testGenerateListMethod() {
        try {
            SearchCriteriaGenerator generator = new SearchCriteriaGenerator();

            String generatedString = (String) ReflectionUtils.executePrivateMethod(generator, "generateListMethod");

            assertEquals("\n" + "	public List list(){\n" + "		return aCriteria.list();\n" + "	}\n", generatedString);

        } catch (Exception e) {
            System.out.println(e.getMessage());
            fail();
        }
    }

}
