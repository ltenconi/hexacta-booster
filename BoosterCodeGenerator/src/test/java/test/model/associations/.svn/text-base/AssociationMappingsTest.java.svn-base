package test.model.associations;

import java.io.File;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import junit.framework.TestCase;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import test.hexacta.booster.providers.CodeGeneratorConfigurationProvider;
import test.hexacta.booster.providers.ProjectConfigurationToolProvider;
import test.model.Address;
import test.model.Company;
import test.model.Course;
import test.model.Employee;
import test.model.House;
import test.model.Person;
import test.model.Room;
import test.model.Student;
import test.model.daos.AddressDAO;
import test.model.daos.CompanyDAO;
import test.model.daos.EmployeeDAO;
import test.model.daos.HouseDAO;
import test.model.daos.PersonDAO;
import test.model.daos.StudentDAO;

import com.hexacta.booster.codegeneration.GenerationInfo;
import com.hexacta.booster.codegeneration.configuration.CodeGeneratorConfiguration;
import com.hexacta.booster.codegeneration.configuration.NotSupportedClassException;
import com.hexacta.booster.codegeneration.persistence.OrmGenerator;
import com.hexacta.booster.codegeneration.persistence.ormmodel.OrmMapping;
import com.hexacta.booster.codegeneration.persistence.strategy.EntityBranchStrategy;
import com.hexacta.booster.project.configuration.HibernateDaos;
import com.hexacta.booster.project.configuration.JUnit3;
import com.hexacta.booster.project.configuration.JavaProjectType;
import com.hexacta.booster.project.configuration.NHibernate;
import com.hexacta.booster.project.configuration.NoBuildTool;
import com.hexacta.booster.project.configuration.NoDtoTool;
import com.hexacta.booster.project.configuration.NoFramework;
import com.hexacta.booster.project.configuration.NoVersioningSystem;
import com.hexacta.booster.project.configuration.NoViewLayerFramework;
import com.hexacta.booster.project.configuration.ProjectConfigurationTool;

/**
 * 
 */
public class AssociationMappingsTest extends TestCase {

    private Configuration anHibernateConfiguration;

    private SessionFactory aSessionFactory;

