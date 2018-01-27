package test.model.daos;

import test.model.House;

/**
 * 
 */
public class HouseDAO extends GenericDAO<House> {

    @Override
    protected Class<House> getDomainClass() {
        return House.class;
    }

}