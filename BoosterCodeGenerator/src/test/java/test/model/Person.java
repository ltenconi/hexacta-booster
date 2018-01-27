package test.model;

/**
 * 
 */
public class Person {

    int personId;

    String name;

    Address address;

    public Person() {

    }

    public int getPersonId() {
        return personId;
    }

    public void setPersonId(final int personId) {
        this.personId = personId;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(final Address address) {
        this.address = address;
    }

}
