package test.model;

/**
 * 
 */
public class Door {

    int doorId;

    public Door(final int id) {
        doorId = id;
    }

    public Door(final int id, final int mas) {
        doorId = id + mas;
    }

    public Door(final int id, final int mas, final int por) {
        doorId = (id + mas) * por;
    }

    public Door() {
    }

    public int getDoorId() {
        return doorId;
    }
}
