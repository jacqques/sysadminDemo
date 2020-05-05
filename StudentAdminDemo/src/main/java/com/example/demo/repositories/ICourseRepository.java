package com.example.demo.repositories;

import com.example.demo.models.Course;
import com.example.demo.models.Student;

import java.util.List;

public interface ICourseRepository {

    public boolean create(Course course);

    public Course read(int id);

    public List<Course> readAll();

    public boolean update(Course course);

    public boolean delete(int id);

    public int getNumberOfCourses();

}
