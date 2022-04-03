package hu.kaluczagabor;

import hu.kaluczagabor.model.Dao;
import hu.kaluczagabor.model.GetDataFromUser;
import hu.kaluczagabor.model.Student;
import hu.kaluczagabor.model.StudentDao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Connection conn = null;
        try {
            conn = DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/test", "postgres", "153654");
            Dao model = new StudentDao(conn);
            List<Student> studentList = model.getAllStudent();
            int input;
            do {
                System.out.println("Milyen muveletet szeretnel vegezni az adatbazison?");
                System.out.println("0 - osszes tanulo adatainak megjelenitese");
                System.out.println("1 - uj tanulo hozzaadasa (adatokat ekezetek nelkul erdemes megadni)");
                System.out.println("2 - meglevo tanulo torlese id alapjan");
                System.out.println("3 - meglevo tanulo modositasa");
                System.out.println("4 - adatok importalasa CSV fajlba");
                System.out.println("5 - kilepes");
                System.out.println();
                input = sc.nextInt();

                switch (input) {
                    case 0 -> {
                        studentList = model.getAllStudent();
                        studentList.forEach(System.out::println);
                        System.out.println();
                    }
                    case 1 -> {
                        GetDataFromUser gdf = new GetDataFromUser();
                        model.addStudent(gdf.getStudentData());
                        System.err.println("Tanulo sikeresen hozzaadva az adatbazishoz.");
                    }
                    case 2 -> {
                        System.out.println("Melyik tanulot szerented torolni, add meg az id-t.");
                        int studentDeleteId = sc.nextInt();
                        Student studentToDelete = null;

                        if (model.getStudentById(studentDeleteId) != null) {
                            studentToDelete = model.getStudentById(studentDeleteId);
                            model.deleteStudent(studentToDelete);
                            System.err.println("Sikeres torles");
                        } else {
                            System.out.println("Nem sikerult torolni az adatbazisbol a tanulot");
                        }

                    }
                }
            } while (input != 5);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

