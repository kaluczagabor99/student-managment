package hu.kaluczagabor.model;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public interface Dao {

    List<Student> getAllStudent() throws SQLException;
    int addStudent(Student student) throws SQLException;
    Student getStudentById(int id) throws SQLException;
    int deleteStudent(Student student) throws  SQLException;
    int updateStudent(Student student) throws  SQLException;
}