    private File mappingsDirectory = new File("./src/test/resources/mappings/");

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        mappingsDirectory.mkdirs();
        generateMappings();
        anHibernateConfiguration = new Configuration();
        anHibernateConfiguration.configure("hibernateTest.cfg.xml");
        anHibernateConfiguration.addDirectory(mappingsDirectory);
        aSessionFactory = anHibernateConfiguration.buildSessionFactory();
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
        deleteMappings();
    }

    private static void generateMappings() {

        CodeGeneratorConfiguration aCodeGeneratorConfiguration;
        aCodeGeneratorConfiguration = new CodeGeneratorConfigurationProvider().getCodeGeneratorConfiguration(
                "./src/test/java/test/model/", "./src/test/resources/mappings/");
        aCodeGeneratorConfiguration.setMappingHierarchyCuttingStrategy(new EntityBranchStrategy());

        ProjectConfigurationTool projectConfigurationTool = new ProjectConfigurationToolProvider()
                .getProjectConfigurationTool();
        OrmGenerator ormGenerator = new OrmGenerator();
        List<OrmMapping> ormMappings;
        try {
            ormMappings = ormGenerator.generateOrmMappings(aCodeGeneratorConfiguration, new ProjectConfigurationTool(
                    new NoBuildTool(), new NoVersioningSystem(), new JavaProjectType(), new NHibernate(),
                    new NoDtoTool(), new HibernateDaos(), new NoFramework(), new JUnit3(),new NoViewLayerFramework()));
            ormGenerator.generateHibernateMappings(ormMappings, aCodeGeneratorConfiguration, projectConfigurationTool,
                    new GenerationInfo());
        } catch (NotSupportedClassException e) {
            e.printStackTrace();
        }

    }

    public void testOneToOneUnidirectionalAssociation() {

        Session aSession = aSessionFactory.openSession();
        Transaction aTransaction = aSession.beginTransaction();

        Company company = new Company();
        company.setName("Hexacta");
        Address address = new Address();
        address.setName("Alsina");
        company.setAddress(address);

        CompanyDAO companyHibernateDAO = new CompanyDAO();
        companyHibernateDAO.setSession(aSession);
        companyHibernateDAO.save(company);

        aTransaction.commit();

        Company companyRecovered = companyHibernateDAO.findById(company.getCompanyId());

        assertEquals(company.getAddress(), companyRecovered.getAddress());

    }

    public void testOneToManyUnidirectionalAssociation() {

        Session aSession = aSessionFactory.openSession();
        Transaction aTransaction = aSession.beginTransaction();

        Room room1 = new Room();
        room1.setDoorsCount(2);

        Room room2 = new Room();
        room2.setDoorsCount(3);

        Room room3 = new Room();
        room3.setDoorsCount(1);

        List<Room> rooms = new LinkedList<Room>();
        rooms.add(room1);
        rooms.add(room2);
        rooms.add(room3);

        House house = new House();
        house.setRooms(rooms);

        HouseDAO houseHibernateDAO = new HouseDAO();
        houseHibernateDAO.setSession(aSession);
        houseHibernateDAO.save(house);

        aTransaction.commit();

        House houseRecovered = houseHibernateDAO.findById(house.getHouseId());

        assertTrue(houseRecovered.getRooms().size() == 3);
        assertEquals(rooms, houseRecovered.getRooms());

        aSession.close();
    }

    public void testManyToOneUnidirectionalAssociation() {

        Session aSession = aSessionFactory.openSession();
        Transaction aTransaction = aSession.beginTransaction();

        Company company1 = new Company();
        company1.setName("Hexacta");

        Company company2 = new Company();
        company2.setName("Surland");

        Address address = new Address();
        address.setName("Belgrano");
        address.setNumber(133);

        company1.setAddress(address);
        company2.setAddress(address);

        CompanyDAO companyHibernateDAO = new CompanyDAO();
        companyHibernateDAO.setSession(aSession);
        companyHibernateDAO.save(company1);
        companyHibernateDAO.save(company2);

        aTransaction.commit();

        Company company1Recovered = companyHibernateDAO.findById(company1.getCompanyId());
        Company company2Recovered = companyHibernateDAO.findById(company2.getCompanyId());

        assertEquals(address, company1Recovered.getAddress());
        assertEquals(address, company2Recovered.getAddress());

        aSession.close();

    }

    public void testManyToManyUnidirectionalAssociation() {

        Session aSession = aSessionFactory.openSession();
        Transaction aTransaction = aSession.beginTransaction();

        Course course1 = new Course();
        course1.setName("Matematica");

        Course course2 = new Course();
        course2.setName("Ingles");

        Course course3 = new Course();
        course3.setName("Fisica");

        Student student1 = new Student();
        student1.setName("Federico");

        Student student2 = new Student();
        student2.setName("Lucas");

        Course[] student1Courses = new Course[2];
        student1Courses[0] = course1;
        student1Courses[1] = course3;

        Course[] student2Courses = new Course[2];
        student2Courses[0] = course2;
        student2Courses[1] = course3;

        student1.setCourses(student1Courses);

        student2.setCourses(student2Courses);

        StudentDAO studentHibernateDAO = new StudentDAO();
        studentHibernateDAO.setSession(aSession);
        studentHibernateDAO.save(student1);

        aTransaction.commit();

        try {

            studentHibernateDAO.save(student2);
            aTransaction.commit();
            fail();
        } catch (RuntimeException e) {
            assertTrue(true);
        } finally {

            aSession.close();

        }

    }

    public void testOneToManyBidirectionalAssociation() { // muchos a uno es
        // igual

        Session aSession = aSessionFactory.openSession();
        Transaction aTransaction = aSession.beginTransaction();

        Person person1 = new Person();
        person1.setName("Federico");

        Person person2 = new Person();
        person2.setName("Lucas");

        Address address = new Address();
        address.setName("Zapiolla");
        address.setNumber(133);

        person1.setAddress(address);
        person2.setAddress(address);

        Set<Person> people = new HashSet<Person>();
        people.add(person1);
        people.add(person2);

        address.setPeople(people);

        PersonDAO personHibernateDAO = new PersonDAO();
        personHibernateDAO.setSession(aSession);

        AddressDAO addressHibernateDAO = new AddressDAO();
        addressHibernateDAO.setSession(aSession);

        addressHibernateDAO.save(address);

        aTransaction.commit();

        Person person1Recovered = personHibernateDAO.findById(person1.getPersonId());

        assertEquals(address.getAddressId(), person1Recovered.getAddress().getAddressId());

        Address addressRecovered = addressHibernateDAO.findById(address.getAddressId());

        assertEquals(people, addressRecovered.getPeople());

        aSession.close();

    }

    public void testOneToOneBidirectionalAssociation() {

        Session aSession = aSessionFactory.openSession();
        Transaction aTransaction = aSession.beginTransaction();

        House house = new House();

        Address address = new Address();
        address.setName("Zapiolla");
        address.setNumber(1214);

        address.setHouse(house);
        house.setAddress(address);

        HouseDAO houseHibernateDAO = new HouseDAO();
        houseHibernateDAO.setSession(aSession);
        houseHibernateDAO.save(house);

        aTransaction.commit();

        House houseRecovered = houseHibernateDAO.findById(house.getHouseId());
        assertEquals(houseRecovered.getAddress(), address);

        AddressDAO addressHibernateDAO = new AddressDAO();
        addressHibernateDAO.setSession(aSession);
        Address addressRecovered = addressHibernateDAO.findById(address.getAddressId());
        assertEquals(addressRecovered.getHouse(), house);

        House anotherHouse = new House();

        anotherHouse.setAddress(addressRecovered);

        try {
            houseHibernateDAO.save(anotherHouse);
            fail();
        } catch (RuntimeException e) {
            assertTrue(true);
        }

        aSession.close();
    }

    public void testManyToManyBidirectionalAssociation() {

        Session aSession = aSessionFactory.openSession();
        Transaction aTransaction = aSession.beginTransaction();

        Employee employee1 = new Employee();
        employee1.setName("Federico");

        Employee employee2 = new Employee();
        employee2.setName("Lucas");

        Employee employee3 = new Employee();
        employee3.setName("Virginia");

        Company company1 = new Company();
        company1.setName("Hexacta");

        Company company2 = new Company();
        company2.setName("Google");

        Company company3 = new Company();
        company3.setName("Microsoft");

        Set<Company> employee1Companies = new HashSet<Company>();
        employee1Companies.add(company1);
        employee1Companies.add(company3);

        employee1.setCompanies(employee1Companies);

        Set<Company> employee2Companies = new HashSet<Company>();
        employee2Companies.add(company2);
        employee2Companies.add(company3);

        employee2.setCompanies(employee2Companies);

        Set<Company> employee3Companies = new HashSet<Company>();
        employee3Companies.add(company1);
        employee3Companies.add(company2);

        employee3.setCompanies(employee3Companies);

        Map<Integer, Employee> company1Employees = new HashMap<Integer, Employee>();
        company1Employees.put(1, employee1);
        company1Employees.put(2, employee3);

        company1.setEmployees(company1Employees);

        Map<Integer, Employee> company2Employees = new HashMap<Integer, Employee>();
        company2Employees.put(1, employee2);
        company2Employees.put(2, employee3);

        company2.setEmployees(company2Employees);

        Map<Integer, Employee> company3Employees = new HashMap<Integer, Employee>();
        company3Employees.put(1, employee1);
        company3Employees.put(2, employee2);

        company3.setEmployees(company3Employees);

        EmployeeDAO employeeHibernateDAO = new EmployeeDAO();
        employeeHibernateDAO.setSession(aSession);

        employeeHibernateDAO.save(employee1);
        employeeHibernateDAO.save(employee2);
        employeeHibernateDAO.save(employee3);

        aTransaction.commit();

        Employee employee1Recovered = employeeHibernateDAO.findById(employee1.getPersonId());
        Employee employee2Recovered = employeeHibernateDAO.findById(employee2.getPersonId());
        Employee employee3Recovered = employeeHibernateDAO.findById(employee3.getPersonId());

        assertEquals(employee1Companies, employee1Recovered.getCompanies());
        assertEquals(employee2Companies, employee2Recovered.getCompanies());
        assertEquals(employee3Companies, employee3Recovered.getCompanies());

        CompanyDAO companyHibernateDAO = new CompanyDAO();
        companyHibernateDAO.setSession(aSession);

        Company company1Recovered = companyHibernateDAO.findById(company1.getCompanyId());
        Company company2Recovered = companyHibernateDAO.findById(company2.getCompanyId());
        Company company3Recovered = companyHibernateDAO.findById(company3.getCompanyId());

        assertTrue(company1Recovered.getEmployees().size() == 2);
        assertEquals(company1Employees.get("Virginia"), company1Recovered.getEmployees().get("Virginia"));
        assertEquals(company1Employees.get("Federico"), company1Recovered.getEmployees().get("Federico"));
        assertTrue(company2Recovered.getEmployees().size() == 2);
        assertEquals(company2Employees.get("Virginia"), company2Recovered.getEmployees().get("Virginia"));
        assertEquals(company2Employees.get("Lucas"), company2Recovered.getEmployees().get("Lucas"));
        assertTrue(company3Recovered.getEmployees().size() == 2);
        assertEquals(company3Employees.get("Federico"), company3Recovered.getEmployees().get("Federico"));
        assertEquals(company3Employees.get("Lucas"), company3Recovered.getEmployees().get("Lucas"));

        aSession.close();
    }

    public void testTwoSameClassInternalColaborators() {

        Session aSession = aSessionFactory.openSession();
        Transaction aTransaction = aSession.beginTransaction();

        Company company = new Company();
        company.setName("Hexacta");

        Address address = new Address();
        address.setName("Alsina");
        company.setAddress(address);

        Address addressBahia = new Address();
        address.setName("Colon");
        company.setBahiaBranchAddress(addressBahia);

        CompanyDAO companyHibernateDAO = new CompanyDAO();
        companyHibernateDAO.setSession(aSession);
        companyHibernateDAO.save(company);

        aTransaction.commit();

        Company companyRecovered = companyHibernateDAO.findById(company.getCompanyId());

        assertEquals(company.getAddress(), companyRecovered.getAddress());
        assertEquals(company.getBahiaBranchAddress(), companyRecovered.getBahiaBranchAddress());

    }

    private void deleteMappings() {

        File aDirectory = new File("./src/test/resources/mappings/");
        deleteDirectory(aDirectory);
        mappingsDirectory.deleteOnExit();

    }

    private void deleteDirectory(final File path) {
        if (path.exists()) {
            File[] files = path.listFiles();
            for (File file : files) {
                if (file.isDirectory()) {
                    deleteDirectory(file);
                } else {
                    file.delete();
                }
            }
        }
    }

}
