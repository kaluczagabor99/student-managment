package hu.kaluczagabor.model;

import java.time.LocalDate;
import java.util.Date;

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
        return  "id = " + id +
                ", vezeteknev = " + firstName +
                ", keresztnev = " + lastName +
                ", nem = " + gender +
                ", szuletesi hely = " + countryOfBirth +
                ", szuletesi datum = " + dateOfBirth;
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
