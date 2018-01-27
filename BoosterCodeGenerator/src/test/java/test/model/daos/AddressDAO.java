package test.model.daos;

import test.model.Address;

/**
 * 
 */
public class AddressDAO extends GenericDAO<Address> {

    @Override
    protected Class<Address> getDomainClass() {
        return Address.class;
    }

}