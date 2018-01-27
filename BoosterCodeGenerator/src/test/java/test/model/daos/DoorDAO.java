package test.model.daos;

import test.model.Door;

/**
 * 
 */
public class DoorDAO extends GenericDAO<Door> {

    @Override
    protected Class<Door> getDomainClass() {
        return Door.class;
    }

}