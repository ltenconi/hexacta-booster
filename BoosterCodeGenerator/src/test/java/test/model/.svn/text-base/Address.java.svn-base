package test.model;

import java.util.Set;

/**
 * 
 */
public class Address {
    int addressId;

    String name;

    int number;

    Set<Person> people;

    House house;

    public Address() {

    }

    public int getAddressId() {
        return addressId;
    }

    public void setAddressId(final int addressId) {
        this.addressId = addressId;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(final int number) {
        this.number = number;
    }

    public Set<Person> getPeople() {
        return people;
    }

    public void setPeople(final Set<Person> people) {
        this.people = people;
    }

    public House getHouse() {
        return house;
    }

    public void setHouse(final House house) {
        this.house = house;
    }

    @Override
    public boolean equals(final Object anObject) {

        if (anObject.getClass().getSimpleName().compareTo("Address") != 0)
            return false;
        else {
            Address address = (Address) anObject;
            return addressId == address.getAddressId();
        }
    }

}
