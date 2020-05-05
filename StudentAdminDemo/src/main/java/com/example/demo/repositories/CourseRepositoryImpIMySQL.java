package com.example.demo.repositories;

import com.example.demo.models.Course;

import java.util.List;

public class CourseRepositoryImpIMySQL implements ICourseRepository {


    @Override
    public boolean create(Course course) {
        return false;
    }

    @Override
    public Course read(int id) {
        return null;
    }

    @Override
    public List<Course> readAll() {
        return null;
    }

    @Override
    public boolean update(Course course) {
        return false;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }

    @Override
    public int getNumberOfCourses() {
        return 0;
    }
}
