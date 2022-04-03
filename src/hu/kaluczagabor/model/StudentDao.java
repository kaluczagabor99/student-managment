package hu.kaluczagabor.model;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDao implements Dao {
    private static final String studentTable = "student";
    private Connection conn;
    private final PreparedStatement getAllStudentPstmt;
    private final PreparedStatement addStudentPstmt;
    private final PreparedStatement getStudentByIdPstmt;
    private final PreparedStatement updateStudentPstmt;
    private final PreparedStatement deleteStudentPstmt;


    public StudentDao(Connection conn) throws SQLException {
        this.conn = conn;

        getAllStudentPstmt = conn.prepareStatement("SELECT * FROM " + studentTable);

        addStudentPstmt = conn.prepareStatement(
                "INSERT INTO " + studentTable + " (first_name,last_name,gender,country_of_birth,date_of_birth) VALUES (?,?,?,?,?)");

        getStudentByIdPstmt = conn.prepareStatement("SELECT * FROM " + studentTable + " WHERE id = ?");

        updateStudentPstmt = conn.prepareStatement(
                "UPDATE " + studentTable + " SET first_name = ?, last_name = ?, gender = ?,country_of_birth = ?, date_of_birth = ?");

        deleteStudentPstmt = conn.prepareStatement("DELETE FROM " + studentTable + " WHERE id = ?");
    }

    @Override
    public List<Student> getAllStudent() throws SQLException {
        List<Student> studentList = new ArrayList<>();
        ResultSet rs = getAllStudentPstmt.executeQuery();
        while (rs.next()) {
            studentList.add(
                    new Student(
                            rs.getInt(1),
                            rs.getString(2),
                            rs.getString(3),
                            rs.getString(4),
                            rs.getString(5),
                            rs.getString(6))
            );
        }
        return studentList;
    }

    @Override
    public int addStudent(Student student) throws SQLException {
        addStudentPstmt.setString(1, student.getFirstName());
        addStudentPstmt.setString(2, student.getLastName());
        addStudentPstmt.setString(3, student.getGender());
        addStudentPstmt.setString(4, student.getCountryOfBirth());
        addStudentPstmt.setDate(5, Date.valueOf(student.getDateOfBirth()));

        return addStudentPstmt.executeUpdate();
    }

    @Override
    public Student getStudentById(int id) throws SQLException {
        Student student = null;
        getStudentByIdPstmt.setInt(1, id);

        ResultSet rs = getStudentByIdPstmt.executeQuery();
        if (rs.next()) {
            student = new Student(
                    rs.getString(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getString(4),
                    rs.getString(5)
            );
        }
        return student;
    }

    @Override
    public int deleteStudent(Student student) throws SQLException {
        deleteStudentPstmt.setInt(1, student.getId());
        return deleteStudentPstmt.executeUpdate();
    }

    @Override
    public int updateStudent(Student student) throws SQLException {
        updateStudentPstmt.setString(1, student.getFirstName());
        updateStudentPstmt.setString(2, student.getLastName());
        updateStudentPstmt.setString(3, student.getGender());
        updateStudentPstmt.setString(4, student.getCountryOfBirth());
        updateStudentPstmt.setString(5, student.getDateOfBirth());

        return updateStudentPstmt.executeUpdate();
    }
}
