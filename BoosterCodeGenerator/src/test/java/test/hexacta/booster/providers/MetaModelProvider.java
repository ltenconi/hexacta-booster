package test.hexacta.booster.providers;

import java.util.ArrayList;
import java.util.List;

import com.hexacta.booster.codegeneration.configuration.ClassList;
import com.hexacta.booster.codegeneration.metamodel.Class;
import com.hexacta.booster.codegeneration.metamodel.MetaModelGeneratorForJavaModel;

/**
 * 
 */
public final class MetaModelProvider {

    /**
     * 
     */
    private MetaModelProvider() {

    }

    public static Class getMetaModel(final java.lang.Class<?> aClass) {

        MetaModelGeneratorForJavaModel metaModelGeneratorForJavaModel = new MetaModelGeneratorForJavaModel();
        List<java.lang.Class<?>> classList = new ArrayList<java.lang.Class<?>>();
        classList.add(aClass);

        return metaModelGeneratorForJavaModel.generate(aClass, new ClassList());
    }

}
