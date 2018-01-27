package test.model;

import java.util.List;

/**
 * 
 */
public class House {

    private int houseId;

    private Address address;

    private List<Room> rooms;

    public House() {
    }

    public int getHouseId() {
        return houseId;
    }

    public void setHouseId(final int houseId) {
        this.houseId = houseId;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(final Address address) {
        this.address = address;
    }

    public List<Room> getRooms() {
        return rooms;
    }

    public void setRooms(final List<Room> rooms) {
        this.rooms = rooms;
    }

    @Override
    public boolean equals(final Object anObject) {

        if (anObject.getClass().getSimpleName().compareTo("House") != 0)
            return false;
        else {
            House house = (House) anObject;
            return houseId == house.getHouseId();
        }
    }

}