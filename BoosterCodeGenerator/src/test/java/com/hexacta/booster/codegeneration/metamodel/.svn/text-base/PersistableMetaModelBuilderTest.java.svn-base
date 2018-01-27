package com.hexacta.booster.codegeneration.metamodel;

import junit.framework.TestCase;
import test.hexacta.booster.providers.MetaModelProvider;

import com.hexacta.booster.codegeneration.configuration.AttributesSubset;
import com.hexacta.booster.codegeneration.configuration.ClassList;
import com.hexacta.booster.codegeneration.configuration.PersistableMetaModelAttributes;
import com.hexacta.booster.exception.ModelClassNotFound;

/**
 * 
 */
public class PersistableMetaModelBuilderTest extends TestCase {

    public void testPersistableMetaModelBuilder() {

        Class metaModelClass = MetaModelProvider.getMetaModel(test.model.Room.class);
        ClassList classList = new ClassList();
        classList.addClass(metaModelClass);

        PersistableMetaModelAttributes persistableMetaModelAttributes = new PersistableMetaModelAttributes();
        AttributesSubset attributesSubset = new AttributesSubset();
        attributesSubset.add("roomId");
        persistableMetaModelAttributes.add(metaModelClass.getSimpleName(), attributesSubset);

        ClassList persistableList = null;
        try {

            persistableList = PersistableMetaModelBuilder.build(classList, persistableMetaModelAttributes);

        } catch (NotExistAttributeException e) {
            fail(e.getMessage());

        } catch (ModelClassNotFound e) {
            fail(e.getMessage());
        } catch (NotAnEntityClassException e) {
            fail(e.getMessage());
        }

        assertEquals(1, persistableList.getClass(metaModelClass.getName()).getDeclaredFields().length);
        assertEquals("roomId", persistableList.getClass(metaModelClass.getName()).getDeclaredFields()[0].getName());

    }

}
