package com.hexacta.booster.codegeneration.configuration;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.hexacta.booster.codegeneration.metamodel.Class;

/**
 * 
 */
public class ClassList implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private List<Class> classList;

    public ClassList() {
        classList = new ArrayList<Class>();
    }

    public void addClass(final Class className) {
        classList.add(className);
    }

    public boolean isEmpty() {
        return classList.isEmpty();
    }

    public Iterator<Class> iterator() {
        return classList.iterator();
    }

    public boolean hasClass(final String className) {
        for (Class name : classList) {
            if (name.getName().equalsIgnoreCase(className))
                return true;
        }
        return false;
    }

    public Class getClass(final String className) {
        for (Class name : classList) {
            if (name.getName().equalsIgnoreCase(className))
                return name;
        }
        return null;
    }

    public Class getClassBySimpleName(final String classSimpleName) {
        for (Class name : classList) {
            if (name.getSimpleName().equalsIgnoreCase(classSimpleName))
                return name;
        }
        return null;

    }

    public int length() {
        return classList.size();
    }

}
