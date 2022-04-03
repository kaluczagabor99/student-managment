package hu.kaluczagabor;

import hu.kaluczagabor.model.Student;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class ImportData {
    public void importToCSV(List<Student> studentList) throws SQLException, IOException {
        FileWriter fr = new FileWriter("data.csv");
        BufferedWriter bw = new BufferedWriter(fr);

        bw.write("id;vezeteknev;keresztnev;neme;szuletesi hely;szuletesi datum\n");
        for (int i = 0; i < studentList.size(); i++) {
            bw.write(studentList.get(i).getId() + ";");
            bw.write(studentList.get(i).getFirstName() + ";");
            bw.write(studentList.get(i).getLastName() + ";");
            bw.write(studentList.get(i).getGender() + ";");
            bw.write(studentList.get(i).getCountryOfBirth() + ";");
            bw.write(studentList.get(i).getDateOfBirth() + "\n");
        }
        bw.close();
    }
}
