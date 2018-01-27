package test.model;

import java.util.Set;

/**
 * 
 */
public class Employee extends Person {

    Set<Company> companies;

    String since;

    public Employee() {

    }

    public Set<Company> getCompanies() {
        return companies;
    }

    public void setCompanies(final Set<Company> companies) {
        this.companies = companies;
    }

    public String getSince() {
        return since;
    }

    public void setSince(final String since) {
        this.since = since;
    }

    @Override
    public boolean equals(final Object anObject) {

        if (anObject.getClass().getSimpleName().compareTo("Employee") != 0)
            return false;
        else {

            Employee employee = (Employee) anObject;
            return personId == employee.getPersonId();

        }
    }

}
