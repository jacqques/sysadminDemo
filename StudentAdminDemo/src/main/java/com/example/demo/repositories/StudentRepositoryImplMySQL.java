package com.example.demo.repositories;

import com.example.demo.models.Student;
import com.example.demo.util.DatabaseConnectionManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentRepositoryImplMySQL implements IStudentRepository {
    private Connection conn;

    public StudentRepositoryImplMySQL(){
        this.conn = DatabaseConnectionManager.getDatabaseConnection();
    }

    @Override
    public boolean create(Student student) {
        try {
            String insert = "INSERT INTO student VALUES (?,?,?,?,?)";
            PreparedStatement insertStatement = conn.prepareStatement(insert);
            insertStatement.setInt(1,student.getId());
            insertStatement.setString(2,student.getFirstName());
            insertStatement.setString(3,student.getLastName());
            insertStatement.setDate(4,new java.sql.Date(student.getEnrollmentDate().getTime()));
            insertStatement.setString(5,student.getCpr());
            insertStatement.executeUpdate();
            return true;
        }
        catch (SQLException e){
            System.out.println("connection failed");
            System.out.println(e.getMessage());
        }
        return false;
    }

    @Override
    public Student read(int id) {
        Student studentToReturn = new Student();
        try {
            PreparedStatement getSingleStudent = conn.prepareStatement("SELECT * FROM student WHERE id=?");
            getSingleStudent.setInt(1,id);
            ResultSet rs = getSingleStudent.executeQuery();
            while(rs.next()){
                studentToReturn = new Student();
                studentToReturn.setId(rs.getInt(1));
                studentToReturn.setFirstName(rs.getString(2));
                studentToReturn.setLastName(rs.getString(3));
                studentToReturn.setEnrollmentDate(rs.getDate(4));
                studentToReturn.setCpr(rs.getString(5));
            }
        }
        catch(SQLException s){
            s.printStackTrace();
        }
        return studentToReturn;
    }

    @Override
    public List<Student> readAll() {
        List<Student> allStudents = new ArrayList<Student>();
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM student");
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Student tempStudent = new Student();
                tempStudent.setId(rs.getInt(1));
                tempStudent.setFirstName(rs.getString(2));
                tempStudent.setLastName(rs.getString(3));
                tempStudent.setEnrollmentDate(rs.getDate(4));
                tempStudent.setCpr(rs.getString(5));
                allStudents.add(tempStudent);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allStudents;
    }

    @Override
    public boolean update(Student student) {
        try {
            String updateThis = "UPDATE student SET firstName=?, lastName=?, enrollmentDate=?, cpr=? WHERE id=?";
            PreparedStatement updateStatement = conn.prepareStatement(updateThis);
            updateStatement.setString(1, student.getFirstName());
            updateStatement.setString(2, student.getLastName());
            updateStatement.setDate(3, new java.sql.Date(student.getEnrollmentDate().getTime()));
            updateStatement.setString(4, student.getCpr());
            updateStatement.setInt(5,student.getId());
            updateStatement.executeUpdate();
            return true;
        }
        catch (SQLException e){
            System.out.println("SQL FAILED!!!");
            System.out.println(e.getMessage());
        }

        return false;
    }


    @Override
    public int getNumberOfStudents(){
        int count = 0;
        try {
            String statement = "SELECT count(*) FROM student";
            PreparedStatement countStatement = conn.prepareStatement(statement);
            ResultSet results = countStatement.executeQuery();
            results.next();
            count = results.getInt(1);
        }
        catch (SQLException e){
            System.out.println("fejl ved MySQL");
            System.out.println(e.getMessage());
        }
        return count;
    }

    @Override
    public boolean delete(int id) {
        try {
            String delete = "DELETE FROM student WHERE id=?";
            PreparedStatement deleteStatement = conn.prepareStatement(delete);
            deleteStatement.setInt(1,id);
            deleteStatement.executeUpdate();
            return true;
        }
        catch (SQLException e){
            System.out.println("MySQL fejlede");
            System.out.println(e.getMessage());
        }
        return false;
    }
}
