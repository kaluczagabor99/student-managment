package hu.kaluczagabor.model;

public class Student {

    private int id;
    private String firstName;
    private String lastName;
    private String gender;
    private String countryOfBirth;
    private String dateOfBirth;

    public Student() {
    }

    public Student(int id, String firstName, String lastName, String gender, String countryOfBirth, String dateOfBirth) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.countryOfBirth = countryOfBirth;
        this.dateOfBirth = dateOfBirth;
    }

    public Student(String firstName, String lastName, String gender, String countryOfBirth, String dateOfBirth) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.countryOfBirth = countryOfBirth;
        this.dateOfBirth = dateOfBirth;
    }

    @Override
    public String toString() {
        return String.format("id: %s, vezeteknev: %s, keresztnev: %s, nem: %s, szuletesi hely: %s, szuletesi datum: %s",
                id, firstName, lastName, gender, countryOfBirth, dateOfBirth);
    }

    public int getId() {
        return id;
    }
    public String getFirstName() {
        return firstName;
    }
    public String getLastName() {

        return lastName;
    }
    public String getGender() {
        return gender;
    }
    public String getCountryOfBirth() {
        return countryOfBirth;
    }
    public String getDateOfBirth() {
        return dateOfBirth;
    }
}
