package test.model;

/**
 * Represents a student object in the hibernate tutorial Student Register
 * example.
 */
public class Student extends Person {
    // unique student id
    // private int studentId;
    // first name of the student
    private String firstName;

    // last name of the student
    private String lastName;

    // set of courses that the student is related/registered for
    private Course[] courses;

    public Student() {
        super();
    }

    public Student(final int id, final String nombre) {

        firstName = nombre;

    }

    /**
     * Creates a new instance of Student.
     * 
     * @param firstName
     *            first name.
     * @param lastName
     *            last name.
     * @param address
     *            address.
     */
    public Student(final String firstName, final String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;

    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(final String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(final String lastName) {
        this.lastName = lastName;
    }

    public Course[] getCourses() {
        return courses;
    }

    public void setCourses(final Course[] courses) {
        this.courses = courses;
    }

    public String clear() {
        firstName = "";
        lastName = "";
        return "clear";
    }
}
