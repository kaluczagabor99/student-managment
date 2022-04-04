package hu.kaluczagabor.model;

import java.util.Scanner;

public class GetDataFromUser {

    public Student getStudentData() {
        Scanner sc = new Scanner(System.in);

        System.out.println("Add meg a tanulo vezeteknevet: ");
        String firstName = sc.nextLine();

        System.out.println("Add meg a tanulo keresztnevet: ");
        String lastName = sc.nextLine();

        System.out.println("Add meg a tanulo nemet: pl: (Ferfi, No)");
        String gender = sc.nextLine();

        System.out.println("Add meg a tanulo szuletesi helyet:");
        String countryOfBirth = sc.nextLine();

        System.out.println("Add meg a tanulo szuletesi datumat: (yyyy-mm-dd) ");
        String dateOfBirth = sc.nextLine();

        return new Student(firstName, lastName, gender, countryOfBirth, dateOfBirth);
    }
}
