package test.model.daos;

import test.model.Person;

/**
 * 
 */
public class PersonDAO extends GenericDAO<Person> {

    @Override
    protected Class<Person> getDomainClass() {
        return Person.class;
    }

}