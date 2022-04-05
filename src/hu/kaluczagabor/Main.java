package hu.kaluczagabor;

import hu.kaluczagabor.model.Dao;
import hu.kaluczagabor.model.GetDataFromUser;
import hu.kaluczagabor.model.Student;
import hu.kaluczagabor.model.StudentDao;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;

public class Main {

    private static final String LOCATION =
            "C:\\Users\\kaluczagabor\\IdeaProjects\\studentManagement\\src\\hu\\kaluczagabor\\resources\\JDBCSettings.properties";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        try {
            Connection conn = DriverManager.getConnection(initConfigFile()[0], initConfigFile()[1], initConfigFile()[2]);
            Dao model = new StudentDao(conn);
            List<Student> studentList = model.getAllStudent();
            int input;
            do {
                input = getInput(sc);

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
                        System.out.println("Melyik tanulot szeretned torolni? Add meg az id-t.");
                        Student studentToDelete = model.getStudentById(sc.nextInt());
                        model.deleteStudent(studentToDelete);
                        System.err.println("Sikeresen toroltuk a tanulot: " +
                                studentToDelete.getFirstName() + " " + studentToDelete.getLastName());
                    }
                    case 3 -> {
                        System.out.println("Melyik tanulot szerented modositani? Add meg az id-t.");
                        Student studentToModify = model.getStudentById(sc.nextInt());
                        GetDataFromUser gdf = new GetDataFromUser();
                        Student updatedStudent = gdf.getStudentData();
                        model.updateStudent(updatedStudent);
                    }
                    case 4 -> {
                        ImportData importData = new ImportData();
                        try {
                            importData.importToCSV(model.getAllStudent());
                            System.out.println("Sikeresen importalva a helyi mappaba");
                        } catch (IOException e) {
                            System.out.println("Sikertelen importalas: " + e.getMessage());
                        }
                    }
                }
            } while (input != 5);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static int getInput(Scanner sc) {
        int input;
        System.out.println("Milyen muveletet szeretnel vegezni az adatbazison?");
        System.out.println("0 - osszes tanulo adatainak megjelenitese");
        System.out.println("1 - uj tanulo hozzaadasa (adatokat ekezetek nelkul erdemes megadni)");
        System.out.println("2 - meglevo tanulo torlese id alapjan");
        System.out.println("3 - meglevo tanulo modositasa");
        System.out.println("4 - osszes adat importalasa CSV fajlba");
        System.out.println("5 - kilepes");
        System.out.println();
        input = sc.nextInt();
        return input;
    }

    public static String[] initConfigFile() {
        String[] propData = new String[3];
        Properties properties = new Properties();
        File conf = new File(LOCATION);
        if (conf.exists() && conf.canRead()) {
            try {
                properties.load(new FileInputStream(conf));
                String dbURL = properties.getProperty("db.conn.url");
                String dbUserName = properties.getProperty("db.username");
                String dbPassword = properties.getProperty("db.password");

                propData[0] = dbURL;
                propData[1] = dbUserName;
                propData[2] = dbPassword;

            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
        return propData;
    }
}

