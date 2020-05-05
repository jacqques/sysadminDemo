package com.example.demo.repositories;

import com.example.demo.models.Student;

import java.util.*;

public class InMemoryStudentRepositoryImpl implements IStudentRepository{
    private List<Student> inMemoryDatabase;

    public InMemoryStudentRepositoryImpl(){
        this.inMemoryDatabase = new ArrayList<Student>(
                Arrays.asList(
                        new Student(1, "Nicklas","Frederiksen", new Date(12312), "31134115-1231"),
                        new Student(2, "Bent","Karlsen", new Date(2141241), "31134115-4112"),
                        new Student(3, "Bob","Alicesen",new Date(12424141), "23312414-5551"),
                        new Student(3, "Bob","Alicesen",new Date(12424141), "23312414-5551"),
                        new Student(3, "Bob","Alicesen",new Date(12424141), "23312414-5551"),
                        new Student(3, "Bob","Alicesen",new Date(12424141), "23312414-5551"),
                        new Student(3, "Bob","Alicesen",new Date(12424141), "23312414-5551"),
                        new Student(3, "Bob","Alicesen",new Date(12424141), "23312414-5551"),
                        new Student(3, "Bob","Alicesen",new Date(12424141), "23312414-5551")

                )
        );
    }

    @Override
    public int getNumberOfStudents(){
        return inMemoryDatabase.size();
    }

    @Override
    public boolean create(Student student) {
        inMemoryDatabase.add(student);
        return false;
    }

    @Override
    public Student read(int id) {
        for(Student stu : inMemoryDatabase){
            if(stu.getId() == id){
                return stu;
            }
        }
        return null;
    }

    @Override
    public List<Student> readAll() {
        return inMemoryDatabase;
    }

    @Override
    public boolean update(Student student) {
        return false;
    }

    @Override
    public boolean delete(int id) {
        boolean wasRemoved = false;
        List<Student> toRemove = new LinkedList<>();
        for (Student student : inMemoryDatabase){
            if (student.getId()==id){
                toRemove.add(student); //aren't deleting here, because you can't loop and delete with this for loop.
            }
        }
        for (Student student : toRemove){
            inMemoryDatabase.remove(student);
            wasRemoved = true;
        }
        return wasRemoved;
    }
}
