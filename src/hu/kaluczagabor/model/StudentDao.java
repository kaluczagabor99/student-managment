package hu.kaluczagabor.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDao implements Dao {
    private static final String STUDENT_TABLE = "student";
    private final PreparedStatement getAllStudentPstmt;
    private final PreparedStatement addStudentPstmt;
    private final PreparedStatement getStudentByIdPstmt;
    private final PreparedStatement updateStudentPstmt;
    private final PreparedStatement deleteStudentPstmt;


    public StudentDao(Connection conn) throws SQLException {
        getAllStudentPstmt = conn.prepareStatement("SELECT * FROM " + STUDENT_TABLE);
        deleteStudentPstmt = conn.prepareStatement("DELETE FROM " + STUDENT_TABLE + " WHERE id = ?");
        getStudentByIdPstmt = conn.prepareStatement("SELECT * FROM " + STUDENT_TABLE + " WHERE id = ?");
        addStudentPstmt = conn.prepareStatement(
                "INSERT INTO " + STUDENT_TABLE + " (first_name,last_name,gender,country_of_birth,date_of_birth) VALUES (?,?,?,?,?)");
        updateStudentPstmt = conn.prepareStatement(
                "UPDATE " + STUDENT_TABLE + " SET first_name = ?, last_name = ?, gender = ?,country_of_birth = ?, date_of_birth = ?");
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
        addStudentPstmt.setString(3,   student.getGender());
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
                    rs.getInt(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getString(4),
                    rs.getString(5),
                    rs.getString(6)
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
        updateStudentPstmt.setDate(5, Date.valueOf(student.getDateOfBirth()));

        return updateStudentPstmt.executeUpdate();
    }
}
